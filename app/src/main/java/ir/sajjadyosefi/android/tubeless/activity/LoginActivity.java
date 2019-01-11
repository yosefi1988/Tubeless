package ir.sajjadyosefi.android.tubeless.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.orm.SugarContext;
import com.zl.reik.dilatingdotsprogressbar.DilatingDotsProgressBar;
//import com.vansuita.gaussianblur.GaussianBlur;

import ir.adad.client.Adad;
//import ir.sajjadyosefi.android.tubeless.Class.ProgressGenerator;
import ir.sajjadyosefi.android.tubeless.asyncTask.account.AsyncChangePassword;
import ir.sajjadyosefi.android.tubeless.asyncTask.account.AsyncLogin;
import ir.sajjadyosefi.android.tubeless.asyncTask.account.AsyncRegister;
import ir.sajjadyosefi.android.tubeless.classes.business.Validator;
import ir.sajjadyosefi.android.tubeless.classes.model.User.Device;
import ir.sajjadyosefi.android.tubeless.classes.model.User.User;
import ir.sajjadyosefi.android.tubeless.Global;
import ir.sajjadyosefi.android.tubeless.R;

import static ir.sajjadyosefi.android.tubeless.classes.business.Navigator.redirectToMainActivity;


public class LoginActivity extends AppCompatActivity   {

    //Bundle String list
    public static final String Type = "TYPE";

    //Create New Activity List
    public static final int LOGIN                   = 1;
    public static final int REGISTER                = 2;
    public static final int FORGET_PASSWORD         = 3;
    public static final int CHANGE_PASSWORD         = 4;

    Context mContext;
    DilatingDotsProgressBar dilatingDotsProgressBar;

    LinearLayout linearLayoutLogin;
    LinearLayout linearLayoutRegister;
    LinearLayout linearLayoutForgetPassword;
    LinearLayout linearLayoutChangePassword;

    Validator validator = new Validator(mContext);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //init
        mContext = this;
        SugarContext.init(this);
        Adad.initialize(getApplicationContext());
        setContentView(R.layout._activity_login);

        linearLayoutLogin = (LinearLayout) findViewById(R.id.linearLayoutLogin);
        linearLayoutRegister = (LinearLayout) findViewById(R.id.linearLayoutRegister);
        linearLayoutForgetPassword = (LinearLayout) findViewById(R.id.linearLayoutForgetPassword);
        linearLayoutChangePassword = (LinearLayout) findViewById(R.id.linearLayoutChangePassword);

        //getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        //getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        //getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        dilatingDotsProgressBar = (DilatingDotsProgressBar) findViewById(R.id.PBSjd);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        switch (bundle.getInt(LoginActivity.Type)) {
            case LoginActivity.LOGIN:{

                linearLayoutLogin.setVisibility(View.VISIBLE);
                linearLayoutRegister.setVisibility(View.GONE);
                linearLayoutForgetPassword.setVisibility(View.GONE);
                linearLayoutChangePassword.setVisibility(View.GONE);

                final EditText editTextMobile = (EditText) findViewById(R.id.editText_login_mobile);
                final EditText editTextPassword = (EditText) findViewById(R.id.editText_login_password);
                Button buttonLogin = (Button) findViewById(R.id.button_login_login);
                Button buttonCancel = (Button) findViewById(R.id.button_login_cancel);

                buttonCancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        redirectToMainActivity(mContext);
                    }
                });

                buttonLogin.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (validator.isValidMobile(editTextMobile.getText().toString())){
                            User user = new User();
                            user.setLoginPhone(editTextMobile.getText().toString());
                            user.setLoginPassword(editTextPassword.getText().toString());

                            Device device = new Device();
                            Global.GetAndroidID(mContext, device);
                            AsyncLogin asyncLogin = new AsyncLogin(mContext,dilatingDotsProgressBar,user,device);
                            asyncLogin.execute();
                        }else {
                            Toast.makeText(mContext,mContext.getResources().getString(R.string.NotMobileValid),Toast.LENGTH_SHORT).show();
                        }

                    }
                });
                break;
            }
            case LoginActivity.REGISTER:{

                linearLayoutLogin.setVisibility(View.GONE);
                linearLayoutRegister.setVisibility(View.VISIBLE);
                linearLayoutForgetPassword.setVisibility(View.GONE);
                linearLayoutChangePassword.setVisibility(View.GONE);

                final EditText editTextMobile = (EditText) findViewById(R.id.editText_register_mobile);
                final EditText editTextPassword = (EditText) findViewById(R.id.editText_register_password);
                Button buttonRegister = (Button) findViewById(R.id.button_register_reg);
                Button buttonCancel = (Button) findViewById(R.id.button_register_cancel);

                buttonCancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        redirectToMainActivity(mContext);
                        //onBackPressed();
                    }
                });

                buttonRegister.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (validator.isValidMobile(editTextMobile.getText().toString())) {
                            User user = new User();
                            user.setLoginPhone(editTextMobile.getText().toString());
                            user.setLoginPassword(editTextPassword.getText().toString());

                            user.setDevice(new Device());
                            Global.GetAndroidID(mContext,user.getDevice());
                            user.getDevice().setAndroidVersion(Build.VERSION.RELEASE);      //new
                            user.getDevice().setAndroidAPI(Build.VERSION.SDK_INT);          //new
                            user.getDevice().setApplicationID(17);
                            user.getDevice().setVersionID(100);
                            AsyncRegister asyncRegister = new AsyncRegister(mContext,dilatingDotsProgressBar, user);
                            asyncRegister.execute();
                        }
                    }
                });
                break;
            }
            case LoginActivity.FORGET_PASSWORD:{
                linearLayoutLogin.setVisibility(View.GONE);
                linearLayoutRegister.setVisibility(View.GONE);
                linearLayoutForgetPassword.setVisibility(View.VISIBLE);
                linearLayoutChangePassword.setVisibility(View.GONE);

                break;
            }
            case LoginActivity.CHANGE_PASSWORD:{
                linearLayoutLogin.setVisibility(View.GONE);
                linearLayoutRegister.setVisibility(View.GONE);
                linearLayoutForgetPassword.setVisibility(View.GONE);
                linearLayoutChangePassword.setVisibility(View.VISIBLE);


                final EditText editTextPassword = (EditText) findViewById(R.id.editText_changePassword_password);
                final EditText editTextRePassword = (EditText) findViewById(R.id.editText_changePassword_repassword);
                Button buttonRegister = (Button) findViewById(R.id.button_changePassword_reg);
                Button buttonCancel = (Button) findViewById(R.id.button_changePassword_cancel);

                buttonCancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        redirectToMainActivity(mContext);
                        //onBackPressed();
                    }
                });

                buttonRegister.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(editTextPassword.getText().toString().equals(editTextRePassword.getText().toString())){
                            User user = new User();
                            user.setMobileNumber(Global.mUser.getMobileNumber());
                            user.setPassword(editTextPassword.getText().toString());
                            user.setLoginPassword(editTextRePassword.getText().toString());
                            AsyncChangePassword asyncChangePassword = new AsyncChangePassword(mContext,dilatingDotsProgressBar,user);
                            asyncChangePassword.execute();
                        }else
                            Global.ShowMessageDialog(mContext,"", mContext.getString(R.string.notMatchPassword));
                    }
                });
                break;
            }
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        redirectToMainActivity(mContext);
    }
}
