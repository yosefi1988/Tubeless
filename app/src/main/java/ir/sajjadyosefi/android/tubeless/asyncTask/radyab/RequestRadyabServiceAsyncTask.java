package ir.sajjadyosefi.android.tubeless.asyncTask.radyab;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import ir.sajjadyosefi.android.tubeless.classes.business.RadyabBusiness;
import ir.sajjadyosefi.android.tubeless.classes.model.radyab.response.GooglePushResponse;
import ir.sajjadyosefi.android.tubeless.classes.networkLayout.HttpUrlconnectionHelper;


/**
 * Created by sajjad on 5/7/2018.
 */

public class RequestRadyabServiceAsyncTask extends AsyncTask {

    private final int type;
    private Context mContext;
    private String messageToResoinse;

    public RequestRadyabServiceAsyncTask(Context mContext , int type) {
        this.mContext = mContext;
        this.type = type;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        RadyabBusiness radyabBusiness = new RadyabBusiness();
        messageToResoinse = radyabBusiness.createRequestServiceJson(type);
    }

    @Override
    protected GooglePushResponse doInBackground(Object[] objects) {
        HttpUrlconnectionHelper httpUrlconnectionHelper = new HttpUrlconnectionHelper();
        GooglePushResponse googlePushRequest = (GooglePushResponse) httpUrlconnectionHelper.PostRequestToFCM(messageToResoinse,GooglePushResponse.class);
        return googlePushRequest;
    }

    @Override
    protected void onPostExecute(Object googlePushResponse) {
        super.onPostExecute(googlePushResponse);
        GooglePushResponse googlePushResponse2 = (GooglePushResponse) googlePushResponse;

        try {
            for (GooglePushResponse.Results results : googlePushResponse2.getResults()) {
                Toast.makeText(mContext, results.error, Toast.LENGTH_LONG).show();
            }
        }catch (Exception ex){
            Toast.makeText(mContext, ex.getMessage(), Toast.LENGTH_LONG).show();
        }
    }
}