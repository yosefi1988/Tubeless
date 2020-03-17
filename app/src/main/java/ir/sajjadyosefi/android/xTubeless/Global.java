package ir.sajjadyosefi.android.xTubeless;

import android.app.Activity;
import android.app.DownloadManager;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import androidx.core.app.ActivityCompat;
import androidx.appcompat.app.AlertDialog;
import androidx.multidex.MultiDex;
import androidx.multidex.MultiDexApplication;

import android.telephony.TelephonyManager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import org.litepal.LitePal;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.channels.FileChannel;
import java.util.Random;

//import ir.sajjadyosefi.android.tubeless.adapter.fragment.FragmentGashtogozarAdapter;
//import ir.sajjadyosefi.android.tubeless.adapter.fragment.FragmentMashinbazAdapter;
//import ir.sajjadyosefi.android.tubeless.adapter.fragment.FragmentPoliceAdapter;
//import ir.sajjadyosefi.android.tubeless.adapter.fragment.FragmentTamirgahAdapter;
//import ir.sajjadyosefi.android.tubeless.adapter.fragment.FragmentTimelineAdapter;
//import ir.sajjadyosefi.android.tubeless.adapter.fragment.FragmentYaftehaAdapter;
import dagger.Component;

import ir.sajjadyosefi.android.xTubeless.classes.model.Device;
import ir.sajjadyosefi.android.xTubeless.networkLayout.networkLayout.retrofit.RetrofitHelper2;
import ir.sajjadyosefi.android.xTubeless.networkLayout.networkLayout.retrofit.RetrofitHelperEpolice;
import ir.sajjadyosefi.android.xTubeless.networkLayout.retrofit.RetrofitHelperTubeless;

import ir.sajjadyosefi.android.xTubeless.classes.model.user.User;
import ir.sajjadyosefi.android.xTubeless.networkLayout.retroftPost.RetrofitHelper;
import ir.sajjadyosefi.android.xTubeless.utility.FontChanger;
import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

//اس دی کی های تبلیغاتی عدد
import ir.adad.banner.AdadBannerAd;
import ir.adad.core.Adad;

import static ir.sajjadyosefi.android.xTubeless.widget.CustomEditText.FONT_IRANSANS_MOBILE_NORMAL_TTF;


/**
 * Created by Sajad on 2/11/2017.
 */
public class Global extends MultiDexApplication {

    //_____________ ok ________________
    public static User user = null;
    //_________________________________



    public static String token;
    public static RetrofitHelper apiManagerPost;
    public static RetrofitHelperTubeless apiManagerTubeless;
    public static RetrofitHelperEpolice retrofitHelperEpolice;
    public static RetrofitHelper2 retrofit2;


    @Override
    public void onCreate() {
        super.onCreate();
        MultiDex.install(this);
        apiManagerPost = RetrofitHelper.getInstance();
        retrofitHelperEpolice = RetrofitHelperEpolice.getInstance(this);
        retrofit2 = RetrofitHelper2.getInstance(this);

        //font 1
//        FontChanger.overrideDefaultFont(this, "DEFAULT", FONT_IRANSANS_MOBILE_NORMAL_TTF);

        //font 6 ok
        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath(FONT_IRANSANS_MOBILE_NORMAL_TTF)
                .setFontAttrId(R.attr.fontPath)
                .build());


        Adad.setEnabled(true);
        Adad.setTestMode(false);
        Adad.initialize("357418d6-115d-47d3-8fb4-a228d2c1b882");// جایگاه وارد کردن توکن یا شناسه ی اپلیکیشن مورد نظر


        apiManagerTubeless = RetrofitHelperTubeless.getInstance();
        LitePal.initialize(this);





    }

    public static void copy(File src, File dst) {
        try {

            FileInputStream inStream = new FileInputStream(src);
            FileOutputStream outStream = new FileOutputStream(dst);
            FileChannel inChannel = inStream.getChannel();
            FileChannel outChannel = outStream.getChannel();
            inChannel.transferTo(0, inChannel.size(), outChannel);
            inStream.close();
            outStream.close();
        }catch (Exception ex ){

        }
    }

    public static void StartDownload(String Url,Context context) {
        // show download is started to user
        Toast.makeText(context,context.getString(R.string.startDownloadText), Toast.LENGTH_SHORT);
        /////////////////////////////// Start Download //////////////////////////////////
        //DownloadManager.Request r = new DownloadManager.Request(Uri.parse(Url));
        DownloadManager.Request request = new DownloadManager.Request(Uri.parse(Url));
        request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, context.getString(R.string.downloadNotifText));
        request.allowScanningByMediaScanner();
        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
        DownloadManager dm = (DownloadManager) context.getSystemService(Context.DOWNLOAD_SERVICE);
        dm.enqueue(request);
        //////////////////////////////////////////////////////////////////////////////
    }

    public static long generateRandom(int length) {
        Random random = new Random();
        char[] digits = new char[length];
        digits[0] = (char) (random.nextInt(9) + '1');
        for (int i = 1; i < length; i++) {
            digits[i] = (char) (random.nextInt(10) + '0');
        }
        return Long.parseLong(new String(digits));
    }

    public static  String getFileType(String word){
        if (word.length() == 3) {
            return word;
        } else if (word.length() > 3) {
            return word.substring(word.length() - 3);
        } else {
            // whatever is appropriate in this case
            throw new IllegalArgumentException("word has less than 3 characters!");
        }
    }

