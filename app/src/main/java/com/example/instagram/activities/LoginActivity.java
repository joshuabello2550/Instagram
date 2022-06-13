package com.example.instagram.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.instagram.R;
import com.parse.LogInCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseUser;

public class LoginActivity extends AppCompatActivity {

    private static final String TAG = "LoginActivity";
    private EditText etUsername;
    private EditText etPassword;
    private Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initialize();

        // if the user already logged in
        if (ParseUser.getCurrentUser() != null){
            goMainActivity();
        }
    }


    private void initialize(){
        etUsername =  findViewById(R.id.tvUsername);
        etPassword =  findViewById(R.id.tvPassword);
        btnLogin = findViewById(R.id.btnLogin);
        loginUser();
    }

    private void loginUser() {
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = etUsername.getText().toString();
                String password = etPassword.getText().toString();
                ParseUser.logInInBackground(username, password, new LogInCallback() {
                    @Override
                    public void done(ParseUser user, ParseException e) {
                        // if user was unable to login
                        if (e != null) {
                            // TODO: better error login
                            Log.e(TAG, "Issue with login", e);
                            return;
                        }
                        // navigate to main activity
                        goMainActivity();
                        Toast.makeText(LoginActivity.this, "Sucess!", Toast.LENGTH_SHORT);
                    }
                });
            }
        });

    }

    private void goMainActivity() {
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
        finish();
    }
}