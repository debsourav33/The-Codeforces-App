package com.example.codeforcesapp.networking.Contest;

import com.example.codeforcesapp.data.contest.CFContest;
import com.example.codeforcesapp.data.contest.CFContestEntry;
import com.example.codeforcesapp.data.contest.ContestModel;
import com.example.codeforcesapp.data.contest.ContestStatus;

import java.util.Collections;

public class FetchUpcomingContestListUseCase extends FetchContestListUseCase {

    public static FetchUpcomingContestListUseCase fetchUpcomingContestListUseCase;

    private FetchUpcomingContestListUseCase(){

    }

    public static FetchUpcomingContestListUseCase getInstance() {
        if(fetchUpcomingContestListUseCase==null){
            fetchUpcomingContestListUseCase= new FetchUpcomingContestListUseCase();
        }

        return fetchUpcomingContestListUseCase;
    }

    @Override
    protected void process(CFContest cfContest) {
        cfContestEntryList = cfContest.getResultContestList();

        for (CFContestEntry contestEntry : cfContestEntryList) {
            ContestModel cm= new ContestModel(contestEntry);

            if(cm.getContestStatus() == ContestStatus.FINISHED)  continue;

            mList.add(cm);
        }

        Collections.sort(mList);
    }
}
