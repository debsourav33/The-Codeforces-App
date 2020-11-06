package com.example.codeforcesapp.networking.UserInfo;

import com.example.codeforcesapp.networking.Contest.CFContestEntry;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CFUserInfo {
    @SerializedName("status")
    @Expose
    String status;

    @SerializedName("result")
    @Expose
    List<CFUserInfoEntry> resultUserInfoList;

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

    public List<CFUserInfoEntry> getResultUserInfoList() {
        return resultUserInfoList;
    }

    public void setResultUserInfoList(List<CFUserInfoEntry> resultUserInfoList) {
        this.resultUserInfoList = resultUserInfoList;
    }
}
