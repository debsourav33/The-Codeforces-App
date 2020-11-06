package com.example.codeforcesapp.screens.contestList;

import com.example.codeforcesapp.networking.Contest.ContestModel;

public interface IListItemViewMvc {
    interface Listener{
        void onContestClicked(ContestModel contestModel);
    }

}
