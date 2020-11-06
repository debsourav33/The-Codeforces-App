package com.example.codeforcesapp.networking.Contest;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CFContest {
    @SerializedName("status")
    @Expose
    String status;

    @SerializedName("result")
    @Expose
    List<CFContestEntry> resultContestList;

    @SerializedName("comment")
    @Expose
    String comment;

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<CFContestEntry> getResultContestList() {
        return resultContestList;
    }

    public void setResultContestList(List<CFContestEntry> resultContestList) {
        this.resultContestList = resultContestList;
    }
}
