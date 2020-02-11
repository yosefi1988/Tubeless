package ir.sajjadyosefi.android.xTubeless.activity.common;


import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import ir.sajjadyosefi.android.tubeless.R;
import ir.sajjadyosefi.android.tubeless.view.fragment.minor.FragmentTimelineMinor;
import ir.sajjadyosefi.android.tubeless.view.fragment.profile.FragmentProfile;
import ir.sajjadyosefi.android.tubeless.view.xFragment.FragmentNotifications;
import ir.sajjadyosefi.android.xTubeless.activity.TubelessActivity;


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
//        }else {
//            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
//            FragmentNotifications fragmentDemo = FragmentNotifications.newInstance(1,1);
//            ft.replace(R.id.output, fragmentDemo);
//            ft.commit();
        }else {
            // Begin the transaction
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            // Replace the contents of the container with the new fragment
            ft.replace(R.id.output, prepareFragment());
            // or ft.add(R.id.your_placeholder, new FooFragment());
            // Complete the changes added above
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


    Fragment prepareFragment() {

        int id = getIntent().getExtras().getInt("id");
        String term = getIntent().getExtras().getString("term");

        Bundle args = new Bundle();
        args.putString("term", term);
        args.putInt("id", id);
        FragmentTimelineMinor fragment = new FragmentTimelineMinor();
        fragment.setArguments(args);
        //this.values = context.getSharedPreferences(Statics.MAHAN, 0);
        return fragment;
    }

}