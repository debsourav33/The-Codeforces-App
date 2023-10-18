package com.example.codeforcesapp.screens.contestList;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.codeforcesapp.R;
import com.example.codeforcesapp.data.contest.ContestModel;
import com.example.codeforcesapp.screens.navigationviews.BaseNavigationView;
import com.example.codeforcesapp.screens.common.ViewMvcFactory;

import java.util.ArrayList;
import java.util.List;


public class ContestListViewMvc extends BaseNavigationView<IListViewMvc.Listener> implements IListViewMvc, ContestRecyclerAdapter.OnContestClickListener {
    ListView mListView;
    RecyclerView recyclerView;
    ContestRecyclerAdapter contestAdapter;
    Toolbar toolbar;
    ViewMvcFactory viewMvcFactory;

    public ContestListViewMvc(LayoutInflater inflater, ViewGroup parent, ViewMvcFactory viewMvcFactory) {
        super(inflater,parent);

        setRootView(inflater.inflate(R.layout.activity_list_view, parent, false));
        //setRootView(inflater.inflate(R.layout.title_layout,parent,false));

        this.viewMvcFactory= viewMvcFactory;

        contestAdapter= new ContestRecyclerAdapter(this);

        recyclerView= findViewById(R.id.recyclerView);
        recyclerView.setAdapter(contestAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setHasFixedSize(true);

        initToolbar();
    }


   void bindItems(List<ContestModel> list){
        contestAdapter.bindItems(list);
    }

    @Override
    public void onContestClicked(ContestModel contestModel) {
        for(Listener listener: getListeners()){
            listener.onContestClicked(contestModel);
        }
    }
}
