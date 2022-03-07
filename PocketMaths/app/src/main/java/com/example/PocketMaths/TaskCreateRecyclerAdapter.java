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

public class TaskCreateRecyclerAdapter extends RecyclerView.Adapter<TaskCreateRecyclerAdapter.ViewHolder> {

    private ArrayList<Task> tasks = new ArrayList<>();

    private Context context;

    private DatabaseHelper databaseHelper;


    public TaskCreateRecyclerAdapter(Context context, DatabaseHelper databaseHelper){
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

        String reward;

        if (task.getReward().isEmpty()){
            reward = "";
        }
        else{
            reward = (String) String.format(context.getString(R.string.reward_details), task.getReward());
        }

        //TODO: Mention Question Set
        holder.txtCreateTaskDetails.setText(String.format(context.getString(R.string.create_task_details),
                task.getName(), reward));


    }

    @Override
    public int getItemCount() {
        return tasks.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView txtCreateTaskDetails;
        private ImageView imgDelete;

        public ViewHolder(View itemView) {
            super(itemView);
            initViews();

        }

        private void initViews() {
            txtCreateTaskDetails = itemView.findViewById(R.id.txtCreateTaskDetails);
            imgDelete = itemView.findViewById(R.id.imgDelete);

            imgDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    deleteTask(getAdapterPosition());
                }
            });
        }
    }

    public void setTasks(ArrayList<Task> tasks){
        this.tasks = tasks;
        notifyDataSetChanged();
    }


    public void deleteTask(int position){
        // Deleting specified item:

        if (databaseHelper.deleteTask(tasks.get(position))){
            Toast.makeText(context, "DELETION APPEARS SUCCESSFUL", Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(context, "DELETION APPEARS UNSUCCESSFUL", Toast.LENGTH_SHORT).show();
        }

        // Setting updated database:
        setTasks(databaseHelper.getTasks());

        //Item Changed:
        notifyItemChanged(position);

        //TODO: Problem, delete button needs to be clicked twice
        //TODO: Doesn't actually delete when clicked the first time after an activity is started??????

    }


}
