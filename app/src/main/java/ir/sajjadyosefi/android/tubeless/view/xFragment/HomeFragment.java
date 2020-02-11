package ir.sajjadyosefi.android.tubeless.view.xFragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;

import com.andremion.counterfab.CounterFab;

import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.astuetz.MainPagerSlidingTabStrip;
import com.google.android.material.navigation.NavigationView;
import com.readystatesoftware.systembartint.SystemBarTintManager;

import ir.sajjadyosefi.android.tubeless.R;


import ir.sajjadyosefi.android.tubeless.adapter.xFragments.MainPagerAdapter;
import ir.sajjadyosefi.android.xTubeless.activity.common.ContactUsActivity;
import ir.sajjadyosefi.android.xTubeless.activity.common.ContainerActivity;
import ir.sajjadyosefi.android.xTubeless.activity.account.LoginActivity;


import static ir.sajjadyosefi.android.tubeless.view.fragment.minor.FragmentTimelineMinor.ARG_LIST;

//import com.astuetz.PagerSlidingTabStripDefault;

/**
 * Created by sajjad on 08/14/2016.
 */
// In this case, the fragment displays simple text based on the page
@SuppressLint("ValidFragment")
public class HomeFragment extends Fragment {
    public static Context context;
    public ViewPager mainViewPager;
    private SystemBarTintManager mSystemBarTint;
    private boolean mTranslucentStatus;
    private boolean mTranslucentStatusSet;
    private boolean mTranslucentNavigationSet;




    public static int mIndex;
    private int mPage;
    public static final String ARG_PAGE = "ARG_PAGE";
    public FloatingActionButton floatingActionButton, floatingActionButton2;

    public HomeFragment() {

    }

    public HomeFragment newInstance(Context context,  int page, int list) {
        this.context = context ;
        Bundle args = new Bundle();
        args.putInt(ARG_PAGE, page);
        args.putInt(ARG_LIST, list);
        HomeFragment fragment = new HomeFragment();
        fragment.setArguments(args);
        //this.values = context.getSharedPreferences(Statics.MAHAN, 0);
        return fragment;
    }
    public HomeFragment(Context context) {
        this.context = context;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.x_home_fragment, container, false);
        mainViewPager = (ViewPager) view.findViewById(R.id.viewpager);
        CounterFab counterFab = (CounterFab) view.findViewById(R.id.fabx);

        MainPagerSlidingTabStrip mainTabsStrip = (MainPagerSlidingTabStrip) view.findViewById(R.id.tabs);

        mainViewPager.setAdapter(new MainPagerAdapter(context, mainViewPager, getChildFragmentManager()));
        mainTabsStrip.setViewPager(mainViewPager);

        Toolbar toolbar = view.findViewById(R.id.toolbar);
//        drawableMenu(view , toolbar);
//        ((AppCompatActivity)getActivity()).getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolbar.setTitleTextColor(Color.RED);//for red colored toolbar text
        toolbar.setTitle(getContext().getString(R.string.app_name));
        Typeface font = Typeface.createFromAsset(getContext().getAssets(), "fonts/BYekan.ttf");
        CollapsingToolbarLayout collapsingToolbar = view.findViewById(R.id.toolbar_layout);
        collapsingToolbar.setCollapsedTitleTypeface(font);
        collapsingToolbar.setExpandedTitleTypeface(font);
        collapsingToolbar.setExpandedTitleTextAppearance(R.style.toolbar_text);
        collapsingToolbar.setCollapsedTitleTextColor(Color.BLACK);
        collapsingToolbar.setExpandedTitleColor(Color.WHITE);
//        collapsingToolbar.setExpandedTitleGravity(Gravity.LEFT);
//        collapsingToolbar.setCollapsedTitleGravity(Gravity.RIGHT);




        counterFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Toast.makeText(getActivity(), "fabx", Toast.LENGTH_SHORT).show();
//                counterFab.setCount(10); // Set the count value to show on badge
//                counterFab.increase(); // Increase the current count value by 1


//                Intent autoActivityIntent =  new Intent(getContext(), RegYafteActivity.class);
//                getContext().startActivity(autoActivityIntent);

