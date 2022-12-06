package com.example.hotelapp.utils;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ListView;

public class ListViewFitHeight extends ListView {
    public ListViewFitHeight(Context context) {
        super(context);
    }

    public ListViewFitHeight(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ListViewFitHeight(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,
                MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpec);
    }
}
