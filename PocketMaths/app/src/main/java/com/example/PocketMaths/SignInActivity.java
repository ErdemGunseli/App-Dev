package com.example.PocketMaths;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;

import androidx.appcompat.app.AppCompatActivity;

import com.example.PocketMaths.R;

public class SignInActivity extends AppCompatActivity implements View.OnClickListener {
    private RelativeLayout relSignIn;

    private EditText edtTxtEmail, edtTxtPassword;

    private Button btnForgotPassword, btnBack, btnConfirm;

    private EditText[] inputs;

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
                // Go back to the previous activity:
                finish();
                break;


            case (R.id.btnForgotPassword):
                if (Utils.getInstance().isValidEmail(edtTxtEmail.getText().toString())) {
                    // Email sent if email is valid:
                    Utils.getInstance().showSnackBar(this, relSignIn, getString(R.string.email_sent), getString(R.string.ok));
                }
                else{
                    // If Email invalid, they need to check:
                    Utils.getInstance().showSnackBar(this, relSignIn, getString(R.string.check_email), getString(R.string.ok));
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
        if (!Utils.getInstance().inputsFilled(inputs)){
            // If all of the inputs are not filled, they need to check:
            Utils.getInstance().showSnackBar(this, relSignIn, getString(R.string.empty_fields), getString(R.string.ok));
        }

        else if (!Utils.getInstance().isValidEmail(edtTxtEmail.getText().toString())){
            // If Email invalid, they need to check:
            Utils.getInstance().showSnackBar(this, relSignIn,getString(R.string.check_email), getString(R.string.ok));
        }
        else if (!Utils.getInstance().isValid(new EditText[]{(EditText) edtTxtPassword.getText()}, 6)) {
            // If Password invalid, they need to check:
            Utils.getInstance().showSnackBar(this, relSignIn, getString(R.string.check_password), getString(R.string.ok));
        }
        else{
            // User not found:
            Utils.getInstance().showSnackBar(this, relSignIn, getString(R.string.user_not_found), getString(R.string.ok));
            // TODO: Here, I would access a server to check for the details but I do not have it:
        }
    }


}
