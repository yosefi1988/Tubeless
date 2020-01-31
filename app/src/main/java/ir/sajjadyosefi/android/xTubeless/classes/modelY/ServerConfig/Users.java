package ir.sajjadyosefi.android.xTubeless.classes.model.ServerConfig;

import java.io.Serializable;

/**
 * Created by sajjad on 10/31/2016.
 */
public class Users implements Serializable {
    int count;
    String state [];

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String[] getState() {
        return state;
    }

    public void setState(String[] state) {
        this.state = state;
    }
}
