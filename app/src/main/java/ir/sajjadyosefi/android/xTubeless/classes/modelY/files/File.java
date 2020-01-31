package ir.sajjadyosefi.android.xTubeless.classes.modelY.files;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Environment;
import android.provider.MediaStore;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import ir.sajjadyosefi.android.xTubeless.classes.modelY.main.TubelessObject;

public class File extends TubelessObject {

    public static int MAP_K = 1000 ;
    public static int MAP_1 = 1001 ;
    public static int SIGNATURE = 1 ;
    public static int MAP_3 = 1003 ;

    private String uri;
    private String title;
    private int fileType;
    private int requestContentId;
    private int frame;
    private boolean sended = false;
    private String TAG ="file";


    public int getRequestContentId() {
        return requestContentId;
    }

    public void setRequestContentId(int requestContentId) {
        this.requestContentId = requestContentId;
    }

    public int getFrame() {
        return frame;
    }

    public void setFrame(int frame) {
        this.frame = frame;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getFileType() {
        return fileType;
    }

    public void setFileType(int fileType) {
        this.fileType = fileType;
    }


    public boolean isSended() {
        return sended;
    }

    public void setSended(boolean sended) {
        this.sended = sended;
    }

    public void writeToxFile(byte[] array) {
        try {
            String path = "...";
            FileOutputStream stream = new FileOutputStream(path);
            try {
                stream.write(array);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }


    public void save(Context context,Bitmap pictureBivp) {
// Assume block needs to be inside a Try/Catch block.
        try {

            String path = Environment.getExternalStorageDirectory().toString();
            OutputStream fOut = null;
            Integer counter = 0;
            java.io.File file = new java.io.File(path, "FitnessGirl" + counter + ".jpg"); // the File to save , append increasing numeric counter to prevent files from getting overwritten.
            fOut = new FileOutputStream(file);

            Bitmap pictureBitmap = pictureBivp ;// obtaining the Bitmap
            pictureBitmap.compress(Bitmap.CompressFormat.JPEG, 85, fOut); // saving the Bitmap to a file compressed as a JPEG with 85% compression rate
            fOut.flush(); // Not really required
            fOut.close(); // do not forget to close the stream

            MediaStore.Images.Media.insertImage(context.getContentResolver(), file.getAbsolutePath(), file.getName(), file.getName());
        }catch (Exception e){

            int a = 5 ;
            a++;
        }
    }


}
