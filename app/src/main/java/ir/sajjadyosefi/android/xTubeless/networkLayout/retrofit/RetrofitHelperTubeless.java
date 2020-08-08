package ir.sajjadyosefi.android.xTubeless.networkLayout.retrofit;


import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;

import ir.sajjadyosefi.android.xTubeless.R;
import ir.sajjadyosefi.android.xTubeless.classes.StaticValue;
import ir.sajjadyosefi.android.xTubeless.classes.model.request.NewBlogCommentRequest;
import ir.sajjadyosefi.android.xTubeless.classes.model.request.NewVoteRequest;
import ir.sajjadyosefi.android.xTubeless.networkLayout.retrofit.DownloadUploadPicture.ImageRequest;
import ir.sajjadyosefi.android.xTubeless.networkLayout.retrofit.DownloadUploadPicture.RemoteApi;
import ir.sajjadyosefi.android.xTubeless.classes.model.config.Configuration;
import ir.sajjadyosefi.android.xTubeless.classes.model.request.ContactUsRequest;
import ir.sajjadyosefi.android.xTubeless.classes.model.request.NewBlogRequest;
import ir.sajjadyosefi.android.xTubeless.classes.model.request.DeviceRequest;
import ir.sajjadyosefi.android.xTubeless.classes.model.network.request.accounting.LoginRequest;
import ir.sajjadyosefi.android.xTubeless.utility.DeviceUtil;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Query;

import static ir.sajjadyosefi.android.xTubeless.classes.StaticValue.IDApplication;
import static ir.sajjadyosefi.android.xTubeless.networkLayout.networkLayout.Url.REST_API_IP_ADDRESS_MAIN;

public class RetrofitHelperTubeless {
    private static ApiServiceTubeless service;
    private static RetrofitHelperTubeless apiManager;

