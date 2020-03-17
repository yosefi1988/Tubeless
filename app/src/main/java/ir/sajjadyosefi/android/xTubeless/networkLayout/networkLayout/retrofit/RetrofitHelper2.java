package ir.sajjadyosefi.android.xTubeless.networkLayout.networkLayout.retrofit;

import android.content.Context;

import java.io.IOException;
import java.net.URL;

import ir.sajjadyosefi.android.xTubeless.classes.model.config.Configuration;
import okhttp3.Dispatcher;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static ir.sajjadyosefi.android.xTubeless.networkLayout.networkLayout.Url.REST_API_IP_ADDRESS2;


/**
 * Created by sajjad on 11/7/2018.
 */

public class RetrofitHelper2 {

    private static samaniumRestApi service;
    private static RetrofitHelper2 retrofitHelper2;

    private RetrofitHelper2() {
        try {
            URL url = new URL(REST_API_IP_ADDRESS2);
            String serverHostname = url.getHost();
            HostSelectionInterceptor interceptor = new HostSelectionInterceptor();

            Dispatcher dispatcher = new Dispatcher();
            dispatcher.setMaxRequests(1);

            // OkHttp 3
            OkHttpClient client =
                    new OkHttpClient().newBuilder()
                            .addInterceptor(interceptor)
                            .build();


            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(REST_API_IP_ADDRESS2)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(client)
                    .build();

            service = retrofit.create(samaniumRestApi.class);

            int a = 4 ;
            a++;
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static RetrofitHelper2 getInstance(Context mContext) {
        if (retrofitHelper2 == null) {
            retrofitHelper2 = new RetrofitHelper2();
        }
        return retrofitHelper2;
    }

    public static final class HostSelectionInterceptor implements Interceptor {
        private volatile String host;

        public void setHost(String host) {
            this.host = host;
        }

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

    public void config(Callback<Configuration> callback) {
        Call<Configuration> userCall = service.config();
        userCall.enqueue(callback);
    }



}
