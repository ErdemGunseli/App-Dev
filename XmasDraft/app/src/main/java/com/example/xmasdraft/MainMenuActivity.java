package com.example.xmasdraft;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


public class MainMenuActivity extends AppCompatActivity implements View.OnClickListener {
    private RecyclerView rvMainMenu;

    private ImageView imgAccount, imgTasks, imgCreateTask, imgSettings;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        initViews();




        // Initialising Adapter
        MainMenuRecyclerAdapter mainMenuRecyclerAdapter = new MainMenuRecyclerAdapter(this);

        // Setting our Question Sets Array List from the Utils to the Adapter
        mainMenuRecyclerAdapter.setQuestionSets(Utils.getInstance().getQuestionSets());

        // Setting the adapter to the recycler view
        rvMainMenu.setAdapter(mainMenuRecyclerAdapter);

        // We also need to set a layout manager for our Recycler View:

        // We are using the Grid Layout Manager
         // mainMenuRecyclerView.setLayoutManager(new GridLayoutManager(this, 2));

        //Changing to Linear Layout Manager for the implementation of Expandable Card View:

        rvMainMenu.setLayoutManager((new LinearLayoutManager(this)));
    }

    private void initViews() {
        rvMainMenu = findViewById(R.id.rvMainMenu);

        imgAccount = findViewById(R.id.imgAccount);
        imgTasks = findViewById(R.id.imgTasks);
        imgCreateTask = findViewById(R.id.imgCreateTask);
        imgSettings = findViewById(R.id.imgSettings);

        imgAccount.setOnClickListener(this);
        imgTasks.setOnClickListener(this);
        imgCreateTask.setOnClickListener(this);
        imgSettings.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        //TODO: Temporary, change!

        switch (v.getId()){

            case (R.id.imgAccount):
                Toast.makeText(MainMenuActivity.this, "Account Clicked", Toast.LENGTH_SHORT).show();
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
}
