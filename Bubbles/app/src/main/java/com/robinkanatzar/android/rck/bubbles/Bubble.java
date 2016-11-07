package com.robinkanatzar.android.rck.bubbles;


import android.graphics.RectF;

import java.util.Random;

public class Bubble {

    private RectF mRect;
    private float mXVelocity;
    private float mYVelocity;
    private float mBubbleWidth; // bubble width
    private float mBubbleHeight; // bubble height


    // construtor for a new bubble
    public Bubble(int screenX, int screenY){

        // Make the mBall size relative to the screen resolution
        mBubbleWidth = screenX / 100; // TODO change ball size to make a bigger bubble
        mBubbleHeight = mBubbleWidth;

          /*
            Start the ball travelling straight up
            at a quarter of the screen height per second
          */
        mYVelocity = screenY / 4; // TODO slow down bubble, change with levels
        mXVelocity = mYVelocity;

        // Initialize the Rect that represents the mBall
        mRect = new RectF();

    }

    // Give access to the Rect
    public RectF getRect(){
        return mRect;
    }

    // Change the position each frame
    public void update(long fps){
        mRect.left = mRect.left + (mXVelocity / fps);
        mRect.top = mRect.top + (mYVelocity / fps);
        mRect.right = mRect.left + mBubbleWidth;
        mRect.bottom = mRect.top - mBubbleHeight;
    }

    // Set random velocity
    public void setRandomVelocity(){
        Random generator = new Random();
        int answer = generator.nextInt(2);

    }

    public void reset(int x, int y){
        mRect.left = x / 2;
        mRect.top = y - 20;
        mRect.right = x / 2 + mBubbleWidth;
        mRect.bottom = y - 20 - mBubbleHeight;
    }
}
