package ir.sajjadyosefi.android.xTubeless.classes.modelY.NetworkLayout;


import ir.sajjadyosefi.android.xTubeless.classes.model.ServerConfig.Setting;
import ir.sajjadyosefi.android.xTubeless.classes.model.ServerConfig.Users;
import ir.sajjadyosefi.android.xTubeless.classes.model.ServerConfig.Version;

/**
 * Created by sajjad on 10/31/2016.
 */
public class Configuration {

    Boolean canSendPicture;
    Setting setting ;
    Version version ;
    Users users ;

    public Setting getSetting() {
        return setting;
    }

    public void setSetting(Setting setting) {
        this.setting = setting;
    }

    public Version getVersion() {
        return version;
    }

    public void setVersion(Version version) {
        this.version = version;
    }

    public Users getUsers() {
        return users;
    }

    public void setUsers(Users users) {
        this.users = users;
    }


    public Boolean getCanSendPicture() {
        return canSendPicture;
    }

    public void setCanSendPicture(Boolean canSendPicture) {
        this.canSendPicture = canSendPicture;
    }
}
