package ir.sajjadyosefi.android.xTubeless.classes.modelY.NetworkLayout;

/**
 * Created by sajjad on 10/31/2016.
 */
public class  ResponseStatus {
    private int Code;
    private String Message;

    public ResponseStatus() {
        Code = -1 ;
        Message = "";
    }

    public int getCode() {
        return Code;
    }

    public void setCode(int code) {
        Code = code;
    }

    public String getMessage() {
        return Message;
    }

    public void setMessage(String message) {
        Message = message;
    }



}
