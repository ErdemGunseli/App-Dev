package com.example.PocketMaths;

import java.util.ArrayList;

/**
 * The instances of this class contain the details of each question.
 * The instances of this class are associated to other classes, such as to QuestionSet.
 */
public class Question {

    public static final String WRITTEN = "written";
    public static final String MULTIPLE_CHOICE = "multipleChoice";
    //TODO: Use ID!
    private int id;
    private String text;
    private int imageId;
    private String type;

    // For Multiple Choice
    private String[] answerOptions;
    private int correctAnswerIndex;
    private ArrayList<Integer> userAnswerIndexes = new ArrayList<>();

    // For Written
    private String correctWrittenAnswer;
    private ArrayList<String> userWrittenAnswers = new ArrayList<>();

    private String topic;
    private int attempts = 0;

    private int pointsPossible;
    private int pointsEarned = 0;

    /**
     * Constructor for Multiple Choice Question
     *
     * @param topic              Topic of the question.
     * @param text               The question itself.
     * @param imageId            The id of the image needed - 0 if there is none.
     * @param answerOptions      The multiple-choice options.
     * @param correctAnswerIndex The index of the correct option.
     * @param pointsPossible     The number of points possible.
     */
    public Question(String topic, String text, int imageId, String[] answerOptions, int correctAnswerIndex, int pointsPossible) {
        this.topic = topic;
        this.text = text;
        this.imageId = imageId;
        this.answerOptions = answerOptions;
        this.correctAnswerIndex = correctAnswerIndex;
        this.pointsPossible = pointsPossible;
        this.type = MULTIPLE_CHOICE;
    }

    /**
     * Constructor for Written Question
     *
     * @param topic          Topic of the question.
     * @param text           The question itself.
     * @param imageId        The id of the image needed - 0 if there is none.
     * @param correctAnswer  The correct answer.
     * @param pointsPossible The number of points possible.
     */
    public Question(String topic, String text, int imageId, String correctAnswer, int pointsPossible) {
        this.topic = topic;
        this.text = text;
        this.imageId = imageId;
        this.correctWrittenAnswer = correctAnswer;
        this.pointsPossible = pointsPossible;

        this.type = WRITTEN;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String[] getAnswerOptions() {
        return answerOptions;
    }

    public void setAnswerOptions(String[] answerOptions) {
        this.answerOptions = answerOptions;
    }

    public int getCorrectAnswerIndex() {
        return correctAnswerIndex;
    }

    public void setCorrectAnswerIndex(int correctAnswerIndex) {
        this.correctAnswerIndex = correctAnswerIndex;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    /**
     * Checks the answer entered by the user.
     * Increases the number of attempts made by 1.
     * If the answer has been answered correctly, calls calculatePointsEarned()
     *
     * @param chosenAnswerIndex The option selected by the user - only used if the question is multiple-choice.
     * @param answer            The answer entered by the user - only used if the question is written.
     * @return Whether the answer is correct.
     */
    public boolean checkAnswer(int chosenAnswerIndex, String answer) {

        if (this.type.equals(MULTIPLE_CHOICE)) {
            this.userAnswerIndexes.add(chosenAnswerIndex);
            if (correctAnswerIndex == chosenAnswerIndex) {
                this.calculatePointsEarned();
                this.attempts += 1;
                return true;
            }
        } else if (this.type.equals(WRITTEN)) {
            this.userWrittenAnswers.add(answer);
            if (answer.equals(this.correctWrittenAnswer)) {
                this.calculatePointsEarned();
                this.attempts += 1;
                return true;
            }
        }

        this.attempts += 1;
        return false;

    }


    public int getAttempts() {
        return attempts;
    }

    public void setAttempts(int attempts) {
        this.attempts = attempts;
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

    /**
     * Calculates the points earned for a question by taking into account the number of points
     * available and the number of attempts made.
     * Updates the points earned.
     */
    public void calculatePointsEarned() {
        // If the question was answered correctly, points can no longer be decreased:
        if (this.pointsEarned == 0) {
            if (attempts == 0) {
                this.pointsEarned = this.pointsPossible;
            } else if (this.attempts == 1) {
                this.pointsEarned = this.pointsPossible / 2;
            }
        }
    }

    public String getCorrectWrittenAnswer() {
        return correctWrittenAnswer;
    }

    public void setCorrectWrittenAnswer(String correctWrittenAnswer) {
        this.correctWrittenAnswer = correctWrittenAnswer;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public ArrayList<Integer> getUserAnswerIndexes() {
        return userAnswerIndexes;
    }

    public void setUserAnswerIndexes(ArrayList<Integer> userAnswerIndexes) {
        this.userAnswerIndexes = userAnswerIndexes;
    }

    public ArrayList<String> getUserWrittenAnswers() {
        return userWrittenAnswers;
    }

    public void setUserWrittenAnswers(ArrayList<String> userWrittenAnswers) {
        this.userWrittenAnswers = userWrittenAnswers;
    }
}