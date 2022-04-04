package com.example.PocketMaths;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.Collections;

/**
 * This class provides many app-wide functionalities.
 * It gives access to many methods and attributes that are required throughout the app.
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * Utils follows the Singleton pattern so that only one instance of it can be made.
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */
public class Utils {

    private static Utils instance;
    private Account userAccount;
    private Class<?> targetClass = MainMenuActivity.class;

    public static final String THEME_ID = "themeId";
    public static final String SHOW_REFRESHERS = "showRefreshers";

    private int themeId = R.style.Theme_PocketMaths;
    private static ArrayList<QuestionSet> questionSets;
    private static ArrayList<Refresher> refreshers;
    private boolean showRefreshers = true;


    /**
     * Private Constructor
     * Follows the Singleton pattern such that new Utils objects can only be constructed from within
     * the class.
     * Causes data to be initialised.
     */
    private Utils() {
        // If the Question Sets have not been initialised yet, initialise them.
        if (questionSets == null) {
            initData();
        }
    }

    /**
     * Initialising Question Sets
     */
    private void initData() {
        // TODO: These will all be stored in the database, so the IDs etc. will not be problematic.

        questionSets = new ArrayList<>();
        refreshers = new ArrayList<>();

        Question[] fractionsBasicsQuestions = new Question[]{

//                new Question("Fractions Basics",
//                        "What fraction does the following diagram represent? (5/12)", 0,
//                        "5/12", 10),

                new Question("Fractions Basics",
                        "David has a pizza consisting of 8 slices. He eats 1 of the slices. What fraction of the pizza has he eaten?", 0,
                        new String[]{"(A)\t8/1", "(B)\t1/2", "(C)\t1/8", "(D)\t1/4"}, 2, 10),

                new Question("Fractions Basics",
                        "What is the denominator of the fraction represented by the diagram? (13)", 0,
                        "13", 20),

                new Question("Fractions Basics",
                        "What is the numerator of the fraction represented by the diagram? (6)", 0,
                        "6", 20),

                new Question("Fractions Basics",
                        "Jenna, Emma and Alex split 23 marbles between themselves. Emma gets 8 and Alex gets 6. What fraction of the marbles does Jenna receive?", 0,
                        new String[]{"(A)\t14/23", "(B)\t6/23", "(C)\t8/23", "(D)\t9/23"}, 3, 30),


                new Question("Fractions Basics",
                        "Edward's mother's age is 3 times that of Edward's. Edward's mother is 36 years old. Which of the following shows Edward's age as a fraction of his mother's?", 0,
                        new String[]{"(A)\t1/3", "(B)\t1/36", "(C)\t1/12", "(D)\t1/6"}, 0, 30),


                new Question("Fractions Basics",
                        "James is given £15 to spend in the museum. He decides that he will spend a third of this money. He would like to buy a gift each for 5 of his friends. He will spend the same amount of money on each gift. Express the money he spends on 2 of the gifts as a fraction of his total money.",
                        0, new String[]{"(A)\t5/15", "(B)\t2/15", "(C)\t2/5", "(D)\t1/3"}, 1, 50),


                new Question("Simplifying Fractions",
                        "Ronald buys 9 watermelons, 6 of which he later sells. Which of the following shows the number of watermelons not sold as a fraction of all of the watermelons in its simplest form?", 0,
                        new String[]{"(A)\t6/9", "(B)\t1/3", "(C)\t2/3", "(D)\t3/9"}, 1, 50),

                new Question("Simplifying Fractions",
                        "The following is an ingredients list for a cake. Flour comes in packets of 1kg. Which of the following is the amount of flour used for the cake expressed as a fraction of the amount of flour in a packet in its simplest form?", R.drawable.cake_ingredients_list,
                        new String[]{"(A)\t200/1000", "(B)\t200/1", "(C)\t2/10", "(D)\t1/5"}, 3, 100),

                new Question("Simplifying Fractions",
                        "The following is an ingredients list for a cake. Kate would like to follow this recipe. She uses 1 cup of vegetable oil. How many cups of milk must she use?", R.drawable.cake_ingredients_list,
                        "2", 100),

                //TODO: Improper Fractions

                // To compare fractions, obtain the same base:
                new Question("Comparing Fractions",
                        "Twins Cameron and Luke each get an identically sized cake for their birthday. Cameron slices his cake into 5 equal pieces and eats 2. Luke slices his cake into 7 equal pieces and eats 3. Which one of them has had more cake? By how much?",
                        0, new String[]{"(A)\tLuke by 1/35", "(B)\tCameron by 1/35", "(C)\tLuke by 1/7", "(D)\tCameron by 1/5"}, 0, 200),


                new Question("Comparing Fractions",
                        "A cinema offers 3 sizes of popcorn. The small size is 100g and costs 50p. The medium size is 250g and costs £1.10. The large size is 350g and costs £2. Which size has the best value?", 0,
                        new String[]{"(A)\tSmall Size", "(B)\tMedium Size", "(C)\tLarge Size", "(D)\tAll sizes have the same value."}, 1, 250)

        };


        refreshers.add(new Refresher(0, "Fractions Basics", R.drawable.fraction_basics_1));
        refreshers.add(new Refresher(1, "Simplifying Fractions", R.drawable.fraction_basics_2));
        // Reversing the order of the refreshers such that they are displayed correctly:
        Collections.reverse(refreshers);


        questionSets.add(new QuestionSet(0, "Fractions Basics", "Get the hang of expressing, simplifying & comparing fractions and mixed numbers.", R.color.Silver,
                fractionsBasicsQuestions));

        questionSets.add(new QuestionSet(1, "Working with Fractions", "Master adding, subtracting, multiplying & dividing fractions, mixed and whole numbers.", R.color.Silver,
                fractionsBasicsQuestions));

        questionSets.add(new QuestionSet(2, "Fractions & Decimals", "Practice converting between fractions and decimals", R.color.Silver,
                fractionsBasicsQuestions));

        // Learn, Discover, Understand, find out, comprehend, retain
    }

