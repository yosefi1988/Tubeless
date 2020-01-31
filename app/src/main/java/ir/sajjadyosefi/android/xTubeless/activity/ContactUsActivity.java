package ir.sajjadyosefi.android.xTubeless.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.ScrollView;
import android.widget.TextView;

import com.orm.SugarContext;
//import com.vansuita.gaussianblur.GaussianBlur;

import ir.adad.client.Adad;
import ir.sajjadyosefi.android.tubeless.asyncTask.account.AsyncSendMessage;
import ir.sajjadyosefi.android.xTubeless.classes.modelY.Message;
import ir.sajjadyosefi.android.xTubeless.classes.model.user.User;
import ir.sajjadyosefi.android.tubeless.Global;
import ir.sajjadyosefi.android.tubeless.R;
import ir.sajjadyosefi.android.tubeless.view.widget.view.PersianTextView;


public class ContactUsActivity extends AppCompatActivity {

    //Bundle String list
    public static final String Type = "TYPE";

    //Create New Activity List
    public static final int CONTACT_US              = 5;
    public static final int ORDER_APP               = 6;
    public static final int SUGGESTION              = 7;
    public static int messageType = 0;


    Context context;
    PersianTextView tvRadio1,tvRadio2,tvRadio3,tvField1,tvField2;
    TextView tvTitle;
    RadioButton radioButton1,radioButton2,radioButton3;
    TextView tvTitleText02;
    EditText etField1;
    EditText etField2;
    EditText etField3 ;
    Button btn1;
    Button btn2;
    ScrollView svScroll;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //init
        context = this;
        SugarContext.init(this);
        Adad.initialize(getApplicationContext());

        //getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        //getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        //getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout._activity_contact_us);

        tvTitleText02 = (TextView) findViewById(R.id.tvTitleText02);
        etField1 = (EditText) findViewById(R.id.etField1);
        etField2 = (EditText) findViewById(R.id.etField2);
        etField3 = (EditText) findViewById(R.id.etField3);
        btn1 = (Button) findViewById(R.id.btn1);
        btn2 = (Button) findViewById(R.id.btn2);
        radioButton1 = (RadioButton) findViewById(R.id.radioButton1);
        radioButton2 = (RadioButton) findViewById(R.id.radioButton2);
        radioButton3 = (RadioButton) findViewById(R.id.radioButton3);
        tvRadio1 = (PersianTextView) findViewById(R.id.tvRadio1);
        tvRadio2 = (PersianTextView) findViewById(R.id.tvRadio2);
        tvRadio3 = (PersianTextView) findViewById(R.id.tvRadio3);
        tvField1 = (PersianTextView) findViewById(R.id.tvField1);
        tvField2 = (PersianTextView) findViewById(R.id.tvField2);
        tvTitle = (TextView) findViewById(R.id.tvTitle);
        svScroll = (ScrollView) findViewById(R.id.svScroll);

        //hide keyboard
        View.OnClickListener imageviewClickListener =  new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //hide keyboard
                InputMethodManager imm = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(tvTitle.getWindowToken(), 0);
            }
        };
        tvTitleText02.setOnClickListener(imageviewClickListener);
        tvRadio1.setOnClickListener(imageviewClickListener);
        tvRadio2.setOnClickListener(imageviewClickListener);
        tvRadio3.setOnClickListener(imageviewClickListener);
        tvField1.setOnClickListener(imageviewClickListener);
        tvField2.setOnClickListener(imageviewClickListener);
        tvTitle.setOnClickListener(imageviewClickListener);

        if(Global.user != null){
            etField1.setText(Global.user.getMobileNumber());
            etField1.setEnabled(false);
        }

        btn1.setText(context.getString(R.string.send));
        btn2.setText(context.getString(R.string.cancel));
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if((etField1.getText().toString().length()>10 ||etField1.getText().toString().length()>10 ) && etField3.getText().toString().length()>10 ){
                    Message message = new Message();
                    message.setApplicationID(17);
                    message.setPhoneNumber(etField1.getText().toString());
                    message.setText(etField3.getText().toString());
                    switch (messageType) {
                        case ContactUsActivity.CONTACT_US:{
                            message.setTitle("تماس با ما");
                            break;
                        }
                        case ContactUsActivity.ORDER_APP:{
                            message.setTitle("سفارش نرم افزار");
                            break;
                        }
                        case ContactUsActivity.SUGGESTION:{
                            message.setTitle("پیشنهادات و انتقادات");
                            break;
                        }
                    }
                    message.setType(messageType + "");
                    AsyncSendMessage asyncSendMessage = new AsyncSendMessage(context,message);
                    asyncSendMessage.execute();
                }else
                    Global.ShowMessageDialog(context,"",context.getString(R.string.notCorrectInput));


            }
        });

        //getType
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        switch (bundle.getInt(ContactUsActivity.Type)) {
            case ContactUsActivity.CONTACT_US:{
                messageType = ContactUsActivity.CONTACT_US;
                break;
            }
            case ContactUsActivity.ORDER_APP:{
                messageType = ContactUsActivity.ORDER_APP;
                break;
            }
            case ContactUsActivity.SUGGESTION:{
                messageType = ContactUsActivity.SUGGESTION;
                break;
            }
        }

        //get Checked Radio
        ((RadioGroup)findViewById(R.id.rgRadios)).setOnCheckedChangeListener(
                new RadioGroup.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(RadioGroup group, int checkedId) {
                        switch (checkedId) {
                            case R.id.radioButton1:
                                radioButton1.setChecked(true);
                                radioButton2.setChecked(false);
                                radioButton3.setChecked(false);
                                tvTitleText02.setText(context.getResources().getString(R.string.radio1Text));
                                messageType = ContactUsActivity.CONTACT_US;
                                break;
                            case R.id.radioButton2:
                                radioButton1.setChecked(false);
                                radioButton2.setChecked(true);
                                radioButton3.setChecked(false);
                                tvTitleText02.setText(context.getResources().getString(R.string.radio2Text));
                                messageType = ContactUsActivity.ORDER_APP;
                                break;
                            case R.id.radioButton3:
                                radioButton1.setChecked(false);
                                radioButton2.setChecked(false);
                                radioButton3.setChecked(true);
                                tvTitleText02.setText(context.getResources().getString(R.string.radio3Text));
                                messageType = ContactUsActivity.SUGGESTION;
                                break;
                        }
                    }
                }
        );

    }

    @Override
    protected void onStart() {
        super.onStart();
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        radioButton2.performClick();
        radioButton1.performClick();

        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                svScroll.fullScroll(ScrollView.FOCUS_UP);
            }
        },200);
    }



    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Global.user = new User();
//        Intent intent = new Intent(this,xMainActivity.class);
//        startActivity(intent);

        finish();
        overridePendingTransition(R.anim.fadeout, R.anim.fadein);
    }
}
