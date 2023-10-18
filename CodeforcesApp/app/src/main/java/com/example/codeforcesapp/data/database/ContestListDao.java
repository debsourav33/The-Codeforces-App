package com.example.codeforcesapp.data.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.codeforcesapp.data.contest.ContestEntity;

import java.util.List;

@Dao
public interface ContestListDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(ContestEntity contestEntry);

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertAll(List<ContestEntity> contestEntries);

    @Delete
    void delete(ContestEntity contestEntry);

    @Query("Select * from ContestEntity")
    List<ContestEntity> getAllContests();

    @Query("SELECT * from ContestEntity WHERE phase = 'FINISHED'")
    LiveData<List<ContestEntity>> getPastContests();
}
