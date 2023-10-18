package com.example.codeforcesapp.data.userinfo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class UserInfoApiResponse {
    @SerializedName("status")
    @Expose
    String status;

    @SerializedName("result")
    @Expose
    List<UserInfo> resultUserInfoList;

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

    public List<UserInfo> getResultUserInfoList() {
        return resultUserInfoList;
    }

    public void setResultUserInfoList(List<UserInfo> resultUserInfoList) {
        this.resultUserInfoList = resultUserInfoList;
    }
}
