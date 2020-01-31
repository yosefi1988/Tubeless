package ir.sajjadyosefi.android.xTubeless.classes.modelY.Exception;


import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.android.material.snackbar.Snackbar;

import ir.sajjadyosefi.android.tubeless.R;
import ir.sajjadyosefi.android.xTubeless.classes.modelY.responses.basic.ServerResponseBase;


public class TubelessException extends Exception{

    public static final int NATIONAL_CODE_NOT_TRUE = 1001;
    public static final int NAME_NOT_TRUE = 1002;
    public static final int FAMILY_NOT_TRUE = 1003;
    public static final int FATHER_NOT_TRUE = 1004;


    private String message;
    private int code;



    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }


    // Overrides Exception's getMessage()
    @Override
    public String getMessage(){
        return message;
    }


    public void setMessage(String message) {
        this.message = message;
    }


    public TubelessException() {

    }

    @Override
    public void printStackTrace() {
//        super.printStackTrace();
//        System.out.println(getMessage());
//        Log.w("myApp", getMessage());
        Log.e("tYafte", getMessage());
    }



    public TubelessException(int errorCode) {
        switch (errorCode){
            case 0:{
                message = "ssssssssssssssss";
                //log

                break;
            }
            case NATIONAL_CODE_NOT_TRUE:{
                message = "sajjad Error : National Code Not true.";
                break;
            }

            default:{
                message = "sajjad Error";
            }
        }
        code = errorCode;
    }

    public void handleServerMessage(Context mContext, View rootView, ServerResponseBase responseX) {
        try {
            assert rootView == null;
            assert mContext == null;

            int resID = mContext.getResources().getIdentifier("error_message_" + (responseX.getTubelessException().getCode() * -1), "string", mContext.getPackageName());
            if (resID == 0) {
                //Snackbar.make(rootView, mContext.getString(R.string.error) + responseX.getTubelessException().getCode(), Snackbar.LENGTH_SHORT).show();

                View.OnClickListener snackOnClickListener = new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Toast.makeText(mContext,"ssss",Toast.LENGTH_SHORT).show();
                    }
                };
                Snackbar
                        .make(rootView, "یک خطای پیش بینی نشده", 8000)
                        //.setAction("Yes", snackOnClickListener)
                        .setActionTextColor(Color.MAGENTA)
                        .show();
            }else {
                Snackbar.make(rootView, resID, Snackbar.LENGTH_SHORT).show();

            }

        }catch (Exception e){
            e.printStackTrace();
        }

        if (responseX != null) {
            handleServerMessage(mContext,rootView,responseX.getTubelessException().getCode());
        }else {
//            Toast.makeText(context, context.getResources().getString(R.string.server_not_response), Toast.LENGTH_SHORT).show();

            if (rootView != null) {
                Snackbar.make(rootView, mContext.getResources().getString(R.string.server_not_response), Snackbar.LENGTH_SHORT).show();
            }
        }
    }

    public static void handleServerMessage(Context mContext, View view, int code) {
        try {
            int resID = mContext.getResources().getIdentifier("error_message_" + code, "string", mContext.getPackageName());
            Snackbar.make(view, resID, Snackbar.LENGTH_SHORT).show();

        }catch (Exception e){
            e.printStackTrace();
        }
    }


    public static void handleClientMessage(Context mContext, View view, int code) {
//        if (mContext != null) {
//            SamanToast samanToast = null;
//            samanToast = new SamanToast(mContext,mContext.getString(R.string.error_text_entered_valuse_not_valid), SamanToast.ERROR);
//            samanToast.show();
//        }

        try {
            int resID = mContext.getResources().getIdentifier("error_message_" + code, "string", mContext.getPackageName());
            Snackbar.make(view, resID, Snackbar.LENGTH_SHORT).show();

        }catch (Exception e){
            e.printStackTrace();
        }
    }


//    public void handleMessage(Context mContext, ServerResponse response) {
//        try {
//            int resID = mContext.getResources().getIdentifier("error_message_" + response.getException().getCode() , "string", mContext.getPackageName());
//            Toast.makeText(mContext, resID, Toast.LENGTH_SHORT).show();
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//    }

    public static void ShowSheetDialog(Context context, BottomSheetDialog dialog, View.OnClickListener onClickListener) {
        try {
            View view = ((Activity) context).getLayoutInflater().inflate(R.layout.bottom_sheet_dialog_connection_lost, null);
            dialog.setContentView(view);
            Button buttonTryAgain = view.findViewById(R.id.buttonTryAgain);
            buttonTryAgain.setOnClickListener(onClickListener);
            dialog.show();
        }catch (Exception ex){

        }
    }

    public static void ShowSheetDialogMessage(Context context, BottomSheetDialog dialog, String message , View.OnClickListener onClickListener) {
        try {
            View view = ((Activity) context).getLayoutInflater().inflate(R.layout.bottom_sheet_dialog_connection_lost, null);
            dialog.setContentView(view);
            Button buttonTryAgain = view.findViewById(R.id.buttonTryAgain);
            TextView buttonMessage = view.findViewById(R.id.textView);
            buttonMessage.setText(message);
            buttonTryAgain.setOnClickListener(onClickListener);
            dialog.show();
        }catch (Exception ex){

        }
    }
    public static void ShowSheetDialogMessage(Context context, BottomSheetDialog dialog, String message , String ok, View.OnClickListener onClickListener) {
        try {
            View view = ((Activity) context).getLayoutInflater().inflate(R.layout.bottom_sheet_dialog_connection_lost, null);
            dialog.setContentView(view);
            Button buttonTryAgain = view.findViewById(R.id.buttonTryAgain);
            TextView buttonMessage = view.findViewById(R.id.textView);
            buttonMessage.setText(message);
            buttonTryAgain.setText(ok);
            buttonTryAgain.setOnClickListener(onClickListener);
            dialog.show();
        }catch (Exception ex){

        }

    }


}