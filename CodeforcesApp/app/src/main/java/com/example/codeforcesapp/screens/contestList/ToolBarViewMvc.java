package com.example.codeforcesapp.screens.contestList;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.codeforcesapp.R;
import com.example.codeforcesapp.screens.common.ViewMvc;

public class ToolBarViewMvc extends ViewMvc {
    TextView textView;

    public ToolBarViewMvc(LayoutInflater inflater, ViewGroup parent) {
        setRootView(inflater.inflate(R.layout.layout_toolbar,parent,false));
        textView= findViewById(R.id.txtTitle);
    }

    void bindTitle(String title){
        textView.setText(title);
    }
}
