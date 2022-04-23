package com.dima.letslearn;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
import android.os.Looper;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;

import com.airbnb.lottie.LottieAnimationView;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;


public class HomeFragment extends Fragment {
    private Database db;
    private ArrayList<College> colleges;
    private RecyclerView home_recycleView_colleges;
    private ProgressBar home_progressbar;
    private TextInputLayout home_txtField_search;
    private LottieAnimationView main_splash;
    private Activity activity;
    public HomeFragment(Activity activity) {
        // Required empty public constructor
        this.activity = activity;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        findViews(view);
        initVars();
        final Handler handler = new Handler(Looper.getMainLooper());
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                //Do something after 100ms
                main_splash.setVisibility(View.INVISIBLE);
            }
        }, 1500);
        return view;
    }
    private void findViews(View view) {
        home_recycleView_colleges = view.findViewById(R.id.home_recycleView_colleges);
        home_progressbar = view.findViewById(R.id.home_progressbar);
        home_txtField_search = view.findViewById(R.id.home_txtField_search);
        main_splash = view.findViewById(R.id.main_splash);
    }

    private void initVars() {
        db = new Database();
        colleges = new ArrayList<>();
        db.setCollegeCallBack(callBack);
        db.getColleges();
        home_txtField_search.getEditText().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                searchForCollage(charSequence.toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

    }

    @SuppressLint("NotifyDataSetChanged")
    private void searchForCollage(String collegeName){
        ArrayList<College> filteredColleges = new ArrayList<>();

        for(int i = 0; i < this.colleges.size(); i++){
            if(this.colleges.get(i).getName().contains(collegeName)){
                filteredColleges.add(this.colleges.get(i));
            }
        }

        CollegeAdapter collegeAdapter = (CollegeAdapter)home_recycleView_colleges.getAdapter();
        if(collegeName != "")
            collegeAdapter.setColleges(filteredColleges);
        else
            collegeAdapter.setColleges(colleges);

        collegeAdapter.notifyDataSetChanged();
    }

    private CollegeCallBack callBack = new CollegeCallBack() {
        @Override
        public void onCollegesDataFetch(ArrayList<College> arr) {
            colleges = arr;
            CollegeAdapter adapter_collage = new CollegeAdapter(activity, arr);
            home_recycleView_colleges.setAdapter(adapter_collage);
            home_recycleView_colleges.setLayoutManager(new LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false));
            home_recycleView_colleges.setHasFixedSize(true);
            home_recycleView_colleges.setItemAnimator(new DefaultItemAnimator());
            home_progressbar.setVisibility(View.GONE);
            adapter_collage.setCollegeAdapter_callBack(new CollegeAdapter_CallBack() {
                @Override
                public void onClick(College college) {
                    Intent intent = new Intent(activity, CollegeActivity.class);
                    intent.putExtra(MainActivity.COLLEGE_ID, college.getId());
                    startActivity(intent);
                }
            });
        }

        @Override
        public void onCollegeDataFetch(College college) {

        }
    };

}