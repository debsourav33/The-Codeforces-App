package com.example.codeforcesapp.screens.oldcontests;

import android.os.Bundle;

import com.example.codeforcesapp.data.contest.ContestModel;
import com.example.codeforcesapp.networking.Contest.FetchPastContestListUseCase;
import com.example.codeforcesapp.networking.common.FetchItemsUseCase;
import com.example.codeforcesapp.screens.navigationviews.BaseNavigationActivity;
import com.example.codeforcesapp.screens.navigationviews.BaseNavigationView;

import java.util.ArrayList;

public class OldContestListActivity extends BaseNavigationActivity {

    OldContestListViewMvc mViewMvc;
    FetchPastContestListUseCase useCase;

    private class FetchedItemListener implements FetchItemsUseCase.OnFetchItemsListener<ContestModel>{

        @Override
        public void onItemListFetched(ArrayList<ContestModel> data) {
            mViewMvc.bindData(data);
        }

        @Override
        public void onFetchItemListFetchFailed(String error) {

        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mViewMvc = getViewMvcFactory().getNewFeatureViewMVC(null);
        setContentView(mViewMvc.getRootView());

        useCase = new FetchPastContestListUseCase();
        useCase.registerListener(new FetchedItemListener());
        useCase.fetchItems(false);
    }

    @Override
    protected BaseNavigationView getNavView() {
        return mViewMvc;
    }
}