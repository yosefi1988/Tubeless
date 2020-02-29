package ir.sajjadyosefi.android.xTubeless.activity;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.IdRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.viewpager.widget.ViewPager;

import com.andremion.counterfab.CounterFab;
import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;
import com.google.gson.Gson;
import com.readystatesoftware.systembartint.SystemBarTintManager;
import com.squareup.picasso.Picasso;

import org.litepal.LitePal;

import ir.sajjadyosefi.android.xTubeless.R;
import ir.sajjadyosefi.android.xTubeless.Global;
import ir.sajjadyosefi.android.xTubeless.Adapter.FirstFragmentsAdapter;
import ir.sajjadyosefi.android.xTubeless.activity.account.ProfileActivity;
import ir.sajjadyosefi.android.xTubeless.activity.account.login.LoginActivity;
import ir.sajjadyosefi.android.xTubeless.activity.common.ContactUsActivity;
import ir.sajjadyosefi.android.xTubeless.activity.common.ReadBlogActivity;
import ir.sajjadyosefi.android.xTubeless.activity.common.WebViewActivity;
import ir.sajjadyosefi.android.xTubeless.activity.post.SearchByNameActivity;
import ir.sajjadyosefi.android.xTubeless.activity.register.RegNewYadakActivity;
import ir.sajjadyosefi.android.xTubeless.activity.register.RegNewYafteActivity;
import ir.sajjadyosefi.android.xTubeless.classes.SAccounts;
import ir.sajjadyosefi.android.xTubeless.classes.StaticValue;
import ir.sajjadyosefi.android.xTubeless.classes.model.user.User;
import ir.sajjadyosefi.android.xTubeless.classes.model.exception.TubelessException;
import ir.sajjadyosefi.android.xTubeless.classes.model.TimelineItem;
import ir.sajjadyosefi.android.xTubeless.classes.model.response.TimelineListResponse;
import ir.sajjadyosefi.android.xTubeless.networkLayout.retrofit.TubelessRetrofitCallbackss;
import it.sephiroth.android.library.bottomnavigation.BadgeProvider;
import it.sephiroth.android.library.bottomnavigation.BottomNavigation;
import it.sephiroth.android.library.bottomnavigation.FloatingActionButtonBehavior;
import it.sephiroth.android.library.bottomnavigation.MiscUtils;
import retrofit2.Call;

import static android.util.Log.VERBOSE;

@TargetApi (Build.VERSION_CODES.KITKAT_WATCH)
public class MainActivity extends TubelessActivity implements BottomNavigation.OnMenuItemSelectionListener {

    //val
    private static int LOGIN_REQUEST_CODE = 101 ;

    private BottomNavigation mBottomNavigation;
    private DrawerLayout drawer_layout;
    private ViewPager viewPager;
    ViewGroup root;

    //Top of page
    private ImageView headerImageView;
    private CollapsingToolbarLayout toolbar_layout;
    private TextView headerText;
    private Toolbar toolbar;
    private AppBarLayout AppBarLayout01;
    private CounterFab counterFab;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


