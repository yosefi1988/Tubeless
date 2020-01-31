package ir.sajjadyosefi.android.tubeless.asyncTask.account;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.widget.Toast;

import com.zl.reik.dilatingdotsprogressbar.DilatingDotsProgressBar;

import ir.sajjadyosefi.android.tubeless.classes.CommonClass;
import ir.sajjadyosefi.android.tubeless.networkLayout.networkLayout.RestApiHelper;


import ir.sajjadyosefi.android.tubeless.Global;
import ir.sajjadyosefi.android.tubeless.R;
import ir.sajjadyosefi.android.xTubeless.activity.MainActivity;
import ir.sajjadyosefi.android.xTubeless.classes.modelY.NetworkLayout.RegisterResponse;
import ir.sajjadyosefi.android.xTubeless.classes.model.user.User;


/**
 * Created by Other on 10/23/2016.
 */
public class AsyncRegister extends AsyncTask {
    private final Context mContext;
    private final User mUser;
    DilatingDotsProgressBar mProgressBar;

    public AsyncRegister(Context context,DilatingDotsProgressBar dilatingDotsProgressBar, User user) {
        this.mContext = context;
        this.mUser = user;
        this.mProgressBar = dilatingDotsProgressBar;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        if (mProgressBar != null)
            mProgressBar.showNow();
    }


    @Override
    protected RegisterResponse doInBackground(Object[] params) {
        if (CommonClass.isNetworkConnected(mContext)) {
            RestApiHelper httpUrlHelper = new RestApiHelper();
            RegisterResponse registerResponse = httpUrlHelper.Register(mContext,mUser);
            return registerResponse;
        }else
            return null;
    }

    @Override
    protected void onPostExecute(Object response) {
        RegisterResponse registerResponse = (RegisterResponse) response;
        if(response == null){
            Global.ShowMessageDialog(mContext, "",mContext.getString(R.string.RegisterErrorOnServer));
        }else {
//            if (registerResponse.getServerStatus().getCode() == 98)
//                Global.ShowMessageDialog(mContext, "", registerResponse.getServerStatus().getMessage());
//            else {
                //ok

                Global.user = registerResponse.getUser();

                Intent intent = new Intent(mContext, MainActivity.class);
                ((Activity) mContext).startActivity(intent);
                ((Activity) mContext).overridePendingTransition(R.anim.fadeout, R.anim.fadein);
                ((Activity) mContext).finish();
                //Global.ShowMessageDialog(mContext,"",registerResponse.getUser().getUserName());
                Toast.makeText(mContext, registerResponse.getServerStatus().getMessage(), Toast.LENGTH_SHORT).show();
            }
//        }
        if (mProgressBar != null)
            mProgressBar.hideNow();
    }
}
