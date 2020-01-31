package ir.sajjadyosefi.android.tubeless.asyncTask.account;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;

import com.zl.reik.dilatingdotsprogressbar.DilatingDotsProgressBar;

import ir.sajjadyosefi.android.tubeless.classes.CommonClass;
import ir.sajjadyosefi.android.tubeless.networkLayout.networkLayout.RestApiHelper;

import ir.sajjadyosefi.android.tubeless.Global;
import ir.sajjadyosefi.android.tubeless.R;
import ir.sajjadyosefi.android.xTubeless.activity.MainActivity;
import ir.sajjadyosefi.android.xTubeless.classes.modelY.NetworkLayout.ChangePasswordResponse;
import ir.sajjadyosefi.android.xTubeless.classes.model.user.User;


/**
 * Created by Other on 10/23/2016.
 */
public class AsyncChangePassword extends AsyncTask {
    private final Context mContext;
    private final User mUser;
    DilatingDotsProgressBar mProgressBar;

    public AsyncChangePassword(Context context,DilatingDotsProgressBar dilatingDotsProgressBar, User user) {
        this.mContext = context;
        this.mUser = user;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        if (mProgressBar != null)
            mProgressBar.showNow();
    }


    @Override
    protected ChangePasswordResponse doInBackground(Object[] params) {
        if (CommonClass.isNetworkConnected(mContext)) {
            RestApiHelper httpUrlHelper = new RestApiHelper();
            ChangePasswordResponse changePasswordResponse = httpUrlHelper.ChangePassword(mContext,mUser);
            return changePasswordResponse;
        }else
            return null;
    }

    @Override
    protected void onPostExecute(Object response) {
        ChangePasswordResponse changePasswordResponse = (ChangePasswordResponse) response;

        //Global.ShowMessageDialog(mContext,"",changePasswordResponse.getServerStatus().getMessage());
//        Toast.makeText(mContext,changePasswordResponse.getServerStatus().getMessage(), Toast.LENGTH_SHORT).show();
//        if(changePasswordResponse.getServerStatus().getCode() == 203){
        if(true){

//        else {
//            //ok
//
//            Global.user = registerResponse.getUser();
//
            Global.user = null;
            Intent intent = new Intent(mContext, MainActivity.class);
            ((Activity)mContext).startActivity(intent);
            ((Activity)mContext).overridePendingTransition(R.anim.fadeout, R.anim.fadein);
            ((Activity)mContext).finish();
//            Toast.makeText(mContext,registerResponse.getUser().getUserName(), Toast.LENGTH_SHORT).show();
        }
        if (mProgressBar != null)
            mProgressBar.hideNow();
    }
}