    /**
     * Retrieves instance of Utils.
     * If there are none, creates an instance.
     * Synchronised to be Thread-Safe (if multi-threaded processing was to be added).
     *
     * @return The instance created now or previously.
     */
    public static synchronized Utils getInstance() {
        if (instance == null) {
            instance = new Utils();
        }
        return instance;
    }


    public static ArrayList<QuestionSet> getQuestionSets() {
        return questionSets;
    }

    /**
     * Iterates through each QuestionSet instance, returning the object of the required ID.
     *
     * @param questionSetId The ID of the Question Set
     * @return The QuestionSet instance retrieved, or null if none found.
     */
    public QuestionSet getQuestionSetById(int questionSetId) {
        for (QuestionSet questionSet : questionSets) {
            if (questionSet.getId() == questionSetId) {
                return questionSet;
            }
        }
        return null;
    }

    public ArrayList<Refresher> getRefreshers() {
        return refreshers;
    }


    /**
     * Iterates through each Refresher instance, returning the object of the required ID.
     *
     * @param refresherId The ID of the Refresher
     * @return The Refresher instance retrieved, or null if none found.
     */
    public Refresher getRefresherById(int refresherId) {
        for (Refresher refresher : refreshers) {
            if (refresher.getId() == refresherId) {
                return refresher;
            }
        }
        return null;
    }

    public Account getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(Account userAccount) {
        this.userAccount = userAccount;
    }

    public void setThemeId(int id) {
        this.themeId = id;
    }

    public int getThemeId() {
        return this.themeId;
    }

