package ir.sajjadyosefi.android.tubeless.asyncTask.blog;

import android.content.Context;
import android.os.AsyncTask;

import com.zl.reik.dilatingdotsprogressbar.DilatingDotsProgressBar;

import ir.sajjadyosefi.android.tubeless.classes.CommonClass;

import ir.sajjadyosefi.android.tubeless.networkLayout.networkLayout.RestApiHelper;
import ir.sajjadyosefi.android.xTubeless.classes.modelY.responses.basic.ServerResponseBase;


public class AsyncFavouriteBlogItem extends AsyncTask {

    private final Context mContext;
    private final DilatingDotsProgressBar mProgressBar;

    //View
    public int idBlogItem = 0;
    public long idUser = 0;
    public Boolean oldFavouriteState = false;

    public AsyncFavouriteBlogItem(Context context, DilatingDotsProgressBar progressBar, int idBlogItem,long idUser, boolean oldFavouriteState) {
        this.idBlogItem = idBlogItem;
        this.idUser = idUser;
        this.oldFavouriteState = oldFavouriteState;
        this.mContext = context ;
        this.mProgressBar = progressBar;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        if (mProgressBar != null)
            mProgressBar.showNow();
    }

    @Override
    protected Object doInBackground(Object[] params) {
        if (CommonClass.isNetworkConnected(mContext)) {
            RestApiHelper myParser = new RestApiHelper();
            //ServerResponseConfig
            Object object = myParser.changeBlogItemFavouriteStatus(idUser,idBlogItem,!oldFavouriteState);
            return object;
        }else
            return null;
    }

    @Override
    protected void onPostExecute(Object result) {
        super.onPostExecute(result);

        prepare_result((ServerResponseBase)result);
    }
    private void prepare_result(ServerResponseBase result) {
        if (mProgressBar != null)
            mProgressBar.hideNow();


    }
}
