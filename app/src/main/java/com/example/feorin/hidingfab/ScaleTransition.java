package com.example.feorin.hidingfab;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.transition.TransitionValues;
import android.transition.Visibility;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by feorin on 2016-08-27.
 */
public class ScaleTransition extends Visibility {


    @Override
    public Animator onAppear(ViewGroup sceneRoot, View view, TransitionValues startValues, TransitionValues endValues) {
        return createScaleTransitionAnimator(view, 0, 1);
    }

    @Override
    public Animator onDisappear(ViewGroup sceneRoot, View view, TransitionValues startValues, TransitionValues endValues) {
        return createScaleTransitionAnimator(view, 1, 0);
    }

    public Animator createScaleTransitionAnimator(View view, int startValue, int endValue){
        AnimatorSet scaleSet = new AnimatorSet();

        ObjectAnimator xScaleAnimator = ObjectAnimator.ofFloat(view, View.SCALE_X, startValue, endValue);
        ObjectAnimator yScaleAnimator = ObjectAnimator.ofFloat(view, View.SCALE_Y, startValue, endValue);

        scaleSet.playTogether(xScaleAnimator, yScaleAnimator);
        return scaleSet;

    }


}

