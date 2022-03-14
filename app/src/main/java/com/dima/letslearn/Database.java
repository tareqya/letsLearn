package com.dima.letslearn;
import android.net.Uri;
import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.Iterator;

public class Database {
    private FirebaseDatabase mDatabase;
    private FirebaseStorage mStorage;
    private CollegeCallBack collegeCallBack;

    public Database(){
        mDatabase = FirebaseDatabase.getInstance("https://letslearn-5d4de-default-rtdb.firebaseio.com");
        mStorage = FirebaseStorage.getInstance();
    }

    public void setCollegeCallBack(CollegeCallBack collegeCallBack){
        this.collegeCallBack = collegeCallBack;
    }

    public void insertCollege(College college){
        mDatabase.getReference().child("Colleges").push().setValue(college);
    }

    public void getCollege(String key) {
        mDatabase.getReference().child("Colleges").child(key).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                College college = snapshot.getValue(College.class);
                college.setId(snapshot.getKey());
                StorageReference storageReference = mStorage.getReference("collegeImages/"+college.getImageUrl());
                Task<Uri> task = storageReference.getDownloadUrl();
                while (!task.isComplete());
                if(task.getException() == null)
                    college.setImageUrl(task.getResult().toString());
                else
                    college.setImageUrl(null);

                if(collegeCallBack != null){
                    collegeCallBack.onCollegeDataFetch(college);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
    public void getColleges(){
        mDatabase.getReference().child("Colleges").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                ArrayList<College> arr = new ArrayList<>();
                for(DataSnapshot data: snapshot.getChildren()){
                    College college = data.getValue(College.class);
                    college.setId(data.getKey());
                    //download logo url
                    StorageReference storageReference = mStorage.getReference("collegeImages/"+college.getLogoUrl());
                    Task<Uri> task = storageReference.getDownloadUrl();
                    while (!task.isComplete());
                    if(task.getException() == null)
                        college.setLogoUrl(task.getResult().toString());
                    else
                        college.setLogoUrl(null);

                    arr.add(college);
                }
                if(collegeCallBack != null){
                    collegeCallBack.onCollegesDataFetch(arr);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }


}
