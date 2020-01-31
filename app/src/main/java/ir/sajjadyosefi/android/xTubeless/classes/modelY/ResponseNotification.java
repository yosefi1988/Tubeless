package ir.sajjadyosefi.android.xTubeless.classes.modelY;

import java.io.Serializable;

import ir.sajjadyosefi.android.xTubeless.classes.modelY.NetworkLayout.ResponseStatus;


/**
 * Created by sajjad on 07/05/2016.
 */
public class ResponseNotification implements Serializable {


    private Notification notification = new Notification();
    private ResponseStatus resultStatus = new ResponseStatus();

    public Notification getNotification() {
        return notification;
    }

    public void setNotification(Notification notification) {
        this.notification = notification;
    }

    public ResponseStatus getResultStatus() {
        return resultStatus;
    }

    public void setResultStatus(ResponseStatus resultStatus) {
        this.resultStatus = resultStatus;
    }


}
