package com.example.xmasdraft;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

public class QuestionSetActivity extends AppCompatActivity implements View.OnClickListener {

    // Using this to get extra data from the Intent:
    public static final String QUESTION_SET_ID = "questionSetID";

    private ScrollView svQuestionSet;
    private TextView txtQuestion, txtQuestionSetName, txtCurrentQuestionIndex, txtPointsPossible;
    private RadioGroup rgQuestionAnswerOptions;
    private RadioButton answerA, answerB, answerC, answerD;
    private Button btnConfirm;
    private ImageView imgExit, imgQuestion, imgPrevious, imgNext;

    private QuestionSet questionSet;
    private Question currentQuestion;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question_set);

        // Initialising View Objects:
        initViews();

        // Setting data from the intent:
        setDataFromIntent();

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
    /**
     * Initialising View objects:
     */
    private void initViews() {
        svQuestionSet = findViewById(R.id.svQuestionSet);

        txtQuestionSetName = findViewById(R.id.txtQuestionSetName);
        txtCurrentQuestionIndex = findViewById(R.id.txtCurrentQuestionIndex);
        txtPointsPossible = findViewById(R.id.txtPointsPossible);
        txtQuestion = findViewById(R.id.txtQuestion);

        imgExit = findViewById(R.id.imgExit);
        imgQuestion = findViewById(R.id.imgQuestion);
        imgPrevious = findViewById(R.id.imgPrevious);
        imgNext = findViewById(R.id.imgNext);

        rgQuestionAnswerOptions = findViewById(R.id.rgQuestionAnswerOptions);
        answerA = findViewById(R.id.answerA);
        answerB = findViewById(R.id.answerB);
        answerC = findViewById(R.id.answerC);
        answerD = findViewById(R.id.answerD);
        btnConfirm = findViewById(R.id.btnConfirm);

        // Setting On Click Listeners:
        imgExit.setOnClickListener(this);
        imgPrevious.setOnClickListener(this);
        imgNext.setOnClickListener(this);
        btnConfirm.setOnClickListener(this);
    }


    /**
     * Sets question data depending on which question is currently being done.
     * @param questionSet The question set from which the data should be set.
     */
    private void setData(QuestionSet questionSet) {

        // Removing the check from the previous question:
        rgQuestionAnswerOptions.clearCheck();

        txtQuestionSetName.setText(questionSet.getName());

        // We are getting the current question index to set the correct values:
        currentQuestion = questionSet.getQuestions()[questionSet.getCurrentQuestionIndex()];

        txtQuestion.setText(currentQuestion.getQuestionText());


        txtCurrentQuestionIndex.setText("Question " + (questionSet.getCurrentQuestionIndex() + 1) + " of " + questionSet.getQuestions().length);


        txtPointsPossible.setText(currentQuestion.getPointsPossible() + " Points");

        // Set question set image (id = 0 if no image):
        imgQuestion.setImageResource(currentQuestion.getImageID());


        answerA.setText(currentQuestion.getAnswers()[0]);
        answerB.setText(currentQuestion.getAnswers()[1]);
        answerC.setText(currentQuestion.getAnswers()[2]);
        answerD.setText(currentQuestion.getAnswers()[3]);


        // Sets the visibility of navigation images and the text of the confirm
        // button depending on the current question:
        if (firstQuestion()){
            btnConfirm.setText("CONFIRM");
            imgNext.setVisibility(View.VISIBLE);
            imgPrevious.setVisibility(View.GONE);
        }
        else if (lastQuestion()){
            btnConfirm.setText("FINISH");
            imgNext.setVisibility(View.GONE);
            imgPrevious.setVisibility(View.VISIBLE);
        }
        else{
            btnConfirm.setText("CONFIRM");
            imgPrevious.setVisibility(View.VISIBLE);
            imgNext.setVisibility(View.VISIBLE);
        }

    }

    private boolean firstQuestion() {
        // If the current question index is 1 less than length:
        return questionSet.getCurrentQuestionIndex() == 0;
    }

    private boolean lastQuestion() {
        // If the current question index is 1 less than length:
        return questionSet.getCurrentQuestionIndex() + 1 == questionSet.length();
    }


    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case (R.id.btnConfirm):
                confirmClicked();
                break;

            case (R.id.imgExit):
                startActivity(new Intent(this, MainMenuActivity.class));
                break;

            case (R.id.imgPrevious):
                // Decreasing the current question index by 1
                questionSet.setCurrentQuestionIndex(questionSet.getCurrentQuestionIndex() - 1);
                setData(questionSet);
                break;

            case (R.id.imgNext):

                // If the current question has been solved, they can go to the next question:
                if (questionSet.getQuestions()[questionSet.getCurrentQuestionIndex()].isAttempted()){

                    // Increasing the current question index by 1
                    questionSet.setCurrentQuestionIndex(questionSet.getCurrentQuestionIndex() + 1);
                    setData(questionSet);

                }
                else{
                    Toast.makeText(this, "Try This Question First", Toast.LENGTH_SHORT).show();
                }

            default:
                break;


        }

    }

    private void confirmClicked() {

        // Scrolling to the top
        svQuestionSet.smoothScrollTo(0,0);


        // If the index is not -1, an answer has been selected:
        if (getCheckedRadioButtonIndex() != -1) {

            //TODO: Proceduralise

            // TODO: For now, if the answer is correct, we are moving immediately to the next question. To be changed!
            if (currentQuestion.checkAnswer(getCheckedRadioButtonIndex())) {

                if (lastQuestion()){
                    // TODO: Should Go To Results Page!!
                    // TODO: Should be finished() function!!

                    // If they start the question set again, it will start from 0:
                    questionSet.setCurrentQuestionIndex(0);

                    // Starting the Results Activity:
                    startActivity(new Intent(this, ResultsActivity.class)
                            .putExtra(QUESTION_SET_ID, questionSet.getQuestionSetID()));

                } else {

                    // Increasing the current question index by 1
                    questionSet.setCurrentQuestionIndex(questionSet.getCurrentQuestionIndex() + 1);
                    setData(questionSet);

                }
            } else {
                Toast.makeText(this, "Try Again, You Can Do It!", Toast.LENGTH_SHORT).show();

            }
        } else {

            // Telling the user to select an answer.
            // Alternatively, this could be done using a Snack Bar.
            Toast.makeText(QuestionSetActivity.this, "Please Select An Answer", Toast.LENGTH_SHORT).show();
        }
    }


    private int getCheckedRadioButtonIndex() {

        // Get the index of which Radio Button has been clicked:
        switch (rgQuestionAnswerOptions.getCheckedRadioButtonId()) {

            case (R.id.answerA):
                return 0;


            case (R.id.answerB):
                return 1;

            case (R.id.answerC):
                return 2;

            case (R.id.answerD):
                return 3;

            default:
                // If none have been clicked, return 0:
                return -1;

        }
    }
}