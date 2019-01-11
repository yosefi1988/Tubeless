package ir.sajjadyosefi.android.tubeless.asyncTask.account;

import android.content.Context;
import android.os.AsyncTask;

import ir.sajjadyosefi.android.tubeless.classes.CommonClass;
import ir.sajjadyosefi.android.tubeless.classes.networkLayout.RestApiHelper;
import ir.sajjadyosefi.android.tubeless.classes.model.NetworkLayout.DeviceRegisterResponse;
import ir.sajjadyosefi.android.tubeless.classes.model.User.Device;
import ir.sajjadyosefi.android.tubeless.classes.model.User.User;
import ir.sajjadyosefi.android.tubeless.Global;


/**
 * Created by Other on 10/23/2016.
 */
public class AsyncDeviceRegister extends AsyncTask {
    private final Context mContext;
    private final Device mDevice;

    public AsyncDeviceRegister(Context context, Device device) {
        this.mContext = context;
        this.mDevice = device;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
//        if (mProgressBar != null)
//            mProgressBar.showNow();
    }


    @Override
    protected DeviceRegisterResponse doInBackground(Object[] params) {
        if (CommonClass.isNetworkConnected(mContext)) {
            RestApiHelper httpUrlHelper = new RestApiHelper();
            DeviceRegisterResponse deviceRegisterResponse = httpUrlHelper.Register(mContext,mDevice);
            return deviceRegisterResponse;
        }else
            return null;
    }

    @Override
    protected void onPostExecute(Object response) {
        if(response!=null){
            DeviceRegisterResponse deviceRegisterResponse = (DeviceRegisterResponse) response;
            Global.mUser = new User();
            Global.mUser.setDevice(deviceRegisterResponse.getDevice());
        }
    }
}
