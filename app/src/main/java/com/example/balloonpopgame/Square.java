package com.example.balloonpopgame;

import android.graphics.Color;

import java.util.Random;

/*
  Written by Max Chen for CS6326.001, assignment 6, starting November 16, 2019.
    NetID: mmc170330
 */
public class Square extends MyShape {
    int OG_YPOS;
    int OG_WIDTH;

    public Square(int id, String shape, int widthOfView, int yPos){
        this.id = id;
        this.color = randomStart_color();
        this.shape = shape;
        this.size = randomStart_size();
        this.speed = randomStart_speed();
        this.xPos = randomStart_xPos(widthOfView, size);
        this.yPos = yPos;
        OG_YPOS = yPos;
        OG_WIDTH = widthOfView;
    }

    //Setters
    public void setColor(int color){
        this.color = color;
    }
    public void setSize(int size){
        this.size = size;
    }
    public void setSpeed(int speed){
        this.speed = speed;
    }
    public void set_xPos(int xPos){
        this.xPos = xPos;
    }
    public void set_yPos(int yPos){
        this.yPos = yPos;
    }

    //Getters
    public int getId(){ return id; }
    public int getColor(){
        return color;
    }
    public String getShape(){
        return shape;
    }
    public int getSize(){
        return size;
    }
    public int getSpeed(){
        return speed;
    }
    public int get_xPos(){
        return xPos;
    }
    public int get_yPos(){
        return yPos;
    }

    public int randomStart_xPos(int widthOfView, int size){
        Random r = new Random();
        int r_xPos = r.nextInt(widthOfView + 1 - size);

        return r_xPos;
    }

    public int randomStart_color(){
        Random r = new Random();
        int r_color;
        int whichColor = r.nextInt(7 + 1);

        // switch statement with int data type
        switch (whichColor) {
            case 1:
                r_color = Color.RED;
                break;
            case 2:
                r_color = Color.rgb(255, 165, 0);
                break;
            case 3:
                r_color = Color.YELLOW;
                break;
            case 4:
                r_color = Color.GREEN;
                break;
            case 5:
                r_color = Color.BLUE;
                break;
            case 6:
                r_color = Color.MAGENTA;
                break;
            case 7:
                r_color =Color.WHITE;
                break;
            default:
                r_color = Color.RED;
                break;
        }
        return r_color;
    }

    public int randomStart_size(){
        Random r = new Random();
        int max = 150;
        int min = 100;

        return r.nextInt((max - min) + 1) + min;
    }

    public int randomStart_speed(){
        Random r = new Random();
        int max = 20;
        int min = 10;

        return r.nextInt((max - min) + 1) + min;
    }

    public void moveShape(){
        yPos = yPos - speed;
    }

    public void resetShape(){
        size = randomStart_size();
        yPos = OG_YPOS;
        xPos = randomStart_xPos(OG_WIDTH, size);
        color = randomStart_color();
        speed = randomStart_speed();
    }
}
