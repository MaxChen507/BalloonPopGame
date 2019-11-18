package com.example.balloonpopgame;

/*
  Written by Max Chen for CS6326.001, assignment 6, starting November 16, 2019.
    NetID: mmc170330
 */
public abstract class MyShape {
    int test;
    int id;
    int color;
    String shape;
    int size;
    int speed;
    int xPos;
    int yPos;

    public MyShape(){
        test = 1;
    }

    //Setters
    abstract void setColor(int color);
    abstract void setSize(int size);
    abstract void setSpeed(int speed);
    abstract void set_xPos(int xPos);
    abstract void set_yPos(int yPos);

    //Getters
    abstract int getId();
    abstract int getColor();
    abstract String getShape();
    abstract int getSize();
    abstract int getSpeed();
    abstract int get_xPos();
    abstract int get_yPos();
    abstract void moveShape();

}
