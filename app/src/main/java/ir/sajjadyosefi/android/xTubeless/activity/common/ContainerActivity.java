package ir.sajjadyosefi.android.xTubeless.activity.common;


import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import com.google.android.material.appbar.AppBarLayout;
import com.readystatesoftware.systembartint.SystemBarTintManager;

import ir.sajjadyosefi.android.xTubeless.Fragment.BlankFragment;
import ir.sajjadyosefi.android.xTubeless.Fragment.ListFragment;
import ir.sajjadyosefi.android.xTubeless.R;
import ir.sajjadyosefi.android.xTubeless.activity.TubelessActivity;
import ir.sajjadyosefi.android.xTubeless.activity.TubelessTransparentStatusBarActivity;
import ir.sajjadyosefi.android.xTubeless.activity.account.LoginActivity;
import it.sephiroth.android.library.bottomnavigation.BottomNavigation;

import static ir.sajjadyosefi.android.xTubeless.Adapter.FirstFragmentsAdapter.TYPE_POST_SEARCH_RESULT;


public class ContainerActivity extends TubelessTransparentStatusBarActivity {

    private Context mContext;
    private int type = 0 ;
    private Toolbar toolbar;


    //singletone instance
    private static ContainerActivity containerActivity;

    //singletone
    public synchronized static ContainerActivity getInstance(){
        if (containerActivity == null){
            containerActivity = new ContainerActivity();
        }
        return containerActivity;
    }

    //default constractor
    public ContainerActivity() { }


    public synchronized static Intent getIntent(Context context) {
        return getIntent(context,null);
    }

    public synchronized static Intent getIntent(Context context, Bundle bundle) {
        bundle.putString("item1","value1");
        Intent intent = new Intent(context,ContainerActivity.class);
        intent.putExtras(bundle);
        return intent;
    }


    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.x_activity_container);
        mContext = this;
        type = getIntent().getIntExtra("type",0);


        if (type == TYPE_POST_SEARCH_RESULT){
//            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
//            FragmentAbouteUs fragmentDemo = FragmentAbouteUs.newInstance();
//            ft.replace(R.id.output, fragmentDemo);
//            ft.commit();

            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.include, new ListFragment(getContext(),2));
//            ft.replace(R.id.include, new BlankFragment(getContext()));
            ft.commit();
        }else if (type == 1){

            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
//            FragmentNotifications fragmentDemo = FragmentNotifications.newInstance(1,1);
//            ft.replace(R.id.output, fragmentDemo);
            ft.commit();

        }else if (type == 2){
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
//            FragmentProfile fragmentDemo = FragmentProfile.newInstance(getContext() ,1,1);
//            ft.replace(R.id.output, fragmentDemo);
            ft.commit();
//        }else {
//            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
//            FragmentNotifications fragmentDemo = FragmentNotifications.newInstance(1,1);
//            ft.replace(R.id.output, fragmentDemo);
//            ft.commit();
        } else {
            // Begin the transaction
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            // Replace the contents of the container with the new fragment
            ft.replace(R.id.include, prepareFragment());
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



    Fragment prepareFragment() {

        int id = getIntent().getExtras().getInt("id");
        String term = getIntent().getExtras().getString("term");

        Bundle args = new Bundle();
        args.putString("term", term);
        args.putInt("id", id);
//        FragmentTimelineMinor fragment = new FragmentTimelineMinor();
//        fragment.setArguments(args);
        //this.values = context.getSharedPreferences(Statics.MAHAN, 0);
//        return fragment;
        return null;
    }




    @Override
    public SystemBarTintManager getSystemBarTint() {
        return null;
    }

    @Override
    public boolean hasTranslucentNavigation() {
        return false;
    }

    @Override
    public boolean hasTranslucentStatusBar() {
        return false;
    }

    @Override
    public BottomNavigation getBottomNavigation() {
        return null;
    }

    @Override
    public int getNavigationBarHeight() {
        return 0;
    }

    @Override
    public boolean hasManagedToolbarScroll() {
        return false;
    }

    @Override
    public boolean hasAppBarLayout() {
        return false;
    }

    @Override
    public Toolbar getToolbar() {
        return null;
    }







}