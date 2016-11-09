package com.robinkanatzar.android.rck.animationpractice;


import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import static android.R.attr.x;
import static android.R.attr.y;

public class BubbleView extends SurfaceView implements Runnable{

    Thread mGameThread = null; // thread
    SurfaceHolder mSurfaceHolder; // surface holder when use paint and canvas in a thread
    volatile boolean mPlaying; // bool when game is running or not
    boolean mPaused = true; // game paused at start

    Canvas mCanvas;
    Paint mPaint;

    long mFPS; // game frame rate - frames per second

    int mScreenX; // x dimension of screen
    int mScreenY; // y dimension of screen

    // constructor called when we call new() on BubbleView
    public BubbleView(Context context, int x, int y) {
        super(context); // asks SurfaceView class to set up objects

        mScreenX = x; // set screen width
        mScreenY = y; // set screen height

        mSurfaceHolder = getHolder(); // initialize holder
        mPaint = new Paint(); // initialize Paint object

        setupAndRestart(); // set up the game
    }

    public void setupAndRestart() {

        // maybe reset bubble position
        // maybe reset lives here

        mPaint.setColor(Color.WHITE); // Text Color
        mPaint.setStrokeWidth(12); // Text thickness
        mPaint.setTextSize(50); // Text size
        mPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_OVER)); // Text Overlapping Pattern
        mCanvas.drawText("Testing...", x, y, mPaint);

    }

    @Override
    public void run() {

        while (mPlaying) {

            long startFrameTime = System.currentTimeMillis(); // Capture the current time in milliseconds in startFrameTime

            if (!mPaused) {
                update(); // update the frame
            }

            draw(); // Draw the frame

            // calculate the frame per second speed
            long timeThisFrame = System.currentTimeMillis() - startFrameTime;
            if (timeThisFrame >= 1) {
                mFPS = 1000 / timeThisFrame;
            }
        }
    }

    public void update() {

    }

    public void draw() {

        // Make sure our drawing surface is valid or we crash
        if (mSurfaceHolder.getSurface().isValid()) {

            mCanvas = mSurfaceHolder.lockCanvas(); // Lock the mCanvas ready to draw

            mCanvas.drawColor(Color.argb(255, 255, 255, 255)); // Draw the background color

            mPaint.setColor(Color.argb(255, 102, 0, 204)); // Choose the brush color for drawing

            // TODO draw bubble and text here

            mSurfaceHolder.unlockCanvasAndPost(mCanvas); // Draw everything to the screen

        }
    }

    // If the Activity is paused/stopped shutdown our thread.
    public void pause() {
        mPlaying = false;
        try {
            mGameThread.join();
        } catch (InterruptedException e) {
            Log.e("Error:", "joining thread");
        }
    }

    // If the Activity starts/restarts start our thread.
    public void resume() {
        mPlaying = true;
        mGameThread = new Thread(this);
        mGameThread.start();
    }

    // The SurfaceView class implements onTouchListener
    // So we can override this method and detect screen touches.
    @Override
    public boolean onTouchEvent(MotionEvent motionEvent) {

        switch (motionEvent.getAction() & MotionEvent.ACTION_MASK) {

            // Player has touched the screen
            case MotionEvent.ACTION_DOWN:

                mPaused = false;

                // TODO did the user touch the bubble
                // TODO did the user touch the button ish things
                // Is the touch on the right or left?
                if(motionEvent.getX() > mScreenX / 2){
                    // Right side of screen
                }
                else{
                    // Left side of screen
                }
                break;

            // Player has removed finger from screen
            case MotionEvent.ACTION_UP:

                break;
        }
        return true;
    }

    public void chooseText() {
        // use random number to get text from table on bubble
    }

    public int delayBubble() {
        // use random number
        // return number of seconds to delay bubble
        return 1;
    }
}
