package ir.sajjadyosefi.android.tubeless.classes.model.radyab.response;


import ir.sajjadyosefi.android.tubeless.classes.model.Responses.ServerResponse;
import ir.sajjadyosefi.android.tubeless.classes.model.Responses.ServerStatus;

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
