package com.example.hotelapp.utils;

import android.text.InputFilter;
import android.text.Spanned;

public class InputFilterMin implements InputFilter {
    private float min;

    public InputFilterMin(float min) {
        this.min = min;
    }

    public InputFilterMin(String min) {
        this.min = Float.parseFloat(min);
    }

    @Override
    public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
        //noinspection EmptyCatchBlock
        try {
            float input = Float.parseFloat(dest.toString() + source.toString());
            if (isInRange(min, input))
                return null;
        } catch (NumberFormatException nfe) {
        }
        return "";
    }

    private boolean isInRange(float a, float b) {
        return a <= b;
    }

}
