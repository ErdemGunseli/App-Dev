package com.example.xmasdraft;

public class Question {

    // Each question will have some text, an array of answer options, a correct answer and possibly an image.
    private String questionText;
    private int imageID;


    private String type;

    // For Multiple Choice
    private String[] answers;
    private int correctAnswerIndex;
    private int chosenAnswerIndex;


    // For Written
    private String answer;
    private String writtenAnswer;


    private String topic;
    // The type/model of the question
    private String model;

    private int attempted = 0;

    private int pointsPossible;
    private int pointsEarned = 0;





    public Question(String topic, String model, String questionText, int imageID, String[] answers, int correctAnswerIndex, int pointsPossible) {
        this.model = model;
        this.topic = topic;
        this.questionText = questionText;
        this.imageID = imageID;
        this.answers = answers;
        this.correctAnswerIndex = correctAnswerIndex;
        this.pointsPossible = pointsPossible;

        this.type = "multipleChoice";
    }

    public Question(String topic, String model, String questionText, int imageID, String answer, int pointsPossible){
        this.model = model;
        this.topic = topic;
        this.questionText = questionText;
        this.imageID = imageID;
        this.answer = answer;
        this.pointsPossible = pointsPossible;

        this.type = "written";
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
    public boolean checkAnswer(int chosenAnswerIndex, String answer) {

        if (this.type.equals("multipleChoice")) {

            // Their initial answer
            if (this.attempted == 0) {
                this.chosenAnswerIndex = chosenAnswerIndex;
            }

            if (correctAnswerIndex == chosenAnswerIndex) {
                this.calculatePointsEarned();
                this.attempted += 1;
                return true;
            }
        }

        else if (this.type.equals("written")){

            if (this.attempted == 0) {
                this.writtenAnswer = answer;
            }

            if (answer.equals(this.answer)){
                this.calculatePointsEarned();
                this.attempted += 1;
                return true;
            }




        }
        this.attempted += 1;
        return false;

    }


    public int getAttempted() {
        return attempted;
    }

    public void setAttempted(int attempted) {
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

    public void calculatePointsEarned(){

        // If they got it right first, do not penalise:
        if (this.pointsEarned == 0) {
            if (attempted == 0) {
                this.pointsEarned = this.pointsPossible;
            } else if (this.attempted == 1) {
                this.pointsEarned = this.pointsPossible / 2;
            }
        }
    }

    public int getChosenAnswerIndex() {return chosenAnswerIndex;}

    public void setChosenAnswerIndex(int chosenAnswerIndex) {this.chosenAnswerIndex = chosenAnswerIndex;}


    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
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



