package ir.sajjadyosefi.android.xTubeless.activity.account.profile;

import io.reactivex.Flowable;
import io.reactivex.Single;
import okhttp3.ResponseBody;

public interface IFileUploadModel {

    Flowable<Double> uploadFile(String selectedFilePath, String userId, String type);

    Single<ResponseBody> uploadFileWithoutProgress(String filePath, String userId, String type);
}
