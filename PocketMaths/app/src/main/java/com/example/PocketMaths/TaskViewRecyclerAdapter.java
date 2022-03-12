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

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_task_item, parent, false);
        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Task task = tasks.get(position);

        String reward;

        String questionSetName = Utils.getInstance().getQuestionSetByID(task.getQuestionSetId()).getName();

        if (task.getReward().isEmpty()){
            reward = "";
        }
        else{
            reward = (String) String.format(context.getString(R.string.reward_details), task.getReward());
        }

        //TODO: Mention Question Set
        holder.txtViewTaskDetail.setText(String.format(context.getString(R.string.create_task_details),
                task.getName(), questionSetName, reward));

    }

    @Override
    public int getItemCount() {
        return tasks.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView txtViewTaskDetail;

        public ViewHolder(View itemView) {
            super(itemView);
            initViews();

        }

        private void initViews() {
            txtViewTaskDetail = itemView.findViewById(R.id.txtViewTaskDetail);
        }
    }

    public void setTasks(ArrayList<Task> tasks){
        this.tasks = tasks;
        notifyDataSetChanged();
    }

}
