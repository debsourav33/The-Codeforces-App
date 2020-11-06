package com.example.codeforcesapp.screens.contestList;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.appcompat.widget.Toolbar;

import com.example.codeforcesapp.R;
import com.example.codeforcesapp.networking.Contest.ContestModel;
import com.example.codeforcesapp.screens.navigationviews.BaseNavigationView;
import com.example.codeforcesapp.screens.common.ViewMvcFactory;

import java.util.ArrayList;


public class ContestListViewMvc extends BaseNavigationView<IListViewMvc.Listener> implements IListViewMvc, ContestAdapter.OnContestClickListener {
    ListView mListView;
    ContestAdapter adapter;
    Toolbar toolbar;
    ViewMvcFactory viewMvcFactory;

    public ContestListViewMvc(LayoutInflater inflater, ViewGroup parent, ViewMvcFactory viewMvcFactory) {
        super(inflater,parent);

        setRootView(inflater.inflate(R.layout.activity_list_view, parent, false));
        //setRootView(inflater.inflate(R.layout.title_layout,parent,false));

        this.viewMvcFactory= viewMvcFactory;

        mListView= findViewById(R.id.listView);
        adapter= new ContestAdapter(getContext(),this);
        mListView.setAdapter(adapter);

        initToolbar();
    }


   void bindItems(ArrayList<ContestModel> list){
        adapter.clear();
        adapter.addAll(list);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onContestClicked(ContestModel contestModel) {
        for(Listener listener: getListeners()){
            listener.onContestClicked(contestModel);

        }
    }


}
