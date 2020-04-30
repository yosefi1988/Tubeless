package ir.sajjadyosefi.android.xTubeless.activity.account.profile;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.FileProvider;
import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.annotations.Nullable;
import ir.sajjadyosefi.android.xTubeless.BuildConfig;
import ir.sajjadyosefi.android.xTubeless.R;
import ir.sajjadyosefi.android.xTubeless.utility.DialogUtil;
import ir.sajjadyosefi.android.xTubeless.utility.xUtility.AndroidOs;

import static ir.sajjadyosefi.android.xTubeless.utility.DialogUtil.SelectSource;

public class MainActivityProfile extends AppCompatActivity implements IProfileView, IFileUploadView {

    public static final int REQUEST_TAKE_PHOTO = 1001;
    public static final int PERMISSION_REQUEST_TAKE_PHOTO = 10010;
    public static final int REQUEST_GALLERY_PHOTO = 1002;
    public static final int PERMISSION_REQUEST_GALLERY_PHOTO = 10020;
//    static String[] permissions = new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA};

    Context context;


    @BindView(R.id.header_cover_image)
    ImageView headerCoverImage;
    @BindView(R.id.user_profile_photo)
    ImageButton userProfilePhoto;
    @BindView(R.id.user_profile_name)
//    TextView userProfileName;
//    @BindView(R.id.user_profile_short_bio)
    TextView userProfileShortBio;
//    @BindView(R.id.profile_layout)
//    RelativeLayout profileLayout;
    @BindView(R.id.textViewProgress)
    TextView txtProgress;
    @BindView(R.id.upload_file_progress)
    Button uploadFileProgress;
    @BindView(R.id.btn_upload_file_without_progress)
    Button btnUploadFileWithoutProgress;

    private ImagePresenter mImagePresenter;
    private FileUploaderPresenter mUploaderPresenter;

    File mPhotoFile;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_main);
        ButterKnife.bind(this);
        context = this;
        mImagePresenter = new ImagePresenter(this);


    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == REQUEST_TAKE_PHOTO) {
//                try {
//                    File resultedFile = mCompressor.compressToFile(mPhotoFile);
//                    mImagePresenter.saveImage(resultedFile.getPath());
//                    mImagePresenter.showPreview(resultedFile);
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
            } else if (requestCode == REQUEST_GALLERY_PHOTO) {
                Uri selectedImage = data.getData();
//                try {
//                    File resultedFile = mCompressor.compressToFile(new File(Objects.requireNonNull(getRealPathFromUri(selectedImage))));
//                    mImagePresenter.saveImage(resultedFile.getPath());
//                    mImagePresenter.showPreview(resultedFile);
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }

            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        if (grantResults.length > 0){
            switch (requestCode) {
                case PERMISSION_REQUEST_GALLERY_PHOTO:
                    if(grantResults[0] == PackageManager.PERMISSION_GRANTED){
                        //GRANTED
                        mImagePresenter.chooseGalleryClick();
                    }else {
                        //DENIED
                        showSettingsDialog();
                    }
                    break;
                case PERMISSION_REQUEST_TAKE_PHOTO:
                    if(grantResults[0] == PackageManager.PERMISSION_GRANTED){
                        //GRANTED
                        mImagePresenter.cameraClick();
                    }else {
                        //DENIED
                        showSettingsDialog();
                    }
                    break;
                default:
                    break;
            }
        }else {


        }
    }

    @OnClick({R.id.user_profile_photo, R.id.upload_file_progress, R.id.btn_upload_file_without_progress})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.user_profile_photo:
                selectImage(this);
                break;
            case R.id.upload_file_progress:
//                mUploaderPresenter.onFileSelected(mImagePresenter.getImage(), "49", "avatar");
                break;
            case R.id.btn_upload_file_without_progress:
