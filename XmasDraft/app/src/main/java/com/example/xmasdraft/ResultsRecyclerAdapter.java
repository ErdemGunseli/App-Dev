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

        holder.txtCurrentQuestionIndex.setText("Question " + (position + 1) + " of " + questionSet.getQuestions().length);

        holder.txtPointsPossible.setText(currentQuestion.getPointsEarned() + " / "+ currentQuestion.getPointsPossible() + " Points");

        holder.txtQuestion.setText(currentQuestion.getQuestionText());

        holder.imgQuestion.setImageResource(currentQuestion.getImageID());

        if (currentQuestion.getType().equals("multipleChoice")) {
            holder.txtCorrectAnswer.setText("Your Answer: " + answers[chosenAnswerIndex]);
            holder.txtChosenAnswer.setText("Correct Answer: " + answers[correctAnswerIndex]);
        }
        else if (currentQuestion.getType().equals("written")) {
            holder.txtCorrectAnswer.setText("Your Answer: " + currentQuestion.getWrittenAnswer());
            holder.txtChosenAnswer.setText("Correct Answer: " + currentQuestion.getAnswer());
        }
    }


    @Override
    public int getItemCount() {
        // This method returns the number of items.
        // Since our contacts are stored in the contacts array list, we can just return the size of that.
        return questionSet.getQuestions().length;
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
