package com.example.PocketMaths;


import java.util.ArrayList;

public class QuestionSet {

    /* Each Question Set will have a name, description, image id (so that it can have an image in the main
    * menu), an array of Questions, the index of the current question, and whether it is expanded
    * (in the main menu).
    */

    private int questionSetId;
    private String name, description;
    private int imageId;
    private Question[] questions;
    private int currentQuestionIndex;
    private boolean isExpanded;

    private ArrayList<String> topics = new ArrayList<>();

    private String dateCompleted;

    private Refresher[] refreshers;



    public QuestionSet(int questionSetId, String name, String description, int imageID, Question[] questions, Refresher[] refreshers) {
        this.questionSetId = questionSetId;
        this.name = name;
        this.description = description;
        this.imageId = imageID;
        this.questions = questions;
        this.refreshers = refreshers;
        this.currentQuestionIndex = 0;
        arrangeTopics();

        this.isExpanded = false;
    }

    private void arrangeTopics() {
        for (Question question : this.questions) {
            String topic = question.getTopic();

            if (!this.topics.contains(topic)){
                this.topics.add(topic);
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
    public int getId() {
        return questionSetId;
    }

    public void setQuestionSetId(int questionSetId) {
        this.questionSetId = questionSetId;
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
        int moreAttempts = this.questions.length - firstAttempt - secondAttempt;

        return new int[] {firstAttempt, secondAttempt, moreAttempts};
    }

    public void reset(){

        for (Question question: this.questions) {

            // If the student has completed the question set, reset the values
            // so that it can be done again.
            question.setPointsEarned(0);
            question.setAttempts(0);

        }
    }

    public ArrayList<String> getFailedTopics() {
        ArrayList<String> failedTopics = new ArrayList<>();

        for (Question question : this.questions) {
            String topic = question.getTopic();

            // If answered incorrectly, add the topic/model to arraylist.
            if (question.getPointsPossible() != question.getPointsEarned()){
                if (!failedTopics.contains(topic)){
                    failedTopics.add(topic);
                }
            }
        }
        return failedTopics;
    }

    public ArrayList<String> getTopics() {
        return topics;
    }

    public void setTopics(ArrayList<String> topics) {
        this.topics = topics;
    }

    public Refresher[] getRefreshers() {
        return refreshers;
    }

    public void setRefreshers(Refresher[] refreshers) {
        this.refreshers = refreshers;
    }
}
