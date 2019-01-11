package ir.sajjadyosefi.android.tubeless.classes.model;

import com.orm.SugarRecord;

import java.io.Serializable;

/**
 * Created by sajjad on 2/17/2017.
 */
public class AppStatus  extends SugarRecord implements Serializable {

    public String buyStatus;
    public String AllSends;
    public String AllReciveMony;
    public String AllPendingMony;
    public String UserName;
    public String UserPicture;
    public String InstallDate;

    public AppStatus() {
    }
}
