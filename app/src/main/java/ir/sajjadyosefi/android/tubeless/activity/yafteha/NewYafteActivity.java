package ir.sajjadyosefi.android.tubeless.activity.yafteha;

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
import android.os.Handler;
import android.provider.MediaStore;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.ScrollView;
import android.widget.TextView;

import com.orm.SugarContext;
import com.zl.reik.dilatingdotsprogressbar.DilatingDotsProgressBar;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.util.Random;

import eu.janmuller.android.simplecropimage.CropImage;
import ir.adad.client.Adad;
import ir.sajjadyosefi.android.tubeless.Global;
import ir.sajjadyosefi.android.tubeless.R;
import ir.sajjadyosefi.android.xTubeless.classes.modelY.Blog.BlogItem;
import ir.sajjadyosefi.android.tubeless.networkLayout.networkLayout.HttpFileUploadAsync;
import ir.sajjadyosefi.android.tubeless.networkLayout.networkLayout.Url;
import ir.sajjadyosefi.android.tubeless.provider.InternalStorageContentProvider;
import ir.sajjadyosefi.android.tubeless.view.widget.view.PersianTextView;
import ir.sajjadyosefi.android.xTubeless.classes.model.user.User;

import static ir.sajjadyosefi.android.tubeless.activity.uploadPicture.UploadPictureActivity.REQUEST_CODE_CROP_IMAGE;
import static ir.sajjadyosefi.android.tubeless.activity.uploadPicture.UploadPictureActivity.REQUEST_CODE_GALLERY;
import static ir.sajjadyosefi.android.tubeless.activity.uploadPicture.UploadPictureActivity.REQUEST_CODE_TAKE_PICTURE;

//import com.vansuita.gaussianblur.GaussianBlur;


public class NewYafteActivity extends AppCompatActivity {

    //Bundle String list
    public static final String Type = "TYPE";

    //Create New Activity List
    public static final int GOM_SHODE                 = 16;
    public static final int PEYDA_SHODE               = 17;
    public static final int SERGHATI                  = 18;
    public static int messageType = 0;
    public static boolean selectedPicture = false;
    public static boolean selectedR = false;
    public static int selectedRadio = 17;

    Context context;
    DilatingDotsProgressBar dilatingDotsProgressBar;
    PersianTextView tvField1,tvField2;
    TextView tvTitle;
    RadioButton radioButton1,radioButton2,radioButton3;
    TextView tvTitleText02;
    EditText etField1;
    EditText etField2;
    EditText etField3 ;
    EditText etField4 ;
    Button btn1;
    Button btn2;
    ScrollView svScroll;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //init
        context = this;
        SugarContext.init(this);
        Adad.initialize(getApplicationContext());

        //getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        //getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        //getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_new_yafte);

        tvTitleText02 = (TextView) findViewById(R.id.tvTitleText02);
        etField1 = (EditText) findViewById(R.id.etField1);
        etField2 = (EditText) findViewById(R.id.etField2);
        etField3 = (EditText) findViewById(R.id.etField3);
        etField4 = (EditText) findViewById(R.id.etField4);


        final Random rnd = new Random();
//        ImageView imageView1 = (ImageView) findViewById(R.id.imageView1);
        ImageView imageView2 = (ImageView) findViewById(R.id.imageView2);
        ImageView imageView3 = (ImageView) findViewById(R.id.imageView3);
        int aaa = (rnd.nextInt(3));
        if (aaa == 0){
//            imageView1.setVisibility(View.VISIBLE);
        }
        if (aaa == 1){
            imageView2.setVisibility(View.VISIBLE);
        }
        if (aaa == 2){
            imageView3.setVisibility(View.VISIBLE);
        }

        dilatingDotsProgressBar = (DilatingDotsProgressBar) findViewById(R.id.PBSjd);

