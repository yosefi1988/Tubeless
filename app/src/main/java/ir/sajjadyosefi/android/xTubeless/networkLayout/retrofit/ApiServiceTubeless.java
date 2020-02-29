package ir.sajjadyosefi.android.xTubeless.networkLayout.retrofit;

import android.graphics.Movie;

import java.util.List;


//import ir.sajjadyosefi.android.xTubeless.classes.model.request.NewBlogRequest;
//import ir.sajjadyosefi.android.xTubeless.classes.model.request.DeviceRequest;
import ir.sajjadyosefi.android.xTubeless.classes.model.network.request.accounting.LoginRequest;
//import ir.sajjadyosefi.android.xTubeless.classes.modelY.request.common.ContactUsRequest;
import ir.sajjadyosefi.android.xTubeless.classes.model.request.DeviceRequest;
import ir.sajjadyosefi.android.xTubeless.classes.model.request.NewBlogRequest;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;

/**
 * Created by sajjad on 11/7/2018.
 */

public interface ApiServiceTubeless {


    @GET("Api/TimeLine/getTubelessProjectLastTimelineNews")
    Call<Object> getTubelessNews(@Query("id") int projectId,
                                 @Query("index") int index,
                                 @Query("count") int count);

    @POST("Api/Device/deviceRegister")
    Call<Object> deviceRegister(@Body DeviceRequest request);

    @POST("Api/User/Login")
    Call<Object> login(@Body LoginRequest request);

    @POST("Api/Blog/SaveNewBlog")
    Call<Object> newYafte(@Body NewBlogRequest request);

    @GET("Api/TimeLine/getTubelessTimeline")
    Call<Object> getYafteTimeline(@Query("index") int index,
                                  @Query("count") int count);

    @GET("Api/TimeLine/getTubelessYadakTimeline")
    Call<Object> getTimelineYadak(@Query("index") int index,
                                  @Query("count") int count);

    @GET("Api/Timeline/getTubelessTimelineItem")
    Call<Object> getTimelineItem(@Query("id") int id);







//    @GET("Api/TimeLine/getYadakTimeline")
//    Call<Object> getYadakTimeline(@Query("index") int index,
//                                  @Query("count") int count);




















//    @POST("Api/Common/ContactUs")
//    Call<Object> contactUs(@Body ContactUsRequest request);

//    @GET("questions")
//    Call<Question> getQuestionsService(@Query("page") int page,
//                                       @Query("pagesize") int pagesize,
//                                       @Query("order") String order,
//                                       @Query("sort") String sort,
//                                       @Query("tagged") String tagged,
//                                       @Query("site") String site);

    @GET("movies.json")
    Call<List<Movie>> getMoviesService();

//    @POST("/data/2.1")
//    Call < T > postMovieDetails(
//            @Field("userId") String userID,
//            @Field("token") String token);







    @GET("Api/Blog/deleteBlog")
    Call<Object> deleteTimelineItem(@Query("id") int id,
                                    @Query("userId") String userId);

    @GET("Api/Blog/invisibleBlog")
    Call<Object> invisibleTimelineItem(@Query("id") int id,
                                       @Query("userId") String userId);



    @Multipart
    @POST("api/UploadFileFormAndroid")
    Call<Object> upload2(
            @Part MultipartBody.Part file,
            @Part("userName") RequestBody userName,
            @Part("password") RequestBody password,
            @Part("androidId") RequestBody androidId,
            @Part("serialRequestCode") RequestBody serialRequestCode,
            @Part("fileType") RequestBody fileType,
            @Part("senderType") RequestBody senderType);

    @Multipart
    @POST("api/UploadFileFormAndroid")
//    @Headers("Content-Type: application/json")
    @Headers({"Content-Type: multipart/form-data",
            "Accept: application/json",
            "Accept-Encoding: gzip, deflate"})

    Call<Object> upload(
            @Part MultipartBody.Part file,
            @Part("userName") RequestBody userName,
            @Part("password") RequestBody password,
            @Part("androidId") RequestBody androidId,
            @Part("serialRequestCode") RequestBody serialRequestCode,
            @Part("fileType") RequestBody fileType,
            @Part("senderType") RequestBody senderType);



}
