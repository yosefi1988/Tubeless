package ir.sajjadyosefi.android.tubeless.activity.radyab;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.orm.SugarContext;
import com.zl.reik.dilatingdotsprogressbar.DilatingDotsProgressBar;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import eu.janmuller.android.simplecropimage.CropImage;
import ir.adad.client.Adad;
import ir.sajjadyosefi.android.tubeless.Global;
import ir.sajjadyosefi.android.tubeless.R;

import ir.sajjadyosefi.android.tubeless.provider.InternalStorageContentProvider;
import ir.sajjadyosefi.android.xTubeless.classes.model.user.User;

import static ir.sajjadyosefi.android.tubeless.activity.uploadPicture.UploadPictureActivity.REQUEST_CODE_CROP_IMAGE;
import static ir.sajjadyosefi.android.tubeless.activity.uploadPicture.UploadPictureActivity.REQUEST_CODE_GALLERY;
import static ir.sajjadyosefi.android.tubeless.activity.uploadPicture.UploadPictureActivity.REQUEST_CODE_TAKE_PICTURE;

//import com.vansuita.gaussianblur.GaussianBlur;


public class RadyabCarActivity extends AppCompatActivity {

    Context context;
    DilatingDotsProgressBar dilatingDotsProgressBar;
    TextView tvTitle;
    TextView tvTitleText;
    EditText etField1;
    EditText etField2;
    EditText etField3 ;
    EditText etField4 ;
    Button btn1;
    Button btn2;
    ImageView ivShow;
    ScrollView svScroll;
    public static File mFileTemp;
    public static final String TEMP_PHOTO_FILE_NAME = "TubelessImageYafte.jpg";

    private boolean selectedPicture;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //init
        context = this;
        SugarContext.init(this);

        Intent intent = getIntent();
        final int slaveId = intent.getExtras().getInt("IdSlave");

        Adad.initialize(getApplicationContext());

        //getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        //getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        //getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_car_radyab);

        etField1 = (EditText) findViewById(R.id.etField1);
        etField2 = (EditText) findViewById(R.id.etField2);
        etField3 = (EditText) findViewById(R.id.etField3);
        etField4 = (EditText) findViewById(R.id.etField4);
        ivShow = (ImageView) findViewById(R.id.imageView);

        dilatingDotsProgressBar = (DilatingDotsProgressBar) findViewById(R.id.PBSjd);

        btn1 = (Button) findViewById(R.id.btn1);
        btn2 = (Button) findViewById(R.id.btn2);
        tvTitle = (TextView) findViewById(R.id.tvTitle);
        svScroll = (ScrollView) findViewById(R.id.svScroll);

        //hide keyboard
        View.OnClickListener imageviewClickListener =  new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //hide keyboard
                InputMethodManager imm = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(tvTitle.getWindowToken(), 0);
            }
        };

        tvTitle.setOnClickListener(imageviewClickListener);

//        if(Global.user != null){
//            etField1.setText(Global.user.getMobileNumber());
//            etField1.setEnabled(false);
//        }

        btn1.setText(context.getString(R.string.send));
        btn2.setText(context.getString(R.string.cancel));
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });


        (findViewById(R.id.btnCamera)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShowSelectSturceDialog(context);
            }
        });

        final Button btnSend = (Button)(findViewById(R.id.btn1));
        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    //getFileSize(context, mFileTemp)

                File mFileTemp2;
                String TEMP2_PHOTO_FILE_NAME = "TubelessCar" + slaveId + ".jpg";

                //mFileTemp = ok
                String state = Environment.getExternalStorageState();
                if (Environment.MEDIA_MOUNTED.equals(state)) {
                    mFileTemp2 = new File(Environment.getExternalStorageDirectory(), TEMP2_PHOTO_FILE_NAME);
                }
                else {
                    mFileTemp2 = new File(getFilesDir(), TEMP2_PHOTO_FILE_NAME);
                }

                Global.copy(mFileTemp, mFileTemp2 );

