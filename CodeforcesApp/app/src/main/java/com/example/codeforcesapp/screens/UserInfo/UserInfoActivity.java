package com.example.codeforcesapp.screens.UserInfo;

import android.os.Bundle;
import android.widget.Toast;

import com.example.codeforcesapp.networking.UserInfo.FetchUserInfoUseCase;
import com.example.codeforcesapp.data.userinfo.UserInfoModel;
import com.example.codeforcesapp.networking.common.FetchItemsUseCase;
import com.example.codeforcesapp.screens.navigationviews.BaseNavigationActivity;
import com.example.codeforcesapp.screens.navigationviews.BaseNavigationView;

import java.util.ArrayList;

public class UserInfoActivity extends BaseNavigationActivity implements FetchItemsUseCase.OnFetchItemsListener<UserInfoModel>, UserInfoViewMvc.OnUserHandlerChangeListener {
    UserInfoViewMvc mViewMvc;
    FetchUserInfoUseCase fetchUserInfoUseCase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mViewMvc= getViewMvcFactory().getUserInfoViewMvc(null);

        fetchUserInfoUseCase= FetchUserInfoUseCase.getInstance();

        setContentView(mViewMvc.getRootView());
    }

    @Override
    protected BaseNavigationView getNavView() {
        return mViewMvc;
    }

    @Override
    protected void onStart() {
        super.onStart();

        mViewMvc.registerListener(this);

        startListeningToFetchUserSubmission(false);

    }

    private void startListeningToFetchUserSubmission(boolean ignoreCacheAndForceRetrieve) {
        if(fetchUserInfoUseCase.hasUserHandler()) {
            fetchUserInfoUseCase.registerListener(this);
            fetchUserInfoUseCase.fetchItems(ignoreCacheAndForceRetrieve);
        }
    }

    @Override
    protected void onStop() {
        super.onStop();

        mViewMvc.unregisterListener(this);
        fetchUserInfoUseCase.unregisterListener(this);
    }

    @Override
    public void onItemListFetched(ArrayList<UserInfoModel> list) {
        passItem(list.get(0));
    }

    @Override
    public void onFetchItemListFetchFailed(String error) {
        Toast.makeText(this, error, Toast.LENGTH_SHORT).show();
    }

    private void passItem(UserInfoModel userInfoModel) {
        mViewMvc.bindItem(userInfoModel);
    }

    @Override
    public void onUserHandlerChanged(String userHandler) {
        fetchUserInfoUseCase.setUserHandler(userHandler);
        startListeningToFetchUserSubmission(true);
    }


}
