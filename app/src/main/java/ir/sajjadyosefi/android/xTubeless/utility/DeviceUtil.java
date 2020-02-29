package ir.sajjadyosefi.android.xTubeless.utility;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.provider.Settings;
import android.telephony.TelephonyManager;

import androidx.core.app.ActivityCompat;

public class DeviceUtil {


    public static String GetAndroidId(Context context) {
        return Settings.Secure.getString(context.getContentResolver(),Settings.Secure.ANDROID_ID);



        //Global
//        TelephonyManager TelephonyMgr = (TelephonyManager) context.getSystemService(TELEPHONY_SERVICE);
//        String m_deviceId = "";// = TelephonyMgr.getDeviceId();
//        if (ActivityCompat.checkSelfPermission(context, android.Manifest.permission.READ_PHONE_STATE) == PackageManager.PERMISSION_GRANTED) {
//            m_deviceId = TelephonyMgr.getDeviceId();
//        }
//
////
////            device.setAndroidID(m_deviceId);
//        if(m_deviceId != "")
//            device.setAndroidID(m_deviceId);                                                                    //
//        else
//            device.setAndroidID(Build.SERIAL);



    }


    public static String getSimCard1Number(Context context) {
        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
            return "";
        }else {
            TelephonyManager telemamanger = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
            String getSimSerialNumber = telemamanger.getSimSerialNumber();
            String getSimNumber = telemamanger.getLine1Number();
            return getSimNumber;
        }

    }


//    public static String GetAndroidId2(Context context){
//        String androidID = null;
//        TelephonyManager telephonyManager = (TelephonyManager)context.getSystemService(Context.TELEPHONY_SERVICE);
//        androidID = telephonyManager.getDeviceId();
//        return androidID;
//        return "";
//    }

}
