package ir.sajjadyosefi.android.tubeless.asyncTask.blog;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import com.zl.reik.dilatingdotsprogressbar.DilatingDotsProgressBar;

import ir.sajjadyosefi.android.tubeless.classes.CommonClass;
import ir.sajjadyosefi.android.tubeless.networkLayout.networkLayout.RestApiHelper;
import ir.sajjadyosefi.android.xTubeless.classes.modelY.responses.basic.ServerResponseBase;


public class AsyncDeleteBlogItem extends AsyncTask {

    private final Context mContext;
    private final DilatingDotsProgressBar mProgressBar;

    //View
    public int idBlogItem = 0;
    public long idUser = 0;

    public AsyncDeleteBlogItem(Context context, DilatingDotsProgressBar progressBar, int idBlogItem, long idUser) {
        this.idBlogItem = idBlogItem;
        this.idUser = idUser;
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
    protected ServerResponseBase doInBackground(Object[] params) {
        if (CommonClass.isNetworkConnected(mContext)) {
            RestApiHelper myParser = new RestApiHelper();
            //ServerResponseConfig
            ServerResponseBase object = myParser.deleteBlogItem(idUser,idBlogItem);
            return object;
        }else
            return null;
    }

    @Override
    protected void onPostExecute(Object result) {
        super.onPostExecute(result);

        try {
            prepare_result((ServerResponseBase)result);
        }catch (Exception ex){}
    }
    private void prepare_result(ServerResponseBase result) {
        if (mProgressBar != null)
            mProgressBar.hideNow();


        Toast.makeText(mContext,result.getServerStatus().getMessage(),Toast.LENGTH_LONG).show();;

    }
}
