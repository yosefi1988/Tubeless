package ir.sajjadyosefi.android.xTubeless.activity.account.login.model;

import ir.sajjadyosefi.android.xTubeless.activity.account.login.presenter.ILoginPresenterI;
import ir.sajjadyosefi.android.xTubeless.classes.model.network.request.accounting.LoginRequest;

public interface IUser {

    void CheckUserValidity(ILoginPresenterI presenter , LoginRequest request);
}
