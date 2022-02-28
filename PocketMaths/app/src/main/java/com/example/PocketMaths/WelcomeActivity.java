package com.example.PocketMaths;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.PocketMaths.R;

public class WelcomeActivity extends AppCompatActivity implements View.OnClickListener {
    private Button btnSignUp, btnSignIn, btnContinueAsGuest;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        initViews();

    }

    private void initViews() {
        btnSignUp = findViewById(R.id.btnSignUp);
        btnSignIn = findViewById(R.id.btnSignIn);
        btnContinueAsGuest = findViewById(R.id.btnContinueAsGuest);

        btnSignUp.setOnClickListener(this);
        btnSignIn.setOnClickListener(this);
        btnContinueAsGuest.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            //TODO: Make The sign-up, sign-in get data from a database, emails should ideally work as well::

            // Go to the appropriate activity depending on the Button clicked.
            case (R.id.btnSignUp):
                startActivity(new Intent(this, SignUpActivity.class));
                break;

            case (R.id.btnSignIn):
                startActivity(new Intent(this, SignInActivity.class));
                break;

            case (R.id.btnContinueAsGuest):
                // Creating a guest account:
                Utils.getInstance().setUserAccount(new Account());
                startActivity(new Intent(this, MainMenuActivity.class));
                break;
            default:
                break;

        }

    }

}