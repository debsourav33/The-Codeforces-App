package com.example.codeforcesapp.usecases;

import com.example.codeforcesapp.networking.Contest.ContestModel;
import com.example.codeforcesapp.screens.common.BaseObservable;

public class PrepareItemUseCase extends BaseObservable<PrepareItemUseCase.Listener> {
    String names;
    public interface Listener{
        void onItemPrepared(String names);
    }

    public void prepareItemAndNotify(ContestModel contestModel){
        names= contestModel.getNames();
        notifySuccess();
    }

    private void notifySuccess() {
        for(Listener listener: getListeners())
            listener.onItemPrepared(names);
    }


}
