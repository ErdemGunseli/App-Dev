package com.example.PocketMaths;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class RefresherActivity extends AppCompatActivity implements View.OnClickListener {

    public static String REFRESHER_ID = "refresherID";

    private Refresher refresher;

    private TextView txtRefresherModel;
    private ImageView imgRefresher;
    private Button btnContinue;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setTheme(Utils.getInstance().getThemeID());
        setContentView(R.layout.activity_refresher);

        // Initialising View Objects:
        initViews();

        // Setting data from the intent:
        setDataFromIntent();

    }

    private void setDataFromIntent() {
        // Getting Question Set from the Intent:
        Intent intent = getIntent();

        // If the intent is not null:
        if (intent != null) {

            // Get the extra data from the Intent:
            // If the value is set to null, it will default to -1:
            int refresherId = intent.getIntExtra(

                    REFRESHER_ID, -1);

            if (refresherId != -1) {
                // Finding our Question Set by ID:
                refresher = Utils.getInstance().getRefresherById(refresherId);

                if (refresher != null) {

                    // Setting the data from the Question Set to our View item:
                    setData(refresher);
                }
            }

        }
    }

    private void initViews() {
        txtRefresherModel = findViewById(R.id.txtRefresherModel);
        imgRefresher = findViewById(R.id.imgRefresher);
        btnContinue = findViewById(R.id.btnContinue);

        btnContinue.setOnClickListener(this);
    }


    private void setData(Refresher refresher){
        txtRefresherModel.setText(String.format(getString(R.string.refresher),refresher.getModel()));
        imgRefresher.setImageResource(refresher.getImageId());
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btnContinue){
            finish();
        }
    }
}
