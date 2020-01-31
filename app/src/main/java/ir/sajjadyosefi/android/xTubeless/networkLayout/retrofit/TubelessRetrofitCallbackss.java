package ir.sajjadyosefi.android.xTubeless.networkLayout.retrofit;

import android.content.Context;
import android.os.Handler;
import android.view.View;
import android.widget.ProgressBar;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.internal.Primitives;

import java.lang.reflect.Type;

import ir.sajjadyosefi.android.xTubeless.classes.modelY.Exception.TubelessException;
import ir.sajjadyosefi.android.xTubeless.classes.modelY.responses.basic.ServerResponseBase;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public abstract class TubelessRetrofitCallbackss implements Callback ,ICallback{

    private final Class<?> aClass;
    private Context mContext;
    public TubelessRetrofitCallbackss(Context context, Class<?> loginResponseClass) {
        this.mContext = context;
        this.aClass = loginResponseClass;
        t_beforeSendRequest();
    }

    public abstract void t_beforeSendRequest();

    public abstract void t_afterGetResponse();

    public abstract void t_complite();

    public abstract void t_responseNull();

    public abstract void t_retry(Call<Object> call);


    @Override
    public void onResponse(Call call, Response response) {
        t_afterGetResponse();

        Gson gson = new Gson();
        JsonElement jsonElement = gson.toJsonTree(response.body());
        ServerResponseBase responseX = null;


        try {
            if (response.body() == null){
                //throw new Exception();
                t_responseNull();
            }

            responseX = gson.fromJson(jsonElement.getAsString(), ServerResponseBase.class);
            if (response.body() != null ) {
                if (responseX.getTubelessException().getCode() != 0) {
                    if (responseX.getTubelessException().getCode() > 0) {
                        if (call != null && response != null) {

                            Object object = gson.fromJson(jsonElement.getAsString(), (Type) aClass);
                            Object xxxxxxxx = Primitives.wrap(aClass).cast(object);
                            t_onSuccess(xxxxxxxx);
                        }
                    } else {
                        throw responseX.getTubelessException();
                    }
                }else {
                    throw responseX.getTubelessException();
                }
            }else {
                throw responseX.getTubelessException();
            }

            t_complite();
        } catch (TubelessException sException) {
            sException.printStackTrace();
//            if (showResult)
//                sException.handleServerMessage(mContext,rootView,responseX);
        }catch (Exception sException) {
            sException.printStackTrace();
        }

    }

    @Override
    public void onFailure(Call call, Throwable t) {
        int a =0 ;
        a++;

        try {
            showConnectionLostDialog(mContext, null , new Runnable() {
                @Override
                public void run() {
                    retry(call);
                }
            });
        }catch (Exception ex){
            int aX =0 ;
            aX  ++;

        }
    }

    private void retry(Call<java.lang.Object> call) {
        t_retry(call);
        call.clone().enqueue(this);
    }

    public static void showConnectionLostDialog(Context context, final ProgressBar progressBar, final Runnable runnable) {
        final BottomSheetDialog dialog = new BottomSheetDialog(context);
        TubelessException.ShowSheetDialog(context,dialog ,new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Handler().postDelayed(runnable,5);
                dialog.dismiss();
                if (progressBar != null)
                    progressBar.setVisibility(View.VISIBLE);
            }
        });
    }

}