//                LitePallDatabaseUtils databaseUtils = new LitePallDatabaseUtils(context);
//                SlaveDetails slaveDetails = new SlaveDetails();
//                slaveDetails.setCarName(etField1.getText().toString());
//                slaveDetails.setShomareMotor(etField4.getText().toString());
//                slaveDetails.setShomarePelak(etField2.getText().toString());
//                slaveDetails.setShomareShasi(etField3.getText().toString());
//                slaveDetails.setCarPicture(mFileTemp2.getPath());
//                databaseUtils.updateSlaveDetails(slaveId,slaveDetails);


                Intent intent = new Intent(context, SelectCarForRadyab.class);
                Bundle bundle = new Bundle();
                bundle.putInt("IdSlave",slaveId);
                intent.putExtras(bundle);
                context.startActivity(intent);
            }
        });


        //mFileTemp = ok
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state)) {
            mFileTemp = new File(Environment.getExternalStorageDirectory(), TEMP_PHOTO_FILE_NAME);
        }
        else {
            mFileTemp = new File(getFilesDir(), TEMP_PHOTO_FILE_NAME);
        }
    }

    public static long getFileSize(Context context, File file) {
        long length = file.length();
        length = length/1024;
        //System.out.println("File Path : " + file.getPath() + ", File size : " + length +" KB");
        return length/1024; //MB
    }

    public String toHex(String arg) {

        String retVal = "";
        retVal = arg.replace(" ", "%20");
        retVal = retVal.replace("_", "%5F");
        retVal = retVal.replace("\n", "%0A");

        return retVal;

    }

    public void ShowSelectSturceDialog(final Context context){

        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        View dialoglayout = inflater.inflate(R.layout.picture_type_layout, null);
        final AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setView(dialoglayout);
        final AlertDialog alertDialog1 = builder.create();
        alertDialog1.getWindow().getAttributes().windowAnimations = R.style.alertDialogAnimation;
        alertDialog1.setCancelable(true);


        final Button btnCamera = (Button) dialoglayout.findViewById(R.id.btnCamera);
        final Button btnBrowse = (Button) dialoglayout.findViewById(R.id.btnBrowse);
        btnCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog1.dismiss();

                int permissionReadCheck = ContextCompat.checkSelfPermission(context, android.Manifest.permission.READ_EXTERNAL_STORAGE);
                int permissionWriteCheck = ContextCompat.checkSelfPermission(context, android.Manifest.permission.WRITE_EXTERNAL_STORAGE);

                if (permissionReadCheck != PackageManager.PERMISSION_GRANTED && permissionWriteCheck != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(((Activity)context),
                            new String[]{
                                    android.Manifest.permission.READ_EXTERNAL_STORAGE,
                                    android.Manifest.permission.WRITE_EXTERNAL_STORAGE
                            },
                            9001);
                } else {
                    takePicture();
                }
            }//end click
        });
        btnBrowse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog1.dismiss();
                openGallery();
            }
        });

        //text
        TextView textViewText = (TextView) dialoglayout.findViewById(R.id.textView1);
        textViewText.setText(context.getString(R.string.inputType));


        try {
            alertDialog1.show();
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode != RESULT_OK) {
            return;
        }
        Bitmap bitmap;
        switch (requestCode) {
            case REQUEST_CODE_GALLERY:
                try {
                    //mFileTemp ok
                    InputStream inputStream = getContentResolver().openInputStream(data.getData());
                    FileOutputStream fileOutputStream = new FileOutputStream(mFileTemp);
                    copyStream(inputStream, fileOutputStream);
                    fileOutputStream.close();
                    inputStream.close();
                    startCropImage();
                } catch (Exception e) {
                    //Log.e(TAG, "Error while creating temp file", e);
                }
                break;
            case REQUEST_CODE_TAKE_PICTURE:
                startCropImage();
                break;
//            case REQUEST_CODE_GET_CATEGORY:
//                Bundle res = data.getExtras();
//                int result = res.getInt("ID");
//                selectedCar = res.getString("DirectoryName");
//                selectedCategory = result;
//                break;
            case REQUEST_CODE_CROP_IMAGE:
                //mFileTemp ok
                String path = data.getStringExtra(CropImage.IMAGE_PATH);
                if (path == null) {
                    selectedPicture = false;
                    return;
                }
                selectedPicture = true;
                bitmap = BitmapFactory.decodeFile(mFileTemp.getPath());
                ivShow.setImageBitmap(bitmap);
                ivShow.setVisibility(View.VISIBLE);
                ((Button)(findViewById(R.id.btnCamera))).setText( "عکس انتخاب شد ");
                break;
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void takePicture() {
        //mFileTemp ok
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        try {
            Uri mImageCaptureUri = null;
            String state = Environment.getExternalStorageState();
            if (Environment.MEDIA_MOUNTED.equals(state)) {
                mImageCaptureUri = Uri.fromFile(mFileTemp);
            }
            else {
	        	/*
	        	 * The solution is taken from here: http://stackoverflow.com/questions/10042695/how-to-get-camera-result-as-a-uri-in-data-folder
	        	 */
                mImageCaptureUri = InternalStorageContentProvider.CONTENT_URI;
            }
            intent.putExtra(MediaStore.EXTRA_OUTPUT, mImageCaptureUri);
            intent.putExtra("return-data", true);
            startActivityForResult(intent, REQUEST_CODE_TAKE_PICTURE);
        } catch (ActivityNotFoundException e) {
            //Log.d(TAG, "cannot take picture", e);
        }
    }
    private void openGallery() {

        Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
        photoPickerIntent.setType("image/*");
        startActivityForResult(photoPickerIntent, REQUEST_CODE_GALLERY);
    }


    private void startCropImage() {
        //mFileTemp ok
        Intent intent = new Intent(this, CropImage.class);
        intent.putExtra(CropImage.IMAGE_PATH, mFileTemp.getPath());
        intent.putExtra(CropImage.SCALE, true);
        intent.putExtra(CropImage.ASPECT_X, 3);
        intent.putExtra(CropImage.ASPECT_Y, 2);
        startActivityForResult(intent, REQUEST_CODE_CROP_IMAGE);
    }
    public static void copyStream(InputStream input, OutputStream output) throws IOException {
        byte[] buffer = new byte[1024];
        int bytesRead;
        while ((bytesRead = input.read(buffer)) != -1) {
            output.write(buffer, 0, bytesRead);
        }
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Global.user = new User();
//        Intent intent = new Intent(this,xMainActivity.class);
//        startActivity(intent);
        finish();
        overridePendingTransition(R.anim.fadeout, R.anim.fadein);
    }
}
