package ir.sajjadyosefi.android.tubeless.view.fragment.minor;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.astuetz.PagerSlidingTabStrip;

import ir.sajjadyosefi.android.tubeless.adapter.fragment.FragmentPoliceAdapter;
import ir.sajjadyosefi.android.tubeless.R;

//import com.astuetz.PagerSlidingTabStripDefault;

/**
 * Created by sajjad on 08/14/2016.
 */
// In this case, the fragment displays simple text based on the page
@SuppressLint("ValidFragment")
public class FragmentPolice extends Fragment {
    public static Context context;
    public static int mIndex;

    public static final String ARG_PAGE = "ARG_PAGE";


    public FragmentPolice() {


    }
    public FragmentPolice(Context context) {
        this.context = context;
    }

    private int mPage;
    public FloatingActionButton floatingActionButton, floatingActionButton2;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.main_home_fragment_page_sajjadtest, container, false);
        ViewPager viewPager = (ViewPager) view.findViewById(R.id.viewpager);
        viewPager.setAdapter(new FragmentPoliceAdapter(context,getChildFragmentManager()));

        PagerSlidingTabStrip tabsStrip = (PagerSlidingTabStrip) view.findViewById(R.id.tabs);
        tabsStrip.setViewPager(viewPager);

        return view;
    }


