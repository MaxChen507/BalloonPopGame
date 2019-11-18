package com.example.balloonpopgame;

import android.content.Context;
import android.util.Log;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

/*
  Written by Max Chen for CS6326.001, assignment 6, starting November 16, 2019.
    NetID: mmc170330
 */
public class DataSingleton {


    // Constructor
    private DataSingleton(){

    }

    // Declares singleton object
    private static DataSingleton mDataSingleton;

    // Returns singleton object, creates a new one if it doesn't exist
    public static DataSingleton get(){
        if(mDataSingleton == null){
            mDataSingleton = new DataSingleton();
        }
        return mDataSingleton;
    }

    // Writes to file
    public void writeHighScoreListToFile(ArrayList<HighScoreInfo> highScoreInfos, Context context){
        try {
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(context.openFileOutput("HighScoreList.txt", Context.MODE_PRIVATE));

            StringBuilder myString = new StringBuilder();

            for(HighScoreInfo item: highScoreInfos){
                String tempString = item.getName() + "\t" + item.getScore() + "\t" + item.getDate() + "\n";
                myString.append(tempString);
            }

            outputStreamWriter.write(myString.toString());
            outputStreamWriter.close();
        }
        catch (IOException e) {
            Log.e("Exception", "File write failed: " + e.toString());
        }
    }

    // Reads from file
    public ArrayList<HighScoreInfo> getHighScoreLitFromFile(Context context){
        ArrayList<HighScoreInfo> highScoreInfos = new ArrayList<>();

        try {
            InputStream inputStream = context.openFileInput("HighScoreList.txt");

            if ( inputStream != null ) {
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String receiveString;

                while ( (receiveString = bufferedReader.readLine()) != null ) {
                    String[] arrOfStr = receiveString.split("\t");
                    HighScoreInfo tempInfo = new HighScoreInfo(arrOfStr[0], arrOfStr[1], arrOfStr[2]);
                    highScoreInfos.add(tempInfo);
                }

                inputStream.close();
            }
        }
        catch (FileNotFoundException e) {
            Log.e("login activity", "File not found: " + e.toString());
        } catch (IOException e) {
            Log.e("login activity", "Can not read file: " + e.toString());
        }

        return highScoreInfos;
    }

}