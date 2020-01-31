package ir.sajjadyosefi.android.tubeless.asyncTask.radyab;

import android.content.Context;
import android.os.AsyncTask;

import com.zl.reik.dilatingdotsprogressbar.DilatingDotsProgressBar;

import ir.sajjadyosefi.android.xTubeless.classes.model.common.Sms;
import ir.sajjadyosefi.android.xTubeless.utility.radyab.SMSUtils;


/**
 * Created by Other on 10/29/2016.
 */
public class AsyncSendSms extends AsyncTask{

    Context context;
    DilatingDotsProgressBar progressBar;
    Sms sms;

    public AsyncSendSms(Context context, DilatingDotsProgressBar dilatingDotsProgressBar, Sms sms) {
        this.context = context;
        this.progressBar = dilatingDotsProgressBar;
        this.sms = sms;
    }

    @Override
    protected Object doInBackground(Object[] params) {
        SMSUtils.sendSMS(context,sms);

        return null;
    }



}
