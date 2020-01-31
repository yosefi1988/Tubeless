package ir.sajjadyosefi.android.tubeless.asyncTask.timeline;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.zl.reik.dilatingdotsprogressbar.DilatingDotsProgressBar;

import java.util.List;
import java.util.Random;

import ir.sajjadyosefi.android.tubeless.adapter.EndlessList_Adapter;
import ir.sajjadyosefi.android.tubeless.adapter.fragment.FragmentTimelineAdapter;
import ir.sajjadyosefi.android.tubeless.classes.CommonClass;
import ir.sajjadyosefi.android.xTubeless.classes.modelY.TimelineItemBase;

import ir.sajjadyosefi.android.tubeless.Global;
import ir.sajjadyosefi.android.tubeless.networkLayout.networkLayout.RestApiHelper;
import ir.sajjadyosefi.android.xTubeless.classes.modelY.NetworkLayout.TimelineResponse;


public class AsyncLoadTimeline extends AsyncTask {

    private final Context mContext;
    private final DilatingDotsProgressBar mProgressBar;
    private final TextView mTextViewNoting;
    private final SwipeRefreshLayout mSwipeRefreshLayout;
    private final RecyclerView mRecyclerViewTimeline;
    private List<Object> mTimelineItemList;
    private final EndlessList_Adapter mPostsAdapter;

    //View
    private boolean loading;
    private boolean animation = false ;
    public int listType = 0;
    public int idHeader = 0;


    public AsyncLoadTimeline(Context context, DilatingDotsProgressBar progressBar, TextView textViewNoting, SwipeRefreshLayout swipeRefreshLayoutNews, final RecyclerView recyclerViewTimeline, List<Object> timelineItemList, EndlessList_Adapter mPostsAdapter, int listType) {
        this.mContext = context ;
        this.mProgressBar = progressBar;
        this.mTextViewNoting = textViewNoting;
        this.mSwipeRefreshLayout = swipeRefreshLayoutNews ;
        this.mRecyclerViewTimeline = recyclerViewTimeline;
        this.mTimelineItemList = timelineItemList;
        this.mPostsAdapter = mPostsAdapter ;
        this.listType = listType;
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
            if (listType == FragmentTimelineAdapter.LIST_TIMELINE) {
                RestApiHelper myParser = new RestApiHelper();
                TimelineResponse timelineResponse = myParser.GetTimeline(mContext, Global.getIndexByTabType(0));
                if(timelineResponse != null)
                    return timelineResponse.getTimelineBase();
                else
                    return null;
            } else
                return null;
        }else
            return null;
    }

    @Override
    protected void onPostExecute(Object result) {
        super.onPostExecute(result);

        if((List<TimelineItemBase>)result != null)
            Fill_Home((List<TimelineItemBase>)result , true);

        if (result != null) {
            //add addad
            Random random = new Random();
            int randNum = random.nextInt(((List<TimelineItemBase>) result).size() + 1);
            TimelineItemBase addadItem = new TimelineItemBase();
            addadItem.setID_TimelineType(100);  //"addad"
            addadItem.setNmae("adad");  //"addad"

            ((List<Object>) result).add(randNum, addadItem);

            for (Object item : (List<Object>) result) {
                mTimelineItemList.add(item);
            }
            mPostsAdapter.notifyDataSetChanged();
            Global.increamentIndex(0);
        }
    }
    private void Fill_Home(List<TimelineItemBase> result, Boolean showHome) {
        if (mProgressBar != null)
            mProgressBar.hideNow();

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
