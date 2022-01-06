package com.example.xmasdraft;

import java.util.ArrayList;

public class Utils {

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

    private void initData() {
        // Initialising Question Sets

        questionSets = new ArrayList<>();
        Question[] sampleQuestionSetQuestions = new Question[] {
                new Question("The answer to this question is not (B). What is the answer?",-1, new String[]{"(A)\tThe Answer Is D", "(B)\tThe Answer Is A", "(C)\tThe Answer Is C", "(D)\tThere Is Not Enough Information"}, 2),
                new Question("Question 2. The answer is B.",R.drawable.ic_launcher_background, new String[]{"(A)\tSample", "(B)\tSample", "(C)\tSample", "(D)\tSample"}, 1),
                new Question("Question 3. The answer is D.", R.drawable.ic_launcher_background,  new String[]{"(A)\tSample", "(B)\tSample", "(C)\tSample", "(D)\tSample"}, 3),
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

    // synchronized to be thread-safe
    public static synchronized Utils getInstance() {
        if (instance == null){
            instance = new Utils();
        }
        return instance;
    }


    public static ArrayList<QuestionSet> getQuestionSets() {
        return questionSets;
    }


    public QuestionSet getQuestionSetByID(int questionSetID) {
        // This method is used to get a given Question Set by its ID

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
