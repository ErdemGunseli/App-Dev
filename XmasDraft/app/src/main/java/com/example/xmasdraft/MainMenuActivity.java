package com.example.xmasdraft;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MainMenuActivity extends AppCompatActivity implements View.OnClickListener {
    private RecyclerView mainMenuRecyclerView;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        initViews();




        // Initialising Adapter
        MainMenuRecyclerAdapter mainMenuRecyclerAdapter = new MainMenuRecyclerAdapter(this);

        // Setting our Question Sets Array List from the Utils to the Adapter
        mainMenuRecyclerAdapter.setQuestionSets(Utils.getInstance().getQuestionSets());

        // Setting the adapter to the recycler view
        mainMenuRecyclerView.setAdapter(mainMenuRecyclerAdapter);

        // We also need to set a layout manager for our Recycler View:

        // We are using the Grid Layout Manager
         // mainMenuRecyclerView.setLayoutManager(new GridLayoutManager(this, 2));

        //Changing to Linear Layout Manager for the implementation of Expandable Card View:

       mainMenuRecyclerView.setLayoutManager((new LinearLayoutManager(this)));
    }

    private void initViews() {
        mainMenuRecyclerView = findViewById(R.id.mainMenuRecyclerView);
    }

    @Override
    public void onClick(View v) {

    }
}
