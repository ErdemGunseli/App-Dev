package com.example.flashcarddemo;

import java.util.ArrayList;

public class Deck {


    private int Id, topicId;

    private String name;

    private ArrayList<Flashcard> flashcards;

    public Deck(int Id, String name, int topicId, ArrayList<Flashcard> flashcards) {
        this.Id = Id;
        this.name = name;
        this.topicId = topicId;
        this.flashcards = flashcards;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getTopicId() {
        return topicId;
    }

    public void setTopicId(int topicId) {
        this.topicId = topicId;
    }

    public ArrayList<Flashcard> getFlashcards() {
        return flashcards;
    }

    public void setFlashcards(ArrayList<Flashcard> flashcards) {
        this.flashcards = flashcards;
    }
}
