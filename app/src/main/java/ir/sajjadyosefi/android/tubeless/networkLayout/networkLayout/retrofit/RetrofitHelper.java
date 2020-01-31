package ir.sajjadyosefi.android.tubeless.networkLayout.networkLayout.retrofit;



import ir.sajjadyosefi.android.xTubeless.classes.modelY.yafte.search.Search;
import ir.sajjadyosefi.android.xTubeless.classes.modelY.yafte.search.SearchResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by sajjad on 11/7/2018.
 */

public class RetrofitHelper {

    private static APIService service;
    private static RetrofitHelper apiManager;

    private RetrofitHelper() {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://postyafteh.post.ir/r/Handlers/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        service = retrofit.create(APIService.class);
    }

    public static RetrofitHelper getInstance() {
        if (apiManager == null) {
            apiManager = new RetrofitHelper();
        }
        return apiManager;
    }

    public void createUser(Search search, Callback<SearchResponse> callback) {
        Call<SearchResponse> userCall = service.search(search);
        userCall.enqueue(callback);
    }
}
