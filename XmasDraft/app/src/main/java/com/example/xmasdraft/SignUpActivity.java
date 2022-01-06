package com.example.xmasdraft;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;

import java.util.Locale;

public class SignUpActivity extends SignActivityTemplate implements View.OnClickListener {

    private RelativeLayout relSignUp;

    private EditText edtTxtParentName, edtTxtStudentName, edtTxtEmail, edtTxtPassword, edtTxtConfirmPassword, edtTxtPin, edtTxtConfirmPin;

    private Button btnBack, btnConfirm;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        initViews();


    }

    private void initViews() {

        relSignUp = findViewById(R.id.relSignUp);

        edtTxtParentName = findViewById(R.id.edtTxtParentName);
        edtTxtStudentName = findViewById(R.id.edtTxtStudentName);
        edtTxtEmail = findViewById(R.id.edtTxtEmail);
        edtTxtPassword = findViewById(R.id.edtTxtPassword);
        edtTxtConfirmPassword = findViewById(R.id.edtTxtConfirmPassword);
        edtTxtPin = findViewById(R.id.edtTxtPin);
        edtTxtConfirmPin = findViewById(R.id.edtTxtConfirmPin);

        btnBack = findViewById(R.id.btnBack);
        btnConfirm = findViewById(R.id.btnConfirm);

        btnBack.setOnClickListener(this);
        btnConfirm.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){

            case (R.id.btnBack):
                startActivity(new Intent(this, WelcomeActivity.class));
                break;

            case (R.id.btnConfirm):

                // The inputs are converted from View objects to Strings:

                // The names should be uppercase:
                String parentName = edtTxtParentName.getText().toString().toUpperCase(Locale.ROOT);
                String studentName = edtTxtStudentName.getText().toString().toUpperCase(Locale.ROOT);
                // The email should be lowercase
                String email =edtTxtEmail.getText().toString().toLowerCase(Locale.ROOT);
                String password = edtTxtPassword.getText().toString();
                String confirmPassword = edtTxtConfirmPassword.getText().toString();
                String pin = edtTxtPin.getText().toString();
                String confirmPin = edtTxtConfirmPin.getText().toString();


                /* Classifying the inputs depending on how many characters they need to have
                * so that they can be validated accordingly
                */
                String[] fieldsMinLength_2 = {parentName, studentName};

                String[] fieldsMinLength_4 = {pin, confirmPin};

                String[] fieldsMinLength_6 = {email, password, confirmPassword};


                /* The functions below will produce a warning text depending on the error, and
                * if there is none, they will return an empty string
                * This can be used to determine the error and display an appropriate SnackBar.
                */
                String[] warningTexts = {
                        inputLengthMessage(fieldsMinLength_2, 2),
                        inputLengthMessage(fieldsMinLength_4, 4),
                        inputLengthMessage(fieldsMinLength_6, 6),
                        inputsMatchMessage(password, confirmPassword, "Passwords"),
                        inputsMatchMessage(pin, confirmPin, "Pins"),
                        containsMessage(email, "@", "Email"),
                        containsMessage(email, ".", "Email")
                };

                String warning = checkForWarning(warningTexts);

                if (warning != null) {
                    showSnackBar(relSignUp, warning, "OK");
                    break;
                }


                // We are creating an account:
                Account currentUserAccount = new Account(parentName, studentName, email, password, pin);

                // Going to the main menu activity:
                startActivity(new Intent(this, MainMenuActivity.class));
                break;


            default:
                break;

        }

    }
}
