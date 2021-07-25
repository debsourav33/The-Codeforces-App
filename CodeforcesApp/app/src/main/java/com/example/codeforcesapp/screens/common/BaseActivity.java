package com.example.codeforcesapp.screens.common;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.LayoutInflater;

import com.example.codeforcesapp.common.ActivityManagerHost;

public class BaseActivity extends AppCompatActivity {
    ActivityManagerHost manager= ActivityManagerHost.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void onResume() {
        super.onResume();
        manager.setCurrActivity(this);
    }

    protected ViewMvcFactory getViewMvcFactory(){
        return new ViewMvcFactory(LayoutInflater.from(this));
    }
}
