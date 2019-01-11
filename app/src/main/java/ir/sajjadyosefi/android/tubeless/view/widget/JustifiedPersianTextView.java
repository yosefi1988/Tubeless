package ir.sajjadyosefi.android.tubeless.view.widget;


        import android.content.Context;
        import android.graphics.Typeface;
        import android.util.AttributeSet;
        import android.widget.TextView;

        import com.uncopt.android.widget.text.justify.JustifiedTextView;

/**
 * Created by sajjad on 9/14/2017.
 */


public class JustifiedPersianTextView extends JustifiedTextView {
    public JustifiedPersianTextView(Context context) {
        super(context);
    }

    public JustifiedPersianTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public JustifiedPersianTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @Override
    public void setText(CharSequence text, BufferType type) {
        super.setText(text, type);
        Typeface font = Typeface.createFromAsset(getContext().getAssets(), "BYekan.ttf");
        setTypeface(font);
    }
}
