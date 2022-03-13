package com.example.PocketMaths;

import android.content.Context;
import android.transition.TransitionManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Locale;

public class TaskViewRecyclerAdapter extends RecyclerView.Adapter<TaskViewRecyclerAdapter.ViewHolder> {

    private ArrayList<Task> tasks = new ArrayList<>();

    private Context context;

    private DatabaseHelper databaseHelper;


    public TaskViewRecyclerAdapter(Context context, DatabaseHelper databaseHelper){
        this.context = context;
        this.databaseHelper = databaseHelper;

    }

    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.create_task_item, parent, false);
        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Task task = tasks.get(position);

        // Showing Details:

        holder.txtTaskName.setText(task.getName());

        holder.txtQuestionSetName.setText(String.format(context.getString(R.string.view_task_question_set_name),
                Utils.getInstance().getQuestionSetByID(task.getQuestionSetId()).getName()));

        holder.txtPassMark.setText(String.format(context.getString(R.string.view_task_pass_mark), task.getPassMark()));


        // Only show the reward if there is one:
        if (task.getReward().isEmpty()){
            holder.txtReward.setVisibility(View.GONE);
        }
        else{
            holder.txtReward.setText(String.format(context.getString(R.string.view_task_reward), task.getReward()));
        }

        // The delete button should not be visible:
        holder.imgDelete.setVisibility(View.GONE);

    }

    @Override
    public int getItemCount() {
        return tasks.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView txtTaskName, txtQuestionSetName, txtPassMark, txtReward;

        private ImageView imgDelete;

        public ViewHolder(View itemView) {
            super(itemView);
            initViews();

        }

        private void initViews() {
            txtTaskName = itemView.findViewById(R.id.txtTaskName);
            txtQuestionSetName = itemView.findViewById(R.id.txtQuestionSetName);
            txtPassMark = itemView.findViewById(R.id.txtPassMark);
            txtReward = itemView.findViewById(R.id.txtReward);

            imgDelete = itemView.findViewById(R.id.imgDelete);
        }
    }

    public void setTasks(ArrayList<Task> tasks){
        this.tasks = tasks;
        notifyDataSetChanged();
    }

}
