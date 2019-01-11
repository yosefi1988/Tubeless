package ir.sajjadyosefi.android.tubeless.classes;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

/**
 * Created by Sajad on 2/11/2017.
 */
public class MonoPushClass extends BroadcastReceiver
{
    @Override
    public void onReceive(Context context, Intent intent) {


        long reqId = intent.getLongExtra("RequestId", -1);
        String msg = intent.getStringExtra("Message");

//        MonoPush.OpenedPush(context,reqId);
//        MonoPush.DismissPush(context,reqId);

        Toast.makeText(context,"Notif recive",Toast.LENGTH_LONG).show();


    }
}
