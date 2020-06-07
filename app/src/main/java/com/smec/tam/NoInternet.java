package com.smec.tam;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class NoInternet extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setTitle("No Internet!");
        setContentView(R.layout.activity_no_internet);


    }
}
