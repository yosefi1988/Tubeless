package ir.sajjadyosefi.android.xTubeless.activity;

import android.accounts.AccountManager;
import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.IdRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.content.ContextCompat;
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
import com.magnetadservices.sdk.AdSize;
import com.magnetadservices.sdk.MagnetNativeExpress;
import com.readystatesoftware.systembartint.SystemBarTintManager;
import com.squareup.picasso.Picasso;
import com.zarinpal.ewallets.purchase.OnCallbackRequestPaymentListener;
import com.zarinpal.ewallets.purchase.OnCallbackVerificationPaymentListener;
import com.zarinpal.ewallets.purchase.PaymentRequest;
import com.zarinpal.ewallets.purchase.ZarinPal;

import org.litepal.LitePal;

import java.util.Calendar;

import ir.sajjadyosefi.accountauthenticator.activity.AuthenticatorActivity;
import ir.sajjadyosefi.accountauthenticator.activity.SignInActivity;
import ir.sajjadyosefi.accountauthenticator.authentication.AccountGeneral;
import ir.sajjadyosefi.android.xTubeless.BuildConfig;
import ir.sajjadyosefi.android.xTubeless.R;
import ir.sajjadyosefi.android.xTubeless.Global;
import ir.sajjadyosefi.android.xTubeless.Adapter.FirstFragmentsAdapter;

import ir.sajjadyosefi.android.xTubeless.activity.account.login.model.IUser;
import ir.sajjadyosefi.android.xTubeless.activity.account.profile.MainActivityProfile;
import ir.sajjadyosefi.android.xTubeless.activity.common.ContactUsActivity;
import ir.sajjadyosefi.android.xTubeless.activity.common.ContainerActivity;
import ir.sajjadyosefi.android.xTubeless.activity.common.blog.ReadBlogActivity;
import ir.sajjadyosefi.android.xTubeless.activity.common.WebViewActivity;
import ir.sajjadyosefi.android.xTubeless.activity.filter.filterDetailsActivity;
import ir.sajjadyosefi.android.xTubeless.activity.register.RegNewBlogActivity;
import ir.sajjadyosefi.android.xTubeless.bussines.police.KarteSokhtActivity;
import ir.sajjadyosefi.android.xTubeless.activity.register.RegNewYadakActivity;
import ir.sajjadyosefi.android.xTubeless.activity.register.RegNewYafteActivity;
import ir.sajjadyosefi.android.xTubeless.bussines.police.fragment.KartesekhtFragment;
import ir.sajjadyosefi.android.xTubeless.classes.SAccounts;
import ir.sajjadyosefi.android.xTubeless.classes.StaticValue;
import ir.sajjadyosefi.android.xTubeless.classes.Validator;
import ir.sajjadyosefi.android.xTubeless.classes.model.config.Configuration;
import ir.sajjadyosefi.android.xTubeless.classes.model.network.request.accounting.LoginRequest;
import ir.sajjadyosefi.android.xTubeless.classes.model.post.NewTimelineItem;
import ir.sajjadyosefi.android.xTubeless.classes.model.user.User;
import ir.sajjadyosefi.android.xTubeless.classes.model.exception.TubelessException;


import ir.sajjadyosefi.android.xTubeless.networkLayout.retrofit.TubelessRetrofitCallbackss;
import ir.sajjadyosefi.android.xTubeless.networkLayout.retrofit.tmp.TimelineItem;
import ir.sajjadyosefi.android.xTubeless.networkLayout.retrofit.tmp.TimelineListResponse;
import ir.sajjadyosefi.android.xTubeless.utility.DeviceUtil;
import ir.sajjadyosefi.android.xTubeless.utility.DialogUtil;
import it.sephiroth.android.library.bottomnavigation.BadgeProvider;
import it.sephiroth.android.library.bottomnavigation.BottomNavigation;
import it.sephiroth.android.library.bottomnavigation.FloatingActionButtonBehavior;
import it.sephiroth.android.library.bottomnavigation.MiscUtils;
import retrofit2.Call;
import retrofit2.Response;

import static android.util.Log.VERBOSE;
import static ir.sajjadyosefi.accountauthenticator.activity.AuthenticatorActivity.PARAM_USER;
import static ir.sajjadyosefi.android.xTubeless.Adapter.FirstFragmentsAdapter.TYPE_SEARCH_POST_BY_NAME;


