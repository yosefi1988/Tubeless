package ir.sajjadyosefi.android.tubeless.adapter.xFragments;

import android.content.Context;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

/**
 * Created by sajjad on 10/18/2016.
 */
public class MainPagerAdapter extends FragmentPagerAdapter
{



    Context context;
    private final FragmentManager mFragmentManager;
    private final ViewPager mViewPager;

    final int PAGE_COUNT = 2;
    private String mTabTitles[] = new String[] {
            "\uE802",
            "\uE801",
            "\uE804",
            "\uE800",
            "\uE803"};




    public MainPagerAdapter(Context context, ViewPager viewPager, FragmentManager supportFragmentManager) {
        super(supportFragmentManager);
        this.context = context ;
        this.mFragmentManager = supportFragmentManager ;
        this.mViewPager = viewPager ;
    }

    @Override
    public int getCount() {
        return PAGE_COUNT;
    }



    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0: //timeline
//                TimelineFragment mainHomePageFragment2 = new TimelineFragment();
//                return mainHomePageFragment2.newInstance(context,position,LIST_TIMELINE );

//                return new ListFragment(context);

            case 1: //Yafteh
//                FragmentYafteha fragmentTimeline2 = new FragmentYafteha();
//                return fragmentTimeline2.newInstance(context,position,LIST_TIMELINE );
//                return new ListFragment(context);

//            case 2: //Profile
//                FragmentProfile mainHomePageFragmentxz = new FragmentProfile();
//                return mainHomePageFragmentxz.newInstance(context,position,LIST_TIMELINE );

        }
        return null;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        // Generate text based on item position
        return mTabTitles[position];
    }
}