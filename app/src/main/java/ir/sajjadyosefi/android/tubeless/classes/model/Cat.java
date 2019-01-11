package ir.sajjadyosefi.android.tubeless.classes.model;

import com.orm.SugarRecord;
import java.io.Serializable;

/**
 * Created by sajjad on 2/17/2017.
 */
public class Cat  extends SugarRecord implements Serializable {
    public int serverID;
    public String title;
    public int parentId;

    public Cat() {
    }
}
