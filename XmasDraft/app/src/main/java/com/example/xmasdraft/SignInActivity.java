package com.example.xmasdraft;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.snackbar.Snackbar;

public class SignInActivity extends AppCompatActivity implements View.OnClickListener {
    private RelativeLayout relSignIn;

    private EditText edtTxtEmail, edtTxtPassword;

    private Button btnForgotPassword, btnBack, btnConfirm;

    EditText[] inputs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        initViews();

    }

    /**
     * Initialising View objects:
     */
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

        // Placing Edit Texts in array:
        inputs = new EditText[]{edtTxtEmail, edtTxtPassword};

    }


    @Override
    public void onClick(View v) {
        // Switch Case for determining which View has been pressed
        switch (v.getId()) {

            case (R.id.btnBack):
                startActivity(new Intent(this, WelcomeActivity.class));
                break;


            case (R.id.btnForgotPassword):
                if (isValidEmail(edtTxtEmail.getText().toString())) {
                    showSnackBar(relSignIn, "Email Sent", "OK");
                }
                else{
                    showSnackBar(relSignIn, "Check The Email", "OK");
                }
                break;

            case (R.id.btnConfirm):
               confirmClicked();
                break;

            default:
                break;

        }
    }

    /**
     * Does the necessary validations when the Confirm Button is clicked:
     */
    private void confirmClicked() {
        // Displays appropriate Snack Bar if inputs are invalid:
        if (!inputsFilled(inputs)){
           showSnackBar(relSignIn, "Please Fill In All The Fields", "OK");
        }

        else if (!isValidEmail(edtTxtEmail.getText().toString())){
            showSnackBar(relSignIn,"Check The Email", "OK");
        }
        else if (!isValid(edtTxtPassword.getText().toString(), 6)) {
            showSnackBar(relSignIn, "Check The Password", "OK");
        }
        else{
            showSnackBar(relSignIn, "User Not Found", "OK");
        }
    }


    /**
     *Checks if an array containing inputs is filled:
     * @param inputs Array of EditTexts
     * @return Whether or not all of the inputs have been filled in
     */
    public boolean inputsFilled(EditText[] inputs) {
        for (EditText input: inputs){
            if (input.getText().toString().isEmpty()){
                return false;
            }
        }
        return true;
    }


    /**
     * Checks if minimum length is matched
     * @param text The text to be validated
     * @param minLength The minimum length of the text
     * @return Whether the input is valid
     */
    public boolean isValid(String text, int minLength) {
        return text.length() >= minLength;

    }


    /**
     * Checks if email is valid
     * @param target The input
     * @return Whether or not the input is an Email
     */
    public boolean isValidEmail(CharSequence target) {
        return target != null && android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();
    }

    /**
     * Shows a Snack Bar
     * @param layout The layout
     * @param mainText The main text
     * @param actionText The action text
     */
    public void showSnackBar(ViewGroup layout, String mainText, String actionText) {

        // Shows Snack Bar:
        Snackbar.make(layout, mainText, Snackbar.LENGTH_LONG)
                .setAction(actionText, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                })
                .setActionTextColor(getResources().getColor(R.color.Accent))
                .setTextColor(getResources().getColor(R.color.Accent))
                .setBackgroundTint(getResources().getColor(R.color.Menu))
                .show();
    }

}