import static ir.sajjadyosefi.android.xTubeless.Fragment.FinancialAccountLimitFragment.READ_RULE_AND_PAY;
import ir.sajjadyosefi.android.xTubeless.classes.model.bourseState.BourseState;

import static ir.sajjadyosefi.android.xTubeless.bussines.police.fragment.KartesekhtFragment.cancelByBackbuttonPressed;
import static ir.sajjadyosefi.android.xTubeless.bussines.police.fragment.KartesekhtFragment.mContext;
import static ir.sajjadyosefi.android.xTubeless.networkLayout.networkLayout.Url.Instagram;
import static ir.sajjadyosefi.android.xTubeless.networkLayout.networkLayout.Url.Telegram;
import static ir.sajjadyosefi.android.xTubeless.utility.DialogUtil.ShowMessageDialog;
import static ir.sajjadyosefi.android.xTubeless.utility.DialogUtil.ShowSelectSturceDialog;

@TargetApi (Build.VERSION_CODES.KITKAT_WATCH)
public class MainActivity extends TubelessActivity implements BottomNavigation.OnMenuItemSelectionListener  {

    //val
    private static int LOGIN_REQUEST_CODE = 101 ;
    private static int OPEN_PROFILE_REQUEST_CODE = 102 ;
    public static int payType;


    private BottomNavigation mBottomNavigation;
    private DrawerLayout drawer_layout;
    public ViewPager viewPager;
    private FirstFragmentsAdapter firstFragmentsAdapter;
    ViewGroup root;
    public ViewGroup rootView ;


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


        if (requestCode == LOGIN_REQUEST_CODE || requestCode == OPEN_PROFILE_REQUEST_CODE) {
            if (resultCode == Activity.RESULT_OK) {

                if (requestCode == LOGIN_REQUEST_CODE){
                    if (data.hasExtra(PARAM_USER)){
                        Gson gson = new Gson();
//                        ir.sajjadyosefi.accountauthenticator.model.User userLib = gson.fromJson(data.getExtras().getString(PARAM_USER),ir.sajjadyosefi.accountauthenticator.model.User.class);
                        User user = gson.fromJson(data.getExtras().getString(PARAM_USER),User.class);

                        if(savedToDataBase(user)){
                            if (Global.user != null && Global.user.isAdmin()) {
                                firstFragmentsAdapter.notifyList();
                                updatedrawableMenuItems();
                            }
                        }

                        Toast.makeText(getContext(),getContext().getString(R.string.welcome) ,Toast.LENGTH_LONG).show();
                    }
                }

                if ((Global.user != null && Global.user.isAdmin()) || (data.hasExtra("MustRefresh") && (data.getExtras().getBoolean("MustRefresh")) ))
                    firstFragmentsAdapter.notifyList();

                updatedrawableMenuItems();
            }
        }

