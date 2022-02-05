package com.example.xmasdraft;


import android.content.Intent;
import android.graphics.Color;
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

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

public class ResultsActivity extends AppCompatActivity implements View.OnClickListener {


    private ImageView imgExit;

    private TextView txtQuestionSetName, txtResult, txtFirstAttempt, txtSecondAttempt, txtResultPercentage;

    private QuestionSet questionSet;

    private RecyclerView rvQuestions;

    private Button btnFinish;

    private PieChart pieResults;

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

        txtFirstAttempt = findViewById(R.id.txtFirstAttempt);
        txtSecondAttempt = findViewById(R.id.txtSecondAttempt);

        txtResultPercentage = findViewById(R.id.txtResultPercentage);
        btnFinish = findViewById(R.id.btnFinish);

        pieResults = findViewById(R.id.pieResults);

        imgExit.setOnClickListener(this);
        btnFinish.setOnClickListener(this);

    }

    private void setData(QuestionSet questionSet){

        txtQuestionSetName.setText(questionSet.getName());
        txtResult.setText(questionSet.calculatePointsEarned() + " Points out of " + questionSet.calculatePointsPossible());

        txtFirstAttempt.setText("Solved " + questionSet.calculateNumberOfQuestionsSolved()[0] + " out of " + questionSet.getQuestions().length + " with one attempt.");

        int remaining = (questionSet.getQuestions().length - questionSet.calculateNumberOfQuestionsSolved()[0]);

        // Only present if score isn't 100%
        if (remaining > 0) {
            txtSecondAttempt.setVisibility(View.VISIBLE);
            txtSecondAttempt.setText("Solved " + questionSet.calculateNumberOfQuestionsSolved()[1] + " out of the remaining " +
                    (remaining) + " with two attempts.");
        }
        else{
            txtSecondAttempt.setVisibility(View.GONE);
        }

        txtResultPercentage.setText("Result: " + questionSet.calculateResult() + "%");

        setUpPieChart();
        loadPieChart();



    }

    private void loadPieChart() {
        ArrayList<PieEntry> entries = new ArrayList<>();

        int firstAttempt = questionSet.calculateNumberOfQuestionsSolved()[0];
        int secondAttempt = questionSet.calculateNumberOfQuestionsSolved()[1];
        int moreAttempts = questionSet.getQuestions().length - (questionSet.calculateNumberOfQuestionsSolved()[0] + questionSet.calculateNumberOfQuestionsSolved()[1]);


        // If greater than 0, show in chart:
        if (firstAttempt > 0) {
            entries.add(new PieEntry(firstAttempt, "1 Attempt"));
        }
        if (secondAttempt > 0){
            entries.add(new PieEntry(secondAttempt, "2 Attempts"));
        }
        if (moreAttempts > 0) {
            entries.add(new PieEntry(moreAttempts, "Over 2 Attempts"));
        }


        ArrayList<Integer> colors = new ArrayList<>();
        for (int color: ColorTemplate.MATERIAL_COLORS){
            colors.add(color);
        }




        PieDataSet dataSet = new PieDataSet(entries, "");
        dataSet.setColors(colors);

        PieData data = new PieData(dataSet);
        data.setDrawValues(true);
        data.setValueFormatter(new PercentFormatter(pieResults));
        data.setValueTextSize(11f);
        data.setValueTextColor(getResources().getColor(R.color.Primary));

        pieResults.setData(data);
        pieResults.invalidate();
    }

    private void setUpPieChart(){

        // 'donut' shape
        pieResults.setDrawHoleEnabled(true);
        pieResults.setHoleColor(getResources().getColor(R.color.Surface1));
        pieResults.setUsePercentValues(true);
        pieResults.setEntryLabelTextSize(13);
        pieResults.setEntryLabelColor(getResources().getColor(R.color.Secondary));
        pieResults.setCenterText("RESULTS");
        pieResults.setCenterTextColor(getResources().getColor(R.color.Primary));
        pieResults.setCenterTextSize(14);
        pieResults.getDescription().setText("");


    }

    @Override
    public void onClick(View view) {
        int v = view.getId();

        if (v == R.id.imgExit || v == R.id.btnFinish){
            //TODO: Save data
            questionSet.setCompleted(true);
            questionSet.reset();
            startActivity(new Intent(this, MainMenuActivity.class));
        }


    }


}
