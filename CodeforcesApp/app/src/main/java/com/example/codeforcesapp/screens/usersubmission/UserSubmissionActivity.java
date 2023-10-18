package com.example.codeforcesapp.screens.usersubmission;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.codeforcesapp.networking.UserSubmission.FetchUserSubmissionListUseCase;
import com.example.codeforcesapp.data.usersubmission.UserSubmissionModel;
import com.example.codeforcesapp.networking.common.FetchItemsUseCase;
import com.example.codeforcesapp.screens.navigationviews.BaseNavigationActivity;
import com.example.codeforcesapp.screens.navigationviews.BaseNavigationView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class UserSubmissionActivity extends BaseNavigationActivity implements FetchItemsUseCase.OnFetchItemsListener<UserSubmissionModel>,  UserSubmissionViewMvc.OnUserHandlerChangeListener{

    UserSubmissionViewMvc userSubmissionViewMvc;
    FetchUserSubmissionListUseCase fetchUserSubmissionListUseCase;
    HashMap<String,Float> verdictMap= new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        userSubmissionViewMvc= getViewMvcFactory().getUserSubmissionViewMvc(null);
        fetchUserSubmissionListUseCase = FetchUserSubmissionListUseCase.getInstance(this);


        setContentView(userSubmissionViewMvc.getRootView());

    }

    @Override
    protected void onStart() {
        super.onStart();

        startListeningToFetchUserSubmission(false);

        userSubmissionViewMvc.registerListener(this);
    }

    private void startListeningToFetchUserSubmission(boolean ignoreCacheAndForceRetrieve) {
        if(fetchUserSubmissionListUseCase.hasUserHandler()) {
            fetchUserSubmissionListUseCase.registerListener(this);
            fetchUserSubmissionListUseCase.fetchItems(ignoreCacheAndForceRetrieve);
        }
    }

    @Override
    protected void onStop() {
        super.onStop();

        fetchUserSubmissionListUseCase.unregisterListener(this);
    }

    @Override
    protected BaseNavigationView getNavView() {
        return userSubmissionViewMvc;
    }

    private void initVerdictMap(){
        verdictMap.clear();
        verdictMap.put("OK",0f);
        verdictMap.put("WRONG_ANSWER",0f);
        verdictMap.put("TIME_LIMIT_EXCEEDED",0f);
        verdictMap.put("COMPILATION_ERROR",0f);
        verdictMap.put("RUNTIME_ERROR",0f);
        verdictMap.put("MEMORY_LIMIT_EXCEEDED",0f);
    }

    @Override
    public void onItemListFetched(List<UserSubmissionModel> list) {
        initVerdictMap();

        for(UserSubmissionModel model: list) {
            Log.i("dods", model.getId() + " " + model.getVerdict());

            String verdict= model.getVerdict();

            if(!verdictMap.containsKey(verdict))  verdictMap.put(verdict,0f);
            float cnt= verdictMap.get(verdict);
            verdictMap.put(verdict,cnt+1);
        }

        userSubmissionViewMvc.bindItems(verdictMap);
    }

    @Override
    public void onFetchItemListFetchFailed(String error) {
        Toast.makeText(this, error, Toast.LENGTH_SHORT).show();
    }


    @Override
    public void onUserHandlerChanged(String userHandler) {
        fetchUserSubmissionListUseCase.setUserHandler(userHandler);
        startListeningToFetchUserSubmission(true);
    }
}
