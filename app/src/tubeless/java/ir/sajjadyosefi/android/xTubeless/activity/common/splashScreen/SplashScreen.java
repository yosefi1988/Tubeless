package ir.sajjadyosefi.android.xTubeless.activity.common.splashScreen;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.analytics.FirebaseAnalytics;
import com.splunk.mint.Mint;

//import ir.sajjadyosefi.android.tubeless.Class.model.AppStatus;
import java.io.Serializable;
import java.util.ArrayList;

import ir.sajjadyosefi.android.xTubeless.BuildConfig;
import ir.sajjadyosefi.android.xTubeless.R;
import ir.sajjadyosefi.android.xTubeless.activity.MainActivity;
import ir.sajjadyosefi.android.xTubeless.activity.common.ContainerActivity;
import ir.sajjadyosefi.android.xTubeless.activity.common.splashScreen.presenter.SplashScreenPresenterCompl;
import ir.sajjadyosefi.android.xTubeless.activity.common.splashScreen.view.ISplashScreenView;
import ir.sajjadyosefi.android.xTubeless.utility.xUtility.AndroidOs;

import static ir.sajjadyosefi.android.xTubeless.Adapter.FirstFragmentsAdapter.TYPE_LIST;
import static ir.sajjadyosefi.android.xTubeless.Adapter.FirstFragmentsAdapter.TYPE_POST_SEARCH_RESULT;
//import ir.sls.android.slspush.Mono;
//import ir.sls.android.slspush.MonoPush;


//mvp
public class SplashScreen extends AppCompatActivity implements ISplashScreenView {

    Context context;
//    AppStatus appStatus;
    private FirebaseAnalytics analytics;
    private SplashScreenPresenterCompl peresenter;

    private String                  wantPermission              = Manifest.permission.READ_PHONE_STATE;
    private static final int        PERMISSION_REQUEST_CODE     = 1;

    //sim card
    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case PERMISSION_REQUEST_CODE:
                if (AndroidOs.isPermissionGranted(grantResults)) {
                    peresenter.registerDevice();
                } else {
                    Toast.makeText(this,this.getString(R.string.simcardPermissionError), Toast.LENGTH_LONG).show();
                }
                break;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //init
        context = this;
        peresenter = new SplashScreenPresenterCompl(this,this);

        //SugarContext.init(this);
//        Adad.initialize(getApplicationContext());

//        requestWindowFeature(Window.FEATURE_NO_TITLE);
//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash_screen);

//        mint();
        //FirebaseMessaging.getInstance().subscribeToTopic("topic");
        //Thread.setDefaultUncaughtExceptionHandler(new CrashHandler(mContext));

//        List<AppStatus> appStatusList = AppStatus.listAll(AppStatus.class);
//        if(appStatusList.size() == 0){
//            appStatus = new AppStatus();
//            appStatus.AllPendingMony = "" ;
//            appStatus.AllReciveMony = "" ;
//            appStatus.AllSends = "" ;
//            appStatus.buyStatus = "Null" ;
//            appStatus.UserName = "" ;
//            appStatus.UserPicture = "" ;
//
//
//            appStatus.save();
//        }else {
//            appStatus = appStatusList.get(0);
//        }
//        MonoPush.init(this, "d3d08b8d-9cd9-4549-9774-2abbc69663e4", new IFinishedInit() {
//            @Override
//            public void finishedInit(int result) {
//                switch (result) {
//                    case Mono.RESULT_OK:
//                        Toast.makeText(getBaseContext(),"installingMono OK!" , Toast.LENGTH_SHORT).show();
//                        break;
//                    case Mono.RESULT_FAILED:
//                        Toast.makeText(getBaseContext(),"installingMono NotOK!" , Toast.LENGTH_SHORT).show();
//                        break;
//                }
//            }
//        });



//        analytics = FirebaseAnalytics.getInstance(this);

//        Intent launchIntent = getPackageManager().getLaunchIntentForPackage("ir.sajjadyosefi.tubelessyafte");
//        startActivity( launchIntent );

        ((TextView)(findViewById(R.id.textViewVersionName))).setText(BuildConfig.VERSION_NAME);


