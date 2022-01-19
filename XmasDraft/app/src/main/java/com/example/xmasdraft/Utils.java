package com.example.xmasdraft;

import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RelativeLayout;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

public class Utils extends AppCompatActivity {

    private static Utils instance;

    // TODO: More arrays??
    private static ArrayList<QuestionSet> questionSets;

    // We are using Static variables so that they can be accessed anywhere in the application
    // The class needs to be Singleton:

    private Utils(){

        // If the Question Sets have not been initialised yet, initialise them.
        if (questionSets == null){
            initData();

        }

    }

    /**
     * Initialising Question Sets
     */
    private void initData() {
        questionSets = new ArrayList<>();
        Question[] sampleQuestionSetQuestions = new Question[] {
                new Question("The answer to this question is not (B). What is the answer?",0, new String[]{"(A)\tThe Answer Is D", "(B)\tThe Answer Is A", "(C)\tThe Answer Is C", "(D)\tThere Is Not Enough Information"}, 2, 200),
                new Question("Question 2. The answer is B.",R.drawable.ic_launcher_background, new String[]{"(A)\tSample", "(B)\tSample", "(C)\tSample", "(D)\tSample"}, 1, 150),
                new Question("Question 3. The answer is D.", R.drawable.ic_launcher_background,  new String[]{"(A)\tSample", "(B)\tSample", "(C)\tSample", "(D)\tSample"}, 3, 100),
        };

        questionSets.add(new QuestionSet(0, "Question Set 1", "The First Question Set", R.drawable.ic_launcher_background,
                sampleQuestionSetQuestions));

        questionSets.add(new QuestionSet(1, "Question Set 2", "The Second Question Set", R.drawable.ic_launcher_background,
                sampleQuestionSetQuestions));

        questionSets.add(new QuestionSet(2, "Question Set 3", "The Third Question Set", R.drawable.ic_launcher_background,
                sampleQuestionSetQuestions));

        questionSets.add(new QuestionSet(3, "Question Set 4", "The Fourth Question Set", R.drawable.ic_launcher_background,
                sampleQuestionSetQuestions));

        questionSets.add(new QuestionSet(4, "Question Set 5", "The Fifth Question Set", R.drawable.ic_launcher_background,
                sampleQuestionSetQuestions));

    }

    /**
     * Gets instance of Singleton Class
     * Synchronised to be Thread-Safe
     * @return The instance created now or previously
     */
    public static synchronized Utils getInstance() {
        if (instance == null){
            instance = new Utils();
        }
        return instance;
    }


    public static ArrayList<QuestionSet> getQuestionSets() {
        return questionSets;
    }

    /**
     * This method is used to get a given Question Set by its ID
     * @param questionSetID The ID of the Question Set
     * @return The Question Set found by the function
     */
    public QuestionSet getQuestionSetByID(int questionSetID) {
        // For Each loop to go through each Question Set:
        for (QuestionSet questionSet: questionSets) {

            // If the current Question Set has the same ID as the target, return it:
            if (questionSet.getQuestionSetID() == questionSetID){
                return questionSet;
            }
        }
        // If we have not found the Question Set, we are returning null:
        return null;
    }


}
