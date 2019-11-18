package com.example.balloonpopgame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/*
  Written by Max Chen for CS6326.001, assignment 6, starting November 16, 2019.
    NetID: mmc170330
 */
public class MainActivity extends AppCompatActivity {

    // Declare btn variables
    private Button mOkBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Sets our views to our variables
        mOkBtn = findViewById(R.id.startBtn);

        // Method when save button is clicked
        mOkBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), GamePlayActivity.class);
                startActivity(intent);
            }
        });
    }
}
