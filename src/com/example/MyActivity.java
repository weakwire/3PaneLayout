package com.example;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.BounceInterpolator;
import android.view.animation.DecelerateInterpolator;

public class MyActivity extends Activity {

    ThreeLayout layout;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        layout = new ThreeLayout(this, 3);
        layout.setAnimationDuration(1000);
        setContentView(layout);
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
