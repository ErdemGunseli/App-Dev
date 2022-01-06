package com.example.xmasdraft;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;

import java.util.Locale;

public class SignInActivity extends SignActivityTemplate implements View.OnClickListener {
    private RelativeLayout relSignIn;

    private EditText edtTxtEmail, edtTxtPassword;

    private Button btnForgotPassword, btnBack,btnConfirm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        initViews();

    }

    private void initViews() {
        relSignIn = findViewById(R.id.relSignIn);
        edtTxtEmail = findViewById(R.id.edtTxtEmail);
        edtTxtPassword = findViewById(R.id.edtTxtPassword);
        btnForgotPassword = findViewById(R.id.btnForgotPassword);
        btnConfirm = findViewById(R.id.btnConfirm);
        btnBack = findViewById(R.id.btnBack);


        btnForgotPassword.setOnClickListener(this);
        btnBack.setOnClickListener(this);
        btnConfirm.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        // Switch Case for determining which View has been pressed
        switch (v.getId()){

            case (R.id.btnBack):
                startActivity(new Intent(this, WelcomeActivity.class));
                break;


            case (R.id.btnForgotPassword):
                    showSnackBar(relSignIn,"Email Sent", "OK");
                break;

            case (R.id.btnConfirm):
                // Series of Validation

                // The email should be lowercase:
                String email = edtTxtEmail.getText().toString().toLowerCase(Locale.ROOT);
                String password = edtTxtPassword.getText().toString();

                // Strings stored here can have a minimum length of 6 characters:
                String[] fieldsMinLength_6 = {email, password};

                String[] warningTexts = {
                        inputLengthMessage(fieldsMinLength_6, 6),
                        containsMessage(email, "@", "Email"),
                        containsMessage(email, ".", "Email")
                };


                String warning = checkForWarning(warningTexts);

                if (warning != null){
                    showSnackBar(relSignIn, warning, "OK");
                    break;
                }

                showSnackBar(relSignIn, "User Not Found", "OK");

            default:
                break;

        }
    }




}
