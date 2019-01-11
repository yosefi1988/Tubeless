package ir.sajjadyosefi.android.tubeless.provider;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Environment;
import android.os.ParcelFileDescriptor;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;

/**
 * Created by Mahdi on 1/17/2016.
 */

public class MyFileContentProvider extends ContentProvider {

    public static final Uri CONTENT_URI = Uri.parse("content://com.hamsa/");

    public static Uri getFile() {
        String filepath = Environment.getExternalStorageDirectory().getAbsolutePath() + "/Tubeless";
        File file = new File(filepath,"image");

        if(!file.exists())
            file.mkdirs();

        String imagePath = file.getAbsolutePath() + "/" + "newImage.jpg";
        return Uri.fromFile(new File(imagePath));

    }

    public static void deleteTempFile(){
        Uri uri = MyFileContentProvider.getFile();
        File file = new File(uri.getPath());
        if(file.exists())
            file.delete();
    }

    public static final String FILE_NAME = "newImage.jpg";

    private static final HashMap<String, String> MIME_TYPES = new HashMap<String, String>();

    static {

        MIME_TYPES.put(".jpg", "image/jpeg");

        MIME_TYPES.put(".jpeg", "image/jpeg");

    }

    @Override

    public boolean onCreate() {

        try {

            File mFile = new File(getContext().getFilesDir(), FILE_NAME);

            if (!mFile.exists()) {

                mFile.createNewFile();

            }

            getContext().getContentResolver().notifyChange(CONTENT_URI, null);

            return (true);

        } catch (Exception e) {

            e.printStackTrace();

            return false;

        }

    }

    @Override

    public String getType(Uri uri) {

        String path = uri.toString();

        for (String extension : MIME_TYPES.keySet()) {

            if (path.endsWith(extension)) {

                return (MIME_TYPES.get(extension));

            }

        }

        return (null);

    }

    @Override

    public ParcelFileDescriptor openFile(Uri uri, String mode)

            throws FileNotFoundException {

        File f = new File(getContext().getFilesDir(), "newImage.jpg");

        if (f.exists()) {

            return (ParcelFileDescriptor.open(f,

                    ParcelFileDescriptor.MODE_READ_WRITE));

        }

        throw new FileNotFoundException(uri.getPath());

    }

    @Override

    public Cursor query(Uri url, String[] projection, String selection,

                        String[] selectionArgs, String sort) {

        throw new RuntimeException("Operation not supported");

    }

    @Override

    public Uri insert(Uri uri, ContentValues initialValues) {

        throw new RuntimeException("Operation not supported");

    }

    @Override

    public int update(Uri uri, ContentValues values, String where,

                      String[] whereArgs) {

        throw new RuntimeException("Operation not supported");

    }

    @Override

    public int delete(Uri uri, String where, String[] whereArgs) {

        throw new RuntimeException("Operation not supported");

    }
}
