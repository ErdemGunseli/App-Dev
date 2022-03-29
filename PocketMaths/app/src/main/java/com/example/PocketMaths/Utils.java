package com.example.PocketMaths;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import androidx.appcompat.app.AppCompatActivity;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.Collections;

public class Utils extends AppCompatActivity {

    public static final String THEME_ID = "themeId";
    public static final String SHOW_REFRESHERS = "showRefreshers";

    // Target Class used for Pin Checking, initialised to the main menu:
    private Class<?> targetClass = MainMenuActivity.class;

    // Singleton Class
    private static Utils instance;

    private Account userAccount;

    private int themeId = 0;

    private static ArrayList<QuestionSet> questionSets;

    private static ArrayList<Refresher> refreshers;

    private boolean showRefreshers = true;




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
        // TODO: These will all be stored in the database, so the IDs etc. will not be problematic.

        questionSets = new ArrayList<>();
        Question[] sampleQuestionSetQuestions = new Question[] {
                new Question("Topic 1", "Model 1", "The answer to this question is not (B). What is the answer?",0, new String[]{"(A)\tThe Answer Is D", "(B)\tThe Answer Is A", "(C)\tThe Answer Is C", "(D)\tThere Is Not Enough Information"}, 2, 200),
                new Question("Topic 2", "Model 2","Question 2. The answer is B.", R.color.Silver, new String[]{"(A)\tSample", "(B)\tSample", "(C)\tSample", "(D)\tSample"}, 1, 150),
                new Question("Topic 3", "Model 3","Question 3. The answer is D.", R.color.Silver,  new String[]{"(A)\tSample", "(B)\tSample", "(C)\tSample", "(D)\tSample"}, 3, 100),
                new Question("Topic 4", "Model 4","The answer is 123", R.color.Silver, "123", 300)
        };

        Refresher[] sampleRefreshers = new Refresher[]{
                new Refresher(0, "Topic 2", R.drawable.sample, 1),
                new Refresher(1, "Topic 3", R.drawable.sample, 2),
                new Refresher(2, "Topic 4", R.drawable.sample, 3),
        };

        refreshers = new ArrayList<>();
        Collections.addAll(refreshers, sampleRefreshers);



        questionSets.add(new QuestionSet(0, "Question Set 1", "The First Question Set", R.color.Silver,
                sampleQuestionSetQuestions, sampleRefreshers));

        questionSets.add(new QuestionSet(1, "Question Set 2", "The Second Question Set", R.color.Silver,
                sampleQuestionSetQuestions, sampleRefreshers));

        questionSets.add(new QuestionSet(2, "Question Set 3", "The Third Question Set", R.color.Silver,
                sampleQuestionSetQuestions, sampleRefreshers));

        questionSets.add(new QuestionSet(3, "Question Set 4", "The Fourth Question Set", R.color.Silver,
                sampleQuestionSetQuestions, sampleRefreshers));

        questionSets.add(new QuestionSet(4, "Question Set 5", "The Fifth Question Set", R.color.Silver,
                sampleQuestionSetQuestions, sampleRefreshers));


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
    public QuestionSet getQuestionSetById(int questionSetID) {
        // For Each loop to go through each Question Set:
        for (QuestionSet questionSet: questionSets) {

            // If the current Question Set has the same ID as the target, return it:
            if (questionSet.getId() == questionSetID){
                return questionSet;
            }
        }
        // If we have not found the Question Set, we are returning null:
        return null;
    }

    public Refresher getRefresherById(int refresherId){
        for (Refresher refresher: refreshers) {

            // If the current refresher has the same ID as the target, return it:
            if (refresher.getId() == refresherId){
                return refresher;
            }
        }
        // If we have not found the refresher, we are returning null:
        return null;
    }

    public Account getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(Account userAccount) {
        this.userAccount = userAccount;
    }

    public void setThemeId(int id){this.themeId = id;}

    public int getThemeID(){return this.themeId;}

