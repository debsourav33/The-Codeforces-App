package com.example.codeforcesapp.networking.Contest;

import android.content.Context;

import com.example.codeforcesapp.data.contest.ContestApiResponse;
import com.example.codeforcesapp.data.contest.ContestEntity;
import com.example.codeforcesapp.data.contest.ContestModel;
import com.example.codeforcesapp.data.contest.ContestStatus;

import java.util.Collections;

public class FetchUpcomingContestListUseCase extends FetchContestListUseCase {

    public static FetchUpcomingContestListUseCase fetchUpcomingContestListUseCase;

    private FetchUpcomingContestListUseCase(Context context){
        super(context);

    }

    public static FetchUpcomingContestListUseCase getInstance(Context context) {
        if(fetchUpcomingContestListUseCase==null){
            fetchUpcomingContestListUseCase= new FetchUpcomingContestListUseCase(context);
        }

        return fetchUpcomingContestListUseCase;
    }

    @Override
    protected void process(ContestApiResponse contest) {
        contestEntityList = contest.getResultContestList();

        for (ContestEntity contestEntry : contestEntityList) {
            ContestModel cm= new ContestModel(contestEntry);

            if(cm.getContestStatus() == ContestStatus.FINISHED)  continue;

            mList.add(cm);
        }

        Collections.sort(mList);
    }
}
