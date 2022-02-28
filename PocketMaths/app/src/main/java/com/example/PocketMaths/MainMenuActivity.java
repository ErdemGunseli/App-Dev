package com.example.PocketMaths;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.PocketMaths.R;

import java.util.ArrayList;
import java.util.Locale;


public class MainMenuActivity extends AppCompatActivity implements View.OnClickListener {

    private RecyclerView rvMainMenu;

    private ImageView imgAccount, imgTasks, imgCreateTask, imgSettings;

    private SearchView svQuestionSet;

    // Declaring Adapter
    // Done here so it has the scope of the whole class:
    private MainMenuRecyclerAdapter mainMenuRecyclerAdapter;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        initViews();

        // Initialising Adapter
        mainMenuRecyclerAdapter = new MainMenuRecyclerAdapter(this);

        // Setting our Question Sets Array List from the Utils to the Adapter
        mainMenuRecyclerAdapter.setQuestionSets(Utils.getInstance().getQuestionSets());

        // Setting the adapter to the recycler view
        rvMainMenu.setAdapter(mainMenuRecyclerAdapter);

        // We also need to set a layout manager for our Recycler View:
        //Changing to Linear Layout Manager for the implementation of Expandable Card View:
        rvMainMenu.setLayoutManager((new LinearLayoutManager(this)));

    }

    private void initViews() {
        rvMainMenu = findViewById(R.id.rvMainMenu);

        imgAccount = findViewById(R.id.imgAccount);
        imgTasks = findViewById(R.id.imgTasks);
        imgCreateTask = findViewById(R.id.imgCreateTask);
        imgSettings = findViewById(R.id.imgSettings);
        svQuestionSet = findViewById(R.id.svQuestionSet);

        imgAccount.setOnClickListener(this);
        imgTasks.setOnClickListener(this);
        imgCreateTask.setOnClickListener(this);
        imgSettings.setOnClickListener(this);

        svQuestionSet.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                // Filtering the Question Sets based on the value of s:
                filterQuestionSets(s);
                return false;




            }
        });
    }

    public void filterQuestionSets(String targetText){

        ArrayList<QuestionSet> questionSetsToShow = new ArrayList<>();
        targetText = targetText.toUpperCase(Locale.ROOT);

        // Iterating through the Question Set:
        for (QuestionSet questionSet: Utils.getInstance().getQuestionSets()){
            // If the Question Set Name or Description contains the target text, include it:
            if (questionSet.getName().toUpperCase(Locale.ROOT).contains(targetText) ||
                    questionSet.getDescription().toUpperCase(Locale.ROOT).contains(targetText)) {
                questionSetsToShow.add(questionSet);
            }
        }

        // Setting the filtered array list of question sets:

        mainMenuRecyclerAdapter.setQuestionSets(questionSetsToShow);
    }

    @Override
    public void onClick(View v) {
        //TODO: Don't forget to make the main menu buttons functional::

        switch (v.getId()){

            case (R.id.imgAccount):
                // Starting the Account Activity:
                startActivity(new Intent(this, AccountActivity.class));
                break;

            case (R.id.imgTasks):
                Toast.makeText(MainMenuActivity.this, "Set Tasks Clicked", Toast.LENGTH_SHORT).show();
                break;

            case (R.id.imgCreateTask):
                Toast.makeText(MainMenuActivity.this, "New Task Clicked", Toast.LENGTH_SHORT).show();
                break;

            case (R.id.imgSettings):
                Toast.makeText(MainMenuActivity.this, "Settings Clicked", Toast.LENGTH_SHORT).show();
                break;

            default:
                break;


        }
    }


    @Override
    public void onBackPressed(){
        // The back button should not do anything here, so overriding into empty procedure.
    }
}
