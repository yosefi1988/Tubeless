package ir.sajjadyosefi.android.tubeless.classes.business;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import ir.sajjadyosefi.android.tubeless.R;
import ir.sajjadyosefi.android.xTubeless.activity.MainActivity;


/**
 * Created by sajjad on 9/28/2018.
 */

public class Navigator {


    public static void redirectToMainActivity(Context context){
        Intent intent = new Intent(context, MainActivity.class);
        ((Activity)context).startActivity(intent);
        ((Activity)context).overridePendingTransition(R.anim.fadeout, R.anim.fadein);
        ((Activity)context).finish();
    }

}
