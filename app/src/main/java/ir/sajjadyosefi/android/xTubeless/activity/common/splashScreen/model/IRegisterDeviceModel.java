package ir.sajjadyosefi.android.xTubeless.activity.common.splashScreen.model;

import android.content.Context;

import ir.sajjadyosefi.android.xTubeless.activity.common.splashScreen.presenter.ISplashScreenPeresenter;
import ir.sajjadyosefi.android.xTubeless.activity.common.splashScreen.presenter.SplashScreenPresenterCompl;
import ir.sajjadyosefi.android.xTubeless.classes.model.request.DeviceRequest;

public interface IRegisterDeviceModel {

    void tryToRegisterDevice(ISplashScreenPeresenter peresenter ,  DeviceRequest deviceRequest);
    boolean checkIsFirstRun();
    boolean setFirstRunIsDone();

}
