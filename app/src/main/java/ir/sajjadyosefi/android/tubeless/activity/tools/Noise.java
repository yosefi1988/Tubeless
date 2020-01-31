package ir.sajjadyosefi.android.tubeless.activity.tools;

/**
 * Created by sajjad on 4/14/2018.
 */
import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.os.Handler;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import ir.sajjadyosefi.android.tubeless.Global;
import ir.sajjadyosefi.android.tubeless.R;

public class Noise extends Activity {

    TextView mStatusView;
    TextView mStatusView2;
    MediaRecorder mRecorder;
    Thread runner;
    private static double mEMA = 0.0;
    static final private double EMA_FILTER = 0.6;

    final Runnable updater = new Runnable(){

        public void run(){
            updateTv();
        };
    };

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case 9001:
                if ((grantResults.length > 0) ){
                    if(grantResults[0] == PackageManager.PERMISSION_GRANTED){
                        prepareToShare();
                    }else {
                        //Toast.makeText(context,context.getString(R.string.WeNeedYourDeviceInfo),Toast.LENGTH_LONG).show();
                        Global.ShowMessageDialog(getApplicationContext(),"",getApplicationContext().getString(R.string.WeNeedYourDeviceInfo));
                    }
                }else {
                    Toast.makeText(getApplicationContext(),getApplicationContext().getString(R.string.ErrorOnGetPermision),Toast.LENGTH_LONG).show();
                }
                break;

            default:
                break;
        }
    }
    final Handler mHandler = new Handler();

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_noise);
        mStatusView = (TextView) findViewById(R.id.status);
        mStatusView2 = (TextView) findViewById(R.id.status2);


        int permissionReadCheck = ContextCompat.checkSelfPermission(getApplicationContext(), android.Manifest.permission.RECORD_AUDIO);
        int permissionReadCheck2 = ContextCompat.checkSelfPermission(getApplicationContext(), android.Manifest.permission.CAPTURE_AUDIO_OUTPUT);

        if (permissionReadCheck != PackageManager.PERMISSION_GRANTED &&
            permissionReadCheck2 != PackageManager.PERMISSION_GRANTED ) {

            ActivityCompat.requestPermissions(((Activity)this),
                    new String[]{
                            android.Manifest.permission.RECORD_AUDIO,
                            android.Manifest.permission.CAPTURE_AUDIO_OUTPUT
                    },
                    9001);
        } else {
            prepareToShare();
        }

    }

    private void prepareToShare() {

        startRecorder();

        if (runner == null)
        {
            runner = new Thread(){
                public void run()
                {
                    while (runner != null)
                    {
                        try
                        {
                            Thread.sleep(100);
                            Log.i("Noise", "Tock");
                        } catch (InterruptedException e) { };
                        mHandler.post(updater);
                    }
                }
            };
            runner.start();
            Log.d("Noise", "start runner()");
        }
    }

    public void onResume(){
        super.onResume();

    }

    public void onPause(){
        super.onPause();
        stopRecorder();
    }

    public void startRecorder(){
        if (mRecorder == null) {
            mRecorder = new MediaRecorder();
            mRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
            mRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
//            mRecorder.setOutputFormat(MediaRecorder.OutputFormat.MPEG_4);
            mRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
            mRecorder.setOutputFile("/dev/null");
            try {
                mRecorder.prepare();
            }catch (java.io.IOException ioe) {
                android.util.Log.e("[Monkey]", "IOException: " + android.util.Log.getStackTraceString(ioe));
            }catch (java.lang.SecurityException e) {
                android.util.Log.e("[Monkey]", "SecurityException: " +  android.util.Log.getStackTraceString(e));
            }
            try {
                mRecorder.start();
            }catch (java.lang.SecurityException e) {
                android.util.Log.e("[Monkey]", "SecurityException: " + android.util.Log.getStackTraceString(e));
            }

            //mEMA = 0.0;
        }

    }



    public void stopRecorder() {
        if (mRecorder != null) {
            mRecorder.stop();
            mRecorder.release();
            mRecorder = null;
        }
    }

    public void updateTv(){
        mStatusView.setText(Double.toString((getAmplitudeEMA())) + " dB");
        mStatusView2.setText(soundDb(getAmplitude()) + " dB");
    }
    public double soundDb(double ampl){
        return  20 * Math.log10(getAmplitudeEMA() / ampl);
    }
    public double getAmplitude() {
        if (mRecorder != null)
            return  (mRecorder.getMaxAmplitude());
        else
            return 0;

    }
    public double getAmplitudeEMA() {
        double amp =  getAmplitude();
        mEMA = EMA_FILTER * amp + (1.0 - EMA_FILTER) * mEMA;
        return mEMA;
    }

}