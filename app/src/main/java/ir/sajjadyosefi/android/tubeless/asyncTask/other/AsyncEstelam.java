package ir.sajjadyosefi.android.tubeless.asyncTask.other;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Build;
import android.util.Log;

import ir.sajjadyosefi.android.tubeless.classes.CommonClass;
import ir.sajjadyosefi.android.tubeless.networkLayout.networkLayout.RestApiHelper;
import ir.sajjadyosefi.android.xTubeless.classes.modelY.RequestEstelam;
import ir.sajjadyosefi.android.tubeless.R;


/**
 * Created by Other on 10/29/2016.
 */
public class AsyncEstelam extends AsyncTask {

    private final Context context;
    RequestEstelam requestEstelam = new RequestEstelam();


    public AsyncEstelam(Context context) {
        this.context = context ;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();

        requestEstelam.AndroidID        = CommonClass.GetAndroidId(context);
        requestEstelam.AndroidVersion   = Build.VERSION.CODENAME;
//        requestEstelam.AppVersion       = CommonClass.GetAppVersion(context);

        requestEstelam.AppID            = context.getString(R.string.app_id);
        requestEstelam.SERIAL           = Build.SERIAL;
        requestEstelam.MODEL            = Build.MODEL;
        requestEstelam.BuildID          = Build.ID;
        requestEstelam.MANUFACTURER     = Build.MANUFACTURER;
        requestEstelam.RELEASE_VERSION  = Build.VERSION.RELEASE;
        requestEstelam.TYPE             = Build.TYPE;
        requestEstelam.BuildUSER        = Build.USER;
        requestEstelam.BASE_VERSION_CODES = Build.VERSION_CODES.BASE + "";
        requestEstelam.INCREMENTAL_VERSION = Build.VERSION.INCREMENTAL;
        requestEstelam.SDK              = Build.VERSION.SDK_INT + "";
        requestEstelam.BOARD            = Build.BRAND;
        requestEstelam.BRAND            = Build.BRAND;
        requestEstelam.HOST             = Build.HOST;
        requestEstelam.FINGERPRINT      = Build.FINGERPRINT;
        requestEstelam.DISPLAY          = Build.DISPLAY;



        Log.i("TAG", "SERIAL: " + Build.SERIAL);
        Log.i("TAG","MODEL: " + Build.MODEL);
        Log.i("TAG","ID: " + Build.ID);
        Log.i("TAG","Manufacture: " + Build.MANUFACTURER);
        Log.i("TAG","brand: " + Build.BRAND);
        Log.i("TAG","type: " + Build.TYPE);
        Log.i("TAG","user: " + Build.USER);
        Log.i("TAG","BASE: " + Build.VERSION_CODES.BASE);
        Log.i("TAG","INCREMENTAL " + Build.VERSION.INCREMENTAL);
        Log.i("TAG","SDK  " + Build.VERSION.SDK);
        Log.i("TAG","BOARD: " + Build.BOARD);
        Log.i("TAG","BRAND " + Build.BRAND);
        Log.i("TAG","HOST " + Build.HOST);
        Log.i("TAG","FINGERPRINT: "+Build.FINGERPRINT);
        Log.i("TAG","Version Code: " + Build.VERSION.RELEASE);

    }

    @Override
    protected Object doInBackground(Object[] params) {

        RestApiHelper myParser = new RestApiHelper();
        return myParser.Estelam(requestEstelam);
    }

    @Override
    protected void onPostExecute(final Object serverResponse) {
        super.onPostExecute(serverResponse);

        if (serverResponse != null) {

//            Toast.makeText(context, ((ServerResponseConfig) serverResponse).details.BuyStatus, Toast.LENGTH_SHORT).show();
//            Toast.makeText(context, ((ServerResponseConfig) serverResponse).details.LastVersion, Toast.LENGTH_SHORT).show();
//            Toast.makeText(context, ((ServerResponseConfig) serverResponse).details.MoarefStatus, Toast.LENGTH_SHORT).show();
//
//
//            //Save
//            AppStatus appStatus;
//            List<AppStatus> appStatusList = AppStatus.listAll(AppStatus.class);
//            if (appStatusList.size() == 0) {
//
//            } else {
//                appStatus = appStatusList.get(0);
//                appStatus.buyStatus = ((ServerResponseConfig) serverResponse).details.BuyStatus;
//                appStatus.InstallDate = ((ServerResponseConfig) serverResponse).details.InstallDate;
//                appStatus.save();
//            }
//            //End Save
//
//
//            //
//            if (((ServerResponseConfig) serverResponse).details.BuyStatus.equals("Limit")) {
//                //تا 3 روز نال برميگردونه
//                //برنامه اجرا ميشه
//                //
//                CommonClass.runApplication(context, true, ((ServerResponseConfig) serverResponse));
//            } else if (((ServerResponseConfig) serverResponse).details.BuyStatus.equals("OK")) {
//                CommonClass.runApplication(context, false, null);
//            } else if (((ServerResponseConfig) serverResponse).details.BuyStatus.equals("NotBuyed")) {
//                //
////بعد از 3 روز
//                //برنامه اجرا نمیشه
//                //
//                //CommonClass.ShowPardakht(context);
//                if(CommonClass.isNetworkConnected(context))
//                    CommonClass.ShowPardakht(context);
//                else
//                    CommonClass.ShowInternetConnection(context,CommonClass.MUST_COONNECT_TO_COUNTINUE);
//            }
        }else {
            CommonClass.ShowInternetConnection(context,CommonClass.NOT_CONNECT);
        }



    }
}
