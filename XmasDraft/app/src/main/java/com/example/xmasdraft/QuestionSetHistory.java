package com.example.xmasdraft;

import java.util.Date;

public class QuestionSetHistory {

    private String name;

    private int pointsPossible;
    private int pointsEarned;
    private int result;

    private int firstAttempt;
    private int secondAttempt;
    private int moreAttempts;

    private int numberOfQuestions;

    private String dateCompleted;


    public QuestionSetHistory(String name, int pointsEarned, int pointsPossible, int result, int firstAttempt, int secondAttempt, int numberOfQuestions, String dateCompleted) {
        this.name = name;
        this.pointsEarned = pointsEarned;
        this.pointsPossible = pointsPossible;
        this.result = result;
        this.firstAttempt = firstAttempt;
        this.secondAttempt = secondAttempt;
        this.moreAttempts = numberOfQuestions - firstAttempt - secondAttempt;
        this.numberOfQuestions = numberOfQuestions;
        this.dateCompleted = dateCompleted;
    }

    public String getName() {
        return name;
    }

    public int getPointsPossible() {
        return pointsPossible;
    }

    public int getPointsEarned() {
        return pointsEarned;
    }

    public int getResult() {
        return result;
    }

    public int getFirstAttempt() {
        return firstAttempt;
    }

    public int getSecondAttempt() {
        return secondAttempt;
    }

    public int getMoreAttempts() {
        return moreAttempts;
    }

    public int getNumberOfQuestions() {
        return numberOfQuestions;
    }

    public String getDateCompleted() {
        return dateCompleted;
    }
}