        if (requestCode == READ_RULE_AND_PAY) {
            if (resultCode == Activity.RESULT_OK) {
                //go to pay
                if (checkResult(getContext(), StaticValue.configuration)) {
                    //ShowSelectSturceDialog(mContext);
                    payType = data.getIntExtra("payType" , 0);
                    payment(getContext(),data.getIntExtra("payType" , 0));
                } else {
                    StaticValue.bourseState.totalPayedValue = StaticValue.configuration.getConfiguration().getVip1Month() + StaticValue.bourseState.totalPayedValue;
                    StaticValue.bourseState.lastPayedValue = StaticValue.configuration.getConfiguration().getVip1Month();
                    StaticValue.bourseState.lastPayedType = 1;
                    StaticValue.bourseState.updateAfterPay(10,StaticValue.configuration.getResponseStatus().getDate());
                    //refresh tab 3
                    firstFragmentsAdapter.notifyDataSetChanged();
                }
//
                //Toast.makeText(getContext(),"pay success" ,Toast.LENGTH_LONG).show();

                backButtonPressedInPayment = false;
            }else {
                //cancel
                backButtonPressedInPayment = false;
                DialogUtil.hideLoading();

            }
        }
    }


    private void payment(Context mContext, int payType) {
        ZarinPal purches = ZarinPal.getPurchase(mContext);
        PaymentRequest payment = ZarinPal.getPaymentRequest();

        switch (payType){
            case 1:
                payment.setAmount(StaticValue.configuration.getConfiguration().vip1Month);
                break;
            case 2:
                payment.setAmount(StaticValue.configuration.getConfiguration().vip2Month);
                break;
            case 3:
                payment.setAmount(StaticValue.configuration.getConfiguration().vip3Month);
                break;
            case 6:
                payment.setAmount(StaticValue.configuration.getConfiguration().vip6Month);
                break;
            case 100:
                payment.setAmount(StaticValue.configuration.getConfiguration().oneTime);
                break;
        }
        payment.setMerchantID("e8a913e8-f089-11e6-8dec-005056a205be");
        payment.setDescription(getString(R.string.paymenttitle) + "-signal Type:" + payType);
//        payment.setCallbackURL("return2://zarinpalpayment");
        payment.setCallbackURL(String.format("%s://%s",getString(R.string.schemezarinpalpayment),getString(R.string.zarinpalpayment)));
//        payment.setCallbackURL("return3://zarinpalpayment3");

        purches.startPayment(payment, new OnCallbackRequestPaymentListener() {
            @Override
            public void onCallbackResultPaymentRequest(int status, String authority, Uri paymentGatewayUri, Intent intent) {

                if (status == 100){
                    //ok
                    startActivity(intent);

                    backButtonPressedInPayment = true;
                }else {
                    //error in payment
                    DialogUtil.hideLoading();
                    backButtonPressedInPayment = false;
                }
            }


        });

    }

    boolean backButtonPressedInPayment = false;

    public static boolean checkResult(Context mContext , Configuration configuration)  {
        Boolean aBoolean = false;

        try {
            PackageInfo pInfo = mContext.getPackageManager().getPackageInfo(mContext.getPackageName(), 0);
            String versionName = pInfo.versionName;
            if (versionName.contains("a")) {
                aBoolean = configuration.getConfiguration().play;

            } else if (versionName.contains("b")) {
                aBoolean = configuration.getConfiguration().bazaar;

            } else if (versionName.contains("m")) {
                aBoolean = configuration.getConfiguration().myket;

            } else if (versionName.contains("i")) {
                aBoolean = configuration.getConfiguration().iranapps;

            } else if (versionName.contains("j")) {
                aBoolean = configuration.getConfiguration().jhobin;

            } else if (versionName.contains("s")) {
                aBoolean = configuration.getConfiguration().social;

            } else if (versionName.contains("k")) {
                aBoolean = configuration.getConfiguration().kandoo;

            } else {
                aBoolean = configuration.getConfiguration().play;
            }

        }catch (Exception e){
            e.printStackTrace();
            aBoolean = false;
        }
        return aBoolean;
    }


    private boolean savedToDataBase(User tmpUser) {
        tmpUser.setAdmin(tmpUser.CheckUserIsAdmin(tmpUser));

        //save to db
        if (Global.user == null){
            if ((new User(tmpUser)).save()){
                Global.user = tmpUser;


                if ((new Validator()).isIranianMobileNumber(tmpUser.getUserName()))
                    tmpUser.setPassword(tmpUser.getPassword());

                return true;
            }else {
                User dbUser = LitePal.where("userId like ?", tmpUser.getUserId() + "").findFirst(User.class);
                if (dbUser != null) {
                    Global.user = dbUser;

                    if ((new Validator()).isIranianMobileNumber(tmpUser.getUserName()))
                        tmpUser.setPassword(tmpUser.getPassword());
                    return true;
                }else {
                    Global.user = null;
                }
            }
        }else {
            Global.user = tmpUser;

            if ((new Validator()).isIranianMobileNumber(tmpUser.getUserName()))
                tmpUser.setPassword(tmpUser.getPassword());
            return true;
        }
        return false;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

//        BottomNavigation.Companion.setDEBUG(BuildConfig.DEBUG);
        setContentView(R.layout.activity_main);
        rootView = (ViewGroup) ((ViewGroup) this.findViewById(android.R.id.content)).getChildAt(0);


        root = findViewById(R.id.CoordinatorLayout01);
        counterFab = (CounterFab) findViewById(R.id.fabx);
        headerImageView = (ImageView) findViewById(R.id.header);
        toolbar_layout =  findViewById(R.id.toolbar_layout);
        headerText = (TextView) findViewById(R.id.headerText);
        toolbar = findViewById(R.id.toolbar);
        AppBarLayout01 = findViewById(R.id.AppBarLayout01);
        drawer_layout = findViewById(R.id.drawer_layout);
        viewPager = findViewById(R.id.ViewPager01);




//        loadTubelessAccountData(getContext());


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

////        toolbar
////          mBottomNavigation




//        Thread nonstandard =new Thread(new Runnable() {
//            @Override
//            public int hashCode() {
//                return super.hashCode();
//            }
//
//            @Override
//            public void run() {
//                System.out.println("i'm zero ");
//            }
//        });
//        nonstandard.start();




        Uri data2 = getIntent().getData();
        ZarinPal.getPurchase(getContext()).verificationPayment(data2, new OnCallbackVerificationPaymentListener() {
            @Override
            public void onCallbackResultVerificationPayment(boolean isPaymentSuccess, String refID, PaymentRequest paymentRequest) {
                if(isPaymentSuccess){
//                    //Toast.makeText(getContext(),"pay success" ,Toast.LENGTH_LONG).show();
                    DialogUtil.showLoadingDialog(getContext());

                    if(payType == 1){
                        StaticValue.bourseState.totalPayedValue = StaticValue.configuration.getConfiguration().getVip1Month() + StaticValue.bourseState.totalPayedValue;
                        StaticValue.bourseState.lastPayedValue = StaticValue.configuration.getConfiguration().getVip1Month();
                    }
                    if(payType == 2){
                        StaticValue.bourseState.totalPayedValue = StaticValue.configuration.getConfiguration().getVip2Month() + StaticValue.bourseState.totalPayedValue;
                        StaticValue.bourseState.lastPayedValue = StaticValue.configuration.getConfiguration().getVip2Month();
                    }
                    if(payType == 3){
                        StaticValue.bourseState.totalPayedValue = StaticValue.configuration.getConfiguration().getVip3Month() + StaticValue.bourseState.totalPayedValue;
                        StaticValue.bourseState.lastPayedValue = StaticValue.configuration.getConfiguration().getVip3Month();
                    }
                    if(payType == 6){
                        StaticValue.bourseState.totalPayedValue = StaticValue.configuration.getConfiguration().getVip6Month() + StaticValue.bourseState.totalPayedValue;
                        StaticValue.bourseState.lastPayedValue = StaticValue.configuration.getConfiguration().getVip6Month();
                    }
                    if(payType == 100){
                        //کارت سوخت
                        firstFragmentsAdapter.kartesokhtPayComplete(rootView);
                        cancelByBackbuttonPressed = false;
                        int a = 5 ;
                        a++;
                    }
                    if(payType == 0) {
                        StaticValue.bourseState.totalPayedValue = 0 + StaticValue.bourseState.totalPayedValue;
                        StaticValue.bourseState.lastPayedValue = 0;
                    }


                    if(payType != 100) {

                        StaticValue.bourseState.lastPayedType = payType;
                        StaticValue.bourseState.updateAfterPay(30, StaticValue.configuration.getResponseStatus().getDate());

                        //refresh tab 3
                        firstFragmentsAdapter.notifyDataSetChanged();

                        //load tsb 3
                        viewPager.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                getViewPager().setCurrentItem(3,true);
                            }
                        }, 200);
                    }

                }else {
                    //not ok
//                    show message refID
//                    Toast.makeText(getContext(),"not ok " ,Toast.LENGTH_LONG).show();

                    cancelByBackbuttonPressed = true;
                }

                final Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        DialogUtil.hideLoading();
                    }
                },2000);

            }
        });


    }



    @Override
    protected void onStart() {
        super.onStart();

//        View noticeView = (View) findViewById(R.id.noticeView);
//        AdadBannerAd bannerAd = (AdadBannerAd) findViewById(R.id.banner_1);
//
//        //تعریف کردن یک listener برای آگاهی از وضعیت تبلیغ بنری
//        bannerAd.setAdListener(new AdadAdListener() {
//            @Override
//            public void onLoaded() {
//                Toast.makeText(getContext(),"با کلیک روی تبلیغ آن را حذف خواهید کرد" ,Toast.LENGTH_LONG).show();
//            }
//
//            @Override
//            public void onShowed() {
//
//            }
//
//            @Override
//            public void onActionOccurred(int i) {
//                System.out.println();
//                noticeView.setVisibility(View.GONE);
//            }
//
//            @Override
//            public void onError(int i, String s) {
//
//            }
//
//            @Override
//            public void onClosed() {
//
//            }
//        });
        if (checkIsFirstRun()){
            drawer_layout.openDrawer(Gravity.LEFT);
            setFirstRunIsDone();
        }

        if(backButtonPressedInPayment){
            DialogUtil.hideLoading();
        }

        //startActivity(new Intent(this, filterDetailsActivity.class));




//        final Handler handler = new Handler();
//        handler.postDelayed(new Runnable() {
//            @Override
//            public void run() {
//
//                if (firstFragmentsAdapter != null) {
//                    firstFragmentsAdapter.kartesokhtPayComplete(rootView);
//                }
//
//            }
//        },10000);


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

            @SuppressLint("RestrictedApi")
            @Override
            public void t_onSuccess(Object response) {
                TimelineListResponse responseX = (TimelineListResponse) response;
                counterFab.setCount(responseX.getTimelineList().size());


                if(responseX.getTimelineList().size() > 0){
                    counterFab.setVisibility(View.VISIBLE);
                }

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

                            // Old Transfer
//                    intent.putExtra("Object", json);

                            //New Transfer
                            Bundle bundle = new Bundle();
                            bundle.putString("Object", json);
                            bundle.putString("Type", "MainPageNews");
                            intent.putExtras(bundle);

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
                            .load("item.getPicture()")
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
//                    headerText.setText(item.getText() + "\n" + item.getRegisterDate());
                    headerText.setText("\n" + item.getRegisterDate());
                    ////////////////////////////////// end on click ///////////////////////////////////
                }
            }
        };
        Global.apiManagerTubeless.getTubelessNews(ssssssss);

    }

    public static User loadTubelessAccountData(Context context) {
        SAccounts sAccounts = new SAccounts(context);
        if (sAccounts.hasUserAccount()){
            int accountId = sAccounts.getUserAccountID();
            Global.user = LitePal.where("userId like ?", accountId + "").findFirst(User.class);

            if (Global.user == null){
                String accountName = sAccounts.getUserAccountName();
                LoginRequest loginRequest = null;
                IUser iUser;
                iUser = new User(context);
                Validator validator = new Validator();
                if (validator.isIranianMobileNumber(accountName)){
                    loginRequest = new LoginRequest(accountName, sAccounts.getUserAccountPassword(), DeviceUtil.GetAndroidId(context));
                }else if (validator.isIranianMobileNumber(accountName)) {
                    loginRequest = new LoginRequest(accountName, "");
                }else {
                    loginRequest = new LoginRequest(accountName);
                }
                iUser.CheckUserValidity(null, loginRequest);

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
                    Bundle bundle = new Bundle();
                    if (Global.user != null && Global.user.isAdmin())
                        bundle.putBoolean("MustRefresh" , true);

//                    getActivity().startActivityForResult(ProfileActivity.getIntent(getContext(),bundle), LOGIN_REQUEST_CODE);
                    getActivity().startActivityForResult(MainActivityProfile.getIntent(getContext(),bundle), LOGIN_REQUEST_CODE);

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
//                     getActivity().startActivityForResult(LoginActivity.getIntent(getContext(),bundle, LOGIN_REQUEST_CODE);

//call sync
                    Intent intent = SignInActivity.getIntent(getContext(),bundle);
                    intent.putExtra(AuthenticatorActivity.ARG_ACCOUNT_TYPE, AccountGeneral.ACCOUNT_TYPE);
                    intent.putExtra(AuthenticatorActivity.ARG_AUTH_TYPE, AccountGeneral.AUTHTOKEN_TYPE_FULL_ACCESS);
                    intent.putExtra(AuthenticatorActivity.ARG_IS_ADDING_NEW_ACCOUNT, true);
                    //intent.putExtra(AccountManager.KEY_ACCOUNT_AUTHENTICATOR_RESPONSE, response);
                    bundle.putParcelable(AccountManager.KEY_INTENT, intent);
                    getActivity().startActivityForResult(intent, LOGIN_REQUEST_CODE);

//call direct
//                    Intent intent = new Intent(getContext(), SignInActivity.class);
//                    intent.putExtra(AuthenticatorActivity.ARG_ACCOUNT_TYPE, AccountGeneral.ACCOUNT_TYPE);
//                    intent.putExtra(AuthenticatorActivity.ARG_AUTH_TYPE, AccountGeneral.AUTHTOKEN_TYPE_FULL_ACCESS);
//                    intent.putExtra(AuthenticatorActivity.ARG_IS_ADDING_NEW_ACCOUNT, true);
////        intent.putExtra(AccountManager.KEY_ACCOUNT_AUTHENTICATOR_RESPONSE, response);
//                    bundle.putParcelable(AccountManager.KEY_INTENT, intent);
//                    getContext().startActivity(intent);


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


                } else if (id == R.id.nav_karte_sokht) {
                    try {
                        PackageInfo pInfo = getContext().getPackageManager().getPackageInfo(getContext().getPackageName(), 0);
                        String versionName = pInfo.versionName;
                        //todo 1 change version if not complete
                        if (versionName.contains("4.3.0") || versionName.contains("1.3.0")|| versionName.contains("1.1.0")) {
                            Toast.makeText(getContext(),"در حال آماده سازی هستیم." , Toast.LENGTH_LONG).show();
                        } else {
                            getContext().startActivity(new Intent(getContext(), KarteSokhtActivity.class));
                        }
                    }catch (Exception ex){

                    }

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
//                        getActivity().startActivity(new Intent(getContext(), SearchByNameActivity.class));

                        Bundle bundle = new Bundle();
                        bundle.putInt("type" , TYPE_SEARCH_POST_BY_NAME);
                        getActivity().startActivity(ContainerActivity.getIntent(getContext(),bundle));
                    }

                } else if (id == R.id.nav_contact_us) {
//                    ContainerActivity.type = 2 ;
//                    getContext().startActivity(new Intent(getContext(), ContainerActivity.class));

                    Bundle bundle = new Bundle();
                    bundle.putInt(ContactUsActivity.Type , ContactUsActivity.CONTACT_US);
                    getActivity().startActivity(ContactUsActivity.getIntent(getContext(),bundle));
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

                    Uri uri = Uri.parse(Telegram);
                    Intent likeIng = new Intent(Intent.ACTION_VIEW, uri);
                    likeIng.setPackage("org.telegram.messenger");
                    try {
                        startActivity(likeIng);
                    } catch (ActivityNotFoundException e) {
                        startActivity(new Intent(Intent.ACTION_VIEW,Uri.parse(Telegram)));
                    }

                } else if (id == R.id.nav_instagram) {
                    Uri uri = Uri.parse(Instagram);
                    Intent likeIng = new Intent(Intent.ACTION_VIEW, uri);
                    likeIng.setPackage("com.instagram.android");
                    try {
                        startActivity(likeIng);
                    } catch (ActivityNotFoundException e) {
                        startActivity(new Intent(Intent.ACTION_VIEW,Uri.parse(Instagram)));
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


        if (BuildConfig.FLAVOR_version_name.equals("kartesokht")){
            MenuItem kartesokht = menux.findItem(R.id.nav_karte_sokht);
            kartesokht.setVisible(false);
        }
    }


    @Override
    public void onContentChanged() {
        super.onContentChanged();
    }

    protected void initializeBottomNavigation(final Bundle savedInstanceState) {

        if (null == savedInstanceState) {
            BottomNavigation x = getBottomNavigation();
            x.setDefaultSelectedIndex(0);
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

//        final ViewPager viewPager = getViewPager();
        if (null != viewPager) {

            getBottomNavigation().setMenuChangedListener(parent -> {
                firstFragmentsAdapter = new FirstFragmentsAdapter(getContext(), getActivity(), parent.getMenuItemCount());

                viewPager.setAdapter(firstFragmentsAdapter);
                viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                    @Override
                    public void onPageScrolled(
                            final int position, final float positionOffset, final int positionOffsetPixels) { }

                    @Override
                    public void onPageSelected(final int position) {
                        if (getBottomNavigation() != null) {
                            if (getBottomNavigation().getSelectedIndex() != position) {
                                getBottomNavigation().setSelectedIndex(position, false);
                            }
                        }
                    }

                    @Override
                    public void onPageScrollStateChanged(final int state) { }
                });





            });

            if (firstFragmentsAdapter != null && firstFragmentsAdapter.getItem(0) instanceof KartesekhtFragment) {

                int a = 5 ;
                a++;
            }
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


            if (BuildConfig.FLAVOR_version_name.equals("tubeless") ||  BuildConfig.FLAVOR_version_name.equals("yadak")){
                //tubeless // yadak
                if (null != getViewPager() && position != 2) {
                    getViewPager().setCurrentItem(position);
                }
                if (position == 2) {
                    if(Global.user != null) {

                        Bundle bundle = new Bundle();
                        if (Global.user != null && Global.user.isAdmin())
                            bundle.putBoolean("MustRefresh" , true);

//                        getActivity().startActivityForResult(ProfileActivity.getIntent(getContext(),bundle),OPEN_PROFILE_REQUEST_CODE);
                        getActivity().startActivityForResult(MainActivityProfile.getIntent(getContext(),bundle), OPEN_PROFILE_REQUEST_CODE);

                    }else {
                        Toast.makeText(getContext(),getContext().getString(R.string.not_login),Toast.LENGTH_LONG).show();
                    }
                }
            }else if (BuildConfig.FLAVOR_version_name.equals("yafte") || BuildConfig.FLAVOR_version_name.equals("kartesokht") || BuildConfig.FLAVOR_version_name.equals("bourse")){
                if (null != getViewPager()) {
                    getViewPager().setCurrentItem(position);
                }
            }else {
                //Default , tubeless
                if (null != getViewPager() && position != 2) {
                    getViewPager().setCurrentItem(position);
                }
                if (position == 2) {
                    if(Global.user != null) {
                        Bundle bundle = new Bundle();
                        if (Global.user != null && Global.user.isAdmin())
                            bundle.putBoolean("MustRefresh" , true);

//                        getActivity().startActivityForResult(ProfileActivity.getIntent(getContext(),bundle),OPEN_PROFILE_REQUEST_CODE);
                        getActivity().startActivityForResult(MainActivityProfile.getIntent(getContext(),bundle),OPEN_PROFILE_REQUEST_CODE);

                    }else {
                        Toast.makeText(getContext(),getContext().getString(R.string.not_login),Toast.LENGTH_LONG).show();
                    }
                }
            }

        }
    }

    @Override
    public void onMenuItemReselect(@IdRes final int itemId, final int position, final boolean fromUser) {
        onMenuItemSelect(itemId,position,fromUser);

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


    public boolean checkIsFirstRun() {
        SharedPreferences prefs = null;
        prefs =  this.getSharedPreferences("ir.sajjadyosefi.android.tubeless", MODE_PRIVATE);
        return prefs.getBoolean("firstrun", true);
    }

    public boolean setFirstRunIsDone() {
        SharedPreferences prefs = null;
        prefs =  this.getSharedPreferences("ir.sajjadyosefi.android.tubeless", MODE_PRIVATE);

        try {
            prefs.edit().putBoolean("firstrun", false).commit();
            return true;
        }catch (Exception e) {
            return false;
        }
    }


    public void BackButtonPressed() {


        if (doubleBackToExitPressedOnce) {
            finish();
        }
        this.doubleBackToExitPressedOnce = true;

//        Snackbar snack = Snackbar.make(rootView, getContext().getString(R.string.exit_double_tap_message), Snackbar.LENGTH_SHORT);
//        View view = snack.getView();
//        //view.setBackgroundColor(getContext().getResources().getColor(R.color.samanGreenText));
//        TextView tv = view.findViewById(R.id.snackbar_text);
//        tv.setTextColor(Color.WHITE);
//        //((SnackbarContentLayout) tv.getParent()).setBackgroundColor(getContext().getResources().getColor(R.color.samanGreenText));
//        snack.show();

        Toast.makeText(getContext(), getContext().getString(R.string.exit_double_tap_message),Toast.LENGTH_LONG).show();


        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                doubleBackToExitPressedOnce = false;
            }
        }, 2000);
    }

    boolean doubleBackToExitPressedOnce = false;
    @Override
    public void onBackPressed() {
        BackButtonPressed();
    }

    Calendar calendar = Calendar.getInstance();
    public double getFirstNumber() {
        return Double.parseDouble(calendar.get(Calendar.SECOND) + "");
    }

    public double getSecondNumber() {
        return Double.parseDouble(calendar.get(Calendar.SECOND) + "");
    }

    public void setAdditionResult(double result) {
        Log.d("test : ", Double.toString(result));
    }



}
