package com.example.PocketMaths;

import static com.example.PocketMaths.DatabaseHelper.COLUMN_TASK_COMPLETED;
import static com.example.PocketMaths.DatabaseHelper.COLUMN_TASK_ID;
import static com.example.PocketMaths.DatabaseHelper.COLUMN_TASK_NAME;
import static com.example.PocketMaths.DatabaseHelper.COLUMN_TASK_QUESTION_SET_ID;
import static com.example.PocketMaths.DatabaseHelper.COLUMN_TASK_REWARD;
import static com.example.PocketMaths.DatabaseHelper.TASKS_TABLE;
import static com.example.PocketMaths.DatabaseHelper.TASKS_TABLE_CREATE_SQLs;

import android.content.Intent;
import android.os.Bundle;
import android.transition.TransitionManager;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
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

public class TaskCreateActivity extends AppCompatActivity implements View.OnClickListener {

    private ScrollView svCreateTask;

    private ImageView imgExit;

    private EditText edtTxtTaskName, edtTxtReward;

    private Spinner spQuestionSets;

    private SwitchCompat swAddReward;

    private Button btnCreate;

    private RecyclerView rvTasks;

    private DatabaseHelper databaseHelper = new DatabaseHelper(this);

    private TaskCreateRecyclerAdapter taskCreateRecyclerAdapter = new TaskCreateRecyclerAdapter(this, databaseHelper);

    private int questionSetId = -1;

    private ArrayList<String> questionSetNames = new ArrayList<>();

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
        spQuestionSets = findViewById(R.id.spQuestionSets);

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
        spQuestionSets.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                questionSetId = i;
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                questionSetId = -1;
            }
        });

        for (QuestionSet questionSet: Utils.getQuestionSets()){
            questionSetNames.add(questionSet.getName());
        }

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, R.layout.spinner_item, questionSetNames);
        arrayAdapter.setDropDownViewResource(R.layout.spinner_dropdown_item);
        spQuestionSets.setAdapter(arrayAdapter);
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
            Utils.getInstance().showSnackBar(TaskCreateActivity.this, svCreateTask, getString(R.string.empty_inputs), getString(R.string.ok));
        }
        else if (!Utils.getInstance().isValid(inputs, 2)){
            Utils.getInstance().showSnackBar(TaskCreateActivity.this, svCreateTask, getString(R.string.input_lengths), getString(R.string.ok));
        }
        else if (questionSetId == -1) {
            Utils.getInstance().showSnackBar(TaskCreateActivity.this, svCreateTask, getString(R.string.choose_qs), getString(R.string.ok));
        }

        else{
            //TODO: Passing 0 as question set ID for now. Change This.
            System.out.println();


            Task task = new Task(questionSetId, edtTxtTaskName.getText().toString(), edtTxtReward.getText().toString(), spQuestionSets.getSelectedItemPosition(), false);
            databaseHelper.addTask(task);
            taskCreateRecyclerAdapter.setTasks(databaseHelper.getTasks());
            TransitionManager.beginDelayedTransition(svCreateTask);
        }
    }
}
