package ir.sajjadyosefi.android.tubeless.asyncTask.blog;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.zl.reik.dilatingdotsprogressbar.DilatingDotsProgressBar;

import java.util.List;

import ir.sajjadyosefi.android.tubeless.R;
import ir.sajjadyosefi.android.tubeless.activity.ContactUsActivity;
import ir.sajjadyosefi.android.tubeless.activity.yafteha.NewYafteActivity;
import ir.sajjadyosefi.android.tubeless.adapter.EndlessList_Adapter;
import ir.sajjadyosefi.android.tubeless.classes.CommonClass;
import ir.sajjadyosefi.android.tubeless.networkLayout.networkLayout.RestApiHelper;

import ir.sajjadyosefi.android.tubeless.Global;
import ir.sajjadyosefi.android.xTubeless.classes.modelY.Blog.BlogItem;
import ir.sajjadyosefi.android.xTubeless.classes.modelY.Blog.BlogItemListResponse;


public class AsyncLoadBlogItemList extends AsyncTask {

    private final Context mContext;
    private final DilatingDotsProgressBar mProgressBar;
    private final TextView mTextViewNoting;
    private final SwipeRefreshLayout mSwipeRefreshLayout;
    private final RecyclerView mRecyclerViewTimeline;
    private String term;
    private List<Object> mTimelineItemList;
    private final EndlessList_Adapter mPostsAdapter;

    //View
    private boolean loading;
    private boolean animation = false ;
    public int listType = 0;
    public int idHeader = 0;

    public AsyncLoadBlogItemList(Context context, DilatingDotsProgressBar progressBar, TextView textViewNoting, SwipeRefreshLayout swipeRefreshLayoutNews, final RecyclerView recyclerViewTimeline, List<Object> timelineItemList, EndlessList_Adapter mPostsAdapter, int listType, int idHeader) {
        this.idHeader = idHeader;
        this.mContext = context ;
        this.mProgressBar = progressBar;
        this.mTextViewNoting = textViewNoting;
        this.mSwipeRefreshLayout = swipeRefreshLayoutNews ;
        this.mRecyclerViewTimeline = recyclerViewTimeline;
        this.mTimelineItemList = timelineItemList;
        this.mPostsAdapter = mPostsAdapter ;
        this.listType = listType;
    }

    public AsyncLoadBlogItemList(Context context, DilatingDotsProgressBar progressBar, TextView textViewNoting, SwipeRefreshLayout swipeRefreshLayoutNews, final RecyclerView recyclerViewTimeline, List<Object> timelineItemList, EndlessList_Adapter mPostsAdapter, int listType, String term, int idHeader) {
        this.idHeader = idHeader;
        this.mContext = context ;
        this.mProgressBar = progressBar;
        this.mTextViewNoting = textViewNoting;
        this.mSwipeRefreshLayout = swipeRefreshLayoutNews ;
        this.mRecyclerViewTimeline = recyclerViewTimeline;
        this.mTimelineItemList = timelineItemList;
        this.mPostsAdapter = mPostsAdapter ;
        this.listType = listType;
        this.term = term;
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
            BlogItemListResponse timelineResponse;
            if(term == null)
                timelineResponse = myParser.GetBlogList(mContext,  Global.getIndexByTabType(idHeader),idHeader);
            else
                timelineResponse = myParser.GetSearchYafteResult(mContext, term,idHeader);

            if (timelineResponse != null)
                return timelineResponse.getBlogItem();
            return null;
        }else
            return null;
    }

    @Override
    protected void onPostExecute(Object result) {
        super.onPostExecute(result);

        if((List<BlogItem>)result != null)
            Fill_Home((List<BlogItem>)result , true);
        Global.increamentIndex(idHeader);
    }
    private void Fill_Home(List<BlogItem> result, Boolean showHome) {
        if (mProgressBar != null)
            mProgressBar.hideNow();

        if(idHeader == 16 || idHeader == 17 ||idHeader == 18 ){
            if(result.size() == 0 && (term != null)){
                term = null;
                LayoutInflater inflater = ((Activity)mContext).getLayoutInflater();
                View dialoglayout = inflater.inflate(R.layout.notfound_layout, null);
                final AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
                builder.setView(dialoglayout);
                final AlertDialog alertDialog1 = builder.create();
                alertDialog1.getWindow().getAttributes().windowAnimations = R.style.alertDialogAnimation;


                final Button cancelBtn = (Button) dialoglayout.findViewById(R.id.buttonCancel);
                final Button downloadBtn = (Button) dialoglayout.findViewById(R.id.buttonDownload);
                downloadBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(mContext,NewYafteActivity.class);
                        Bundle bundle = new Bundle();
                        bundle.putInt(ContactUsActivity.Type,16);
                        intent.putExtras(bundle);
                        ((Activity)mContext).startActivity(intent);
                        ((Activity)mContext).overridePendingTransition(R.anim.fadeout, R.anim.fadein);
                        alertDialog1.cancel();

                    }
                });

                cancelBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        alertDialog1.cancel();
                    }
                });


                try {
                    alertDialog1.show();
                }catch (Exception ex){

                }

            }
        }
        if (mTimelineItemList != null) {

            if (result != null) {
                if (result.size() >= 0) {

                    mTimelineItemList.addAll(result);
                    mPostsAdapter.notifyDataSetChanged();

//                    Pages_Adapter adapter_page = new Pages_Adapter(mContext,(Activity)mContext,  result, mRecyclerViewTimeline, mLinearLayoutManager, showHome, mProgressBar);
//                    mRecyclerViewTimeline.setAdapter(adapter_page);
                }
//                else if(result.getPages().size() == 0) {
//                    new SweetAlertDialog(context,SweetAlertDialog.WARNING_TYPE)
//                            .setTitleText("")
//                            .setContentText(context.getString(R.string.nothingToShow))
//                            .show();
//                }
            }
            // else {
//                mRecyclerViewTimeline.setAdapter(null);
//                Toast.makeText(xMainActivity.this, "Nothing to show !", Toast.LENGTH_SHORT).show();
//                mTextViewNoting.setText("Nothing To Show !");
//                new SweetAlertDialog(context, SweetAlertDialog.WARNING_TYPE)
//                        .setTitleText("")
//                        .setContentText(context.getString(R.string.nothingToShow))
//                        .show();
//            }
//
            mSwipeRefreshLayout.setRefreshing(false);
        }
    }
}
