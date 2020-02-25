package ir.sajjadyosefi.android.xTubeless.activity.account.login.presenter;

import android.content.Context;

import ir.sajjadyosefi.android.xTubeless.activity.account.login.model.IUser;

public interface IPresenter {
    void tryToLoginByPhoneNumber(String phoneNumber, String password);
    void tryToLoginByMail(String email, String password);
    void tryToLoginBySimCard(String simId);

    String getAndroidId(Context context);

    void onSuccess(IUser user);

    void onThrowException(Throwable t);

    void goToForgetPassword();
}
