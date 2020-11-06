package com.example.codeforcesapp.screens.userdetails;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.example.codeforcesapp.networking.Contest.ContestModel;
import com.example.codeforcesapp.screens.contestList.ContestListActivity;
import com.example.codeforcesapp.screens.navigationviews.BaseNavigationActivity;
import com.example.codeforcesapp.screens.navigationviews.BaseNavigationView;
import com.example.codeforcesapp.usecases.PrepareItemUseCase;

public class ListItemDetailsActivity extends BaseNavigationActivity implements PrepareItemUseCase.Listener, ListItemDetailsViewMvc.Listener {
    ListItemDetailsViewMvc mViewMvc;
    PrepareItemUseCase prepareItemUseCase;

    ContestModel mContestModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContestModel = (ContestModel) getIntent().getParcelableExtra("ContestModel");

        mViewMvc = getViewMvcFactory().getListItemDetailsViewMvc(null);
        prepareItemUseCase= new PrepareItemUseCase();



        setContentView(mViewMvc.getRootView());
    }

    @Override
    protected BaseNavigationView getNavView() {
        return mViewMvc;
    }

    @Override
    protected void onStart() {
        super.onStart();

        mViewMvc.registerListener(this);

        prepareItemUseCase.registerListener(this);
        prepareItemUseCase.prepareItemAndNotify(mContestModel);
    }

    @Override
    protected void onStop() {
        super.onStop();

        mViewMvc.unregisterListener(this);
        prepareItemUseCase.unregisterListener(this);
    }

    private void passItem(String names){
        mViewMvc.bindItem(names);
    }

    public static void start(Context context, ContestModel contestModel) {
        Intent intent= new Intent(context, ListItemDetailsActivity.class);
        intent.putExtra("ContestModel", contestModel);
        context.startActivity(intent);
    }


    @Override
    public void onItemPrepared(String names) {
        passItem(names);
    }

    @Override
    public void switchToListViewScreen() {
        Intent intent= new Intent(this, ContestListActivity.class);
        startActivity(intent);
    }
}
