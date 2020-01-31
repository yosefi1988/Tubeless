package ir.sajjadyosefi.android.xTubeless.classes.modelY.NetworkLayout;

import java.io.Serializable;

import ir.sajjadyosefi.android.xTubeless.classes.modelY.responses.basic.ServerResponseBase;
import ir.sajjadyosefi.android.xTubeless.classes.modelY.responses.basic.ServerStatus;


/**
 * Created by sajjad on 06/18/2016.
 */
public class ServerResponseConfig extends ServerResponseBase implements Serializable {


    public ResponseDetails details;
//    public int index;
    Configuration configuration ;

    public Configuration getConfiguration() {
        return configuration;
    }

    public void setConfiguration(Configuration configuration) {
        this.configuration = configuration;
    }

    public ResponseDetails getDetails() {
        return details;
    }

    public void setDetails(ResponseDetails details) {
        this.details = details;
    }

    public ServerStatus getResponseStatus() {
        return null;
    }

//    public int getIndex() {
//        return index;
//    }
//
//    public void setIndex(int index) {
//        this.index = index;
//    }

}

