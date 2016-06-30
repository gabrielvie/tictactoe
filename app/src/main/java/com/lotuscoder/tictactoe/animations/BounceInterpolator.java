package com.lotuscoder.tictactoe.animations;

import android.view.animation.Interpolator;

/**
 * Created by gabrielvie on 29/06/16.
 */
public class BounceInterpolator implements Interpolator {
    private double mAmplitude = 0.2;
    private double mFrequency = 20;

    public BounceInterpolator(double amplitude, double frequency) {
        this.mAmplitude = amplitude;
        this.mFrequency = frequency;
    }

    public BounceInterpolator() {}


    public float getInterpolation(float time) {
        return (float) (-1 * Math.pow(Math.E, -time / this.mAmplitude) * Math.cos(this.mFrequency * time) + 1);
    }
}
