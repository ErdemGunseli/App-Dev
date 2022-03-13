package com.example.PocketMaths;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Locale;

public class Task {

    private int id;
    private String name;
    private int accountId;
    private int passMark;
    private String reward;
    private int questionSetId;
    private boolean isCompleted;

    //TODO: Add date due??

    public Task(int id, int accountId, String name, int passMark, String reward, int questionSetId, boolean isCompleted) {
        this.id = id;
        this.name = name.toUpperCase(Locale.ROOT);
        this.accountId = accountId;
        this.passMark = passMark;
        this.reward = reward.toUpperCase(Locale.ROOT);
        this.questionSetId = questionSetId;
        this.isCompleted = isCompleted;
    }

    public int getId() {return id;}

    public void setId(int id) {this.id = id;}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getReward() {
        return reward;
    }

    public void setReward(String reward) {
        this.reward = reward;
    }

    public int getQuestionSetId() {
        return questionSetId;
    }

    public void setQuestionSetId(int questionSetId) {
        this.questionSetId = questionSetId;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public void setCompleted(boolean completed) {
        this.isCompleted = completed;
    }

    public int getPassMark() {
        return passMark;
    }

    public void setPassMark(int passMark) {
        this.passMark = passMark;
    }

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }
}
