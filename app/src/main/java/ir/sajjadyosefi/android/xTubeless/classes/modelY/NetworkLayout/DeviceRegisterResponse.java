package ir.sajjadyosefi.android.xTubeless.classes.modelY.NetworkLayout;

import ir.sajjadyosefi.android.xTubeless.classes.modelY.Device;
import ir.sajjadyosefi.android.xTubeless.classes.modelY.responses.basic.ServerResponseBase;

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

