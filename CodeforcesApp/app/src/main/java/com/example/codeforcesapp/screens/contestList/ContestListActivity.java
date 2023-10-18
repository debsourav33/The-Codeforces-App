package com.example.codeforcesapp.screens.contestList;

import android.os.Bundle;

import androidx.lifecycle.Observer;

import com.example.codeforcesapp.data.contest.ContestEntity;
import com.example.codeforcesapp.data.contest.ContestModel;
import com.example.codeforcesapp.data.database.ContestRepository;
import com.example.codeforcesapp.screens.navigationviews.BaseNavigationActivity;
import com.example.codeforcesapp.screens.navigationviews.BaseNavigationView;

import java.util.List;
import java.util.stream.Collectors;

public class ContestListActivity extends BaseNavigationActivity implements ContestListViewMvc.Listener {
    ContestListViewMvc mViewMvc;


    ContestRepository repository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mViewMvc = getViewMvcFactory().getContestListViewMvc(null);

        repository = new ContestRepository(getApplicationContext());
        repository.getNewContests().observe(this, new Observer<List<ContestEntity>>() {
            @Override
            public void onChanged(List<ContestEntity> contestEntities) {
                List<ContestModel> models = contestEntities.stream()
                        .map((contestEntity -> new ContestModel(contestEntity)))
                        .collect(Collectors.toList());
                passItems(models);

            }
        });

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

        //fetchUpcomingContestListUseCase.registerListener(this);
        //fetchUpcomingContestListUseCase.fetchItems(false);
    }

    @Override
    protected void onStop() {
        super.onStop();


        mViewMvc.unregisterListener(this);
        //fetchUpcomingContestListUseCase.unregisterListener(this);
    }

    private void passItems(List<ContestModel> contestModelList) {
        mViewMvc.bindItems(contestModelList);
    }


    @Override
    public void onContestClicked(ContestModel contestModel) {
        //Toast.makeText(this, contestModel.getNames()+" "+contestModel.getStartTime(), Toast.LENGTH_SHORT).show();
        //ListItemDetailsActivity.start(this, contestModel);
    }
}
