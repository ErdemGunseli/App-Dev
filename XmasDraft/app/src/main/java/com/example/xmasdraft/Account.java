package com.example.xmasdraft;

import java.util.ArrayList;

public class Account {
    // A class to hold the account details.
//test
    private String parentName, studentName, email, password, pin;

    private String accountType;

    private ArrayList<QuestionSetHistory> questionSetsCompleted = new ArrayList<>();


    public Account(String parentName, String studentName, String email, String password, String pin) {
        this.parentName = parentName;
        this.studentName = studentName;
        this.email = email;
        this.password = password;
        this.pin = pin;
        this.accountType = "Member";
    }

    public Account(){
        this.accountType = "Guest";
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

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public ArrayList<QuestionSetHistory> getQuestionSetsCompleted() {
        return questionSetsCompleted;
    }

    public void setQuestionSetsCompleted(ArrayList<QuestionSetHistory> questionSetsCompleted) {
        this.questionSetsCompleted = questionSetsCompleted;
    }

    public void addQuestionSet(QuestionSetHistory questionSetHistory) {
        this.questionSetsCompleted.add(questionSetHistory);
    }


}
