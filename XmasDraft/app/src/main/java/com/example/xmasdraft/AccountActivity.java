package com.example.xmasdraft;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class AccountActivity extends AppCompatActivity implements View.OnClickListener{

    private Account account = Utils.getInstance().getUserAccount();

    private TextView txtTest;



    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);

        initViews();

        setData();

    }

    private void initViews() {
        txtTest = findViewById(R.id.txtTest);
    }

    private void setData(){
        ArrayList<QuestionSet> questionSets = account.getQuestionSetsCompleted();

        for (QuestionSet questionSet: questionSets){
            String name = questionSet.getName();
            txtTest.append("\n" + name);
        }

    }

    @Override
    public void onClick(View view) {

    }
}
