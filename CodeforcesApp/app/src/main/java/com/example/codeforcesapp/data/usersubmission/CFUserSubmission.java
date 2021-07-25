package com.example.codeforcesapp.data.usersubmission;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CFUserSubmission {
    @SerializedName("status")
    @Expose
    String status;

    @SerializedName("result")
    @Expose
    List<CFUserSubmissionEntry> resultSubmissionList;

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

    public List<CFUserSubmissionEntry> getResultSubmissionList() {
        return resultSubmissionList;
    }

    public void setResultSubmissionList(List<CFUserSubmissionEntry> resultSubmissionList) {
        this.resultSubmissionList = resultSubmissionList;
    }
}
