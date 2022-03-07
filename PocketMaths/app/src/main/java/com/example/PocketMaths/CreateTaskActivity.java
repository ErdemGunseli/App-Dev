package com.example.PocketMaths;

import android.content.Intent;
import android.os.Bundle;
import android.transition.TransitionManager;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CreateTaskActivity extends AppCompatActivity implements View.OnClickListener {

    private ScrollView svCreateTask;

    private ImageView imgExit;

    private EditText edtTxtTaskName, edtTxtReward;

    private Spinner spQuestionSets;

    private SwitchCompat swAddReward;

    private Button btnCreate;

    private RecyclerView rvTasks;

    private DatabaseHelper databaseHelper = new DatabaseHelper(this);

    private TaskCreateRecyclerAdapter taskCreateRecyclerAdapter = new TaskCreateRecyclerAdapter(this, databaseHelper);

    //TODO: Task Item Needs Improvement

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_task);

        initViews();

        ArrayList<Task> tasks = new ArrayList<>();

        // Finding the incomplete tasks to show
        for (Task task: databaseHelper.getTasks()){
            if (!task.isCompleted()){
                tasks.add(task);
            }
        }

        taskCreateRecyclerAdapter.setTasks(tasks);

        rvTasks.setLayoutManager(new LinearLayoutManager(this));
        rvTasks.setAdapter(taskCreateRecyclerAdapter);
    }


    private void initViews() {
        imgExit = findViewById(R.id.imgExit);
        edtTxtTaskName = findViewById(R.id.edtTxtTaskName);
        svCreateTask = findViewById(R.id.svCreateTask);
        edtTxtReward = findViewById(R.id.edtTxtReward);
        swAddReward = findViewById(R.id.swAddReward);
        btnCreate = findViewById(R.id.btnCreate);
        svCreateTask = findViewById(R.id.svCreateTask);
        rvTasks = findViewById(R.id.rvTasks);

        imgExit.setOnClickListener(this);
        btnCreate.setOnClickListener(this);
        swAddReward.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean checked) {
                if (checked){
                    TransitionManager.beginDelayedTransition(svCreateTask);
                    edtTxtReward.setVisibility(View.VISIBLE);
                }
                else{
                    TransitionManager.beginDelayedTransition(svCreateTask);
                    edtTxtReward.setVisibility(View.GONE);
                    // Resetting the text of the Edit Text:
                    edtTxtReward.setText("");
                }
            }
        });
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()){

            case (R.id.btnCreate):
                createClicked();
                break;

            case (R.id.imgExit):
                startActivity(new Intent(this, MainMenuActivity.class));
                break;

            default:
                break;
        }

    }

    private void createClicked() {
        // TODO: Add Question Set ID:::::
        EditText[] inputs;

        if (swAddReward.isChecked()) {
            inputs = new EditText[]{edtTxtTaskName, edtTxtReward};
        }
        else{
            inputs = new EditText[]{edtTxtTaskName};
        }

        if (!Utils.getInstance().inputsFilled(inputs)){
            Utils.getInstance().showSnackBar(CreateTaskActivity.this, svCreateTask, getString(R.string.empty_inputs), getString(R.string.ok));
        }
        else if (!Utils.getInstance().isValid(inputs, 2)){
            Utils.getInstance().showSnackBar(CreateTaskActivity.this, svCreateTask, getString(R.string.input_lengths), getString(R.string.ok));
        }
        else{
            //TODO: Passing 0 as question set ID for now. Change This.

            Task task = new Task(0, edtTxtTaskName.getText().toString(), edtTxtReward.getText().toString(), 0, false);
            databaseHelper.addTask(task);
            taskCreateRecyclerAdapter.setTasks(databaseHelper.getTasks());
            TransitionManager.beginDelayedTransition(svCreateTask);
        }
    }
}
