package ir.sajjadyosefi.android.tubeless.asyncTask.blog;

import android.content.Context;
import android.os.AsyncTask;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;
import com.zl.reik.dilatingdotsprogressbar.DilatingDotsProgressBar;

import ir.sajjadyosefi.android.tubeless.classes.CommonClass;
import ir.sajjadyosefi.android.tubeless.classes.networkLayout.RestApiHelper;
import ir.sajjadyosefi.android.tubeless.classes.model.Blog.BlogItem;
import ir.sajjadyosefi.android.tubeless.classes.model.Blog.BlogItemResponse;
import ir.sajjadyosefi.android.tubeless.R;
import ir.sajjadyosefi.android.tubeless.activity.innerActivity.ReadBlogActivity;


public class AsyncLoadBlogItem extends AsyncTask {

    private final Context mContext;
    private final DilatingDotsProgressBar mProgressBar;
    private final ImageView TextPicture;
    private final TextView tvText;
    private final TextView TextHeader;
    private final TextView textViewNoting;

    //View
    public int idBlogItem = 0;

    public AsyncLoadBlogItem(Context context, DilatingDotsProgressBar progressBar, ImageView TextPicture, TextView TextHeader, TextView Text, TextView textViewNoting, int idBlogItem) {
        this.idBlogItem = idBlogItem;
        this.mContext = context ;
        this.mProgressBar = progressBar;

        this.TextPicture = TextPicture ;
        this.tvText = Text ;
        this.TextHeader = TextHeader ;
        this.textViewNoting=textViewNoting;
    }


    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        if (mProgressBar != null)
            mProgressBar.showNow();
    }

    @Override
    protected Object doInBackground(Object[] params) {
        try {

            if (CommonClass.isNetworkConnected(mContext)) {
                RestApiHelper myParser = new RestApiHelper();
                BlogItemResponse timelineResponse = myParser.GetBlog(mContext, idBlogItem);
                return timelineResponse.getBlog();
            } else
                return null;
        }catch (Exception ex){
            return  null;
        }
    }

    @Override
    protected void onPostExecute(Object result) {
        super.onPostExecute(result);

        Fill_Blog((BlogItem)result);
    }
    private void Fill_Blog(BlogItem result) {
        if (mProgressBar != null)
            mProgressBar.hideNow();


        //textViewNoting.setText(result.getText());
        tvText.setText(result.getText());

        if(result.getText().contains(result.getStatement()))
            TextHeader.setVisibility(View.GONE);
        else
            TextHeader.setText(result.getStatement());

        ((ReadBlogActivity)mContext).getSupportActionBar().setTitle(result.getTitle());


        Picasso.with(mContext)
                .load(result.getTextPicture())
                .placeholder(R.drawable.progress_animation)
                .into(TextPicture ,new Callback() {
                    @Override
                    public void onSuccess() {

                    }

                    @Override
                    public void onError() {
                        TextPicture.setVisibility(View.GONE);
                    }
                });


    }
}