//                CommonUtils.showLoadingDialog(this);
//                mUploaderPresenter.onFileSelectedWithoutShowProgress(mImagePresenter.getImage(), "androidwave", "info@androidwave");
                break;
        }
    }


    private void selectImage(Context context) {
        txtProgress.setText("");
//        final CharSequence[] items = {getString(R.string.camera), getString(R.string.gallery),
//                getString(R.string.cancel)};
//        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivityProfile.this);
//        builder.setItems(items, (dialog, item) -> {
//            if (items[item].equals("Capture Photo")) {
//                mImagePresenter.cameraClick();
//            } else if (items[item].equals("Choose from Library")) {
//                mImagePresenter.chooseGalleryClick();
//            } else if (items[item].equals("Cancel")) {
//                dialog.dismiss();
//            }
//        });
//        builder.show();
        SelectSource(context,findViewById(android.R.id.content) , mImagePresenter);
    }


    @Override
    public void showErrorMessage(String message) {

    }

    @Override
    public void uploadCompleted() {

    }

    @Override
    public void setUploadProgress(int progress) {

    }

    @Override
    public boolean checkPermission(String permisson) {
        return AndroidOs.checkPermission(context , permisson);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void showPermissionDialog(boolean isGallery) {
//        //not Work
//        ActivityCompat.requestPermissions(
//                ((Activity)context),
//                permissions,
//                PERMISSION_REQUEST_GALLERY_PHOTO);
//
//        //Work OK
//        ActivityCompat.requestPermissions(this, new String[]
//                {
//                        CAMERA,
//                        RECORD_AUDIO,
//                        SEND_SMS,
//                        GET_ACCOUNTS
//                }, PERMISSION_REQUEST_GALLERY_PHOTO);
//
//    <uses-permission android:name="android.permission.READ_CONTACTS" />
//    <uses-permission android:name="android.permission.WRITE_CONTACTS" />
//    <uses-permission android:name="android.permission.CAMERA"/>
//    <uses-permission android:name="android.permission.RECORD_AUDIO"/>
//    <uses-permission android:name="android.permission.SEND_SMS"/>

        DialogUtil.ShowMessageDialog(
            context,
            context.getString(R.string.permission_title),
            (isGallery) ? context.getString(R.string.permission_gallery_text):context.getString(R.string.permission_camera_text)
            ,isGallery);
    }

    @Override
    public File getFilePath() {
        return getExternalFilesDir(Environment.DIRECTORY_PICTURES);
    }

    @Override
    public void openSettings() {

    }

    @Override
    public void startCamera(File file) {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            if (file != null) {
                Uri mPhotoURI = FileProvider.getUriForFile(this,
                        BuildConfig.APPLICATION_ID + ".provider", file);
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, mPhotoURI);
                mPhotoFile = file;
                startActivityForResult(takePictureIntent, REQUEST_TAKE_PHOTO);

            }
        }
    }

    @Override
    public void chooseGallery() {
        Intent pickPhoto = new Intent(Intent.ACTION_PICK,MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        pickPhoto.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        startActivityForResult(pickPhoto, REQUEST_GALLERY_PHOTO);
    }

    @Override
    public File newFile() {
        Calendar cal = Calendar.getInstance();
        long timeInMillis = cal.getTimeInMillis();
        String mFileName = String.valueOf(timeInMillis) + ".jpeg";
        File mFilePath = getFilePath();
        try {
            File newFile = new File(mFilePath.getAbsolutePath(), mFileName);
            newFile.createNewFile();
            return newFile;

        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public void showErrorDialog() {
        Toast.makeText(getApplicationContext(), getString(R.string.error_message), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void displayImagePreview(File mFile) {

    }

    @Override
    public String getRealPathFromUri(Uri contentUri) {
        return null;
    }

    public void showSettingsDialog() {
        DialogUtil.ShowMessageDialog(context,context.getString(R.string.permission_title),context.getString(R.string.WeNeedYourDeviceInfo));


//        AlertDialog.Builder builder = new AlertDialog.Builder(this);
//        builder.setTitle(getString(R.string.message_need_permission));
//        builder.setMessage(getString(R.string.message_grant_permission));
//        builder.setPositiveButton(getString(R.string.label_setting), (dialog, which) -> {
//            dialog.cancel();
//            openSettings();
//        });
//        builder.setNegativeButton(getString(R.string.cancel), (dialog, which) -> dialog.cancel());
//        builder.show();
    }
}
