package ir.sajjadyosefi.android.tubeless.services;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.widget.Toast;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import ir.sajjadyosefi.android.tubeless.R;

import static ir.sajjadyosefi.android.tubeless.classes.business.RadyabBusiness.handleNotifications;


/**
 * Created by sajjad on 12/5/2017.
 */

public class GCMNotificationIntentService extends FirebaseMessagingService {

    @Override
    public void onMessageReceived(final RemoteMessage remoteMessage) {
        Log.d("Zero TO Hero", "Sender :" + remoteMessage.getFrom());
        if (remoteMessage.getData().size() > 0) {
            Log.d("Zero TO Hero", "Data :" + remoteMessage.getData());
            handleNotifications(getApplicationContext(),remoteMessage.getData().toString());
        }
//        if (remoteMessage.getNotification() !=null) {
//            Log.d("Zero TO Hero", "Message :" + remoteMessage.getNotification().getBody());
//            Log.d("Zero TO Hero", "Title  :" + remoteMessage.getNotification().getTitle());
//        }
//        Toast.makeText(getApplicationContext(),"Get Notif",Toast.LENGTH_LONG).show();

        Handler handler = new Handler(Looper.getMainLooper());
        handler.post(new Runnable() {

            @Override
            public void run() {
                Toast.makeText(GCMNotificationIntentService.this.getApplicationContext(),
                        remoteMessage.getData().toString()
                        ,Toast.LENGTH_SHORT).show();
            }
        });
        //showNotification(remoteMessage.getNotification().getBody(), getApplicationContext());
    }



    private void showNotification(String eventtext, Context ctx) {

        NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

        //API level 11
        Intent intent = new Intent("com.rj.notitfications.SECACTIVITY");

        PendingIntent pendingIntent = PendingIntent.getActivity(ctx, 1, intent, 0);

        Notification.Builder builder = new Notification.Builder(ctx);

        builder.setAutoCancel(false);
        builder.setTicker("this is ticker text");
        builder.setContentTitle("WhatsApp Notification");
        builder.setContentText("You have a new message");
        builder.setSmallIcon(R.drawable.ic_launcher);
        builder.setContentIntent(pendingIntent);
        builder.setOngoing(true);
        builder.setSubText("This is subtext...");   //API level 16
        builder.setNumber(100);
        builder.build();

        Notification myNotication = builder.getNotification();
        manager.notify(11, myNotication);

    }

}