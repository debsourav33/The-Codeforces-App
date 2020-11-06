package com.example.codeforcesapp.networking.UserSubmission;


public class UserSubmissionModel {
    String id;
    String verdict;

    public UserSubmissionModel(String id, String verdict) {
        this.id = id;
        this.verdict = verdict;
    }

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
