package ir.sajjadyosefi.android.xTubeless.classes.model.network.responses;


import ir.sajjadyosefi.android.xTubeless.classes.modelY.Exception.TubelessException;
import ir.sajjadyosefi.android.xTubeless.classes.model.user.User;

/**
 * Created by sajjad on 10/31/2016.
 */
public class LoginResponse extends User {
    private TubelessException tubelessException;

    public TubelessException getTubelessException() {
        return tubelessException;
    }

    public void setTubelessException(TubelessException tubelessException) {
        this.tubelessException = tubelessException;
    }
}
