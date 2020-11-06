package com.example.codeforcesapp.common;

import android.app.Activity;

import androidx.appcompat.app.AppCompatActivity;

public class ActivityManagerHost {
    static ActivityManagerHost manager;
    AppCompatActivity currActivity;


    private ActivityManagerHost(){

    }

    public static ActivityManagerHost getInstance() {
        if(manager==null){
            manager= new ActivityManagerHost();
        }

        return manager;
    }

    public void setCurrActivity(AppCompatActivity currActivity) {
        this.currActivity = currActivity;
    }

    public Activity getCurrActivity() {
        return currActivity;
    }
}
