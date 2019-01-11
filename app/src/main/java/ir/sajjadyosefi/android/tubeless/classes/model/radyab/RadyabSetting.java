package ir.sajjadyosefi.android.tubeless.classes.model.radyab;

import com.j256.ormlite.field.DatabaseField;

import java.util.List;

import ir.sajjadyosefi.android.tubeless.classes.model.Car.Car;

/**
 * Created by sajjad on 5/2/2018.
 */

public class RadyabSetting {

    @DatabaseField
    private String masterPushNotificationToken ;

    @DatabaseField
    private String masterPhoneNamber ;

    @DatabaseField
    private boolean isMasterTokenValid;

    public String getMasterPushNotificationToken() {
        return masterPushNotificationToken;
    }

    public void setMasterPushNotificationToken(String masterPushNotificationToken) {
        this.masterPushNotificationToken = masterPushNotificationToken;
    }

    public String getMasterPhoneNamber() {
        return masterPhoneNamber;
    }

    public void setMasterPhoneNamber(String masterPhoneNamber) {
        this.masterPhoneNamber = masterPhoneNamber;
    }

    public boolean isMasterTokenValid() {
        return isMasterTokenValid;
    }

    public void setMasterTokenValid(boolean masterTokenValid) {
        isMasterTokenValid = masterTokenValid;
    }





}
