package ir.sajjadyosefi.android.xTubeless.activity.account;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;
import com.google.android.material.bottomsheet.BottomSheetDialog;


import ir.sajjadyosefi.android.tubeless.Global;
import ir.sajjadyosefi.android.tubeless.R;

import ir.sajjadyosefi.android.xTubeless.classes.model.user.User;
import ir.sajjadyosefi.android.xTubeless.activity.TubelessActivity;
import ir.sajjadyosefi.android.xTubeless.classes.SAccounts;
import ir.sajjadyosefi.android.xTubeless.classes.modelY.Exception.TubelessException;
import ir.sajjadyosefi.android.xTubeless.classes.model.network.request.accounting.LoginRequest;
import ir.sajjadyosefi.android.xTubeless.classes.model.network.responses.LoginResponse;
import ir.sajjadyosefi.android.xTubeless.networkLayout.retrofit.TubelessRetrofitCallbackss;
import ir.sajjadyosefi.android.xTubeless.utility.DeviceUtil;

import ir.sajjadyosefi.android.xTubeless.utility.xUtility.AndroidHardware;
import ir.sajjadyosefi.android.xTubeless.utility.xUtility.AndroidOs;
import ir.sajjadyosefi.android.xTubeless.utility.xUtility.Validation;
import retrofit2.Call;

public class LoginActivity extends TubelessActivity {

    //val
    private static final String     TAG                         = "sssssssssssssss";
    private int                     RC_SIGN_IN                  = 1000;
    private String                  wantPermission              = Manifest.permission.READ_PHONE_STATE;
    private static final int        PERMISSION_REQUEST_CODE     = 1;

    //var
    String UserName;

    //widget
    Button submitButton, submitBySimCard;
    SignInButton submitByGoogle;
    EditText editTextPass, editTextPhone;


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


    //sim card
    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case PERMISSION_REQUEST_CODE:
                if (AndroidOs.isPermissionGranted(grantResults)) {
                    LoginRequest loginRequest = new LoginRequest(AndroidHardware.getPhoneNumber(getContext()));
                    UserName = AndroidHardware.getPhoneNumber(getContext());
                    loginRequest.setAndroidID(DeviceUtil.GetAndroidId(getContext()));
                    LoginOrRegister(loginRequest, submitBySimCard);
                } else {
                    Toast.makeText(getActivity(),getContext().getString(R.string.simcardPermissionError), Toast.LENGTH_LONG).show();
                }
                break;
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_login);
        setRootActivity();



        submitButton        = findViewById(R.id.submit);
        submitBySimCard     = findViewById(R.id.submitBySimCard);
        submitByGoogle      = findViewById(R.id.submitByGoogle);
        editTextPass        = findViewById(R.id.editTextPass);
        editTextPhone       = findViewById(R.id.editTextPhone);


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
                    LoginRequest loginRequest = new LoginRequest(AndroidHardware.getPhoneNumber(getContext()));
                    loginRequest.setAndroidID(DeviceUtil.GetAndroidId(getContext()));
                    UserName = AndroidHardware.getPhoneNumber(getContext());
                    LoginOrRegister(loginRequest, submitBySimCard);
                }
            }
        });


        //phone Number
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                LoginRequest loginRequest = new LoginRequest("09123678522");
                final BottomSheetDialog dialog = new BottomSheetDialog(getContext());
                if (Validation.validatePhoneNumber(editTextPhone.getText().toString())){
                    if (editTextPass.getText().toString().length() < 5) {
                        TubelessException.ShowSheetDialogMessage(getContext(), dialog, getContext().getString(R.string.passwordError), new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                dialog.dismiss();
                            }
                        });
                    }else {
                        LoginRequest loginRequest = new LoginRequest(editTextPhone.getText().toString(), editTextPass.getText().toString(), DeviceUtil.GetAndroidId(getContext()));
                        UserName = editTextPhone.getText().toString();
                        LoginOrRegister(loginRequest,submitButton);
                    }
                }else {
                    TubelessException.ShowSheetDialogMessage(getContext(), dialog, getContext().getString(R.string.phoneError), new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            dialog.dismiss();
                        }
                    });
                }

            }
        });


        ((TextView) findViewById(R.id.textViewForgetpass)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent intent = new Intent(getContext(), LoginActivity.class);
//                getActivity().startActivity(intent);
//                getActivity().finish();


                Toast.makeText(getContext(),"ddddddd",Toast.LENGTH_LONG).show();
                //((TubelessActivity) getContext()).progressDialog.show();
            }
        });


        GoogleSignInOptions gso = new GoogleSignInOptions
                .Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();


        final GoogleSignInClient mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

