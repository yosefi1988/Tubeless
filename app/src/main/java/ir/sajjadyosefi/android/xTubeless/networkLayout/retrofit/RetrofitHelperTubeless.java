package ir.sajjadyosefi.android.xTubeless.networkLayout.retrofit;


import ir.sajjadyosefi.android.xTubeless.Global;
import ir.sajjadyosefi.android.xTubeless.classes.model.request.ContactUsRequest;
import ir.sajjadyosefi.android.xTubeless.classes.model.request.NewBlogRequest;
import ir.sajjadyosefi.android.xTubeless.classes.model.request.DeviceRequest;
import ir.sajjadyosefi.android.xTubeless.classes.model.network.request.accounting.LoginRequest;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static ir.sajjadyosefi.android.xTubeless.classes.StaticValue.IDApplication;

public class RetrofitHelperTubeless {
    private static ApiServiceTubeless service;
    private static RetrofitHelperTubeless apiManager;

    private RetrofitHelperTubeless() {
        Retrofit retrofit = new Retrofit.Builder()
//                .baseUrl("http://10.0.2.2:3000/")
                .baseUrl("http://test.sajjadyosefi.ir/")
//                .baseUrl("http://192.168.1.5/")
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




















//    public void createUser(SearchRequest searchRequest, Callback<ServerResponse> callback) {
//        Call<ServerResponse> userCall = service.search(searchRequest);
//        userCall.enqueue(callback);
//    }.



//    public void requestUpload2(MultipartBody.Part file, RequestBody userName, RequestBody password, RequestBody androidId , RequestBody serialRequestCode , RequestBody fileType , RequestBody senderType, TubelessRetrofitCallback<Object> callback) {
//        Call<Object> userCall = service.upload2(file , userName,password,androidId, serialRequestCode , fileType , senderType);
//        userCall.enqueue(callback);
//    }

}
