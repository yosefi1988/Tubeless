package ir.sajjadyosefi.android.xTubeless.activity.register;

import android.accounts.AccountManager;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.gson.JsonObject;
import com.readystatesoftware.systembartint.SystemBarTintManager;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import ir.sajjadyosefi.accountauthenticator.activity.AuthenticatorActivity;
import ir.sajjadyosefi.accountauthenticator.activity.SignInActivity;
import ir.sajjadyosefi.accountauthenticator.authentication.AccountGeneral;
import ir.sajjadyosefi.android.xTubeless.Adapter.EndlessList_AdapterFile;
import ir.sajjadyosefi.android.xTubeless.Global;
import ir.sajjadyosefi.android.xTubeless.R;
import ir.sajjadyosefi.android.xTubeless.activity.TubelessTransparentStatusBarActivity;
import ir.sajjadyosefi.android.xTubeless.activity.common.WebViewActivity;
import ir.sajjadyosefi.android.xTubeless.classes.model.File;
import ir.sajjadyosefi.android.xTubeless.classes.model.exception.TubelessException;
import ir.sajjadyosefi.android.xTubeless.classes.model.request.NewBlogCommentRequest;
import ir.sajjadyosefi.android.xTubeless.classes.model.request.NewBlogRequest;
import ir.sajjadyosefi.android.xTubeless.classes.model.response.ServerResponseBase;
import ir.sajjadyosefi.android.xTubeless.networkLayout.retrofit.DownloadUploadPicture.FileListActivity;
import ir.sajjadyosefi.android.xTubeless.networkLayout.retrofit.TubelessRetrofitCallbackss;
import ir.sajjadyosefi.android.xTubeless.service.FileUploadService;
import ir.sajjadyosefi.android.xTubeless.utility.file.UriUtil;
import it.sephiroth.android.library.bottomnavigation.BottomNavigation;
import retrofit2.Call;

import static ir.sajjadyosefi.android.xTubeless.Adapter.EndlessList_AdapterFile.lastCheckedPosition;
import static ir.sajjadyosefi.android.xTubeless.Adapter.EndlessList_AdapterFile.lastCheckedPosition2;
import static ir.sajjadyosefi.android.xTubeless.activity.register.RegNewYafteActivity.GO_TO_LOGIN;
import static ir.sajjadyosefi.android.xTubeless.classes.StaticValue.NOT_LOGN_USER;


public class RegNewCommentActivity extends TubelessTransparentStatusBarActivity {


    public Button buttonReg , buttonBack , buttonAddFiles;
    EditText editTextText;
    private int REQUEST_FILE_LIST = 525;

    static List<File> filesList;

    public static int LOGIN_REQUEST_NEW_COMMENT = 119 ;


    public synchronized static Intent getIntent(Context context) {
        return getIntent(context,new Bundle());
    }

    public synchronized static Intent getIntent(Context context, Bundle bundle) {
        bundle.putString("item1","value1");
        Intent intent = new Intent(context, RegNewCommentActivity.class);
        intent.putExtras(bundle);
        return intent;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        filesList = new ArrayList<File>();
        File headerInList = new File();
        headerInList.setListItemType(EndlessList_AdapterFile.ListItemType.TYPE_HEADER);
        filesList.add(headerInList);

        File Emptylist = new File();
        Emptylist.setListItemType(EndlessList_AdapterFile.ListItemType.TYPE_EMPTY_LIST);
        filesList.add(Emptylist);

        setContentView(R.layout.activity_new_comment);

        buttonReg = findViewById(R.id.buttonReg);
        buttonBack = findViewById(R.id.buttonBack);
        buttonAddFiles = findViewById(R.id.buttonAddFiles);
        editTextText = findViewById(R.id.editTextText);

//        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
//                .requestEmail()
//                .build();




//        final GoogleSignInClient mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

//        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);
//        updateUI(account);

// Set the dimensions of the sign-in button.
//        SignInButton signInButton = findViewById(R.id.sign_in_button);
//        signInButton.setSize(SignInButton.SIZE_STANDARD);
//
//        findViewById(R.id.sign_in_button).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent signInIntent = mGoogleSignInClient.getSignInIntent();
//                startActivityForResult(signInIntent, RC_SIGN_IN);
//            }
//        });


//
//        intent.putExtra(AccountManager.KEY_ACCOUNT_TYPE, accountType);
//        intent.putExtra(ADD_ACCOUNT, true);
//        intent.putExtra(AccountManager.KEY_ACCOUNT_AUTHENTICATOR_RESPONSE, response);
//                bundle.putParcelable(AccountManager.KEY_INTENT, intent);

        //getType
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        int blogId = bundle.getInt("blogId");


        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        buttonAddFiles.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Global.user.isAdmin()) {
                    Bundle bundle = new Bundle();
                    bundle.putInt("FILE_COUNT", 4);
                    bundle.putSerializable("LIST", (Serializable) filesList);
                    getActivity().startActivityForResult(FileListActivity.getIntent(getContext(), bundle),REQUEST_FILE_LIST);
                }else {
                    Toast.makeText(getContext(),getContext().getString(R.string.notEnugh),Toast.LENGTH_LONG).show();
                }
            }
        });
        buttonReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final BottomSheetDialog dialog = new BottomSheetDialog(getContext());

                if ( editTextText.getText().toString().length() < 5){
                    TubelessException.ShowSheetDialogMessage(getContext(), dialog, getContext().getString(R.string.values_not_correct), new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            dialog.dismiss();
                        }
                    });
                }else {
                    NewBlogCommentRequest aaaa = new NewBlogCommentRequest();


                    aaaa.setUserID((int)Global.user.getUserId());
//                    aaaa.setUserID(140430);
//                    aaaa.setBlogID(84511);
                    aaaa.setBlogID(blogId);

                    JsonObject oooo = new JsonObject();
//                    oooo.addProperty("title",       editTextTitle.getText().toString());
//                    oooo.addProperty("model",       editTextTitleStatment.getText().toString());
//                    oooo.addProperty("description", editTextTitlePicture.getText().toString());
//                    oooo.addProperty("mobile","00");
                    oooo.addProperty("text",        editTextText.getText().toString());
                    aaaa.setText(oooo.toString());
                    newComment(aaaa);
                }
            }
        });


