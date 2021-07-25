package com.example.codeforcesapp.data.usersubmission;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CFUserSubmissionEntry {
    @SerializedName("id")
    @Expose
    String id;

    @SerializedName("verdict")
    @Expose
    String verdict;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getVerdict() {
        return verdict;
    }

    public void setVerdict(String verdict) {
        this.verdict = verdict;
    }
}
