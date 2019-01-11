package ir.sajjadyosefi.android.tubeless.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


import com.astuetz.MainPagerSlidingTabStrip;

import org.json.JSONObject;

import ir.adad.client.AdListener;
import ir.adad.client.AdView;
import ir.sajjadyosefi.android.tubeless.adapter.fragment.MainPagerAdapter;
import ir.sajjadyosefi.android.tubeless.asyncTask.config.AsyncCheckServerConfing;
import ir.sajjadyosefi.android.tubeless.classes.CommonClass;
import ir.sajjadyosefi.android.tubeless.R;

import ir.moslem_deris.apps.zarinpal.PaymentBuilder;
import ir.moslem_deris.apps.zarinpal.ZarinPal;
import ir.moslem_deris.apps.zarinpal.enums.ZarinPalError;
import ir.moslem_deris.apps.zarinpal.listeners.OnPaymentListener;
import ir.moslem_deris.apps.zarinpal.models.Payment;

public class MainActivity extends _BaseDrawerActivity {

    public ViewPager mainViewPager;
    private TextView textView;
    Context context;

    public static AdView adView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = this;
        setContentView(R.layout.activity_main);
        //overridePendingTransition(R.anim.rotator, R.anim.rotator);

        //pager and tab adapter
        mainViewPager = (ViewPager) findViewById(R.id.viewpager);
        mainViewPager.setAdapter(new MainPagerAdapter(context, mainViewPager, getSupportFragmentManager()));
        MainPagerSlidingTabStrip mainTabsStrip = (MainPagerSlidingTabStrip) findViewById(R.id.tabs);
        mainTabsStrip.setViewPager(mainViewPager);
        getSupportActionBar().hide();

        //int s = 5/0;

        //startActivity(new Intent(this, WebViewActivity.class));
        //new AsyncSendSms(this, "Amozesh" , "Amozesh").execute();
        //new AsyncEstelam(mContext).execute();


        textView = (TextView) findViewById(R.id.textview)                                                                                       ;
        Button btn = (Button) findViewById(R.id.btn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //  Show loading message
                textView.setText("در حال انتقال به صفحه پرداخت\nلطفا صبر کنید ...");
                //  Start payment process
                pay();
            }
        });

//        adView = ((AdView) findViewById(R.id.banner_ad_view));
//        adView.setAdListener(mAdListener);
//        if(Global.avalableBazarAd(mContext))
//            adView.setVisibility(View.VISIBLE);


        if(CommonClass.isNetworkConnected(context))
            new AsyncCheckServerConfing(context).execute();
    }




    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.fadeout, R.anim.fadein);
        finish();
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
                .setAmount(500)                                        //  In Toman
                .setDescription("پرداخت تست پلاگین اندروید")
                .setEmail("yosefi1988@gmail.com")                     //  This field is not necessary.
                .setMobile("09123678522")                               //  This field is not necessary.
                .create();

        /* Call pay method and pass your considered payment object.
            Don't call a payment before the last one is finished, if you do the first payment will be destroyed
            and you may even not receive any callbacks (onSuccess or onFailure)
        */
        ZarinPal.pay(this, payment, new OnPaymentListener() {
            @Override
            public void onSuccess(String refID) {
                Toast.makeText(MainActivity.this, refID, Toast.LENGTH_LONG).show();
                Log.wtf("TAG", "::ZarinPal::  RefId: " + refID);
                textView.setText("پرداخت با موفقیت انجام شد\nکد پیگیری: " + refID);
            }

            @Override
            public void onFailure(ZarinPalError error) {
                String errorMessage = "";
                switch (error){
                    case INVALID_PAYMENT: errorMessage = "پرداخت تایید نشد";           break;
                    case USER_CANCELED:   errorMessage = "پرداخت توسط کاربر متوقف شد"; break;
                    case NOT_ENOUGH_DATA: errorMessage = "اطلاعات پرداخت کافی نیست";    break;
                    case UNKNOWN:         errorMessage = "خطای ناشناخته";              break;
                }
                Log.wtf("TAG", "::ZarinPal::  ERROR: " + errorMessage);
                textView.setText("خطا!!!" + "\n" + errorMessage);
            }
        });
    }

    public AdListener mAdListener = new AdListener() {
        @Override
        public void onAdLoaded() {
            // این بخش زمانی که تبلیغ در برنامه شما بارگزاری می‌شود فراخوانی خواهد شد

            int a = 5 ;
            a++;
        }

        @Override
        public void onAdFailedToLoad() {
            // این بخش زمانی که مشکلی در بارگزاری تبلیغ وجود داشته باشد فراخوانی خواهد شد. به عنوان نمونه قطع شدن اینترنت و یا عدم وجود تبلیغ متناسب با برنامه شما در آن لحظه در سرور عدد
            int a = 5 ;
            a++;
        }

        @Override
        public void onMessageReceive(JSONObject message) {
            int a = 5 ;
            a++;
        }

        @Override
        public void onRemoveAdsRequested() {
            // این بخش در صورتی که شما در برنامه خود امکان حذف تبلیغات را به کاربر داده باشید و کاربر آن گزینه را انتخاب کند فراخوانی خواهد شد. می‌توانید در این بخش کاربر را به صفحه فروشگاه برنامه خود هدایت کنید
            int a = 5 ;
            a++;
        }

    };

    @Override
    protected void onStart() {
        super.onStart();
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);



        //DownloadTask

        // execute this when the downloader must be fired
//        final DownloadTask downloadTask = new DownloadTask(mContext,progressBar,holder.iv_icon_file,holder.iv_icon_photo,holder.ib_icon_photo_DL,"file",null , sizeFile,holder.dl_progress_indetermine);
//        String url = "";
//        String filepath = "/sdcard/Download/" + url.substring(url.lastIndexOf('/')+1, url.length() );
//
//        File file = new File(filepath);
//        if (file.exists()) {
//            //open
//            String tempArr[] = filepath.split("\\.");
//            String fileFormat = tempArr[tempArr.length - 1];
//            if(fileFormat.toUpperCase().equals("MP4") ||
//                    fileFormat.toUpperCase().equals("3GP") ||
//                    fileFormat.toUpperCase().equals("JPG") ||
//                    fileFormat.toUpperCase().equals("PNG") ||
//                    fileFormat.toUpperCase().equals("JPEG"))
//            {
//                Intent viewOfPlayer = new Intent(mContext, ImageAndVideoPlayer.class);
//                viewOfPlayer.putExtra("fileFormat",fileFormat);
//                viewOfPlayer.putExtra("filePath",filepath);
//                mContext.startActivity(viewOfPlayer);
//            }
//            else {
//                try {
//                    //Toast.makeText(mContext, "Exist", Toast.LENGTH_SHORT).show();
//                    File myFile = new File("your any type of file url");
//                    FileOpen.openFile(mContext, file);
//                } catch (Exception ex) {
//
//                    ex.printStackTrace();
//                }
//            }
//
//        }else{
////                                holder.dl_progress_indetermine.setIndeterminate(true);
//            //Toast.makeText(mContext, "Download", Toast.LENGTH_SHORT).show();
//
//            //downloadTask.execute(url, values.getString(Statics.COOKIES, "") + " " + values.getString("cookies_login", ""));
//            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB)
//                downloadTask.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR,url, values.getString(Statics.COOKIES, "") + " " + values.getString("cookies_login", ""));
//            else
//                downloadTask.execute(url, values.getString(Statics.COOKIES, "") + " " + values.getString("cookies_login", ""));
//        }
    }
}

