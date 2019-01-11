package ir.sajjadyosefi.android.tubeless.classes.model.ServerConfig;

import java.io.Serializable;

/**
 * Created by sajjad on 12/23/2016.
 */
public class Setting implements Serializable {
    int transactionNewCount ;

    public int getTransactionNewCount() {
        return transactionNewCount;
    }

    public void setTransactionNewCount(int transactionNewCount) {
        this.transactionNewCount = transactionNewCount;
    }
}
