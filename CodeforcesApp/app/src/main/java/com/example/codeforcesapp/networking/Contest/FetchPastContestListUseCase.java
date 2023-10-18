package com.example.codeforcesapp.networking.Contest;

import android.content.Context;
import android.util.Log;

import com.example.codeforcesapp.data.contest.ContestApiResponse;
import com.example.codeforcesapp.data.contest.ContestEntity;
import com.example.codeforcesapp.data.contest.ContestModel;

import java.util.ArrayList;
import java.util.List;

public class FetchPastContestListUseCase extends FetchContestListUseCase {
    public static FetchPastContestListUseCase fetchPastContestListUseCase;

    private FetchPastContestListUseCase(Context context){
        super(context);
    }

    public static FetchPastContestListUseCase getInstance(Context context) {
        if(fetchPastContestListUseCase==null)
            fetchPastContestListUseCase= new FetchPastContestListUseCase(context);

        return fetchPastContestListUseCase;
    }

    @Override
    protected void process(ContestApiResponse response) {
        List<ContestEntity> processedEntities = new ArrayList<>();
        mContestEntities = response.getResultContestList();

        for (ContestEntity contest : mContestEntities) {
            ContestModel cm= new ContestModel(contest.getName(), contest.getStartTime());

            if(cm.getTimeTillStart()>=0)  continue;

            mList.add(cm);
            processedEntities.add(contest);
        }

        Log.d("fetchPastContest", "fetched data: " + mList);
        //Collections.sort(mList);

    }
}
