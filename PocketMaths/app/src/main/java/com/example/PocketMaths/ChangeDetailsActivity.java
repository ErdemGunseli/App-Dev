package com.example.PocketMaths;

import static com.example.PocketMaths.Account.Member;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Locale;

/**
 * This activity relates to the Change Details Page of the app.
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * ChangeDetailsActivity extends AppCompatActivity class to have access to Activity methods.
 * ChangeDetailsActivity implements View.OnCLickListener interface to detect touch input.
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * This activity is never started directly, only set as a target and started through PinVerificationActivity.
 * It displays all of the user account details of the instance of Account set as active.
 * Each field can be changed, and saving these changes results in the Account object being changed.
 * The relevant record in the Accounts table is updated.
 */

public class ChangeDetailsActivity extends AppCompatActivity implements View.OnClickListener {

    private String[] inputs;

    private DatabaseHelper databaseHelper = new DatabaseHelper(this);

    private RelativeLayout relChangeDetails;
    private EditText edtTxtParentName, edtTxtStudentName, edtTxtEmail, edtTxtPassword, edtTxtConfirmPassword, edtTxtPin, edtTxtConfirmPin;
    private Button btnCancel, btnSaveChanges;


    /**
     * Overrides the onCreate method of the super class.
     * Runs when ChangeDetailsActivity starts.
     * Checks and applies the user's chosen theme.
     * Uses the XML layout file 'activity_change_details'.
     * Calls initViews()
     * Calls setData()
     * @param savedInstanceState Required for super constructor.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(Utils.getInstance().getThemeId());
        setContentView(R.layout.activity_change_details);

        initViews();

        setData();

    }

    /**
     * Initialises View objects.
     * Sets the activity's click listener to appropriate View objects.
     */
    private void initViews() {
        relChangeDetails = findViewById(R.id.relChangeDetails);
        edtTxtParentName = findViewById(R.id.edtTxtParentName);
        edtTxtStudentName = findViewById(R.id.edtTxtStudentName);
        edtTxtEmail = findViewById(R.id.edtTxtEmail);
        edtTxtPassword = findViewById(R.id.edtTxtPassword);
        edtTxtConfirmPassword = findViewById(R.id.edtTxtConfirmPassword);
        edtTxtPin = findViewById(R.id.edtTxtPin);
        edtTxtConfirmPin = findViewById(R.id.edtTxtConfirmPin);

        btnCancel = findViewById(R.id.btnCancel);
        btnSaveChanges = findViewById(R.id.btnSaveChanges);

        btnCancel.setOnClickListener(this);
        btnSaveChanges.setOnClickListener(this);

    }

    /**
     * Sets user account details to the appropriate View object.
     */
    private void setData() {
        Account account = Utils.getInstance().getUserAccount();

        edtTxtParentName.setText(account.getParentName());
        edtTxtStudentName.setText(account.getStudentName());
        edtTxtEmail.setText(account.getEmail());
        edtTxtPassword.setText(account.getPassword());
        edtTxtConfirmPassword.setText(account.getPassword());
        edtTxtPin.setText(account.getPin());
        edtTxtConfirmPin.setText(account.getPin());

    }

    /**
     * Determines which View object has been clicked and performs the appropriate action.
     * @param view Used to determine the View object clicked.
     */
    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case (R.id.btnCancel):
                startActivity(new Intent(this, AccountActivity.class));
                break;

            case (R.id.btnSaveChanges):
                if (inputsValid()){updateAccount();}
                break;
        }


    }

    /**
     * Checks if each input is valid.
     * A valid input must be of the correct type, contain necessary characters (if applicable),
     * and be no shorter than the minimum length or longer than the maximum length.
     * Maximum lengths have been set in the layout file, so no need to check for that.
     * Shows an appropriate message if any input is invalid.
     * @return Whether the inputs are valid.
     */
    private boolean inputsValid() {

        boolean validity = false;

        inputs = new String[]{edtTxtParentName.getText().toString(),
                edtTxtStudentName.getText().toString(),
                edtTxtEmail.getText().toString(),
                edtTxtPassword.getText().toString(),
                edtTxtConfirmPassword.getText().toString(),
                edtTxtPin.getText().toString(),
                edtTxtConfirmPin.getText().toString()};

        // Displaying appropriate Snack Bar if inputs are invalid:
        if (!Utils.getInstance().inputsFilled(inputs)){
            Utils.getInstance().showSnackBar(this,relChangeDetails, getString(R.string.empty_inputs), getString(R.string.ok));
        }
        else if (!Utils.getInstance().isValid(new String[]{edtTxtParentName.getText().toString(), edtTxtStudentName.getText().toString()}, 2)) {
            Utils.getInstance().showSnackBar(this,relChangeDetails, getString(R.string.input_lengths), getString(R.string.ok));
        }
        else if (!Utils.getInstance().isValid(new String[]{edtTxtPassword.getText().toString()}, 6)){
            Utils.getInstance().showSnackBar(this,relChangeDetails,getString(R.string.check_password), getString(R.string.ok));
        }
        else if (!Utils.getInstance().isValidEmail(edtTxtEmail.getText().toString())){
            Utils.getInstance().showSnackBar(this,relChangeDetails,getString(R.string.check_email), getString(R.string.ok));
        }
        else if (!edtTxtPassword.getText().toString().equals(edtTxtConfirmPassword.getText().toString())){
            Utils.getInstance().showSnackBar(this,relChangeDetails, getString(R.string.passwords_do_not_match), getString(R.string.ok));
        }
        else if (!Utils.getInstance().isValid(new String[]{edtTxtPin.getText().toString()}, 4)){
            Utils.getInstance().showSnackBar(this,relChangeDetails, getString(R.string.pin_length), getString(R.string.ok));
        }
        else if (!edtTxtPin.getText().toString().equals(edtTxtConfirmPin.getText().toString())){
            Utils.getInstance().showSnackBar(this,relChangeDetails, getString(R.string.pins_do_not_match), getString(R.string.ok));
        }
        else{
            // If all checks are passed, the input is valid:
            validity = true;
        }

        return validity;
    }

    /**
     * Retrieves data from each of the EditText objects.
     * Sets this data as the relevant attributes of the instance of Account set as active.
     * Updates this account in the relational database.
     * Starts the relevant activity.
     */
    private void updateAccount(){

        Account account = Utils.getInstance().getUserAccount();

        account.setParentName(edtTxtParentName.getText().toString().toUpperCase(Locale.ROOT));
        account.setStudentName(edtTxtStudentName.getText().toString().toUpperCase(Locale.ROOT));
        account.setEmail(edtTxtEmail.getText().toString().toLowerCase(Locale.ROOT));
        account.setPassword(edtTxtPassword.getText().toString());
        account.setPin(edtTxtPin.getText().toString());
        account.setAccountType(Member);

        databaseHelper.updateAccount(account);

        startActivity(new Intent(this, AccountActivity.class));
        finish();
        }
}
