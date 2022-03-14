package com.dima.letslearn;

import java.util.ArrayList;

public class College {
    private String id;
    private String name;
    private String description;
    private String logoUrl;
    private String imageUrl;
    private String collegeWebSiteUrl;
    private ArrayList<Faculty> faculties;
    private boolean university;


    public College(String name, String description, String logoUrl, String imageUrl, String collegeWebSiteUrl, boolean university ,ArrayList<Faculty> faculties) {
        this.name = name;
        this.description = description;
        this.logoUrl = logoUrl;
        this.imageUrl = imageUrl;
        this.collegeWebSiteUrl = collegeWebSiteUrl;
        this.faculties = faculties;
        this.university = university;
    }

    public College(){
        this.faculties = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public College setName(String name) {
        this.name = name;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public College setDescription(String description) {
        this.description = description;
        return this;
    }

    public String getLogoUrl() {
        return logoUrl;
    }

    public College setLogoUrl(String logoUrl) {
        this.logoUrl = logoUrl;
        return this;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public College setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
        return this;
    }

    public String getCollegeWebSiteUrl() {
        return collegeWebSiteUrl;
    }

    public College setCollegeWebSiteUrl(String collegeWebSiteUrl) {
        this.collegeWebSiteUrl = collegeWebSiteUrl;
        return this;
    }

    public ArrayList<Faculty> getFaculties() {
        return faculties;
    }

    public College setFaculties(ArrayList<Faculty> faculties) {
        this.faculties = faculties;
        return this;
    }

    public boolean isUniversity() {
        return university;
    }

    public College setUniversity(boolean university) {
        this.university = university;
        return this;
    }

    public String getId() {
        return id;
    }

    public College setId(String id) {
        this.id = id;
        return this;
    }
}
