package ir.sajjadyosefi.android.xTubeless.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.appcompat.app.AppCompatActivity;

import android.widget.Toast;


import com.google.firebase.analytics.FirebaseAnalytics;
import com.splunk.mint.Mint;

import ir.adad.client.Adad;
//import ir.sajjadyosefi.android.tubeless.Class.model.AppStatus;
import ir.sajjadyosefi.android.tubeless.Global;
import ir.sajjadyosefi.android.tubeless.R;
import ir.sajjadyosefi.android.tubeless.asyncTask.account.AsyncDeviceRegister;
import ir.sajjadyosefi.android.tubeless.classes.CommonClass;
import ir.sajjadyosefi.android.xTubeless.classes.modelY.Device;
//import ir.sls.android.slspush.Mono;
//import ir.sls.android.slspush.MonoPush;


public class Splash_Screen extends AppCompatActivity {

    Context context;
    //AppStatus appStatus;
    private FirebaseAnalytics analytics;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //init
        context = this;
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



    }

    @Override
    protected void onStart() {
        super.onStart();

//        int permissionCheck = ContextCompat.checkSelfPermission(context, android.Manifest.permission.READ_PHONE_STATE);
////        int permissionReadCheck = ContextCompat.checkSelfPermission(mContext, android.Manifest.permission.READ_EXTERNAL_STORAGE);
////        int permissionWriteCheck = ContextCompat.checkSelfPermission(mContext, android.Manifest.permission.WRITE_EXTERNAL_STORAGE);
//
//        if (permissionCheck != PackageManager.PERMISSION_GRANTED
////                && permissionReadCheck != PackageManager.PERMISSION_GRANTED &&
////                permissionWriteCheck != PackageManager.PERMISSION_GRANTED
//                ) {
//            ActivityCompat.requestPermissions(((Activity)context),
//                    new String[]{
//                            android.Manifest.permission.READ_PHONE_STATE
////                            ,
////                            android.Manifest.permission.READ_EXTERNAL_STORAGE,
////                            android.Manifest.permission.WRITE_EXTERNAL_STORAGE
//                    },
//                    9001);
//        } else {
//            myOnStart();
//            deviceRegister();
//        }
//        getSupportActionBar().hide();
//        getSupportActionBar().setDisplayShowTitleEnabled(false);

        myOnStart();
    }

    private void deviceRegister() {

        if(CommonClass.isNetworkConnected(context))
            if (Global.isFirstRun(context) ) {

                Device device = new Device();
                Global.GetAndroidID(context, device);
                device.setApplicationID(17);                                                                //OK
                device.setVersionID(100);                                                                   //OK
                device.setBOARD(CommonClass.GetAppVersion(context));                                        //new
                device.setBRAND(Build.BRAND);                                                               //OK
                //device.setBuildID(Build.VERSION.SDK_INT + "-" + Build.VERSION.RELEASE  + "-" + Build.ID);   //new
                device.setDISPLAY(Build.DISPLAY);                                                           //
                device.setMANUFACTURER(Build.MANUFACTURER);                                                 //OK
                device.setMODEL(Build.MODEL);                                                               //OK
                device.setSERIAL(Build.SERIAL);                                                             //OK

                device.setBuildID(Build.ID);                //new
                device.setAndroidVersion(Build.VERSION.RELEASE);   //new
                device.setAndroidAPI(Build.VERSION.SDK_INT);   //new

                AsyncDeviceRegister asyncDeviceRegister = new AsyncDeviceRegister(context,device);
                asyncDeviceRegister.execute();
            }


    }



    private void mint() {
        Mint.setApplicationEnvironment(Mint.appEnvironmentDevelopment);
        Mint.initAndStartSession(Splash_Screen.this, "91043c9b");
    }

    private void myOnStart() {
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
                    Toast.makeText(context,"gms version : " + v ,Toast.LENGTH_LONG).show();
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
                overridePendingTransition(R.anim.fadeout, R.anim.fadein);
                ((Activity)context).finish();

            }
        },1500);
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
//                        Global.ShowMessageDialog(context,"",context.getString(R.string.WeNeedYourDeviceInfo));
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
