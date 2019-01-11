package ir.sajjadyosefi.android.tubeless.classes.model.NetworkLayout;

import ir.sajjadyosefi.android.tubeless.classes.model.User.Message;

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



}