                getContext().startActivity(new Intent(getContext(), ContainerActivity.class));

            }
        });


        Button xx = (view.findViewById(R.id.btn));
        xx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mainViewPager.setCurrentItem(0);
            }
        });

        Button xx1 = (view.findViewById(R.id.btn1));
        xx1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mainViewPager.setCurrentItem(1,true);
            }
        });

        Button xx2 = (view.findViewById(R.id.btn2));
        xx2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent xxxxxxxxxx = new Intent(getContext(), ContainerActivity.class);
//                Bundle ssssssssss = new Bundle();
//                ssssssssss.putString("",);
//                xxxxxxxxxx.putExtras(ssssssssss);
//                getContext().startActivity(xxxxxxxxxx);


                Intent intent = new Intent(context, ContainerActivity.class);
                ContainerActivity.type = 2 ;
                Bundle bundle = new Bundle();
                bundle.putInt(ContactUsActivity.Type, 2);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });



        prepareUi();

        return view;
    }

    private void prepareUi() {
        final int statusbarHeight = getStatusBarHeight();
//        final boolean translucentStatus =  hasTranslucentStatusBar();
//        final boolean translucentNavigation = hasTranslucentNavigation();



    }
    public int getStatusBarHeight() {
        return getSystemBarTint().getConfig().getStatusBarHeight();
    }
//    @TargetApi(19)
//    public boolean hasTranslucentStatusBar() {
//        if (!mTranslucentStatusSet) {
//            if (Build.VERSION.SDK_INT >= 19) {
//                mTranslucentStatus =
//                        ((getWindow().getAttributes().flags & WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
//                                == WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
//            } else {
//                mTranslucentStatus = false;
//            }
//            mTranslucentStatusSet = true;
//        }
//        return mTranslucentStatus;
//    }

