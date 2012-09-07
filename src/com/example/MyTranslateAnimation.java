package com.example;

import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Transformation;
import android.widget.RelativeLayout;

/**
 * Created with IntelliJ IDEA.
 * User: weakwire (John Papadakis)
 * Date: 9/7/12
 * Time: 1:38 AM
 */
public class MyTranslateAnimation extends Animation {
    private View mView;
    private final float fromX;
    private final float toX;
    private final float fromY;
    private final float toY;


    public MyTranslateAnimation(View v, float fromX, float toX, float fromY, float toY) {

        mView = v;
        this.fromX = fromX;
        this.toX = toX;
        this.fromY = fromY;
        this.toY = toY;
        setDuration(400);
    }

    @Override
    protected void applyTransformation(float interpolatedTime, Transformation t) {
        float x =
                (toX - fromX) * interpolatedTime + fromX;
        float y = (toY - fromY) * interpolatedTime + fromY;
        RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) mView.getLayoutParams();
        params.setMargins(0, 0, (int) -x, (int) -y);
        mView.requestLayout();
    }
}