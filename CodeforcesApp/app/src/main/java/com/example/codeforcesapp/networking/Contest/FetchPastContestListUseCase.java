package com.example.codeforcesapp.networking.Contest;

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

        Collections.sort(mList);

    }
}
