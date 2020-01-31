package ir.sajjadyosefi.android.xTubeless.classes.modelY;

import com.orm.SugarRecord;

import java.io.Serializable;

/**
 * Created by sajjad on 2/17/2017.
 */
public class Content extends SugarRecord implements Serializable {
    public String ServerID ;
    public String Title ;
    public String Text ;
    public int CatID ;
    public boolean isFav ;


    public Content() {
    }
}
