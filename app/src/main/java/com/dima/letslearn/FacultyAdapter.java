package com.dima.letslearn;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class FacultyAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    private ArrayList<Faculty> faculties;
    private Activity activity ;

    public FacultyAdapter(Activity activity, ArrayList<Faculty> faculties){
        this.activity = activity;
        this.faculties = faculties;
    }

    public void setFaculties(ArrayList<Faculty> faculties){
        this.faculties = faculties;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.faculty_item, parent, false);
        return new FacultyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        FacultyViewHolder facultyViewHolder = (FacultyViewHolder) holder;
        Faculty faculty = getItem(position);

        facultyViewHolder.faculty_tv_name.setText(faculty.getName());
        facultyViewHolder.faculty_tv_bagGrade.setText(faculty.getBagrotGrade() + "");
        facultyViewHolder.faculty_tv_psychoGrade.setText(faculty.getPsychometricGrade() + "");


    }

    @Override
    public int getItemCount() {
        return faculties.size();
    }

    public Faculty getItem(int position){
        return faculties.get(position);
    }

    public class FacultyViewHolder extends RecyclerView.ViewHolder {
        public TextView faculty_tv_name;
        public TextView faculty_tv_psychoGrade;
        public TextView faculty_tv_bagGrade;

        public FacultyViewHolder(@NonNull View itemView) {
            super(itemView);
            faculty_tv_name =itemView.findViewById(R.id.faculty_tv_name);
            faculty_tv_psychoGrade = itemView.findViewById(R.id.faculty_tv_psychoGrade);
            faculty_tv_bagGrade = itemView.findViewById(R.id.faculty_tv_bagGrade);

        }
    }
}
