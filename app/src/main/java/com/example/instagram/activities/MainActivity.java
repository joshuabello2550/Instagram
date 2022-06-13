package com.example.instagram.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.instagram.R;
import com.parse.ParseUser;

public class MainActivity extends AppCompatActivity {

    private EditText etDescription;
    private Button btnCaptureImage;
    private ImageView ivPostImage;
    private Button btnSubmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initilaize();
    }

    private void initilaize() {
        etDescription =  findViewById(R.id.etDescription);
        btnCaptureImage =  findViewById(R.id.btnCaptureImage);
        btnSubmit =  findViewById(R.id.btnSubmit);
        ivPostImage =  findViewById(R.id.ivPostImage);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.btnLogout){
            ParseUser.logOut();
            // navigate back to login screen
            Intent i = new Intent(this, LoginActivity.class);
            startActivity(i);
        }
        return super.onOptionsItemSelected(item);
    }
}