//        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);
//        updateUI(account);

        submitByGoogle.setSize(SignInButton.SIZE_STANDARD);
        submitByGoogle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent signInIntent = mGoogleSignInClient.getSignInIntent();
                startActivityForResult(signInIntent, RC_SIGN_IN);
            }
        });
//        PackageManager p = getPackageManager();
//        p.setComponentEnabledSetting(getComponentName(), PackageManager.COMPONENT_ENABLED_STATE_DISABLED, PackageManager.DONT_KILL_APP);
    }


    public static void LoginOrRegisterSilent(Context context , LoginRequest loginRequest) {
//        Global.apiManagerTubeless.loginOrRregister(loginRequest,new TubelessRetrofitCallback<Object>(context,null, false, null, new Callback<Object>() {
//            @Override
//            public void onResponse(Call<Object> call, Response<Object> response) {
//                Gson gson = new Gson();
//                JsonElement jsonElement = gson.toJsonTree(response.body());
//                LoginResponse responseX = gson.fromJson(jsonElement.getAsString(), LoginResponse.class);
//
//
////                Global.user = responseX;
//
//                //save to db
//                UserDb newUserDb = new UserDb();
//                newUserDb = Global.user.getDbUser();
//                newUserDb.save();
//
//
//            }
//
//            @Override
//            public void onFailure(Call<Object> call, Throwable t) {
//
//            }
//        }));
    }

    private void LoginOrRegister(LoginRequest loginRequest, View submitButton) {

        TubelessRetrofitCallbackss ssssssss = new TubelessRetrofitCallbackss(getContext(), LoginResponse.class) {
            @Override
            public void t_beforeSendRequest() {

            }

            @Override
            public void t_afterGetResponse() {

            }

            @Override
            public void t_complite() {

            }


            @Override
            public void t_responseNull() {

            }


            @Override
            public void t_retry(Call<Object> call) {

            }


            @Override
            public void t_onSuccess(Object response) {
                Global.user = (User) response;

                SAccounts sAccounts = new SAccounts(getContext());
                sAccounts.performAccount(UserName,(int) Global.user.getUserId());

                //save to db
                if (Global.user == null){
                    if ((new User(Global.user)).save()){
                        Toast.makeText(getContext(),getContext().getString(R.string.welcome) ,Toast.LENGTH_LONG).show();
                        retData(new Intent());
                    }else {
                        Toast.makeText(getContext(),"613458872",Toast.LENGTH_LONG).show();
                    }
                }else {
                    Toast.makeText(getContext(),getContext().getString(R.string.welcome) ,Toast.LENGTH_LONG).show();
                    retData(new Intent());
                }

            }
        };
        Global.apiManagerTubeless.loginOrRregister(loginRequest, ssssssss);
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInClient.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            // The Task returned from this call is always completed, no need to attach
            // a listener.
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            handleGoogleSignInResult(task);
        }
    }
    private void handleGoogleSignInResult(Task<GoogleSignInAccount> completedTask) {
        try {
            GoogleSignInAccount account = completedTask.getResult(ApiException.class);

//            Toast.makeText(getContext(),"Id:" +account.getId(),Toast.LENGTH_LONG).show();
//            Toast.makeText(getContext(),"DisplayName:" + account.getDisplayName(),Toast.LENGTH_LONG).show();
//            Toast.makeText(getContext(),account.getEmail(),Toast.LENGTH_LONG).show();
//            Toast.makeText(getContext(),account.getFamilyName(),Toast.LENGTH_LONG).show();
//            Toast.makeText(getContext(),account.getGivenName(),Toast.LENGTH_LONG).show();
//            Toast.makeText(getContext(),account.getAccount().name,Toast.LENGTH_LONG).show();
//            Toast.makeText(getContext(),account.getAccount().type,Toast.LENGTH_LONG).show();
//            if (account.getPhotoUrl() != null)
//                Toast.makeText(getContext(),"hotoUrl" + account.getPhotoUrl().toString(),Toast.LENGTH_LONG).show();

//            accounts(account.getEmail());
//            int a = 5 ;
//            a++;
            // Signed in successfully, show authenticated UI.
//            updateUI(account);

            LoginRequest loginRequest = new LoginRequest(account.getEmail(),(account.getPhotoUrl()== null ? "" : account.getPhotoUrl().toString()));
            loginRequest.setAndroidID(DeviceUtil.GetAndroidId(getContext()));
            UserName = account.getEmail();
            LoginOrRegister(loginRequest,submitByGoogle);
        } catch (ApiException e) {
            // The ApiException status code indicates the detailed failure reason.
            // Please refer to the GoogleSignInStatusCodes class reference for more information.
            Log.w(TAG, "signInResult:failed code=" + e.getStatusCode());
//            updateUI(null);
        }
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        retCancel();
    }

}
