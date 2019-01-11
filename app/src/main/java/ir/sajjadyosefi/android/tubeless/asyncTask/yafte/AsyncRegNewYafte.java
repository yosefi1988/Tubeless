package ir.sajjadyosefi.android.tubeless.asyncTask.yafte;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.widget.Toast;

import com.zl.reik.dilatingdotsprogressbar.DilatingDotsProgressBar;

import ir.sajjadyosefi.android.tubeless.Global;
import ir.sajjadyosefi.android.tubeless.R;
import ir.sajjadyosefi.android.tubeless.activity.MainActivity;
import ir.sajjadyosefi.android.tubeless.classes.CommonClass;
import ir.sajjadyosefi.android.tubeless.classes.model.Blog.BlogItem;
import ir.sajjadyosefi.android.tubeless.classes.model.NetworkLayout.response.NewYafteResponse;
import ir.sajjadyosefi.android.tubeless.classes.networkLayout.RestApiHelper;


/**
 * Created by Other on 10/23/2016.
 */
public class AsyncRegNewYafte extends AsyncTask {
    private final Context mContext;
    private final BlogItem mBlogItem;
    DilatingDotsProgressBar mProgressBar;

    public AsyncRegNewYafte(Context context, DilatingDotsProgressBar dilatingDotsProgressBar, BlogItem blogItem) {
        this.mContext = context;
        this.mBlogItem = blogItem;
        this.mProgressBar = dilatingDotsProgressBar;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        if (mProgressBar != null)
            mProgressBar.showNow();
    }


    @Override
    protected NewYafteResponse doInBackground(Object[] params) {
        if (CommonClass.isNetworkConnected(mContext)) {
            RestApiHelper httpUrlHelper = new RestApiHelper();
            NewYafteResponse registerResponse = httpUrlHelper.NewYafte(mContext,mBlogItem);
            return registerResponse;
        }else
            return null;
    }

    @Override
    protected void onPostExecute(Object response) {
        NewYafteResponse registerResponse = (NewYafteResponse) response;
        if(response == null){
            Global.ShowMessageDialog(mContext, "",mContext.getString(R.string.RegisterErrorOnServer));
        }else {
            if (registerResponse.getServerStatus().getCode() == 98)
                Global.ShowMessageDialog(mContext, "", registerResponse.getServerStatus().getMessage());
            else {
                //ok

                //Global.mUser = registerResponse.he;

                Intent intent = new Intent(mContext, MainActivity.class);
                ((Activity) mContext).startActivity(intent);
                ((Activity) mContext).overridePendingTransition(R.anim.fadeout, R.anim.fadein);
                ((Activity) mContext).finish();
                //Global.ShowMessageDialog(mContext,"",registerResponse.getUser().getUserName());
                Toast.makeText(mContext, registerResponse.getServerStatus().getMessage(), Toast.LENGTH_SHORT).show();
            }
        }
        if (mProgressBar != null)
            mProgressBar.hideNow();
    }
}
