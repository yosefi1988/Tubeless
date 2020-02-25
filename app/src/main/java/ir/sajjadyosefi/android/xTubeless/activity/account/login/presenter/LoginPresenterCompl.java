package ir.sajjadyosefi.android.xTubeless.activity.account.login.presenter;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;

import ir.sajjadyosefi.android.xTubeless.activity.account.login.model.IUser;
import ir.sajjadyosefi.android.xTubeless.activity.account.login.view.ILoginView;
import ir.sajjadyosefi.android.xTubeless.classes.model.network.request.accounting.LoginRequest;
import ir.sajjadyosefi.android.xTubeless.classes.model.user.User;

public class LoginPresenterCompl implements IPresenter {
    IUser iUser;
    ILoginView iLoginView;
    Context context;
    LoginPresenterCompl presenter;
    Handler handler;

    public LoginPresenterCompl(Context context, ILoginView iLoginView) {
        iUser = new User(context);
        this.iLoginView = iLoginView;
        this.context = context;
        presenter = this;
        handler = new Handler(Looper.getMainLooper());

    }

    @Override
    public void tryToLoginByPhoneNumber(String phoneNumber, String password) {
        LoginRequest loginRequest = new LoginRequest(phoneNumber, password, getAndroidId(context));
        iLoginView.showProgressBar();
        iUser.CheckUserValidity(presenter, loginRequest);
    }

    @Override
    public void tryToLoginByMail(String email,String photoUrl) {
        LoginRequest loginRequest = new LoginRequest(email,(photoUrl == null ? "" : photoUrl));
        iLoginView.showProgressBar();
        iUser.CheckUserValidity(presenter, loginRequest);
    }

    @Override
    public void tryToLoginBySimCard(String simId) {
        LoginRequest loginRequest = new LoginRequest(simId);
        iLoginView.showProgressBar();
        iUser.CheckUserValidity(presenter, loginRequest);
    }

    @Override
    public String getAndroidId(Context context) {
        return null;
    }

    @Override
    public void onSuccess(IUser xxxxxxxx) {
        iLoginView.hideProgressBar();
        iLoginView.onLoginSuccessFinish(xxxxxxxx);
    }

    @Override
    public void onThrowException(Throwable t) {
        iLoginView.hideProgressBar();
        iLoginView.onThrowException(t);
    }


//    @Override
//    public void doLogin(String name, String passwd) {
//        Boolean isLoginSuccess = true;
//        final int code = 0;//= user.checkUserValidity(name,passwd);
//        if (code != 0) isLoginSuccess = false;
//        final Boolean result = isLoginSuccess;
//        handler.postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                iLoginView.onLoginSuccessFinish(result, code);
//            }
//        }, 5000);
//    }


    @Override
    public void goToForgetPassword() {
        iLoginView.OnPressGoToForgetPassword();
    }

}