package com.example.xmasdraft;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import static com.example.xmasdraft.QuestionSetActivity.QUESTION_SET_ID;


import androidx.appcompat.app.AppCompatActivity;

public class ResultsActivity extends AppCompatActivity implements View.OnClickListener {


    private ImageView imgExit;

    private TextView txtQuestionSetName, txtResult, txtResultQuestions, txtResultPercentage;

    private QuestionSet questionSet;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);

        // Initialising View Objects:
        initViews();

        // Setting data from the intent:
        setDataFromIntent();

        //TODO: To reset all chosen answers, points etc after the page is dismissed

    }

    private void setDataFromIntent() {
        // Getting Question Set from the Intent:
        Intent intent = getIntent();

        // If the intent is not null:
        if (intent != null) {

            // Get the extra data from the Intent:
            // If the value is set to null, it will default to -1:
            int questionSetID = intent.getIntExtra(QUESTION_SET_ID, -1);

            if (questionSetID != -1) {
                // Finding our Question Set by ID:
                questionSet = Utils.getInstance().getQuestionSetByID(questionSetID);

                if (questionSet != null) {

                    // Setting the data from the Question Set to our View item:
                    setData(questionSet);
                }
            }

        }
    }

    private void initViews() {
        imgExit = findViewById(R.id.imgExit);
        txtQuestionSetName = findViewById(R.id.txtQuestionSetName);
        txtResult = findViewById(R.id.txtResult);
        txtResultQuestions = findViewById(R.id.txtResultQuestions);
        txtResultPercentage = findViewById(R.id.txtResultPercentage);

        imgExit.setOnClickListener(this);

    }

    private void setData(QuestionSet questionSet){

        txtQuestionSetName.setText(questionSet.getName());
        txtResult.setText(questionSet.calculatePointsEarned() + " Points out of " + questionSet.calculatePointsPossible());
        txtResultQuestions.setText("Solved " + questionSet.calculateNumberOfQuestionsSolved() + " out of " + questionSet.getQuestions().length);
        txtResultPercentage.setText("Result: " + questionSet.calculateResult() + "%");



    }

    @Override
    public void onClick(View view) {

        switch (view.getId()){

            case (R.id.imgExit):
                questionSet.reset();
                startActivity(new Intent(this, MainMenuActivity.class));
                break;


            default:
                break;


        }

    }
}
