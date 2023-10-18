package com.example.codeforcesapp.data.contest;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Entity(tableName = "ContestEntity")
public class ContestEntity {
    @SerializedName("id")
    @PrimaryKey()
    @NonNull
    private String id;

    @SerializedName("name")
    @ColumnInfo(name = "Title")
    @Expose
    private String name;

    @SerializedName("startTimeSeconds")
    @ColumnInfo(name = "Start_Time")
    @Expose
    private String startTime;

    @SerializedName("durationSeconds")
    @ColumnInfo(name = "Duration_In_Seconds")
    @Expose
    private String durationSec;

    @Expose
    private String phase;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDurationSec() {
        return durationSec;
    }

    public void setDurationSec(String durationSec) {
        this.durationSec = durationSec;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getPhase() {
        return phase;
    }

    public void setPhase(String phase) {
        this.phase = phase;
    }
}
