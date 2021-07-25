package com.example.codeforcesapp.networking.common;

import com.example.codeforcesapp.screens.common.BaseObservable;

import java.util.ArrayList;

public abstract class FetchItemsUseCase<ResponseType, EntryModel> extends BaseObservable<FetchItemsUseCase.OnFetchItemsListener<EntryModel>> {
    public static final String NO_INTERNET= "Check Network Connection And Try Again";

    public interface OnFetchItemsListener<EntryModel> {
        void onItemListFetched(ArrayList<EntryModel> list);
        void onFetchItemListFetchFailed(String error);
    }

    protected CodeforcesAPI cfApi = RetrofitInstance.getCodeforcesAPIInstance();

    protected abstract void fetchItems(boolean ignoreCacheAndForceRetrieve, boolean notifyListeners);

    public synchronized void prepareItems(){
        fetchItems(true, false);
    }

    public synchronized void fetchItems(boolean ignoreCacheAndForceRetrieve){
        fetchItems(ignoreCacheAndForceRetrieve,true);
    }

    protected abstract void process(ResponseType response);

    protected abstract void notifyError(String errorMsg);
    protected abstract void notifySuccess();

}
