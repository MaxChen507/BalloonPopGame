package com.example.balloonpopgame;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;

/*
  Written by Max Chen for CS6326.001, assignment 6, starting November 16, 2019.
    NetID: mmc170330
 */
public class HighScoreSingleton {
    // Stores a list of our high score object data
    private ArrayList<HighScoreInfo> mHighScoreList;

    // Constructor
    private HighScoreSingleton(){

    }
    // Declares singleton object
    private static  HighScoreSingleton mHighScoreSingleton;

    // Returns singleton object, creates a new one if it doesn't exist
    public static HighScoreSingleton get(){
        if(mHighScoreSingleton == null){
            mHighScoreSingleton = new HighScoreSingleton();
        }
        return mHighScoreSingleton;
    }

    // Returns the shared singleton high score list
    public ArrayList<HighScoreInfo> getHighScoreList(){
        return mHighScoreList;
    }

    // Sets the shared singleton high score list
    public void setHighScoreList(ArrayList<HighScoreInfo> highScoreList){mHighScoreList = highScoreList;}

    // Adds a score the the singleton high score list
    public void addHighScore(HighScoreInfo highScoreInfo){
        mHighScoreList.add(highScoreInfo);
    }

    // Sorts the singleton high score list
    public void sortHighScoreList(){
        Collections.sort(mHighScoreList, new Comparator<HighScoreInfo>() {
            @Override
            public int compare(HighScoreInfo o1, HighScoreInfo o2) {

                if(Integer.parseInt(o1.getScore()) > Integer.parseInt(o2.getScore())){
                    return -1;
                }
                else if(Integer.parseInt(o1.getScore()) < Integer.parseInt(o2.getScore())){
                    return 1;
                }
                else if (Integer.parseInt(o1.getScore()) == Integer.parseInt(o2.getScore())){

                    try {
                        Date date_o1=new SimpleDateFormat("d/M/yyyy").parse(o1.getDate());
                        Date date_o2=new SimpleDateFormat("d/M/yyyy").parse(o2.getDate());

                        if(date_o1.after(date_o2)){
                            return -1;
                        }
                        else if(date_o1.before(date_o2)){
                            return 1;
                        }
                        else if(date_o1.equals(date_o2)){
                            return 0;
                        }
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }

                }
                return o1.getScore().compareTo(o2.getScore());

            }
        });
    }

}