        //todo remove
        Bundle bundle = new Bundle();
        bundle.putInt("type" , TYPE_LIST);
        bundle.putSerializable("LIST", (Serializable) new ArrayList<>());
        this.startActivity(ContainerActivity.getIntent(this,bundle));
    }

    @Override
    protected void onStart() {
        super.onStart();


        if (peresenter.isFirstRun()) {
//            if (!AndroidOs.checkPermission(context, wantPermission)) {
//                if (AndroidOs.shouldShowRequestPermissionRationale(this, wantPermission)){
//                    Toast.makeText(this, this.getString(R.string.loginBySimcardDescription), Toast.LENGTH_LONG).show();
//                }
//                AndroidOs.requestPermissions(this, new String[]{wantPermission},PERMISSION_REQUEST_CODE);
//            } else {
                peresenter.registerDevice();
//            }
        }else {
            peresenter.goToMainPage();
        }


//        getSupportActionBar().hide();
//        getSupportActionBar().setDisplayShowTitleEnabled(false);


    }



    private void mint() {
        Mint.setApplicationEnvironment(Mint.appEnvironmentDevelopment);
        Mint.initAndStartSession(SplashScreen.this, "91043c9b");
    }

    @Override
    public void onSplashScreenDone() {
        //1


        //2
//        if(appStatus.buyStatus.equals("Limit")) {
//            if (CommonClass.isLimitValid()) {
//                CommonClass.runApplication(mContext, false,null);
//            } else {
//                if(CommonClass.isNetworkConnected(mContext))
//                    CommonClass.ShowPardakht(mContext);
//                else
//                    CommonClass.ShowInternetConnection(mContext,CommonClass.MUST_COONNECT_TO_COUNTINUE);
//            }
//        } else {
//            switch (appStatus.buyStatus){
//                case "Null":
//                    if(CommonClass.isNetworkConnected(mContext))
//                        new AsyncEstelam(mContext).execute();
//                    else
//                        CommonClass.ShowInternetConnection(mContext,CommonClass.FIRST_TIME);
//                    break;
//                case "OK":
//                    CommonClass.runApplication(mContext, false,null);
//                    break;
//                case "NotBuyed":
//                    if(CommonClass.isNetworkConnected(mContext))
//                        CommonClass.ShowPardakht(mContext);
//                    else
//                        CommonClass.ShowInternetConnection(mContext,CommonClass.MUST_COONNECT_TO_COUNTINUE);
//                    break;
//            }
//        }

        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {

//                Bundle bundle = new Bundle();
//                bundle.putString(FirebaseAnalytics.Param.ITEM_ID, "1");
//                bundle.putString(FirebaseAnalytics.Param.CONTENT_TYPE , "1");
//                bundle.putString(FirebaseAnalytics.Param.ITEM_NAME, "google-sign-in");
//                analytics.logEvent(FirebaseAnalytics.Event.SELECT_CONTENT, bundle);


                try {
                    int v = getPackageManager().getPackageInfo("com.google.android.gms", 0 ).versionCode;
                    if(v>11020000){
                        //show error
                    }
//                    Toast.makeText(context,"gms version : " + v ,Toast.LENGTH_LONG).show();
                } catch (PackageManager.NameNotFoundException e) {
                    e.printStackTrace();
                }



                //FirebaseCrash.log("this is log");
                try {
                    int a = 10/0;
                } catch (Exception e) {
                    //FirebaseCrash.report(e);
                }

                context.startActivity(new Intent(context, MainActivity.class));
//                context.startActivity(new Intent(context, ReadBlogActivity.class));
                overridePendingTransition(R.anim.fadeout, R.anim.fadein);
                ((Activity)context).finish();

            }
        },1500);
    }

    @Override
    public void hideProgressBar() {

    }

    @Override
    public void showProgressBar() {

    }


    @Override
    public void onThrowException(Throwable t) {

    }

//    @Override
//    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
//        switch (requestCode) {
//            case 9001:
//                if ((grantResults.length > 0) ){
//                    if(grantResults[0] == PackageManager.PERMISSION_GRANTED){
//                        myOnStart();
//                        deviceRegister();
//                    }else {
//                        //Toast.makeText(mContext,mContext.getString(R.string.WeNeedYourDeviceInfo),Toast.LENGTH_LONG).show();
//                        DialogUtil.ShowMessageDialog(context,"",context.getString(R.string.WeNeedYourDeviceInfo));
//                    }
//                }else {
//                    Toast.makeText(context,context.getString(R.string.ErrorOnGetPermision),Toast.LENGTH_LONG).show();
//                }
//                break;
//
//            default:
//                break;
//        }
//    }




}
