package com.example.registrationchallenge;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onBtnClick (View view) {
        TextView displayInfo = findViewById(R.id.infoDisplayer);
        EditText firstNameTxtEdit = findViewById(R.id.firstNameTxtEdit);
        EditText lastNameTxtEdit = findViewById(R.id.lastNameTxtEdit);
        EditText emailTxtEdit = findViewById(R.id.emailTxtEdit);

        displayInfo.setText("Your Details:" + "\n" + "First Name: " + firstNameTxtEdit.getText().toString() + "\n" + "Last Name: " + lastNameTxtEdit.getText().toString() + "\n" + "Email: " + emailTxtEdit.getText().toString());


    }
}
