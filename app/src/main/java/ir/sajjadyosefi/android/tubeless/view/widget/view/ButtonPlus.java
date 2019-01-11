package ir.sajjadyosefi.android.tubeless.view.widget.view;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.Button;

/**
 * Created by sajjad on 10/24/2016.
 */
public class ButtonPlus extends Button {




    public ButtonPlus(Context context) {
        super(context);

        Typeface typeface = Typeface.createFromAsset(((context)).getAssets(), "BYekan.ttf");
        this.setTypeface(typeface);

    }

    public ButtonPlus(Context context, AttributeSet attrs) {
        super(context, attrs);
        Typeface typeface = Typeface.createFromAsset(((context)).getAssets(), "BYekan.ttf");
        this.setTypeface(typeface);
    }

    public ButtonPlus(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        Typeface typeface = Typeface.createFromAsset(((context)).getAssets(), "BYekan.ttf");
        this.setTypeface(typeface);
    }
}
