package ir.sajjadyosefi.android.tubeless.adapter.fragment;

import android.content.Context;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;


import ir.sajjadyosefi.android.tubeless.view.fragment.minor.FragmentTimelineMinor;

/**
 * Created by sajjad on 10/18/2016.
 */
public class FragmentGashtogozarAdapter extends FragmentPagerAdapter {
    final int PAGE_COUNT = 1;
    public static int LIST_TIMELINE = 0 ;
    public static int LIST_BLOG = 1 ;

    Context context;
    private String mTabTitles[] = new String[] {"توصیه ها"};
    public static int mTab1 = 0 ;


    public FragmentGashtogozarAdapter(Context context, FragmentManager supportFragmentManager) {
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
//                FragmentChats mainHomePageFragment = new FragmentChats();
//                return mainHomePageFragment;
//                //return mainHomePageFragment.newInstance(context,position );

//                FragmentTimelineMinor mainHomePageFragment2 = new FragmentTimelineMinor();
//                return mainHomePageFragment2.newInstance(context,position,LIST_TIMELINE );

                FragmentTimelineMinor mainHomePageFragment12 = new FragmentTimelineMinor();
                return mainHomePageFragment12.newInstance(context,position,LIST_BLOG ,14);
            case 1:
                FragmentTimelineMinor mainHomePageFragment1 = new FragmentTimelineMinor();
                return mainHomePageFragment1.newInstance(context,position,LIST_BLOG ,14);

        }
        return null;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        // Generate text based on item position
        return mTabTitles[position];
    }
}