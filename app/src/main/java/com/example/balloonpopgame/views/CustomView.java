package com.example.balloonpopgame.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;

import com.example.balloonpopgame.Circle;
import com.example.balloonpopgame.GamePlayActivity;
import com.example.balloonpopgame.Square;

import java.util.ArrayList;

/*
  Written by Max Chen for CS6326.001, assignment 6, starting November 16, 2019.
    NetID: mmc170330
 */
public class CustomView extends View {

    //Declare Canvas size variables
    private int CanvasHeight = 1836;
    private int CanvasWidth = 1080;

    //Declare paint objects
    private Paint mPaintBorder;
    private Paint mPaintShape;
    private Paint mPaintText;

    //Declare square array list
    ArrayList<Square> squaresList;

    //Declare circle array list
    ArrayList<Circle> circlesList;

    //Timer variable in milliseconds;
    Integer timer = 60000;

    //Score variable;
    Integer score = 0;

    //Extra time counter
    int extraTimeCounter = 0;

    //Winning balloons missed counter
    int balloonMissedCounter = 0;

    //Declare winning shape string;
    String winningShape = "circle";

    //Declare winning color int;
    Integer winningColor = Color.RED;

    private Handler mHandler = new Handler();
    int frameRate = 10;
    private Runnable r = new Runnable() {
        @Override
        public void run() {

            //Change timer
            timer = timer - frameRate;

            //Move shapes
            moveAllShapes(squaresList, circlesList);

            //Redraw
            invalidate();
        }
    };

    public CustomView(Context context) {
        super(context);

        init(null);
    }

