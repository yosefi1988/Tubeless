package ir.sajjadyosefi.android.xTubeless.activity.common.splashScreen.presenter;


import android.content.Context;

public interface ISplashScreenPeresenter {

    void registerDevice();
    boolean isFirstRun();

    void onSuccess();

    void onThrowException(Throwable t);

    void goToMainPage();

}
