package com.example.PocketMaths;

import android.content.Intent;
import android.os.Bundle;
import android.transition.TransitionManager;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

public class TaskViewActivity extends AppCompatActivity implements View.OnClickListener {

    private ScrollView svViewTask;

    private ImageView imgExit;

    private TextView txtNothingHere;

    private RecyclerView rvTasks;

    private TabLayout tlStatus;

    private DatabaseHelper databaseHelper = new DatabaseHelper(this);

    private TaskViewRecyclerAdapter taskViewRecyclerAdapter = new TaskViewRecyclerAdapter(this, databaseHelper);

    private ArrayList<String> questionSetNames = new ArrayList<>();

    private ArrayList<Task> taskedTasks = new ArrayList<>();

    private ArrayList<Task> completedTasks = new ArrayList<>();


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_task);

        initViews();

        // Finding the incomplete tasks to show

        for (Task task: databaseHelper.getTasks()){
            if (task.isCompleted()){
                completedTasks.add(task);
            }
            else{
                taskedTasks.add(task);
            }
        }


        taskViewRecyclerAdapter.setTasks(taskedTasks);

        // Show message if there are no tasks:

        showMessageIfEmpty(taskedTasks);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        // Reverse order since latest task should appear first:
        linearLayoutManager.setReverseLayout(true);

        rvTasks.setLayoutManager(linearLayoutManager);




        rvTasks.setAdapter(taskViewRecyclerAdapter);



    }




    private void initViews() {
        svViewTask = findViewById(R.id.svViewTask);
        tlStatus = findViewById(R.id.tlStatus);
        imgExit = findViewById(R.id.imgExit);
        txtNothingHere = findViewById(R.id.txtNothingHere);
        rvTasks = findViewById(R.id.rvTasks);


        imgExit.setOnClickListener(this);
        tlStatus.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                switch (tab.getPosition()){

                    case (0):
                        taskViewRecyclerAdapter.setTasks(taskedTasks);

                        // Show message if there are no tasks:
                        showMessageIfEmpty(taskedTasks);
                        break;

                    case (1):
                        taskViewRecyclerAdapter.setTasks(completedTasks);

                        // Show message if there are no tasks:
                        showMessageIfEmpty(completedTasks);
                        break;

                    default:
                        break;

                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()){


            case (R.id.imgExit):
                startActivity(new Intent(this, MainMenuActivity.class));
                break;

            default:
                break;

        }

    }

    private void showMessageIfEmpty(ArrayList<Task> tasks) {
        if (tasks.isEmpty()){
            TransitionManager.beginDelayedTransition(svViewTask);
            txtNothingHere.setVisibility(View.VISIBLE);
        }
        else{
            TransitionManager.beginDelayedTransition(svViewTask);
            txtNothingHere.setVisibility(View.GONE);
        }

    }
}
