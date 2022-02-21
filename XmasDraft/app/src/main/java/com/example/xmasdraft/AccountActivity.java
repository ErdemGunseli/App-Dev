package com.example.xmasdraft;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Objects;

public class AccountActivity extends AppCompatActivity implements View.OnClickListener{

    private Account account = Utils.getInstance().getUserAccount();

    private RecyclerView rvAccountHistory;

    private TextView txtDetailsText, txtDetails;

    private Button btnTryQuestions, btnSignUp, btnSignIn;



    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);

        initViews();
        
        setData();

        AccountHistoryRecyclerAdapter accountHistoryRecyclerAdapter = new AccountHistoryRecyclerAdapter(this);
        accountHistoryRecyclerAdapter.setQuestionSets(account.getQuestionSetsCompleted());

        // Setting adapter to recycler view:
        rvAccountHistory.setAdapter(accountHistoryRecyclerAdapter);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL, false);
        // Reverse order since last question set done should appear first:
        linearLayoutManager.setReverseLayout(true);

        rvAccountHistory.setLayoutManager(linearLayoutManager);

        // Scrolling to the bottom of the array, top of the Recycler View:
        rvAccountHistory.scrollToPosition(account.getQuestionSetsCompleted().size() - 1);

        // If no question sets have been completed yet:
        if (Objects.requireNonNull(rvAccountHistory.getAdapter()).getItemCount() == 0){
            // Show a button that takes them to the question sets and hide recycler view.
            rvAccountHistory.setVisibility(View.GONE);
            btnTryQuestions.setVisibility(View.VISIBLE);

        }
        else{
            // Reverse of above:
            rvAccountHistory.setVisibility(View.VISIBLE);
            btnTryQuestions.setVisibility(View.GONE);
        }

    }

    private void setData() {
        Account account = Utils.getInstance().getUserAccount();

        if (account.getAccountType().equals("Guest")){
            // If they have not made an account, we cannot display their details.
            txtDetails.setVisibility(View.GONE);

            // Show them the sign-up / sign-in buttons:
            btnSignUp.setVisibility(View.VISIBLE);
            btnSignIn.setVisibility(View.VISIBLE);

        }
        else if (account.getAccountType().equals("Member")){

            // If they have made an account, display their details.
            txtDetails.setVisibility(View.VISIBLE);

            // Hide the sign-up / sign-in buttons:
            btnSignUp.setVisibility(View.GONE);
            btnSignIn.setVisibility(View.GONE);


            // Set data to the details textview:

            txtDetails.append(
                    getString(R.string.your_name) + account.getParentName() + "\n" +
                    getString(R.string.student_name) + account.getStudentName() + "\n" +
                    getString(R.string.email) + account.getEmail() + "\n"
            );

            // TODO: Change Password, Change Pin

        }

    }

    private void initViews() {
        rvAccountHistory = findViewById(R.id.rvAccountHistory);
        txtDetailsText = findViewById(R.id.txtDetailsText);
        txtDetails = findViewById(R.id.txtDetails);
        btnTryQuestions = findViewById(R.id.btnTryQuestions);
        btnSignUp = findViewById(R.id.btnSignUp);
        btnSignIn = findViewById(R.id.btnSignIn);

        btnTryQuestions.setOnClickListener(this);
        btnSignUp.setOnClickListener(this);
        btnSignIn.setOnClickListener(this);

    }

    

    @Override
    public void onClick(View view) {

        switch (view.getId()){

            case (R.id.btnTryQuestions):
                startActivity(new Intent(this, MainMenuActivity.class));
                break;

            case (R.id.btnSignUp):
                startActivity(new Intent(this, SignUpActivity.class));
                break;

            case (R.id.btnSignIn):
                startActivity(new Intent(this, SignInActivity.class));
                break;
        }

    }
}
