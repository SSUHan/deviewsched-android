package com.gdgssu.android_deviewsched.util;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

import com.gdgssu.android_deviewsched.R;

public class DeviewTextView extends TextView {
    public DeviewTextView(Context context) {
        super(context);
    }

    public DeviewTextView(Context context, AttributeSet attrs) {
        super(context, attrs);

        init(attrs);
    }

    public DeviewTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        init(attrs);
    }

    private void init(AttributeSet attrs) {
        if (attrs!=null) {
            TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable.DeviewTextView);
            String fontName = a.getString(R.styleable.DeviewTextView_fontName);
            if (fontName!=null) {
                Typeface myTypeface = Typeface.createFromAsset(getContext().getAssets(), "fonts/" + fontName);
                setTypeface(myTypeface);
            }
            a.recycle();
        }
    }
}
