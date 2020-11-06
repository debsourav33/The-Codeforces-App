package com.example.codeforcesapp.screens.navigationviews;

import android.graphics.Color;

public class ColorUtils {
    public static final String RANK_NEWBIE= "#808080";
    public static final String RANK_PUPIL= "#008000";
    public static final String RANK_SPECIALIST= "#03a89e";
    public static final String RANK_EXPERT= "#0000ff";
    public static final String RANK_CANDIDATEMASTER= "#aa00aa";
    public static final String RANK_MASTER= "#ff8c00";
    public static final String RANK_GRANDMASTER= "#ff0000";

    public static final int newbieEnd= 1199, pupilStart= 1200, pupilEnd= 1399,
                    specialistStart=1400, specialistEnd= 1599,
                    expertStart= 1699, expertEnd=1899, canMasterStart=1900, canMasterEnd= 2199,
                    masterStart= 2200, masterEnd= 2399, grandmasterStart= 2400;

    public static int ColorForRating(int rating){
        if(rating<= newbieEnd)  return Color.parseColor(RANK_NEWBIE);
        else if(rating<= pupilEnd)  return Color.parseColor(RANK_PUPIL);
        else if(rating<= specialistEnd)  return Color.parseColor(RANK_SPECIALIST);
        else if(rating<= expertEnd)  return Color.parseColor(RANK_EXPERT);
        else if(rating<= canMasterEnd)  return Color.parseColor(RANK_CANDIDATEMASTER);
        else if(rating<= masterEnd)  return Color.parseColor(RANK_MASTER);
        else  return Color.parseColor(RANK_GRANDMASTER);
    }

    public static int ColorForRating(String ratingString){
        int rating= Integer.parseInt(ratingString);

        if(rating<= newbieEnd)  return Color.parseColor(RANK_NEWBIE);
        else if(rating<= pupilEnd)  return Color.parseColor(RANK_PUPIL);
        else if(rating<= specialistEnd)  return Color.parseColor(RANK_SPECIALIST);
        else if(rating<= expertEnd)  return Color.parseColor(RANK_EXPERT);
        else if(rating<= canMasterEnd)  return Color.parseColor(RANK_CANDIDATEMASTER);
        else if(rating<= masterEnd)  return Color.parseColor(RANK_MASTER);
        else  return Color.parseColor(RANK_GRANDMASTER);
    }

}
