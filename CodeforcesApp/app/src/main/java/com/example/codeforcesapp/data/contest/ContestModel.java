package com.example.codeforcesapp.data.contest;


import android.os.Parcel;
import android.os.Parcelable;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ContestModel implements Parcelable, Comparable {
    private String name, startTime, id, durationSec, startTimeFormatted;

    public ContestModel(String name, String startTime) {
        this.name = name;
        this.startTime = startTime;

        setStartTimeFormatted(unixToDateTime(Long.parseLong(startTime)));
    }

    public ContestModel(CFContestEntry contestEntry){
        this.name = contestEntry.getName();
        this.startTime= contestEntry.getStartTime();
        this.durationSec= contestEntry.getDurationSec();

        setStartTimeFormatted(unixToDateTime(Long.parseLong(startTime)));
    }

    public ContestModel(Parcel parcel) {
        this.name = parcel.readString();
        this.startTime = parcel.readString();
        this.startTimeFormatted= parcel.readString();
    }

    public static final Creator<ContestModel> CREATOR = new Creator<ContestModel>() {
        @Override
        public ContestModel createFromParcel(Parcel in) {
            return new ContestModel(in);
        }

        @Override
        public ContestModel[] newArray(int size) {
            return new ContestModel[size];
        }
    };

    public String getName() {
        return name;
    }

    public String getStartTime() {
        return startTime;
    }

    public String getId() {
        return id;
    }

    public String getStartTimeFormatted() {
        return startTimeFormatted;
    }

    public void setStartTimeFormatted(String startTimeFormatted) {
        this.startTimeFormatted = startTimeFormatted;
    }

    public long getTimeTillStart(){
        long startTimeSec= Long.parseLong(startTime);
        long currTimeSec= System.currentTimeMillis()/1000L;

        long rSec= startTimeSec-currTimeSec;

        return rSec;
    }

    public long getTimeTillFinish(){
        long finishTimeSec= Long.parseLong(startTime)+ Long.parseLong(durationSec);
        long currTimeSec= System.currentTimeMillis()/1000L;

        long rSec= finishTimeSec-currTimeSec;

        return rSec;
    }

    public long getContestRemainingTime(){
        long startTimeSec= Long.parseLong(startTime);
        long currTimeSec= System.currentTimeMillis()/1000L;

        long rSec= startTimeSec-currTimeSec;

        return rSec;
    }


    public String getDurationSec() {
        return durationSec;
    }

    public ContestStatus

    getContestStatus(){
        if(getTimeTillStart()>0)  return ContestStatus.UPCOMING;
        if(getTimeTillFinish()>0)  return ContestStatus.RUNNING;

        return ContestStatus.FINISHED;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(startTime);
        dest.writeString(startTimeFormatted);
        //dest.writeString(id);
    }

    private String unixToDateTime(long unixSeconds){
        // convert seconds to milliseconds
        Date date = new java.util.Date(unixSeconds*1000L);
        // the format of your date
        SimpleDateFormat sdf = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        // give a timezone reference for formatting (see comment at the bottom)
        sdf.setTimeZone(java.util.TimeZone.getTimeZone("GMT+6"));
        String formattedDate = sdf.format(date);

        return formattedDate;
    }

    @Override
    public int compareTo(Object o) {
        ContestModel cm= (ContestModel) o;

        if(this.getContestStatus()==ContestStatus.FINISHED && cm.getContestStatus()==ContestStatus.FINISHED)
            return this.getTimeTillStart() > cm.getTimeTillStart() ? -1 : 1;


        return this.getTimeTillStart() < cm.getTimeTillStart() ? -1 : 1;
    }


}
