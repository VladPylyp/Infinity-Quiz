package com.stormarts.infinityquiz.ui.widget;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

public class BebasTextView extends android.support.v7.widget.AppCompatTextView {
    public BebasTextView(Context context) {
        super(context);
        setTypeface();
    }

    public BebasTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setTypeface();
    }

    public BebasTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setTypeface();
    }

    private void setTypeface(){
        Typeface typeFace=Typeface.createFromAsset(getContext().getAssets(),"fonts/bebas.ttf");
        this.setTypeface(typeFace);
    }
}
