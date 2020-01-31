package ir.sajjadyosefi.android.tubeless.networkLayout.networkLayout;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.gson.Gson;
import com.zl.reik.dilatingdotsprogressbar.DilatingDotsProgressBar;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;


import ir.sajjadyosefi.android.tubeless.Global;
import ir.sajjadyosefi.android.tubeless.R;

import ir.sajjadyosefi.android.tubeless.activity.uploadPicture.UploadPictureActivity;
import ir.sajjadyosefi.android.xTubeless.classes.modelY.Config.SendCarPictureResponse;
import ir.sajjadyosefi.android.xTubeless.utility.MultipartUtility;
import ir.sajjadyosefi.android.xTubeless.activity.MainActivity;


/**
 * Created by Other on 10/23/2016.
 */
public class HttpFileUploadAsync extends AsyncTask<Void, Void, SendCarPictureResponse> {
    URL connectURL;
    String ServerPath;
    String callbackurl;
    String Url;
    FileInputStream fileInputStream = null;
    File mfile;
    Context context;
    DilatingDotsProgressBar dilatingDotsProgressBar;
    Button btnSend;

    public HttpFileUploadAsync(Context context, DilatingDotsProgressBar dilatingDotsProgressBar, Button btnSend, String urlString, File file, String serverPath, String callbackurl) throws MalformedURLException, FileNotFoundException {
        this.context = context;
        this.connectURL =  new URL(urlString) ;
        Url = urlString ;
        fileInputStream = new FileInputStream(file);
        mfile = file;
        ServerPath = serverPath;
        this.callbackurl =  callbackurl;
        this.dilatingDotsProgressBar = dilatingDotsProgressBar;
        this.btnSend = btnSend;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        dilatingDotsProgressBar.showNow();
        btnSend.setEnabled(true);
    }
    SendCarPictureResponse Sending2(){
        try {
            String charset = "UTF-8";
            String requestURL = Url;


            MultipartUtility multipart = null;

            multipart = new MultipartUtility(requestURL, charset);

            multipart.addFormField("ServerPath", ServerPath);
            multipart.addFormField("callbackurl", callbackurl);
            multipart.addFormField("password", "12");
            multipart.addFilePart("uploadedfile", mfile);
            String response = multipart.finish(); // response from server.

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    SendCarPictureResponse Sending(){
        SendCarPictureResponse sendCarPictureResponse = new SendCarPictureResponse();
        String iFileName = mfile.getName();
        String lineEnd = "\r\n";
        String twoHyphens = "--";
        String boundary = "*****";
        String Tag="fSnd";
        try
        {
            Log.e(Tag,"Starting Http File Sending to URL");

            // Open a HTTP connection to the URL
            HttpURLConnection conn = (HttpURLConnection)connectURL.openConnection();

            // Allow Inputs
            conn.setDoInput(true);

            // Allow Outputs
            conn.setDoOutput(true);

            // Don't use a cached copy.
            conn.setUseCaches(false);

            conn.setReadTimeout(10000);
            conn.setConnectTimeout(15000);

            // Use a post method.
            conn.setRequestMethod("POST");

            conn.setRequestProperty("Connection", "Keep-Alive");

            conn.setRequestProperty("Content-Type", "multipart/form-data;boundary="+boundary);

            DataOutputStream dos = new DataOutputStream(conn.getOutputStream());

            dos.writeBytes(twoHyphens + boundary + lineEnd);
            dos.writeBytes("Content-Disposition: form-data; name=\"ServerPath\""+ lineEnd);
            dos.writeBytes(lineEnd);
            dos.writeBytes(ServerPath);
            dos.writeBytes(lineEnd);
            dos.writeBytes(twoHyphens + boundary + lineEnd);

            dos.writeBytes("Content-Disposition: form-data; name=\"callbackurl\""+ lineEnd);
            dos.writeBytes(lineEnd);
            //dos.writeBytes(callbackurl);
            //dos.writeBytes(callbackurl);
            dos.write(callbackurl.getBytes("UTF-8"));

            dos.writeBytes(lineEnd);
            dos.writeBytes(twoHyphens + boundary + lineEnd);



            dos.writeBytes("Content-Disposition: form-data; name=\"password\""+ lineEnd);
            dos.writeBytes(lineEnd);
            dos.writeBytes("12");
            dos.writeBytes(lineEnd);
            dos.writeBytes(twoHyphens + boundary + lineEnd);


            dos.writeBytes("Content-Disposition: form-data; name=\"uploadedfile\";filename=\"" + iFileName +"\"" + lineEnd);
            dos.writeBytes(lineEnd);

            Log.e(Tag,"Headers are written");

            // create a buffer of maximum size
            int bytesAvailable = fileInputStream.available();

            int maxBufferSize = 1024;
            int bufferSize = Math.min(bytesAvailable, maxBufferSize);
            byte[ ] buffer = new byte[bufferSize];

            // read file and write it into form...
            int bytesRead = fileInputStream.read(buffer, 0, bufferSize);

            while (bytesRead > 0)
            {
                dos.write(buffer, 0, bufferSize);
                bytesAvailable = fileInputStream.available();
                bufferSize = Math.min(bytesAvailable,maxBufferSize);
                bytesRead = fileInputStream.read(buffer, 0,bufferSize);
            }
            dos.writeBytes(lineEnd);
            dos.writeBytes(twoHyphens + boundary + twoHyphens + lineEnd);

            // close streams
            fileInputStream.close();

            dos.flush();

//            Log.e(Tag,"File Sent, Response: "+String.valueOf(conn.getResponseCode()));


            //InputStream is = conn.getInputStream();
            BufferedReader input = null;
            try {

                int status = conn.getResponseCode();
                InputStream error = conn.getErrorStream();


                InputStream inputStream = conn.getInputStream();
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "UTF-8");
                try {
                    input = new BufferedReader(inputStreamReader);
                }catch (Exception ex){
                    int a = 3 ;
                    a++;
                }
                StringBuilder strB = new StringBuilder();
                String str;
                while (null != (str = input.readLine())) {
                    strB.append(str).append("\r\n");
                }
                input.close();
                Gson gson = new Gson();
                sendCarPictureResponse = gson.fromJson(strB.toString(), SendCarPictureResponse.class);

            } catch (IOException e) {
                e.printStackTrace();
            }

            dos.close();
        }
        catch (MalformedURLException ex)
        {
            Log.e(Tag, "URL error: " + ex.getMessage(), ex);
        }

        catch (IOException ioe)
        {
            Log.e(Tag, "IO error: " + ioe.getMessage(), ioe);
        }
        return sendCarPictureResponse;
    }

    @Override
    protected SendCarPictureResponse doInBackground(Void... voids) {
        return Sending();
    }

    @Override
    protected void onPostExecute(SendCarPictureResponse response) {
        super.onPostExecute(response);
        if(response != null)
            if (response.getServerStatus() != null) {
                if (response.getServerStatus().getMessage() != "") {

                    dilatingDotsProgressBar.hideNow();
                    btnSend.setEnabled(true);

                    View.OnClickListener clickListner = new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            UploadPictureActivity.mFileTemp = null;
                            UploadPictureActivity.selectedPicture = false;

                            Intent intent = new Intent(context, MainActivity.class);
                            ((Activity)context).startActivity(intent);
                            ((Activity)context).overridePendingTransition(R.anim.fadeout, R.anim.fadein);
                            ((Activity)context).finish();
                        }
                    } ;
                    Global.ShowMessageDialog(context,"",response.getServerStatus().getMessage(),clickListner);
                }
            }
    }

void post() {

    }
}
