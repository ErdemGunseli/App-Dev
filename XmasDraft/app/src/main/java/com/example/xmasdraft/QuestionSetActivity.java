package com.example.xmasdraft;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class QuestionSetActivity extends AppCompatActivity implements View.OnClickListener {

    // Using this to get extra data from the Intent:
    public static final String QUESTION_SET_ID = "questionSetID";

    private TextView txtQuestion, txtQuestionSetName, txtCurrentQuestionIndex;
    private RadioGroup rgQuestionAnswerOptions;
    private RadioButton answerA, answerB, answerC, answerD;
    private Button btnConfirm;
    private ImageView imgQuestion;


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

    private void initViews() {
        txtQuestion = findViewById(R.id.txtQuestion);
        txtQuestionSetName = findViewById(R.id.txtQuestionSetName);
        txtCurrentQuestionIndex = findViewById(R.id.txtCurrentQuestionIndex);

        imgQuestion = findViewById(R.id.imgQuestion);

        rgQuestionAnswerOptions = findViewById(R.id.rgQuestionAnswerOptions);
        answerA = findViewById(R.id.answerA);
        answerB = findViewById(R.id.answerB);
        answerC = findViewById(R.id.answerC);
        answerD = findViewById(R.id.answerD);
        btnConfirm = findViewById(R.id.btnConfirm);

        // Setting On Click Listeners:
        btnConfirm.setOnClickListener(QuestionSetActivity.this);
    }

    private void setData(QuestionSet questionSet) {

        // Removing the check from the previous question:
        rgQuestionAnswerOptions.clearCheck();

        txtQuestionSetName.setText(questionSet.getName());

        // We are getting the current question index to set the correct values:
        currentQuestion = questionSet.getQuestions()[questionSet.getCurrentQuestionIndex()];

        txtQuestion.setText(currentQuestion.getQuestionText());


        txtCurrentQuestionIndex.setText("Question " + (questionSet.getCurrentQuestionIndex() + 1) + " of " + questionSet.getQuestions().length);

        // If the question has an image, set the image:
        if (currentQuestion.getImageID() != -1){
            imgQuestion.setImageResource(currentQuestion.getImageID());
        }

        answerA.setText(currentQuestion.getAnswers()[0]);
        answerB.setText(currentQuestion.getAnswers()[1]);
        answerC.setText(currentQuestion.getAnswers()[2]);
        answerD.setText(currentQuestion.getAnswers()[3]);

        if (lastQuestion()){
            btnConfirm.setText("FINISH");
        }

    }

    private boolean lastQuestion() {
        // If the current question index is 1 less than length:
        if (questionSet.getCurrentQuestionIndex() + 1 == questionSet.length()){
            return true;
        }
        return false;
    }


    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case (R.id.btnConfirm):

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

                            // PlaceHolder:
                            startActivity(new Intent(this, MainMenuActivity.class));

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
                break;

            default:
                break;


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