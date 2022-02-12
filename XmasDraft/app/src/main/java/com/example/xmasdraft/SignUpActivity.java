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

import java.util.Locale;

public class SignUpActivity extends AppCompatActivity implements View.OnClickListener {

    private RelativeLayout relSignUp;

    private EditText edtTxtParentName, edtTxtStudentName, edtTxtEmail, edtTxtPassword, edtTxtConfirmPassword, edtTxtPin, edtTxtConfirmPin;

    private Button btnBack, btnConfirm;

    EditText[] inputs;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        initViews();


    }
    /**
     * Initialising View objects:
     */
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

        inputs = new EditText[]{edtTxtParentName, edtTxtStudentName, edtTxtEmail, edtTxtPassword, edtTxtConfirmPassword, edtTxtPin, edtTxtConfirmPin};
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){

            case (R.id.btnBack):
                startActivity(new Intent(this, WelcomeActivity.class));
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
    private void confirmClicked(){
        // Displays appropriate Snack Bar if inputs are invalid:
        if (!inputsFilled(inputs)){
            showSnackBar(relSignUp, getString(R.string.empty_fields), getString(R.string.ok));
        }
        else if (!isValid(new EditText[]{edtTxtParentName, edtTxtStudentName}, 2)) {
            showSnackBar(relSignUp, getString(R.string.input_lengths), getString(R.string.ok));
        }
        else if (!isValid(new EditText[]{edtTxtPassword}, 6)){
            showSnackBar(relSignUp,getString(R.string.check_password), getString(R.string.ok));
        }
        else if (!isValidEmail(edtTxtEmail.getText().toString())){
            showSnackBar(relSignUp,getString(R.string.check_email), getString(R.string.ok));
        }
        else if (!edtTxtPassword.getText().toString().equals(edtTxtConfirmPassword.getText().toString())){
            showSnackBar(relSignUp, getString(R.string.passwords_do_not_match), getString(R.string.ok));
        }
        else if (!isValid(new EditText[]{edtTxtPin}, 4)){
            showSnackBar(relSignUp, getString(R.string.pin_length), getString(R.string.ok));
        }
        else if (!edtTxtPin.getText().toString().equals(edtTxtConfirmPin.getText().toString())){
            showSnackBar(relSignUp, getString(R.string.pins_do_not_match), getString(R.string.ok));
        }
        else{
            // We are declaring an account depending on the data entered by the user:
            // TODO: Use This!!
            Account userAccount = new Account(edtTxtParentName.getText().toString(),
                    edtTxtStudentName.getText().toString(), edtTxtEmail.getText().toString(),
                    edtTxtPassword.getText().toString(),
                    edtTxtPin.getText().toString());

            // Setting the account:
            Utils.getInstance().setUserAccount(userAccount);

            // Going to the main menu activity:
            startActivity(new Intent(this, MainMenuActivity.class));
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
     * Checks if an input array of Edit Texts is valid:
     * @param inputs Array of input Edit Texts
     * @param minLength Minimum length that each input can take
     * @return Whether all the inputs in the array are valid
     */
    public boolean isValid(EditText[] inputs, int minLength) {

        for (EditText input: inputs){
            if (input.getText().toString().length() < minLength){
                return false;
            }
        }
        return true;

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
