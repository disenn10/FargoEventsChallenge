package com.myriadandroidchallenge.fargoevents.View;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;

/**
 * Created by disen on 2/13/2019.
 */

public class Custom_ImageView extends android.support.v7.widget.AppCompatImageView{
    public Custom_ImageView(Context context) {
        super(context);
    }

    public Custom_ImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public Custom_ImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int threeTwoheight = View.MeasureSpec.getSize(widthMeasureSpec) * 2 / 3;
        int threeTwoheightSpec = View.MeasureSpec.makeMeasureSpec(threeTwoheight, View.MeasureSpec.EXACTLY);
        super.onMeasure(widthMeasureSpec, threeTwoheightSpec);

    }
}
