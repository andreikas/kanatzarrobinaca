package com.robinkanatzar.android.rck.animationpractice;


import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.drawable.BitmapDrawable;
import android.os.Handler;
import android.util.AttributeSet;
import android.widget.ImageView;

public class AnimatedView extends ImageView {
    private Context mContext;
    int x = -1;
    int y = -1;
    private int xVelocity = 0; // keep this as zero so it moves vertically only
    private int yVelocity = 2; // speed it moves up
    private int xVelocity2 = 0;
    private int yVelocity2 = 8;
    private Handler h;
    private final int FRAME_RATE = 30;

    public AnimatedView(Context context, AttributeSet attrs)  {
        super(context, attrs);
        mContext = context;
        h = new Handler();
    }

    private Runnable r = new Runnable() {
        @Override
        public void run() {
            invalidate();
        }
    };

    protected void onDraw(Canvas c) {
        BitmapDrawable ball = (BitmapDrawable) mContext.getResources().getDrawable(R.mipmap.bubble);
        if (x<0 && y <0) {
            x = this.getWidth()/2;
            y = this.getHeight()/2;
        } else {
            x += xVelocity;
            y += yVelocity;
            if ((x > this.getWidth() - ball.getBitmap().getWidth()) || (x < 0)) {
                xVelocity = xVelocity*-1;
            }
            if ((y > this.getHeight() - ball.getBitmap().getHeight()) || (y < 0)) {
                yVelocity = yVelocity*-1;
            }
        }
        c.drawBitmap(ball.getBitmap(), x, y, null);
        h.postDelayed(r, FRAME_RATE);

        createAnotherBubble(c);
    }

    public void createAnotherBubble(Canvas c) {
        BitmapDrawable ball2 = (BitmapDrawable) mContext.getResources().getDrawable(R.mipmap.ic_launcher);

        if (x<0 && y <0) {
            x = this.getWidth()/2;
            y = this.getHeight()/2;
        } else {
            x += xVelocity;
            y += yVelocity;
            if ((x > this.getWidth() - ball2.getBitmap().getWidth()) || (x < 0)) {
                xVelocity = xVelocity*-1;
            }
            if ((y > this.getHeight() - ball2.getBitmap().getHeight()) || (y < 0)) {
                yVelocity = yVelocity*-1;
            }
        }

        Paint paint = new Paint();
        paint.setColor(Color.WHITE); // Text Color
        paint.setStrokeWidth(12); // Text thickness
        paint.setTextSize(50); // Text size
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_OVER)); // Text Overlapping Pattern
        // some more settings...

        // TODO randomize offset
        // TODO randomize words from db
        // TODO make bubbles dissappear off top
        // TODO on touch listener for bubble
        c.drawBitmap(ball2.getBitmap(), x + 100, y+100, paint);
        c.drawText("Testing...", x+100, y+100, paint);

        h.postDelayed(r, FRAME_RATE);
    }
}