//        if (Global.IDUser == NOT_LOGN_USER ){


        if (Global.user == null || Global.user.getUserId() == NOT_LOGN_USER ){
            Bundle bundlex = new Bundle();
            Intent intentx = SignInActivity.getIntent(getContext(),bundlex);
            intentx.putExtra(AuthenticatorActivity.ARG_ACCOUNT_TYPE, AccountGeneral.ACCOUNT_TYPE);
            intentx.putExtra(AuthenticatorActivity.ARG_AUTH_TYPE, AccountGeneral.AUTHTOKEN_TYPE_FULL_ACCESS);
            intentx.putExtra(AuthenticatorActivity.ARG_IS_ADDING_NEW_ACCOUNT, true);
            //intent.putExtra(AccountManager.KEY_ACCOUNT_AUTHENTICATOR_RESPONSE, response);
            bundlex.putParcelable(AccountManager.KEY_INTENT, intentx);
            getActivity().startActivityForResult(intentx, GO_TO_LOGIN);
        }




//        SendFileRequest ss = new SendFileRequest();
////            ss.setFileType("1");
//        ss.setFileType(((ir.sajjadyosefi.evaluation.model.business.File)files).getFileType() + "");
//        ss.setSenderType("1");
////            ss.setSerialRequestCode("9801000144");
//        ss.setSerialRequestCode(Global.CurrentTask.getSerialRequestCode());
//
//        ss.setUserName(systemUserName);
//        ss.setPassword(systemPassword);
//        ss.setAndroidId(androidId);
//        sendAvatar(ss,((File) files),(((File) files).getUri()));


//        try {
//            PackageInfo pInfo = getContext().getPackageManager().getPackageInfo(getContext().getPackageName(), 0);
//            String versionName = pInfo.versionName;
//            if (versionName.contains("4.5.0") || versionName.contains("1.5.0")|| versionName.contains("1.3.0")) {
//                buttonAddFiles.setEnabled(false);
//            } else {
                buttonAddFiles.setEnabled(true);
//            }
//        }catch (Exception ex){
//
//        }
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
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK){
            if (requestCode == REQUEST_FILE_LIST){
                filesList = (List<File>) data.getSerializableExtra("LIST1");
            }else {

            }
        }else {

        }

        if (requestCode == GO_TO_LOGIN){
            if (resultCode == Activity.RESULT_OK){

            }else {
                finish();
            }
        }

    }

    private void newComment(NewBlogCommentRequest blogCommentItem) {
        final BottomSheetDialog dialog = new BottomSheetDialog(getContext());

        TubelessRetrofitCallbackss ssssssss = new TubelessRetrofitCallbackss(getContext(), ServerResponseBase.class) {
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
                TubelessException.ShowSheetDialogMessage(getContext(), dialog, getContext().getString(R.string.tray_error_again), new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });

            }

            @Override
            public void t_retry(Call<Object> call) {

            }

            @Override
            public void t_onSuccess(Object response) {
                ServerResponseBase responseX = (ServerResponseBase)response;

                if (responseX.getTubelessException().getCode() > 0) {

                    //pic
//                    boolean havePic = true;
//                    if (filesList.size()>=3){
//                        havePic = true;
//                    }
//
//                    if(havePic){
//
//                        //ok
//                        Intent mIntent = new Intent(getContext(), FileUploadService.class);
//                        mIntent.putExtra("BlogId", responseX.getTubelessException().getMessage());
//
//                        if (lastCheckedPosition2 != -1){
//                            mIntent.putExtra("TitlePicture" , UriUtil.getPath(getContext(), Uri.parse(filesList.get(lastCheckedPosition2).getUri())));
//                        }
//
//                        if (lastCheckedPosition != -1){
//                            mIntent.putExtra("TextPicture" , UriUtil.getPath(getContext(), Uri.parse(filesList.get(lastCheckedPosition).getUri())));
//                        }
//
//                        int index = 0;
//                        for (File file : filesList) {
//                            if (file.getListItemType() == EndlessList_AdapterFile.ListItemType.TYPE_ITEM && !file.isHeaderPic() && !file.isContentPic() ) {
//                                index++;
//                                mIntent.putExtra("image" + index, UriUtil.getPath(getContext(), Uri.parse(file.getUri())));
//                            }
//                        }
//                        mIntent.putExtra("filesCount", index );
//                        FileUploadService.enqueueWork(getContext(), mIntent);
//
//
//                    }else {
                        TubelessException.ShowSheetDialogMessage(getContext(), dialog, getContext().getString(R.string.new_yafte_new_yafte_inserted), "ok", new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                finish();
                            }
                        });
//                    }
                }else {
                    TubelessException.ShowSheetDialogMessage(getContext(), dialog, getContext().getString(R.string.tray_again), new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            dialog.dismiss();
                        }
                    });
                }
            }
        };
        Global.apiManagerTubeless.registerNewComment(blogCommentItem ,ssssssss);


    }
}
