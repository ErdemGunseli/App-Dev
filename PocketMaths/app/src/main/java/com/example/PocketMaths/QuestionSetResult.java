package com.example.PocketMaths;

import java.util.Date;

public class QuestionSetResult {

    private int questionSetResultId;
    private int questionSetId;
    private int accountId;

    private int pointsPossible;
    private int pointsEarned;
    private int result;

    private int firstAttempt;
    private int secondAttempt;
    private int moreAttempts;

    private String dateCompleted;


    public QuestionSetResult(int questionSetResultId, int questionSetId,int accountId , int pointsEarned, int pointsPossible, int firstAttempt, int secondAttempt, int moreAttempts, String dateCompleted) {
        this.accountId = accountId;
        this.questionSetId = questionSetId;
        this.pointsEarned = pointsEarned;
        this.pointsPossible = pointsPossible;
        this.result = (int) (((float) pointsEarned / (float) pointsPossible) * 100);
        this.firstAttempt = firstAttempt;
        this.secondAttempt = secondAttempt;
        this.moreAttempts =moreAttempts;
        this.dateCompleted = dateCompleted;
    }

    public int getQuestionSetResultId() {
        return questionSetResultId;
    }

    public int getQuestionSetId() {
        return questionSetId;
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

    public String getDateCompleted() {
        return dateCompleted;
    }

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }
}
