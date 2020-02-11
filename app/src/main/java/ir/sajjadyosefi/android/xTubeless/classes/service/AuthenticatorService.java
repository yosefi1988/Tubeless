package ir.sajjadyosefi.android.xTubeless.classes.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import ir.sajjadyosefi.android.xTubeless.classes.account.AccountAuthenticator;

public class AuthenticatorService extends Service {
    @Override
    public IBinder onBind(Intent intent) {
        AccountAuthenticator authenticator = new AccountAuthenticator(this);
        return authenticator.getIBinder();
    }
}