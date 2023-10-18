package com.example.codeforcesapp.screens.contestList;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.codeforcesapp.R;
import com.example.codeforcesapp.data.contest.ContestModel;
import com.example.codeforcesapp.screens.common.ObservableViewMvc;


public class ContestEntryViewMvc extends ObservableViewMvc<IListItemViewMvc.Listener> implements IListItemViewMvc{
    TextView txtName, txtStartTime, txtIsInProgress, txtStartsIn;
    ContestModel mContestModel;

    private Handler timerUpdatehandler;

    private Runnable updateTimerTask = new Runnable() {
        @Override
        public void run() {
            updateTimer();

            timerUpdatehandler.postDelayed(this,1000);
        }
    };

    public ContestEntryViewMvc(LayoutInflater inflater, ViewGroup parent) {
        setRootView(inflater.inflate(R.layout.item_view, parent, false));
        txtName= findViewById(R.id.txt1);
        txtStartTime = findViewById(R.id.txt2);
        txtIsInProgress= findViewById(R.id.txtInProgress);
        txtStartsIn= findViewById(R.id.txtStartsIn);

        getRootView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for(Listener listener: getListeners())
                    listener.onContestClicked(mContestModel);
            }
        });

        timerUpdatehandler = new Handler(getContext().getMainLooper());

        //Fixed: don't start the timer before contestModel is binded by bindItem()
        //timerUpdatehandler.postDelayed(updateTimerTask,0);
    }

    public void bindItem(ContestModel contestModel){
        mContestModel = contestModel;
        txtName.setText(mContestModel.getName());
        timerUpdatehandler.postDelayed(updateTimerTask,0); //update the timer
    }

    public void updateStartTime(){
        txtStartTime.setText(mContestModel.getStartTimeFormatted());
    }

    public void updateTimer(long rSec){
        long rMin= rSec/60;  rSec%= 60;
        long rHour=  rMin/60;  rMin%= 60;
        long rDay= rHour/24;

        String remainingTime;
        if(rDay>=2)  remainingTime= String.format("%d days",rDay);
        else remainingTime= String.format("%02d:%02d:%02d",rHour,rMin,rSec);

        txtStartTime.setText(remainingTime);
    }

    public void updateTimer() {
        long rSec;
        switch (mContestModel.getContestStatus()){
            case UPCOMING:
                txtIsInProgress.setVisibility(View.GONE);
                txtStartsIn.setVisibility(View.VISIBLE);


                rSec= mContestModel.getTimeTillStart();
                updateTimer(rSec);
                break;
            case RUNNING:
                txtStartsIn.setVisibility(View.GONE);
                txtIsInProgress.setVisibility(View.VISIBLE);
                rSec= mContestModel.getTimeTillFinish();
                updateTimer(rSec);
                break;
            case FINISHED:
                updateStartTime();
        }
    }
}
