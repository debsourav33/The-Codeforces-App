package com.example.codeforcesapp.screens.contestList;

import android.content.Context;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.codeforcesapp.data.contest.ContestModel;

import java.util.ArrayList;
import java.util.List;

public class ContestAdapter extends ArrayAdapter<ContestModel> implements IListItemViewMvc.Listener {

    public interface OnContestClickListener {
        void onContestClicked(ContestModel contestModel);
    }

    List<ContestEntryViewMvc> viewList= new ArrayList<>();

    OnContestClickListener mOnContestClickListener;

    public ContestAdapter(@NonNull Context context, OnContestClickListener onContestClickListener) {
        super(context,0);
        mOnContestClickListener = onContestClickListener;

        //handler.postDelayed(r,0);
    }

    private Handler handler= new Handler();

    private Runnable r= new Runnable() {
        @Override
        public void run() {
            for(ContestEntryViewMvc itemViewMvc: viewList){
                itemViewMvc.updateTimer();
            }

            handler.postDelayed(this,500);
        }
    };

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ContestModel contestModel = getItem(position);

        if(convertView==null){
            ContestEntryViewMvc contestEntryViewMvc = new ContestEntryViewMvc(LayoutInflater.from(getContext()), parent);
            convertView= contestEntryViewMvc.getRootView();
            convertView.setTag(contestEntryViewMvc);
            contestEntryViewMvc.registerListener(this);

            viewList.add(contestEntryViewMvc);
        }

        ContestEntryViewMvc contestEntryViewMvc = (ContestEntryViewMvc) convertView.getTag();

        contestEntryViewMvc.bindItem(contestModel);
        return convertView;
    }

    @Override
    public void onContestClicked(ContestModel contestModel) {
        mOnContestClickListener.onContestClicked(contestModel);
    }
}
