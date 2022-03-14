package com.dima.letslearn;

public class Faculty {

    private String name;
    private int bagrotGrade;
    private int psychometricGrade;

    public Faculty(){

    }

    public Faculty(String name, int bagrotGrade, int psychometricGrade) {
        this.name = name;
        this.bagrotGrade = bagrotGrade;
        this.psychometricGrade = psychometricGrade;
    }

    public String getName() {
        return name;
    }

    public Faculty setName(String name) {
        this.name = name;
        return this;
    }

    public int getBagrotGrade() {
        return bagrotGrade;
    }

    public Faculty setBagrotGrade(int bagrotGrade) {
        this.bagrotGrade = bagrotGrade;
        return this;
    }

    public int getPsychometricGrade() {
        return psychometricGrade;
    }

    public Faculty setPsychometricGrade(int psychometricGrade) {
        this.psychometricGrade = psychometricGrade;
        return this;
    }
}