    public CustomView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        init(attrs);
    }

    public CustomView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        init(attrs);
    }

    public CustomView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);

        init(attrs);
    }

    private void init(@Nullable AttributeSet set){
        //Initialize paints
        mPaintBorder = new Paint();
        mPaintShape = new Paint();
        mPaintText = new Paint();

        //Create 4 square objects
        Square mSquare1 = new Square(1, "square", CanvasWidth, CanvasHeight);
        Square mSquare2 = new Square(2, "square", CanvasWidth, CanvasHeight);
        Square mSquare3 = new Square(3, "square", CanvasWidth, CanvasHeight);
        Square mSquare4 = new Square(4, "square", CanvasWidth, CanvasHeight);

        //Initialize and populate squares list
        squaresList = new ArrayList<>();
        squaresList.add(mSquare1);
        squaresList.add(mSquare2);
        squaresList.add(mSquare3);
        squaresList.add(mSquare4);

        //Create 4 circle objects
        Circle mCircle1 = new Circle(5, "circle", CanvasWidth, CanvasHeight);
        Circle mCircle2  = new Circle(6, "circle", CanvasWidth, CanvasHeight);
        Circle mCircle3  = new Circle(7, "circle", CanvasWidth, CanvasHeight);
        Circle mCircle4  = new Circle(8, "circle", CanvasWidth, CanvasHeight);

        //Initialize and populate circles list
        circlesList = new ArrayList<>();
        circlesList.add(mCircle1);
        circlesList.add(mCircle2);
        circlesList.add(mCircle3);
        circlesList.add(mCircle4);


    }

    @Override
    protected void onDraw(Canvas canvas) {
        //Changes background of game to be black
        canvas.drawColor(Color.BLACK);

        //Gives a cyan border
        mPaintBorder.setColor( Color.CYAN );
        mPaintBorder.setStrokeWidth(10);
        mPaintBorder.setStyle( Paint.Style.STROKE );
        canvas.drawRect( 0, 0, getWidth(), getHeight(), mPaintBorder);

        //System.out.println("Width = " + getWidth());
        //System.out.println("Height = " + getHeight());

        //Call Collision Handler
        boolean collision = true;
        while(collision){
            collision = collisionHandler(squaresList, circlesList);
        }

        //Draw all squares
        for(Square item_square : squaresList){
            mPaintShape.setColor(item_square.getColor());
            canvas.drawRect(item_square.get_xPos(), item_square.get_yPos() - item_square.getSize(), item_square.get_xPos() + item_square.getSize(), item_square.get_yPos(), mPaintShape);
        }

        //Draw all circles
        for(Circle item_circle : circlesList){
            mPaintShape.setColor(item_circle.getColor());
            canvas.drawCircle(item_circle.get_xPos(), item_circle.get_yPos(), item_circle.getSize(), mPaintShape);
        }

        //Prints time and score on top
        Integer timeSeconds = timer/1000;
        mPaintText.setColor(Color.WHITE);
        mPaintText.setStyle(Paint.Style.FILL);
        mPaintText.setTextSize(75);
        canvas.drawText("Time: " + timeSeconds.toString() + "    Score: " + score.toString(),0,75, mPaintText);
        mPaintText.setColor(Color.BLACK);
        mPaintText.setStyle(Paint.Style.STROKE);
        mPaintText.setStrokeMiter(10);
        canvas.drawText("Time: " + timeSeconds.toString() + "    Score: " + score.toString(),0,75, mPaintText);

        //Call runnable with a certain frame rate
        if(timer <= 0){
            GamePlayActivity activity = (GamePlayActivity)getContext();
            activity.FinishedGame(score, balloonMissedCounter);
        }
        else{
            mHandler.postDelayed(r, frameRate);
        }

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        boolean value = super.onTouchEvent(event);

        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN: {
                float x = event.getX();
                float y = event.getY();

                checkTouchAllShapes(x, y, squaresList, circlesList);
            }
        }

        return value;
    }

    public boolean collisionHandler(ArrayList<Square> squaresList, ArrayList<Circle> circlesList){
        //As a square loop through both lists
        for(Square item_base_square : squaresList){
            //loop through squares
            for(Square item_square : squaresList){
                //first check if id is the same
                if(item_base_square.getId() == item_square.getId()){
                    //Do nothing
                }
                else{
                    if(isSquaresColliding(item_base_square, item_square)){
                        item_base_square.set_yPos(item_base_square.get_yPos()+1);
                        return true;
                    }
                }
            }
            //loop through circles
            for(Circle item_circle : circlesList){
                if(isSquare_CircleColliding(item_base_square, item_circle)){
                    item_base_square.set_yPos(item_base_square.get_yPos()+1);
                    return true;
                }
            }
        }
        //As a circle loop through both lists
        for(Circle item_base_circle : circlesList){
            //loop through circles
            for(Circle item_circle : circlesList){
                //first check if id is the same
                if(item_base_circle.getId() == item_circle.getId()){
                    //Do nothing
                }
                else{
                    if(isCirclesColliding(item_base_circle, item_circle)){
                        item_base_circle.set_yPos(item_base_circle.get_yPos()+1);
                        return true;
                    }
                }
            }
            //loop through squares
            for(Square item_square : squaresList){
                if(isSquare_CircleColliding(item_base_circle, item_square)){
                    item_base_circle.set_yPos(item_base_circle.get_yPos()+1);
                    return true;
                }
            }
        }

        return false;
    }

    public boolean isSquaresColliding(Square sq1, Square sq2){
        int topEdge1 = sq1.get_yPos() - sq1.getSize();
        int rightEdge1 = sq1.get_xPos() + sq1.getSize();
        int leftEdge1 = sq1.get_xPos();
        int bottomEdge1 = sq1.get_yPos();
        int topEdge2 = sq2.get_yPos() - sq2.getSize();
        int rightEdge2 = sq2.get_xPos() + sq2.getSize();
        int leftEdge2 = sq2.get_xPos();
        int bottomEdge2 = sq2.get_yPos();

        if( leftEdge1 < rightEdge2 && rightEdge1 > leftEdge2 && bottomEdge1 > topEdge2 && topEdge1 < bottomEdge2){
            //Handle Push
            //int dif = bottomEdge1 - topEdge2;
            //sq1.set_yPos(sq1.get_yPos()+dif);
            //sq1.setSpeed(sq1.getSpeed()+10);
            return true;
        }
        return false;
    }

    public boolean isCirclesColliding(Circle cir1, Circle cir2){
        float sidea = Math.abs(cir1.get_xPos() - cir2.get_xPos());
        float sideb = Math.abs(cir1.get_yPos() - cir2.get_yPos());
        sidea = sidea * sidea;
        sideb = sideb * sideb;
        float distance = (float) Math.sqrt(sidea+sideb);

        if(distance < cir1.getSize() + cir2.getSize()){
            //Handle Push
            //cir1.set_yPos(cir1.get_yPos()+(int)distance);
            //cir1.setSpeed(cir1.getSpeed()+10);
            return true;
        }

        return false;
    }

    public boolean isSquare_CircleColliding(Square sq, Circle cir){
        int distanceX = Math.abs(cir.get_xPos() - ( sq.get_xPos() + sq.getSize()/2) );
        int distanceY = Math.abs(cir.get_yPos() - ( sq.get_yPos() - sq.getSize()/2) );
        if (distanceX > (sq.getSize()/2 + cir.getSize())) { return false; }
        if (distanceY > (sq.getSize()/2 + cir.getSize())) { return false; }
        if (distanceX <= (sq.getSize()/2)) { return true; }
        if (distanceY <= (sq.getSize()/2)) { return true; }
        double cDist_sq = Math.pow((distanceX - sq.getSize()/2), 2) + Math.pow((distanceY - sq.getSize()/2), 2);

        return (cDist_sq <= (cir.getSize()*cir.getSize()));
    }

    public boolean isSquare_CircleColliding(Circle cir, Square sq){
        int distanceX = Math.abs(cir.get_xPos() - ( sq.get_xPos() + sq.getSize()/2) );
        int distanceY = Math.abs(cir.get_yPos() - ( sq.get_yPos() - sq.getSize()/2) );
        if (distanceX > (sq.getSize()/2 + cir.getSize())) { return false; }
        if (distanceY > (sq.getSize()/2 + cir.getSize())) { return false; }
        if (distanceX <= (sq.getSize()/2)) { return true; }
        if (distanceY <= (sq.getSize()/2)) { return true; }
        double cDist_sq = Math.pow((distanceX - sq.getSize()/2), 2) + Math.pow((distanceY - sq.getSize()/2), 2);

        return (cDist_sq <= (cir.getSize()*cir.getSize()));
    }

    public void moveAllShapes(ArrayList<Square> squaresList, ArrayList<Circle> circlesList){
        for(Square item_square : squaresList){
            item_square.moveShape();
            //If shape went off screen
            if(item_square.get_yPos() <= 0){
                //Checks if its a winning pop
                if(item_square.getShape().equals(winningShape) && item_square.getColor() == winningColor){
                    balloonMissedCounter+=1;
                }
                item_square.resetShape();
            }
        }
        for(Circle item_circle : circlesList){
            item_circle.moveShape();
            //If shape went off screen
            if(item_circle.get_yPos() <= 0){
                //Checks if its a winning pop
                if(item_circle.getShape().equals(winningShape) && item_circle.getColor() == winningColor){
                    balloonMissedCounter+=1;
                }
                item_circle.resetShape();
            }
        }
    }

    public void checkTouchAllShapes(float x, float y, ArrayList<Square> squaresList, ArrayList<Circle> circlesList){
        for(Square item_square : squaresList){
            //Checks if touch coordinates inside square area
            if(x >= item_square.get_xPos() && x <= item_square.get_xPos()+item_square.getSize()&& y >= item_square.get_yPos()-item_square.getSize() && y <= item_square.get_yPos()){

                //Checks if its a winning pop
                if(item_square.getShape().equals(winningShape) && item_square.getColor() == winningColor){
                    score+=1;
                }
                else{
                    score-=1;
                }

                //Keeps track of extra time
                extraTimeCounter+=1;
                if(extraTimeCounter == 10){
                    timer+=10000;
                    extraTimeCounter=0;
                }

                //Resets the shape
                item_square.resetShape();

            }
        }
        for(Circle item_circle : circlesList){
            //Checks if touch coordinates inside circle area
            if(Math.pow(x - item_circle.get_xPos(),2) + Math.pow(y - item_circle.get_yPos(),2) <  Math.pow(item_circle.getSize(),2)){

                //Checks if its a winning pop
                if(item_circle.getShape().equals(winningShape) && item_circle.getColor() == winningColor){
                    score+=1;
                }
                else{
                    score-=1;
                }

                //Keeps track of extra time
                extraTimeCounter+=1;
                if(extraTimeCounter == 10){
                    timer+=10000;
                    extraTimeCounter=0;
                }

                //Resets the shape
                item_circle.resetShape();
            }
        }
    }

}
