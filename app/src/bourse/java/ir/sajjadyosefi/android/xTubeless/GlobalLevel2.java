package ir.sajjadyosefi.android.xTubeless;

import android.app.DownloadManager;
import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Environment;
import android.widget.Toast;

import androidx.multidex.MultiDex;
import androidx.multidex.MultiDexApplication;

import com.google.gson.Gson;
import com.magnetadservices.sdk.MagnetSDK;
import com.squareup.picasso.OkHttp3Downloader;
import com.squareup.picasso.Picasso;

import org.litepal.LitePal;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.channels.FileChannel;
import java.util.Random;

import ir.tapsell.plus.TapsellPlus;


/**
 * Created by Sajad on 2/11/2017.
 */
public class GlobalLevel2 extends Global {

    @Override
    public void onCreate() {
        super.onCreate();
        if (BuildConfig.FLAVOR_market.equals("myket") ) {
//            MagnetSDK.initialize(getApplicationContext());
//            if (BuildConfig.DEBUG) {
//                MagnetSDK.getSettings().setTestMode(true);
//            } else {
//                MagnetSDK.getSettings().setTestMode(false);
//            }

            TapsellPlus.initialize(this, "esaohdhqicloeoqojffeteerbddecqkcecoabcckcnrofigptnmsghsmfkacoqmoratktg");
        }


//        Picasso.Builder builder = new Picasso.Builder(this);
//        builder.downloader(new OkHttp3Downloader(this,Integer.MAX_VALUE));
//        Picasso built = builder.build();
//        built.setIndicatorsEnabled(true);
//        built.setLoggingEnabled(true);
//        Picasso.setSingletonInstance(built);
//
//        Picasso.with(getActivity())
//                .load(imageUrl)
//                .networkPolicy(NetworkPolicy.OFFLINE)
//                .into(imageView, new Callback() {
//                    @Override
//                    public void onSuccess() {
//
//                    }
//
//                    @Override
//                    public void onError() {
//                        //Try again online if cache failed
//                        Picasso.with(getActivity())
//                                .load(posts.get(position).getImageUrl())
//                                .error(R.drawable.header)
//                                .into(imageView, new Callback() {
//                                    @Override
//                                    public void onSuccess() {
//
//                                    }
//
//                                    @Override
//                                    public void onError() {
//                                        Log.v("Picasso","Could not fetch image");
//                                    }
//                                });
//                    }
//                });
    }

}