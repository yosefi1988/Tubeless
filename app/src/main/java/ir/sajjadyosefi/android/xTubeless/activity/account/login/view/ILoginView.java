package ir.sajjadyosefi.android.xTubeless.activity.account.login.view;

import ir.sajjadyosefi.android.xTubeless.activity.account.login.model.IUser;

public interface ILoginView {
    void onLoginSuccessFinish(IUser user);
    void onThrowException(Throwable t);

    void showProgressBar();
    void hideProgressBar();

    void OnPressGoToForgetPassword();
}
