package com.example.PocketMaths;

import static com.example.PocketMaths.Account.Guest;
import static com.example.PocketMaths.Account.Member;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.PocketMaths.R;

import java.util.Objects;

public class AccountActivity extends AppCompatActivity implements View.OnClickListener{

    private Account account = Utils.getInstance().getUserAccount();

    private RecyclerView rvAccountHistory;

    private TextView txtDetailsText, txtDetails;

    private Button btnTryQuestions, btnChangeDetails, btnSignOut, btnSignUp, btnSignIn;

    private ImageView imgExit;

    //TODO: Display Account Details Better

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);

        initViews();
        
        setData();

        DatabaseHelper databaseHelper = new DatabaseHelper(this);

        AccountHistoryRecyclerAdapter accountHistoryRecyclerAdapter = new AccountHistoryRecyclerAdapter(this);
        accountHistoryRecyclerAdapter.setQuestionSets(databaseHelper.getQuestionSetResults());

        // Setting adapter to recycler view:
        rvAccountHistory.setAdapter(accountHistoryRecyclerAdapter);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL, false);
        // Reverse order since last question set done should appear first:
        linearLayoutManager.setReverseLayout(true);

        rvAccountHistory.setLayoutManager(linearLayoutManager);

        // Scrolling to the bottom of the array, top of the Recycler View:
        rvAccountHistory.scrollToPosition(databaseHelper.getQuestionSetResults().size() - 1);

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

        if (account.getAccountType().equals(Guest)){
            // If they have not made an account, we cannot display their details.
            txtDetails.setVisibility(View.GONE);
            btnChangeDetails.setVisibility(View.GONE);
            btnSignOut.setVisibility(View.GONE);

            // Show them the sign-up / sign-in buttons:
            btnSignUp.setVisibility(View.VISIBLE);
            btnSignIn.setVisibility(View.VISIBLE);

        }
        else if (account.getAccountType().equals(Member)){

            // If they have made an account, display their details.
            txtDetails.setVisibility(View.VISIBLE);
            btnChangeDetails.setVisibility(View.VISIBLE);
            btnSignOut.setVisibility(View.VISIBLE);

            // Hide the sign-up / sign-in buttons:
            btnSignUp.setVisibility(View.GONE);
            btnSignIn.setVisibility(View.GONE);



            // Set data to the details textview:

            txtDetails.append(
                    getString(R.string.your_name) + "\t" + account.getParentName() + "\n" +
                    getString(R.string.student_name) + "\t" +account.getStudentName() + "\n" +
                    getString(R.string.email) + "\t" +account.getEmail() + "\n"
            );

        }

    }

    private void initViews() {
        rvAccountHistory = findViewById(R.id.rvAccountHistory);
        txtDetailsText = findViewById(R.id.txtDetailsText);
        txtDetails = findViewById(R.id.txtDetails);
        btnTryQuestions = findViewById(R.id.btnTryQuestions);
        btnChangeDetails = findViewById(R.id.btnChangeDetails);
        btnSignOut = findViewById(R.id.btnSignOut);
        btnSignUp = findViewById(R.id.btnSignUp);
        btnSignIn = findViewById(R.id.btnSignIn);
        imgExit = findViewById(R.id.imgExit);

        btnTryQuestions.setOnClickListener(this);
        btnChangeDetails.setOnClickListener(this);
        btnSignOut.setOnClickListener(this);
        btnSignUp.setOnClickListener(this);
        btnSignIn.setOnClickListener(this);
        imgExit.setOnClickListener(this);


    }

    

    @Override
    public void onClick(View view) {

        switch (view.getId()){

            case (R.id.btnTryQuestions):
            case (R.id.imgExit):
                startActivity(new Intent(this, MainMenuActivity.class));
                break;

            case (R.id.btnSignUp):
                startActivity(new Intent(this, SignUpActivity.class));
                break;

            case (R.id.btnSignIn):
                startActivity(new Intent(this, SignInActivity.class));
                break;

            case (R.id.btnChangeDetails):
                Utils.getInstance().setTargetClass(ChangeDetailsActivity.class);

                // Taking the user to the pin confirmation page with the
                // target activity passed as extra intent:
                startActivity(new Intent(this, PinVerificationActivity.class));
                break;

            case (R.id.btnSignOut):
                //TODO: Save account here::
                Utils.getInstance().setUserAccount(new Account());
                startActivity(new Intent(this, WelcomeActivity.class));


            default:
                break;
        }

    }
}
