package com.robinkanatzar.android.rck.confettitest;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.github.jinatonic.confetti.ConfettiManager;
import com.github.jinatonic.confetti.ConfettiSource;
import com.github.jinatonic.confetti.ConfettoGenerator;
import com.github.jinatonic.confetti.Utils;
import com.github.jinatonic.confetti.confetto.Confetto;
import com.github.jinatonic.confetti.confetto.ShimmeringConfetto;

import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity implements ConfettoGenerator {

    Button mButton;
    ViewGroup rootViewGroup;
    private int size;
    private int velocitySlow, velocityNormal;
    private List<Bitmap> confettoBitmaps;
    protected int goldDark, goldMed, gold, goldLight;
    protected int[] colors;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rootViewGroup = (ViewGroup) findViewById(R.id.innerView).getRootView();

        mButton = (Button) findViewById(R.id.button);


        final Resources res = getResources();
        size = res.getDimensionPixelSize(R.dimen.default_confetti_size);
        velocitySlow = res.getDimensionPixelOffset(R.dimen.default_velocity_slow);
        velocityNormal = res.getDimensionPixelOffset(R.dimen.default_velocity_normal);
        goldDark = res.getColor(R.color.gold_dark);
        goldMed = res.getColor(R.color.gold_med);
        gold = res.getColor(R.color.gold);
        goldLight = res.getColor(R.color.gold_light);
        colors = new int[] { goldDark, goldMed, gold, goldLight };

        final int[] colors = { Color.BLACK };


        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                confettoBitmaps = Utils.generateConfettiBitmaps(colors, size);

                getConfettiManager().setNumInitialCount(0)
                        .setEmissionDuration(ConfettiManager.INFINITE_DURATION)
                        .setEmissionRate(50)
                        .animate();
            }
        });

    }

    private ConfettiManager getConfettiManager() {
        final ConfettiSource confettiSource = new ConfettiSource(0, -size, rootViewGroup.getWidth(),
                -size);
        return new ConfettiManager(this, this, confettiSource, rootViewGroup)
                .setVelocityX(0, velocitySlow)
                .setVelocityY(velocityNormal, velocitySlow)
                .setInitialRotation(180, 180)
                .setRotationalAcceleration(360, 180)
                .setTargetRotationalVelocity(360);
    }

    @Override
    public Confetto generateConfetto(Random random) {
        return new ShimmeringConfetto(
                confettoBitmaps.get(random.nextInt(confettoBitmaps.size())),
                goldLight, goldDark, 1000, random);
    }
}
