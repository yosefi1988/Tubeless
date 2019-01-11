package ir.sajjadyosefi.android.tubeless.asyncTask.account;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.widget.Toast;

import com.zl.reik.dilatingdotsprogressbar.DilatingDotsProgressBar;

import ir.sajjadyosefi.android.tubeless.asyncTask.config.AsyncCheckServerConfing;
import ir.sajjadyosefi.android.tubeless.classes.CommonClass;
import ir.sajjadyosefi.android.tubeless.classes.networkLayout.RestApiHelper;
import ir.sajjadyosefi.android.tubeless.classes.model.NetworkLayout.LoginResponse;
import ir.sajjadyosefi.android.tubeless.classes.model.User.Device;
import ir.sajjadyosefi.android.tubeless.classes.model.User.User;
import ir.sajjadyosefi.android.tubeless.Global;
import ir.sajjadyosefi.android.tubeless.R;
import ir.sajjadyosefi.android.tubeless.activity.MainActivity;


/**
 * Created by Other on 10/23/2016.
 */
public class AsyncLogin extends AsyncTask {
    private final Context mContext;
    private final User mUser;
    private final Device mDevice;
    DilatingDotsProgressBar mProgressBar;

    public AsyncLogin(Context context, DilatingDotsProgressBar dilatingDotsProgressBar, User user, Device device) {
        this.mContext = context;
        this.mUser = user;
        this.mDevice = device;
        this.mProgressBar = dilatingDotsProgressBar;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        if (mProgressBar != null)
            mProgressBar.showNow();
    }


    @Override
    protected LoginResponse doInBackground(Object[] params) {
        if (CommonClass.isNetworkConnected(mContext)) {
            RestApiHelper httpUrlHelper = new RestApiHelper();
            LoginResponse loginResponse = httpUrlHelper.Login(mContext,mUser,mDevice);
            return loginResponse;
        }else
            return null;
    }

    @Override
    protected void onPostExecute(Object response) {
        LoginResponse loginResponse = (LoginResponse) response;
        if(loginResponse.getServerStatus().getCode() == 98)
            Global.ShowMessageDialog(mContext,"",loginResponse.getServerStatus().getMessage());
        else {
            //ok

            Global.mUser = loginResponse.getUser();
            Global.SaveLogedInUser(mContext,loginResponse.getUser());

            Intent intent = new Intent(mContext,MainActivity.class);
            ((Activity)mContext).startActivity(intent);
            ((Activity)mContext).overridePendingTransition(R.anim.fadeout, R.anim.fadein);
            ((Activity)mContext).finish();

            //Global.ShowMessageDialog(mContext,"",loginResponse.getUser().getUserName());
            Toast.makeText(mContext,loginResponse.getServerStatus().getMessage(), Toast.LENGTH_SHORT).show();


            new AsyncCheckServerConfing(mContext).execute();
        }
        if (mProgressBar != null)
            mProgressBar.hideNow();
    }
}
