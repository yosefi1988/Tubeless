package ir.sajjadyosefi.android.xTubeless.Adapter;

import android.content.Context;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import ir.sajjadyosefi.android.xTubeless.Fragment.ListFragment;

/**
 * Created by sajjad on 10/18/2016.
 */
public class FirstFragmentsAdapter extends FragmentPagerAdapter {
    Context context;

    final int PAGE_COUNT = 2;
    private String mTabTitles[] = new String[] {
            "\uE802",
            "\uE801",
            "\uE804",
            "\uE800",
            "\uE803"};

    public static int TYPE_YAFTE  = 3;
    public static int TYPE_YADAK  = 2;
    public static int TYPE_IMAGE  = 1;
    public static int TYPE_POST_SEARCH_RESULT  = 4;

    public FirstFragmentsAdapter(Context context, ViewPager viewPager, FragmentManager supportFragmentManager) {
        super(supportFragmentManager);
        this.context = context ;
    }

    public FirstFragmentsAdapter(final AppCompatActivity activity, int count) {
        super(activity.getSupportFragmentManager());
    }

    public FirstFragmentsAdapter(Context context,final AppCompatActivity activity, int count) {
        super(activity.getSupportFragmentManager());
        this.context = context;
    }

    @Override
    public int getCount() {
        return PAGE_COUNT;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                Fragment fragmentx;
//                FragmentNotifications d33333 = new FragmentNotifications();
//                fragmentx = d33333.newInstance(position,LIST_TIMELINE );
//
//                fragment = new ListFragment(context);
                fragmentx = new ListFragment(context,TYPE_YAFTE);
                return fragmentx;

            case 1:
                return new ListFragment(context,TYPE_YADAK);

            case 2:
                Fragment fragment;
//
//                FragmentProfile mainHomePageFragmentxz = new FragmentProfile();
//                fragment = mainHomePageFragmentxz.newInstance(context,position,LIST_TIMELINE );
//
//                FragmentYafteha fragmentNotifications = new FragmentYafteha();
//                fragment = fragmentNotifications.newInstance(context,position,LIST_TIMELINE );
//
                fragment = new ListFragment(context,TYPE_IMAGE);
                return fragment;
        }
        return null;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        // Generate text based on item position
        return mTabTitles[position];
    }
}