    public void createPieChart(Context context, PieChart pieChart, int[] values, int valueTextSize, String description, int descriptionTextSize, String[] labels, int labelTextSize, int labelColour, String centerText, int centerTextSize, int textColour, int backgroundColour){

        /// Setting Up:
        pieChart.setDrawHoleEnabled(true);
        pieChart.setHoleColor(context.getResources().getColor(backgroundColour));
        pieChart.setUsePercentValues(true);
        pieChart.setEntryLabelTextSize(labelTextSize);
        pieChart.setEntryLabelColor(context.getResources().getColor(labelColour));
        pieChart.setCenterText(centerText);
        pieChart.setCenterTextColor(context.getResources().getColor(textColour));
        pieChart.setCenterTextSize(centerTextSize);
        pieChart.getDescription().setText(description);
        pieChart.getDescription().setTextSize(descriptionTextSize);

        /// Loading:
        ArrayList<PieEntry> entries = new ArrayList<>();

        // If the value is greater than 0, give it a label:
        // Add it to the pie chart either way (for correct colour assignment)
        String label;
        for (int index = 0; index < values.length; index++){
            label = null;
            if (values[index] > 0){
               label = labels[index];
            }
            entries.add(new PieEntry(values[index], label));
        }

        ArrayList<Integer> colors = new ArrayList<>();
        // Getting colours of the segments of the pie chart:
        for (int color : ColorTemplate.MATERIAL_COLORS) {
            colors.add(color);
        }


        PieDataSet dataSet = new PieDataSet(entries, null);
        dataSet.setColors(colors);

        PieData data = new PieData(dataSet);
        data.setDrawValues(true);
        data.setValueFormatter(new CustomPercentFormatter(pieChart));
        data.setValueTextSize(valueTextSize);
        data.setValueTextColor(context.getResources().getColor(textColour));

        pieChart.setData(data);
        pieChart.invalidate();

    }

    /**
     *Checks if an array containing inputs is filled:
     * @param inputs Array of EditTexts
     * @return Whether or not all of the inputs have been filled in
     */
    public boolean inputsFilled(String[] inputs) {
        for (String input: inputs){
            if (input.isEmpty()){
                return false;
            }
        }
        return true;
    }


    /**
     * Checks if an input array of Edit Texts is valid:
     * @param inputs Array of input Edit Texts
     * @param minLength Minimum length that each input can take
     * @return Whether all the inputs in the array are valid
     */
    public boolean isValid(String[] inputs, int minLength) {
        for (String input: inputs){
            if (input.length() < minLength){
                return false;
            }
        }
        return true;
    }



    /**
     * Checks if email is valid
     * @param target The input
     * @return Whether or not the input is an Email
     */
    public boolean isValidEmail(CharSequence target) {
        return target != null && android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();
    }

    /**
     * Shows a Snack Bar
     * @param layout The layout
     * @param mainText The main text
     * @param actionText The action text
     */
    public void showSnackBar(Context context, ViewGroup layout, String mainText, String actionText) {

        // Shows Snack Bar:
        Snackbar.make(layout, mainText, Snackbar.LENGTH_LONG)
                .setAction(actionText, new View.OnClickListener() {
                    @Override 
                    public void onClick(View v) {
                    }
                })
                .setActionTextColor(context.getResources().getColor(R.color.YellowOrange))
                .setTextColor(context.getResources().getColor(R.color.YellowOrange))
                .setBackgroundTint(context.getResources().getColor(R.color.OxfordBlue))
                .show();
    }

    public Class<?> getTargetClass() {
        return targetClass;
    }

    public void setTargetClass(Class<?> targetClass) {
        if (targetClass != null){
            this.targetClass = targetClass;

        }
    }

    public boolean refreshersEnabled() {
        return showRefreshers;
    }

    public void setShowRefreshers(boolean showRefreshers) {
        this.showRefreshers = showRefreshers;
    }
}
