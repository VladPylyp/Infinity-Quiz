package com.stormarts.infinityquiz.ui.widget;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.Button;

public class BebasButton extends android.support.v7.widget.AppCompatButton {


    public BebasButton(Context context) {
        super(context);
        setTypeface();
    }

    public BebasButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        setTypeface();
    }

    public BebasButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setTypeface();
    }

    private void setTypeface(){
        Typeface typeFace=Typeface.createFromAsset(getContext().getAssets(),"fonts/bebas.ttf");
        this.setTypeface(typeFace);
    }




}
