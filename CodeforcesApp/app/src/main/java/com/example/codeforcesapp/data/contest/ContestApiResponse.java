package com.example.codeforcesapp.data.contest;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ContestApiResponse {
    @SerializedName("status")
    @Expose
    String status;

    @SerializedName("result")
    @Expose
    List<ContestEntity> resultContestList;

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

    public List<ContestEntity> getResultContestList() {
        return resultContestList;
    }

    public void setResultContestList(List<ContestEntity> resultContestList) {
        this.resultContestList = resultContestList;
    }
}
