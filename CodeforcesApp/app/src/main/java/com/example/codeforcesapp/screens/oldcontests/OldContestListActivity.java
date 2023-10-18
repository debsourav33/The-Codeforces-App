package com.example.codeforcesapp.screens.oldcontests;

import android.os.Bundle;
import android.widget.Toast;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;

import com.example.codeforcesapp.data.contest.ContestEntity;
import com.example.codeforcesapp.data.contest.ContestModel;
import com.example.codeforcesapp.data.database.ContestRepository;
import com.example.codeforcesapp.networking.Contest.FetchPastContestListUseCase;
import com.example.codeforcesapp.networking.common.FetchItemsUseCase;
import com.example.codeforcesapp.screens.navigationviews.BaseNavigationActivity;
import com.example.codeforcesapp.screens.navigationviews.BaseNavigationView;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class OldContestListActivity extends BaseNavigationActivity {
    OldContestListViewMvc mViewMvc;
    FetchPastContestListUseCase useCase;
    ContestRepository repository;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mViewMvc = getViewMvcFactory().getNewFeatureViewMVC(null);
        setContentView(mViewMvc.getRootView());


        useCase = FetchPastContestListUseCase.getInstance(this);
        //useCase.registerListener(new FetchedItemListener());
        //useCase.fetchItems(false);

        repository = new ContestRepository(getApplicationContext());
        LiveData<List<ContestEntity>> data = repository.getPastContests();
        data.observe(this, new Observer<List<ContestEntity>>() {
            @Override
            public void onChanged(List<ContestEntity> contestEntities) {
                List<ContestModel> models=  contestEntities.stream()
                        .map((entity) -> new ContestModel(entity))
                        .collect(Collectors.toList());

                Toast.makeText(OldContestListActivity.this, "Past Contest LiveData Changed", Toast.LENGTH_SHORT).show();
                mViewMvc.bindData(models);
            }
        });
        
    }

    @Override
    protected BaseNavigationView getNavView() {
        return mViewMvc;
    }
}