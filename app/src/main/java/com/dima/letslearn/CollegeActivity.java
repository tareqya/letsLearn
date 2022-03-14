package com.dima.letslearn;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.android.material.button.MaterialButton;

import java.util.ArrayList;

public class CollegeActivity extends AppCompatActivity {
    private final String SEARCH_TYPE_PSYCHO = "psycho";
    private final String SEARCH_TYPE_BAG = "bag";

    private Database db;
    private String college_key;
    private ImageView collageScreen_img_collegeImage;
    private TextView collageScreen_tv_name, collageScreen_tv_disc;
    private RecyclerView collageScreen_RV_faculty;
    private ProgressBar collageScreen_progressBar;
    private NestedScrollView collageScreen_scrollView;
    private RadioGroup collageScreen_rbg_type;
    private String searchType = SEARCH_TYPE_PSYCHO;
    private EditText collageScreen_et_toGrade, collageScreen_et_fromGrade;
    private MaterialButton collageScreen_btn_filter;
    private ArrayList<Faculty> faculties;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_college);

        Intent intent = getIntent();
        college_key = intent.getStringExtra(MainActivity.COLLEGE_ID);
        findViews();
        initVars();
    }

    private void findViews() {
        collageScreen_img_collegeImage = findViewById(R.id.collageScreen_img_collegeImage);
        collageScreen_tv_name = findViewById(R.id.collageScreen_tv_name);
        collageScreen_tv_disc = findViewById(R.id.collageScreen_tv_disc);
        collageScreen_RV_faculty = findViewById(R.id.collageScreen_RV_faculty);
        collageScreen_progressBar = findViewById(R.id.collageScreen_progressBar);
        collageScreen_scrollView = findViewById(R.id.collageScreen_scrollView);
        collageScreen_rbg_type = findViewById(R.id.collageScreen_rbg_type);
        collageScreen_et_toGrade = findViewById(R.id.collageScreen_et_toGrade);
        collageScreen_et_fromGrade = findViewById(R.id.collageScreen_et_fromGrade);
        collageScreen_btn_filter = findViewById(R.id.collageScreen_btn_filter);

    }

    private void initVars() {
        this.db = new Database();
        db.setCollegeCallBack(collegeCallBack);
        db.getCollege(college_key);
        collageScreen_rbg_type.check(R.id.collageScreen_rb_psycho);
        collageScreen_rbg_type.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @SuppressLint("NonConstantResourceId")
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i){
                    case R.id.collageScreen_rb_psycho:
                        searchType = SEARCH_TYPE_PSYCHO;
                        break;
                    case R.id.collageScreen_rb_bag:
                        searchType = SEARCH_TYPE_BAG;
                        break;
                }
            }
        });

        collageScreen_btn_filter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int fromGrade = Integer.parseInt(collageScreen_et_fromGrade.getText().toString());
                int toGrade = Integer.parseInt(collageScreen_et_toGrade.getText().toString());
                searchForFaculty(fromGrade, toGrade, searchType);
            }
        });
    }

    private CollegeCallBack collegeCallBack = new CollegeCallBack() {
        @Override
        public void onCollegesDataFetch(ArrayList<College> colleges) {

        }

        @Override
        public void onCollegeDataFetch(College college) {
            if(college.getImageUrl() != null)
                Glide.with(CollegeActivity.this)
                        .load(college.getImageUrl())
                        .into(collageScreen_img_collegeImage);

            collageScreen_tv_name.setText(college.getName());
            collageScreen_tv_disc.setText(college.getDescription());
            faculties = college.getFaculties();
            FacultyAdapter facultyAdapter = new FacultyAdapter(CollegeActivity.this, college.getFaculties());
            collageScreen_RV_faculty.setAdapter(facultyAdapter);
            collageScreen_RV_faculty.setLayoutManager(new LinearLayoutManager(CollegeActivity.this, LinearLayoutManager.VERTICAL, false));
            collageScreen_RV_faculty.setHasFixedSize(true);
            collageScreen_RV_faculty.setItemAnimator(new DefaultItemAnimator());


            collageScreen_progressBar.setVisibility(View.GONE);
            collageScreen_scrollView.setVisibility(View.VISIBLE);

        }
    };


    @SuppressLint("NotifyDataSetChanged")
    private void searchForFaculty(int fromGrade, int toGrade, String gradeType){
        ArrayList<Faculty> filteredFaculties = new ArrayList<>();
        for(int i = 0; i < this.faculties.size(); i++){
            if(gradeType.equals(SEARCH_TYPE_PSYCHO)){
                if(faculties.get(i).getPsychometricGrade() >= fromGrade && faculties.get(i).getPsychometricGrade() <= toGrade){
                    filteredFaculties.add(faculties.get(i));
                }
            }else {
                if(faculties.get(i).getBagrotGrade() >= fromGrade && faculties.get(i).getBagrotGrade() <= toGrade){
                    filteredFaculties.add(faculties.get(i));
                }
            }
        }

        FacultyAdapter facultyAdapter = (FacultyAdapter) collageScreen_RV_faculty.getAdapter();
        facultyAdapter.setFaculties(filteredFaculties);
        facultyAdapter.notifyDataSetChanged();
    }
}