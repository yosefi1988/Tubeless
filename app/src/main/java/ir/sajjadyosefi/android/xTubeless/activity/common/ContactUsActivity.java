package ir.sajjadyosefi.android.xTubeless.activity.common;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

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
import com.readystatesoftware.systembartint.SystemBarTintManager;
//import com.vansuita.gaussianblur.GaussianBlur;

import ir.adad.client.Adad;
import ir.sajjadyosefi.android.xTubeless.Global;
import ir.sajjadyosefi.android.xTubeless.activity.TubelessTransparentStatusBarActivity;
import ir.sajjadyosefi.android.xTubeless.classes.model.user.User;
import ir.sajjadyosefi.android.xTubeless.R;
import it.sephiroth.android.library.bottomnavigation.BottomNavigation;


public class ContactUsActivity extends TubelessTransparentStatusBarActivity {

    //Bundle String list
    public static final String Type = "TYPE";

    //Create New Activity List
    public static final int CONTACT_US              = 5;
    public static final int ORDER_APP               = 6;
    public static final int SUGGESTION              = 7;
    public static int messageType = 0;


    Context context;
    RadioButton radioButton1,radioButton2,radioButton3;




    public synchronized static Intent getIntent(Context context) {
        return getIntent(context,new Bundle());
    }

    public synchronized static Intent getIntent(Context context, Bundle bundle) {
        bundle.putString("item1","value1");
        Intent intent = new Intent(context, ContactUsActivity.class);
        intent.putExtras(bundle);
        return intent;
    }


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


        radioButton1 = (RadioButton) findViewById(R.id.radioButton1);
        radioButton2 = (RadioButton) findViewById(R.id.radioButton2);
        radioButton3 = (RadioButton) findViewById(R.id.radioButton3);





        if(Global.user != null){
//            etField1.setText(Global.user.getMobileNumber());
//            etField1.setEnabled(false);
        }

//        btn1.setText(context.getString(R.string.send));
//        btn2.setText(context.getString(R.string.cancel));
//        btn2.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                onBackPressed();
//            }
//        });
//        btn1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
////                if((etField1.getText().toString().length()>10 ||etField1.getText().toString().length()>10 ) && etField3.getText().toString().length()>10 ){
////                    Message message = new Message();
////                    message.setApplicationID(17);
////                    message.setPhoneNumber(etField1.getText().toString());
////                    message.setText(etField3.getText().toString());
////                    switch (messageType) {
////                        case ContactUsActivity.CONTACT_US:{
////                            message.setTitle("تماس با ما");
////                            break;
////                        }
////                        case ContactUsActivity.ORDER_APP:{
////                            message.setTitle("سفارش نرم افزار");
////                            break;
////                        }
////                        case ContactUsActivity.SUGGESTION:{
////                            message.setTitle("پیشنهادات و انتقادات");
////                            break;
////                        }
////                    }
////                    message.setType(messageType + "");
////                    AsyncSendMessage asyncSendMessage = new AsyncSendMessage(context,message);
////                    asyncSendMessage.execute();
////                }else
//                    Global.ShowMessageDialog(context,"",context.getString(R.string.notCorrectInput));
//
//
//            }
//        });

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

                                messageType = ContactUsActivity.CONTACT_US;
                                break;
                            case R.id.radioButton2:
                                radioButton1.setChecked(false);
                                radioButton2.setChecked(true);
                                radioButton3.setChecked(false);

                                messageType = ContactUsActivity.ORDER_APP;
                                break;
                            case R.id.radioButton3:
                                radioButton1.setChecked(false);
                                radioButton2.setChecked(false);
                                radioButton3.setChecked(true);

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
//                svScroll.fullScroll(ScrollView.FOCUS_UP);
            }
        },200);
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
