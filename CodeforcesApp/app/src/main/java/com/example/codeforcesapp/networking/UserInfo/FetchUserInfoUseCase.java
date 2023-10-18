package com.example.codeforcesapp.networking.UserInfo;

import android.content.Context;
import android.util.Log;

import com.example.codeforcesapp.data.userinfo.UserInfoApiResponse;
import com.example.codeforcesapp.data.userinfo.UserInfo;
import com.example.codeforcesapp.data.userinfo.UserInfoModel;
import com.example.codeforcesapp.networking.common.FetchItemsUseCase;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FetchUserInfoUseCase extends FetchItemsUseCase<UserInfoApiResponse, UserInfoModel> {
    private UserInfoApiResponse cfUserInfo;
    private List<UserInfo> cfUserInfoEntryList;
    private ArrayList<UserInfoModel> mList= new ArrayList<>();

    private String userHandler;


    public static FetchUserInfoUseCase fetchUserInfoUseCase;

    private FetchUserInfoUseCase(Context ctx){
        super(ctx);
    }

    public static FetchUserInfoUseCase getInstance(Context ctx) {
        if(fetchUserInfoUseCase==null){
            fetchUserInfoUseCase= new FetchUserInfoUseCase(ctx);
        }

        return fetchUserInfoUseCase;
    }

    @Override
    protected void fetchItems(boolean ignoreCacheAndForceRetrieve, final boolean notifyListeners) {
        if(!hasUserHandler())  return;

        if(!ignoreCacheAndForceRetrieve  && notifyListeners && mList.size()>0){
            notifySuccess();
            return;
        }

        if(ignoreCacheAndForceRetrieve)  mList.clear();

        Call<UserInfoApiResponse> call= cfApi.getUserInfo(userHandler);

        call.enqueue(new Callback<UserInfoApiResponse>() {
            @Override
            public void onResponse(Call<UserInfoApiResponse> call, Response<UserInfoApiResponse> response) {
                cfUserInfo = response.body();


                if(cfUserInfo==null){
                    notifyError(NO_HANDLER_MSG());
                    return;
                }

                if(!cfUserInfo.getStatus().equals("OK")){
                    notifyError(cfUserInfo.getComment());
                    return;
                }


                process(cfUserInfo);

                if(mList.isEmpty())  notifyError(NO_SUBMISSION_MSG());
                else if(notifyListeners) notifySuccess();
            }

            @Override
            public void onFailure(Call<UserInfoApiResponse> call, Throwable t) {
                notifyError(NO_INTERNET);
            }
        });
    }



    @Override
    protected void process(UserInfoApiResponse cfUserInfo) {
        cfUserInfoEntryList= cfUserInfo.getResultUserInfoList();

        for(UserInfo entry: cfUserInfoEntryList){
            UserInfoModel userInfoModel= new UserInfoModel.Builder()
                                        .firstName(entry.getFirstName())
                                        .lastName(entry.getLastName())
                                        .rating(entry.getRating())
                                        .maxRating(entry.getMaxRating())
                                        .rank(entry.getRank())
                                        .maxRank(entry.getMaxRank())
                                        .Country(entry.getCountry())
                                        .proPicUrl(entry.getTitlePhoto())
                                        .build();

            mList.add(userInfoModel);
        }
    }

    @Override
    protected void notifyError(String errorMsg) {
        for(OnFetchItemsListener<UserInfoModel> listener: getListeners())
            listener.onFetchItemListFetchFailed(errorMsg);
    }

    @Override
    protected void notifySuccess() {
        Log.i("dodo", "notifySuccess: Yes");

        for(OnFetchItemsListener<UserInfoModel> listener: getListeners())
            listener.onItemListFetched(mList);
    }

    public String getUserHandler() {
        return userHandler;
    }

    public void setUserHandler(String userHandler) {
        this.userHandler = userHandler;
    }

    public boolean hasUserHandler(){
        return userHandler!=null && !userHandler.equals("");
    }

    private String NO_HANDLER_MSG(){
        return "User "+userHandler+" Doesn't Exist";
    }

    private String NO_SUBMISSION_MSG(){
        return "User "+userHandler+" Has No Submissions";
    }
}
