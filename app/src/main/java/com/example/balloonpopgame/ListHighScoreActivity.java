package com.example.balloonpopgame;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;

/*
  Written by Max Chen for CS6326.001, assignment 6, starting November 16, 2019.
    NetID: mmc170330
 */
public class ListHighScoreActivity extends AppCompatActivity {

    // Declare user created ArrayAdapter
    private HighScoreListAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_high_score);

        // Initialize mListview
        ListView mListView = findViewById(R.id.listView);

        // Get data from a file
        ArrayList<HighScoreInfo> mHighScoreList = DataSingleton.get().getHighScoreLitFromFile(this);

        // Set singleton
        HighScoreSingleton.get().setHighScoreList(mHighScoreList);

        // Add new high score to singleton

        // Sets the high score
        String name = getIntent().getStringExtra("EXTRA_HIGH_SCORE_NAME");
        String score = getIntent().getStringExtra("EXTRA_HIGH_SCORE_SCORE");
        String date = getIntent().getStringExtra("EXTRA_HIGH_SCORE_DATE");

        // Create a new temp highscoreinfo
        HighScoreInfo highScoreInfo = new HighScoreInfo(name,score, date);

        // Add new temp to singleton high score list
        HighScoreSingleton.get().addHighScore(highScoreInfo);

        // Calls method to update the text file
        DataSingleton.get().writeHighScoreListToFile(HighScoreSingleton.get().getHighScoreList(), this);

        // Calls a sort
        HighScoreSingleton.get().sortHighScoreList();

        // Sort singleton
        HighScoreSingleton.get().sortHighScoreList();

        // Get highScoreInfo list from singleton
        ArrayList<HighScoreInfo> highScoreInfoList = HighScoreSingleton.get().getHighScoreList();

        // Initializes adapter to this activity, with adapter_view_layout.xml, and with highScoreInfo list
        mAdapter = new HighScoreListAdapter(this, R.layout.adapter_view_layout, highScoreInfoList);

        // Sets the adapter to mListView
        mListView.setAdapter(mAdapter);
    }
}
