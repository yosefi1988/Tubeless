package ir.sajjadyosefi.android.xTubeless.activity;


import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.FragmentTransaction;

import ir.sajjadyosefi.android.tubeless.R;
import ir.sajjadyosefi.android.tubeless.view.fragment.profile.FragmentProfile;
import ir.sajjadyosefi.android.tubeless.view.xFragment.FragmentNotifications;


public class ContainerActivity extends TubelessActivity {

    public static Context mContext;
    public static int type = 0 ;


    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.x_activity_container);
        mContext = this;

        if (type == 1){
//            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
//            FragmentAbouteUs fragmentDemo = FragmentAbouteUs.newInstance();
//            ft.replace(R.id.output, fragmentDemo);
//            ft.commit();

            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            FragmentNotifications fragmentDemo = FragmentNotifications.newInstance(1,1);
            ft.replace(R.id.output, fragmentDemo);
            ft.commit();

        }else if (type == 2){
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            FragmentProfile fragmentDemo = FragmentProfile.newInstance(getContext() ,1,1);
            ft.replace(R.id.output, fragmentDemo);
            ft.commit();
        }else {
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            FragmentNotifications fragmentDemo = FragmentNotifications.newInstance(1,1);
            ft.replace(R.id.output, fragmentDemo);
            ft.commit();
        }

        Activity ddd = getActivity();
        Context ssss = getContext();
        View view = getRootActivity();

        Activity ddd2 = (Activity) getContext();
        Context ssss2 = getActivity();

        int a = 5 ;
        a++;
    }


    @Override
    protected void onStart() {
        super.onStart();


        int a = 5 ;
        a++;
    }



}