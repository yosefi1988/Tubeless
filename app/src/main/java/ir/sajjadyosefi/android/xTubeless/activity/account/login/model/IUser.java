package ir.sajjadyosefi.android.xTubeless.activity.account.login.model;

import ir.sajjadyosefi.android.xTubeless.activity.account.login.presenter.IPresenter;
import ir.sajjadyosefi.android.xTubeless.classes.model.network.request.accounting.LoginRequest;
import ir.sajjadyosefi.android.xTubeless.networkLayout.retrofit.TubelessRetrofitCallbackss;

public interface IUser {

    void CheckUserValidity(IPresenter presenter , LoginRequest request);
}
