package com.example.balloonpopgame;

/*
  Written by Max Chen for CS6326.001, assignment 6, starting November 16, 2019.
    NetID: mmc170330
 */
public class HighScoreInfo {
    private String name;
    private String score;
    private String date;

    public HighScoreInfo(String name, String score, String date){
        this.name = name;
        this.score = score;
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}