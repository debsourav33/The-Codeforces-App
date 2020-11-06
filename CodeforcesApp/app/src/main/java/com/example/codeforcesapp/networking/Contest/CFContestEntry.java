package com.example.codeforcesapp.networking.Contest;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CFContestEntry {
    @SerializedName("id")
    private String id;

    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("startTimeSeconds")
    @Expose
    private String startTime;

    @SerializedName("durationSeconds")
    @Expose
    private String durationSec;

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
}
