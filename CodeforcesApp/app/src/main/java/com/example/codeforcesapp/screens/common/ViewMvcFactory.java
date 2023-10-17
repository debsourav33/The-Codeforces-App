package com.example.codeforcesapp.screens.common;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.Nullable;

import com.example.codeforcesapp.screens.oldcontests.OldContestListViewMvc;
import com.example.codeforcesapp.screens.userdetails.ListItemDetailsViewMvc;
import com.example.codeforcesapp.screens.UserInfo.UserInfoViewMvc;
import com.example.codeforcesapp.screens.contestList.ContestListViewMvc;
import com.example.codeforcesapp.screens.contestList.ToolBarViewMvc;
import com.example.codeforcesapp.screens.usersubmission.UserSubmissionViewMvc;

public class ViewMvcFactory {
    LayoutInflater mLayoutInflater;

    public ViewMvcFactory(LayoutInflater mLayoutInflater) {
        this.mLayoutInflater = mLayoutInflater;
    }

    public ContestListViewMvc getContestListViewMvc(@Nullable ViewGroup parent){
        return new ContestListViewMvc(mLayoutInflater,parent,this);
    }

    public ListItemDetailsViewMvc getListItemDetailsViewMvc(@Nullable ViewGroup parent){
        return new ListItemDetailsViewMvc(mLayoutInflater,parent);
    }

    public ToolBarViewMvc getToolBarViewMvc(@Nullable ViewGroup parent){
        return new ToolBarViewMvc(mLayoutInflater,parent);
    }

    public LayoutInflater getmLayoutInflater() {
        return mLayoutInflater;
    }

    public UserInfoViewMvc getUserInfoViewMvc(ViewGroup parent) {
        return new UserInfoViewMvc(mLayoutInflater,parent);
    }

    public UserSubmissionViewMvc getUserSubmissionViewMvc(ViewGroup parent){
        return new UserSubmissionViewMvc(mLayoutInflater,parent);
    }

    public OldContestListViewMvc getNewFeatureViewMVC(ViewGroup parent){
        return new OldContestListViewMvc(mLayoutInflater,parent);
    }
}
