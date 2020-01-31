package ir.sajjadyosefi.android.xTubeless.classes.modelY.NetworkLayout;

import ir.sajjadyosefi.android.xTubeless.classes.modelY.Message;
import ir.sajjadyosefi.android.xTubeless.classes.modelY.responses.basic.ServerResponseBase;
import ir.sajjadyosefi.android.xTubeless.classes.modelY.responses.basic.ServerStatus;

/**
 * Created by sajjad on 3/2/2017.
 */
public class MessageResponse extends ServerResponseBase {
    private Message message ;

    public Message getMessage() {
        return message;
    }

    public void setMessage(Message message) {
        this.message = message;
    }


    public ServerStatus getServerStatus() {
        return null;
    }
}

