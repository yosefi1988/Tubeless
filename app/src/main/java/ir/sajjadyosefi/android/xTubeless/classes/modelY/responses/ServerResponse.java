package ir.sajjadyosefi.android.xTubeless.classes.modelY.responses;


import ir.sajjadyosefi.android.xTubeless.classes.modelY.responses.basic.ServerStatus;

/**
 * Created by sajjad on 3/1/2017.
 */
public class ServerResponse {
    public ServerStatus serverStatus;
    public Exception exception;

    public Exception getException() {
        return exception;
    }

    public void setException(Exception exception) {
        this.exception = exception;
    }


    public ServerStatus getServerStatus() {
        return serverStatus;
    }

    public void setServerStatus(ServerStatus serverStatus) {
        this.serverStatus = serverStatus;
    }
}
