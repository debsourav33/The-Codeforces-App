package com.example.codeforcesapp.data.database;

import android.content.Context;
import android.os.Handler;
import android.os.HandlerThread;

import androidx.lifecycle.LiveData;

import com.example.codeforcesapp.data.contest.ContestEntity;
import com.example.codeforcesapp.networking.Contest.FetchPastContestListUseCase;
import com.example.codeforcesapp.networking.common.FetchItemsUseCase;

import java.util.ArrayList;
import java.util.List;

public class ContestRepository {
    Context context;
    ContestListDao dao;
    HandlerThread handlerThread;
    Handler handler;
    public ContestRepository(Context context) {
        this.context = context;
        ContestsDatabase db = ContestsDatabase.getInstance(context);
        dao = db.getContestListDao();
        handlerThread = new HandlerThread("ContestDBThread");
        handlerThread.start();
        handler = new Handler(handlerThread.getLooper());

        fetchFromApiAndStoreInDatabase();
    }

    public void fetchFromApiAndStoreInDatabase(){
        FetchPastContestListUseCase useCase = FetchPastContestListUseCase.getInstance(context);
        useCase.fetchItems(true);
        useCase.registerListener(new FetchItemsUseCase.OnFetchItemsListener<ContestEntity>() {
            @Override
            public void onItemListFetched(List<ContestEntity> contestEntities) {
                insertAll(contestEntities);
            }

            @Override
            public void onFetchItemListFetchFailed(String error) {

            }
        });
    }

    public LiveData<List<ContestEntity>> getPastContests(){
        LiveData<List<ContestEntity>> pastContests = dao.getPastContests();
        return pastContests;
    }



    public void insert(final ContestEntity contestEntry){
        handler.post(() -> dao.insert(contestEntry));
    }

    public void insertAll(List<ContestEntity> contests){
        handler.post(new Runnable(){
            @Override
            public void run() {
                dao.insertAll(contests);
            }
        });
    }

    public void delete(final ContestEntity contestEntry){
        handler.post(new Runnable() {
            @Override
            public void run() {
                dao.delete(contestEntry);
            }
        });
    }

    public List<ContestEntity> getContestList(){
        return dao.getAllContests();
    }
}
