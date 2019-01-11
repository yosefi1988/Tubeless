package ir.sajjadyosefi.android.tubeless.asyncTask.other;

import android.content.Context;
import android.os.AsyncTask;

import java.util.List;

import ir.sajjadyosefi.android.tubeless.classes.CommonClass;
import ir.sajjadyosefi.android.tubeless.classes.model.AppStatus;
import ir.sajjadyosefi.android.tubeless.classes.model.RequestConfirmPayment;
import ir.sajjadyosefi.android.tubeless.classes.networkLayout.RestApiHelper;


/**
 * Created by Other on 10/29/2016.
 */
public class AsyncPeymentResult extends AsyncTask {

    private final Context context;
    private final RequestConfirmPayment requestConfirmPayment;


    public AsyncPeymentResult(Context context, RequestConfirmPayment requestConfirmPayment) {
        this.context = context ;
        this.requestConfirmPayment = requestConfirmPayment;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();

    }

    @Override
    protected Object doInBackground(Object[] params) {

        RestApiHelper myParser = new RestApiHelper();
        return myParser.ConfirmPayment(requestConfirmPayment);
    }

    @Override
    protected void onPostExecute(final Object serverResponse) {
        super.onPostExecute( serverResponse);

        if (serverResponse != null) {

            //Save
            AppStatus appStatus;
            List<AppStatus> appStatusList = AppStatus.listAll(AppStatus.class);
            if (appStatusList.size() == 0) {

            } else {
                appStatus = appStatusList.get(0);
//                appStatus.buyStatus = ((ServerResponseConfig) serverResponse).details.BuyStatus;
//                appStatus.InstallDate = ((ServerResponseConfig) serverResponse).details.InstallDate;
                appStatus.save();
            }
            //End Save

        }else {
            CommonClass.ShowInternetConnection(context,CommonClass.CONTACT_WITH_US,requestConfirmPayment.getRefId());
        }
    }
}
