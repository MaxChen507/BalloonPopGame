package com.example.balloonpopgame;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

/*
  Written by Max Chen for CS6326.001, assignment 6, starting November 16, 2019.
    NetID: mmc170330
 */
public class HighScoreListAdapter extends ArrayAdapter<HighScoreInfo> {

    // Declare variables
    private Context mContext;
    int mResource;

    // Adapter Constructor
    public HighScoreListAdapter(@NonNull Context context, int resource, @NonNull ArrayList<HighScoreInfo> objects) {
        super(context, resource, objects);
        mContext = context;
        mResource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        //Gets the highscore information
        String name = getItem(position).getName();
        String score = getItem(position).getScore();
        String date = getItem(position).getDate();

        // Creates the highscore object with the information
        //HighScoreInfo highScoreInfo = new HighScoreInfo(name, score, date);

        // Instantiates a layout XML file into its corresponding View objects
        LayoutInflater inflater = LayoutInflater.from(mContext);
        convertView = inflater.inflate(mResource, parent, false);

        // Gets the text view items
        TextView tvName = convertView.findViewById(R.id.nameTextView);
        TextView tvScore = convertView.findViewById(R.id.enterScoreTextView);
        TextView tvDate = convertView.findViewById(R.id.enterDateTextView);

        // Sets the text view items
        tvName.setText(name);
        tvScore.setText(score);
        tvDate.setText(date);

        return convertView;
    }
}