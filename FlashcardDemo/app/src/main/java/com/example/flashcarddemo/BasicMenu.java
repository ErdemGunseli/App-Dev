package com.example.flashcarddemo;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class BasicMenu extends AppCompatActivity implements View.OnClickListener {


    ImageView imgAdd;
    RecyclerView rvDecks;

    //TODO: Get Database Helper instance from Utils?
    DatabaseHelper databaseHelper = new DatabaseHelper(this);



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basic_menu);

        initViews();


        //testing();

        setUpRecyclerAdapter();
    }

    private void testing() {

        ArrayList<Flashcard> flashcards = new ArrayList<>();
        flashcards.add(new Flashcard(0, 0, 0, "F1", "B1"));
        flashcards.add(new Flashcard(0, 0, 1, "F2", "B2"));
        flashcards.add(new Flashcard(0, 0, 2, "F3", "B3"));
        flashcards.add(new Flashcard(0, 0, 3, "F4", "B4"));
        flashcards.add(new Flashcard(0, 0, 4, "F5", "B5"));

        databaseHelper.addDeck(new Deck(0, "Deck 1", 0, flashcards));
        databaseHelper.addDeck(new Deck(0, "Deck 2", 0, flashcards));
        databaseHelper.addDeck(new Deck(0, "Deck 3", 0, flashcards));
        databaseHelper.addDeck(new Deck(0, "Deck 4", 0, flashcards));
        databaseHelper.addDeck(new Deck(0, "Deck 5", 0, flashcards));

        int count = databaseHelper.getAllDecks().size();
        System.out.println("...Number of Decks:" + count);

    }



    private void initViews() {
        this.imgAdd = findViewById(R.id.imgAdd);
        this.rvDecks = findViewById(R.id.rvDecks);

        this.imgAdd.setOnClickListener(this);
    }


    private void setUpRecyclerAdapter() {
        DeckRecyclerAdapter deckRecyclerAdapter = new DeckRecyclerAdapter(this, databaseHelper.getAllDecks());
        rvDecks.setAdapter(deckRecyclerAdapter);
        rvDecks.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()){

            case (R.id.imgAdd):
                Toast.makeText(this, "Create FlashCard", Toast.LENGTH_SHORT).show();

            default:
                break;
        }

    }
}
