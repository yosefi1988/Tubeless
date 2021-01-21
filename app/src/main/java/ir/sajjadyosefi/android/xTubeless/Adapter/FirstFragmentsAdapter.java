package ir.sajjadyosefi.android.xTubeless.Adapter;

import android.content.Context;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import java.util.ArrayList;
import java.util.List;

import ir.sajjadyosefi.android.xTubeless.BuildConfig;
import ir.sajjadyosefi.android.xTubeless.Fragment.BlankFragment;
import ir.sajjadyosefi.android.xTubeless.Fragment.FinancialAccountLimitFragment;
import ir.sajjadyosefi.android.xTubeless.Fragment.TwoLevelListFragment;
import ir.sajjadyosefi.android.xTubeless.bussines.police.fragment.KartesekhtFragment;
import ir.sajjadyosefi.android.xTubeless.classes.model.bourseState.BourseState;

import ir.sajjadyosefi.android.xTubeless.Fragment.ListFragment;
import ir.sajjadyosefi.android.xTubeless.bussines.post.fragment.SearchByNameFragment;
import ir.sajjadyosefi.android.xTubeless.classes.StaticValue;
import ir.sajjadyosefi.android.xTubeless.classes.model.filter.CategoryFiltersNode;
import ir.sajjadyosefi.android.xTubeless.classes.model.post.IItems;

import static ir.sajjadyosefi.android.xTubeless.activity.MainActivity.categoryFiltersNodeList;
import static ir.sajjadyosefi.android.xTubeless.activity.MainActivity.checkResult;

/**
 * Created by sajjad on 10/18/2016.
 */
public class FirstFragmentsAdapter extends FragmentStatePagerAdapter  {
    Context context;
    FragmentManager supportFragmentManager;

    int PAGE_COUNT;
    private String mTabTitles[] = new String[] {
            "\uE802",
            "\uE801",
            "\uE804",
            "\uE800",
            "\uE803"};

    public static int TYPE_TUBELESS_NEWS  = 7;

    public static int TYPE_BOURSE_NEWS = 26;
    public static int TYPE_BOURSE_ANALIZE_All = 25;
    public static int TYPE_BOURSE_ANALIZE_Old = 27;
    public static int TYPE_BOURSE_TRAIN  = 24;

    public static int TYPE_YAFTE  = 3;
    public static int TYPE_YADAK  = 2;
    public static int TYPE_IMAGE  = 1;
    public static int TYPE_POST_SEARCH_RESULT  = 4;
    public static int TYPE_SEARCH_POST_BY_NAME  = 5;
    public static int TYPE_COMMENTS  = 6;
    public static int TYPE_BOURSE_PLANE  = 7;
    public static int TYPE_KSOKHT  = 8;
    public static int TYPE_LIST  = 9;


    public static int TYPE_SELECT_CATEGORY = 101;
    public static int TYPE_LIST_CATEGORY = 102;
    public static int TYPE_LIST_CATEGORIES_DATA = 103;


    ////////////////// Nerkh Rox  ///////////////////////
    public static int TYPE_NERKHROZ_BOURSE = 201;
    /////////////////////////////////////////////////////


    public FirstFragmentsAdapter(Context context, ViewPager viewPager, FragmentManager supportFragmentManager) {
        super(supportFragmentManager);
        this.supportFragmentManager = supportFragmentManager;
        this.context = context ;
        setCount();
    }

    public FirstFragmentsAdapter(final AppCompatActivity activity, int count) {
        super(activity.getSupportFragmentManager());
        setCount();
    }

    public FirstFragmentsAdapter(Context context  ,final AppCompatActivity activity, int count) {
        super(activity.getSupportFragmentManager());
        this.supportFragmentManager = activity.getSupportFragmentManager();
        this.context = context;
        setCount();
    }

    private void setCount() {
        if (BuildConfig.FLAVOR_version_name.equals("tubeless")){
            PAGE_COUNT = 2;
        }else if (BuildConfig.FLAVOR_version_name.equals("yafte")){
            PAGE_COUNT = 3;
        }else if (BuildConfig.FLAVOR_version_name.equals("bourse")){
            PAGE_COUNT = 4;
        }else if (BuildConfig.FLAVOR_version_name.equals("yadak")){
            PAGE_COUNT = 3;
        }else if (BuildConfig.FLAVOR_version_name.equals("kartesokht")){
            PAGE_COUNT = 3;
        }else {
            PAGE_COUNT = 2;
        }
    }

    @Override
    public int getCount() {
        return PAGE_COUNT;
    }

    @Override
    public int getItemPosition(@NonNull Object object) {
//        return super.getItemPosition(object);
        return POSITION_NONE;
    }

    public void notifyList() {
//        if (BuildConfig.FLAVOR_version_name.equals("bourse")){
//            boolean isVip = true;
//            if (isVip){
//                fragmentx3 = new ListFragment(context, TYPE_BOURSE_ANALIZE_All);
//            }
//        }else {
            if (fragmentx1 instanceof ListFragment)
                ((ListFragment) fragmentx1).refreshForAdmin();

            if (fragmentx2 instanceof ListFragment)
                ((ListFragment) fragmentx2).refreshForAdmin();

            if (fragmentx3 instanceof ListFragment)
                ((ListFragment) fragmentx3).refreshForAdmin();
//        }
    }


    Fragment fragmentx1;
    public static Fragment fragmentx2;
    Fragment fragmentx3;
    Fragment fragmentx4;

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
//                FragmentNotifications d33333 = new FragmentNotifications();
//                fragmentx = d33333.newInstance(position,LIST_TIMELINE );
//                fragment = new ListFragment(context);

