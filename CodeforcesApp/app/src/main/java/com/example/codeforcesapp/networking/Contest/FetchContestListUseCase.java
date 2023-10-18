package com.example.codeforcesapp.networking.Contest;

import android.content.Context;
import android.util.Log;

import com.example.codeforcesapp.data.contest.ContestApiResponse;
import com.example.codeforcesapp.data.contest.ContestEntity;
import com.example.codeforcesapp.data.contest.ContestModel;
import com.example.codeforcesapp.networking.common.FetchItemsUseCase;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public abstract class FetchContestListUseCase extends FetchItemsUseCase<ContestApiResponse, ContestEntity> {
    protected ContestApiResponse contestApiResponse;
    protected List<ContestEntity> contestEntityList;
    protected ArrayList<ContestModel> mList= new ArrayList<>();
    protected List<ContestEntity> mContestEntities = new ArrayList<>();

    public FetchContestListUseCase(Context context) {
        super(context);
    }

    @Override
    protected void fetchItems(boolean ignoreCacheAndForceRetrieve, final boolean notifyListeners) {
        if(!ignoreCacheAndForceRetrieve  && notifyListeners && mList.size()>0){
            notifySuccess();
            return;
        }

        if(ignoreCacheAndForceRetrieve)  mList.clear();

        Call<ContestApiResponse> call= cfApi.getContestList();

        call.enqueue(new Callback<ContestApiResponse>() {
            @Override
            public void onResponse(Call<ContestApiResponse> call, Response<ContestApiResponse> response) {
                contestApiResponse = response.body();

                if(!contestApiResponse.getStatus().equals("OK")){
                    notifyError(contestApiResponse.getComment());
                    return;
                }

                process(contestApiResponse);

                if(!notifyListeners) {
                }
                else notifySuccess();
            }

            @Override
            public void onFailure(Call<ContestApiResponse> call, Throwable t) {
                notifyError(NO_INTERNET);

            }
        });
    }



    public List<ContestEntity> fetchItemsAsSynced(){
        Call<ContestApiResponse> call= cfApi.getContestList();
        try {
            Response<ContestApiResponse> response= call.execute();
            ContestApiResponse contest = response.body();

            return contest.getResultContestList();


        } catch (IOException e) {
            e.printStackTrace();
        }

        return new ArrayList<>();
    }


    @Override
    protected abstract void process(ContestApiResponse response);

    @Override
    public void notifyError(String errorMsg) {
        //Log.i("dodo", cfContest.getStatus());
        for(OnFetchItemsListener<ContestEntity> listener: getListeners())
            listener.onFetchItemListFetchFailed(errorMsg);
    }

    @Override
    public void notifySuccess() {
        Log.i("dodo", "notifySuccess: Yes");

        for(OnFetchItemsListener<ContestEntity> listener: getListeners())
            listener.onItemListFetched(mContestEntities);
    }
}
