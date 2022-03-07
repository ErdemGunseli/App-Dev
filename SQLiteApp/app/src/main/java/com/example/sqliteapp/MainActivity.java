package com.example.sqliteapp;

import static java.lang.Integer.valueOf;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.Locale;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private ScrollView svMain;

    private TextView edtTxtFirstName, edtTxtLastName, edtTxtAge;

    private SwitchCompat swActivate;

    private Button btnClear, btnAdd;

    private RecyclerView rvList;

    private ArrayList<Contact> contacts = new ArrayList<>();

    private DatabaseHelper databaseHelper = new DatabaseHelper(MainActivity.this);


    private ListRecyclerAdapter listRecyclerAdapter = new ListRecyclerAdapter(this, databaseHelper);



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();

        setData();

        rvList.setLayoutManager(new LinearLayoutManager(this));
        rvList.setAdapter(listRecyclerAdapter);
    }

    private void setData(){
        contacts = databaseHelper.getDatabase();
        listRecyclerAdapter.setContacts(contacts);
    }

    private void initViews() {
        svMain = findViewById(R.id.svMain);
        edtTxtFirstName = findViewById(R.id.edtTxtFirstName);
        edtTxtLastName = findViewById(R.id.edtTxtLastName);
        edtTxtAge = findViewById(R.id.edtTxtAge);
        rvList = findViewById(R.id.rvList);

        btnClear = findViewById(R.id.btnClear);
        btnAdd = findViewById(R.id.btnAdd);

        btnClear.setOnClickListener(this);
        btnAdd.setOnClickListener(this);

        swActivate = findViewById(R.id.swActivate);
        swActivate.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

            }
        });
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {

            case (R.id.btnAdd):
                String firstName = edtTxtFirstName.getText().toString().toUpperCase(Locale.ROOT);
                String lastName = edtTxtLastName.getText().toString().toUpperCase(Locale.ROOT);
                boolean isAdmin = swActivate.isChecked();

                if (!firstName.isEmpty() &&
                        !lastName.isEmpty() &&
                        !edtTxtAge.getText().toString().isEmpty()) {

                    int age = Integer.parseInt(edtTxtAge.getText().toString());



                    Contact contact = new Contact(contacts.size(), firstName, lastName, age, isAdmin);

                    if (databaseHelper.add(contact)){showSnackBar(svMain, getString(R.string.success), getString(R.string.ok));}

                    listRecyclerAdapter.setContacts(databaseHelper.getDatabase());
                } else {
                    showSnackBar(svMain, getString(R.string.check_inputs), getString(R.string.ok));
                }
                break;

            case (R.id.btnClear):

                // Deleting every item:
                for (Contact contact: databaseHelper.getDatabase()){
                   if (databaseHelper.deleteUser(contact)){
                       Toast.makeText(this, "DELETION APPEARS SUCCESSFUL", Toast.LENGTH_SHORT).show();
                   }
                   else{
                       Toast.makeText(this, "DELETION APPEARS UNSUCCESSFUL", Toast.LENGTH_SHORT).show();
                   }

                }

                // Setting new empty database:
                setData();
                break;


            default:
                break;

        }
    }

    public void showSnackBar(ViewGroup layout, String mainText, String actionText) {

        // Shows Snack Bar:
        Snackbar.make(layout, mainText, Snackbar.LENGTH_SHORT)
                .setAction(actionText, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                    }
                })
                .setActionTextColor(getResources().getColor(R.color.accent))
                .setTextColor(getResources().getColor(R.color.accent))
                .setBackgroundTint(getResources().getColor(R.color.white))
                .show();
    }
}

