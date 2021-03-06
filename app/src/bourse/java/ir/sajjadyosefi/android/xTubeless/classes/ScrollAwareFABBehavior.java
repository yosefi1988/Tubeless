package ir.sajjadyosefi.android.xTubeless.classes;

import android.content.Context;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.view.ViewCompat;
import android.util.AttributeSet;
import android.view.View;


public class ScrollAwareFABBehavior extends FloatingActionButton.Behavior {
    Context cx;

    public ScrollAwareFABBehavior() {
    }

    public ScrollAwareFABBehavior(Context context, AttributeSet attributeSet) {
        super();
        cx = context;
    }

    @Override
    public boolean onStartNestedScroll(CoordinatorLayout coordinatorLayout, FloatingActionButton child, View directTargetChild, View target, int nestedScrollAxes) {
        return nestedScrollAxes == ViewCompat.SCROLL_AXIS_VERTICAL;
    }

    @Override
    public void onNestedScroll(CoordinatorLayout coordinatorLayout, FloatingActionButton child, View target, int dxConsumed, int dyConsumed, int dxUnconsumed, int dyUnconsumed) {
        super.onNestedScroll(coordinatorLayout, child, target, dxConsumed, dyConsumed, dxUnconsumed, dyUnconsumed);


//        SharedPreferences values = cx.getSharedPreferences(Statics.MAHAN, 0);
//        if (values.getBoolean("fab", false)) {
//            if (dyConsumed > 0 && child.getVisibility() == View.VISIBLE) {
//                child.hide();
//            } else if (dyConsumed < 0 && child.getVisibility() == View.GONE) {
//                child.show();
//            }
//        }
    }
}