package ir.sajjadyosefi.android.tubeless.activity.innerActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.PopupMenu;
import android.widget.Toast;
import android.widget.VideoView;

import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.Random;

import ir.sajjadyosefi.android.tubeless.classes.DownloadingTask;
import ir.sajjadyosefi.android.tubeless.Global;
import ir.sajjadyosefi.android.tubeless.R;
import com.joaquimley.faboptions.FabOptions;
import com.zl.reik.dilatingdotsprogressbar.DilatingDotsProgressBar;

import static ir.sajjadyosefi.android.xTubeless.activity.WebViewActivity.verifyStoragePermissions;


//import static com.joaquimley.faboptions.sample.R.id.toolbar;

/**
 * Created by sajjad on 9/15/2017.
 */

public class ImageAndVideoPlayer extends AppCompatActivity implements View.OnClickListener {

    String fileFormat;
    String filePath;
    VideoView downloadedVideo;
    ImageView downloadedImage;
    boolean doubleBackToExitPressedOnce = false;
    Context context ;


    private FabOptions mFabOptions;
    public DilatingDotsProgressBar dilatingDotsProgressBar ;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.video_image_player);
        context = this;

        dilatingDotsProgressBar = (DilatingDotsProgressBar) findViewById(R.id.PBSjd);
        downloadedVideo = (VideoView) findViewById(R.id.videoview);
        downloadedImage = (ImageView) findViewById(R.id.imageView);

        mFabOptions = (FabOptions) findViewById(R.id.fab_options);
        mFabOptions.setOnClickListener(this);


        final Button btnMenu = (Button) findViewById(R.id.button4);
        btnMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Creating the instance of PopupMenu
                PopupMenu popup = new PopupMenu(ImageAndVideoPlayer.this, btnMenu);
                //Inflating the Popup using xml file
                popup.getMenuInflater().inflate(R.menu.menu_car_picture_show, popup.getMenu());

                //popup.setAnimationStyle(R.style.Menu_Car_Picture_Show_Animation);

                //registering popup with OnMenuItemClickListener
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    public boolean onMenuItemClick(MenuItem item) {
                        Toast.makeText(ImageAndVideoPlayer.this,"You Clicked : " + item.getTitle(),Toast.LENGTH_SHORT).show();
                        return true;
                    }
                });

                if(btnMenu.isEnabled())
                    popup.show();//showing popup menux
            }
        });//closing the setOnClickListener method

        if(getIntent().getStringExtra("fileFormat") != null)
        {
            fileFormat = getIntent().getStringExtra("fileFormat");
            filePath = getIntent().getStringExtra("filePath");

            if(fileFormat.toUpperCase().equals("MP4") || fileFormat.toUpperCase().equals("3GP")) {
                downloadedVideo.setVisibility(View.VISIBLE);
                setMediaControllerAndPath(downloadedVideo,filePath,fileFormat);
                downloadedVideo.setOnPreparedListener(new videoViewOnPreparedListener());
                downloadedVideo.setOnCompletionListener(new videoOnCompletionListener());

            }
            else if(fileFormat.toUpperCase().equals("JPG") || fileFormat.toUpperCase().equals("PNG") || fileFormat.toUpperCase().equals("JPEG")) {
                downloadedImage.setVisibility(View.VISIBLE);

                if(filePath.contains("/storage/")) {
//                    Picasso.with(ImageAndVideoPlayer.this)
//                            .load(new File(filePath))
////                        .memoryPolicy(MemoryPolicy.NO_CACHE )
////                        .networkPolicy(NetworkPolicy.NO_CACHE)
////                        .resize(512, 512)
////                        .noFade()
//                            .into(downloadedImage);
                }else {
//                    Picasso.with(ImageAndVideoPlayer.this)
//                            .load(filePath)
////                        .memoryPolicy(MemoryPolicy.NO_CACHE )
////                        .networkPolicy(NetworkPolicy.NO_CACHE)
////                        .resize(512, 512)
////                        .noFade()
//                            .into(downloadedImage);
                }
            }
        }
        else
        {
            Global.ShowMessageDialog(getApplicationContext(),"", "Can Not Open This File");
            finish();
        }


    }

    private void setMediaControllerAndPath(VideoView video_view , String path , String type) {
        MediaController mediaController = new MediaController(this);
        mediaController.setAnchorView(video_view);
        video_view.setMediaController(new MediaController(this));
        video_view.setVideoPath(path/* + "." + type*/);
        video_view.requestFocus();
    }


    @Override
    public void onBackPressed() {
        if(fileFormat.toUpperCase().equals("MP4") || fileFormat.toUpperCase().equals("3GP")) {
            if (doubleBackToExitPressedOnce) {
                downloadedVideo.pause();
                finish();
            } else {
                downloadedVideo.pause();
                this.doubleBackToExitPressedOnce = true;
                Global.ShowMessageDialog(getApplicationContext(),"","double Back To Exit Please");
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        doubleBackToExitPressedOnce = false;
                    }
                }, 2000);
            }
        }
        else
            super.onBackPressed();

        overridePendingTransition(R.anim.fadeout, R.anim.fadein);
    }

    private class videoViewOnPreparedListener implements MediaPlayer.OnPreparedListener {
        // Close the progress bar and play the video
        public void onPrepared(MediaPlayer mp) {
            downloadedVideo.start();
        }
    }

    private class videoOnCompletionListener implements MediaPlayer.OnCompletionListener {
        @Override
        public void onCompletion(MediaPlayer mp) {
            finish();
        }
    }



    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.faboptions_like:
                Global.ShowMessageDialog(this,"",this.getString(R.string.commingSoon));
                break;

            case R.id.faboptions_comment:
                Global.ShowMessageDialog(this,"",this.getString(R.string.commingSoon));
                break;


            case R.id.faboptions_download:

                if(Global.user == null){
                    Global.ShowMessageDialog(this,"",this.getString(R.string.NotLoggedIn));
                }else {
                    String fileName = Global.getFileFullName(filePath.toString());
                    File file = Global.getFileLocalPath(fileName);

                    if (file.exists()) {
                        Global.ShowMessageDialog(this,"",this.getString(R.string.GhablanDownloadShode));
                    } else {
                        verifyStoragePermissions(ImageAndVideoPlayer.this);
                        new DownloadingTask(getApplicationContext()).execute(filePath);
                    }
                }
                break;

            case R.id.faboptions_share:
                //1
