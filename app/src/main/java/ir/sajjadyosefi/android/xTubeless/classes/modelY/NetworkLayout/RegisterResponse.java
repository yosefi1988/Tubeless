package ir.sajjadyosefi.android.xTubeless.classes.modelY.NetworkLayout;



import ir.sajjadyosefi.android.xTubeless.classes.modelY.responses.basic.ServerResponseBase;
import ir.sajjadyosefi.android.xTubeless.classes.modelY.responses.basic.ServerStatus;
import ir.sajjadyosefi.android.xTubeless.classes.model.user.User;

/**
 * Created by sajjad on 3/2/2017.
 */
public class RegisterResponse extends ServerResponseBase {
    private ir.sajjadyosefi.android.xTubeless.classes.model.user.User User ;

    public User getUser() {
        return User;
    }

    public void setUser(User user) {
        this.User = user;
    }

    public ServerStatus getServerStatus() {
        return null;
    }
}

