package ir.sajjadyosefi.android.xTubeless.classes.model;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import ir.sajjadyosefi.android.xTubeless.Adapter.EndlessList_AdapterFile;
import ir.sajjadyosefi.android.xTubeless.R;
import ir.sajjadyosefi.android.xTubeless.activity.uploadPicture.FileListActivity;
import ir.sajjadyosefi.android.xTubeless.activity.uploadPicture.ImageRequest;
import ir.sajjadyosefi.android.xTubeless.activity.uploadPicture.RemoteApi;
import ir.sajjadyosefi.android.xTubeless.activity.uploadPicture.RetrofitImageLoader;
import ir.sajjadyosefi.android.xTubeless.utility.DeviceUtil;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class File extends TubelessObject  {

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

    public void prepareYafteItem(Context mContext, EndlessList_AdapterFile.FileViewHolder holder, List<File> mTimelineItemList, int position, EndlessList_AdapterFile adapter, boolean deletable) {

        File file = (File) mTimelineItemList.get(position);

        StringBuilder text = new StringBuilder();
        text.append(file.getTitle());

        holder.textView.setText(text.toString());
        holder.textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//
//                Picasso.Builder builder = new Picasso.Builder(DetailsActivity.ImageView.getContext());
//                RequestCreator picassoImageLoader = createPicassoLoader(
//                        builder,
//                        ImageRequest.DEFAULT_JSON_BODY,
//                        "http://shop.atiafkar.ir/api/DownloadFileForAndroid"
//                );
//                picassoImageLoader.into(DetailsActivity.ImageView);


                loadImageWithRetrofit(mContext,file, FileListActivity.imageView);
            }
        });

        if (deletable){
            holder.buttonDelete.setEnabled(deletable);
            holder.buttonDelete.setVisibility(View.VISIBLE);
        }else {
            holder.buttonDelete.setEnabled(deletable);
            holder.buttonDelete.setVisibility(View.GONE);
        }

        holder.buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mTimelineItemList.remove(position);

                if (mTimelineItemList.size() == 0 )
                    holder.textView.setText(R.string.someText);

                adapter.notifyDataSetChanged();
            }
        });

        holder.buttonShow.setEnabled(true);
        holder.buttonShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                holder.textView.performClick();
            }
        });

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


    public void loadImageWithRetrofit(Context context, File file, ImageView imageView) {
        final RetrofitImageLoader imageLoader = new RetrofitImageLoader(imageView);
        RemoteApi api = RemoteApi.Factory.create();
        ImageRequest.DEFAULT_BODY.setAndroidId(DeviceUtil.GetAndroidId(context));
        ImageRequest.DEFAULT_BODY.setContentId(file.getRequestContentId() + "");
        ImageRequest.DEFAULT_BODY.setFrame(file.getFrame() + "");
        api.getImage(ImageRequest.DEFAULT_BODY).enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                ResponseBody body = response.body();
                if (response.isSuccessful() && body != null) {
                    try {
                        imageLoader.execute(file , body.byteStream());




                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    Log.d(TAG, "Retrofit onResponse(): CODE = [" + response.code() + "], MESSAGE = [" + response.message() + "]");
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.d(TAG, "Retrofit onFailure(): t = [" + t + "]");
            }
        });
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
