package com.dima.letslearn;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ScholarshipAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{
    private Activity activity;
    private ArrayList<Scholarship> scholarships = new ArrayList<>();
    private ScholarshipAdapter_CallBack scholarshipAdapter_callBack;
    public ScholarshipAdapter(Activity activity, ArrayList<Scholarship> scholarships) {
        this.activity = activity;
        this.scholarships = scholarships;
    }

    public void setScholarshipAdapter_callBack(ScholarshipAdapter_CallBack ScholarshipAdapter_callBack){
        this.scholarshipAdapter_callBack = ScholarshipAdapter_callBack;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.scholarship_item, parent, false);
        return new ScholarshipViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ScholarshipViewHolder scholarshipViewHolder = (ScholarshipViewHolder) holder;
        Scholarship scholarship = getItem(position);

        scholarshipViewHolder.scholarship_tv_name.setText(scholarship.getName());
        scholarshipViewHolder.scholarship_tv_disc.setText(scholarship.getDescription());

        if(scholarship.isToggle()){
            scholarshipViewHolder.scholarship_img_icon.setImageResource(R.drawable.ic_baseline_minimize_24);
            scholarshipViewHolder.scholarship_tv_disc.setVisibility(View.VISIBLE);
        }else {
            scholarshipViewHolder.scholarship_img_icon.setImageResource(R.drawable.ic_baseline_add_24);
            scholarshipViewHolder.scholarship_tv_disc.setVisibility(View.GONE);
        }


    }

    @Override
    public int getItemCount() {
        return scholarships.size();
    }

    public Scholarship getItem(int index){
        return this.scholarships.get(index);
    }


    public class ScholarshipViewHolder extends RecyclerView.ViewHolder {
        public ImageView scholarship_img_icon;
        public TextView scholarship_tv_disc;
        public TextView scholarship_tv_name;
        public ScholarshipViewHolder(@NonNull View itemView) {
            super(itemView);
            this.scholarship_img_icon = itemView.findViewById(R.id.scholarship_img_icon);
            this.scholarship_tv_disc = itemView.findViewById(R.id.scholarship_tv_disc);
            this.scholarship_tv_name = itemView.findViewById(R.id.scholarship_tv_name);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Scholarship scholarship = getItem(getAdapterPosition());
                    scholarship.setToggle(!scholarship.isToggle());
                    scholarshipAdapter_callBack.onClick(scholarship, getAdapterPosition());
                }
            });
        }
    }

}
