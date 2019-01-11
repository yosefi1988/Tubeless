package ir.sajjadyosefi.android.tubeless.classes.model.NetworkLayout;

import ir.sajjadyosefi.android.tubeless.classes.model.User.Device;

/**
 * Created by sajjad on 3/2/2017.
 */
public class DeviceRegisterResponse extends ServerResponseBase {
    public Device getDevice() {
        return Device;
    }

    public void setDevice(Device device) {
        this.Device = device;
    }

    private Device Device ;


}

