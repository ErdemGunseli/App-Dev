package com.example.PocketMaths;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Locale;

public class Task {

    private int id;
    private String name;
    private String reward;
    private int questionSetId;
    private boolean isCompleted;

    //TODO: Add date due??

    public Task(int id, String name, String reward, int questionSetId, boolean isCompleted) {
        this.id = id;
        this.name = name.toUpperCase(Locale.ROOT);
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
}
