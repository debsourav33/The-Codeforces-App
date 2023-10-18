package com.example.codeforcesapp.networking.UserSubmission;

import android.content.Context;

import com.example.codeforcesapp.data.usersubmission.UserSubmissionApiResponse;
import com.example.codeforcesapp.data.usersubmission.UserSubmissionEntry;
import com.example.codeforcesapp.data.usersubmission.UserSubmissionModel;
import com.example.codeforcesapp.networking.common.FetchItemsUseCase;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FetchUserSubmissionListUseCase extends FetchItemsUseCase<UserSubmissionApiResponse, UserSubmissionModel> {
    protected UserSubmissionApiResponse cfUserSubmission;
    protected List<UserSubmissionEntry> entryList;
    protected ArrayList<UserSubmissionModel> mList= new ArrayList<>();


    private String userHandler;

    public static FetchUserSubmissionListUseCase fetchUserSubmissionListUseCase;

    private FetchUserSubmissionListUseCase(Context ctx){
        super(ctx);
    }


    public static FetchUserSubmissionListUseCase getInstance(Context ctx) {
        if(fetchUserSubmissionListUseCase ==null) {
            fetchUserSubmissionListUseCase = new FetchUserSubmissionListUseCase(ctx);
        }

        return fetchUserSubmissionListUseCase;
    }

    @Override
    protected void fetchItems(boolean ignoreCacheAndForceRetrieve, final boolean notifyListeners) {
        if(userHandler==null || userHandler.equals(""))  return;

        if(!ignoreCacheAndForceRetrieve  && notifyListeners && mList.size()>0){
            notifySuccess();
            return;
        }

        if(ignoreCacheAndForceRetrieve)  mList.clear();

        Call<UserSubmissionApiResponse> call= cfApi.getUserSubmission(userHandler);

        call.enqueue(new Callback<UserSubmissionApiResponse>() {
            @Override
            public void onResponse(Call<UserSubmissionApiResponse> call, Response<UserSubmissionApiResponse> response) {
                cfUserSubmission = response.body();

                if(cfUserSubmission==null){
                    notifyError(NO_HANDLER_MSG());
                    return;
                }

                if(!cfUserSubmission.getStatus().equals("OK")){
                    notifyError(cfUserSubmission.getComment());
                    return;
                }


                process(cfUserSubmission);

                if(mList.isEmpty())  notifyError(NO_SUBMISSION_MSG());
                else if(notifyListeners) notifySuccess();
            }

            @Override
            public void onFailure(Call<UserSubmissionApiResponse> call, Throwable t) {
                notifyError(NO_INTERNET);

            }
        });
    }

    @Override
    protected void process(UserSubmissionApiResponse response) {
        entryList= response.getResultSubmissionList();

        for(UserSubmissionEntry entry: entryList){
            UserSubmissionModel model= new UserSubmissionModel(entry.getId(),entry.getVerdict());
            mList.add(model);
        }
    }

    @Override
    protected void notifyError(String errorMsg) {
        for(OnFetchItemsListener<UserSubmissionModel> listener: getListeners())
            listener.onFetchItemListFetchFailed(errorMsg);
    }

    @Override
    protected void notifySuccess() {
        for(OnFetchItemsListener<UserSubmissionModel> listener: getListeners())
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
