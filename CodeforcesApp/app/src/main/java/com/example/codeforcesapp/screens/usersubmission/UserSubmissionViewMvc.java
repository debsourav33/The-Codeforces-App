package com.example.codeforcesapp.screens.usersubmission;

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
import android.widget.TextView;
import android.widget.Toast;

import com.example.codeforcesapp.R;
import com.example.codeforcesapp.common.ActivityManagerHost;
import com.example.codeforcesapp.networking.UserSubmission.UserSubmissionModel;
import com.example.codeforcesapp.screens.navigationviews.BaseNavigationView;
import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;

import java.util.ArrayList;
import java.util.HashMap;

public class UserSubmissionViewMvc extends BaseNavigationView<UserSubmissionViewMvc.OnUserHandlerChangeListener> {
    public interface Listener{

    }

    public interface OnUserHandlerChangeListener {
        void onUserHandlerChanged(String userHandler);
    }

    private ArrayList<UserSubmissionModel> mSubmissionList;
    private HashMap<String,Float> verdictMap;
    PieChart pieChart;

    ImageButton btnSearch;
    EditText txtHandler;

    public UserSubmissionViewMvc(LayoutInflater inflater, ViewGroup parent) {
        super(inflater, parent);
        setRootView(inflater.inflate(R.layout.activity_user_submission,parent,false));

        btnSearch= findViewById(R.id.btnSearch);
        btnSearch.setOnClickListener(onSearchClickListener);

        txtHandler= findViewById(R.id.editHandler);
        txtHandler.setOnKeyListener(onKeyListener);

        initToolbar();
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

    private void preparePieChart(){
        pieChart= findViewById(R.id.pieChart);
        pieChart.setVisibility(View.VISIBLE);

        pieChart.setUsePercentValues(false);
        pieChart.getDescription().setEnabled(false);
        pieChart.setExtraOffsets(5,10,5,5);

        pieChart.setDragDecelerationFrictionCoef(.99f);

        pieChart.setDrawHoleEnabled(true);
        pieChart.setHoleColor(Color.WHITE);
        pieChart.setHoleRadius(35f);
        pieChart.setTransparentCircleRadius(45f);
        pieChart.setDrawEntryLabels(false);

        pieChart.animateY(1000,Easing.EaseInOutCubic);

        PieDataSet pieDataSet= getPieDataSet();


        PieData pieData= new PieData(pieDataSet);
        pieData.setValueTextColor(Color.WHITE);
        pieData.setValueTextSize(9f);


        pieChart.setData(pieData);

    }

    private PieDataSet getPieDataSet() throws NullPointerException{
        ArrayList<PieEntry> yValues= new ArrayList<>();

        /*yValues.add(new PieEntry(223f,"Accepted"));
        yValues.add(new PieEntry(180f,"Wrong Answer"));
        yValues.add(new PieEntry(33f,"Time Limit Accepted"));
        yValues.add(new PieEntry(9f,"Compilation Error"));
        yValues.add(new PieEntry(23f,"Runtime Error"));
        yValues.add(new PieEntry(12f,"Memory Limit Exceeded"));*/

        if(verdictMap.get("OK")>0)  yValues.add(new PieEntry(verdictMap.get("OK"),"AC"));
        if(verdictMap.get("WRONG_ANSWER")>0) yValues.add(new PieEntry(verdictMap.get("WRONG_ANSWER"),"WA"));
        if(verdictMap.get("TIME_LIMIT_EXCEEDED")>0) yValues.add(new PieEntry(verdictMap.get("TIME_LIMIT_EXCEEDED"),"TLE"));
        if(verdictMap.get("COMPILATION_ERROR")>0) yValues.add(new PieEntry(verdictMap.get("COMPILATION_ERROR"),"CE"));
        if(verdictMap.get("RUNTIME_ERROR")>0) yValues.add(new PieEntry(verdictMap.get("RUNTIME_ERROR"),"RTE"));
        if(verdictMap.get("MEMORY_LIMIT_EXCEEDED")>0) yValues.add(new PieEntry(verdictMap.get("MEMORY_LIMIT_EXCEEDED"),"MLE"));

        PieDataSet pieDataSet= new PieDataSet(yValues,"Results");
        pieDataSet.setSliceSpace(3f);
        pieDataSet.setSelectionShift(5f);
        pieDataSet.setColors(Submission_Colors);

        return pieDataSet;
    }

    public static final int[] Submission_Colors={
            Color.rgb(28,140,14), Color.RED, Color.rgb(33,150,253),
            Color.rgb(96,125,139), Color.rgb(11,57,139), Color.rgb(255,215,58)

    };

    public void bindItems(ArrayList<UserSubmissionModel> list){
        mSubmissionList= list;
    }

    public void bindItems(HashMap<String,Float> verdictMap){
        this.verdictMap= verdictMap;
        preparePieChart();


    }
}
