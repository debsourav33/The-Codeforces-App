package com.example.codeforcesapp.screens.contestList;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.lifecycle.Observer;

import com.example.codeforcesapp.networking.Contest.ContestModel;
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

        mViewMvc = getViewMvcFactory().getListViewMvc(null);

        fetchUpcomingContestListUseCase=  FetchUpcomingContestListUseCase.getInstance();

        //observeContestModels();

        setContentView(mViewMvc.getRootView());
    }

    private void observeContestModels() {
        fetchUpcomingContestListUseCase.fetchItems(false);

        final Observer<ArrayList<ContestModel>> contestModelObserver= new Observer<ArrayList<ContestModel>>() {
            @Override
            public void onChanged(ArrayList<ContestModel> contestModels) {
                Log.i("dodos", "onChanged: ");
                passItems(contestModels);
            }
        };

        fetchUpcomingContestListUseCase.getmLiveDataList().observe(this,contestModelObserver);
    }

    @Override
    protected BaseNavigationView getNavView() {
        return mViewMvc;
    }


    @Override
    protected void onStart() {
        super.onStart();

        //mViewMvc.registerListener(this);

        registerToObservable(mViewMvc);

        registerToObservable(fetchUpcomingContestListUseCase);
        fetchUpcomingContestListUseCase.fetchItems(false);
    }

    @Override
    protected void onStop() {
        super.onStop();

        //mViewMvc.unregisterListener(this);
        //fetchUpcomingContestListUseCase.unregisterListener(this);

        unregisterFromObservable(mViewMvc);
        unregisterFromObservable(fetchUpcomingContestListUseCase);
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
