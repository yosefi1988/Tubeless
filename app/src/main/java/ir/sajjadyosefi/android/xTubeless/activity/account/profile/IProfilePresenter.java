package ir.sajjadyosefi.android.xTubeless.activity.account.profile;

import android.net.Uri;

import java.io.File;

public interface IProfilePresenter {

    void cameraClick();

    void chooseGalleryClick();

    void saveImage(String filePath);

    String getImage();

    void showPreview(File mFile);

}
