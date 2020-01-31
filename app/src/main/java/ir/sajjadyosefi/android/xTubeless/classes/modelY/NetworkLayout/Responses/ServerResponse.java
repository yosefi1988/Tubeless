package ir.sajjadyosefi.android.xTubeless.classes.modelY.NetworkLayout.Responses;

import ir.sajjadyosefi.android.xTubeless.classes.modelY.responses.basic.ServerStatus;

/**
 * Created by sajjad on 3/1/2017.
 */
public class ServerResponse {
    public ServerStatus serverStatus;

    public ServerStatus getServerStatus() {
        return serverStatus;
    }

    public void setServerStatus(ServerStatus serverStatus) {
        this.serverStatus = serverStatus;
    }
}
