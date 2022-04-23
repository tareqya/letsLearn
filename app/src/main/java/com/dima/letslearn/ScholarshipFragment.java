package com.dima.letslearn;

import android.app.Activity;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;

public class ScholarshipFragment extends Fragment {

    private Activity activity;
    private RecyclerView scholarship_screen_RV_scholarships;

    public ScholarshipFragment(Activity  activity) {
        // Required empty public constructor
        this.activity = activity;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_scholarship, container, false);
        scholarship_screen_RV_scholarships = view.findViewById(R.id.scholarship_screen_RV_scholarships);

        Database db =new Database();
        db.setScholarshipCallBack(scholarshipCallBack);
        db.getScholarships();

        return view;
    }

    private ScholarshipCallBack scholarshipCallBack = new ScholarshipCallBack() {
        @Override
        public void onScholarshipsFetchDone(ArrayList<Scholarship> scholarships, boolean requestStatus) {
            if(!requestStatus){
                Toast.makeText(activity, "internet connections error!", Toast.LENGTH_SHORT).show();
                return;
            }

            ScholarshipAdapter adapter = new ScholarshipAdapter(activity, scholarships);
            scholarship_screen_RV_scholarships.setAdapter(adapter);
            scholarship_screen_RV_scholarships.setLayoutManager(new LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false));
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