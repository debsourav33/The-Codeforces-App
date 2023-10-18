package com.example.codeforcesapp.data.database;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import androidx.annotation.Nullable;

import com.example.codeforcesapp.data.contest.ContestEntity;
import com.example.codeforcesapp.networking.Contest.FetchContestListUseCase;
import com.example.codeforcesapp.networking.Contest.FetchUpcomingContestListUseCase;

import java.util.List;

public class ContestCacheService extends Service {
    public static final String TAG = ContestCacheService.class.getSimpleName();
    FetchContestListUseCase fetchContestListUseCase;
    ContestRepository repository;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        Log.i(TAG, "onCreate called");
        fetchContestListUseCase = FetchUpcomingContestListUseCase.getInstance(this);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        //Note: The context within the service will not be visible until onStart or onStartCommand in services
        Log.i(TAG, "onStartCommand called");
        repository = new ContestRepository(this);

        new Thread(new Runnable() {
            @Override
            public void run() {
                List<ContestEntity> contests = fetchContestListUseCase.fetchItemsAsSynced();
                for(ContestEntity contest: contests){
                    repository.insert(contest);
                }

                stopSelf();
            }
        }).start();


        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
}
