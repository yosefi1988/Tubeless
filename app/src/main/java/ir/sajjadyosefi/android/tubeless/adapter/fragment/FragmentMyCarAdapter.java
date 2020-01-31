package ir.sajjadyosefi.android.tubeless.adapter.fragment;

import android.content.Context;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import ir.sajjadyosefi.android.tubeless.view.fragment.AddCarFragment;
import ir.sajjadyosefi.android.tubeless.view.fragment.BlankFragment;
import ir.sajjadyosefi.android.tubeless.view.fragment.CarDetailsFragment;
import ir.sajjadyosefi.android.tubeless.view.fragment.myCarFragment;

/**
 * Created by sajjad on 10/18/2016.
 */
public class FragmentMyCarAdapter extends FragmentPagerAdapter {
    public static int LIST_TIMELINE = 0 ;
    public static int LIST_BLOG = 1 ;

    Context context;
    public static int mTab1 = 0 ;
    public static int mTab2 = 0 ;
    public static int mTab3 = 0 ;


    public FragmentMyCarAdapter(Context context, FragmentManager supportFragmentManager) {
        super(supportFragmentManager);
        this.context = context ;
    }

    @Override
    public int getCount() {
        return myCarFragment.PAGE_COUNT;
    }
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                AddCarFragment fragmentMyCars = new AddCarFragment();
                return fragmentMyCars.newInstance(context);
            default:
                CarDetailsFragment fragmentMyCarsx = new CarDetailsFragment();
                return fragmentMyCarsx.newInstance(context);
        }
    }

    @Override
    public CharSequence getPageTitle(int position) {
        // Generate text based on item position
        return myCarFragment.mTabTitles[position];
    }
}