package com.example.PocketMaths;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.github.mikephil.charting.charts.PieChart;

import java.util.ArrayList;
import java.util.Objects;

public class AccountHistoryRecyclerAdapter extends RecyclerView.Adapter<AccountHistoryRecyclerAdapter.ViewHolder> {

    private Context context;

    private DatabaseHelper databaseHelper;
    private ArrayList<QuestionSetResult> questionSets;


    public AccountHistoryRecyclerAdapter(Context context) {
        this.context = context;
        this.databaseHelper = new DatabaseHelper(context);
        this.questionSets = databaseHelper.getQuestionSetResults();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // The return type of this method is a ViewHolder, indicating that this is the place to instantiate our View Holder.

        // We are creating a View object:

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.account_history_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        QuestionSetResult questionSetHistory = questionSets.get(position);

        // Question Set Name:
        holder.txtQuestionSetName.setText(Objects.requireNonNull(Utils.getInstance().getQuestionSetById(questionSetHistory.getQuestionSetId())).getName());

        // X Points Out Of X
        holder.txtPoints.setText(String.format(context.getString(R.string.x_points_out_of_x), questionSetHistory.getPointsEarned(), questionSetHistory.getPointsPossible()));


        holder.txtResultPercentage.setText(String.format(context.getString(R.string.result_percent), questionSetHistory.getResult()));


        int[] values = {questionSetHistory.getFirstAttempt(), questionSetHistory.getSecondAttempt(), questionSetHistory.getMoreAttempts()};
        String[] labels = {context.getString(R.string.first_attempt), context.getString(R.string.second_attempt), context.getString(R.string.other)};

        // Creating the Pie Chart
        Utils.getInstance().createPieChart(context,
                holder.pieHistory,
                values,
                0,
                questionSetHistory.getDateCompleted(),
                11,
                labels,
                0,
                R.color.Primary,
                context.getString(R.string.results),
                13,
                R.color.Primary,
                R.color.Silver);

    }


    @Override
    public int getItemCount() {
        // This method returns the number of items.

        // Avoid null pointer exception
        if (questionSets != null){
            return questionSets.size();
        }
        return 0;

    }


    // We are using an inner-class:
    // The view holder class will hold the view for every item in the Recycler View.
    // It is responsible for generating the View objects.
    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView txtQuestionSetName, txtPoints, txtResultPercentage;
        private PieChart pieHistory;


        public ViewHolder(@NonNull View itemView) {
            // We can add all the elements in our layout one by one:
            super(itemView);


            // Initialising View Objects
            initViews();

        }

        private void initViews() {

            // Inside an activity, we cannot simply use the fb function and need the following syntax:
            txtQuestionSetName = itemView.findViewById(R.id.txtQuestionSetName);
            txtPoints = itemView.findViewById(R.id.txtPoints);
            txtResultPercentage = itemView.findViewById(R.id.txtResultPercentage);

            pieHistory = itemView.findViewById(R.id.pieHistory);

        }


    }


    @SuppressLint("NotifyDataSetChanged")
    public void setQuestionSets(ArrayList<QuestionSetResult> questionSets) {
        // We will later use this method to pass the data into the contacts array list.
        this.questionSets = questionSets;

        // When we change the contacts ArrayList, we need to refresh the data inside the Recycler View:
        notifyDataSetChanged();
    }

}
