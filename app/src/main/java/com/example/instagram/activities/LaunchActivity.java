package com.example.instagram.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;

import com.example.instagram.R;

public class LaunchActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launch);


        // Delay time for the login activity
        int time = 500; // time to wait
        Handler mHandler = new Handler();
        mHandler.postDelayed(() -> {
            Intent i = new Intent(LaunchActivity.this, LoginActivity.class);
            startActivity(i);
        }, time);
    }
}