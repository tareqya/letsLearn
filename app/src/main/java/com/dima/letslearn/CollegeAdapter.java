package com.dima.letslearn;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class CollegeAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    private Activity activity;
    private ArrayList<College> colleges = new ArrayList<>();
    private CollegeAdapter_CallBack collegeAdapter_callBack;

    public CollegeAdapter(Activity activity, ArrayList<College> colleges) {
        this.activity = activity;
        this.colleges = colleges;
    }

    public void setColleges(ArrayList<College> colleges){
        this.colleges = colleges;
    }

    public void setCollegeAdapter_callBack(CollegeAdapter_CallBack collegeAdapter_callBack){
        this.collegeAdapter_callBack = collegeAdapter_callBack;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.college_item, parent, false);
        return new CollegeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        CollegeViewHolder collegeViewHolder = (CollegeViewHolder) holder;
        College college = getItem(position);

        collegeViewHolder.collage_tv_disc.setText(college.getDescription());
        collegeViewHolder.collage_tv_name.setText(college.getName());
        if(college.getLogoUrl() != null)
            Glide.with(activity).load(college.getLogoUrl()).into(collegeViewHolder.collage_img_logo);

    }

    @Override
    public int getItemCount() {
        return this.colleges.size();
    }

    public College getItem(int index){
        return this.colleges.get(index);
    }

    public class CollegeViewHolder extends RecyclerView.ViewHolder {
        public ImageView collage_img_logo;
        public TextView collage_tv_disc;
        public TextView collage_tv_name;
        public CollegeViewHolder(@NonNull View itemView) {
            super(itemView);
            this.collage_img_logo = itemView.findViewById(R.id.collage_img_logo);
            this.collage_tv_disc = itemView.findViewById(R.id.collage_tv_disc);
            this.collage_tv_name = itemView.findViewById(R.id.collage_tv_name);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    College college = getItem(getAdapterPosition());
                    if(collegeAdapter_callBack != null){
                        collegeAdapter_callBack.onClick(college);
                    }
                }
            });
        }
    }
}
