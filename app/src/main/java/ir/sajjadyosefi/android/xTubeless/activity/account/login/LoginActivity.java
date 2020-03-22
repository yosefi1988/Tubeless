package ir.sajjadyosefi.android.xTubeless.activity.account.login;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;
import com.readystatesoftware.systembartint.SystemBarTintManager;

import ir.sajjadyosefi.android.xTubeless.R;
import ir.sajjadyosefi.android.xTubeless.activity.TubelessActivity;
import ir.sajjadyosefi.android.xTubeless.activity.account.login.model.IUser;
import ir.sajjadyosefi.android.xTubeless.activity.account.login.presenter.LoginPresenterComplI;
import ir.sajjadyosefi.android.xTubeless.activity.account.login.view.ILoginView;
import ir.sajjadyosefi.android.xTubeless.classes.SAccounts;
import ir.sajjadyosefi.android.xTubeless.classes.model.user.User;
import ir.sajjadyosefi.android.xTubeless.classes.model.exception.TubelessException;
import ir.sajjadyosefi.android.xTubeless.utility.xUtility.AndroidHardware;
import ir.sajjadyosefi.android.xTubeless.utility.xUtility.AndroidOs;
import it.sephiroth.android.library.bottomnavigation.BottomNavigation;

public class LoginActivity extends TubelessActivity implements ILoginView {
    LoginPresenterComplI loginPresenterCompl;

    //val
    private static final String     TAG                         = "sssssssssssssss";
    private int                     RC_SIGN_IN                  = 1000;
    private String                  wantPermission              = Manifest.permission.READ_PHONE_STATE;
    private static final int        PERMISSION_REQUEST_CODE     = 1;


    //widget
    Button submitButton, submitBySimCard;
    SignInButton submitByGoogle;
    EditText editTextPass, editTextPhone;



    //simcard
    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case PERMISSION_REQUEST_CODE:
                if (AndroidOs.isPermissionGranted(grantResults)) {
                    loginPresenterCompl.tryToLoginBySimCard(AndroidHardware.getPhoneNumber(getContext()));
                } else {
                    Toast.makeText(getActivity(),getContext().getString(R.string.simcardPermissionError), Toast.LENGTH_LONG).show();
                }
                break;
        }
    }

    //Google
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInClient.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {

            try {
                Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
                GoogleSignInAccount account = task.getResult(ApiException.class);
                //UserName = account.getEmail();
                loginPresenterCompl.tryToLoginByMail(account.getEmail(), account.getPhotoUrl() == null ? null : account.getPhotoUrl().toString());

            }catch (Exception ex){

            }
        }
    }



    //singletone instance
    private static LoginActivity loginActivity;

    //singletone
    public synchronized static LoginActivity getInstance(){
        if (loginActivity == null){
            loginActivity = new LoginActivity();
        }
        return loginActivity;
    }

    //default constractor
    public LoginActivity() { }


    public synchronized static Intent getIntent(Context context) {
        return getIntent(context,null);
    }

    public synchronized static Intent getIntent(Context context, Bundle bundle) {
        bundle.putString("item1","value1");
        Intent intent = new Intent(context,LoginActivity.class);
        intent.putExtras(bundle);
        return intent;
    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loginPresenterCompl = new LoginPresenterComplI(this,this);
        setContentView(R.layout.activity_login);
        setRootActivity();

        submitButton        = findViewById(R.id.submit);
        submitBySimCard     = findViewById(R.id.submitBySimCard);
        submitByGoogle      = findViewById(R.id.submitByGoogle);
        editTextPass        = findViewById(R.id.editTextPass);
        editTextPhone       = findViewById(R.id.editTextPhone);


        //phone number
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loginPresenterCompl.tryToLoginByPhoneNumber(editTextPhone.getText().toString(),editTextPass.getText().toString());

            }
        });

        //simcard
        submitBySimCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!AndroidOs.checkPermission(getContext(), wantPermission)) {
                    if (AndroidOs.shouldShowRequestPermissionRationale(getActivity(), wantPermission)){
                        Toast.makeText(getActivity(), getContext().getString(R.string.loginBySimcardDescription), Toast.LENGTH_LONG).show();
                    }
                    AndroidOs.requestPermissions(getActivity(), new String[]{wantPermission},PERMISSION_REQUEST_CODE);

                } else {
                    loginPresenterCompl.tryToLoginBySimCard(AndroidHardware.getPhoneNumber(getContext()));
                }
            }
        });

        //google
        submitByGoogle.setSize(SignInButton.SIZE_STANDARD);
        GoogleSignInOptions gso = new GoogleSignInOptions
                .Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        final GoogleSignInClient mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

        submitByGoogle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent signInIntent = mGoogleSignInClient.getSignInIntent();
                startActivityForResult(signInIntent, RC_SIGN_IN);
            }
        });

        ((TextView) findViewById(R.id.textViewForgetpass)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loginPresenterCompl.goToForgetPassword();
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

    protected void retData(Intent returnIntent){
        setResult(Activity.RESULT_OK, returnIntent);
        finish();
    }

    @Override
    public void onLoginSuccessFinish(IUser xxxxxxxx) {
        User tmpUser = (User) xxxxxxxx;
        try {
            SAccounts sAccounts = new SAccounts(getContext());

            if (tmpUser.getPassword() != null && tmpUser.getPassword().length() > 1){
                sAccounts.performAccount(tmpUser.getUserName(), (int) tmpUser.getUserId(),tmpUser.getPassword());
            }else {
                sAccounts.performAccount(tmpUser.getUserName(), (int) tmpUser.getUserId());
            }

            retData(new Intent());
        }catch (Exception ex){
            onThrowException(ex);
        }

    }

    @Override
    public void onThrowException(Throwable t) {
        TubelessException sException = new TubelessException();
        sException.handleServerMessage(getContext(),t);
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        retCancel();
    }




    @Override
    public void showProgressBar() {
        progressDialog.show();
    }

    @Override
    public void hideProgressBar() {
        progressDialog.hide();
    }

    @Override
    public void OnPressGoToForgetPassword() {
        Toast.makeText(getContext(),"ddddddd",Toast.LENGTH_LONG).show();
    }


//    @Override
//    public void onClearText() {
//        editUser.setText("");
//        editPass.setText("");
//    }

//    @Override
//    public void onLoginSuccessFinish(Boolean result, int code) {
//        loginPresenter.setProgressBarVisiblity(View.INVISIBLE);
//        btnLogin.setEnabled(true);
//        btnClear.setEnabled(true);
//        if (result){
//            Toast.makeText(this,"Login Success",Toast.LENGTH_SHORT).show();
//        }
//        else
//            Toast.makeText(this,"Login Fail, code = " + code,Toast.LENGTH_SHORT).show();
//    }


//    @Override
//    public void onSetProgressBarVisibility(int visibility) {
//        progressBar.setVisibility(visibility);
//    }
}
