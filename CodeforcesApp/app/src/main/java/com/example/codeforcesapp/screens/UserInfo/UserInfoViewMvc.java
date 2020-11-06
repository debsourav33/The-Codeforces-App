package com.example.codeforcesapp.screens.UserInfo;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.example.codeforcesapp.R;
import com.example.codeforcesapp.common.ActivityManagerHost;
import com.example.codeforcesapp.screens.navigationviews.BaseNavigationView;
import com.example.codeforcesapp.networking.UserInfo.UserInfoModel;
import com.example.codeforcesapp.screens.navigationviews.ColorUtils;
import com.squareup.picasso.Picasso;

public class UserInfoViewMvc extends BaseNavigationView<UserInfoViewMvc.OnUserHandlerChangeListener> {
    private static final String TAG = "dodo";

    ImageView proPic;
    TextView txtName, txtCountry, txtRank, txtMaxRank, txtRating, txtMaxRating;
    String name, country, rank, maxRank, rating, maxRating;

    ImageButton btnSearch;
    EditText txtHandler;

    private ScrollView layoutDetails;

    public interface OnUserHandlerChangeListener {
        void onUserHandlerChanged(String userHandler);
    }

    public UserInfoViewMvc(LayoutInflater inflater, ViewGroup parent) {
        super(inflater, parent);
        setRootView(inflater.inflate(R.layout.activity_user_info,parent,false));

        initItems();
        initToolbar();
    }

    private void initItems() {
        layoutDetails= findViewById(R.id.layoutDetails);

        btnSearch= findViewById(R.id.btnSearch);
        btnSearch.setOnClickListener(onSearchClickListener);

        txtHandler= findViewById(R.id.editHandler);

        txtHandler.setOnKeyListener(onKeyListener);

        proPic= findViewById(R.id.proPic);
        txtName= findViewById(R.id.txtName);
        txtCountry= findViewById(R.id.txtCountry);
        txtRank= findViewById(R.id.txtRank);
        txtMaxRank= findViewById(R.id.txtMaxRank);
        txtRating= findViewById(R.id.txtRating);
        txtMaxRating= findViewById(R.id.txtMaxRating);

        Log.i(TAG, "initItems: "+txtRating);
    }

    private void hideKeyBoard(){
        Activity curActivity= ActivityManagerHost.getInstance().getCurrActivity();
        InputMethodManager mgr = (InputMethodManager) curActivity.getSystemService(Context.INPUT_METHOD_SERVICE);
        mgr.hideSoftInputFromWindow(txtHandler.getWindowToken(), 0);
    }

    private View.OnKeyListener onKeyListener= new View.OnKeyListener() {
        @Override
        public boolean onKey(View v, int keyCode, KeyEvent event) {
            if (event.getAction() == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_ENTER) {
                hideKeyBoard();

                for (OnUserHandlerChangeListener listener : getListeners()) {
                    String userHandler = txtHandler.getText().toString().trim();
                    if (userHandler.equals("")) return false;

                    listener.onUserHandlerChanged(userHandler);
                }
                return true;
            }
            return false;
        }
    };

    private View.OnClickListener onSearchClickListener= new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.btnSearch:
                    hideKeyBoard();

                    for (OnUserHandlerChangeListener listener : getListeners()) {
                        String userHandler = txtHandler.getText().toString().trim();
                        if (userHandler.equals("")) return;

                        listener.onUserHandlerChanged(userHandler);
                    }
                    break;

            }
        }
    };

    public void bindItem(UserInfoModel model){
        Log.i(TAG, "bindItem: "+ model.getFirstName());
        Log.i(TAG, "bindItem: "+ model.getRank());
        Log.i(TAG, "bindItem: "+ model.getProPicUrl());

        layoutDetails.setVisibility(View.VISIBLE);

        name= model.getFullName();
        country= model.getCountry();
        rank= model.getRank();
        maxRank= model.getMaxRank();
        rating= model.getRating();
        maxRating= model.getMaxRating();



        txtName.setText(model.getFullName());
        txtCountry.setText(model.getCountry());
        txtRank.setText(model.getRank());
        txtMaxRank.setText(model.getMaxRank());
        txtRating.setText(model.getRating());
        txtMaxRating.setText(model.getMaxRating());

        setColors();

        Picasso.with(getContext()).load(model.getProPicUrl()).into(proPic);


    }

    private void setColors() {
        if(rating!=null && !rating.equals("")){
            txtName.setTextColor(ColorUtils.ColorForRating(rating));
            txtRating.setTextColor(ColorUtils.ColorForRating(rating));
            txtRank.setTextColor(ColorUtils.ColorForRating(rating));
            txtCountry.setTextColor(ColorUtils.ColorForRating(rating));
        }

        if(maxRating!=null && !maxRating.equals("")){
            txtMaxRank.setTextColor(ColorUtils.ColorForRating(maxRating));
            txtMaxRating.setTextColor(ColorUtils.ColorForRating(maxRating));
        }
    }

}
