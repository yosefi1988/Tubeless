package ir.sajjadyosefi.android.xTubeless.activity.account;

import android.accounts.Account;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.readystatesoftware.systembartint.SystemBarTintManager;

import org.litepal.LitePal;

import ir.sajjadyosefi.android.xTubeless.R;
import ir.sajjadyosefi.android.xTubeless.Global;
import ir.sajjadyosefi.android.xTubeless.activity.activities.TubelessTransparentStatusBarActivity;
import ir.sajjadyosefi.android.xTubeless.classes.SAccounts;
import ir.sajjadyosefi.android.xTubeless.classes.model.user.User;
import it.sephiroth.android.library.bottomnavigation.BottomNavigation;


public class ProfileActivity extends TubelessTransparentStatusBarActivity {


    EditText ueditTextNameUserId , editTextName , editTextEmail;
    Button buttonBack , buttonSignOut;
    ImageView imageViewUserAvatar;



    public synchronized static Intent getIntent(Context context) {
        return getIntent(context,new Bundle());
    }

    public synchronized static Intent getIntent(Context context, Bundle bundle) {
        bundle.putString("item1","value1");
        bundle.putString("item2","value1");
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
        imageViewUserAvatar = findViewById(R.id.imageViewUserAvatar);


        imageViewUserAvatar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //https://androidwave.com/android-upload-file-to-server-with-progress/


            }
        });

        if (Global.user == null){
            finish();
        }

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
//                    int aaa = LitePal.deleteAll(User.class, "userId = ?", String.valueOf(Global.IDUser));
                    int bbbb = LitePal.deleteAll(User.class);
//                    List<User> dbUser = LitePal.where("userId like ?", String.valueOf(Global.IDUser) + "").find(User.class);
//                    List<User> dbUser2 = LitePal.findAll(User.class);

                    Global.user = null;
                }


                setResult(Activity.RESULT_OK, getIntent());
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

    @Override
    public SystemBarTintManager getSystemBarTint() {
        return null;
    }

    @Override
    public boolean hasTranslucentNavigation() {
        return false;
    }

    @Override
    public boolean hasTranslucentStatusBar() {
        return false;
    }

    @Override
    public BottomNavigation getBottomNavigation() {
        return null;
    }

    @Override
    public int getNavigationBarHeight() {
        return 0;
    }

    @Override
    public boolean hasManagedToolbarScroll() {
        return false;
    }

    @Override
    public boolean hasAppBarLayout() {
        return false;
    }

    @Override
    public Toolbar getToolbar() {
        return null;
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


    @Override
    protected void onStart() {
        super.onStart();
        AdView adView = findViewById(R.id.adView);


        if (Global.user.isAdmin()) {
            adView.setVisibility(View.VISIBLE);
//        MobileAds.initialize(this, new OnInitializationCompleteListener() {
//            @Override
//            public void onInitializationComplete(InitializationStatus initializationStatus) {

//            }
//        });



            AdRequest adRequest = new AdRequest.Builder().build();

//        adView.setAdSize(AdSize.BANNER);
//        adView.setAdUnitId("ca-app-pub-3940256099942544/6300978111");     //test google
            adView.loadAd(adRequest);

            adView.setAdListener(new AdListener() {
                @Override
                public void onAdLoaded() {
                    // Code to be executed when an ad finishes loading.
                    Toast.makeText(getContext(), "onAdLoaded", Toast.LENGTH_LONG).show();
                }

                @Override
                public void onAdFailedToLoad(int errorCode) {
                    // Code to be executed when an ad request fails.
                    Toast.makeText(getContext(), errorCode + "", Toast.LENGTH_LONG).show();

                }

                @Override
                public void onAdOpened() {
                    // Code to be executed when an ad opens an overlay that
                    // covers the screen.
                    Toast.makeText(getContext(), "onAdOpened", Toast.LENGTH_LONG).show();

                }

                @Override
                public void onAdClicked() {
                    // Code to be executed when the user clicks on an ad.
                    Toast.makeText(getContext(), "onAdClicked", Toast.LENGTH_LONG).show();

                }

                @Override
                public void onAdLeftApplication() {
                    // Code to be executed when the user has left the app.
                    Toast.makeText(getContext(), "onAdLeftApplication", Toast.LENGTH_LONG).show();

                }

                @Override
                public void onAdClosed() {
                    // Code to be executed when the user is about to return
                    // to the app after tapping on an ad.
                    Toast.makeText(getContext(), "onAdClosed", Toast.LENGTH_LONG).show();

                }
            });
        }else {
            adView.setVisibility(View.GONE);
        }
    }
}
