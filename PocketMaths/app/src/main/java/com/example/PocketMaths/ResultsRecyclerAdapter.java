package com.example.PocketMaths;

import static com.example.PocketMaths.Question.MULTIPLE_CHOICE;
import static com.example.PocketMaths.Question.WRITTEN;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;


/**
 * This class is required to efficiently display View objects containing data from instances of
 * Question from a particular instance of QuestionSet in the form of a scrollable list.
 * It is used to display the questions sets in the results page.
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * ResultsRecyclerAdapter extends RecyclerView.Adapter<ResultsRecyclerAdapter.ViewHolder>
 * to have access to RecyclerView methods.
 * ResultsRecyclerAdapter has inner class ViewHolder to store content to be displayed on each
 * RecyclerView container.
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */
public class ResultsRecyclerAdapter extends RecyclerView.Adapter<ResultsRecyclerAdapter.ViewHolder> {

    private QuestionSet questionSet;
    private Context context;

    /**
     * Constructor
     *
     * @param context Required to get strings from resources.
     */
    public ResultsRecyclerAdapter(Context context) {
        this.context = context;
    }

    /**
     * Runs when an instance of ViewHolder is created.
     * Attaches the XML layout file 'question_result_item' to the ViewHolder.
     *
     * @param parent   Required to inflate XML layout file into a View object.
     * @param viewType Required for background processing.
     * @return ViewHolder object.
     */
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.question_result_item, parent, false);
        return new ViewHolder(view);
    }

    /**
     * Runs when an instance of ViewHolder object attaches to a container.
     * Sets the appropriate data to the ViewHolder instance layout.
     *
     * @param holder   The instance of ViewHolder object, required for accessing data.
     * @param position The index of the ArrayList, required for accessing ArrayList items.
     */
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Question[] questions = questionSet.getQuestions();
        Question currentQuestion = questions[position];
        String[] answers = currentQuestion.getAnswerOptions();
        int correctAnswerIndex = currentQuestion.getCorrectAnswerIndex();

        ArrayList<Integer> userAnswerIndexes = currentQuestion.getUserAnswerIndexes();

        holder.txtCurrentQuestionIndex.setText(String.format(context.getString(R.string.question_x_of_x), (position + 1), questionSet.getQuestions().length));
        holder.txtPointsPossible.setText(String.format(context.getString(R.string.x_x_points), currentQuestion.getPointsEarned(), currentQuestion.getPointsPossible()));
        holder.txtQuestion.setText(currentQuestion.getText());
        holder.imgQuestion.setImageResource(currentQuestion.getImageId());

        if (currentQuestion.getType().equals(MULTIPLE_CHOICE)) {
            // Displaying their first answer and the correct answer:
            int chosenAnswerIndex = userAnswerIndexes.get(0);

            holder.txtChosenAnswer.setText(String.format(context.getString(R.string.initial_answer), answers[chosenAnswerIndex]));
            holder.txtCorrectAnswer.setText(String.format(context.getString(R.string.correct_answer), answers[correctAnswerIndex]));

        } else if (currentQuestion.getType().equals(WRITTEN)) {
            // If they revealed the answer, the variable storing their answer will be null, so using "?":
            // Otherwise, setting text as variable:
            String chosenAnswer = context.getString(R.string.question_mark);
            String writtenAnswer = currentQuestion.getUserWrittenAnswers().get(0);
            if (writtenAnswer != null) {
                chosenAnswer = writtenAnswer;
            }
            holder.txtChosenAnswer.setText(String.format(context.getString(R.string.initial_answer), chosenAnswer));
            holder.txtCorrectAnswer.setText(String.format(context.getString(R.string.correct_answer), currentQuestion.getCorrectWrittenAnswer()));
        }
    }

    @Override
    public int getItemCount() {
        // Avoid null pointer exception:
        if (questionSet != null) {
            return questionSet.getQuestions().length;
        }
        return 0;
    }

    public void setQuestionSet(QuestionSet questionSet) {
        this.questionSet = questionSet;
    }

    /**
     * This class is required to hold View objects for every item in the RecyclerView.
     * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     * ViewHolder extends RecyclerView.ViewHolder to have access to its constructor.
     * ViewHolder implements View.OnCLickListener interface to detect touch input.
     * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
     */
    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView txtCurrentQuestionIndex, txtPointsPossible, txtQuestion, txtCorrectAnswer, txtChosenAnswer;
        private ImageView imgQuestion;

        /**
         * Constructor
         *
         * @param itemView Required for super constructor
         */
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            initViews();
        }

        /**
         * Initialises View objects.
         * Sets the class' click listener to the appropriate View objects.
         */
        private void initViews() {
            // Outside an activity, we need the following syntax:
            txtCurrentQuestionIndex = itemView.findViewById(R.id.txtCurrentQuestionIndex);
            txtPointsPossible = itemView.findViewById(R.id.txtPointsPossible);
            txtQuestion = itemView.findViewById(R.id.txtQuestion);
            txtCorrectAnswer = itemView.findViewById(R.id.txtCorrectAnswer);
            txtChosenAnswer = itemView.findViewById(R.id.txtChosenAnswer);
            imgQuestion = itemView.findViewById(R.id.imgQuestion);
        }
    }
}
