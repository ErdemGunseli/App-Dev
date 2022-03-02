package com.example.PocketMaths;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import java.util.Objects;

public class PinVerificationActivity extends AppCompatActivity implements View.OnClickListener{

    private RelativeLayout relPinVerification;

    private TextView txtPin;

    private Button btnContinue, btnCancel;

    private EditText edtTxtPin;

    private Intent target;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parental_verification);

        initViews();

    }

    private void initViews() {
        relPinVerification = findViewById(R.id.relPinVerification);
        txtPin = findViewById(R.id.txtPin);
        btnContinue = findViewById(R.id.btnContinue);
        btnCancel = findViewById(R.id.btnCancel);
        edtTxtPin = findViewById(R.id.edtTxtPin);

        btnContinue.setOnClickListener(this);
        btnCancel.setOnClickListener(this);
    }




    @Override
    public void onClick(View view) {

        switch (view.getId()){

            case (R.id.btnContinue):
                checkPin();
                break;

            case (R.id.btnCancel):
                onBackPressed();
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
