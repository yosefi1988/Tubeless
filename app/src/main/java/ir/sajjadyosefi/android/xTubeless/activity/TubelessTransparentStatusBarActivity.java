package ir.sajjadyosefi.android.xTubeless.activity;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.readystatesoftware.systembartint.SystemBarTintManager;

import ir.sajjadyosefi.android.xTubeless.R;
import it.sephiroth.android.library.bottomnavigation.BottomNavigation;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

//import com.crashlytics.android.Crashlytics;
//import io.fabric.sdk.android.Fabric;

public abstract class TubelessTransparentStatusBarActivity extends TubelessActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        //1
//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);

        //2
        //https://medium.com/@sandeeptengale/problem-solved-3-android-full-screen-view-translucent-scrollview-adjustresize-keyboard-b0547c7ced32
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
//            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(Color.TRANSPARENT);
        }
    }


}
