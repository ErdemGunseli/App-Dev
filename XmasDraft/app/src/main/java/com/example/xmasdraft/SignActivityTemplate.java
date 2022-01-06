package com.example.xmasdraft;

import android.text.Layout;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.snackbar.Snackbar;

public class SignActivityTemplate extends AppCompatActivity {
    /* This class contains methods that are used for validation in the Sign In and Sign Up classes..
       Validation methods return Strings (an error message if requirements are not met, and null
       if they are), which may need to be changed.
       TODO: Needs to be fundamentally restructured
     */
    public void showSnackBar(ViewGroup layout, String mainText, String actionText) {

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


    public String inputLengthMessage(String[] fields, int minimumInputLength) {

        for (int i = 0; i < fields.length; i++) {

            if (fields[i].isEmpty()) {
                return "Please Fill In All The Fields";
            } else if (minimumInputLength > 1 && fields[i].length() < minimumInputLength) {
                return "Please Check The Lengths Of The Fields";
            }

        }

        return null;
    }

    public String inputsMatchMessage(String input1, String input2, String type) {

        if (!input1.equals(input2)) {
            return "The " + type + " Do Not Match";
        }
        return null;

    }

    public String containsMessage(String input, String item, String type) {

        if (!input.contains(item)) {
            return "Check The " + type;
        }
        return null;

    }

    public String checkForWarning(String[] array){


        for (int i = 0; i < array.length; i++) {

            // if there is an error, produce, return appropriate message:
            if (array[i] != null) {
                return array[i];
            }
        }
        return null;

    }





}
