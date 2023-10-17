package com.example.codeforcesapp.screens.navigationviews;

import android.content.Intent;

import com.example.codeforcesapp.common.ActivityManagerHost;
import com.example.codeforcesapp.screens.contestList.ContestListActivity;
import com.example.codeforcesapp.screens.UserInfo.UserInfoActivity;
import com.example.codeforcesapp.screens.oldcontests.OldContestListActivity;
import com.example.codeforcesapp.screens.usersubmission.UserSubmissionActivity;

public class NavigationListenerImpl implements NavigationListener {
    private ActivityManagerHost manager;

    public NavigationListenerImpl(){
        manager= ActivityManagerHost.getInstance();
    }

    @Override
    public void onDrawerItemClicked(DrawerItem item) {
        switch (item){
            case UPCOMING_CONTESTS:
                switchToUpcomingContestsActivity();
                break;

            case USER_SUBMISSIONS:
                switchToUserSubmissionActivity();
                break;

            case USER_INFO:
                switchToUserInfoActivity();
                break;

            case NEW_FEATURE:
                switchToSomeActivity();
                break;
        }
    }

    private void switchToUpcomingContestsActivity() {
        if(manager.getCurrActivity() instanceof ContestListActivity)  return;

        manager.getCurrActivity().startActivity(new Intent(manager.getCurrActivity(),ContestListActivity.class));
    }

    private void switchToRecentContestsActivity() {

    }

    private void switchToUserInfoActivity() {
        if(manager.getCurrActivity() instanceof UserInfoActivity)  return;

        manager.getCurrActivity().startActivity(new Intent(manager.getCurrActivity(),UserInfoActivity.class));
    }

    private void switchToUserSubmissionActivity() {
        if(manager.getCurrActivity() instanceof UserSubmissionActivity)  return;

        manager.getCurrActivity().startActivity(new Intent(manager.getCurrActivity(),UserSubmissionActivity.class));
    }

    private void switchToSomeActivity(){
        if(manager.getCurrActivity() instanceof OldContestListActivity)  return;

        manager.getCurrActivity().startActivity(new Intent(manager.getCurrActivity(), OldContestListActivity.class));
    }

}
