package com.example.codeforcesapp.screens.navigationviews;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.ActivityManager;
import android.media.Image;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.example.codeforcesapp.R;
import com.example.codeforcesapp.common.ActivityManagerHost;
import com.example.codeforcesapp.screens.common.ObservableViewMvc;
import com.google.android.material.navigation.NavigationView;

import java.util.HashSet;
import java.util.Set;

public class BaseNavigationView<ListenerType> extends ObservableViewMvc<ListenerType> implements INavDrawerViewMvc, DrawerLayout.DrawerListener
{
    DrawerLayout mDrawer;
    NavigationView mNavView;
    FrameLayout mFrame;

    Toolbar toolbar;
    View toolbarView;
    ImageButton btnDrawer;

    Set<NavigationListener> navigationListeners= new HashSet<>();
    DrawerItem itemClicked= null;
    ActivityManagerHost manager= ActivityManagerHost.getInstance();

    public BaseNavigationView(LayoutInflater inflater, ViewGroup parent) {
        super.setRootView(inflater.inflate(R.layout.nav_view,parent,false));

        mFrame= findViewById(R.id.frame_layout);
        mNavView = findViewById(R.id.nav_view);
        mDrawer= findViewById(R.id.drawer_layout);
        mDrawer.setDrawerListener(this);


        //toolbarView= inflater.inflate(R.layout.element_toolbar,parent,false);



        //toolbar.addView(inflater.inflate(R.layout.layout_toolbar,parent,false));
        /*btnDrawer= toolbar.findViewById(R.id.btnDrawer);

        */

        mNavView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.drawer_menu_list:
                        itemClicked= DrawerItem.UPCOMING_CONTESTS;
                        break;
                    case R.id.drawer_menu_userinfo:
                        itemClicked= DrawerItem.USER_INFO;
                        break;
                    case R.id.drawer_menu_submissions:
                        itemClicked= DrawerItem.USER_SUBMISSIONS;
                        break;
                }

                closeDrawer();

                return true;
            }
        });
    }

    protected void initToolbar(){
        ImageButton btnDrawer= findViewById(R.id.btnDrawer);
        Log.i("dos", "BaseNavigationView: "+btnDrawer.toString());
        btnDrawer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("dos", "BaseNavigationView: clicked");
                if(isDrawerOpened())  closeDrawer();
                else openDrawer();
            }
        });
    }


    public void onDrawerItemClicked(DrawerItem item){
        for(NavigationListener listener: getNavigationListeners())
            listener.onDrawerItemClicked(item);
    }

    @Override
    public void setRootView(View view) {
        mFrame.removeAllViews();
        mFrame.addView(view);
    }

    @Override
    public void openDrawer() {
        mDrawer.openDrawer(GravityCompat.START);
    }

    @Override
    public void closeDrawer() {
        mDrawer.closeDrawers();
    }

    @SuppressLint("WrongConstant")
    @Override
    public boolean isDrawerOpened() {
        return mDrawer.isDrawerOpen(Gravity.START);

    }

    public void registerNavigationListener(NavigationListener listener){
        navigationListeners.add(listener);
    }

    public void unregisterNavigationListener(NavigationListener listener){
        navigationListeners.remove(listener);
    }

    public Set<NavigationListener> getNavigationListeners(){
        return navigationListeners;
    }

    @Override
    public void onDrawerSlide(@NonNull View drawerView, float slideOffset) {

    }

    @Override
    public void onDrawerOpened(@NonNull View drawerView) {

    }

    @Override
    public void onDrawerClosed(@NonNull View drawerView) {
        if(itemClicked!=null)  onDrawerItemClicked(itemClicked);
        itemClicked= null;
    }

    @Override
    public void onDrawerStateChanged(int newState) {

    }

    public View getToolbarView() {
        return toolbarView;
    }

    public Toolbar getToolbar(){
        return toolbar;
    }
}
