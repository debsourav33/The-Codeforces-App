package com.example.codeforcesapp.screens.new_feature;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.codeforcesapp.R;
import com.example.codeforcesapp.screens.navigationviews.BaseNavigationActivity;
import com.example.codeforcesapp.screens.navigationviews.BaseNavigationView;

public class SomeActivity extends BaseNavigationActivity {

    SomeView mViewMvc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mViewMvc = getViewMvcFactory().getNewFeatureViewMVC(null);

        setContentView(mViewMvc.getRootView());
    }

    @Override
    protected BaseNavigationView getNavView() {
        return mViewMvc;
    }
}