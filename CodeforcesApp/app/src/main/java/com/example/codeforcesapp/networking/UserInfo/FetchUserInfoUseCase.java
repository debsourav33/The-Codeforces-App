package com.example.codeforcesapp.networking.UserInfo;

import android.util.Log;

import com.example.codeforcesapp.data.userinfo.CFUserInfo;
import com.example.codeforcesapp.data.userinfo.CFUserInfoEntry;
import com.example.codeforcesapp.data.userinfo.UserInfoModel;
import com.example.codeforcesapp.networking.common.FetchItemsUseCase;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FetchUserInfoUseCase extends FetchItemsUseCase<CFUserInfo, UserInfoModel> {
    private CFUserInfo cfUserInfo;
    private List<CFUserInfoEntry> cfUserInfoEntryList;
    private ArrayList<UserInfoModel> mList= new ArrayList<>();

    private String userHandler;


    public static FetchUserInfoUseCase fetchUserInfoUseCase;

    private FetchUserInfoUseCase(){

    }

    public static FetchUserInfoUseCase getInstance() {
        if(fetchUserInfoUseCase==null){
            fetchUserInfoUseCase= new FetchUserInfoUseCase();
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

        Call<CFUserInfo> call= cfApi.getUserInfo(userHandler);

        call.enqueue(new Callback<CFUserInfo>() {
            @Override
            public void onResponse(Call<CFUserInfo> call, Response<CFUserInfo> response) {
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
            public void onFailure(Call<CFUserInfo> call, Throwable t) {
                notifyError(NO_INTERNET);
            }
        });
    }

    @Override
    protected void process(CFUserInfo cfUserInfo) {
        cfUserInfoEntryList= cfUserInfo.getResultUserInfoList();

        for(CFUserInfoEntry entry: cfUserInfoEntryList){
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
