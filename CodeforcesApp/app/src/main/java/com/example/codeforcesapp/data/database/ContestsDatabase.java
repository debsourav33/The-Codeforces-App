package com.example.codeforcesapp.data.database;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;

import com.example.codeforcesapp.data.contest.CFContestEntry;

@Database(entities = {CFContestEntry.class}, version = 1)
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
