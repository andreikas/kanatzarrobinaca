package com.robinkanatzar.android.rck.bubbles;


import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class GameView extends SurfaceView implements Runnable {

    // This is our thread
    Thread mGameThread = null;

    // This is new. We need a SurfaceHolder
    // When we use Paint and Canvas in a thread
    // We will see it in action in the draw method soon.
    SurfaceHolder mOurHolder;

    // A boolean which we will set and unset
    // when the game is running- or not
    volatile boolean mPlaying;

    // Game is mPaused at the start
    boolean mPaused = true;

    // A Canvas and a Paint object
    Canvas mCanvas;
    Paint mPaint;

    // This variable tracks the game frame rate
    long mFPS;

    // The size of the screen in pixels
    int mScreenX;
    int mScreenY;

    Bubble mBubble;

    // The mScore
    int mScore = 0;

    // Lives
    int mLives = 3;

    // constructor
    public GameView(Context context, int x, int y) {
      /*
        The next line of code asks the
        SurfaceView class to set up our object.
      */
        super(context);

        // Set the screen width and height
        mScreenX = x;
        mScreenY = y;

        // Initialize mOurHolder and mPaint objects
        mOurHolder = getHolder();
        mPaint = new Paint();

        // Create a mBubble
        mBubble = new Bubble(mScreenX, mScreenY);

        setupAndRestart();

    }

    public void setupAndRestart() {

        // Put the mBall back to the start
        mBubble.reset(mScreenX, mScreenY);

        // if game over reset scores and mLives
        if (mLives == 0) {
            mScore = 0;
            mLives = 3;
        }
    }


    // If the Activity is paused/stopped
    // shutdown our thread.
    public void pause() {
        mPlaying = false;
        try {
            mGameThread.join();
        } catch (InterruptedException e) {
            Log.e("Error:", "joining thread");
        }

    }

    // If the Activity starts/restarts
    // start our thread.
    public void resume() {
        mPlaying = true;
        mGameThread = new Thread(this);
        mGameThread.start();
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {

        switch (motionEvent.getAction() & MotionEvent.ACTION_MASK) {

            // Player has touched the screen
            case MotionEvent.ACTION_DOWN:

                mPaused = false;


        }
        return true;
    }

    @Override
    public void run() {

    }
}
