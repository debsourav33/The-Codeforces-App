package com.example.codeforcesapp.screens.contestList;

import com.example.codeforcesapp.networking.Contest.ContestModel;

public interface IListViewMvc{
    interface Listener{
        void onContestClicked(ContestModel contestModel);
    }
}
