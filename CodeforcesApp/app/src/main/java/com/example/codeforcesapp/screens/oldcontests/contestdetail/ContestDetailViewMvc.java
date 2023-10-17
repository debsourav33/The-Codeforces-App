package com.example.codeforcesapp.screens.oldcontests.contestdetail;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.codeforcesapp.R;
import com.example.codeforcesapp.screens.navigationviews.BaseNavigationView;

public class ContestDetailViewMvc extends BaseNavigationView<ContestDetailViewMvc.Listener> {
    public interface Listener{

    }
    public ContestDetailViewMvc(LayoutInflater inflater, ViewGroup parent) {
        super(inflater, parent);
        setRootView(inflater.inflate(R.layout.activity_contest_detail,parent));
    }


}
