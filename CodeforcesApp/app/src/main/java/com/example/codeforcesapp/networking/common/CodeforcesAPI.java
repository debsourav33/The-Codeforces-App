package com.example.codeforcesapp.networking.common;

import com.example.codeforcesapp.data.contest.ContestApiResponse;
import com.example.codeforcesapp.data.userinfo.UserInfoApiResponse;
import com.example.codeforcesapp.data.usersubmission.UserSubmissionApiResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface CodeforcesAPI {
    @GET("api/contest.list?gym=false")
    Call<ContestApiResponse> getContestList();

    @GET("api/user.info?")
    Call<UserInfoApiResponse> getUserInfo(@Query("handles") String user_handle);

    @GET("api/user.status?")
    Call<UserSubmissionApiResponse> getUserSubmission(@Query("handle") String user_handle);
}
