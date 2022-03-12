package com.example.PocketMaths;

import static com.example.PocketMaths.Account.Member;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ChangeDetailsActivity extends AppCompatActivity implements View.OnClickListener {

    private RelativeLayout relChangeDetails;

    private TextView txtChangeDetails;

    private EditText edtTxtParentName, edtTxtStudentName, edtTxtEmail, edtTxtPassword, edtTxtConfirmPassword, edtTxtPin, edtTxtConfirmPin;

    private Button btnCancel, btnSaveChanges;

    private EditText[] inputs;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_details);

        initViews();
        setData();

    }

    private void initViews() {
        relChangeDetails = findViewById(R.id.relChangeDetails);
        txtChangeDetails = findViewById(R.id.txtChangeDetails);
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

        inputs = new EditText[]{edtTxtParentName, edtTxtStudentName, edtTxtEmail, edtTxtPassword, edtTxtConfirmPassword, edtTxtPin, edtTxtConfirmPin};


    }

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


    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case (R.id.btnCancel):
                startActivity(new Intent(this, AccountActivity.class));
                break;

            case (R.id.btnSaveChanges):
                saveChangesClicked();
                break;
        }


    }

    private void saveChangesClicked() {
        // Displays appropriate Snack Bar if inputs are invalid:
        if (!Utils.getInstance().inputsFilled(inputs)){
            Utils.getInstance().showSnackBar(this,relChangeDetails, getString(R.string.empty_inputs), getString(R.string.ok));
        }
        else if (!Utils.getInstance().isValid(new EditText[]{edtTxtParentName, edtTxtStudentName}, 2)) {
            Utils.getInstance().showSnackBar(this,relChangeDetails, getString(R.string.input_lengths), getString(R.string.ok));
        }
        else if (!Utils.getInstance().isValid(new EditText[]{edtTxtPassword}, 6)){
            Utils.getInstance().showSnackBar(this,relChangeDetails,getString(R.string.check_password), getString(R.string.ok));
        }
        else if (!Utils.getInstance().isValidEmail(edtTxtEmail.getText().toString())){
            Utils.getInstance().showSnackBar(this,relChangeDetails,getString(R.string.check_email), getString(R.string.ok));
        }
        else if (!edtTxtPassword.getText().toString().equals(edtTxtConfirmPassword.getText().toString())){
            Utils.getInstance().showSnackBar(this,relChangeDetails, getString(R.string.passwords_do_not_match), getString(R.string.ok));
        }
        else if (!Utils.getInstance().isValid(new EditText[]{edtTxtPin}, 4)){
            Utils.getInstance().showSnackBar(this,relChangeDetails, getString(R.string.pin_length), getString(R.string.ok));
        }
        else if (!edtTxtPin.getText().toString().equals(edtTxtConfirmPin.getText().toString())){
            Utils.getInstance().showSnackBar(this,relChangeDetails, getString(R.string.pins_do_not_match), getString(R.string.ok));
        }
        else{
            updateAccount();
        }
    }

    private void updateAccount(){

        Account account = Utils.getInstance().getUserAccount();

            account.setParentName(edtTxtParentName.getText().toString());
            account.setStudentName(edtTxtStudentName.getText().toString());
            account.setEmail(edtTxtEmail.getText().toString());
            account.setPassword(edtTxtPassword.getText().toString());
            account.setPin(edtTxtPin.getText().toString());
            account.setAccountType(Member);
        // Going to the account activity:
        startActivity(new Intent(this, AccountActivity.class));
        }
}
