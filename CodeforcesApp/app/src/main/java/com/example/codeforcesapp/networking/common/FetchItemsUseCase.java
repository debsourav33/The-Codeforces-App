package com.example.codeforcesapp.networking.common;

import android.content.Context;

import com.example.codeforcesapp.screens.common.BaseObservable;

import java.util.ArrayList;
import java.util.List;

public abstract class FetchItemsUseCase<ResponseType, EntryModel> extends BaseObservable<FetchItemsUseCase.OnFetchItemsListener<EntryModel>> {
    public static final String NO_INTERNET= "Check Network Connection And Try Again";

    //context is needed for database insertions
    protected Context context;
    public FetchItemsUseCase(Context context){
        this.context = context;
    }

    public interface OnFetchItemsListener<EntryModel> {
        void onItemListFetched(List<EntryModel> list);
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