        if (requestCode == LOGIN_REQUEST_CODE) {
            if (resultCode == Activity.RESULT_OK) {
                updatedrawableMenuItems();
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        BottomNavigation.Companion.setDEBUG(BuildConfig.DEBUG);
        setContentView(R.layout.activity_main);

        root = findViewById(R.id.CoordinatorLayout01);
        counterFab = (CounterFab) findViewById(R.id.fabx);
        headerImageView = (ImageView) findViewById(R.id.header);
        toolbar_layout =  findViewById(R.id.toolbar_layout);
        headerText = (TextView) findViewById(R.id.headerText);
        toolbar = findViewById(R.id.toolbar);
        AppBarLayout01 = findViewById(R.id.AppBarLayout01);
        drawer_layout = findViewById(R.id.drawer_layout);
        viewPager = findViewById(R.id.ViewPager01);




        loadTubelessAccountData(getContext());


        setSupportActionBar(toolbar);
        drawableMenu(toolbar);


        final int statusbarHeight = getStatusBarHeight();
//        final boolean translucentStatus = false;
        final boolean translucentStatus = hasTranslucentStatusBar();
        final boolean translucentNavigation = hasTranslucentNavigation();

        MiscUtils.INSTANCE.log(VERBOSE, "translucentStatus: %b", translucentStatus);

        if (translucentStatus) {
            ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams) root.getLayoutParams();
            params.topMargin = -statusbarHeight;

            params = (ViewGroup.MarginLayoutParams) toolbar.getLayoutParams();
//            params.topMargin = statusbarHeight;

            int a = 5;
            a++;
        }

        if (translucentNavigation) {
            final DrawerLayout drawerLayout = getDrawerLayout();
            if (null != drawerLayout) {
                ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams) drawerLayout.getLayoutParams();
                params.bottomMargin = 0;

//                params.bottomMargin = 400;
//                params.bottomMargin = getNavigationBarHeight();


//                params = (ViewGroup.MarginLayoutParams)(findViewById(R.id.ccdsd4)).getLayoutParams();
//                params.bottomMargin = getNavigationBarHeight();

            }
        }

        initializeBottomNavigation(savedInstanceState);
        initializeUI(savedInstanceState);




        loadNews();
    }

    private void loadNews() {


        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), view.toString(), Toast.LENGTH_SHORT).show();
//                counterFab.setCount(10); // Set the count value to show on badge
//                counterFab.increase(); // Increase the current count value by 1

//                Intent autoActivityIntent =  new Intent(getContext(), RegYafteActivity.class);
//                getContext().startActivity(autoActivityIntent);

                //getContext().startActivity(new Intent(getContext(), ContainerActivity.class));
            }
        };

        TubelessRetrofitCallbackss ssssssss = new TubelessRetrofitCallbackss(getContext(), TimelineListResponse.class) {
            @Override
            public void t_beforeSendRequest() {

            }

            @Override
            public void t_afterGetResponse() {

            }

            @Override
            public void t_complite() {

            }

            @Override
            public void t_responseNull() {

            }

            @Override
            public void t_retry(Call<Object> call) {

            }

            @Override
            public void t_onSuccess(Object response) {
                TimelineListResponse responseX = (TimelineListResponse) response;
                counterFab.setCount(responseX.getTimelineList().size());


                for (TimelineItem item : responseX.getTimelineList()){

                    ////////////////////////////////// on click ///////////////////////////////////
                    View.OnClickListener goToReadNews = new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
//                            counterFab.setCount(10); // Set the count value to show on badge
//                            counterFab.increase(); // Increase the current count value by 1

                            Intent intent = new Intent(getContext(), ReadBlogActivity.class);
                            Gson gson = new Gson();
                            String json = gson.toJson(item);
                            intent.putExtra("Object", json);
                            getContext().startActivity(intent);
                            ((Activity) getContext()).overridePendingTransition(R.anim.fadeout, R.anim.fadein);
                        }
                    };

                    counterFab.setOnClickListener(goToReadNews);
                    AppBarLayout01.setOnClickListener(goToReadNews);
                    toolbar.setOnClickListener(goToReadNews);
                    headerText.setOnClickListener(goToReadNews);
                    headerImageView.setOnClickListener(goToReadNews);


                    Picasso.get()
                            .load(item.getPicture())
                            .placeholder(R.drawable.jpg_paeez)
                            //.centerInside()
                            //.transform(transformation)
                            .into(headerImageView, new com.squareup.picasso.Callback() {
                                @Override
                                public void onSuccess() {

                                }

                                @Override
                                public void onError(Exception e) {

                                    Picasso.get()
                                            .load(R.drawable.jpg_paeez)
                                            //.transform(transformation)
                                            .into(headerImageView);
                                }
                            } );

                    toolbar.setTitle(item.getTitle());
                    toolbar_layout.setTitle(item.getTitle());
                    headerText.setText(item.getText() + "\n" + item.getRegisterDate());
                    ////////////////////////////////// end on click ///////////////////////////////////
                }
            }
        };
        Global.apiManagerTubeless.getTubelessNews( ssssssss);
    }

    public static User loadTubelessAccountData(Context context) {
        SAccounts sAccounts = new SAccounts(context);
        if (sAccounts.hasUserAccount()){
            int accountId = sAccounts.getUserAccountID();
            Global.user = LitePal.where("userId like ?", accountId + "").findFirst(User.class);

            if (Global.user == null){
                Toast.makeText(context,"618716848872",Toast.LENGTH_LONG).show();
            }
            return Global.user;
        }else {
            return null;
        }
    }


    private void drawableMenu(Toolbar toolbar) {
//        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.about_us, R.string.cancel);
//        drawer.addDrawerListener(toggle);
//        toggle.syncState();

        //add humberger
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer_layout, toolbar, R.string.save, R.string.cancel);
        drawer_layout.addDrawerListener(toggle);
        toggle.setDrawerIndicatorEnabled(true);
        toggle.syncState();

