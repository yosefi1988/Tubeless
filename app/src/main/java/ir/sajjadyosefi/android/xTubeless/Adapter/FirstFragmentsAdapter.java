package ir.sajjadyosefi.android.xTubeless.Adapter;

import android.content.Context;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import java.util.List;

import ir.sajjadyosefi.android.xTubeless.BuildConfig;
import ir.sajjadyosefi.android.xTubeless.Fragment.BlankFragment;
import ir.sajjadyosefi.android.xTubeless.Fragment.ListFragment;
import ir.sajjadyosefi.android.xTubeless.bussines.post.fragment.SearchByNameFragment;

/**
 * Created by sajjad on 10/18/2016.
 */
public class FirstFragmentsAdapter extends FragmentPagerAdapter {
    Context context;

    int PAGE_COUNT;
    private String mTabTitles[] = new String[] {
            "\uE802",
            "\uE801",
            "\uE804",
            "\uE800",
            "\uE803"};

    public static int TYPE_TUBELESS_NEWS  = 4;
    public static int TYPE_YAFTE  = 3;
    public static int TYPE_YADAK  = 2;
    public static int TYPE_IMAGE  = 1;
    public static int TYPE_POST_SEARCH_RESULT  = 4;
    public static int TYPE_SEARCH_POST_BY_NAME  = 5;

    public FirstFragmentsAdapter(Context context, ViewPager viewPager, FragmentManager supportFragmentManager) {
        super(supportFragmentManager);
        this.context = context ;
        setCount();
    }

    public FirstFragmentsAdapter(final AppCompatActivity activity, int count) {
        super(activity.getSupportFragmentManager());
        setCount();
    }

    public FirstFragmentsAdapter(Context context,final AppCompatActivity activity, int count) {
        super(activity.getSupportFragmentManager());
        this.context = context;
        setCount();
    }

    private void setCount() {
        if (BuildConfig.FLAVOR_version_name.equals("tubeless")){
            PAGE_COUNT = 2;
        }else if (BuildConfig.FLAVOR_version_name.equals("yafte")){
            PAGE_COUNT = 3;
        }else if (BuildConfig.FLAVOR_version_name.equals("yadak")){
            PAGE_COUNT = 2;
        }else {
            PAGE_COUNT = 2;
        }
    }

    @Override
    public int getCount() {
        return PAGE_COUNT;
    }

    public int notifyList() {
        if (fragmentx1 instanceof ListFragment)
            ((ListFragment)fragmentx1).refreshForAdmin();

        if (fragmentx2 instanceof ListFragment)
            ((ListFragment)fragmentx2).refreshForAdmin();

        if (fragmentx3 instanceof ListFragment)
            ((ListFragment)fragmentx3).refreshForAdmin();
        return 0;
    }


    Fragment fragmentx1;
    Fragment fragmentx2;
    Fragment fragmentx3;

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
//                FragmentNotifications d33333 = new FragmentNotifications();
//                fragmentx = d33333.newInstance(position,LIST_TIMELINE );

//                fragment = new ListFragment(context);


                if (BuildConfig.FLAVOR_version_name.equals("tubeless")){
                    //todo uncomment
//                    fragmentx1 = new ListFragment(context,TYPE_YAFTE);

                    //todo comment
                    fragmentx1 = new ListFragment(context,TYPE_YADAK);

                }else if (BuildConfig.FLAVOR_version_name.equals("yafte")){
                    fragmentx1 = new SearchByNameFragment();
                }else if (BuildConfig.FLAVOR_version_name.equals("yadak")){
                    fragmentx1 = new ListFragment(context,TYPE_YADAK);
                }else {
                    fragmentx1 = new ListFragment(context,TYPE_YAFTE);
                }
                return fragmentx1;

            case 1:

                if (BuildConfig.FLAVOR_version_name.equals("tubeless")){
                    fragmentx2 = new ListFragment(context,TYPE_YADAK);
                }else if (BuildConfig.FLAVOR_version_name.equals("yafte")){
                    fragmentx2 = new ListFragment(context,TYPE_YAFTE);
                }else if (BuildConfig.FLAVOR_version_name.equals("yadak")){
                    fragmentx2 = new ListFragment(context,TYPE_YAFTE);
                }else {
                    fragmentx2 = new ListFragment(context,TYPE_YADAK);
                }

                return fragmentx2;

            case 2:
//                FragmentProfile mainHomePageFragmentxz = new FragmentProfile();
//                fragment = mainHomePageFragmentxz.newInstance(context,position,LIST_TIMELINE );
//
//                FragmentYafteha fragmentNotifications = new FragmentYafteha();
//                fragment = fragmentNotifications.newInstance(context,position,LIST_TIMELINE );

                if (BuildConfig.FLAVOR_version_name.equals("tubeless")){
                    fragmentx3 = new BlankFragment();
                }else if (BuildConfig.FLAVOR_version_name.equals("yafte")){
                    fragmentx3 = new ListFragment(context,TYPE_YADAK);
                }else if (BuildConfig.FLAVOR_version_name.equals("yadak")){
                    fragmentx3 = new BlankFragment();
                }else{
                    fragmentx3 = new BlankFragment();
                }
                return fragmentx3;
        }
        return null;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        // Generate text based on item position
        return mTabTitles[position];
    }
}