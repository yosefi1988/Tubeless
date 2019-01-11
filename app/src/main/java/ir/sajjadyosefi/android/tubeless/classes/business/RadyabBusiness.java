package ir.sajjadyosefi.android.tubeless.classes.business;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Looper;
import android.widget.Toast;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.gson.Gson;

import ir.sajjadyosefi.android.tubeless.R;
import ir.sajjadyosefi.android.tubeless.activity.radyab.EditRadyabCarActivity;
import ir.sajjadyosefi.android.tubeless.activity.radyab.MainRadyabActivity;
import ir.sajjadyosefi.android.tubeless.classes.databaseLayout.DatabaseUtils;
import ir.sajjadyosefi.android.tubeless.classes.model.User.Device;
import ir.sajjadyosefi.android.tubeless.classes.model.common.Sms;
import ir.sajjadyosefi.android.tubeless.classes.model.radyab.PushObject;
import ir.sajjadyosefi.android.tubeless.classes.model.radyab.RadyabSetting;
import ir.sajjadyosefi.android.tubeless.classes.model.radyab.SlaveDetails;
import ir.sajjadyosefi.android.tubeless.classes.model.radyab.requestService.RequestService;
import ir.sajjadyosefi.android.tubeless.classes.model.radyab.response.ResponseToken;
import ir.sajjadyosefi.android.tubeless.classes.model.radyab.response.ResponseTokenRoot;
import ir.sajjadyosefi.android.tubeless.classes.networkLayout.Url;
import ir.sajjadyosefi.android.tubeless.classes.utility.DeviceUtils;

/**
 * Created by sajjad on 5/1/2018.
 */

public class RadyabBusiness {
    public static final int TYPE_TOKEN = 10 ;
    public static final int TYPE_AUTO_LOCATION = 1 ;

    public static String TMP = null ;

    public static Sms CreateSmsForSendToClient(String rnd, String phoneNumber) {
        Sms sms = new Sms();
        sms.setPhoneNumber(phoneNumber);
        TMP = phoneNumber;

        String message = "RND" + rnd + "TM" + FirebaseInstanceId.getInstance().getToken();
        sms.setMessage(message);
        return sms;
    }

    public static void handleNotifications(final Context context , String data) {
        ResponseTokenRoot responseToken = new ResponseTokenRoot();
         Gson gson = new Gson();
         responseToken = gson.fromJson(data,ResponseTokenRoot.class);

        DatabaseUtils databaseUtils = new DatabaseUtils(context);
         switch (responseToken.getMessage().getType()){
             case (TYPE_TOKEN):{
                 SlaveDetails slaveDetails = new SlaveDetails();
                 slaveDetails.setSlavePushNotificationToken(responseToken.getMessage().getSlavePushNotificationToken());
                 slaveDetails.setSlaveTokenValid(true);
                 slaveDetails.setSlavePhoneNumber(TMP);
                 int idSlave = databaseUtils.saveSlaveDetails(slaveDetails);
                 if (idSlave > 0){
                     Intent intent = new Intent(context, EditRadyabCarActivity.class);
                     Bundle bundle = new Bundle();
                     bundle.putInt("IdSlave",idSlave);
                     intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                     intent.putExtras(bundle);
                     context.startActivity(intent);
                 }
                 break;
             }
             case (TYPE_AUTO_LOCATION):{
                 final ResponseTokenRoot finalResponseToken = responseToken;
                 new Thread() {
                     @Override
                     public void run() {
                         Looper.prepare();
                         Toast.makeText(context, finalResponseToken.getMessage().toString(),Toast.LENGTH_SHORT).show();
                         Looper.loop();
                     }
                 }.start();
             }
             default:{
                 break;
             }
         }
        int a = 5 ;
        a++;
    }

    public static void refreshMasterDetails(Context context , String refreshedToken) {

        DatabaseUtils databaseUtils = new DatabaseUtils(context);
        RadyabSetting radyabSetting = new RadyabSetting();

        if(databaseUtils.loadMadterList().size() == 0) {
            radyabSetting.setMasterPhoneNamber(DeviceUtils.getSimCardOneNumber(context));
            radyabSetting.setMasterPushNotificationToken(refreshedToken);
            radyabSetting.setMasterTokenValid(true);
            databaseUtils.saveMasterToken(radyabSetting);
        }else {
            radyabSetting.setMasterPhoneNamber(DeviceUtils.getSimCardOneNumber(context));
            radyabSetting.setMasterPushNotificationToken(refreshedToken);
            radyabSetting.setMasterTokenValid(true);
            databaseUtils.updateMasterToken(radyabSetting);
        }

    }

    public String createRequestServiceJson(int type){
        Gson gson = new Gson();
        PushObject pushObject = new PushObject();
        RequestService requestService = new RequestService();
        requestService.setServiceType(type);

        pushObject.setTo(MainRadyabActivity.slaveDetails.getSlavePushNotificationToken());
        String m = gson.toJson(requestService);
        pushObject.data.setMessage(m);//responseToken
        String json = gson.toJson(pushObject);
        int a = 5 ;
        a++;
        return json;
    }
}
