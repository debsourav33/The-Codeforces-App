package com.example.codeforcesapp.networking.UserSubmission;

import android.util.Log;

import com.example.codeforcesapp.networking.Contest.ContestModel;
import com.example.codeforcesapp.networking.common.FetchItemsUseCase;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FetchUserSubmissionListUseCase extends FetchItemsUseCase<CFUserSubmission,UserSubmissionModel> {
    protected CFUserSubmission cfUserSubmission;
    protected List<CFUserSubmissionEntry> entryList;
    protected ArrayList<UserSubmissionModel> mList= new ArrayList<>();


    private String userHandler;

    public static FetchUserSubmissionListUseCase fetchUserSubmissionListUseCase;

    private FetchUserSubmissionListUseCase(){

    }


    public static FetchUserSubmissionListUseCase getInstance() {
        if(fetchUserSubmissionListUseCase ==null) {
            fetchUserSubmissionListUseCase = new FetchUserSubmissionListUseCase();
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

        Call<CFUserSubmission> call= cfAPI.getUserSubmission(userHandler);

        call.enqueue(new Callback<CFUserSubmission>() {
            @Override
            public void onResponse(Call<CFUserSubmission> call, Response<CFUserSubmission> response) {
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
            public void onFailure(Call<CFUserSubmission> call, Throwable t) {
                notifyError(NO_INTERNET);

            }
        });
    }

    @Override
    protected void process(CFUserSubmission response) {
        entryList= response.getResultSubmissionList();

        for(CFUserSubmissionEntry entry: entryList){
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
