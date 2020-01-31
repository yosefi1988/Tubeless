package ir.sajjadyosefi.android.xTubeless.activity.account;

import android.accounts.Account;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import org.litepal.LitePal;

import ir.sajjadyosefi.android.tubeless.Global;
import ir.sajjadyosefi.android.tubeless.R;
import ir.sajjadyosefi.android.xTubeless.activity.TubelessActivity;
import ir.sajjadyosefi.android.xTubeless.classes.SAccounts;
import ir.sajjadyosefi.android.xTubeless.classes.model.user.User;


public class ProfileActivity extends TubelessActivity {


    EditText ueditTextNameUserId , editTextName , editTextEmail;
    Button buttonBack , buttonSignOut;



    public synchronized static Intent getIntent(Context context) {
        return getIntent(context,new Bundle());
    }

    public synchronized static Intent getIntent(Context context, Bundle bundle) {
        bundle.putString("item1","value1");
        Intent intent = new Intent(context,ProfileActivity.class);
        intent.putExtras(bundle);
        return intent;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        ueditTextNameUserId = findViewById(R.id.ueditTextNameUserId);
        editTextName = findViewById(R.id.editTextName);
        editTextEmail = findViewById(R.id.editTextEmail);
        buttonSignOut = findViewById(R.id.buttonSignOut);
        buttonBack = findViewById(R.id.buttonBack);

        ueditTextNameUserId.setText(Global.user.getUserId() == 0 ? "" : Global.user.getUserId() + "");
        editTextName.setText(Global.user.getUserName() == null ? "" : Global.user.getUserName());
        editTextEmail.setText(Global.user.getEmail() == null ? "" : Global.user.getEmail());

        buttonSignOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SAccounts sAccounts = new SAccounts(getContext());
                Account user = sAccounts.getUserAccount();
                if (sAccounts.removeAccount(user)){
                    //db
                    LitePal.deleteAll(User.class, "userId = ?", String.valueOf(Global.IDUser));

                    Global.user = null;
                    Global.IDUser = 0;
                }
                Intent intent = new Intent();
                setResult(Activity.RESULT_OK, intent);
                finish();
            }
        });

        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

//    private void accounts() {
//        SAccounts sAccounts = new SAccounts(getContext());
////        sAccounts.getAccountManager().addAccount()
//
//        final Account account = new Account("accountName", "ir.sajjadyosefi.tubeless") ;
//        sAccounts.getAccountManager().addAccountExplicitly(account, "accountPassword",null);
//
//        AccountAuthenticator accountAuthenticator = new AccountAuthenticator(getContext());
////        accountAuthenticator.addAccount();
////        accountAuthenticator.
//    }


}
