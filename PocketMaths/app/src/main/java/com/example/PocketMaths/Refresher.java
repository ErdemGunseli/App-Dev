package com.example.PocketMaths;

public class Refresher {

    private int id;
    private String topic;
    private int imageId;
    private int questionIndex;
    private boolean shown;

    public Refresher(int id, String topic, int imageId, int questionIndex) {
        this.id = id;
        this.topic = topic;
        this.imageId = imageId;
        this.questionIndex = questionIndex;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    public int getQuestionIndex() {
        return questionIndex;
    }

    public void setQuestionIndex(int questionIndex) {
        this.questionIndex = questionIndex;
    }

    public boolean isShown() {
        return shown;
    }

    public void setShown(boolean shown) {
        this.shown = shown;
    }
}
