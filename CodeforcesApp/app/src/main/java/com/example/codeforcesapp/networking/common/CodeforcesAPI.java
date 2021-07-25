package com.example.codeforcesapp.networking.common;

import com.example.codeforcesapp.data.contest.CFContest;
import com.example.codeforcesapp.data.userinfo.CFUserInfo;
import com.example.codeforcesapp.data.usersubmission.CFUserSubmission;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface CodeforcesAPI {
    @GET("api/contest.list?gym=false")
    Call<CFContest> getContetList();

    @GET("api/user.info?")
    Call<CFUserInfo> getUserInfo(@Query("handles") String user_handle);

    @GET("api/user.status?")
    Call<CFUserSubmission> getUserSubmission(@Query("handle") String user_handle);
}
