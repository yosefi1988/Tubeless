package ir.sajjadyosefi.android.xTubeless.activity.common.splashScreen.presenter;

import android.content.Context;

import org.litepal.LitePal;

import ir.sajjadyosefi.android.xTubeless.Global;
import ir.sajjadyosefi.android.xTubeless.activity.account.login.model.IUser;
import ir.sajjadyosefi.android.xTubeless.activity.common.splashScreen.model.IRegisterDeviceModel;
import ir.sajjadyosefi.android.xTubeless.activity.common.splashScreen.view.ISplashScreenView;
import ir.sajjadyosefi.android.xTubeless.classes.SAccounts;
import ir.sajjadyosefi.android.xTubeless.classes.Validator;
import ir.sajjadyosefi.android.xTubeless.classes.model.Device;
import ir.sajjadyosefi.android.xTubeless.classes.model.network.request.accounting.LoginRequest;
import ir.sajjadyosefi.android.xTubeless.classes.model.request.DeviceRequest;
import ir.sajjadyosefi.android.xTubeless.classes.model.user.User;
import ir.sajjadyosefi.android.xTubeless.utility.DeviceUtil;

public class SplashScreenPresenterCompl implements ISplashScreenPeresenter {

    private final Context context;
    ISplashScreenView splashScreen;
    IRegisterDeviceModel iRegisterDeviceModel;
    IUser iUser;

    public SplashScreenPresenterCompl(Context context , ISplashScreenView splashScreen) {
        this.splashScreen = splashScreen;
        this.context = context ;
        iRegisterDeviceModel = new Device(context);
        iUser = new User(context);
    }


    @Override
    public void registerDevice() {
        splashScreen.showProgressBar();
        DeviceRequest deviceRequest = new DeviceRequest(iRegisterDeviceModel);
        iRegisterDeviceModel.tryToRegisterDevice(this,deviceRequest);
    }

    @Override
    public boolean isFirstRun() {
        return iRegisterDeviceModel.checkDeviceIsRegistered();
    }

    @Override
    public void onSuccess() {
        iRegisterDeviceModel.setRegisterDeviceIsDone();
        goToMainPage();
    }

    @Override
    public void goToMainPage() {
        splashScreen.hideProgressBar();

        SAccounts sAccounts = new SAccounts(context);
        if (sAccounts.hasUserAccount()){
            if (iUser.loadUserData() != null);
                splashScreen.onSplashScreenDone();
        }else {
            splashScreen.onSplashScreenDone();
        }
    }

    @Override
    public void onThrowException(Throwable t) {
        splashScreen.onThrowException(t);
    }

}
