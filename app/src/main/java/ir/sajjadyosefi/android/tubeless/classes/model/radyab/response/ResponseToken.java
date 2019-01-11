package ir.sajjadyosefi.android.tubeless.classes.model.radyab.response;


import ir.sajjadyosefi.android.tubeless.classes.model.Responses.ServerResponse;
import ir.sajjadyosefi.android.tubeless.classes.model.Responses.ServerStatus;

/**
 * Created by sajjad on 5/16/2018.
 */

public class ResponseToken {
    public String slavePushNotificationToken;
    public int Type;


    public int getType() {
        return Type;
    }

    public void setType(int type) {
        Type = type;
    }



    public String getSlavePushNotificationToken() {
        return slavePushNotificationToken;
    }

    public void setSlavePushNotificationToken(String slavePushNotificationToken) {
        this.slavePushNotificationToken = slavePushNotificationToken;
    }



}
