package com.example.codeforcesapp.screens.userdetails;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.codeforcesapp.R;
import com.example.codeforcesapp.screens.navigationviews.BaseNavigationView;
import com.example.codeforcesapp.screens.navigationviews.DrawerItem;

public class ListItemDetailsViewMvc extends BaseNavigationView<ListItemDetailsViewMvc.Listener> {
    TextView txtName, txtDetails;

    public interface Listener{
        void switchToListViewScreen();
    }

    public ListItemDetailsViewMvc(LayoutInflater inflater, ViewGroup parent){
        super(inflater, parent);
        setRootView(inflater.inflate(R.layout.item_view,parent,false));

        txtName= findViewById(R.id.txt1);
        txtDetails= findViewById(R.id.txt2);
    }


    public void bindItem(String names){
        txtName.setText(names);
        //txtDetails.setText(user.getStartTime());
    }

}