//                Intent sendIntent = new Intent();
//                sendIntent.setAction(Intent.ACTION_SEND);
//                sendIntent.putExtra(Intent.EXTRA_TEXT, "Hello");
//                sendIntent.putExtra(Intent.EXTRA_STREAM, filePath);
//                sendIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
//                sendIntent.setType("image/jpeg");
//                this.startActivity(sendIntent);


                //2  error
//                try {
//                    Intent intent = new Intent(Intent.ACTION_SEND);
//                    intent.putExtra(Intent.EXTRA_TEXT, "Hey view/download this image");
//                    String path = null;
//
//                    path = MediaStore.Images.Media.insertImage(getContentResolver(), filePath, "", null);
//                    Uri screenshotUri = Uri.parse(path);
//                    intent.putExtra(Intent.EXTRA_STREAM, screenshotUri);
//                    intent.setType("image/*");
//                    startActivity(Intent.createChooser(intent, "Share image via..."));
//                } catch (FileNotFoundException e) {
//                    e.printStackTrace();
//                }



                //3  ok
//                Intent share = new Intent(Intent.ACTION_SEND);
//                // If you want to share a png image only, you can do:
//                // setType("image/png"); OR for jpeg: setType("image/jpeg");
//                share.setType("image/*");
//
//                // Make sure you put example png image named myImage.png in your
//                // directory
//                String imagePath = Environment.getExternalStorageDirectory()  + filePath;
//                File imageFileToShare = new File(imagePath);
//                Uri uri = Uri.fromFile(imageFileToShare);
//                share.putExtra(Intent.EXTRA_STREAM, uri);
//                startActivity(Intent.createChooser(share, "Share Image!"));


                //4
                int permissionReadCheck = ContextCompat.checkSelfPermission(getApplicationContext(), android.Manifest.permission.READ_EXTERNAL_STORAGE);
                int permissionWriteCheck = ContextCompat.checkSelfPermission(getApplicationContext(), android.Manifest.permission.WRITE_EXTERNAL_STORAGE);

                if (permissionReadCheck != PackageManager.PERMISSION_GRANTED && permissionWriteCheck != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(((Activity)context),
                            new String[]{
                                    android.Manifest.permission.READ_EXTERNAL_STORAGE,
                                    android.Manifest.permission.WRITE_EXTERNAL_STORAGE
                            },
                            9001);
                } else {
                    prepareToShare();
                }
                break;

            default:
                // no-op
        }
    }

    private void prepareToShare() {
        //String FileName = SaveScreenshot();
        String FileName = SaveImage(filePath,dilatingDotsProgressBar);
        Intent intent4 = new Intent(Intent.ACTION_SEND);
        //intent4.setType("image/*");
        intent4.setType("*/*");
        intent4.putExtra(Intent.EXTRA_TEXT, getApplicationContext().getString(R.string.ShareText));
        intent4.putExtra(Intent.EXTRA_STREAM, Uri.parse(FileName));
        startActivity(Intent.createChooser(intent4, getApplicationContext().getString(R.string.ShareTitle)));
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case 9001:
                if ((grantResults.length > 0) ){
                    if(grantResults[0] == PackageManager.PERMISSION_GRANTED){
                        prepareToShare();
                    }else {
                        //Toast.makeText(context,context.getString(R.string.WeNeedYourDeviceInfo),Toast.LENGTH_LONG).show();
                        Global.ShowMessageDialog(getApplicationContext(),"",getApplicationContext().getString(R.string.WeNeedYourDeviceInfo));
                    }
                }else {
                    Toast.makeText(getApplicationContext(),getApplicationContext().getString(R.string.ErrorOnGetPermision),Toast.LENGTH_LONG).show();
                }
                break;

            default:
                break;
        }
    }
    //todo دانلود بهینه بشه   - download behine beshe
    //todo گرفتن مجوز ها بهینه بشه  --permisions behine beshe




    File apkStorage = null;
    File outputFile = null;
    private String SaveImage(String urlString ,DilatingDotsProgressBar dilatingDotsProgressBar) {
        try {
            dilatingDotsProgressBar.showNow();
            //
            URL url = new URL(urlString);                                           //Create Download URl
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();//Open Url Connection
            connection.setRequestMethod("GET");                                     //Set Request Method to "GET" since we are grtting data
            connection.connect();                                                   //connect the URL Connection

            //If Connection response is not OK then show Logs
            if (connection.getResponseCode() != HttpURLConnection.HTTP_OK) {
                Toast.makeText(getApplicationContext(),"Error" , Toast.LENGTH_LONG).show();
            }

            //Get File if SD card is present
            if (Global.isSDCardPresent()) {
                apkStorage = Global.getFileStoragePath();
            }
            //else
            //Toast.makeText(context, "Oops!! There is no SD Card.", Toast.LENGTH_SHORT).show();

            //If File is not present create directory
            if (!apkStorage.exists()) {
                apkStorage.mkdir();
            }

            String fileNameWithoutExtn = url.toString().substring(0, url.toString().lastIndexOf('.'));
            String fileName = Global.getFileFullName(url.toString());
            outputFile = new File(apkStorage,fileName );                                //Create Output file in Main File
            List values = connection.getHeaderFields().get("content-Length");

//            try {
//                // Make sure the Pictures directory exists.
//                //File path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
//                File path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM);
//                path.mkdirs();
//            }catch (Exception ex){
//                int a =  2 + 6 ;
//            }

            File path = Environment.getExternalStoragePublicDirectory( Environment.DIRECTORY_PICTURES);
//            final File path = Environment.getExternalStoragePublicDirectory
//                            (
//                                    //Environment.DIRECTORY_PICTURES
//                                    //Environment.DIRECTORY_DCIM
//                                    Environment.DIRECTORY_DCIM + "/TubelessImages/"
//                            );

            File file = new File(Environment.getExternalStorageDirectory().getAbsoluteFile(), fileName);    //InternalStoreage
            //File file = new File(Environment.getDataDirectory().getAbsoluteFile(), fileName);             //InternalStoreage

            //File file = new File(path, fileName);
            try {
                // Make sure the Pictures directory exists.
                path.mkdirs();
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }

            if (!outputFile.getParentFile().exists())
                outputFile.getParentFile().mkdirs();
            if (!outputFile.exists())
                outputFile.createNewFile();



            //Create New File if not present
            if (!outputFile.exists()) {
                if (!outputFile.getParentFile().exists())
                    outputFile.getParentFile().mkdirs();
                if (!outputFile.exists())
                    outputFile.createNewFile();
            }

            FileOutputStream fos = new FileOutputStream(outputFile);//Get OutputStream for NewFile Location

            InputStream is = connection.getInputStream();//Get InputStream for connection
            String sLength;
            int sLengthInt = 0;
            if (values != null && !values.isEmpty()) {
                // getHeaderFields() returns a Map with key=(String) header
                // name, value = List of String values for that header field.
                // just use the first value here.
                sLength = (String) values.get(0);

                if (sLength != null) {
                    //parse the length into an integer...
                }
                sLengthInt = Integer.parseInt(sLength);
            }

            byte[] buffer = new byte[1024];//Set buffer type
            int len1 = 0;//init length
            int downloadedSize = 0;
            int counter=1;
            long total = 0;

            int darsad = 0,darsadTmp = 0;
            while ((len1 = is.read(buffer)) != -1) {
                total += len1;
                fos.write(buffer, 0, len1);//Write new file

                downloadedSize += len1;
                darsad = (int)((total*100)/sLengthInt);
                if(darsad != darsadTmp) {
                    darsadTmp = darsad;
                }
            }
            //Close all connection after doing task
            fos.close();
            is.close();
            dilatingDotsProgressBar.hideNow();
        } catch (Exception e) {
            //Read exception if something went wrong
            e.printStackTrace();
            outputFile = null;
            dilatingDotsProgressBar.hideNow();
        }

        return "file://" + outputFile.toString();
    }

    ViewGroup _root;
    public String SaveScreenshot(){
        Random random = new Random();

        String path = Environment.getExternalStorageDirectory().toString() + "/EidPics/";
        File dir = new File(path);
        if (!dir.exists())
            dir.mkdirs();
        Bitmap bitmap;
        _root.setDrawingCacheEnabled(true);
        bitmap = Bitmap.createBitmap(_root.getDrawingCache());
        _root.setDrawingCacheEnabled(false);
        path +=  String.valueOf(random.nextInt(1000)) + ".jpg";
        OutputStream fout = null;
        File imageFile = new File(path);

        try {
            fout = new FileOutputStream(imageFile);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 90, fout);
            fout.flush();
            fout.close();

        } catch (FileNotFoundException e) {
            return null;
        } catch (IOException e) {
            return null;
        }
        return "file://" + imageFile.getAbsolutePath();

    }
}
