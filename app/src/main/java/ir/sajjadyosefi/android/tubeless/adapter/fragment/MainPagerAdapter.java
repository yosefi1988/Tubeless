package ir.sajjadyosefi.android.tubeless.adapter.fragment;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import ir.sajjadyosefi.android.tubeless.view.fragment.FragmentYafteha;
import ir.sajjadyosefi.android.tubeless.view.fragment.major.FragmentTimeline;
import ir.sajjadyosefi.android.tubeless.view.fragment.profile.FragmentProfile;

/**
 * Created by sajjad on 10/18/2016.
 */
public class MainPagerAdapter extends FragmentPagerAdapter
{



    Context context;
    private final FragmentManager mFragmentManager;
    private final ViewPager mViewPager;

    final int PAGE_COUNT = 3;
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
            case 0: //Home
                FragmentTimeline fragmentTimeline  = new FragmentTimeline(context);
                //mahanApplicationGlobal.CurrentFragment = mahanApplicationGlobal.homeFragment;
                return fragmentTimeline;
            case 1: //Yafteh
                FragmentYafteha fragmentTimeline2  = new FragmentYafteha(context);
                //mahanApplicationGlobal.CurrentFragment = mahanApplicationGlobal.homeFragment;
                return fragmentTimeline2;
            case 2: //Home
                FragmentProfile fragmentTimeline3  = new FragmentProfile(context);
                //mahanApplicationGlobal.CurrentFragment = mahanApplicationGlobal.homeFragment;
                return fragmentTimeline3;

//                FragmentPolice fragmentTimeline2  = new FragmentPolice(context);
//                //mahanApplicationGlobal.CurrentFragment = mahanApplicationGlobal.homeFragment;
//                return fragmentTimeline2;
//            case 2: //Home
//                FragmentMashinbaz fragmentTimeline3  = new FragmentMashinbaz(context);
//                //mahanApplicationGlobal.CurrentFragment = mahanApplicationGlobal.homeFragment;
//                return fragmentTimeline3;
//            case 3: //Home
//                FragmentGashtogozar fragmentTimeline4  = new FragmentGashtogozar(context);
//                //mahanApplicationGlobal.CurrentFragment = mahanApplicationGlobal.homeFragment;
//                return fragmentTimeline4;
//            case 4: //Home
//                FragmentTamirgah fragmentTimeline5  = new FragmentTamirgah(context);
//                //mahanApplicationGlobal.CurrentFragment = mahanApplicationGlobal.homeFragment;
//                return fragmentTimeline5;
////            case 1: //firends
////                FragmentFirendRequests firendRequests = new FragmentFirendRequests(context,mainViewPager);
////                //mahanApplicationGlobal.CurrentFragment = mahanApplicationGlobal.firendFragment;
////                return firendRequests;
////            case 2: //chat
////                FragmentChats fragmentChats = new FragmentChats(context);
//////                mahanApplicationGlobal.CurrentFragment = mahanApplicationGlobal.chatFragment;
////                return fragmentChats ;
////            case 3: //Notifi
////                FragmentNotifications fragmentNotifications  = new FragmentNotifications(context);
//////                mahanApplicationGlobal.CurrentFragment = mahanApplicationGlobal.notifFragment;
////                return fragmentNotifications;
////            case 4: //Profile
////                FragmentTimeline fragmentTimeline2  = new FragmentTimeline(context);
//////                mahanApplicationGlobal.CurrentFragment = mahanApplicationGlobal.homeFragment;
////                return fragmentTimeline2;
////            default:
////                FragmentNotifications notificationsPageFragmentSajjadTest5  = new FragmentNotifications(context);
////                return notificationsPageFragmentSajjadTest5;
        }
        return null;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        // Generate title based on item position
        return mTabTitles[position];
    }
}