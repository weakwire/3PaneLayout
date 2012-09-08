package com.example;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.Gravity;
import android.view.Window;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.BounceInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.widget.TextView;
import android.widget.Toast;

public class MyActivity extends Activity {

    ThreeLayout layout;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        layout = new ThreeLayout(this, 3);
        layout.setAnimationDuration(1000);

        setContentView(layout);
        setupLayout();

        Toast.makeText(getBaseContext(), "Press back to start demoAnimations", Toast.LENGTH_SHORT).show();

    }

    private void setupLayout() {

        TextView tv1 = new TextView(getBaseContext());
        TextView tv2 = new TextView(getBaseContext());
        TextView tv3 = new TextView(getBaseContext());
        tv1.setText("Fragment1");
        tv2.setText("Fragment2");
        tv3.setText("Fragment3");
        tv1.setTextSize(30);
        tv1.setGravity(Gravity.CENTER);
        tv1.setTextColor(Color.BLACK);
        tv2.setTextSize(60);
        tv2.setGravity(Gravity.CENTER);
        tv2.setTextColor(Color.BLACK);
        tv3.setTextSize(30);
        tv3.setGravity(Gravity.CENTER);
        tv3.setTextColor(Color.BLACK);
        layout.getLeftLayout().addView(tv1);
        layout.getMiddleLayout().addView(tv2);
        layout.getRightLayout().addView(tv3);
    }

    @Override
    public void onBackPressed() {
        testAnimations();
    }

    private void testAnimations() {
        Handler h = new Handler();
        h.postDelayed(new Runnable() {
            @Override
            public void run() {
                layout.startLeftAnimation();
            }
        }, 1000);

        h.postDelayed(new Runnable() {
            @Override
            public void run() {
                layout.setInterpolator(new BounceInterpolator());
                layout.startRightAnimation();
            }
        }, 3000);

        h.postDelayed(new Runnable() {
            @Override
            public void run() {
                layout.setInterpolator(new AccelerateInterpolator());
                layout.startLeftAnimation();
            }
        }, 6000);

        h.postDelayed(new Runnable() {
            @Override
            public void run() {
                layout.setInterpolator(new DecelerateInterpolator());
                layout.startRightAnimation();
            }
        }, 9000);
    }
}
