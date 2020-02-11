package ir.sajjadyosefi.android.tubeless.asyncTask.config;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import androidx.appcompat.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;

import ir.sajjadyosefi.android.tubeless.classes.CommonClass;
import ir.sajjadyosefi.android.tubeless.networkLayout.networkLayout.RestApiHelper;

import ir.sajjadyosefi.android.tubeless.Global;
import ir.sajjadyosefi.android.tubeless.R;
import ir.sajjadyosefi.android.xTubeless.classes.modelY.NetworkLayout.ServerResponseConfig;
import ir.sajjadyosefi.android.xTubeless.classes.modelY.UpdateRequest;


/**
 * Created by Other on 10/23/2016.
 */
public class AsyncCheckServerConfing extends AsyncTask<String, Void, ServerResponseConfig> {
    private final Context context;
    private String CurrentVersion;

    public AsyncCheckServerConfing(Context context) {
        this.context = context;
    }

    @Override
    protected ServerResponseConfig doInBackground(String... params) {
        UpdateRequest updateRequest = new UpdateRequest(context);
        RestApiHelper myParser = new RestApiHelper();
        return myParser.checkServerConfig(updateRequest);
    }

    @Override
    protected void onPostExecute(ServerResponseConfig serverResponseConfig) {

        //check Server Version
        if(serverResponseConfig != null){
            if(serverResponseConfig.getResponseStatus() != null){
                if (serverResponseConfig.getResponseStatus().getCode() == 200)
                {
//                    CurrentVersion = CommonClass.GetAppVersion(context);
//                    Toast.makeText(context,CurrentVersion,Toast.LENGTH_LONG).show();
//                    Toast.makeText(context,context.getApplicationContext().getPackageName(),Toast.LENGTH_LONG).show();

//                    if (Global.user != null){
//                        Global.user.setCanSendPicture(serverResponseConfig.getConfiguration().getCanSendPicture());
//                    }
                    CurrentVersion = CurrentVersion.substring(0,CurrentVersion.length()-2);
                    if (!serverResponseConfig.getConfiguration().getVersion().getLastVersion().equals(CurrentVersion)){
                        ShowDownloadPromp2(context , serverResponseConfig);
                    }
                }else {
                    Global.ShowMessageDialog(context,"",serverResponseConfig.getResponseStatus().getMessage());
                }
            }else {
                try {
                    Global.ShowMessageDialog(context,"","Server error");
                }catch (Exception ex){}
            }
        }else {
            try {
                Global.ShowMessageDialog(context,"","System error");
            }catch (Exception ex){}
        }

    }
    public void ShowDownloadPromp2(final Context context, final ServerResponseConfig serverResponseConfig){
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        View dialoglayout = inflater.inflate(R.layout.update_layout, null);
        final AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setView(dialoglayout);
        final AlertDialog alertDialog1 = builder.create();
        alertDialog1.getWindow().getAttributes().windowAnimations = R.style.alertDialogAnimation;


        final Button cancelBtn = (Button) dialoglayout.findViewById(R.id.buttonCancel);
        final Button downloadBtn = (Button) dialoglayout.findViewById(R.id.buttonDownload);

        if(!serverResponseConfig.getConfiguration().getVersion().isUpdateForce()){
            cancelBtn.setVisibility(View.VISIBLE);
            cancelBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    alertDialog1.cancel();
                }
            });
        }else {
            cancelBtn.setVisibility(View.INVISIBLE);
            alertDialog1.setCancelable(false);
        }

        downloadBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog1.cancel();

                String url = serverResponseConfig.getConfiguration().getVersion().getUpdateApi();
                if(!url.contains("http"))
                    url = "http://" + url;

                Global.StartDownload(url, context);
            }
        });


        try {
            alertDialog1.show();
        }catch (Exception ex){

        }
    }
}
