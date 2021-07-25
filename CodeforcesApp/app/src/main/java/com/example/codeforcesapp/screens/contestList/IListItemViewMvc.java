package com.example.codeforcesapp.screens.contestList;

import com.example.codeforcesapp.data.contest.ContestModel;

public interface IListItemViewMvc {
    interface Listener{
        void onContestClicked(ContestModel contestModel);
    }

}
