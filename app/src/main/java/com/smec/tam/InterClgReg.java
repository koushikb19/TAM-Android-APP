package com.smec.tam;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class InterClgReg extends AppCompatActivity {
    Button submit;
    Spinner spinDept;
    Spinner spinYear;
    Spinner spinSec;
    TextInputEditText name;
    TextInputEditText phone;
    TextInputEditText rollNo;
    TextInputEditText clg;
    Spinner spinClg;
    TextInputEditText email;
    TextView title;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        Intent intent = getIntent();
        setContentView(R.layout.activity_inter_clg_reg);
        name = findViewById(R.id.inp_intreg_name);
        phone = findViewById(R.id.inp_intreg_phone);
        rollNo = findViewById(R.id.inp_intreg_rollNo);
        title = findViewById(R.id.txt_intreg_title);
        clg = findViewById(R.id.inp_intreg_clg);
        email = findViewById(R.id.inp_intreg_email);
        title.setText(intent.getStringExtra("title"));

        String[] departments = {"Department", "CSE", "ECE", "EEE", "MECH", "CIVIL", "IT"};
        String[] years = {"Year", "1", "2", "3", "4"};
        String[] years_first = {"Year", "1"};
        String[] sections = {"Section", "A", "B", "C", "D"};

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference rootRef = database.getReference();
        final List<String> clgList = new ArrayList<>();

        clgList.add("Select your College");

        rootRef.child("collegeList").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot child : dataSnapshot.getChildren()) {
                    clgList.add(child.getValue(String.class));
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        clgList.add("Other");

        spinDept = findViewById(R.id.spn_intreg_dept);
        spinYear = findViewById(R.id.spn_intreg_year);
        spinSec = findViewById(R.id.spn_intreg_sec);
        spinClg = findViewById(R.id.spn_intreg_clg);

        boolean isFirstYear = intent.getBooleanExtra("isFirst",false);

        openKeyboard();

        ArrayAdapter<String> adapterDept = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, departments);
        ArrayAdapter<String> adapterYear = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, years);
        ArrayAdapter<String> adapterYearFirst = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, years_first);
        ArrayAdapter<String> adaptersec = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, sections);
        ArrayAdapter<String> adapterClg = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, clgList);

        adapterDept.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapterYear.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adaptersec.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapterClg.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinDept.setAdapter(adapterDept);
        if(isFirstYear)
            spinYear.setAdapter(adapterYearFirst);
        else
            spinYear.setAdapter(adapterYear);
        spinSec.setAdapter(adaptersec);
        spinClg.setAdapter(adapterClg);

        openKeyboard();

        final String eventName = intent.getStringExtra("title");

        submit = findViewById(R.id.btn_intreg_submit);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if( name.getText().toString().trim().equals("")) {
                    closeKeyboard();
                    name.setError( "Name is required!" );
                }
                else if (phone.getText().toString().trim().equals("")) {
                    closeKeyboard();
                    phone.setError("Phone is required");
                }
                else if (rollNo.getText().toString().trim().equals("")) {
                    closeKeyboard();
                    rollNo.setError("Roll Number is required");
                }
                else if (spinDept.getSelectedItem().toString().trim().equals("Department")) {
                    closeKeyboard();
                    Snackbar.make(view, "Select Department", Snackbar.LENGTH_LONG).show();
                }
                else if (spinSec.getSelectedItem().toString().trim().equals("Section")) {
                    closeKeyboard();
                    Snackbar.make(view, "Select Section", Snackbar.LENGTH_LONG).show();
                }
                else if (spinYear.getSelectedItem().toString().trim().equals("Year")) {
                    closeKeyboard();
                    Snackbar.make(view, "Select Year", Snackbar.LENGTH_LONG).show();
                }
                else if (phone.getText().toString().length() < 10) {
                    closeKeyboard();
                    Snackbar.make(view, "Enter valid Phone Number", Snackbar.LENGTH_LONG).show();
                }
                else if (spinClg.getSelectedItem().toString().trim().equals("Select your College")) {
                    closeKeyboard();
                    Snackbar.make(view, "Select college or enter your college name", Snackbar.LENGTH_LONG).show();
                }
                else if (email.getText().toString().equals("")) {
                    closeKeyboard();
                    Snackbar.make(view, "Enter email", Snackbar.LENGTH_LONG).show();
                }
                else {
                    String namestr = name.getText().toString();
                    String phonestr = phone.getText().toString();
                    String rollNostr = rollNo.getText().toString();
                    String deptStr = spinDept.getSelectedItem().toString();
                    String secStr = spinSec.getSelectedItem().toString();
                    String yearStr = spinYear.getSelectedItem().toString();
                    String dept_section_year = ""+deptStr+"_"+secStr+"_"+yearStr;
                    String clgStr = spinClg.getSelectedItem().toString().trim().equals("Other") ? clg.getText().toString() : spinClg.getSelectedItem().toString();
                    String emailStr = email.getText().toString();

                    InterRegModel registrationModel = new InterRegModel(deptStr, dept_section_year, namestr, phonestr, rollNostr, secStr, yearStr, clgStr, emailStr);
                    final ProgressDialog progressDialog = new ProgressDialog(InterClgReg.this);
                    progressDialog.setMessage("Loading");
                    progressDialog.show();

                    FirebaseDatabase database = FirebaseDatabase.getInstance();
                    DatabaseReference rootRef = database.getReference();

                    DatabaseReference regRef = rootRef.child("Registrations");
                    DatabaseReference eventRef = regRef.child(eventName);

                    eventRef.child(rollNostr).setValue(registrationModel);
                    progressDialog.dismiss();
                    closeKeyboard();
                    Snackbar.make(view, "Successfully Submitted", Snackbar.LENGTH_LONG).show();
                }
            }
        });
    }

    private void closeKeyboard() {
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    private void openKeyboard() {
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.showSoftInput(view, InputMethodManager.SHOW_IMPLICIT);
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
