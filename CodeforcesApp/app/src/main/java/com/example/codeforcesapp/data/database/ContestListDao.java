package com.example.codeforcesapp.data.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.codeforcesapp.data.contest.CFContestEntry;

import java.util.List;

@Dao
public interface ContestListDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(CFContestEntry contestEntry);

    @Delete
    void delete(CFContestEntry contestEntry);

    @Query("Select * from Contest_Table")
    List<CFContestEntry> getAllContests();
}
