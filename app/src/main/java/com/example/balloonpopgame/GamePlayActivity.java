package com.example.balloonpopgame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Bundle;

import com.example.balloonpopgame.views.CustomView;

/*
  Written by Max Chen for CS6326.001, assignment 6, starting November 16, 2019.
    NetID: mmc170330
 */
public class GamePlayActivity extends AppCompatActivity {

    private CustomView mCustomView;

    private Integer score;
    private Integer missedBalloons;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_play);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        mCustomView = findViewById(R.id.customView);

    }

    public void FinishedGame(Integer score, Integer missedBalloons){
        this.score = score;
        this.missedBalloons = missedBalloons;

        Intent intent = new Intent(getApplicationContext(), AddHighScoreActivity.class);
        intent.putExtra("EXTRA_SCORE", score.toString());
        intent.putExtra("EXTRA_MISSED_BALLOONS", missedBalloons.toString());
        startActivity(intent);
        finish();
    }
}
