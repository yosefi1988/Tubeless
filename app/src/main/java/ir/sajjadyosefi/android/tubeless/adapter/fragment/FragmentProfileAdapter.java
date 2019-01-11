package ir.sajjadyosefi.android.tubeless.adapter.fragment;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import ir.sajjadyosefi.android.tubeless.view.fragment.BlankFragment;
import ir.sajjadyosefi.android.tubeless.view.fragment.minor.FragmentTimelineMinor;
import ir.sajjadyosefi.android.tubeless.view.fragment.myCarFragment;
import ir.sajjadyosefi.android.tubeless.view.fragment.profile.FragmentMyCars;

/**
 * Created by sajjad on 10/18/2016.
 */
public class FragmentProfileAdapter extends FragmentPagerAdapter {
    final int PAGE_COUNT = 2;
    public static int LIST_TIMELINE = 0 ;
    public static int LIST_BLOG = 1 ;

    Context context;
    private String mTabTitles[] = new String[] {"پروفایل من","ردیابی","پیداشده ها"};
    public static int mTab1 = 0 ;
    public static int mTab2 = 0 ;
    public static int mTab3 = 0 ;


    public FragmentProfileAdapter(Context context, FragmentManager supportFragmentManager) {
        super(supportFragmentManager);
        this.context = context ;
    }

    @Override
    public int getCount() {
        return PAGE_COUNT;
    }
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                myCarFragment fragmentMyCars = new myCarFragment();
                return fragmentMyCars.newInstance(context);
            case 1:
                BlankFragment fragmentMyCarsx = new BlankFragment();
                return fragmentMyCarsx.newInstance(context);
            default:
            case 2:
                BlankFragment fragmentMyCarsc = new BlankFragment();
                return fragmentMyCarsc.newInstance(context);

        }
    }

    @Override
    public CharSequence getPageTitle(int position) {
        // Generate title based on item position
        return mTabTitles[position];
    }
}