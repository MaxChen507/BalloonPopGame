package com.example.balloonpopgame;

/*
  Written by Max Chen for CS6326.001, assignment 6, starting November 16, 2019.
    NetID: mmc170330
 */
public class GameSingleton {

    int timer = 60000;

    // Constructor
    private GameSingleton(){

    }
    // Declares singleton object
    private static  GameSingleton mGameSingleton;

    // Returns singleton object, creates a new one if it doesn't exist
    public static GameSingleton get(){
        if(mGameSingleton == null){
            mGameSingleton = new GameSingleton();
        }
        return mGameSingleton;
    }

    // Setter
    public void setTimer(int time){
        timer = time;
    }

    // Getter
    public int getTimer(){
        return timer;
    }

}