//
//    //8888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888
//
//    Page page = new Page();
//    Posts posts = new Posts();
//    EndlessList_Adapter adapter_Posts;
//
//    private void loadHomeList(View view) {
//
//        page.setPage_id("0");
//        mProgressBar = (DilatingDotsProgressBar) view.findViewById(R.id.PBSjd);
//        mRecyclerViewTimeline = (RecyclerView) view.findViewById(R.id.listView);
//        nothingText = (TextView) view.findViewById(R.id.nothing_text);
//        mSwipeRefreshLayout_news = (SwipeRefreshLayout)  view.findViewById(R.id.swipe_refresh_news);
//
//        mLinearLayoutManager = new LinearLayoutManager(context);
//        mRecyclerViewTimeline.setLayoutManager(mLinearLayoutManager);
//        mRecyclerViewTimeline.setHasFixedSize(true);
//        mRecyclerViewTimeline.setItemAnimator(new DefaultItemAnimator());
//        adapter_Posts = new EndlessList_Adapter(context,mProgressBar,nothingText,mSwipeRefreshLayout_news,mRecyclerViewTimeline, posts, page,null);
//        mRecyclerViewTimeline.setAdapter(adapter_Posts);
//
//        mSwipeRefreshLayout_news.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
//            @Override
//            public void onRefresh() {
//                adapter_Posts = new EndlessList_Adapter(context,mProgressBar,nothingText,mSwipeRefreshLayout_news,mRecyclerViewTimeline, posts, page,null);
//                mRecyclerViewTimeline.setAdapter(adapter_Posts);
//            }
//        });
//    }
//
//
//    //8888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888
//
//
//
//    ///////////////////////////////////////////////////////////////////////////////////////////////////////
//    boolean firstLogin = true;
//    RecyclerView mRecyclerViewTimeline;
//    private TextView nothingText;
//    private SwipeRefreshLayout mSwipeRefreshLayout_news;
//    LinearLayoutManager mLinearLayoutManager;
//    public DilatingDotsProgressBar mProgressBar;
//
//
//    private void loadPagesList(View view) {
//        mRecyclerViewTimeline = (RecyclerView) view.findViewById(R.id.listView);
//        nothingText = (TextView) view.findViewById(R.id.nothing_text);
//        mSwipeRefreshLayout_news = (SwipeRefreshLayout)  view.findViewById(R.id.swipe_refresh_news);
//        mProgressBar = (DilatingDotsProgressBar) view.findViewById(R.id.PBSjd);
//
//        mLinearLayoutManager = new LinearLayoutManager(context);
//        mRecyclerViewTimeline.setLayoutManager(mLinearLayoutManager);
//
//        new AsyncLoad_Home().execute();
//
//        mSwipeRefreshLayout_news.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
//            @Override
//            public void onRefresh() {
//                new AsyncLoad_Home().execute();
//            }
//        });
//    }
//
//    private class AsyncLoad_Home extends AsyncTask<Void, Void, Pages> {
//
//        @Override
//        protected void onPreExecute() {
//            super.onPreExecute();
//
//            //mProgressBar.showNow();
//            //Load Offlines
//            Pages result = Pages.getOfflinePages();
//            Pages result2 = new Pages();
//            for (int i = result.getPages().size() ; i > 0; i--) {
//                result2.addItem(result.getPages().get(i-1));
//            }
//            Fill_Home(result2,true);
//        }
//
//        @Override
//        protected Pages doInBackground(Void... params) {
//            Pages result = null,result2 = null;
//            if (mahanApplicationGlobal.isNetworkConnected()) {
//                RestApiHelper myParser = new RestApiHelper();
//
//                if (firstLogin) {
//                    firstLogin = false;
//                    if (context != null)
//                        result =  myParser.getPages(true, values.getString(Statics.COOKIES, ""), context);
//                    else
//                        return null;
//                } else
//                    if (context != null)
//                        result = myParser.getPages(false, values.getString(Statics.COOKIES, "") + " " + values.getString("cookies_login", ""), context);
//                    else
//                        return null;
//
//                if(result == null)
//                    result = new Pages();
//
//                if(mahanApplicationGlobal.isNetworkConnected())
//                {
//                    Page homePage = new Page();
//                    homePage.setTitle("Home");
//                    homePage.setNotify("0");
//                    homePage.setPage_id("0");
//                    homePage.setCover("");
//                    homePage.setUser_image("");
//                    result.addItem(homePage);
//                }
//
//                Pages.DeleteOfflinePages();
//                Pages.insertPages(result);
//
//                result2 = new Pages();
//                for (int i = result.getPages().size() ; i > 0; i--) {
//                    result2.addItem(result.getPages().get(i-1));
//                }
//            }
//            return result2;
//        }
//
//        @Override
//        protected void onPostExecute(Pages result) {
//            if(result != null)
//                Fill_Home(result , true);
//        }
//    }
//    private void Fill_Home(Pages result,Boolean showHome) {
//        //mProgressBar.hideNow();
//        if (result != null) {
//
////            li_post.setVisibility(View.GONE);
////            li_profile.setVisibility(View.GONE);
////            li_all.setVisibility(View.VISIBLE);
//
////            nothingText.setText("");
////            text.setText("صفحه اصلی");
////            home_layout.setBackgroundColor(Color.parseColor("#1f1f20"));
////            profile_layout.setBackgroundColor(Color.parseColor("#313539"));
////            friend_layout.setBackgroundColor(Color.parseColor("#313539"));
////            notify_layout.setBackgroundColor(Color.parseColor("#313539"));
////            home_sel.setVisibility(View.VISIBLE);
////            profile_sel.setVisibility(View.INVISIBLE);
////            friend_sel.setVisibility(View.INVISIBLE);
////            notify_sel.setVisibility(View.INVISIBLE);
//
//
//
//            if (result != null) {
//                if (result.getPages().size() >= 0) {
//                    Pages_Adapter adapter_page = new Pages_Adapter(context,(xMainActivity)context,  result, mRecyclerViewTimeline, mLinearLayoutManager, showHome, mProgressBar);
//                    mRecyclerViewTimeline.setAdapter(adapter_page);
//                }
//                else if(result.getPages().size() == 0) {
//                    new SweetAlertDialog(context,SweetAlertDialog.WARNING_TYPE)
//                            .setTitleText("")
//                            .setContentText(context.getString(R.string.nothingToShow))
//                            .show();
//                }
//            } else {
//                mRecyclerViewTimeline.setAdapter(null);
////                Toast.makeText(xMainActivity.this, "Nothing to show !", Toast.LENGTH_SHORT).show();
//                nothingText.setText("Nothing To Show !");
//                new SweetAlertDialog(context, SweetAlertDialog.WARNING_TYPE)
//                        .setTitleText("")
//                        .setContentText(context.getString(R.string.nothingToShow))
//                        .show();
//            }
//
//            mSwipeRefreshLayout_news.setRefreshing(false);
//        }
//    }
//    ///////////////////////////////////////////////////////////////////////////////////////////////////////

}