package com.example.codeforcesapp.screens.contestList;

import com.example.codeforcesapp.data.contest.ContestModel;

public interface IListViewMvc{
    interface Listener{
        void onContestClicked(ContestModel contestModel);
    }
}