//    @TargetApi (19)
//    public boolean hasTranslucentNavigation() {
//        if (!mTranslucentNavigationSet) {
//            final SystemBarTintManager.SystemBarConfig config = getSystemBarTint().getConfig();
//            if (Build.VERSION.SDK_INT >= 19) {
//                boolean themeConfig =
//                        ((getWindow().getAttributes().flags & WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION)
//                                == WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
//
//                mTranslucentNavigation = themeConfig && config.hasNavigtionBar() && config.isNavigationAtBottom()
//                        && config.getNavigationBarHeight() > 0;
//            }
//            mTranslucentNavigationSet = true;
//        }
//        return mTranslucentNavigation;
//    }

    public SystemBarTintManager getSystemBarTint() {
        if (null == mSystemBarTint) {
            mSystemBarTint = new SystemBarTintManager(getActivity());
        }
        return mSystemBarTint;
    }

    DrawerLayout drawer;
    private void drawableMenu(final View view, Toolbar toolbar) {
        drawer = (DrawerLayout) view.findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(getActivity(), drawer, toolbar, R.string.save, R.string.cancel);
        drawer.addDrawerListener(toggle);
        toggle.syncState();


        NavigationView navigationView = (NavigationView) view.findViewById(R.id.nav_view);



//        if (Global.user != null) {
//            // get menux from navigationView
//            Menu menux = navigationView.getMenu();
//
//            // find MenuItem you want to change
//            MenuItem nav_camara = menux.findItem(R.id.nav_account);
//
//            // set new text to the MenuItem
//            nav_camara.setTitle(getContext().getString(R.string.profile));
//            nav_camara.setIcon(R.drawable.ic_menu_send);
//
////        // do the same for other MenuItems
////        MenuItem nav_gallery = menux.findItem(R.id.nav_gallery);
////        nav_gallery.setTitle("NewTitleForGallery");
////        menux.add(R.string.code_melli);
//        }


        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                // Handle navigation view item clicks here.
                int id = item.getItemId();

                if (id == R.id.nav_account) {
                    // Handle the camera action
//                    if (Global.user != null) {
//                        Intent autoActivityIntent =  new Intent(getContext(), ProfileActivity.class);
//                        getActivity().startActivity(autoActivityIntent);
//                    }else {
                        Intent autoActivityIntent = new Intent(getContext(), LoginActivity.class);
                        getActivity().startActivity(autoActivityIntent);
//                    }

//                } else if (id == R.id.nav_gallery) {
//                    Intent autoActivityIntent =  new Intent(getContext(), ProfileActivity.class);
//                    getActivity().startActivity(autoActivityIntent);

                }else if (id == R.id.nav_profile) {

                } else if (id == R.id.nav_add_new_item) {
//                    Intent autoActivityIntent =  new Intent(getContext(), RegYafteActivity.class);
//                    getContext().startActivity(autoActivityIntent);

                } else if (id == R.id.nav_search_by_name) {
//                    if (getContext().getPackageName().contains("yadak")){
//                        final BottomSheetDialog progressDialog = new BottomSheetDialog(getContext());
//                        TubelessException.ShowSheetDialogMessage(getContext(), progressDialog, getContext().getString(R.string.yadakMessage) , "ok" , new View.OnClickListener() {
//                            @Override
//                            public void onClick(View view) {
//                                progressDialog.dismiss();
//                            }
//                        });
//                    }else {
//                        getActivity().startActivity(new Intent(getContext(), SearchByNameActivity.class));
//                        getActivity().finish();
//                    }
                } else if (id == R.id.nav_search_by_national_code) {
//                    if (getContext().getPackageName().contains("yadak")){
//                        final BottomSheetDialog progressDialog = new BottomSheetDialog(getContext());
//                        TubelessException.ShowSheetDialogMessage(getContext(), progressDialog, getContext().getString(R.string.yadakMessage) , "ok" , new View.OnClickListener() {
//                            @Override
//                            public void onClick(View view) {
//                                progressDialog.dismiss();
//                            }
//                        });
//                    }else {
//                        getActivity().startActivity(new Intent(getContext(), SearchByNationalCodeActivity.class));
//                        getActivity().finish();
//                    }

                } else if (id == R.id.nav_about_us) {
//                    ContainerActivity.type = 1 ;
//                    getContext().startActivity(new Intent(getContext(), ContainerActivity.class));
                } else if (id == R.id.nav_contact_us) {
//                    ContainerActivity.type = 2 ;
//                    getContext().startActivity(new Intent(getContext(), ContainerActivity.class));
                } else if (id == R.id.nav_telegram) {
                    //share on telegram

//                    final String appName = "org.telegram.messenger";
//                    final boolean isAppInstalled = isAppAvailable(getApplicationContext(), appName);
//                    if (isAppInstalled)
//                    {
//                        Intent myIntent = new Intent(Intent.ACTION_SEND);
//                        myIntent.setType("text/plain");
//                        myIntent.setPackage(appName);
//                        myIntent.putExtra(Intent.EXTRA_TEXT, msg);//
//                        startActivity(Intent.createChooser(myIntent, "Share with"));
//                    }
//                    else
//                    {
//                        Toast.makeText(getContext(), "Telegram not Installed", Toast.LENGTH_SHORT).show();
//                    }

                    //channel

//                    Uri uri = Uri.parse(StaticValue.Telegram);
//                    Intent likeIng = new Intent(Intent.ACTION_VIEW, uri);
//                    likeIng.setPackage("org.telegram.messenger");
//                    try {
//                        startActivity(likeIng);
//                    } catch (ActivityNotFoundException e) {
//                        startActivity(new Intent(Intent.ACTION_VIEW,Uri.parse(StaticValue.Telegram)));
//                    }

                } else if (id == R.id.nav_instagram) {
//                    Uri uri = Uri.parse(StaticValue.Instagram);
//                    Intent likeIng = new Intent(Intent.ACTION_VIEW, uri);
//                    likeIng.setPackage("com.instagram.android");
//                    try {
//                        startActivity(likeIng);
//                    } catch (ActivityNotFoundException e) {
//                        startActivity(new Intent(Intent.ACTION_VIEW,Uri.parse(StaticValue.Instagram)));
//                    }
                }

                DrawerLayout drawer = (DrawerLayout) view.findViewById(R.id.drawer_layout);
                drawer.closeDrawer(GravityCompat.START);
                return true;
            }
        });
    }

}