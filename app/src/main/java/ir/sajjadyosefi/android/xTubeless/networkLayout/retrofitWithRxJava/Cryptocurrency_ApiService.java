package ir.sajjadyosefi.android.xTubeless.networkLayout.retrofitWithRxJava;

import android.graphics.Movie;

import java.util.List;

import io.reactivex.Observable;
import ir.sajjadyosefi.android.xTubeless.classes.model.config.Configuration;
import ir.sajjadyosefi.android.xTubeless.classes.model.crypto.Crypto;
import ir.sajjadyosefi.android.xTubeless.classes.model.network.request.accounting.LoginRequest;
import ir.sajjadyosefi.android.xTubeless.classes.model.request.ContactUsRequest;
import ir.sajjadyosefi.android.xTubeless.classes.model.request.DeviceRequest;
import ir.sajjadyosefi.android.xTubeless.classes.model.request.NewBlogRequest;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;


public interface Cryptocurrency_ApiService {

    //1
    String BASE_URL = "https://api.cryptonator.com/api/full/";

    @GET("{coin}-usd")
    Observable<Crypto> getCoinData(@Path("coin") String coin);



}
