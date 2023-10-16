package com.example.codeforcesapp.screens.contestList;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.example.codeforcesapp.data.contest.ContestModel;
import com.example.codeforcesapp.data.database.ContestCacheService;
import com.example.codeforcesapp.networking.Contest.FetchUpcomingContestListUseCase;
import com.example.codeforcesapp.networking.common.FetchItemsUseCase;
import com.example.codeforcesapp.screens.navigationviews.BaseNavigationActivity;
import com.example.codeforcesapp.screens.navigationviews.BaseNavigationView;

import java.util.ArrayList;

public class ContestListActivity extends BaseNavigationActivity implements ContestListViewMvc.Listener, FetchItemsUseCase.OnFetchItemsListener<ContestModel> {
    ContestListViewMvc mViewMvc;

    //FetchContestListUseCase fetchContestListUseCase;
    FetchUpcomingContestListUseCase fetchUpcomingContestListUseCase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mViewMvc = getViewMvcFactory().getContestListViewMvc(null);

        fetchUpcomingContestListUseCase=  FetchUpcomingContestListUseCase.getInstance();
        //Intent intent = new Intent(this, ContestCacheService.class);
        //startService(intent);

        setContentView(mViewMvc.getRootView());
    }

    @Override
    protected BaseNavigationView getNavView() {
        return mViewMvc;
    }

    @Override
    protected void onStart() {
        super.onStart();

        //mViewMvc.registerListener(this);

        mViewMvc.registerListener(this);

        fetchUpcomingContestListUseCase.registerListener(this);
        fetchUpcomingContestListUseCase.fetchItems(false);
    }

    @Override
    protected void onStop() {
        super.onStop();

        //mViewMvc.unregisterListener(this);
        //fetchUpcomingContestListUseCase.unregisterListener(this);

        mViewMvc.unregisterListener(this);
        fetchUpcomingContestListUseCase.unregisterListener(this);
    }

    private void passItems(ArrayList<ContestModel> contestModelList) {
        mViewMvc.bindItems(contestModelList);
    }


    @Override
    public void onContestClicked(ContestModel contestModel) {
        //Toast.makeText(this, contestModel.getNames()+" "+contestModel.getStartTime(), Toast.LENGTH_SHORT).show();
        //ListItemDetailsActivity.start(this, contestModel);
    }


    @Override
    public void onItemListFetched(ArrayList<ContestModel> list) {
        passItems(list);
    }

    @Override
    public void onFetchItemListFetchFailed(String error) {
        Toast.makeText(this, error, Toast.LENGTH_SHORT).show();
    }

}
