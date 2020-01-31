package ir.sajjadyosefi.android.tubeless.view.xFragment;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;

import com.zl.reik.dilatingdotsprogressbar.DilatingDotsProgressBar;

import ir.sajjadyosefi.android.tubeless.adapter.fragment.Notify_AD_SajjadTest;

import ir.sajjadyosefi.android.tubeless.R;
import ir.sajjadyosefi.android.tubeless.networkLayout.networkLayout.RestApiHelper;
import ir.sajjadyosefi.android.xTubeless.classes.modelY.ResponseNotification;

import static ir.sajjadyosefi.android.tubeless.view.fragment.minor.FragmentTimelineMinor.ARG_LIST;
import static ir.sajjadyosefi.android.tubeless.view.fragment.minor.FragmentTimelineMinor.ARG_PAGE;

/**
 * Created by sajjad on 08/14/2016.
 */
// In this case, the fragment displays simple text based on the page
@SuppressLint("ValidFragment")
public class FragmentNotifications extends Fragment {

    RecyclerView recyclerList ;
    public static SwipeRefreshLayout mSwipeRefreshLayout_list;
    public Notify_AD_SajjadTest adapter_notify;
    LinearLayoutManager llm2;
    DilatingDotsProgressBar progressBar;
    RadioGroup radioGroup;

    public FragmentNotifications() {
    }

    public static FragmentNotifications newInstance(int page, int list) {
        //this.context = context ;
        Bundle args = new Bundle();
        args.putInt(ARG_PAGE, page);
        args.putInt(ARG_LIST, list);
        FragmentNotifications fragment = new FragmentNotifications();
        fragment.setArguments(args);
        //this.values = context.getSharedPreferences(Statics.MAHAN, 0);
        return fragment;
    }

    public FragmentNotifications(Context context) {
        this.context = context;
    }
    public Context context;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

//        final View view = inflater.inflate(R.layout.friend_request_fragment_page_sajjadtest, container, false);
        final View view = inflater.inflate(R.layout.empty_fragment_sajjadtest, container, false);
//        radioGroup = (RadioGroup) view.findViewById(R.id.rdogrp);
//        radioGroup.getLayoutParams().height = 0 ;
//
//        recyclerList = (RecyclerView) view.findViewById(R.id.recyclerList);
//        mProgressBar = (DilatingDotsProgressBar) view.findViewById(R.id.PBSjd);
//        mSwipeRefreshLayout_list = (SwipeRefreshLayout)view.findViewById(R.id.swipe_refresh_list);
//        mSwipeRefreshLayout_list.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
//            @Override
//            public void onRefresh() {
//                new AsyncLoadNotify(context,recyclerList,mProgressBar, mSwipeRefreshLayout_list).execute();
//            }
//        });
//
//        mLinearLayoutManager = new LinearLayoutManager(context);
//        recyclerList.setLayoutManager(mLinearLayoutManager);
//
//        //new AsyncLoadFriendRequests(context,recyclerList,mProgressBar, mSwipeRefreshLayout_list).execute();
//        new AsyncLoadNotify(context,recyclerList,mProgressBar, mSwipeRefreshLayout_list).execute();
        return view;
    }

    private class AsyncLoadNotify extends AsyncTask<Void, Void, ResponseNotification> {

        private final Context context;
        private final RecyclerView recyclerList;
        private final DilatingDotsProgressBar progressBar;
        private final SwipeRefreshLayout mSwipeRefreshLayout_list;

        public AsyncLoadNotify(Context context, RecyclerView recyclerList, DilatingDotsProgressBar progressBar, SwipeRefreshLayout mSwipeRefreshLayout_list) {
            this.context = context ;
            this.recyclerList = recyclerList ;
            this.progressBar = progressBar ;
            this.mSwipeRefreshLayout_list = mSwipeRefreshLayout_list ;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressBar.showNow();
            mSwipeRefreshLayout_list.setRefreshing(true);
        }

        @Override
        protected ResponseNotification doInBackground(Void... params) {
            RestApiHelper myParser = new RestApiHelper();
            //return myParser.getNotificationfromserver();

            return null;

        }

        @Override
        protected void onPostExecute(ResponseNotification serverNotification) {
            super.onPostExecute(serverNotification);
            progressBar.hideNow();
            mSwipeRefreshLayout_list.setRefreshing(false);

            if (serverNotification != null) {
                if (serverNotification.getNotification().getItems() != null && serverNotification.getNotification().getItems().size() >= 1) {
                    adapter_notify = new Notify_AD_SajjadTest(
                            ((Activity) context), serverNotification.getNotification(), recyclerList, llm2);
                    recyclerList.setLayoutManager(new LinearLayoutManager(context));
                    recyclerList.setAdapter(adapter_notify);

//                adapter_friendRequest = new FriendRequest_AD_SajjadTest(context, responseFriendRequests.getRequests(), recyclerList,mProgressBar);
//                recyclerList.setLayoutManager(new LinearLayoutManager(context));
//                recyclerList.setAdapter(adapter_friendRequest);

                }else {
                    recyclerList.setVisibility(View.GONE);
                }

            } else {

//                new SweetAlertDialog(xMainActivity.this, SweetAlertDialog.ERROR_TYPE).setTitleText("Error")
//                        .setContentText(xMainActivity.this.getString(R.string.NOTING_TO_SHOW))
//                        .show();
            }
        }

    }

}