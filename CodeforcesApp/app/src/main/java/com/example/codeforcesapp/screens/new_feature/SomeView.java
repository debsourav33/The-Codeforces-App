package com.example.codeforcesapp.screens.new_feature;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.codeforcesapp.R;
import com.example.codeforcesapp.screens.navigationviews.BaseNavigationView;

import java.util.logging.Handler;

public class SomeView extends BaseNavigationView<SomeView.SomeViewListener> {

    public SomeView(LayoutInflater inflater, ViewGroup parent) {
        super(inflater, parent);
        setRootView(inflater.inflate(R.layout.activity_some,parent,false));

        initToolbar();

    }

    public interface SomeViewListener{

    }
}
