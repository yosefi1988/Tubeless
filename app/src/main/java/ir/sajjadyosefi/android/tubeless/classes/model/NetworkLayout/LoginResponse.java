package ir.sajjadyosefi.android.tubeless.classes.model.NetworkLayout;

import ir.sajjadyosefi.android.tubeless.classes.model.User.Device;
import ir.sajjadyosefi.android.tubeless.classes.model.User.User;

/**
 * Created by sajjad on 3/2/2017.
 */
public class LoginResponse extends ServerResponseBase {
    private User User ;
    private Device Device;

    public ir.sajjadyosefi.android.tubeless.classes.model.User.Device getDevice() {
        return Device;
    }

    public void setDevice(ir.sajjadyosefi.android.tubeless.classes.model.User.Device device) {
        Device = device;
    }

    public User getUser() {
        return User;
    }

    public void setUser(User user) {
        this.User = user;
    }

}

