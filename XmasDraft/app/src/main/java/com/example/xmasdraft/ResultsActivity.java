package com.example.xmasdraft;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import static com.example.xmasdraft.QuestionSetActivity.QUESTION_SET_ID;


import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class ResultsActivity extends AppCompatActivity implements View.OnClickListener {


    private ImageView imgExit;

    private TextView txtQuestionSetName, txtResult, txtResultQuestions, txtResultPercentage;

    private QuestionSet questionSet;

    private RecyclerView rvQuestions;

    private Button btnFinish;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);

        // Initialising View Objects:
        initViews();

        // Setting data from the intent:
        setDataFromIntent();

        ResultsRecyclerAdapter resultsRecyclerAdapter = new ResultsRecyclerAdapter(this);
        resultsRecyclerAdapter.setQuestionSet(questionSet);

        // Setting adapter to recycler view:
        rvQuestions.setAdapter(resultsRecyclerAdapter);

        rvQuestions.setLayoutManager(new LinearLayoutManager(this));

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
        rvQuestions = findViewById(R.id.rvQuestions);
        imgExit = findViewById(R.id.imgExit);
        txtQuestionSetName = findViewById(R.id.txtQuestionSetName);
        txtResult = findViewById(R.id.txtResult);
        txtResultQuestions = findViewById(R.id.txtResultQuestions);
        txtResultPercentage = findViewById(R.id.txtResultPercentage);
        btnFinish = findViewById(R.id.btnFinish);

        imgExit.setOnClickListener(this);
        btnFinish.setOnClickListener(this);

    }

    private void setData(QuestionSet questionSet){

        txtQuestionSetName.setText(questionSet.getName());
        txtResult.setText(questionSet.calculatePointsEarned() + " Points out of " + questionSet.calculatePointsPossible());
        txtResultQuestions.setText("Solved " + questionSet.calculateNumberOfQuestionsSolved() + " out of " + questionSet.getQuestions().length);
        txtResultPercentage.setText("Result: " + questionSet.calculateResult() + "%");



    }

    @Override
    public void onClick(View view) {
        int v = view.getId();

        if (v == R.id.imgExit || v == R.id.btnFinish){
            //TODO: Save data
            questionSet.reset();
            startActivity(new Intent(this, MainMenuActivity.class));
        }


    }


}
