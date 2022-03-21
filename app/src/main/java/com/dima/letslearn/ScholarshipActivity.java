package com.dima.letslearn;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;

public class ScholarshipActivity extends AppCompatActivity {
    RecyclerView scholarship_screen_RV_scholarships;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scholarship);
        scholarship_screen_RV_scholarships = findViewById(R.id.scholarship_screen_RV_scholarships);

        Database db =new Database();
        db.setScholarshipCallBack(scholarshipCallBack);
        db.getScholarships();
    }

    private ScholarshipCallBack scholarshipCallBack = new ScholarshipCallBack() {
        @Override
        public void onScholarshipsFetchDone(ArrayList<Scholarship> scholarships, boolean requestStatus) {
            if(!requestStatus){
                Toast.makeText(ScholarshipActivity.this, "internet connections error!", Toast.LENGTH_SHORT).show();
                return;
            }

            ScholarshipAdapter adapter = new ScholarshipAdapter(ScholarshipActivity.this, scholarships);
            scholarship_screen_RV_scholarships.setAdapter(adapter);
            scholarship_screen_RV_scholarships.setLayoutManager(new LinearLayoutManager(ScholarshipActivity.this, LinearLayoutManager.VERTICAL, false));
            scholarship_screen_RV_scholarships.setHasFixedSize(true);
            scholarship_screen_RV_scholarships.setItemAnimator(new DefaultItemAnimator());
            adapter.setScholarshipAdapter_callBack(new ScholarshipAdapter_CallBack() {
                @Override
                public void onClick(Scholarship scholarship, int position) {
                    adapter.notifyItemChanged(position);
                }
            });
        }
    };
}