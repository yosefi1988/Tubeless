package ir.sajjadyosefi.android.tubeless.asyncTask.timeline;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.zl.reik.dilatingdotsprogressbar.DilatingDotsProgressBar;

import java.util.List;

import ir.sajjadyosefi.android.tubeless.adapter.EndlessList_Adapter;
import ir.sajjadyosefi.android.tubeless.classes.CommonClass;
import ir.sajjadyosefi.android.tubeless.classes.networkLayout.RestApiHelper;
import ir.sajjadyosefi.android.tubeless.classes.model.NetworkLayout.TimelineResponse;
import ir.sajjadyosefi.android.tubeless.classes.model.TimelineItemBase;


public class AsyncCredit extends AsyncTask {

    private final Context mContext;
    private final DilatingDotsProgressBar mProgressBar;
    private final TextView mTextViewNoting;
    private final SwipeRefreshLayout mSwipeRefreshLayout;
    private final RecyclerView mRecyclerViewTimeline;
    private int mIndex;
    private List<TimelineItemBase> mTimelineItemList;
    private final EndlessList_Adapter mPostsAdapter;

    //View
    private boolean loading;
    private boolean animation = false ;



    public AsyncCredit(Context context, DilatingDotsProgressBar progressBar, TextView textViewNoting, SwipeRefreshLayout swipeRefreshLayoutNews, final RecyclerView recyclerViewTimeline, List<TimelineItemBase> timelineItemList, EndlessList_Adapter mPostsAdapter, int mIndex) {
        this.mContext = context ;
        this.mProgressBar = progressBar;
        this.mTextViewNoting = textViewNoting;
        this.mSwipeRefreshLayout = swipeRefreshLayoutNews ;
        this.mRecyclerViewTimeline = recyclerViewTimeline;
        this.mTimelineItemList = timelineItemList;
        this.mPostsAdapter = mPostsAdapter ;
        this.mIndex = mIndex;

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
            TimelineResponse timelineResponse =  myParser.GetTimeline(mContext,mIndex);
            return  timelineResponse.getTimelineBase();
        }else
            return null;
    }

    @Override
    protected void onPostExecute(Object result) {
        super.onPostExecute(result);

        if((List<TimelineItemBase>)result != null)
            Fill_Home((List<TimelineItemBase>)result , true);

//        if (result != null) {
//            //add addad
//            Random random = new Random();
//            int randNum = random.nextInt(result.size() + 1);
//            NoticeItem addadItem = new NoticeItem();
//            addadItem.setItemType("addad");
//            result.add(randNum, addadItem);
//
//
//            for (TimelineItem item : result) {
//                mTimelineItemList.add(item);
//            }
//            mPostsAdapter.notifyDataSetChanged();
            mIndex++;
//        }
    }
    private void Fill_Home(List<TimelineItemBase> result, Boolean showHome) {
        if (mProgressBar != null)
            mProgressBar.hideNow();

        if (mTimelineItemList != null) {

//            li_post.setVisibility(View.GONE);
//            li_profile.setVisibility(View.GONE);
//            li_all.setVisibility(View.VISIBLE);

//            mTextViewNoting.setText("");
//            title.setText("صفحه اصلی");
//            home_layout.setBackgroundColor(Color.parseColor("#1f1f20"));
//            profile_layout.setBackgroundColor(Color.parseColor("#313539"));
//            friend_layout.setBackgroundColor(Color.parseColor("#313539"));
//            notify_layout.setBackgroundColor(Color.parseColor("#313539"));
//            home_sel.setVisibility(View.VISIBLE);
//            profile_sel.setVisibility(View.INVISIBLE);
//            friend_sel.setVisibility(View.INVISIBLE);
//            notify_sel.setVisibility(View.INVISIBLE);



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
//                Toast.makeText(MainActivity.this, "Nothing to show !", Toast.LENGTH_SHORT).show();
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
