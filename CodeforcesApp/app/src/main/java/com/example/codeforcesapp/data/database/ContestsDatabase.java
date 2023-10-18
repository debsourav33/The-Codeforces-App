package com.example.codeforcesapp.data.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.codeforcesapp.data.contest.ContestEntity;

@Database(entities = {ContestEntity.class}, version = 2)
public abstract class ContestsDatabase extends RoomDatabase {
    private static ContestsDatabase instance;

    public abstract ContestListDao getContestListDao();

    public static ContestsDatabase getInstance(Context context){
        if(instance==null){
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    ContestsDatabase.class, "ContestDB")
                    .fallbackToDestructiveMigration()
                    .build();
        }

        return instance;
    }
}
