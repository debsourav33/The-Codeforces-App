package com.example.codeforcesapp.screens.common;

import android.content.Context;
import android.view.View;

public class ViewMvc implements IViewMvc {
    View rootView;

    @Override
    public View getRootView() {
        return rootView;
    }

    public void setRootView(View view) {
        rootView = view;
    }

    protected Context getContext(){
        return getRootView().getContext();
    }

    protected <T extends View> T findViewById(int id){
        return getRootView().findViewById(id);
    }
}
