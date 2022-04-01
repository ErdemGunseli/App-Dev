package com.example.PocketMaths;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class SettingsActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView imgExit;

    private RecyclerView rvThemes;

    private SwitchCompat swShowRefreshers;

    private DatabaseHelper databaseHelper = new DatabaseHelper(this);

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(Utils.getInstance().getThemeId());
        setContentView(R.layout.activity_settings);

        initViews();

        setData();

        // Initialising Adapter
        ThemeRecyclerAdapter themeRecyclerAdapter = new ThemeRecyclerAdapter(this);


        // Setting the adapter to the recycler view
        rvThemes.setAdapter(themeRecyclerAdapter);

        // We also need to set a layout manager for our Recycler View:
        rvThemes.setLayoutManager((new GridLayoutManager(this, 2)));


    }


    private void initViews() {
        rvThemes = findViewById(R.id.rvThemes);
        imgExit = findViewById(R.id.imgExit);
        swShowRefreshers = findViewById(R.id.swShowRefreshers);

        imgExit.setOnClickListener(this);
        swShowRefreshers.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean checked) {
                Utils.getInstance().setShowRefreshers(checked);
                databaseHelper.setShowRefreshers(checked);
            }
        });
    }


    private void setData() {
        swShowRefreshers.setChecked(Utils.getInstance().refreshersEnabled());
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()){

            case (R.id.imgExit):
                // We cannot simply finish as if the theme has been changed,
                // the previous activity must be restarted.
                startActivity(new Intent(this, MainMenuActivity.class));
                break;

            default:
                break;
        }
    }
    @Override
    public void onBackPressed(){
        // Go to the main menu to prevent accidental changing of themes
        // when the back button is pressed
        startActivity(new Intent(this, MainMenuActivity.class));
    }

}
