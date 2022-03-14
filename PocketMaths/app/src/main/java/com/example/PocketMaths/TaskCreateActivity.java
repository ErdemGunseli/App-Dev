package com.example.PocketMaths;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.transition.TransitionManager;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.slider.Slider;

import java.util.ArrayList;

public class TaskCreateActivity extends AppCompatActivity implements View.OnClickListener {

    private ScrollView svCreateTask;

    private ImageView imgExit;

    private TextView txtPassMark;

    private EditText edtTxtTaskName, edtTxtReward;

    private Slider sliderPassMark;

    private Spinner spQuestionSets;

    private SwitchCompat swAddReward;

    private Button btnCreate;

    private RecyclerView rvTasks;

    private DatabaseHelper databaseHelper = new DatabaseHelper(this);

    private TaskCreateRecyclerAdapter taskCreateRecyclerAdapter = new TaskCreateRecyclerAdapter(this, databaseHelper);

    private int questionSetId = -1;

    private ArrayList<String> questionSetNames = new ArrayList<>();

    private ArrayList<Task> tasks = new ArrayList<>();

    private int passMark;

    //TODO: Task Item Needs Improvement

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_task);

        initViews();


        showIncompleteTasks();

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        // Reverse order since latest task should appear first:
        linearLayoutManager.setReverseLayout(true);

        rvTasks.setLayoutManager(linearLayoutManager);
        rvTasks.setAdapter(taskCreateRecyclerAdapter);
    }




    private void initViews() {
        txtPassMark = findViewById(R.id.txtPassMark);
        imgExit = findViewById(R.id.imgExit);
        edtTxtTaskName = findViewById(R.id.edtTxtTaskName);
        svCreateTask = findViewById(R.id.svCreateTask);
        sliderPassMark = findViewById(R.id.sliderPassMark);
        edtTxtReward = findViewById(R.id.edtTxtReward);
        swAddReward = findViewById(R.id.swAddReward);
        btnCreate = findViewById(R.id.btnCreate);
        svCreateTask = findViewById(R.id.svCreateTask);
        rvTasks = findViewById(R.id.rvTasks);
        spQuestionSets = findViewById(R.id.spQuestionSets);

        imgExit.setOnClickListener(this);
        btnCreate.setOnClickListener(this);

        txtPassMark.setText(String.format(getString(R.string.pass_Mark), passMark));
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

        sliderPassMark.setValue(0.0F);

        sliderPassMark.addOnChangeListener(new Slider.OnChangeListener() {
            @SuppressLint("RestrictedApi")
            @Override
            public void onValueChange(@NonNull Slider slider, float value, boolean fromUser) {

                passMark = (int) value;

                txtPassMark.setText(String.format(getString(R.string.pass_Mark), passMark));
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
        String[] inputs;

        if (swAddReward.isChecked()) {
            inputs = new String[]{edtTxtTaskName.getText().toString(),
                    edtTxtReward.getText().toString()};
        }
        else{
            inputs = new String[]{edtTxtTaskName.getText().toString()};
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
            //Hiding Keyboard
            InputMethodManager inputMethodManager = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(svCreateTask.getWindowToken(), 0);

            // Saving Task:
            Task task = new Task(questionSetId, Utils.getInstance().getUserAccount().getId(), edtTxtTaskName.getText().toString(), passMark, edtTxtReward.getText().toString(), spQuestionSets.getSelectedItemPosition(), false);
            databaseHelper.addTask(task);
            showIncompleteTasks();
            TransitionManager.beginDelayedTransition(svCreateTask);
        }
    }

    private void showIncompleteTasks() {
        // Finding the incomplete tasks to show
        this.tasks = new ArrayList<>();
        for (Task task: databaseHelper.getTasks()){
            if (!task.isCompleted()){
                this.tasks.add(task);
            }
        }

        taskCreateRecyclerAdapter.setTasks(tasks);
    }

    @Override
    public void onBackPressed(){
        // The back button should go to the main menu here:
        startActivity(new Intent(this, MainMenuActivity.class));
    }
}
