package ir.sajjadyosefi.android.tubeless.view.widget.view;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

import ir.sajjadyosefi.android.tubeless.R;

/**
 * Created by sajjad on 06/07/2016.
 */
public class PersianTextView extends TextView {
    public PersianTextView(Context context) {
        super(context);
    }

    public PersianTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }
    public PersianTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

    }

    public PersianTextView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }


    @Override
    public void setText(CharSequence text, BufferType type) {
        super.setText(text, type);
        Typeface font = Typeface.createFromAsset(getContext().getAssets(), "BYekan.ttf");
        setTypeface(font);
    }
}
