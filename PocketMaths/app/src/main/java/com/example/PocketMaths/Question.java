package com.example.PocketMaths;

public class Question {

    // Each question will have some text, an array of answer options, a correct answer and possibly an image.
    private int id;
    private String text;
    private int imageId;

    private String type;

    public static final String WRITTEN = "written";
    public static final String MULTIPLE_CHOICE = "multipleChoice";



    // For Multiple Choice
    private String[] answerOptions;
    private int correctAnswerIndex;
    private int chosenAnswerIndex;

    // For Written
    private String correctWrittenAnswer;
    private String writtenAnswer;

    private String topic;
    // The type/model of the question
    private String model;

    private int attempts = 0;

    private int pointsPossible;
    private int pointsEarned = 0;

    //TODO: Get id from questions table:

    public Question(String topic, String model, String text, int imageId, String[] answerOptions, int correctAnswerIndex, int pointsPossible) {
        this.model = model;
        this.topic = topic;
        this.text = text;
        this.imageId = imageId;
        this.answerOptions = answerOptions;
        this.correctAnswerIndex = correctAnswerIndex;
        this.pointsPossible = pointsPossible;

        this.type = MULTIPLE_CHOICE;
    }

    public Question(String topic, String model, String text, int imageId, String answer, int pointsPossible){
        this.model = model;
        this.topic = topic;
        this.text = text;
        this.imageId = imageId;
        this.correctWrittenAnswer = answer;
        this.pointsPossible = pointsPossible;

        this.type = WRITTEN;
    }

    // Question Text
    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }


    // Possible Answers
    public String[] getAnswerOptions() {
        return answerOptions;
    }

    public void setAnswerOptions(String[] answerOptions) {
        this.answerOptions = answerOptions;
    }




    // Correct Answer Index
    public int getCorrectAnswerIndex() {
        return correctAnswerIndex;
    }

    public void setCorrectAnswerIndex(int correctAnswerIndex) {
        this.correctAnswerIndex = correctAnswerIndex;
    }

    // Image ID (optional)
    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }


    // Marking the Question
    public boolean checkAnswer(int chosenAnswerIndex, String answer) {

        if (this.type.equals("multipleChoice")) {

            // Their initial answer
            if (this.attempts == 0) {
                this.chosenAnswerIndex = chosenAnswerIndex;
            }

            if (correctAnswerIndex == chosenAnswerIndex) {
                this.calculatePointsEarned();
                this.attempts += 1;
                return true;
            }
        }

        else if (this.type.equals("written")){

            if (this.attempts == 0) {
                this.writtenAnswer = answer;
            }

            if (answer.equals(this.correctWrittenAnswer)){
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

    public void calculatePointsEarned(){

        // If they got it right first, do not penalise:
        if (this.pointsEarned == 0) {
            if (attempts == 0) {
                this.pointsEarned = this.pointsPossible;
            } else if (this.attempts == 1) {
                this.pointsEarned = this.pointsPossible / 2;
            }
        }
    }

    public int getChosenAnswerIndex() {return chosenAnswerIndex;}

    public void setChosenAnswerIndex(int chosenAnswerIndex) {this.chosenAnswerIndex = chosenAnswerIndex;}


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

    public String getWrittenAnswer() {
        return writtenAnswer;
    }

    public void setWrittenAnswer(String writtenAnswer) {
        this.writtenAnswer = writtenAnswer;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }
}



