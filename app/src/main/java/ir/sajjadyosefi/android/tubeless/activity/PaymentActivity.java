package ir.sajjadyosefi.android.tubeless.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import ir.moslem_deris.apps.zarinpal.PaymentBuilder;
import ir.moslem_deris.apps.zarinpal.ZarinPal;
import ir.moslem_deris.apps.zarinpal.enums.ZarinPalError;
import ir.moslem_deris.apps.zarinpal.listeners.OnPaymentListener;
import ir.moslem_deris.apps.zarinpal.models.Payment;
import ir.sajjadyosefi.android.tubeless.asyncTask.other.AsyncPeymentResult;
import ir.sajjadyosefi.android.tubeless.classes.CommonClass;
import ir.sajjadyosefi.android.xTubeless.activity.common.Splash_Screen;
import ir.sajjadyosefi.android.xTubeless.classes.modelY.AppStatus;
import ir.sajjadyosefi.android.xTubeless.classes.modelY.RequestConfirmPayment;
import ir.sajjadyosefi.android.tubeless.R;

public class PaymentActivity extends Activity {

    public Context context;
    public EditText phone,email,discription;
    private TextView textView;
    Button btn,btnContiniue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);
        context = this;


        textView = (TextView) findViewById(R.id.textview);
        phone = (EditText) findViewById(R.id.editTextPhone);
        email = (EditText) findViewById(R.id.editTextEmail);
        discription = (EditText) findViewById(R.id.editTextDiscription);

        ((TextView) findViewById(R.id.textViewPrice)).setText(context.getString(R.string.app_price));



        btn = (Button) findViewById(R.id.btn);
        btnContiniue = (Button) findViewById(R.id.btnContiniue);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //  Show loading message
                textView.setText("در حال انتقال به صفحه پرداخت\nلطفا صبر کنید ...");
                //  Start payment process
                pay();
            }
        });

    }


    public void onPaymentButtonClicked(View view){

    }

    private void pay(){
        /* Prepare a Payment object with custom data.
            The standard form we recommend is you create a constant value including your merchantID, and for
            anything you want to sell create a unique payment object (instead of editing your current object times and times)
            and pass the variable holding merchantID, this helps you edit your merchantID safe and easy anytime you wanted.
        */




        Payment payment = new PaymentBuilder()
                .setMerchantID("e8a913e8-f089-11e6-8dec-005056a205be")  //  This is an example, put your own merchantID here.
                .setAmount(Integer.parseInt(context.getString(R.string.app_price))   )                                     //  In Toman
                .setDescription(discription.getText().toString() +
                        "+app_id:" + context.getString(R.string.app_id)+
                        "+androidID" + CommonClass.GetAndroidId(context))
                .setEmail(email.getText().toString())                     //  This field is not necessary.
                .setMobile(phone.getText().toString())                               //  This field is not necessary.
                .create();

        /* Call pay method and pass your considered payment object.
            Don't call a payment before the last one is finished, if you do the first payment will be destroyed
            and you may even not receive any callbacks (onSuccess or onFailure)
        */
        ZarinPal.pay(this, payment, new OnPaymentListener() {
            @Override
            public void onSuccess(String refID) {
                Toast.makeText(PaymentActivity.this, refID, Toast.LENGTH_LONG).show();


                //Log.wtf("TAG", "::ZarinPal::  RefId: " + refID);

                RequestConfirmPayment requestConfirmPayment = new RequestConfirmPayment();
                requestConfirmPayment.setAndroidID(CommonClass.GetAndroidId(context));
                requestConfirmPayment.setAppID(context.getString(R.string.app_id));
                requestConfirmPayment.setRefId(refID);
                requestConfirmPayment.setPrice(context.getString(R.string.app_price));
                requestConfirmPayment.setEmail(email.getText().toString());
                requestConfirmPayment.setPhone(phone.getText().toString());


                new AsyncPeymentResult(context, requestConfirmPayment).execute();
                CommonClass.ShowInternetConnection(context,CommonClass.SHOW_PEYMENT_REF_ID,refID);


                textView.setText("پرداخت با موفقیت انجام شد\nکد پیگیری: " + refID);

                //Save
                AppStatus appStatus;
//                List<AppStatus> appStatusList = AppStatus.listAll(AppStatus.class);
//                if (appStatusList.size() == 0) {
//
//                } else {
//                    appStatus = appStatusList.get(0);
//                    appStatus.buyStatus = "OK";
//                    appStatus.save();
//                }
                //End Save




                textView.setTextColor(Color.GREEN);
                btn.setVisibility(View.GONE);
                btnContiniue.setVisibility(View.VISIBLE);
                btnContiniue.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        context.startActivity(new Intent(context, Splash_Screen.class));
                        overridePendingTransition(R.anim.fadeout, R.anim.fadein);
                        finish();
                    }
                });
            }

            @Override
            public void onFailure(ZarinPalError error) {
                String errorMessage = "";
                switch (error){
                    case INVALID_PAYMENT: errorMessage = "پرداخت تایید نشد";           break;
                    case USER_CANCELED:   errorMessage = "پرداخت توسط کاربر متوقف شد"; break;
                    case NOT_ENOUGH_DATA: errorMessage = "اطلاعات پرداخت کافی نیست";    break;
                    case UNKNOWN:         errorMessage = "-1اتصال اینترنت خود را بررسی کنید\n2-مقادیر را به درستی وارد کنید";              break;
                }
                Log.wtf("TAG", "::ZarinPal::  ERROR: " + errorMessage);
                textView.setText("خطا!!!" + "\n" + errorMessage);
                textView.setTextColor(Color.RED);

                btnContiniue.setVisibility(View.VISIBLE);
                btnContiniue.setText(context.getString(R.string.cancelAndExit));
                btnContiniue.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        finish();
                    }
                });
            }
        });
    }
}
