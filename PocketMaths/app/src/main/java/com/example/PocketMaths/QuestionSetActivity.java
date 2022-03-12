package com.example.PocketMaths;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.ScrollView;
import android.widget.TextView;

public class QuestionSetActivity extends AppCompatActivity implements View.OnClickListener {

    // Using this to get extra data from the Intent:
    public static final String QUESTION_SET_ID = "questionSetID";

    private ScrollView svQuestionSet;
    private TextView txtQuestion, txtQuestionSetName, txtCurrentQuestionIndex, txtPointsPossible, txtMessage;
    private RadioGroup rgQuestionAnswerOptions;
    private RadioButton answerA, answerB, answerC, answerD;
    private Button btnConfirm, btnRevealAnswer;
    private ImageView imgExit, imgQuestion, imgPrevious, imgNext;
    private EditText edtTxtAnswer;

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
        txtMessage = findViewById(R.id.txtMessage);

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
        btnRevealAnswer = findViewById(R.id.btnRevealAnswer);
        edtTxtAnswer = findViewById(R.id.edtTxtAnswer);

        // Setting On Click Listeners:
        imgExit.setOnClickListener(this);
        imgPrevious.setOnClickListener(this);
        imgNext.setOnClickListener(this);
        btnConfirm.setOnClickListener(this);
        btnRevealAnswer.setOnClickListener(this);
    }


    /**
     * Sets question data depending on which question is currently being done.
     * @param questionSet The question set from which the data should be set.
     */
    private void setData(QuestionSet questionSet) {
        // We are getting the current question index to set the correct values:
        currentQuestion = questionSet.getQuestions()[questionSet.getCurrentQuestionIndex()];

        if (currentQuestion.getType().equals("multipleChoice")) {
            rgQuestionAnswerOptions.setVisibility(View.VISIBLE);
            edtTxtAnswer.setVisibility(View.GONE);
            btnRevealAnswer.setVisibility(View.GONE);
            // Removing the check from the previous question:
            rgQuestionAnswerOptions.clearCheck();

            answerA.setText(currentQuestion.getAnswerOptions()[0]);
            answerB.setText(currentQuestion.getAnswerOptions()[1]);
            answerC.setText(currentQuestion.getAnswerOptions()[2]);
            answerD.setText(currentQuestion.getAnswerOptions()[3]);

        }

        else if (currentQuestion.getType().equals("written")){

            rgQuestionAnswerOptions.setVisibility(View.GONE);
            edtTxtAnswer.setVisibility(View.VISIBLE);
            btnRevealAnswer.setVisibility(View.VISIBLE);
        }




        // Removing the 'Try Again' message:
        txtMessage.setText("");

        txtQuestionSetName.setText(questionSet.getName());



        txtQuestion.setText(currentQuestion.getText());

        // Question X of X
        txtCurrentQuestionIndex.setText(String.format(getString(R.string.question_x_of_x),(questionSet.getCurrentQuestionIndex() + 1), questionSet.getQuestions().length));

        // Set points accordingly:
        // Needed for if the screen is rotated after a question is done incorrectly.
        if (currentQuestion.getAttempts() > 1) {
            //X Points
            txtPointsPossible.setText(String.format(getString(R.string.points), currentQuestion.getPointsEarned()));
        }
        else if (currentQuestion.getAttempts() == 1){
            // X Points
            txtPointsPossible.setText(String.format(getString(R.string.points), (currentQuestion.getPointsPossible() / 2)));
        }
        else{
            //X Points
            txtPointsPossible.setText(String.format(getString(R.string.points), (currentQuestion.getPointsPossible())));
            //txtPointsPossible.setText(currentQuestion.getPointsPossible() + " Points");
        }

        // Set question set image (id = 0 if no image):
        imgQuestion.setImageResource(currentQuestion.getImageId());





        // Sets the visibility of navigation images and the text of the confirm
        // button depending on the current question:
        if (firstQuestion()){
            btnConfirm.setText(getString(R.string.confirm));
            imgNext.setVisibility(View.VISIBLE);
            imgPrevious.setVisibility(View.GONE);
        }
        else if (lastQuestion()){
            btnConfirm.setText(getString(R.string.finish));
            imgNext.setVisibility(View.GONE);
            imgPrevious.setVisibility(View.VISIBLE);
        }
        else{
            btnConfirm.setText(getString(R.string.confirm));
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
        return questionSet.getCurrentQuestionIndex() == questionSet.length() - 1;
    }


    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case (R.id.imgExit):
                // Not using finish() so that the main menu can be refreshed, displaying where we
                // left off etc.
                startActivity(new Intent(this, MainMenuActivity.class));
                break;

            case (R.id.imgPrevious):
                // Decreasing the current question index by 1
                questionSet.setCurrentQuestionIndex(questionSet.getCurrentQuestionIndex() - 1);
                setData(questionSet);
                break;

            case (R.id.imgNext):

                // If the current question has been solved, they can go to the next question:
                if (currentQuestion.getAttempts() > 0){

                    // Increasing the current question index by 1
                    questionSet.setCurrentQuestionIndex(questionSet.getCurrentQuestionIndex() + 1);
                    setData(questionSet);

                }
                else{
                    txtMessage.setText(getString(R.string.try_first));
                }
                break;

            case (R.id.btnConfirm):
                confirmClicked();
                break;

            case (R.id.btnRevealAnswer)    :
                currentQuestion.setAttempts(2);
                currentQuestion.setPointsEarned(0);
                txtMessage.setText(currentQuestion.getCorrectWrittenAnswer());
                // No points if they reveal the answer.
                txtPointsPossible.setText(String.format(getString(R.string.points), 0));
                break;

            default:
                break;


        }

    }

    private void confirmClicked() {

        // Scrolling to the top
        svQuestionSet.smoothScrollTo(0,0);

        // If an answer has been selected / written
        if ((currentQuestion.getType().equals("multipleChoice") && getCheckedRadioButtonIndex() != -1)
        || (currentQuestion.getType().equals("written") && !edtTxtAnswer.getText().toString().equals("")))
        {

            if (currentQuestion.checkAnswer(getCheckedRadioButtonIndex(), edtTxtAnswer.getText().toString())) {

                if (lastQuestion()){

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
                // If they have answered incorrectly:
                txtMessage.setText(getString(R.string.try_again));
                // Do not penalise if they have answered correctly before
                if (currentQuestion.getPointsEarned() == 0) {
                    if (currentQuestion.getAttempts() == 1) {

                        // X Points
                        txtPointsPossible.setText(String.format(getString(R.string.points), currentQuestion.getPointsPossible() / 2));
                    } else {
                        txtPointsPossible.setText(String.format(getString(R.string.points), 0));
                    }
                }

            }
        } else {

            // Telling the user to enter an answer.
           txtMessage.setText(getString(R.string.enter_answer));
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

    @Override
    public void onBackPressed(){
        // The back button should not do anything here, so overriding into empty procedure.
    }
}