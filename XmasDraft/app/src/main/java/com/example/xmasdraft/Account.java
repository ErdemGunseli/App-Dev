package com.example.xmasdraft;

import java.util.ArrayList;

public class Account {
    // A class to hold the account details.

    private String parentName, studentName, email, password, pin;

    private float timeSpent;

    private int numberOfActivitiesCompleted;
    private ArrayList<String> activitiesCompleted;

    private float averageScore;
    private ArrayList<Float> activityScores;


    public Account(String parentName, String studentName, String email, String password, String pin) {
        this.parentName = parentName;
        this.studentName = studentName;
        this.email = email;
        this.password = password;
        this.pin = pin;
    }

    public String getParentName() {
        return parentName;
    }

    public void setParentName(String parentName) {
        this.parentName = parentName;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }
}
