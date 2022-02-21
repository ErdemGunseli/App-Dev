package com.example.xmasdraft;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RelativeLayout;

import androidx.appcompat.app.AppCompatActivity;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.google.android.material.snackbar.Snackbar;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Utils extends AppCompatActivity {

    private static Utils instance;

    private Account userAccount;

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
                new Question("Topic 1", "Model 1", "The answer to this question is not (B). What is the answer?",0, new String[]{"(A)\tThe Answer Is D", "(B)\tThe Answer Is A", "(C)\tThe Answer Is C", "(D)\tThere Is Not Enough Information"}, 2, 200),
                new Question("Topic 2", "Model 2","Question 2. The answer is B.",R.drawable.ic_launcher_background, new String[]{"(A)\tSample", "(B)\tSample", "(C)\tSample", "(D)\tSample"}, 1, 150),
                new Question("Topic 3", "Model 3","Question 3. The answer is D.", R.drawable.ic_launcher_background,  new String[]{"(A)\tSample", "(B)\tSample", "(C)\tSample", "(D)\tSample"}, 3, 100),
                new Question("Topic 4", "Model 4","The answer is 123", R.drawable.ic_launcher_background, "123", 300)
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

    public Account getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(Account userAccount) {
        this.userAccount = userAccount;
    }

    public void createPieChart(Context context, PieChart pieChart, int[] values, String description, String[] labels, int labelColour, String centerText, int textColour, int backgroundColour){

        /// Setting Up:
        pieChart.setDrawHoleEnabled(true);
        pieChart.setHoleColor(context.getResources().getColor(backgroundColour));
        pieChart.setUsePercentValues(true);
        pieChart.setEntryLabelTextSize(13);
        pieChart.setEntryLabelColor(context.getResources().getColor(labelColour));
        pieChart.setCenterText(centerText);
        pieChart.setCenterTextColor(context.getResources().getColor(textColour));
        pieChart.setCenterTextSize(14);
        pieChart.getDescription().setText(description);
        pieChart.getDescription().setTextSize(11);

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
        data.setValueFormatter(new PercentFormatter(pieChart));
        data.setValueTextSize(11f);
        data.setValueTextColor(context.getResources().getColor(textColour));

        pieChart.setData(data);
        pieChart.invalidate();


    }
}
