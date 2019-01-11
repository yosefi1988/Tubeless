package ir.sajjadyosefi.android.tubeless.activity.radyab;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.zl.reik.dilatingdotsprogressbar.DilatingDotsProgressBar;
import java.util.regex.Pattern;

import ir.sajjadyosefi.android.tubeless.Global;
import ir.sajjadyosefi.android.tubeless.R;
import ir.sajjadyosefi.android.tubeless.activity.MainActivity;
import ir.sajjadyosefi.android.tubeless.classes.business.RadyabBusiness;
import ir.sajjadyosefi.android.tubeless.classes.utility.radyab.SMSUtils;


public class RegisterPhoneActivity extends AppCompatActivity   {

    Context context;

    EditText editTextMobilePhoneNumber ,editTextMobileRndNumber;
    Button buttonSend ,buttonCancel;
    DilatingDotsProgressBar dilatingDotsProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = this;
        setContentView(R.layout.activity_radyab_register_phone);

        dilatingDotsProgressBar = (DilatingDotsProgressBar) findViewById(R.id.PBSjd);
        editTextMobilePhoneNumber = (EditText) findViewById(R.id.etField1);
        editTextMobileRndNumber = (EditText) findViewById(R.id.etField2);
        buttonSend = (Button) findViewById(R.id.btn1);
        buttonCancel = (Button) findViewById(R.id.btn2);


        buttonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        buttonSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isValidMobile(context, editTextMobilePhoneNumber.getText().toString())){
//                    AsyncSendSms asyncSendSms = new AsyncSendSms(context,dilatingDotsProgressBar, RadyabBusiness.CreateSmsForSendToClient(editTextMobilePhoneNumber.getText().toString()));
//                    asyncSendSms.execute();

                    if(editTextMobileRndNumber.getText().toString().length() == 5) {
                        int permissionCheck = ContextCompat.checkSelfPermission(context, android.Manifest.permission.SEND_SMS);

                        if (permissionCheck != PackageManager.PERMISSION_GRANTED) {
                            ActivityCompat.requestPermissions(((Activity) context),
                                    new String[]{
                                            android.Manifest.permission.SEND_SMS
                                    },
                                    9001);
                        } else {
                            deviceRegister();
                        }
                    }else
                        Toast.makeText(context,context.getString(R.string.NotRndValid),Toast.LENGTH_SHORT).show();
                }
            }
        });


    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case 9001:
                if ((grantResults.length > 0) ){
                    if(grantResults[0] == PackageManager.PERMISSION_GRANTED){
                        deviceRegister();
                    }else {
                        //Toast.makeText(context,context.getString(R.string.WeNeedYourDeviceInfo),Toast.LENGTH_LONG).show();
                        Global.ShowMessageDialog(context,"",context.getString(R.string.WeNeedYourDeviceInfo));
                    }
                }else {
                    Toast.makeText(context,context.getString(R.string.ErrorOnGetPermision),Toast.LENGTH_LONG).show();
                }
                break;

            default:
                break;
        }
    }

    private void deviceRegister() {
        SMSUtils.sendSMS(
                context,
                RadyabBusiness.CreateSmsForSendToClient(editTextMobileRndNumber.getText().toString(),editTextMobilePhoneNumber.getText().toString()));
    }


    @Override
    protected void onStart() {
        super.onStart();
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.fadeout, R.anim.fadein);
        finish();
    }

    private boolean isValidMobile(Context context, String phone) {
        boolean check=false;
        if(!Pattern.matches("[a-zA-Z]+", phone)) {
            if(phone.length() != 11 || (!phone.substring(0,2).equals("09"))) {
                check = false;
                Toast.makeText(context,context.getString(R.string.NotMobileValid),Toast.LENGTH_SHORT).show();
            } else {
                check = true;
            }
        } else {
            check=false;
        }
        return check;
    }


}
