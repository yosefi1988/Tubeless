package ir.sajjadyosefi.android.tubeless.asyncTask.account;

import android.content.Context;
import android.os.AsyncTask;

import ir.sajjadyosefi.android.tubeless.classes.CommonClass;
import ir.sajjadyosefi.android.tubeless.networkLayout.networkLayout.RestApiHelper;

import ir.sajjadyosefi.android.xTubeless.classes.modelY.Message;
import ir.sajjadyosefi.android.tubeless.Global;
import ir.sajjadyosefi.android.xTubeless.classes.modelY.NetworkLayout.MessageResponse;


/**
 * Created by Other on 10/23/2016.
 */
public class AsyncSendMessage extends AsyncTask {
    private final Context mContext;
    private final Message mMessage;

    public AsyncSendMessage(Context context, Message message) {
        this.mContext = context;
        this.mMessage = message;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
//        if (mProgressBar != null)
//            mProgressBar.showNow();
    }


    @Override
    protected MessageResponse doInBackground(Object[] params) {
        if (CommonClass.isNetworkConnected(mContext)) {
            RestApiHelper httpUrlHelper = new RestApiHelper();
            MessageResponse changePasswordResponse = httpUrlHelper.SendMessage(mContext,mMessage);
            return changePasswordResponse;
        }else
            return null;
    }

    @Override
    protected void onPostExecute(Object response) {
        MessageResponse registerResponse = (MessageResponse) response;

        Global.ShowMessageDialog(mContext,"",registerResponse.getServerStatus().getMessage());
        if(registerResponse.getServerStatus().getCode() == 204){

//        else {
//            //ok
//
//            Global.user = registerResponse.getUser();
//
            //((Activity)mContext).finish();
//            Toast.makeText(mContext,registerResponse.getUser().getUserName(), Toast.LENGTH_SHORT).show();
        }

     }
}
