package com.example.xmasdraft;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


public class ResultsRecyclerAdapter extends RecyclerView.Adapter<ResultsRecyclerAdapter.ViewHolder> {

    // We need an array list to pass the list of different contacts to the adapter class.
    // If we do not initialise this array list, we will get a null pointer exception.

    private QuestionSet questionSet;

    private Context context;

    public ResultsRecyclerAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // The return type of this method is a ViewHolder, indicating that this is the place to instantiate our View Holder.

        // We are creating a View object:

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.question_result_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Question[] questions = questionSet.getQuestions();
        Question currentQuestion = questions[position];
        String[] answers = currentQuestion.getAnswers();
        int correctAnswerIndex = currentQuestion.getCorrectAnswerIndex();
        int chosenAnswerIndex = currentQuestion.getChosenAnswerIndex();

        // We can access these private attributes directly since they are from a subclass:

        // Question X of X
        holder.txtCurrentQuestionIndex.setText(String.format(context.getString(R.string.question_x_of_x), (position + 1), questionSet.getQuestions().length));

        // X / X Points
        holder.txtPointsPossible.setText(String.format(context.getString(R.string.x_x_points), currentQuestion.getPointsEarned(), currentQuestion.getPointsPossible()));

        holder.txtQuestion.setText(currentQuestion.getQuestionText());

        holder.imgQuestion.setImageResource(currentQuestion.getImageID());

        if (currentQuestion.getType().equals("multipleChoice")) {

            // Your Initial Answer: X
            holder.txtChosenAnswer.setText(String.format(context.getString(R.string.initial_answer), answers[chosenAnswerIndex]));

            // Correct Answer: X
            holder.txtCorrectAnswer.setText(String.format(context.getString(R.string.correct_answer), answers[correctAnswerIndex]));

        }
        else if (currentQuestion.getType().equals("written")) {

            // If they revealed the answer, the variable storing their answer will be null, so use "?".
            // Otherwise, set text as variable.

            String chosenAnswer = "?";

            if (currentQuestion.getWrittenAnswer() != null) {
                chosenAnswer = currentQuestion.getWrittenAnswer();
            }
                // Your Initial Answer: X
                holder.txtChosenAnswer.setText(String.format(context.getString(R.string.initial_answer), chosenAnswer));

                // Correct Answer: X
                holder.txtCorrectAnswer.setText(String.format(context.getString(R.string.correct_answer), currentQuestion.getAnswer()));

        }
    }


    @Override
    public int getItemCount() {
        // This method returns the number of items.

        // Avoid null pointer exception
        if (questionSet != null){
            return questionSet.getQuestions().length;
        }
        return 0;
    }


    // We are using an inner-class:
    // The view holder class will hold the view for every item in the Recycler View.
    // It is responsible for generating the View objects.
    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView txtCurrentQuestionIndex, txtPointsPossible, txtQuestion, txtCorrectAnswer, txtChosenAnswer;
        private ImageView imgQuestion;


        public ViewHolder(@NonNull View itemView) {
            // We can add all the elements in our layout one by one:
            super(itemView);


            // Initialising View Objects
            initViews();



        }

        private void initViews() {

            // Inside an activity, we cannot simply use the fb function and need the following syntax:
            txtCurrentQuestionIndex = itemView.findViewById(R.id.txtCurrentQuestionIndex);
            txtPointsPossible = itemView.findViewById(R.id.txtPointsPossible);
            txtQuestion = itemView.findViewById(R.id.txtQuestion);
            txtCorrectAnswer = itemView.findViewById(R.id.txtCorrectAnswer);
            txtChosenAnswer = itemView.findViewById(R.id.txtChosenAnswer);
            imgQuestion = itemView.findViewById(R.id.imgQuestion);

        }


    }

    public void setQuestionSet(QuestionSet questionSet){this.questionSet = questionSet;}
}
