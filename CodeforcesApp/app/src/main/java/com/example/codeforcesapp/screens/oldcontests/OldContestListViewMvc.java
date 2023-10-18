package com.example.codeforcesapp.screens.oldcontests;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.codeforcesapp.R;
import com.example.codeforcesapp.data.contest.ContestModel;
import com.example.codeforcesapp.screens.navigationviews.BaseNavigationView;

import java.util.List;

public class OldContestListViewMvc extends BaseNavigationView<OldContestListViewMvc.SomeViewListener> {

    private RecyclerView recyclerView;
    private OldContestsRecyclerAdapter adapter = new OldContestsRecyclerAdapter();

    public OldContestListViewMvc(LayoutInflater inflater, ViewGroup parent) {
        super(inflater, parent);
        setRootView(inflater.inflate(R.layout.activity_old_contests,parent,false));

        initToolbar();
        initItems();

    }

    private void initItems() {
        recyclerView = findViewById(R.id.oldContestRecylerView);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    public interface SomeViewListener{

    }

    public void bindData(List<ContestModel> data){
        adapter.bindData(data);
    }
}
