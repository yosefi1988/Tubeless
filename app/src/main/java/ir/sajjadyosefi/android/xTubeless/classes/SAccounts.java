package ir.sajjadyosefi.android.xTubeless.classes;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.content.Context;
import android.os.Bundle;

import static ir.sajjadyosefi.android.xTubeless.classes.StaticValue.NOT_LOGN_USER;


public class SAccounts {

    private final Context context;
    //var
    private AccountManager mAccountManager;

    //val
    public final static String SAMANIUM_AUTH_TYPE = "AUTH_TYPE";
    public static final String AUTHTOKEN_TYPE_NORMAL_ACCESS = "Normal access";
    public static final String AUTHTOKEN_TYPE_ADMIN_ACCESS = "Admin access";
    public static final String ACCOUNT_TYPE = "ir.sajjadyosefi.tubeless";
    public final static String SAMANIUM_ACCOUNT_TYPE = "ACCOUNT_TYPE";
    public final static String SAMANIUM_ACCOUNT_NAME = "ACCOUNT_NAME";

    public SAccounts(Context context) {
        this.context = context;
        mAccountManager = AccountManager.get(context);
    }

    private AccountManager getAccountManager() {
        return mAccountManager;
    }

    public int performAccount(String name , int UserID) {
        SAccounts sAccounts = new SAccounts(context);
        if (!sAccounts.hasUserAccount()){
            sAccounts.createAccount(name,UserID);
            return UserID;
        }else {
            int userid = sAccounts.getUserAccountID();
            return userid;
        }
    }


    public Account getUserAccount() {
        Account availableAccounts[] = mAccountManager.getAccountsByType(ACCOUNT_TYPE);
        if (availableAccounts.length != 0)
            return availableAccounts[0];
        else
            return null;
    }

    public int getUserAccountID() {
        if (hasUserAccount()) {
            return Integer.parseInt(mAccountManager.getUserData(getUserAccount(), "UserID"));
        }else {
            return NOT_LOGN_USER;
        }
    }

    public boolean hasUserAccount() {
        if (getUserAccount() == null) {
            return false;
        } else {
            return true;
        }
    }

    public boolean removeAccount(Account account) {
        try {
            mAccountManager.removeAccount(account, null, null);
            return true;
        }catch (Exception ex){
            return false;
        }
    }

    private void createAccount(String name,int UserID) {
        final Account account = new Account(name, ACCOUNT_TYPE) ;
        Bundle data = new Bundle();
        data.putString("UserID", String.valueOf(UserID));
        this.getAccountManager().addAccountExplicitly(account, "tubeless",data);

    }
}
