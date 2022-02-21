package com.example.xmasdraft;


import java.util.ArrayList;

public class QuestionSet {

    /* Each Question Set will have a name, description, image id (so that it can have an image in the main
    * menu), an array of Questions, the index of the current question, and whether it is expanded
    * (in the main menu).
    */

    private int questionSetID;
    private String name, description;
    private int imageId;
    private Question[] questions;
    private int currentQuestionIndex;
    private boolean isExpanded;

    private ArrayList<String> topics = new ArrayList<>();
    private ArrayList<String> models = new ArrayList<>();

    private String dateCompleted;



    public QuestionSet(int questionSetID, String name, String description, int imageID, Question[] questions) {
        this.questionSetID = questionSetID;
        this.name = name;
        this.description = description;
        this.imageId = imageID;
        this.questions = questions;
        this.currentQuestionIndex = 0;
        arrangeTopics_Models();

        this.isExpanded = false;
    }

    private void arrangeTopics_Models() {
        for (Question question : this.questions) {
            String model = question.getModel();
            String topic = question.getTopic();

            if (!this.topics.contains(topic)){
                this.topics.add(topic);
            }
            if (!this.models.contains(model)){
                this.models.add(model);
            }

        }
    }

    // Name
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    // Image ID
    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }


    // Questions
    public Question[] getQuestions() {
        return questions;
    }

    public void setQuestions(Question[]  questions) {
        this.questions = questions;
    }


    // Is Expanded
    public boolean isExpanded() {
        return isExpanded;
    }

    public void setExpanded(boolean expanded) {
        isExpanded = expanded;
    }


    // Description
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    // Question Set ID
    public int getQuestionSetID() {
        return questionSetID;
    }

    public void setQuestionSetID(int questionSetID) {
        this.questionSetID = questionSetID;
    }


    // Current Question Index
    public int getCurrentQuestionIndex() {
        return currentQuestionIndex;
    }

    public void setCurrentQuestionIndex(int currentQuestionIndex) {
        // To prevent any errors, validate first:
        if (currentQuestionIndex >= 0 && currentQuestionIndex < getQuestions().length) {
            this.currentQuestionIndex = currentQuestionIndex;
        }
    }

    // Length
    public int length(){
        return this.questions.length;
    }

    public int calculateResult(){
        return (int) (((float) calculatePointsEarned() / (float) calculatePointsPossible()) * 100);
    }

    public int calculatePointsPossible() {
        float totalPossible = 0;
        for (Question question : this.questions) {
            totalPossible += question.getPointsPossible();
        }
        return (int) totalPossible;
    }


    public int calculatePointsEarned(){

        float totalEarned = 0;
        for (Question question: this.questions) {
            totalEarned += question.getPointsEarned();
        }
        return (int) totalEarned;
    }

    public int[] calculateNumberOfQuestionsSolved(){
        int firstAttempt = 0;
        int secondAttempt = 0;
        for (Question question: this.questions) {

            // If they have earned any points from a question, consider it solved:
            if(question.getPointsEarned() == question.getPointsPossible()) {
                firstAttempt += 1;
            }
            else if (question.getPointsEarned() == question.getPointsPossible() / 2){
                secondAttempt += 1;
            }
        }
        return new int[] {firstAttempt, secondAttempt};
    }

    public void reset(){

        for (Question question: this.questions) {

            // If the student has completed the question set, reset the values
            // so that it can be done again.
            question.setPointsEarned(0);
            question.setAttempted(0);

        }
    }


    public ArrayList<String> getTopics() {
        return topics;
    }

    public void setTopics(ArrayList<String> topics) {
        this.topics = topics;
    }

    public ArrayList<String> getModels() {
        return models;
    }

    public void setModels(ArrayList<String> models) {
        this.models = models;
    }
}
