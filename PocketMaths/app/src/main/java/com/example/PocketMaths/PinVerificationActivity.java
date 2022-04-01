package com.example.PocketMaths;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

public class PinVerificationActivity extends AppCompatActivity implements View.OnClickListener{

    private RelativeLayout relPinVerification;

    private CardView cvPinVerification, cvSign;

    private TextView txtPin;

    private Button btnContinue, btnCancel, btnSignUp, btnSignIn, btnBack;

    private EditText edtTxtPin;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(Utils.getInstance().getThemeId());
        setContentView(R.layout.activity_pin_verification);

        initViews();

        // Deciding what to display depending on account type:
        Account account = Utils.getInstance().getUserAccount();

        if (account.getAccountType().equals(Account.Guest)){
            cvSign.setVisibility(View.VISIBLE);
            cvPinVerification.setVisibility(View.GONE);
        }
        else{
            cvSign.setVisibility(View.GONE);
            cvPinVerification.setVisibility(View.VISIBLE);
        }


    }

    private void initViews() {
        relPinVerification = findViewById(R.id.relPinVerification);
        cvPinVerification = findViewById(R.id.cvPinVerification);
        txtPin = findViewById(R.id.txtPin);
        btnContinue = findViewById(R.id.btnContinue);

        btnCancel = findViewById(R.id.btnCancel);
        btnBack = findViewById(R.id.btnBack);
        edtTxtPin = findViewById(R.id.edtTxtPin);

        cvSign = findViewById(R.id.cvSign);
        btnSignUp = findViewById(R.id.btnSignUp);
        btnSignIn = findViewById(R.id.btnSignIn);

        btnBack.setOnClickListener(this);
        btnContinue.setOnClickListener(this);
        btnCancel.setOnClickListener(this);
        btnSignUp.setOnClickListener(this);
        btnSignIn.setOnClickListener(this);



    }




    @Override
    public void onClick(View view) {

        switch (view.getId()){

            case (R.id.btnContinue):
                //Hiding Keyboard
                InputMethodManager inputMethodManager = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
                checkPin();
                break;

            case (R.id.btnBack):
            case (R.id.btnCancel):
                onBackPressed();
                break;

            case (R.id.btnSignUp):
                startActivity(new Intent(this, SignUpActivity.class));
                break;

            case (R.id.btnSignIn):
                startActivity(new Intent(this, SignInActivity.class));
                break;

            default:
                break;

        }

    }

    private void checkPin() {
        // Checking the pin entered by the user:
        if (Utils.getInstance().getUserAccount().checkPin(edtTxtPin.getText().toString())){
            startActivity(new Intent(this, Utils.getInstance().getTargetClass()));
        }
        else{
            Utils.getInstance().showSnackBar(this, relPinVerification, getString(R.string.incorrect_pin), getString(R.string.ok));

        }
    }
}
