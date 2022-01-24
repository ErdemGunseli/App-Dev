package com.example.xmasdraft;

public class Question {

    // Each question will have some text, an array of answer options, a correct answer and possibly an image.
    private String questionText;
    private String[] answers;
    private int correctAnswerIndex;
    private int imageID;

    private boolean attempted;

    private int pointsPossible;
    private int pointsEarned = 0;

    private int chosenAnswerIndex;

    public Question(String questionText, int imageID, String[] answers, int correctAnswerIndex, int pointsPossible) {
        this.questionText = questionText;
        this.imageID = imageID;
        this.answers = answers;
        this.correctAnswerIndex = correctAnswerIndex;
        this.pointsPossible = pointsPossible;
    }

    // Question Text
    public String getQuestionText() {
        return questionText;
    }

    public void setQuestionText(String questionText) {
        this.questionText = questionText;
    }


    // Possible Answers
    public String[] getAnswers() {
        return answers;
    }

    public void setAnswers(String[] answers) {
        this.answers = answers;
    }


    // Correct Answer Index
    public int getCorrectAnswerIndex() {
        return correctAnswerIndex;
    }

    public void setCorrectAnswerIndex(int correctAnswerIndex) {
        this.correctAnswerIndex = correctAnswerIndex;
    }

    // Image ID (optional)
    public int getImageID() {
        return imageID;
    }

    public void setImageID(int imageID) {
        this.imageID = imageID;
    }


    // Marking the Question
    public boolean checkAnswer(int chosenAnswerIndex) {
        if (correctAnswerIndex == chosenAnswerIndex) {

            if (!attempted) {
                this.pointsEarned = this.pointsPossible;
                this.chosenAnswerIndex = chosenAnswerIndex;
            }
            return true;
        }

        this.attempted = true;
        this.chosenAnswerIndex = chosenAnswerIndex;
        return false;
    }


    public boolean isAttempted() {
        return attempted;
    }

    public void setAttempted(boolean attempted) {
        this.attempted = attempted;
    }

    public int getPointsPossible() {
        return pointsPossible;
    }

    public void setPointsPossible(int pointsPossible) {
        this.pointsPossible = pointsPossible;
    }

    public int getPointsEarned() {
        return pointsEarned;
    }

    public void setPointsEarned(int pointsEarned) {
        this.pointsEarned = pointsEarned;
    }

    public int getChosenAnswerIndex() {return chosenAnswerIndex;}

    public void setChosenAnswerIndex(int chosenAnswerIndex) {this.chosenAnswerIndex = chosenAnswerIndex;}
}



