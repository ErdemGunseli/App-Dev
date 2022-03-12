package com.example.PocketMaths;

import static com.example.PocketMaths.DatabaseHelper.TASKS_TABLE_CREATE_SQLs;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class TaskViewActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView imgExit;

    private RecyclerView rvTasks;

    private DatabaseHelper databaseHelper = new DatabaseHelper(this);

    private TaskViewRecyclerAdapter taskViewRecyclerAdapter = new TaskViewRecyclerAdapter(this, databaseHelper);

    private ArrayList<String> questionSetNames = new ArrayList<>();


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_task);

        initViews();

        // Finding the incomplete tasks to show
        ArrayList<Task> tasks = new ArrayList<>(databaseHelper.getTasks());

        taskViewRecyclerAdapter.setTasks(tasks);

        rvTasks.setLayoutManager(new LinearLayoutManager(this));
        rvTasks.setAdapter(taskViewRecyclerAdapter);
    }


    private void initViews() {
        imgExit = findViewById(R.id.imgExit);
        rvTasks = findViewById(R.id.rvTasks);

        imgExit.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        if (view.getId() == R.id.imgExit){
            startActivity(new Intent(this, MainMenuActivity.class));
        }

    }
}
