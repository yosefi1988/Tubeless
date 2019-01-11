package ir.sajjadyosefi.android.tubeless.adapter.fragment;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import ir.sajjadyosefi.android.tubeless.view.fragment.minor.FragmentTimelineMinor;

/**
 * Created by sajjad on 10/18/2016.
 */
public class FragmentPoliceAdapter extends FragmentPagerAdapter {
    final int PAGE_COUNT = 2;
    public static int LIST_TIMELINE = 0 ;
    public static int LIST_BLOG = 1 ;

    Context context;
    private String mTabTitles[] = new String[] {"اخبار سوخت و پلیس راهور","قوانین و مقررات"};
    public static int mTab1 = 0 ;
    public static int mTab2 = 0 ;


    public FragmentPoliceAdapter(Context context, FragmentManager supportFragmentManager) {
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
                return mainHomePageFragment12.newInstance(context,position,LIST_BLOG ,2);
            case 1:
                FragmentTimelineMinor mainHomePageFragment1 = new FragmentTimelineMinor();
                return mainHomePageFragment1.newInstance(context,position,LIST_BLOG ,3);

        }
        return null;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        // Generate title based on item position
        return mTabTitles[position];
    }
}