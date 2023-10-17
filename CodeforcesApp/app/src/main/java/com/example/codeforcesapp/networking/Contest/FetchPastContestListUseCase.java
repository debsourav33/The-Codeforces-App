package com.example.codeforcesapp.networking.Contest;

import android.util.Log;

import com.example.codeforcesapp.data.contest.CFContest;
import com.example.codeforcesapp.data.contest.CFContestEntry;
import com.example.codeforcesapp.data.contest.ContestModel;

import java.util.Collections;

public class FetchPastContestListUseCase extends FetchContestListUseCase {
    public static FetchPastContestListUseCase fetchPastContestListUseCase;

    public static FetchPastContestListUseCase getInstance() {
        if(fetchPastContestListUseCase==null)
            fetchPastContestListUseCase= new FetchPastContestListUseCase();

        return fetchPastContestListUseCase;
    }

    @Override
    protected void process(CFContest cfContest) {
        cfContestEntryList = cfContest.getResultContestList();

        for (CFContestEntry contest : cfContestEntryList) {
            ContestModel cm= new ContestModel(contest.getName(), contest.getStartTime());

            if(cm.getTimeTillStart()>=0)  continue;

            mList.add(cm);
        }

        Log.d("fetchPastContest", "fetched data: " + mList);
        //Collections.sort(mList);

    }
}