    private RetrofitHelperTubeless() {

        HostSelectionInterceptor interceptor = new HostSelectionInterceptor();
        //HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();

        OkHttpClient client = new OkHttpClient().newBuilder()
                .addInterceptor(interceptor)
                .build();

        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(REST_API_IP_ADDRESS_MAIN)
                .addConverterFactory(GsonConverterFactory.create())
                //.addConverterFactory(GsonConverterFactory.create(gson))
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

    public void getProfileImages(LoginRequest request, Callback callback) {
        Call<Object> userCall = service.profileImages(request);
        userCall.enqueue(callback);
    }

    private String TAG ="file";
    public void getImageFromRemoteServer(Context context , ImageRequest request, Callback callback) {
        RemoteApi api = RemoteApi.Factory.create();
        ImageRequest.DEFAULT_BODY.setAndroidId(DeviceUtil.GetAndroidId(context));
        api.getImage(ImageRequest.DEFAULT_BODY).enqueue(callback);

    }


    public void getTimelineListForYadak(Context context , int index, TubelessRetrofitCallbackss callback) {
//        Call<Object> userCall = service.getTimelineYadak(index,15);
//        userCall.enqueue(callback);

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(context.getResources().getInteger(R.integer.cat1Yadak));
        stringBuilder.append("_");
        stringBuilder.append(context.getResources().getInteger(R.integer.cat2Yadak));
        stringBuilder.append("_");
        stringBuilder.append(context.getResources().getInteger(R.integer.cat3Yadak));

        Call<Object> userCall = service.getTimeline(index,15,stringBuilder.toString(),null);
        userCall.enqueue(callback);

    }

    public void getTimelineListForYafte(Context context , int index, TubelessRetrofitCallbackss callback) {
//        Call<Object> userCall = service.getYafteTimeline(index,15);
//        userCall.enqueue(callback);

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(context.getResources().getInteger(R.integer.cat1Yafte));
        stringBuilder.append("_");
        stringBuilder.append(context.getResources().getInteger(R.integer.cat2Yafte));
        stringBuilder.append("_");
        stringBuilder.append(context.getResources().getInteger(R.integer.cat3Yafte));

        Call<Object> userCall = service.getTimeline(index,15,stringBuilder.toString(),null);
        userCall.enqueue(callback);
    }

    public void getTimelineListForBourseTrain(Context context , int index, TubelessRetrofitCallbackss callback) {
//        Call<Object> userCall = service.getYafteTimeline(index,15);
//        userCall.enqueue(callback);

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(context.getResources().getInteger(R.integer.cat1BourseTrain));
        stringBuilder.append("_");
        stringBuilder.append(context.getResources().getInteger(R.integer.cat2BourseTrain));
        stringBuilder.append("_");
        stringBuilder.append(context.getResources().getInteger(R.integer.cat3BourseTrain));

        Call<Object> userCall = service.getTimeline(index,15,stringBuilder.toString(),null);
        userCall.enqueue(callback);
    }
    public void getTimelineListForBourseAnalize(Context context , int index, TubelessRetrofitCallbackss callback) {
        //1
//        Call<Object> userCall = service.getYafteTimeline(index,15);
//        userCall.enqueue(callback);

        //2
//        StringBuilder stringBuilder = new StringBuilder();
//        stringBuilder.append(context.getResources().getInteger(R.integer.cat1BourseAnalize));
//        stringBuilder.append("_");
//        stringBuilder.append(context.getResources().getInteger(R.integer.cat2BourseAnalize));
//        stringBuilder.append("_");
//        stringBuilder.append(context.getResources().getInteger(R.integer.cat3BourseAnalize));
//        Call<Object> userCall = service.getTimeline(index,15,stringBuilder.toString(),null);


        StringBuilder stringBuilder = new StringBuilder();
        switch (StaticValue.bourseState.lastPayedType){
            case 0:
            case 1:
                stringBuilder.append(context.getResources().getInteger(R.integer.cat1BourseAnalize));
                break;
            case 2:
                stringBuilder.append(context.getResources().getInteger(R.integer.cat2BourseAnalize));
                break;
            case 3:
                stringBuilder.append(context.getResources().getInteger(R.integer.cat3BourseAnalize));
        }
        Call<Object> userCall = service.getTimeline(index,15,stringBuilder.toString(),null);
        userCall.enqueue(callback);
    }
    public void getTimelineListForBourseAnalizeOld(Context context, int index, TubelessRetrofitCallbackss callback, String endDate) {
        //1
//        Call<Object> userCall = service.getYafteTimeline(index,15);
//        userCall.enqueue(callback);
//
        //2
//        StringBuilder stringBuilder = new StringBuilder();
//        stringBuilder.append(context.getResources().getInteger(R.integer.cat1BourseAnalize));
//        stringBuilder.append("_");
//        stringBuilder.append(context.getResources().getInteger(R.integer.cat2BourseAnalize));
//        stringBuilder.append("_");
//        stringBuilder.append(context.getResources().getInteger(R.integer.cat3BourseAnalize));

        StringBuilder stringBuilder = new StringBuilder();
        switch (StaticValue.bourseState.lastPayedType){
            case 1:
            case 0:
                stringBuilder.append(context.getResources().getInteger(R.integer.cat1BourseAnalize));
                break;
            case 2:
                stringBuilder.append(context.getResources().getInteger(R.integer.cat2BourseAnalize));
                break;
            case 3:
                stringBuilder.append(context.getResources().getInteger(R.integer.cat3BourseAnalize));
        }

        //todo Bourse uncomment
        Call<Object> userCall = service.getTimeline(index,15,stringBuilder.toString(),StaticValue.bourseState.endDate);
        userCall.enqueue(callback);
    }

    public void getTimelineListForBourseNews(Context context , int index, TubelessRetrofitCallbackss callback) {
//        Call<Object> userCall = service.getYafteTimeline(index,15);
//        userCall.enqueue(callback);

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(context.getResources().getInteger(R.integer.cat1BourseNews));
        stringBuilder.append("_");
        stringBuilder.append(context.getResources().getInteger(R.integer.cat2BourseNews));
        stringBuilder.append("_");
        stringBuilder.append(context.getResources().getInteger(R.integer.cat3BourseNews));

        Call<Object> userCall = service.getTimeline(index,15,stringBuilder.toString(),null);
        userCall.enqueue(callback);
    }


    public void registerNewYafte(NewBlogRequest request, TubelessRetrofitCallbackss callback) {
        Call<Object> userCall = service.newYafte(request);
        userCall.enqueue(callback);
    }

    //------------ comments ---------------
    public void getCommentTimeline(int blogId,int index, TubelessRetrofitCallbackss callback) {
        Call<Object> userCall = service.getCommentsOfblog(blogId,index,15);
        userCall.enqueue(callback);
    }

    public void registerNewComment(NewBlogCommentRequest request, TubelessRetrofitCallbackss callback) {
        Call<Object> userCall = service.newBlogComment(request);
        userCall.enqueue(callback);
    }
    public void voteBlogComment(NewVoteRequest request, TubelessRetrofitCallbackss callback) {
        Call<Object> userCall = service.voteBlogComment(request);
        userCall.enqueue(callback);
    }
    public void deleteBlogComment(int id,String userId, TubelessRetrofitCallbackss callback) {
        Call<Object> userCall = service.deleteBlogComment(id,userId);

//        Gson gson = new Gson();
//        String aaaaaaaaaaaaaaaaa = gson.toJson(request);

        userCall.enqueue(callback);
    }
    public void invisibleBlogComment(int id,String userId,  TubelessRetrofitCallbackss callback) {
        Call<Object> userCall = service.invisibleBlogComment(id,userId);
        userCall.enqueue(callback);
    }

    //------------ end comments ---------------

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
