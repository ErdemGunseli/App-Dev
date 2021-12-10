package com.example.first_app;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

//TODO: KEEP GOING


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) { // The logic in here only happens when the application is first started.
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); // This java class is related to the activity_main layout file.


        // In java, we have classes equivalent to the UI elements in our layout file.
        // Writing TextView has imported TextView from the widget package into our java file.
        // The name of the TextView we have declared is txtHello.
        // findViewById is a method to find user interface methods by their ID. We need to pass in the ID of the element as a parameter.
        // R is a special class in java which gives us access to all of our resources, static files in our project.
        // The setText method sets the text displayed by the UI element. It accepts a string as the parameter.


        //TextView txtHello = findViewById(R.id.txtMessage);
        //txtHello.setText("Hello");

        // The toString method converts the text to string.
        // We want to access our editTxtName in our onBtnClick function but the scope of this variable is within the onCreate method. So we need to move our logic into the onBtnclick method.

        // EditText editTxtName = findViewById(R.id.edtTxtName);
        // editTxtName.getText().toString();

    }

    //To change the text of our TextView txtHello when we press the button, we need an event listener and an on-click listener.
    public void onBtnClick (View view) {
        TextView txtHello = findViewById(R.id.txtMessage);
        EditText editTxtName = findViewById(R.id.editTxtName);
        txtHello.setText("Hello " + editTxtName.getText().toString());

    }
}