        btn1 = (Button) findViewById(R.id.btn1);
        btn2 = (Button) findViewById(R.id.btn2);
        radioButton1 = (RadioButton) findViewById(R.id.radioButton1);
        radioButton2 = (RadioButton) findViewById(R.id.radioButton2);
        radioButton3 = (RadioButton) findViewById(R.id.radioButton3);
        tvField1 = (PersianTextView) findViewById(R.id.tvField1);
        tvField2 = (PersianTextView) findViewById(R.id.tvField2);
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
//        tvTitleText02.setOnClickListener(imageviewClickListener);
        radioButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                radioButton1.setChecked(true);
                radioButton2.setChecked(false);
                radioButton3.setChecked(false);
                tvTitleText02.setText(context.getResources().getString(R.string.radioYafte1Text));
                messageType = NewYafteActivity.PEYDA_SHODE;
                selectedRadio = messageType;
                InputMethodManager imm = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(tvTitle.getWindowToken(), 0);
            }
        });
        radioButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                radioButton1.setChecked(false);
                radioButton2.setChecked(true);
                radioButton3.setChecked(false);
                tvTitleText02.setText(context.getResources().getString(R.string.radioYafte2Text));
                messageType = NewYafteActivity.GOM_SHODE;
                selectedRadio = messageType;
                InputMethodManager imm = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(tvTitle.getWindowToken(), 0);
            }
        });
        radioButton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                radioButton1.setChecked(false);
                radioButton2.setChecked(false);
                radioButton3.setChecked(true);
                tvTitleText02.setText(context.getResources().getString(R.string.radioYafte3Text));
                messageType = NewYafteActivity.SERGHATI;
                selectedRadio = messageType;
                InputMethodManager imm = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(tvTitle.getWindowToken(), 0);
            }
        });
