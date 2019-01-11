package ir.sajjadyosefi.android.tubeless.classes;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Environment;
import android.os.Handler;
import android.util.Log;
import android.widget.Toast;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import ir.sajjadyosefi.android.tubeless.Global;

public class DownloadingTask extends AsyncTask<String, Integer, String> {

    File apkStorage = null;
    File outputFile = null;
    Context mContext;




    public DownloadingTask(Context context) {
        mContext = context;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        Toast.makeText(mContext, "در حال اتصال", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onPostExecute(String result) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {


            }
        },200);
        Toast.makeText(mContext, "دانلود کامل شد.", Toast.LENGTH_LONG).show();

//        try {
//            if (outputFile != null) {
//
//            } else {
//                new Handler().postDelayed(new Runnable() {
//                    @Override
//                    public void run() {
//                    }
//                }, 3000);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//
//            //Change button text if exception occurs
//            new Handler().postDelayed(new Runnable() {
//                @Override
//                public void run() {
//                }
//            }, 3000);
//        }

        super.onPostExecute(result);
    }

    @Override
    protected String doInBackground(String... params) {
        try {
            //
            URL url = new URL(params[0]);//Create Download URl
            HttpURLConnection c = (HttpURLConnection) url.openConnection();//Open Url Connection
            c.setRequestMethod("GET");//Set Request Method to "GET" since we are grtting data
            c.connect();//connect the URL Connection

            //If Connection response is not OK then show Logs
            if (c.getResponseCode() != HttpURLConnection.HTTP_OK) {
                Toast.makeText(mContext,"Error" , Toast.LENGTH_LONG).show();
            }


            //Get File if SD card is present
            if (Global.isSDCardPresent()) {
                apkStorage = Global.getFileStoragePath();
            }
            //else
                //Toast.makeText(context, "Oops!! There is no SD Card.", Toast.LENGTH_SHORT).show();

            //If File is not present create directory
            if (!apkStorage.exists()) {
                apkStorage.mkdir();
            }



            String fileNameWithoutExtn = url.toString().substring(0, url.toString().lastIndexOf('.'));
            String fileName = Global.getFileFullName(url.toString());
            outputFile = new File(apkStorage,fileName );//Create Output file in Main File
            List values = c.getHeaderFields().get("content-Length");

//            try {
//                // Make sure the Pictures directory exists.
//                //File path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
//                File path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM);
//                path.mkdirs();
//            }catch (Exception ex){
//                int a =  2 + 6 ;
//            }

            File path = Environment.getExternalStoragePublicDirectory( Environment.DIRECTORY_PICTURES);
//            final File path = Environment.getExternalStoragePublicDirectory
//                            (
//                                    //Environment.DIRECTORY_PICTURES
//                                    //Environment.DIRECTORY_DCIM
//                                    Environment.DIRECTORY_DCIM + "/TubelessImages/"
//                            );

            File file = new File(Environment.getExternalStorageDirectory().getAbsoluteFile(), fileName);    //InternalStoreage
            //File file = new File(Environment.getDataDirectory().getAbsoluteFile(), fileName);             //InternalStoreage

            //File file = new File(path, fileName);
            try {
                // Make sure the Pictures directory exists.
                path.mkdirs();
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }

            if (!outputFile.getParentFile().exists())
                outputFile.getParentFile().mkdirs();
            if (!outputFile.exists())
                outputFile.createNewFile();



                //Create New File if not present
            if (!outputFile.exists()) {

                if (!outputFile.getParentFile().exists())
                    outputFile.getParentFile().mkdirs();
                if (!outputFile.exists())
                    outputFile.createNewFile();
            }

            FileOutputStream fos = new FileOutputStream(outputFile);//Get OutputStream for NewFile Location

            InputStream is = c.getInputStream();//Get InputStream for connection
            String sLength;
            int sLengthInt = 0;
            if (values != null && !values.isEmpty()) {
                // getHeaderFields() returns a Map with key=(String) header
                // name, value = List of String values for that header field.
                // just use the first value here.
                sLength = (String) values.get(0);

                if (sLength != null) {
                    //parse the length into an integer...
                }
                sLengthInt = Integer.parseInt(sLength);
            }

            byte[] buffer = new byte[1024];//Set buffer type
            int len1 = 0;//init length
            int downloadedSize = 0;
            int counter=1;
            long total = 0;

            int darsad = 0,darsadTmp = 0;
            while ((len1 = is.read(buffer)) != -1) {
                total += len1;
                fos.write(buffer, 0, len1);//Write new file

                downloadedSize += len1;
                darsad = (int)((total*100)/sLengthInt);
                if(darsad != darsadTmp) {
                    Log.d("dddd",darsad + "");
                    publishProgress(darsad);
                    darsadTmp = darsad;
                }
            }
            //Close all connection after doing task
            fos.close();
            is.close();

        } catch (Exception e) {
            //Read exception if something went wrong
            e.printStackTrace();
            outputFile = null;
        }

        return null;
    }




    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);




    }
}