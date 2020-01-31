package ir.sajjadyosefi.android.tubeless.services;

import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;

import ir.sajjadyosefi.android.tubeless.Global;
import ir.sajjadyosefi.android.tubeless.classes.business.RadyabBusiness;

/**
 * Created by sajjad on 12/5/2017.
 */

public class MyFireBaseInstanceIDService
//        extends FirebaseInstanceIdService
{


    private static final String TAG = MyFireBaseInstanceIDService.class.getSimpleName();
//
//    @Override
//    public void onTokenRefresh() {
//        // Get updated InstanceID token.
//        String refreshedToken = FirebaseInstanceId.getInstance().getToken();
//        Log.d(TAG, "Refreshed token: " + refreshedToken);
//        RadyabBusiness.refreshMasterDetails(getApplicationContext(), refreshedToken);
//
////
////        if (refreshedToken!=null) {
////            SettingPreferences.setStringValueInPref(this, SettingPreferences.REG_ID, refreshedToken);
////        }
////        // TODO: Implement this method to send any registration to your app's servers.
////        sendRegistrationToServer(refreshedToken);
//    }
// [END refresh_token]

    /**
     * Persist token to third-party servers.
     *
     * Modify this method to associate the user's FCM InstanceID token with any server-side account
     * maintained by your application.
     *
     * @param token The new token.
     */
    private void sendRegistrationToServer(String token) {
        // Add custom implementation, as needed.
        Global.token = token ;
        int a = 1 ;
        a++;
    }

}
