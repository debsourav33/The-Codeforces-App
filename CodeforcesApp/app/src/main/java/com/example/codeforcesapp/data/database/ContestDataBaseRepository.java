package com.example.codeforcesapp.data.database;

import android.content.Context;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Process;

import com.example.codeforcesapp.data.contest.CFContestEntry;

import java.util.List;

public class ContestDataBaseRepository {
    ContestListDao dao;
    HandlerThread handlerThread;
    Handler handler;
    public ContestDataBaseRepository(Context context) {
        ContestsDatabase db = ContestsDatabase.getInstance(context.getApplicationContext());
        dao = db.getContestListDao();
        handlerThread = new HandlerThread("ContestDBThread");
        handlerThread.start();
        handler = new Handler(handlerThread.getLooper());
    }

    public void insert(final CFContestEntry contestEntry){
        handler.post(new Runnable() {
            @Override
            public void run() {
                dao.insert(contestEntry);
            }
        });
    }

    public void delete(final CFContestEntry contestEntry){
        handler.post(new Runnable() {
            @Override
            public void run() {
                dao.delete(contestEntry);
            }
        });
    }

    public List<CFContestEntry> getContestList(){
        return dao.getAllContests();
    }
}
