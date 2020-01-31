package ir.sajjadyosefi.android.xTubeless.classes.modelY.radyab.response;


import ir.sajjadyosefi.android.xTubeless.classes.modelY.NetworkLayout.Responses.ServerResponse;

/**
 * Created by sajjad on 5/16/2018.
 */

public class ResponseTokenRoot extends ServerResponse {
    public ResponseToken getMessage() {
        return message;
    }

    public void setMessage(ResponseToken message) {
        this.message = message;
    }

    ResponseToken message = new ResponseToken();

}
