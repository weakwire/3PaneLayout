package com.example;

import android.content.Context;
import android.graphics.Color;
import android.view.animation.Animation;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;

/**
 * Created with IntelliJ IDEA.
 * User: weakwire (John Papadakis)
 * Date: 9/7/12
 * Time: 1:30 AM
 */
public class ThreeLayout extends RelativeLayout {

    private FrameLayout view1;
    private FrameLayout view2;
    private FrameLayout view3;
    int width;
    int panel;
    long animationDuration = 800;
    private Interpolator interpolator = new LinearInterpolator();

    public ThreeLayout(final Context context, final float leftWeight) {
        super(context);

        post(new Runnable() {
            @Override
            public void run() {
                width = getWidth();
                panel = (int) (width / leftWeight);
                _initWithDimentions(context);
            }
        });
    }

    public void setAnimationDuration(long animationDuration) {
        this.animationDuration = animationDuration;
    }

    public void setInterpolator(Interpolator i) {
        interpolator = i;
    }

    public void startLeftAnimation() {
        showAnimation();
    }

    public void startRightAnimation() {
        hideAnimation();
    }


    private FrameLayout fl1;
    private FrameLayout fl2;
    private FrameLayout fl3;

    public FrameLayout getLeftLayout() {
        return fl1;
    }

    public FrameLayout getMiddleLayout() {
        return fl2;
    }

    public FrameLayout getRightLayout() {
        return fl3;
    }

    private void _initWithDimentions(Context context) {

        fl1 = new FrameLayout(context);
        fl2 = new FrameLayout(context);
        fl3 = new FrameLayout(context);

        this.view1 = fl1;
        this.view2 = fl2;
        this.view3 = fl3;

        fl1.setBackgroundColor(Color.RED);
        fl2.setBackgroundColor(Color.GREEN);
        fl3.setBackgroundColor(Color.YELLOW);

        fl1.setId(1);
        fl2.setId(2);
        fl3.setId(3);

        LayoutParams params1 = new LayoutParams(panel, LayoutParams.FILL_PARENT);
        LayoutParams params2 = new LayoutParams(width - panel, LayoutParams.FILL_PARENT);
        LayoutParams params3 = new LayoutParams(width - panel, LayoutParams.FILL_PARENT);

        fl1.setLayoutParams(params1);
        params2.addRule(RelativeLayout.RIGHT_OF, 1);

        fl2.setLayoutParams(params2);
        params3.addRule(RelativeLayout.RIGHT_OF, 2);

        fl3.setLayoutParams(params3);

        addView(fl1);
        addView(fl2);
        addView(fl3);
    }

    private void showAnimation() {
        hideView1();
        shrinkAndMoveView2();
    }

    private void hideAnimation() {
        showView1();
        growAndMoveView2();
    }

    private void showView1() {
        Animation rightAnimation = new MyTranslateAnimation(view1, view1.getWidth(), 0, 0, 0);
        rightAnimation.setInterpolator(interpolator);
        rightAnimation.setDuration(animationDuration);
        view1.startAnimation(rightAnimation);
    }

    private void hideView1() {
        Animation leftAnimation = new MyTranslateAnimation(view1, 0, view1.getWidth(), 0, 0);
        leftAnimation.setInterpolator(interpolator);
        leftAnimation.setDuration(animationDuration);
        view1.startAnimation(leftAnimation);
    }

    private void growAndMoveView2() {
        Animation rightAnimation = new MyTranslateAnimation(view2, -view1.getWidth(), 0, 0, 0);
        rightAnimation.setInterpolator(interpolator);
        rightAnimation.setDuration(animationDuration);
        view2.startAnimation(rightAnimation);

        Animation shrinkAnimation = new MyScaleAnimation(view2, view2.getWidth(), view2.getHeight(), width - panel, view2.getHeight());
        shrinkAnimation.setInterpolator(interpolator);
        shrinkAnimation.setDuration(animationDuration);
        view2.startAnimation(shrinkAnimation);
    }

    private void shrinkAndMoveView2() {
        Animation leftAnimation = new MyTranslateAnimation(view2, 0, -view1.getWidth(), 0, 0);
        leftAnimation.setInterpolator(interpolator);
        leftAnimation.setDuration(animationDuration);
        view2.startAnimation(leftAnimation);

        Animation shrinkAnimation = new MyScaleAnimation(view2, view2.getWidth(), view2.getHeight(), view1.getWidth(), view2.getHeight());
        shrinkAnimation.setInterpolator(interpolator);
        shrinkAnimation.setDuration(animationDuration);
        view2.startAnimation(shrinkAnimation);
    }

}
