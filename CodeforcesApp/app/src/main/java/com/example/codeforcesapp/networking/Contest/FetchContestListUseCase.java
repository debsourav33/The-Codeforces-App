package com.example.codeforcesapp.networking.Contest;

import android.util.Log;

import androidx.annotation.NonNull;

import com.example.codeforcesapp.data.contest.CFContest;
import com.example.codeforcesapp.data.contest.CFContestEntry;
import com.example.codeforcesapp.data.contest.ContestModel;
import com.example.codeforcesapp.networking.common.FetchItemsUseCase;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public abstract class FetchContestListUseCase extends FetchItemsUseCase<CFContest, ContestModel> {
    protected CFContest cfContest;
    protected List<CFContestEntry> cfContestEntryList;
    protected ArrayList<ContestModel> mList= new ArrayList<>();

    @Override
    protected void fetchItems(boolean ignoreCacheAndForceRetrieve, final boolean notifyListeners) {
        if(!ignoreCacheAndForceRetrieve  && notifyListeners && mList.size()>0){
            notifySuccess();
            return;
        }

        if(ignoreCacheAndForceRetrieve)  mList.clear();

        Call<CFContest> call= cfApi.getContetList();

        call.enqueue(new Callback<CFContest>() {
            @Override
            public void onResponse(Call<CFContest> call, Response<CFContest> response) {
                cfContest = response.body();

                if(!cfContest.getStatus().equals("OK")){
                    notifyError(cfContest.getComment());
                    return;
                }

                process(cfContest);

                if(!notifyListeners) {
                }
                else notifySuccess();
            }

            @Override
            public void onFailure(Call<CFContest> call, Throwable t) {
                notifyError(NO_INTERNET);

            }
        });
    }

    public List<CFContestEntry> fetchItemsAsSynced(){
        Call<CFContest> call= cfApi.getContetList();
        try {
            Response<CFContest> response= call.execute();
            CFContest cfContest= response.body();

            return cfContest.getResultContestList();


        } catch (IOException e) {
            e.printStackTrace();
        }

        return new ArrayList<>();
    }


    @Override
    protected abstract void process(CFContest response);

    @Override
    public void notifyError(String errorMsg) {
        //Log.i("dodo", cfContest.getStatus());
        for(OnFetchItemsListener<ContestModel> listener: getListeners())
            listener.onFetchItemListFetchFailed(errorMsg);
    }

    @Override
    public void notifySuccess() {
        Log.i("dodo", "notifySuccess: Yes");

        for(OnFetchItemsListener<ContestModel> listener: getListeners())
            listener.onItemListFetched(mList);
    }
}
