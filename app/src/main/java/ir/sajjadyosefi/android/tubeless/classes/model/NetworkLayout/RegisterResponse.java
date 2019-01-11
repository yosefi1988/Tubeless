package ir.sajjadyosefi.android.tubeless.classes.model.NetworkLayout;

import ir.sajjadyosefi.android.tubeless.classes.model.User.User;

/**
 * Created by sajjad on 3/2/2017.
 */
public class RegisterResponse extends ServerResponseBase {
    private User User ;

    public User getUser() {
        return User;
    }

    public void setUser(User user) {
        this.User = user;
    }

}

