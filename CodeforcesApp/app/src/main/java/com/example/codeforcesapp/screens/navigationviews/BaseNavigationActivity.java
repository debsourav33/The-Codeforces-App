package com.example.codeforcesapp.screens.navigationviews;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;

import com.example.codeforcesapp.R;
import com.example.codeforcesapp.screens.common.BaseActivity;

public abstract class BaseNavigationActivity extends BaseActivity {
    BaseNavigationView navView;
    NavigationListener navigationListenerImpl= new NavigationListenerImpl();

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
    }


    /*protected void initActionBar() {
        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayShowHomeEnabled(false);
        actionBar.setDisplayShowCustomEnabled(true);
        actionBar.setDisplayShowTitleEnabled(false);
        actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);

        View customView = getNavView().getToolbarView();
        actionBar.setCustomView(customView);

        Toolbar parent =(Toolbar) customView.getParent();
        parent.setContentInsetsAbsolute(0,0);
        parent.setPadding(0, 0, 0, 0);
    }*/


    protected abstract BaseNavigationView getNavView();

    @Override
    protected void onStart() {
        super.onStart();
        getNavView().registerNavigationListener(navigationListenerImpl);
    }

    @Override
    protected void onStop() {
        super.onStop();
        getNavView().unregisterNavigationListener(navigationListenerImpl);

    }

    @Override
    public void onBackPressed() {
        if(getNavView().isDrawerOpened()){
            getNavView().closeDrawer();
            return;
        }

        super.onBackPressed();
    }
}