//    public static int getIndexByTabType(int type){
//        int index = 0 ;
//        switch (type){
//            case 16 :
//            {
//                return FragmentYaftehaAdapter.mTab1;
//            }
//            case 17 :
//            {
//                return FragmentYaftehaAdapter.mTab2;
//            }
//            case 18 :
//            {
//                return FragmentYaftehaAdapter.mTab3;
//            }
//            case 2 :
//            {
//                return FragmentPoliceAdapter.mTab1;
//            }
//            case 3 :
//            {
//                return FragmentPoliceAdapter.mTab2;
//            }
//            case 6:
//            {
//                return FragmentTamirgahAdapter.mTab1;
//            }
//            case 5:
//            {
//                return FragmentTamirgahAdapter.mTab2;
//            }
//            case 12:
//            {
//                return FragmentTimelineAdapter.mTab2;
//            }
//            case 0 :
//            {
//                return FragmentTimelineAdapter.mTab1;
//            }
//            case 9:
//            {
//                return FragmentMashinbazAdapter.mTab1;
//            }
//            case 8:
//            {
//                return FragmentMashinbazAdapter.mTab2;
//            }
//            case 14: {
//                return FragmentGashtogozarAdapter.mTab1;
//            }
//            default :
//                return 10000000;
//        }
//
//    }

//    public static void resetListIndex(int type){
//        switch (type){
//            case 16 :
//            {
//                FragmentYaftehaAdapter.mTab1 = 0;
//                break;
//            }
//            case 17 :
//            {
//                FragmentYaftehaAdapter.mTab2 = 0 ;
//                break;
//            }
//            case 18 :
//            {
//                FragmentYaftehaAdapter.mTab3 = 0;
//                break;
//            }
//            case 2 :
//            {
//                FragmentPoliceAdapter.mTab1 = 0;
//                break;
//            }
//            case 3 :
//            {
//                FragmentPoliceAdapter.mTab2 = 0;
//                break;
//            }
//            case 6:
//            {
//                FragmentTamirgahAdapter.mTab1 = 0;
//                break;
//            }
//            case 5:
//            {
//                FragmentTamirgahAdapter.mTab2 = 0;
//                break;
//            }
//            case 12:
//            {
//                FragmentTimelineAdapter.mTab2 = 0;
//                break;
//            }
//            case 0 :
//            {
//                FragmentTimelineAdapter.mTab1 = 0;
//                break;
//            }
//            case 9:
//            {
//                FragmentMashinbazAdapter.mTab1 = 0;
//                break;
//            }
//            case 8:
//            {
//                FragmentMashinbazAdapter.mTab2 = 0;
//                break;
//            }
//            case 14: {
//                FragmentGashtogozarAdapter.mTab1 = 0;
//                break;
//            }
//        }
//    }