//        //humburger icon
//        toolbar.setNavigationIcon(R.drawable.png_upload);
//        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Toast.makeText(getApplicationContext(),getApplicationContext().getResources().getString(R.string.inputError),Toast.LENGTH_SHORT).show();
//            }
//        });
        ViewGroup.MarginLayoutParams params = (ViewGroup.MarginLayoutParams)toolbar.getLayoutParams();
        //params.bottomMargin = 100;






        updatedrawableMenuItems();


        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                // Handle navigation view item clicks here.
                int id = item.getItemId();

                if (id == R.id.nav_profile) {
                    getActivity().startActivityForResult(ProfileActivity.getIntent(getContext()), LOGIN_REQUEST_CODE);
                }else if (id == R.id.nav_role) {

                    Bundle bundle = new Bundle();
                    bundle.putString("WebType" , "rule");
                    getActivity().startActivityForResult(WebViewActivity.getIntent(getContext(),bundle), LOGIN_REQUEST_CODE);
                }else if (id == R.id.nav_about_us) {

                    Bundle bundle = new Bundle();
                    bundle.putString("WebType" , "about");
                    getActivity().startActivityForResult(WebViewActivity.getIntent(getContext(),bundle), LOGIN_REQUEST_CODE);

                }else  if (id == R.id.nav_login) {
                     Bundle bundle = new Bundle();
                     bundle.putInt("type" , 1);

                     getActivity().startActivityForResult(LoginActivity.getIntent(getContext(),bundle), LOGIN_REQUEST_CODE);


//                }else  if (id == R.id.nav_account) {
//                    // Handle the camera action
//                    if (Global.user != null) {
//                        Intent autoActivityIntent =  new Intent(getContext(), ProfileActivity.class);
//                        getActivity().startActivity(autoActivityIntent);
//                    }else {
//                        Intent autoActivityIntent = new Intent(getContext(), LoginActivity.class);
//                        getActivity().startActivity(autoActivityIntent);
//                    }
//
////                } else if (id == R.id.nav_gallery) {
////                    Intent autoActivityIntent =  new Intent(getContext(), ProfileActivity.class);
////                    getActivity().startActivity(autoActivityIntent);
                } else if (id == R.id.nav_add_new_yafte_item) {
//                    Intent autoActivityIntent =  new Intent(getContext(), RegYafteActivity.class);
//                    getContext().startActivity(autoActivityIntent);

                    getContext().startActivity(new Intent(getContext(), RegNewYafteActivity.class));

                } else if (id == R.id.nav_add_new_yadak_item) {
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
                    getContext().startActivity(new Intent(getContext(), RegNewYadakActivity.class));


                } else if (id == R.id.nav_search_by_name) {
                    if (getContext().getPackageName().contains("yadak")){
                        final BottomSheetDialog progressDialog = new BottomSheetDialog(getContext());
                        TubelessException.ShowSheetDialogMessage(getContext(), progressDialog, getContext().getString(R.string.yadakMessage) , "ok" , new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                progressDialog.dismiss();
                            }
                        });
                    }else {
                        getActivity().startActivity(new Intent(getContext(), SearchByNameActivity.class));
                    }

                } else if (id == R.id.nav_contact_us) {
//                    ContainerActivity.type = 2 ;
//                    getContext().startActivity(new Intent(getContext(), ContainerActivity.class));

                    getActivity().startActivity(ContactUsActivity.getIntent(getContext()));
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

                    Uri uri = Uri.parse(StaticValue.Telegram);
                    Intent likeIng = new Intent(Intent.ACTION_VIEW, uri);
                    likeIng.setPackage("org.telegram.messenger");
                    try {
                        startActivity(likeIng);
                    } catch (ActivityNotFoundException e) {
                        startActivity(new Intent(Intent.ACTION_VIEW,Uri.parse(StaticValue.Telegram)));
                    }

                } else if (id == R.id.nav_instagram) {
                    Uri uri = Uri.parse(StaticValue.Instagram);
                    Intent likeIng = new Intent(Intent.ACTION_VIEW, uri);
                    likeIng.setPackage("com.instagram.android");
                    try {
                        startActivity(likeIng);
                    } catch (ActivityNotFoundException e) {
                        startActivity(new Intent(Intent.ACTION_VIEW,Uri.parse(StaticValue.Instagram)));
                    }
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

                drawer_layout.closeDrawer(GravityCompat.START);
                return true;
            }
        });
    }

    private void updatedrawableMenuItems() {

        // get menux from navigationView
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        Menu menux = navigationView.getMenu();

        MenuItem login = menux.findItem(R.id.nav_login);
        MenuItem profile = menux.findItem(R.id.nav_profile);

        if (Global.user == null) {
            login.setVisible(true);
            profile.setVisible(false);

        }else {
            login.setVisible(false);
            profile.setVisible(true);
        }

//        {
//            // find MenuItem you want to change
//            MenuItem nav_camara = menux.findItem(R.id.nav_account);
//
//            // set new text to the MenuItem
//            nav_camara.setTitle(getContext().getString(R.string.profile));
//            nav_camara.setIcon(R.drawable.ic_menu_send);
//            nav_camara.setVisible(false);

//        // do the same for other MenuItems
//        MenuItem nav_gallery = menux.findItem(R.id.nav_gallery);
//        nav_gallery.setTitle("NewTitleForGallery");
//        menux.add(R.string.code_melli);
//        }

    }


    @Override
    public void onContentChanged() {
        super.onContentChanged();
    }

    protected void initializeBottomNavigation(final Bundle savedInstanceState) {

        if (null == savedInstanceState) {
            getBottomNavigation().setDefaultSelectedIndex(0);
            final BadgeProvider provider = getBottomNavigation().getBadgeProvider();
            provider.show(R.id.bbn_item3);
            provider.show(R.id.bbn_item2);
        }
        if (null != getBottomNavigation()) {
//            Typeface typeface = Typeface.createFromAsset(getAssets(), "Roboto-Light.ttf");
            mBottomNavigation.setMenuItemSelectionListener(this);
//            mBottomNavigation.setDefaultTypeface(typeface);
        }
    }

    protected void initializeUI(final Bundle savedInstanceState) {
        final FloatingActionButton floatingActionButton = findViewById(R.id.fab);
        if (null != floatingActionButton) {
            floatingActionButton.setOnClickListener(view -> {
                Snackbar snackbar = Snackbar.make(root, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction(
                                "Action",
                                null
                        );
                snackbar.show();
            });

            if (hasTranslucentNavigation()) {
                final ViewGroup.LayoutParams params = floatingActionButton.getLayoutParams();
                if (CoordinatorLayout.LayoutParams.class.isInstance(params)) {
                    CoordinatorLayout.LayoutParams params1 = (CoordinatorLayout.LayoutParams) params;
                    if (FloatingActionButtonBehavior.class.isInstance(params1.getBehavior())) {
                        ((FloatingActionButtonBehavior) params1.getBehavior()).setNavigationBarHeight(getNavigationBarHeight());
                    }
                }
            }
        }

        final ViewPager viewPager = getViewPager();
        if (null != viewPager) {

            getBottomNavigation().setMenuChangedListener(parent -> {

                viewPager.setAdapter(new FirstFragmentsAdapter(getContext(),getActivity(), parent.getMenuItemCount()));
                viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                    @Override
                    public void onPageScrolled(
                            final int position, final float positionOffset, final int positionOffsetPixels) { }

                    @Override
                    public void onPageSelected(final int position) {
                        if (getBottomNavigation().getSelectedIndex() != position) {
                            getBottomNavigation().setSelectedIndex(position, false);
                        }
                    }

                    @Override
                    public void onPageScrollStateChanged(final int state) { }
                });
            });

        }
    }


    public int getStatusBarHeight() {
        return getSystemBarTint().getConfig().getStatusBarHeight();
    }

    @TargetApi (19)
    public boolean hasTranslucentStatusBar() {
        if (!mTranslucentStatusSet) {
            if (Build.VERSION.SDK_INT >= 19) {
                mTranslucentStatus = ((getWindow().getAttributes().flags & WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS) == WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            } else {
                mTranslucentStatus = false;
            }
            mTranslucentStatusSet = true;
        }
        return mTranslucentStatus;
    }

    @TargetApi (19)
    public boolean hasTranslucentNavigation() {
        if (!mTranslucentNavigationSet) {
            final SystemBarTintManager.SystemBarConfig config = getSystemBarTint().getConfig();
            if (Build.VERSION.SDK_INT >= 19) {
                boolean themeConfig =
                        ((getWindow().getAttributes().flags & WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION)
                                == WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);

                mTranslucentNavigation = themeConfig && config.hasNavigtionBar() && config.isNavigationAtBottom()
                        && config.getNavigationBarHeight() > 0;
            }
            mTranslucentNavigationSet = true;
        }
        return mTranslucentNavigation;
    }

    public int getNavigationBarHeight() {
        return getSystemBarTint().getConfig().getNavigationBarHeight();
    }

    public Toolbar getToolbar() {
        return (Toolbar) findViewById(R.id.toolbar);
    }

    public boolean hasManagedToolbarScroll() {
        return hasAppBarLayout() && findViewById(R.id.CoordinatorLayout01) instanceof CoordinatorLayout;
    }

    public boolean hasAppBarLayout() {
        return getToolbar().getParent() instanceof AppBarLayout;
    }

    public BottomNavigation getBottomNavigation() {
        if (null == mBottomNavigation) {
            mBottomNavigation = findViewById(R.id.BottomNavigation);
        }
        return mBottomNavigation;
    }

    @Override
    public SystemBarTintManager getSystemBarTint() {
        if (null == mSystemBarTint) {
            mSystemBarTint = new SystemBarTintManager(this);
        }
        return mSystemBarTint;
    }

    public DrawerLayout getDrawerLayout() {
        return drawer_layout;
    }

    private ViewPager getViewPager() {
        return viewPager;
    }





    @Override
    public void onMenuItemSelect(final int itemId, final int position, final boolean fromUser) {
        if (fromUser) {
            getBottomNavigation().getBadgeProvider().remove(itemId);
            if (null != getViewPager() && position != 2) {
                getViewPager().setCurrentItem(position);
            }

            if (position == 2) {
                if(Global.user != null) {
                    getActivity().startActivity(ProfileActivity.getIntent(getContext()));
                }else {
                    Toast.makeText(getContext(),getContext().getString(R.string.not_login),Toast.LENGTH_LONG).show();
                }
            }
        }
    }

    @Override
    public void onMenuItemReselect(@IdRes final int itemId, final int position, final boolean fromUser) {
//        MiscUtils.INSTANCE.log(INFO, "onMenuItemReselect(" + itemId + ", " + position + ", " + fromUser + ")");

//        if (fromUser) {
//            final FragmentManager manager = getSupportFragmentManager();
//            ListFragment fragment = (ListFragment) manager.findFragmentById(R.id.fragment);
//            if (null != fragment) {
//                fragment.scrollToTop();
//            }
//        }
//        Toast.makeText(getContext(),position+"",Toast.LENGTH_LONG).show();
    }
}