//        tvField1.setOnClickListener(imageviewClickListener);
//        tvRadio1.setOnClickListener(imageviewClickListener);
//        tvRadio2.setOnClickListener(imageviewClickListener);
//        tvRadio3.setOnClickListener(imageviewClickListener);
//        tvField2.setOnClickListener(imageviewClickListener);
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


        //getType
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        switch (bundle.getInt(NewYafteActivity.Type)) {
            case NewYafteActivity.PEYDA_SHODE:{
                messageType = NewYafteActivity.PEYDA_SHODE;
                break;
            }
            case NewYafteActivity.GOM_SHODE:{
                messageType = NewYafteActivity.GOM_SHODE;
                break;
            }
            case NewYafteActivity.SERGHATI:{
                messageType = NewYafteActivity.SERGHATI;
                break;
            }
        }


        (findViewById(R.id.btnCamera)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                ShowSelectSturceDialog(context);
                Global.ShowMessageDialog(context,"",context.getString(R.string.commingSoon));
            }
        });


        final Button btnSend = (Button)(findViewById(R.id.btn1));
        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(etField1.getText().length() < 1 ||
                        etField2.getText().length() < 1 ||
                        etField3.getText().length() < 1 ||
                        etField4.getText().length() < 1 ){
                    Global.ShowMessageDialog(context,"",context.getString(R.string.requerment_fields_inYafteha));
                }else {
                    //Do Send

                    //first
//                    RestApiHelper helper = new RestApiHelper();
//                    helper.sendImageToServer(context,mFileTemp,RestApiHelper.CAR_PIC);

                    if(getFileSize(context, mFileTemp)<4) {
                        //3
                        try {
//                            if(Global.user == null) {
//                                Global.ShowMessageDialog(context, "", context.getString(R.string.NotLoggedIn2));
//                            }else
                                {
//                                if(Global.user.getCanSendPicture() == false) {
//                                    Global.ShowMessageDialog(context, "", context.getString(R.string.CanNotSendPicture));
//                                }else
                                    {

                                        if(selectedPicture == true) {
                                            HttpFileUploadAsync HttpFileUploadAsync = new HttpFileUploadAsync(context, dilatingDotsProgressBar, btnSend,
                                                    Url.POST_ADD_YAFTE_WITH_PIC,
                                                    mFileTemp,
//                                            "Files/Pictures/yafte/" + NewYafteActivity.Type + "/" + Global.user.getUserID() + "/",
                                                    "Files/Pictures/yafte/" + messageType + "/",
                                                    Url.MAIN_SERVER_ADDRESS + "Yafte/AddYafte?" +
                                                            "Title=" + toHex(etField1
                                                            .getText()
                                                            .toString())
//                                                                .replace(" ", "%20")
//                                                                .replace("_", "%5F")
                                                            +
                                                            "&Address=" + toHex(etField2.getText().toString()) +
                                                            "&Time=" + toHex(etField3.getText().toString()) +
                                                            "&Comment=" + toHex(etField4.getText().toString()) +
                                                            "&HeaderID=" + selectedRadio +
                                                            "&UserID=49");
                                            //"&UserID=" + Global.user.getUserID() + "");

                                            HttpFileUploadAsync.execute();
                                            mFileTemp = null;
                                        }else {
                                            BlogItem blogItem = new BlogItem();
                                            blogItem.setCategoryID(selectedRadio);
                                            blogItem.setUserID(49);
                                            blogItem.setTitle(etField1.getText().toString());
                                            blogItem.setTitlePicture(etField3.getText().toString());
                                            blogItem.setStatement(etField2.getText().toString());
                                            blogItem.setText(etField4.getText().toString());
                                            blogItem.setTextPicture(etField4.getText().toString());

//                                            AsyncRegNewYafte asyncRegNewYafte = new AsyncRegNewYafte(context,dilatingDotsProgressBar,blogItem);
//                                            asyncRegNewYafte.execute();

                                        }
                                }
                                {
                                    //88888888888888888888888888888888888 mail 888888888888888888888888888888888888888888888
//                                    Message message = new Message();
//                                    message.setApplicationID(17);
//                                    message.setPhoneNumber(etField1.getText().toString());
//                                    message.setText(etField3.getText().toString());
//                                    switch (messageType) {
//                                        case NewYafteActivity.PEYDA_SHODE:{
//                                            message.setTitle("پیدا شده");
//                                            break;
//                                        }
//                                        case NewYafteActivity.GOM_SHODE:{
//                                            message.setTitle("گم شده");
//                                            break;
//                                        }
//                                        case NewYafteActivity.SERGHATI:{
//                                            message.setTitle("سرقتی");
//                                            break;
//                                        }
//                                    }
//                                    message.setType(messageType + "");
//                                    AsyncSendMessage asyncSendMessage = new AsyncSendMessage(context,message);
//                                    asyncSendMessage.execute();
                                    //88888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888
                                }
                            }
                        } catch (MalformedURLException e) {
                            e.printStackTrace();
                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        }



                    }else
                        Global.ShowMessageDialog(context,"",context.getString(R.string.fileSize4Mb));

                }


            }
        });

        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state)) {
            mFileTemp = new File(Environment.getExternalStorageDirectory(), TEMP_PHOTO_FILE_NAME);
        }
        else {
            mFileTemp = new File(getFilesDir(), TEMP_PHOTO_FILE_NAME);
        }

    }
    public static final String TEMP_PHOTO_FILE_NAME = "TubelessImageYafte.jpg";

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
//
//                Bundle res = data.getExtras();
//                int result = res.getInt("ID");
//                selectedCar = res.getString("DirectoryName");
//                selectedCategory = result;
//
//                break;
            case REQUEST_CODE_CROP_IMAGE:

                String path = data.getStringExtra(CropImage.IMAGE_PATH);
                if (path == null) {
                    selectedPicture = false;
                    return;
                }
                selectedPicture = true;
                bitmap = BitmapFactory.decodeFile(mFileTemp.getPath());

//                ivShow.setImageBitmap(bitmap);
//                ivShow.setVisibility(View.VISIBLE);
                ((Button)(findViewById(R.id.btnCamera))).setText( "عکس انتخاب شد ");
                break;
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void takePicture() {

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
            intent.putExtra(android.provider.MediaStore.EXTRA_OUTPUT, mImageCaptureUri);
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


    public static File mFileTemp;
    private void startCropImage() {

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
    protected void onStart() {
        super.onStart();
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);


        switch (selectedRadio){
            case(SERGHATI):
                radioButton3.setChecked(true);
                radioButton3.performClick();
                break;
            case(GOM_SHODE):
                radioButton2.setChecked(true);
                radioButton2.performClick();

                break;
            case(PEYDA_SHODE):
                radioButton1.setChecked(true);
                radioButton1.performClick();

                break;

        }
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                svScroll.fullScroll(ScrollView.FOCUS_UP);
            }
        },200);
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
