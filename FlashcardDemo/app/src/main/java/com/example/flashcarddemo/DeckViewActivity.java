package com.example.flashcarddemo;

import android.content.Intent;
import android.os.Bundle;
import android.transition.TransitionManager;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class DeckViewActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView imgPrevious, imgNext;
    private RelativeLayout relDeckView;
    private TextView txtFront, txtBack, txtDeckName, txtIndex;


    public static final String DECK_ID = "DECK_ID";

    private DatabaseHelper databaseHelper = new DatabaseHelper(this);

    private int currentIndex = 0;

    private Deck deck;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deck_view);

        initViews();

        setDataFromIntent();

    }

    private void initViews() {
        imgPrevious = findViewById(R.id.imgPrevious);
        imgNext = findViewById(R.id.imgNext);
        relDeckView = findViewById(R.id.relDeckView);
        txtFront = findViewById(R.id.txtFront);
        txtBack = findViewById(R.id.txtBack);
        txtDeckName = findViewById(R.id.txtDeckName);
        txtIndex = findViewById(R.id.txtIndex);

        imgPrevious.setOnClickListener(this);
        imgNext.setOnClickListener(this);
    }

    private void setDataFromIntent() {
        Intent intent = getIntent();
        if (intent != null) {
            int deckId = intent.getIntExtra(DECK_ID, -1);
            if (deckId == -1) {Toast.makeText(this, "Deck Not Found", Toast.LENGTH_SHORT).show(); }
            Deck deck = databaseHelper.getDeck(deckId);
            if (deck != null) {
                this.deck = deck;
                setData();
            }
            }
        }



    private void setData() {
        Flashcard currentFlashCard = deck.getFlashcards().get(currentIndex);

        //TODO: Not Working:
        TransitionManager.beginDelayedTransition(relDeckView);
        txtFront.setText(currentFlashCard.getFront());
        txtBack.setText(currentFlashCard.getBack());

        txtDeckName.setText(deck.getName());
        txtIndex.setText(String.format(getString(R.string.flashcard_index), (currentIndex + 1), deck.getFlashcards().size()));

    }




    @Override
    public void onClick (View view){

        switch (view.getId()){

            case (R.id.imgPrevious):
                if (currentIndex > 0){
                    currentIndex -= 1;
                    setData();
                }
                break;

            case (R.id.imgNext):
                if (currentIndex < (deck.getFlashcards().size() - 1)){
                    currentIndex += 1;
                    setData();
                }

                break;

            default:
                break;
        }

    }
}