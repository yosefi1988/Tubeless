package ir.sajjadyosefi.android.tubeless.classes;

/**
 * Created by sajjad on 08/02/2016.
 */
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Looper;
import android.util.Log;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.lang.Thread.UncaughtExceptionHandler;

import ir.sajjadyosefi.android.tubeless.classes.networkLayout.Url;
import ir.sajjadyosefi.android.tubeless.R;

public class CrashHandler implements UncaughtExceptionHandler {

    private Context context;

    public CrashHandler(Context ctx) {
        context = ctx;
    }

    @Override
    public void uncaughtException(Thread t, Throwable e) {

        // we make the error log for debugging
        StringBuilder report = new StringBuilder();
        report.append("Stack:\n");
        Writer result = new StringWriter();
        PrintWriter printWriter = new PrintWriter(result);
        e.printStackTrace(printWriter);
        report.append(result.toString());
        printWriter.close();
        report.append('\n');
        report.append("**** End of current Report ***");
        Log.e(CrashHandler.class.getName(),
                "Error" + report);
        //**** and now we show our custom error view to users
        showErrorDialog();

    }



    public void showErrorDialog() {
        Log.d("sendErrorMail", "get called");
        final AlertDialog.Builder builder = new AlertDialog.Builder(context);
        new Thread() {
            @Override
            public void run() {
                Looper.prepare();
                builder.setTitle(" پوزش");
                builder.create();
                builder.setNegativeButton("خروج",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                System.exit(0);
                            }
                        });
                builder.setPositiveButton("گزارش مشکل",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                String url =  Url.CONFIG_SERVER_ADDRESS + "Pages/contacts.aspx";
                                Intent i = new Intent(Intent.ACTION_VIEW);
                                i.setData(Uri.parse(url));
                                context.startActivity(i);
                                ((Activity)context).overridePendingTransition(R.anim.fadeout, R.anim.fadein);
                            }
                        });
                builder.setMessage("مشکل در اجرای برنامه !!");

                builder.show();
                Looper.loop();
            }
        }.start();
    }

}