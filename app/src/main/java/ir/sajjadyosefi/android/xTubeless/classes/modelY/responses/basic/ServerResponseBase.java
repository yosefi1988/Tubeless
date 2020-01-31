package ir.sajjadyosefi.android.xTubeless.classes.modelY.responses.basic;
import ir.sajjadyosefi.android.xTubeless.classes.modelY.Exception.TubelessException;

/**
 * Created by sajjad on 10/31/2016.
 */
public class ServerResponseBase {
    private TubelessException tubelessException;

    public TubelessException getTubelessException() {
        return tubelessException;
    }

    public void setTubelessException(TubelessException tubelessException) {
        this.tubelessException = tubelessException;
    }

    public ServerStatus getServerStatus() {
        return null;
    }
}
