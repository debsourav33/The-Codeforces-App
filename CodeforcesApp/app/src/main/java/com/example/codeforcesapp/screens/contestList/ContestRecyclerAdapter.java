package com.example.codeforcesapp.screens.contestList;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.codeforcesapp.data.contest.ContestModel;

import java.util.ArrayList;

public class ContestRecyclerAdapter extends RecyclerView.Adapter<ContestRecyclerAdapter.ContestViewHolder> implements IListItemViewMvc.Listener {
    public interface OnContestClickListener{
        void onContestClicked(ContestModel contestModel);
    }

    private ArrayList<ContestModel> contestModels= new ArrayList<>();
    private OnContestClickListener onContestClickListener;

    public ContestRecyclerAdapter(OnContestClickListener onContestClickListener){
        this.onContestClickListener= onContestClickListener;
    }

    @NonNull
    @Override
    public ContestViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ContestEntryViewMvc contestEntryViewMvc= new ContestEntryViewMvc(LayoutInflater.from(parent.getContext()),parent);
        contestEntryViewMvc.registerListener(this);

        return new ContestViewHolder(contestEntryViewMvc.getRootView(), contestEntryViewMvc);
    }

    @Override
    public void onBindViewHolder(@NonNull ContestViewHolder holder, int position) {
        ContestEntryViewMvc contestEntryViewMvc= holder.contestEntryViewMvc;

        ContestModel model= contestModels.get(position);
        contestEntryViewMvc.bindItem(model);
    }

    @Override
    public int getItemCount() {
        return contestModels.size();
    }


    public void bindItems(ArrayList<ContestModel> list) {
        contestModels= list;
        notifyDataSetChanged();
    }

    @Override
    public void onContestClicked(ContestModel contestModel) {
        onContestClickListener.onContestClicked(contestModel);
    }

    static class ContestViewHolder extends RecyclerView.ViewHolder{
        ContestEntryViewMvc contestEntryViewMvc;

        public ContestViewHolder(@NonNull View itemView, ContestEntryViewMvc contestEntryViewMvc) {
            super(itemView);
            this.contestEntryViewMvc= contestEntryViewMvc;
        }
    }

}