    /**
     * Creates an interactive pie chart according to the parameters.
     * @param context Required to get colours.
     * @param pieChart The PieChart instance that is going to display the pie chart.
     * @param values The values that are going to be displayed on the pie chart.
     * @param valueTextSize The text size of the values in sp.
     * @param description The description of the pie chart that will appear underneath it.
     * @param descriptionTextSize The text size of the description in sp.
     * @param labels The labels of the pie chart.
     * @param labelTextSize The text size of the labels in sp.
     * @param labelColourId The ID colour of the labels from resources.
     * @param centerText The text that will go to the center of the pie chart.
     * @param centerTextSize The text size of the center text in sp.
     * @param textColourId The ID of the colour of other texts - center and label.
     * @param backgroundColourId The ID of the background colour of the pie chart.
     */
    public void createPieChart(Context context, PieChart pieChart, int[] values, int valueTextSize, String description, int descriptionTextSize, String[] labels, int labelTextSize, int labelColourId, String centerText, int centerTextSize, int textColourId, int backgroundColourId) {
        pieChart.setDrawHoleEnabled(true);
        pieChart.setHoleColor(context.getResources().getColor(backgroundColourId));
        pieChart.setUsePercentValues(true);
        pieChart.setEntryLabelTextSize(labelTextSize);
        pieChart.setEntryLabelColor(context.getResources().getColor(labelColourId));
        pieChart.setCenterText(centerText);
        pieChart.setCenterTextColor(context.getResources().getColor(textColourId));
        pieChart.setCenterTextSize(centerTextSize);
        pieChart.getDescription().setText(description);
        pieChart.getDescription().setTextSize(descriptionTextSize);

        ArrayList<PieEntry> entries = new ArrayList<>();
        String label;
        for (int index = 0; index < values.length; index++) {
            label = null;
            if (values[index] > 0) {
                label = labels[index];
            }
            entries.add(new PieEntry(values[index], label));
        }

        ArrayList<Integer> colors = new ArrayList<>();
        for (int color : ColorTemplate.MATERIAL_COLORS) {
            colors.add(color);
        }

        PieDataSet dataSet = new PieDataSet(entries, null);
        dataSet.setColors(colors);

        PieData data = new PieData(dataSet);
        data.setDrawValues(true);
        data.setValueFormatter(new CustomPercentFormatter(pieChart));
        data.setValueTextSize(valueTextSize);
        data.setValueTextColor(context.getResources().getColor(textColourId));

        pieChart.setData(data);
        pieChart.invalidate();
    }

    /**
     * Checks if any string in an array is empty.
     *
     * @param inputs Array of Strings
     * @return If any string is empty.
     */
    public boolean emptyInputs(String[] inputs) {
        for (String input : inputs) {
            if (input.isEmpty()) {
                return true;
            }
        }
        return false;
    }


    /**
     * Checks if any string in an array is less than the minimum length required.
     *
     * @param inputs    Array of Strings
     * @param minLength Minimum length that each String must match.
     * @return Whether the length of any of the strings is less than the minimum.
     */
    public boolean inputsInvalid(String[] inputs, int minLength) {
        for (String input : inputs) {
            if (input.length() < minLength) {
                return true;
            }
        }
        return false;
    }


    /**
     * Checks if an email is valid.
     *
     * @param target Email
     * @return Whether or not the email is valid.
     */
    public boolean isValidEmail(CharSequence target) {
        return target != null && android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();
    }

    /**
     * Displays a SnackBar according to the parameters.
     *
     * @param layout     The layout where the SnackBar will be displayed
     * @param mainText   The main text of the SnackBar.
     * @param actionText The action text of the SnackBar.
     */
    public void showSnackBar(Context context, ViewGroup layout, String mainText, String actionText) {
        Snackbar.make(layout, mainText, Snackbar.LENGTH_LONG)
                .setAction(actionText, (View v) -> {})
                .setActionTextColor(context.getResources().getColor(R.color.YellowOrange))
                .setTextColor(context.getResources().getColor(R.color.YellowOrange))
                .setBackgroundTint(context.getResources().getColor(R.color.OxfordBlue))
                .show();
    }

    public Class<?> getTargetClass() {
        return targetClass;
    }

    public void setTargetClass(Class<?> targetClass) {
        if (targetClass != null) {
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