//    public static void increamentIndex(int type){
//        switch (type){
//            case 16 :
//            {
//                FragmentYaftehaAdapter.mTab1 ++;
//                break;
//            }
//            case 17 :
//            {
//                FragmentYaftehaAdapter.mTab2 ++;
//                break;
//            }
//            case 18 :
//            {
//                FragmentYaftehaAdapter.mTab3 ++;
//                break;
//            }
//            case 2 :
//            {
//                FragmentPoliceAdapter.mTab1 ++;
//                break;
//            }
//            case 3 :
//            {
//                FragmentPoliceAdapter.mTab2++;
//                break;
//            }
//            case 6:
//            {
//                FragmentTamirgahAdapter.mTab1++;
//                break;
//            }
//            case 5:
//            {
//                FragmentTamirgahAdapter.mTab2++;
//                break;
//            }
//            case 12:
//            {
//                FragmentTimelineAdapter.mTab2++;
//                break;
//            }
//            case 0 :
//            {
//                FragmentTimelineAdapter.mTab1++;
//                break;
//            }
//            case 9:
//            {
//                FragmentMashinbazAdapter.mTab1++;
//                break;
//            }
//            case 8:
//            {
//                FragmentMashinbazAdapter.mTab2++;
//                break;
//            }
//            case 14: {
//                FragmentGashtogozarAdapter.mTab1++;
//                break;
//            }
//        }
//    }

    //public static class CheckForSDCard {
        //Check If SD Card is present or not method
        public static boolean isSDCardPresent() {
            if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
                return true;
            }
            return false;
        }
    //}

    public static  String getFileFullName(String word){
        return word.toString().substring( word.toString().lastIndexOf('/')+1, word.toString().length() );
    }

    public static final String downloadDirectory = "/DCIM/TubelessImages/";
    public static File getFileLocalPath(String fileName){
        return  new File(Environment.getExternalStorageDirectory() + "/"  + downloadDirectory + fileName);
    }
    public static File getFileStoragePath(){
        return  new File(Environment.getExternalStorageDirectory() + "/"  + downloadDirectory );
    }


    public static final void SaveLogedInUser(Context context,User user){
        SharedPreferences prefs = null;
        prefs =  context.getSharedPreferences("ir.sajjadyosefi.android.tubeless", MODE_PRIVATE);
        Gson gson = new Gson();
        String json = gson.toJson(user);
        prefs.edit().putString("LogedInUser",json).commit();
    }

    public static final User LoadLogedInUser(Context context){
        SharedPreferences prefs = null;
        prefs =  context.getSharedPreferences("ir.sajjadyosefi.android.tubeless", MODE_PRIVATE);

        if(prefs.getString("LogedInUser","").length()>10) {
            Gson gson = new Gson();
            String stringOfUser = prefs.getString("LogedInUser", "");
            return gson.fromJson(stringOfUser, User.class);
        }else {
            return null;
        }
    }
    public static final void ClearLogedInUser(Context context){
        SharedPreferences prefs = null;
        prefs =  context.getSharedPreferences("ir.sajjadyosefi.android.tubeless", MODE_PRIVATE);
        prefs.edit().putString("LogedInUser", "").commit();
    }

    public static void ShowMessageDialog(final Context context, String title, String text){
        ShowMessageDialog(context, title, text , null);
    }

    public static void ShowMessageDialog(final Context context, String title, String text, View.OnClickListener clickListner){
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        View dialoglayout = inflater.inflate(R.layout.update_layout, null);
        final AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setView(dialoglayout);
        final AlertDialog alertDialog1 = builder.create();
        alertDialog1.getWindow().getAttributes().windowAnimations = R.style.alertDialogAnimation;


        final Button cancelBtn = (Button) dialoglayout.findViewById(R.id.buttonCancel);
        final Button downloadBtn = (Button) dialoglayout.findViewById(R.id.buttonDownload);
        downloadBtn.setVisibility(View.GONE);

        TextView textViewText = (TextView) dialoglayout.findViewById(R.id.textView1);
        textViewText.setText(text);
        cancelBtn.setText(context.getString(R.string.ok));

        if(clickListner != null){
//            cancelBtn.setVisibility(View.INVISIBLE);
//            alertDialog1.setCancelable(false);
            cancelBtn.setOnClickListener(clickListner);
            alertDialog1.cancel();
        } else {
            cancelBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    alertDialog1.cancel();
                }
            });
        }


        try {
            alertDialog1.show();
        }catch (Exception ex){

        }
    }


    public static void ShowSelectSturceDialog(final Context context,String title,String text,String btn1Text,View.OnClickListener onClickListener1,String btn2Text,View.OnClickListener onClickListener2){
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        View dialoglayout = inflater.inflate(R.layout.update_layout, null);
        final AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setView(dialoglayout);
        final AlertDialog alertDialog1 = builder.create();
        alertDialog1.getWindow().getAttributes().windowAnimations = R.style.alertDialogAnimation;
        alertDialog1.setCancelable(false);

        final Button btn1 = (Button) dialoglayout.findViewById(R.id.buttonCancel);
        final Button btn2 = (Button) dialoglayout.findViewById(R.id.buttonDownload);
        btn1.setText(btn1Text);
        btn2.setText(btn2Text);
        btn1.setOnClickListener(onClickListener1);
        btn2.setOnClickListener(onClickListener2);

        //titile
        TextView textViewTitle = (TextView) dialoglayout.findViewById(R.id.textView1);
        textViewTitle.setText(title);

        //text
        TextView textViewText = (TextView) dialoglayout.findViewById(R.id.textView1);
        textViewText.setText(text);


        try {
            alertDialog1.show();
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }


    public static final boolean avalableSendAppInMenu(Context context){
        SharedPreferences prefs = null;
        prefs =  context.getSharedPreferences("ir.sajjadyosefi.android.tubeless", MODE_PRIVATE);
        int count = prefs.getInt("avalableSendAppInMenu", 0);
        if (count == 10) {
            return true;
        }else {
            prefs.edit().putInt("avalableSendAppInMenu", count + 1).commit();
            return false;
        }
    }
    public static final boolean avalableBazarAd(Context context){
        SharedPreferences prefs = null;
        prefs =  context.getSharedPreferences("ir.sajjadyosefi.android.tubeless", MODE_PRIVATE);
        int count = prefs.getInt("avalableBazarAd", 0);
        if (count == 13) {
            return true;
        }else {
            prefs.edit().putInt("avalableBazarAd", count + 1).commit();
            return false;
        }
    }

}