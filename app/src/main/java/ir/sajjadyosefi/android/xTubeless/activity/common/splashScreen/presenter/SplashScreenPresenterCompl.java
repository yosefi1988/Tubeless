package ir.sajjadyosefi.android.xTubeless.activity.common.splashScreen.presenter;

import android.content.Context;

import ir.sajjadyosefi.android.xTubeless.activity.common.splashScreen.model.IRegisterDeviceModel;
import ir.sajjadyosefi.android.xTubeless.activity.common.splashScreen.view.ISplashScreenView;
import ir.sajjadyosefi.android.xTubeless.classes.model.Device;
import ir.sajjadyosefi.android.xTubeless.classes.model.request.DeviceRequest;

public class SplashScreenPresenterCompl implements ISplashScreenPeresenter {

    private final Context context;
    ISplashScreenView splashScreen;
    IRegisterDeviceModel iRegisterDeviceModel;

    public SplashScreenPresenterCompl(Context context , ISplashScreenView splashScreen) {
        this.splashScreen = splashScreen;
        this.context = context ;
        iRegisterDeviceModel = new Device(context);
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
    public void goToMainPage() {
        splashScreen.hideProgressBar();
        splashScreen.onSplashScreenDone();
    }

    @Override
    public void onSuccess() {
        splashScreen.hideProgressBar();
        iRegisterDeviceModel.setRegisterDeviceIsDone();
        splashScreen.onSplashScreenDone();
    }

    @Override
    public void onThrowException(Throwable t) {
        splashScreen.onThrowException(t);
    }

}
