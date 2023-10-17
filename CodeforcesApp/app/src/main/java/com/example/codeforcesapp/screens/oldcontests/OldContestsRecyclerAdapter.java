package com.example.codeforcesapp.screens.oldcontests;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.codeforcesapp.R;
import com.example.codeforcesapp.data.contest.ContestModel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class OldContestsRecyclerAdapter extends RecyclerView.Adapter<OldContestsRecyclerAdapter.ViewHolder> {

    private static List<ContestModel> contests = new ArrayList<>();

    static class ViewHolder extends RecyclerView.ViewHolder{

        private TextView textTitle;
        private TextView textTime;
        private View viewCard;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            textTitle = itemView.findViewById(R.id.txtContestTitle);
            textTime = itemView.findViewById(R.id.txtTime);
            viewCard = itemView.findViewById(R.id.cardContest);

            viewCard.setOnClickListener((View view)->{
                Toast.makeText(itemView.getContext(), "Tocuhed " + textTitle.getText(), Toast.LENGTH_SHORT).show();
            });
        }


        public void bindData(ContestModel contest){


            textTitle.setText(contest.getName());
            textTime.setText(contest.getStartTime());
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_old_contest, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int pos) {
        holder.bindData(contests.get(pos));
    }

    @Override
    public int getItemCount() {
        return contests.size();
    }

    public void bindData(ArrayList<ContestModel> data){
        contests = data;
        notifyDataSetChanged();

    }
}
