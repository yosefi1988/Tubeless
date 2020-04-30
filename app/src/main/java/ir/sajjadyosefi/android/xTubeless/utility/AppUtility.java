package ir.sajjadyosefi.android.xTubeless.utility;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.net.Uri;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import ir.sajjadyosefi.android.xTubeless.Global;

/**
 * Created by Sajad on 2/11/2017.
 */
public class AppUtility {


//    private void mint() {
//        Mint.setApplicationEnvironment(Mint.appEnvironmentDevelopment);
//        Mint.initAndStartSession(AppUtility.this, "91043c9b");
//    }




    public void shareApp(Context context) {

        ApplicationInfo app = ((Activity)context).getApplicationContext().getApplicationInfo();
        String filePath2 = app.sourceDir;

        Intent intent = new Intent(Intent.ACTION_SEND);

        intent.setType("*/*");

        File originalApk = new File(filePath2);

        try {
            File tempFile = new File(((Activity)context).getExternalCacheDir() + "/ExtractedApk");
            //If directory doesn't exists create new
            if (!tempFile.isDirectory())
                if (!tempFile.mkdirs())
                    return;
            tempFile = new File(tempFile.getPath() + "/" + ((Activity)context).getString(app.labelRes).replace(" ","").toLowerCase() + ".apk");
            if (!tempFile.exists()) {
                if (!tempFile.createNewFile()) {
                    return;
                }
            }
            InputStream in = new FileInputStream(originalApk);
            OutputStream out = new FileOutputStream(tempFile);

            byte[] buf = new byte[1024];
            int len;
            while ((len = in.read(buf)) > 0) {
                out.write(buf, 0, len);
            }
            in.close();
            out.close();
            System.out.println("File copied.");
            // باز کردن پنجره اشتراک گذاری
            intent.putExtra(Intent.EXTRA_STREAM, Uri.fromFile(tempFile));
            ((Activity)context).startActivity(Intent.createChooser(intent, "اشتراک گذاری با"));

        } catch (IOException e) {
            e.printStackTrace();
        }


    }
    public void showTelegram(Context context) {

        final String appName = "org.telegram.messenger";
        final String appName1 = "com.parsitelg.telegram";
        final String appName2 = "org.telegram.plus";
        final String appName3 = "com.hanista.moboplus";
        final String appName4 = "com.hanista.mobogram";
        final String appName5 = "com.hanista.mobogram.two";
        final String appName6 = "com.hanista.mobogram.three";
        final String appName7 = "ir.persianfox.messenger";
        final String appName8 = "com.telegram.hame.mohamad";
        final String appName9 = "org.ir.talaeii";
        final String appName10 = "ir.rrgc.telegram";
        final String appName11 = "ir.felegram";

        if (CommonClass.isAppAvailable(context, appName)) {
            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://telegram.me/Tubeless_SN"));
            browserIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            (((Activity)context)).startActivity(browserIntent);
        } else {
            if (CommonClass.isAppAvailable(context, appName1)) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://telegram.me/Tubeless_SN"));
                browserIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                (((Activity)context)).startActivity(browserIntent);
            } else {
                if (CommonClass.isAppAvailable(context, appName2)) {
                    Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://telegram.me/Tubeless_SN"));
                    browserIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    (((Activity)context)).startActivity(browserIntent);
                } else {
                    if (CommonClass.isAppAvailable(context, appName3)) {
                        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://telegram.me/Tubeless_SN"));
                        browserIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        (((Activity)context)).startActivity(browserIntent);
                    } else {
                        if (CommonClass.isAppAvailable(context, appName4)) {
                            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://telegram.me/Tubeless_SN"));
                            browserIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            (((Activity)context)).startActivity(browserIntent);
                        } else {
                            if (CommonClass.isAppAvailable(context, appName5)) {
                                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://telegram.me/Tubeless_SN"));
                                browserIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                (((Activity)context)).startActivity(browserIntent);
                            } else {
                                if (CommonClass.isAppAvailable(context, appName6)) {
                                    Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://telegram.me/Tubeless_SN"));
                                    browserIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                    (((Activity)context)).startActivity(browserIntent);
                                } else {
                                    if (CommonClass.isAppAvailable(context, appName7)) {
                                        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://telegram.me/Tubeless_SN"));
                                        browserIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                        (((Activity)context)).startActivity(browserIntent);
                                    } else {
                                        if (CommonClass.isAppAvailable(context, appName8)) {
                                            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://telegram.me/Tubeless_SN"));
                                            browserIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                            (((Activity)context)).startActivity(browserIntent);
                                        } else {
                                            if (CommonClass.isAppAvailable(context, appName9)) {
                                                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://telegram.me/Tubeless_SN"));
                                                browserIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                                (((Activity)context)).startActivity(browserIntent);
                                            } else {
                                                if (CommonClass.isAppAvailable(context, appName10)) {
                                                    Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://telegram.me/Tubeless_SN"));
                                                    browserIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                                    (((Activity)context)).startActivity(browserIntent);
                                                } else {
                                                    if (CommonClass.isAppAvailable(context, appName11)) {
                                                        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://telegram.me/Tubeless_SN"));
                                                        browserIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                                        (((Activity)context)).startActivity(browserIntent);
                                                    } else {
                                                        DialogUtil.ShowMessageDialog(context,"","شما تلگرام را نصب نکرده اید.");
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

//        if(CommonClass.isNetworkConnected(mContext)) {
//        } else
//            Toast.makeText(mContext,"showTelegram", Toast.LENGTH_LONG).show();
    }
    public void showInstagram(Context context) {

        final String appName = "com.instagram.android";

        if (CommonClass.isAppAvailable(context, appName)) {
//            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://instagram.com/Tubeless_SN"));
//            browserIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//            startActivity(browserIntent);


            Intent iIntent = ((Activity)context).getPackageManager().getLaunchIntentForPackage(appName);
            iIntent.setComponent(new ComponentName( "com.instagram.android", "com.instagram.android.activity.UrlHandlerActivity"));
            iIntent.setData( Uri.parse( "http://instagram.com/_u/Tubeless_SN") );
            ((Activity)context).startActivity(iIntent);
        } else {
            //showDownloadDialog(R.string.downloadTelegram, appName);
            DialogUtil.ShowMessageDialog(context,"","شما اینستاگرام را نصب نکرده اید.");
        }

    }

    public void showWebPage(Context context) {


        DialogUtil.ShowMessageDialog(context,"","در حال آماده سازی هستیم.");
        //ok
//        Intent intent = new Intent(getApplicationContext(),WebViewActivity.class);
//        Bundle bundle = new Bundle();
//
////        bundle.putString("WebType", "report");
////        bundle.putString("WebType", "feedback");
////        bundle.putString("WebType", "about");
//        bundle.putString("WebType", "help");
//
//
//        bundle.putBoolean("isOnline" ,false);
//        bundle.putString("address", "http://www.sajjadyosefi.ir");
//
//        drawerLayout.closeDrawer(Gravity.RIGHT);
//        intent.putExtras(bundle);
//        startActivity(intent);
    }
}
