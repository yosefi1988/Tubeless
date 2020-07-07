package ir.sajjadyosefi.android.xTubeless.classes.model.config;

import ir.sajjadyosefi.android.xTubeless.classes.model.ServerStatus;
import ir.sajjadyosefi.android.xTubeless.classes.model.exception.TubelessException;

/**
 * Created by sajjad on 10/31/2016.
 */
public class ServerResponseStatus {
    public TubelessResponseStatus getResponseStatus() {
        return responseStatus;
    }

    public void setResponseStatus(TubelessResponseStatus responseStatus) {
        this.responseStatus = responseStatus;
    }

    private TubelessResponseStatus responseStatus;



}
