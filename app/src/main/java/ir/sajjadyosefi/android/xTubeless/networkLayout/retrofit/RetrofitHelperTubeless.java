package ir.sajjadyosefi.android.xTubeless.networkLayout.retrofit;


import android.app.Dialog;

import java.io.IOException;

import ir.sajjadyosefi.android.xTubeless.Global;
import ir.sajjadyosefi.android.xTubeless.classes.model.config.Configuration;
import ir.sajjadyosefi.android.xTubeless.classes.model.request.ContactUsRequest;
import ir.sajjadyosefi.android.xTubeless.classes.model.request.NewBlogRequest;
import ir.sajjadyosefi.android.xTubeless.classes.model.request.DeviceRequest;
import ir.sajjadyosefi.android.xTubeless.classes.model.network.request.accounting.LoginRequest;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static ir.sajjadyosefi.android.xTubeless.classes.StaticValue.IDApplication;
import static ir.sajjadyosefi.android.xTubeless.networkLayout.networkLayout.Url.REST_API_IP_ADDRESS2;
import static ir.sajjadyosefi.android.xTubeless.networkLayout.networkLayout.Url.REST_API_IP_ADDRESS_MAIN;

public class RetrofitHelperTubeless {
    private static ApiServiceTubeless service;
    private static RetrofitHelperTubeless apiManager;

    private RetrofitHelperTubeless() {

        HostSelectionInterceptor interceptor = new HostSelectionInterceptor();

        OkHttpClient client =
                new OkHttpClient().newBuilder()
                        .addInterceptor(interceptor)
                        .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(REST_API_IP_ADDRESS_MAIN)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        service = retrofit.create(ApiServiceTubeless.class);
    }

    public static RetrofitHelperTubeless getInstance() {
        if (apiManager == null) {
            apiManager = new RetrofitHelperTubeless();
        }
        return apiManager;
    }

    public void loginOrRregister(LoginRequest request, TubelessRetrofitCallbackss callback) {
        Call<Object> userCall = service.login(request);
        userCall.enqueue(callback);
    }

    public void loginOrRregisterMVP(LoginRequest request, Callback callback) {
        Call<Object> userCall = service.login(request);
        userCall.enqueue(callback);
    }

    public void getTimelineYadak(int index, TubelessRetrofitCallbackss callback) {
        Call<Object> userCall = service.getTimelineYadak(index,15);
        userCall.enqueue(callback);
    }

    public void getYafteTimeline(int index, TubelessRetrofitCallbackss callback) {
        Call<Object> userCall = service.getYafteTimeline(index,15);
        userCall.enqueue(callback);
    }

    public void registerNewYafte(NewBlogRequest request, TubelessRetrofitCallbackss callback) {
        Call<Object> userCall = service.newYafte(request);
        userCall.enqueue(callback);
    }

    public void getTimelineItem(int id, TubelessRetrofitCallbackss callback) {
        Call<Object> userCall = service.getTimelineItem(id);
        userCall.enqueue(callback);
    }


    public void getTubelessNews(TubelessRetrofitCallbackss callback) {
        Call<Object> userCall = service.getTubelessNews(IDApplication,0,1);
        userCall.enqueue(callback);
    }

    public void deviceRregister(DeviceRequest request, TubelessRetrofitCallbackss callback) {
        Call<Object> userCall = service.deviceRegister(request);
        userCall.enqueue(callback);
    }

    public void deleteTimelineItem(int id, String userId, TubelessRetrofitCallbackss callback) {
        Call<Object> userCall = service.deleteTimelineItem(id,userId);
        userCall.enqueue(callback);
    }

    public void invisibleTimelineItem(int id, String userId, TubelessRetrofitCallbackss callback) {
        Call<Object> userCall = service.invisibleTimelineItem(id,userId);
        userCall.enqueue(callback);
    }

    public void newContactUs(ContactUsRequest request, TubelessRetrofitCallbackss callback) {
        Call<Object> userCall = service.contactUs(request);
        userCall.enqueue(callback);
    }

    public void config(Callback<Configuration> callback) {
        Call<Configuration> userCall = service.config();
        userCall.enqueue(callback);
    }



















//    public void createUser(SearchRequest searchRequest, Callback<ServerResponse> callback) {
//        Call<ServerResponse> userCall = service.search(searchRequest);
//        userCall.enqueue(callback);
//    }.



//    public void requestUpload2(MultipartBody.Part file, RequestBody userName, RequestBody password, RequestBody androidId , RequestBody serialRequestCode , RequestBody fileType , RequestBody senderType, TubelessRetrofitCallback<Object> callback) {
//        Call<Object> userCall = service.upload2(file , userName,password,androidId, serialRequestCode , fileType , senderType);
//        userCall.enqueue(callback);
//    }




    public static final class HostSelectionInterceptor implements Interceptor {
        private volatile String host;


        @Override
        public okhttp3.Response intercept(Interceptor.Chain chain) throws IOException {
            Request request = chain.request();
            String host = this.host;
            if (host != null) {
                //HttpUrl newUrl = request.url().newBuilder()
                //    .host(host)
                //    .build();
                HttpUrl newUrl = HttpUrl.parse(host);
                request = request.newBuilder()
                        .url(newUrl)
                        .build();
            }

//            SystemClock.sleep(1000 * 10);

            return chain.proceed(request);
        }
    }
}