                if (fragmentx1 == null) {

                    if (BuildConfig.FLAVOR_version_name.equals("tubeless")) {
                        fragmentx1 = new ListFragment(context, TYPE_YAFTE);
                    } else if (BuildConfig.FLAVOR_version_name.equals("yafte")) {
                        fragmentx1 = new SearchByNameFragment();
                    } else if (BuildConfig.FLAVOR_version_name.equals("kartesokht")) {
                        fragmentx1 = new KartesekhtFragment();
                    } else if (BuildConfig.FLAVOR_version_name.equals("bourse")) {
                        fragmentx1 = new ListFragment(context, TYPE_BOURSE_TRAIN);
                    } else if (BuildConfig.FLAVOR_version_name.equals("yadak")) {
                        fragmentx1 = new ListFragment(context, TYPE_YADAK);
                    } else {
                        fragmentx1 = new ListFragment(context, TYPE_YAFTE);
                    }
                }
                return fragmentx1;

            case 1:
                if (fragmentx2 == null) {
                    if (BuildConfig.FLAVOR_version_name.equals("tubeless")) {
                        fragmentx2 = new ListFragment(context, TYPE_YADAK);
                    } else if (BuildConfig.FLAVOR_version_name.equals("yafte")) {
                        fragmentx2 = new ListFragment(context, TYPE_YAFTE);
                    } else if (BuildConfig.FLAVOR_version_name.equals("kartesokht")) {
                        fragmentx2 = new ListFragment(context, TYPE_YAFTE);
                    } else if (BuildConfig.FLAVOR_version_name.equals("bourse")) {
                        fragmentx2 = new ListFragment(context, TYPE_BOURSE_NEWS);
                    } else if (BuildConfig.FLAVOR_version_name.equals("yadak")) {
//                        fragmentx2 = new BlankFragment(supportFragmentManager,this);
                        List<IItems> iItems = new ArrayList<>();
                        CategoryFiltersNode newNode = createRootNode();
                        categoryFiltersNodeList.AddLast(newNode);
                        fragmentx2 = new ListFragment(context,iItems, TYPE_LIST_CATEGORY, categoryFiltersNodeList.getHead());
//                        fragmentx2 = new ListFragment(context, TYPE_YADAK);

                    } else {
                        fragmentx2 = new ListFragment(context, TYPE_YADAK);
                    }
                }
                return fragmentx2;

            case 2:
//                FragmentProfile mainHomePageFragmentxz = new FragmentProfile();
//                fragment = mainHomePageFragmentxz.newInstance(context,position,LIST_TIMELINE );
//
//                FragmentYafteha fragmentNotifications = new FragmentYafteha();
//                fragment = fragmentNotifications.newInstance(context,position,LIST_TIMELINE );

                if (fragmentx3 == null) {
                    if (BuildConfig.FLAVOR_version_name.equals("tubeless")) {
                        fragmentx3 = new BlankFragment();
                    } else if (BuildConfig.FLAVOR_version_name.equals("yafte")) {
                        fragmentx3 = new ListFragment(context, TYPE_YADAK);

                    } else if (BuildConfig.FLAVOR_version_name.equals("kartesokht")) {
                        fragmentx3 = new ListFragment(context, TYPE_YADAK);
                    } else if (BuildConfig.FLAVOR_version_name.equals("bourse")) {
                        if (!checkResult(context, StaticValue.configuration)) {
                            fragmentx3 = new ListFragment(context, TYPE_BOURSE_ANALIZE_All);
                        } else {
                            if (StaticValue.bourseState.totalPayedValue > 0) {
                                if (BourseState.CheckDateIsValid(StaticValue.bourseState.endDate, StaticValue.configuration.getResponseStatus().getDate())) {
                                    fragmentx3 = new ListFragment(context, TYPE_BOURSE_ANALIZE_All);
                                } else {
                                    fragmentx3 = new ListFragment(context, TYPE_BOURSE_ANALIZE_Old);
                                }
                            } else {
//                        fragmentx3 = new FinancialAccountDetailsFragment();

                                //هیچ پرداختی قبلا انجام نداده است
                                fragmentx3 = new FinancialAccountLimitFragment(context);
                            }
                        }
                    } else if (BuildConfig.FLAVOR_version_name.equals("yadak")) {
                        fragmentx3 = new ListFragment(context, TYPE_YAFTE);
                    } else {
                        fragmentx3 = new BlankFragment();
                    }
                }
                return fragmentx3;

            case 3:
//                FragmentProfile mainHomePageFragmentxz = new FragmentProfile();
//                fragment = mainHomePageFragmentxz.newInstance(context,position,LIST_TIMELINE );
//
//                FragmentYafteha fragmentNotifications = new FragmentYafteha();
//                fragment = fragmentNotifications.newInstance(context,position,LIST_TIMELINE );

                if (fragmentx4 == null) {
                    if (BuildConfig.FLAVOR_version_name.equals("bourse")) {
//                        fragmentx4 = new BourseDataFragment(context);
                        fragmentx4 = new TwoLevelListFragment();
                    } else {
                        fragmentx4 = new BlankFragment();
                    }
                }
                return fragmentx4;
        }
        return null;
    }

    public static CategoryFiltersNode createRootNode() {
        //todo hard code
        CategoryFiltersNode newNode = new CategoryFiltersNode();
        newNode.setCatId(10021);
        newNode.setDescription("10021");
        newNode.setPathTitle("\\" + "10021");
        newNode.setTitle("10021");
        return newNode;
    }


    @Override
    public CharSequence getPageTitle(int position) {
        // Generate text based on item position
        return mTabTitles[position];
    }


    public void kartesokhtPayComplete(ViewGroup rootView) {
        if (fragmentx1 != null)
            if (fragmentx1 instanceof KartesekhtFragment){
                ((KartesekhtFragment)fragmentx1).callService(rootView);
            }

    }
}