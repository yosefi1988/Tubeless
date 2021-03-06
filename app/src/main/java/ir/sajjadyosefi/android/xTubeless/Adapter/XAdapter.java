package ir.sajjadyosefi.android.xTubeless.Adapter;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.squareup.picasso.Picasso;
import com.zl.reik.dilatingdotsprogressbar.DilatingDotsProgressBar;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import ir.sajjadyosefi.android.xTubeless.Fragment.ListFragment;
import ir.sajjadyosefi.android.xTubeless.R;
import ir.sajjadyosefi.android.xTubeless.Global;
import ir.sajjadyosefi.android.xTubeless.activity.activities.TubelessActivity;
import ir.sajjadyosefi.android.xTubeless.activity.common.ContainerActivity;
import ir.sajjadyosefi.android.xTubeless.classes.StaticValue;
import ir.sajjadyosefi.android.xTubeless.classes.model.category.CategoryItem;
import ir.sajjadyosefi.android.xTubeless.classes.model.filter.CategoryFiltersNode;
import ir.sajjadyosefi.android.xTubeless.classes.model.mainList.MainListItem;
import ir.sajjadyosefi.android.xTubeless.classes.model.network.responses.post.PostSearchResponseItem;
import ir.sajjadyosefi.android.xTubeless.classes.model.post.NewTimelineItem;
import ir.sajjadyosefi.android.xTubeless.classes.model.post.NotiesItem;
import ir.sajjadyosefi.android.xTubeless.classes.model.post.PictureItem;
import ir.sajjadyosefi.android.xTubeless.classes.model.post.TextItem;
import ir.sajjadyosefi.android.xTubeless.classes.model.post.blog.CommentItem;
import ir.sajjadyosefi.android.xTubeless.classes.model.response.CommentListResponse;
import ir.sajjadyosefi.android.xTubeless.classes.model.response.MainListResponse;
import ir.sajjadyosefi.android.xTubeless.classes.model.response.NewTimelineListResponse;
import ir.sajjadyosefi.android.xTubeless.classes.model.response.category.CategoryListResponse;
import ir.sajjadyosefi.android.xTubeless.classes.model.viewHolder.CommentItemViewHolder;
import ir.sajjadyosefi.android.xTubeless.classes.model.viewHolder.CategoryViewHolder;
import ir.sajjadyosefi.android.xTubeless.classes.model.viewHolder.PostItemViewHolder;
import ir.sajjadyosefi.android.xTubeless.classes.model.viewHolder.PostViewHolder;
import ir.sajjadyosefi.android.xTubeless.classes.model.viewHolder.TimelineItemViewHolder;
import ir.sajjadyosefi.android.xTubeless.classes.model.viewHolder.TwoLinesViewHolder;
import ir.sajjadyosefi.android.xTubeless.networkLayout.retrofit.TubelessRetrofitCallbackss;
import ir.sajjadyosefi.android.xTubeless.networkLayout.retrofit.tmp.TimelineItem;
import ir.sajjadyosefi.android.xTubeless.networkLayout.retrofit.tmp.TimelineListResponse;
import ir.sajjadyosefi.android.xTubeless.utility.CommonClass;

import ir.sajjadyosefi.android.xTubeless.utility.DialogUtil;
import ir.sajjadyosefi.android.xTubeless.widget.recyclerview.EndlessRecyclerOnScrollListener;
import retrofit2.Call;

import static ir.sajjadyosefi.android.xTubeless.Adapter.FirstFragmentsAdapter.TYPE_BOURSE_ANALIZE_All;
import static ir.sajjadyosefi.android.xTubeless.Adapter.FirstFragmentsAdapter.TYPE_BOURSE_ANALIZE_Old;
import static ir.sajjadyosefi.android.xTubeless.Adapter.FirstFragmentsAdapter.TYPE_BOURSE_NEWS;
import static ir.sajjadyosefi.android.xTubeless.Adapter.FirstFragmentsAdapter.TYPE_BOURSE_TRAIN;
import static ir.sajjadyosefi.android.xTubeless.Adapter.FirstFragmentsAdapter.TYPE_COMMENTS;
import static ir.sajjadyosefi.android.xTubeless.Adapter.FirstFragmentsAdapter.TYPE_LIST;
import static ir.sajjadyosefi.android.xTubeless.Adapter.FirstFragmentsAdapter.TYPE_LIST_CATEGORIES_DATA;
import static ir.sajjadyosefi.android.xTubeless.Adapter.FirstFragmentsAdapter.TYPE_LIST_CATEGORY;
import static ir.sajjadyosefi.android.xTubeless.Adapter.FirstFragmentsAdapter.TYPE_SELECT_CATEGORY;
import static ir.sajjadyosefi.android.xTubeless.Adapter.FirstFragmentsAdapter.TYPE_POST_SEARCH_RESULT;
import static ir.sajjadyosefi.android.xTubeless.Adapter.FirstFragmentsAdapter.TYPE_YADAK;
import static ir.sajjadyosefi.android.xTubeless.Adapter.FirstFragmentsAdapter.TYPE_YAFTE;

import static org.litepal.LitePalApplication.getContext;


public class XAdapter extends RecyclerView.Adapter<PostViewHolder> implements ITubelessAdapter {

    private CategoryFiltersNode categoryFiltersNode;
    private View rootView;
    //private final int scrollHeight;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private Bundle bundle;
    private RecyclerView recyclerView;
    private Picasso picasso;
    //private int navigationHeight;
//    private List<IItems> data;
    private Fragment fragment;

    XAdapter adapter;
    int listType;
    //private boolean hasAppBarLayout;
    private Context context;

    //scroll
    public LinearLayoutManager mLayoutManager = null ;

    EndlessRecyclerOnScrollListener onScrollListener;

    public XAdapter(int listType, final Context context, View rootView, RecyclerView recyclerView, LinearLayoutManager linearLayoutManager, SwipeRefreshLayout mSwipeRefreshLayout, Fragment fragment, Bundle bundle) {
         new XAdapter(listType, null, context, rootView, recyclerView, linearLayoutManager, mSwipeRefreshLayout, fragment, bundle) ;
    }

    public XAdapter(int listType, CategoryFiltersNode categoryFiltersNode, final Context context, View rootView, RecyclerView recyclerView, LinearLayoutManager linearLayoutManager, SwipeRefreshLayout mSwipeRefreshLayout, Fragment fragment, Bundle bundle) {

        this.listType = listType;
        this.categoryFiltersNode = categoryFiltersNode;
        //this.navigationHeight = navigationHeight;
        this.fragment = fragment;
        //this.hasAppBarLayout = hasAppBarLayout;
        this.picasso = Picasso.get();
        this.context = context;
        this.rootView = rootView;
        //this.scrollHeight = ToolbarHeight;
        this.mLayoutManager = linearLayoutManager ;
        this.recyclerView = recyclerView;
        this.mSwipeRefreshLayout = mSwipeRefreshLayout ;
        this.adapter = this;
        this.bundle = bundle;

        if (((ListFragment)fragment).list == null)
            ((ListFragment)fragment).list = new ArrayList<>();


        onScrollListener = new EndlessRecyclerOnScrollListener(mLayoutManager) {
            @Override
            protected void onScrollUp() {
//                if (!floatingActionButton.isShown()) {
//                    floatingActionButton.show();
//                }
            }

            @Override
            protected void onScrollDown() {
//                if (floatingActionButton.isShown()) {
//                    floatingActionButton.hide();
//                }
            }

            @Override
            public void onLoadMore(int current_page) {
                loadTimeline(context,current_page,false);
            }
        };

        recyclerView.addOnScrollListener(onScrollListener);
        firstLoadAndRefresh(context);
    }

    private final static int FADE_DURATION = 500; //FADE_DURATION in milliseconds


    private void firstLoadAndRefresh(Context context) {
//        mSwipeRefreshLayout.setOnRefreshListener(() -> {
//            mTimelineItemList.clear();
//            onScrollListener.resetCounter();
//            Handler mHandler = new Handler();
//            mHandler.postDelayed(() -> mSwipeRefreshLayout.setRefreshing(false), 500);
//            loadTimeline(context,1,true);
//        });
        loadTimeline(context,1,false);

        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                onScrollListener.reset();

//                onScrollListener.resetCounter();


                ((ListFragment)fragment).list.clear();

//                if(listType == TYPE_YAFTE){
//                }else
//                if(listType == TYPE_YADAK){
//                    ((ListFragment)fragment).list.clear();
//                }else if(listType == TYPE_IMAGE){
//                }

                loadTimeline(context,1,true);
                mSwipeRefreshLayout.setRefreshing(false);


            }
        });
    }

    private void loadTimeline(Context context,int current_page, boolean isRefresh) {

        if (listType == TYPE_LIST_CATEGORIES_DATA) {
            TubelessRetrofitCallbackss callbackss = new TubelessRetrofitCallbackss(getContext(), NewTimelineListResponse.class) {
                @Override
                public void t_beforeSendRequest() {

                }

                @Override
                public void t_afterGetResponse() {
                    ((TubelessActivity)context).progressDialog.hide();
                }

                @Override
                public void t_complite() {

                }

                @Override
                public void t_responseNull() {

                }

                @Override
                public void t_retry(Call<Object> call) {

                }

                @Override
                public void t_onSuccess(Object response) {
                    NewTimelineListResponse responseX = (NewTimelineListResponse) response;
                    //                    data.add(new NotiesItem());

                    for (NewTimelineItem item : responseX.getTimelineList()) {
                        //                        item.setType(Tubeless_ITEM_TYPE);
                        ((ListFragment) fragment).list.add(item);
                        if (isRefresh) {
                            adapter.notifyDataSetChanged();
                        } else {
                            adapter.notifyItemInserted(((ListFragment) fragment).list.size());
                        }
                    }


                }
            };

            String selectedCatIds = bundle.getString("ids");

            Global.apiManagerTubeless.getTimeline(context, current_page - 1, callbackss,selectedCatIds);
        }else if (listType == TYPE_SELECT_CATEGORY || listType == TYPE_LIST_CATEGORY) {
            TubelessRetrofitCallbackss ssssssss = new TubelessRetrofitCallbackss(getContext(), CategoryListResponse.class) {
                @Override
                public void t_beforeSendRequest() {

                }

                @Override
                public void t_afterGetResponse() {

                }

                @Override
                public void t_complite() {

                }

                @Override
                public void t_responseNull() {

                }

                @Override
                public void t_retry(Call<Object> call) {

                }

                @Override
                public void t_onSuccess(Object response) {
                    CategoryListResponse responseX = (CategoryListResponse) response;
//                    data.add(new NotiesItem());

                    for (CategoryItem item : responseX.getCategoryItems()){
//                        item.setType(Tubeless_ITEM_TYPE);
                        ((ListFragment)fragment).list.add(item);
                        if (isRefresh) {
                            adapter.notifyDataSetChanged();
                        }else {
                            adapter.notifyItemInserted(((ListFragment)fragment).list.size());
                        }
                    }

                    ((TubelessActivity)context).progressDialog.hide();

                    if (current_page == 1 && ((ListFragment)fragment).list.size() == 0){
                        rootView.findViewById(R.id.empty_view).setVisibility(View.VISIBLE);
                    }
                }
            };
            Global.apiManagerTubeless.getCategory(categoryFiltersNode.getCatId(),current_page - 1, ssssssss);

//            MainListResponse responseX = new MainListResponse();
//            List<MainListItem> dddddd = new ArrayList<>();
//            TimelineItem dddddddddd = new TimelineItem();
//            dddddddddd.setTitle("Group One");
//            dddddddddd.setText("group One Text");
//            Date date = new Date();
//            dddddddddd.setDate(date.getTime() + "");
//            dddddddddd.setRegisterDate(date.getTime() + "");
//            dddddddddd.setPicture("https://flutter-learn.ir/wp-content/uploads/2019/09/maxresdefault-100x75.png");
//
//            MainListItem sssssssssssss = new MainListItem(dddddddddd);
//            dddddd.add(sssssssssssss);
//            responseX.setMainListItems(dddddd);
//            for (MainListItem item : responseX.getMainListItems()){
//                ((ListFragment)fragment).list.add(item);
//                if (isRefresh) {
//                    adapter.notifyDataSetChanged();
//                }else {
//                    adapter.notifyItemInserted(((ListFragment)fragment).list.size());
//                }
//            }

        } else if (listType == TYPE_LIST) {
//            TubelessRetrofitCallbackss ssssssss = new TubelessRetrofitCallbackss(getContext(), NewTimelineListResponse.class) {
//                @Override
//                public void t_beforeSendRequest() {
//
//                }
//
//                @Override
//                public void t_afterGetResponse() {
//
//                }
//
//                @Override
//                public void t_complite() {
//
//                }
//
//                @Override
//                public void t_responseNull() {
//
//                }
//
//                @Override
//                public void t_retry(Call<Object> call) {
//
//                }
//
//                @Override
//                public void t_onSuccess(Object response) {
//                    NewTimelineListResponse responseX = (NewTimelineListResponse) response;
////                    data.add(new NotiesItem());
//
//                    for (NewTimelineItem item : responseX.getTimelineList()){
////                        item.setType(Tubeless_ITEM_TYPE);
//                        data.add(item);
//                        if (isRefresh) {
//                            adapter.notifyDataSetChanged();
//                        }else {
//                            adapter.notifyItemInserted(data.size());
//                        }
//                    }
//                }
//            };
//            Global.apiManagerTubeless.getTimelineListForX(context,current_page - 1, ssssssss);


            MainListResponse responseX = new MainListResponse();
            List<MainListItem> dddddd = new ArrayList<>();
            TimelineItem dddddddddd = new TimelineItem();
            dddddddddd.setTitle("Group One");
            dddddddddd.setText("group One Text");
            Date date = new Date();
            dddddddddd.setDate(date.getTime() + "");
            dddddddddd.setRegisterDate(date.getTime() + "");
            dddddddddd.setPicture("https://flutter-learn.ir/wp-content/uploads/2019/09/maxresdefault-100x75.png");

            MainListItem sssssssssssss = new MainListItem(dddddddddd);
            dddddd.add(sssssssssssss);
            responseX.setMainListItems(dddddd);
            for (MainListItem item : responseX.getMainListItems()){
                ((ListFragment)fragment).list.add(item);
                if (isRefresh) {
                    adapter.notifyDataSetChanged();
                }else {
                    adapter.notifyItemInserted(((ListFragment)fragment).list.size());
                }
            }
        }else if (listType == TYPE_YADAK) {
            TubelessRetrofitCallbackss ssssssss = new TubelessRetrofitCallbackss(getContext(), NewTimelineListResponse.class) {
                @Override
                public void t_beforeSendRequest() {

                }

                @Override
                public void t_afterGetResponse() {

                }

                @Override
                public void t_complite() {

                }

                @Override
                public void t_responseNull() {

                }

                @Override
                public void t_retry(Call<Object> call) {

                }

                @Override
                public void t_onSuccess(Object response) {
                    NewTimelineListResponse responseX = (NewTimelineListResponse) response;
//                    data.add(new NotiesItem());

                    for (NewTimelineItem item : responseX.getTimelineList()){
//                        item.setType(Tubeless_ITEM_TYPE);
                        ((ListFragment)fragment).list.add(item);
                        if (isRefresh) {
                            adapter.notifyDataSetChanged();
                        }else {
                            adapter.notifyItemInserted(((ListFragment)fragment).list.size());
                        }
                    }


                }
            };

            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(context.getResources().getInteger(R.integer.cat1Yadak));
            stringBuilder.append("_");
            stringBuilder.append(context.getResources().getInteger(R.integer.cat2Yadak));
            stringBuilder.append("_");
            stringBuilder.append(context.getResources().getInteger(R.integer.cat3Yadak));

            Global.apiManagerTubeless.getTimeline(context,current_page - 1, ssssssss,stringBuilder.toString());

        }else if (listType == TYPE_YAFTE) {
            TubelessRetrofitCallbackss callbackss = new TubelessRetrofitCallbackss(getContext(), TimelineListResponse.class) {
                @Override
                public void t_beforeSendRequest() {

                }

                @Override
                public void t_afterGetResponse() {

                }

                @Override
                public void t_complite() {

                }

                @Override
                public void t_responseNull() {

                }

                @Override
                public void t_retry(Call<Object> call) {

                }

                @Override
                public void t_onSuccess(Object response) {
                    TimelineListResponse responseX = (TimelineListResponse) response;
//                Gson gson = new Gson();
//                JsonElement jsonElement = gson.toJsonTree(response.body());
//                TimelineListResponse responseX = gson.fromJson(jsonElement.getAsString(), TimelineListResponse.class);
//                for (TimelineItem item : responseX.getTimelineList()){
//                    item.setType(Tubeless_ITEM_TYPE);
//                    data.add(item);
//                    if (isRefresh) {
//                        adapter.notifyDataSetChanged();
//                    }else {
//                        adapter.notifyItemInserted(data.size());
//                    }
//                }
//                adapter.notifyDataSetChanged();
                    for (TimelineItem item : responseX.getTimelineList()){
//                        item.setType(Tubeless_ITEM_TYPE);
                        NewTimelineItem newTimelineItem = new NewTimelineItem(item);
                        ((ListFragment)fragment).list.add(newTimelineItem);
                        if (isRefresh) {
                            adapter.notifyDataSetChanged();
                        }else {
                            adapter.notifyItemInserted(((ListFragment)fragment).list.size());
                        }
                    }
                    adapter.notifyDataSetChanged();

                }
            };
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(context.getResources().getInteger(R.integer.cat1Yafte));
            stringBuilder.append("_");
            stringBuilder.append(context.getResources().getInteger(R.integer.cat2Yafte));
            stringBuilder.append("_");
            stringBuilder.append(context.getResources().getInteger(R.integer.cat3Yafte));
            Global.apiManagerTubeless.getTimeline(context,current_page - 1, callbackss,stringBuilder.toString());

        }else if (listType == TYPE_BOURSE_TRAIN) {
            TubelessRetrofitCallbackss ssssssss = new TubelessRetrofitCallbackss(getContext(), NewTimelineListResponse.class) {
                @Override
                public void t_beforeSendRequest() {

                }

                @Override
                public void t_afterGetResponse() {

                }

                @Override
                public void t_complite() {

                }

                @Override
                public void t_responseNull() {

                }

                @Override
                public void t_retry(Call<Object> call) {

                }

                @Override
                public void t_onSuccess(Object response) {
                    NewTimelineListResponse responseX = (NewTimelineListResponse) response;

//                Gson gson = new Gson();
//                JsonElement jsonElement = gson.toJsonTree(response.body());
//                TimelineListResponse responseX = gson.fromJson(jsonElement.getAsString(), TimelineListResponse.class);
//                for (TimelineItem item : responseX.getTimelineList()){
//                    item.setType(Tubeless_ITEM_TYPE);
//                    data.add(item);
//                    if (isRefresh) {
//                        adapter.notifyDataSetChanged();
//                    }else {
//                        adapter.notifyItemInserted(data.size());
//                    }
//                }
//                adapter.notifyDataSetChanged();


                    for (NewTimelineItem item : responseX.getTimelineList()){
//                        item.setType(Tubeless_ITEM_TYPE);
                        ((ListFragment)fragment).list.add(item);
//                        if (isRefresh) {
                            if (current_page != 1){
                                adapter.notifyItemChanged(((ListFragment) fragment).list.size());
                            }
//                        adapter.notifyDataSetChanged();
//                        }else {
//                            adapter.notifyItemInserted(data.size());
//                        }
                    }
//                    adapter.notifyDataSetChanged();
                    if (current_page == 1){
                        adapter.notifyDataSetChanged();
                    }
                }
            };

            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(context.getResources().getInteger(R.integer.cat1BourseTrain));
            stringBuilder.append("_");
            stringBuilder.append(context.getResources().getInteger(R.integer.cat2BourseTrain));
            stringBuilder.append("_");
            stringBuilder.append(context.getResources().getInteger(R.integer.cat3BourseTrain));
            Global.apiManagerTubeless.getTimeline(context,current_page - 1, ssssssss,stringBuilder.toString());

        }else if (listType == TYPE_BOURSE_ANALIZE_All) {
            TubelessRetrofitCallbackss ssssssss = new TubelessRetrofitCallbackss(getContext(), NewTimelineListResponse.class) {
                @Override
                public void t_beforeSendRequest() {

                }

                @Override
                public void t_afterGetResponse() {

                }

                @Override
                public void t_complite() {
                }

                @Override
                public void t_responseNull() {

                }

                @Override
                public void t_retry(Call<Object> call) {

                }

                @Override
                public void t_onSuccess(Object response) {
                    NewTimelineListResponse responseX = (NewTimelineListResponse) response;

//                Gson gson = new Gson();
//                JsonElement jsonElement = gson.toJsonTree(response.body());
//                TimelineListResponse responseX = gson.fromJson(jsonElement.getAsString(), TimelineListResponse.class);
//                for (TimelineItem item : responseX.getTimelineList()){
//                    item.setType(Tubeless_ITEM_TYPE);
//                    data.add(item);
//                    if (isRefresh) {
//                        adapter.notifyDataSetChanged();
//                    }else {
//                        adapter.notifyItemInserted(data.size());
//                    }
//                }
//                adapter.notifyDataSetChanged();


                    for (NewTimelineItem item : responseX.getTimelineList()){
//                        item.setType(Tubeless_ITEM_TYPE);
                        ((ListFragment)fragment).list.add(item);
//                        if (isRefresh) {
                        adapter.notifyDataSetChanged();
//                        }else {
//                            adapter.notifyItemInserted(data.size());
//                        }
                    }
                    adapter.notifyDataSetChanged();
                }
            };

            StringBuilder stringBuilder = new StringBuilder();
            switch (StaticValue.bourseState.lastPayedType){
                case 0:
                case 1:
                    stringBuilder.append(context.getResources().getInteger(R.integer.cat1BourseAnalize));
                    break;
                case 2:
                    stringBuilder.append(context.getResources().getInteger(R.integer.cat2BourseAnalize));
                    break;
                case 3:
                    stringBuilder.append(context.getResources().getInteger(R.integer.cat3BourseAnalize));
            }

            Global.apiManagerTubeless.getTimeline(context,current_page - 1, ssssssss,stringBuilder.toString());

        }else if (listType == TYPE_BOURSE_ANALIZE_Old) {
            TubelessRetrofitCallbackss ssssssss = new TubelessRetrofitCallbackss(getContext(), NewTimelineListResponse.class) {
                @Override
                public void t_beforeSendRequest() {

                }

                @Override
                public void t_afterGetResponse() {

                }

                @Override
                public void t_complite() {

                }

                @Override
                public void t_responseNull() {

                }

                @Override
                public void t_retry(Call<Object> call) {

                }

                @Override
                public void t_onSuccess(Object response) {
                    NewTimelineListResponse responseX = (NewTimelineListResponse) response;

//                Gson gson = new Gson();
//                JsonElement jsonElement = gson.toJsonTree(response.body());
//                TimelineListResponse responseX = gson.fromJson(jsonElement.getAsString(), TimelineListResponse.class);
//                for (TimelineItem item : responseX.getTimelineList()){
//                    item.setType(Tubeless_ITEM_TYPE);
//                    data.add(item);
//                    if (isRefresh) {
//                        adapter.notifyDataSetChanged();
//                    }else {
//                        adapter.notifyItemInserted(data.size());
//                    }
//                }
//                adapter.notifyDataSetChanged();


                    for (NewTimelineItem item : responseX.getTimelineList()){
//                        item.setType(Tubeless_ITEM_TYPE);
                        ((ListFragment)fragment).list.add(item);
//                        if (isRefresh) {
                        adapter.notifyDataSetChanged();
//                        }else {
//                            adapter.notifyItemInserted(data.size());
//                        }
                    }
                    adapter.notifyDataSetChanged();
                }
            };
            StringBuilder stringBuilder = new StringBuilder();
            switch (StaticValue.bourseState.lastPayedType){
                case 1:
                case 0:
                    stringBuilder.append(context.getResources().getInteger(R.integer.cat1BourseAnalize));
                    break;
                case 2:
                    stringBuilder.append(context.getResources().getInteger(R.integer.cat2BourseAnalize));
                    break;
                case 3:
                    stringBuilder.append(context.getResources().getInteger(R.integer.cat3BourseAnalize));
            }

            StringBuilder stringBuilder5 = new StringBuilder();
            switch (StaticValue.bourseState.lastPayedType){
                case 1:
                case 0:
                    stringBuilder5.append(context.getResources().getInteger(R.integer.cat1BourseAnalize));
                    break;
                case 2:
                    stringBuilder5.append(context.getResources().getInteger(R.integer.cat2BourseAnalize));
                    break;
                case 3:
                    stringBuilder5.append(context.getResources().getInteger(R.integer.cat3BourseAnalize));
            }
            Global.apiManagerTubeless.getTimeline(context,current_page - 1, ssssssss, StaticValue.bourseState.endDate,stringBuilder5.toString());

        }else if (listType == TYPE_BOURSE_NEWS) {
            TubelessRetrofitCallbackss ssssssss = new TubelessRetrofitCallbackss(getContext(), NewTimelineListResponse.class) {
                @Override
                public void t_beforeSendRequest() {

                }

                @Override
                public void t_afterGetResponse() {

                }

                @Override
                public void t_complite() {

                }

                @Override
                public void t_responseNull() {

                }

                @Override
                public void t_retry(Call<Object> call) {

                }

                @Override
                public void t_onSuccess(Object response) {
                    NewTimelineListResponse responseX = (NewTimelineListResponse) response;

//                Gson gson = new Gson();
//                JsonElement jsonElement = gson.toJsonTree(response.body());
//                TimelineListResponse responseX = gson.fromJson(jsonElement.getAsString(), TimelineListResponse.class);
//                for (TimelineItem item : responseX.getTimelineList()){
//                    item.setType(Tubeless_ITEM_TYPE);
//                    data.add(item);
                    if (isRefresh) {
                        adapter.notifyDataSetChanged();
                    }else {
                        adapter.notifyItemInserted(((ListFragment)fragment).list.size());
                    }
//                }
//                adapter.notifyDataSetChanged();


                    for (NewTimelineItem item : responseX.getTimelineList()){
//                        item.setType(Tubeless_ITEM_TYPE);
                        ((ListFragment)fragment).list.add(item);
//                        if (isRefresh) {
                        adapter.notifyDataSetChanged();
//                        }else {
//                            adapter.notifyItemInserted(data.size());
//                        }
                    }
                    adapter.notifyDataSetChanged();
                }
            };

            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(context.getResources().getInteger(R.integer.cat1BourseNews));
            stringBuilder.append("_");
            stringBuilder.append(context.getResources().getInteger(R.integer.cat2BourseNews));
            stringBuilder.append("_");
            stringBuilder.append(context.getResources().getInteger(R.integer.cat3BourseNews));

            Global.apiManagerTubeless.getTimeline(context,current_page - 1, ssssssss,stringBuilder.toString());
        }else if (listType == TYPE_COMMENTS) {
            int blogId = bundle.getInt("blogId");
            TubelessRetrofitCallbackss ssssssss = new TubelessRetrofitCallbackss(getContext(), CommentListResponse.class) {
                @Override
                public void t_beforeSendRequest() {
                    if (isRefresh && current_page == 1 ) {
                        ((ListFragment)fragment).list.clear();
                        adapter.notifyDataSetChanged();
                    }

                    if (current_page == 1){
                        if (((Activity)context) instanceof TubelessActivity)
                            ((TubelessActivity)context).progressDialog.show();
                    }
                }

                @Override
                public void t_afterGetResponse() {

                }

                @Override
                public void t_complite() {

                }

                @Override
                public void t_responseNull() {

                }

                @Override
                public void t_retry(Call<Object> call) {

                }

                @Override
                public void t_onSuccess(Object response) {
                    CommentListResponse responseX = (CommentListResponse) response;

                    if (((Activity)context) instanceof TubelessActivity)
                        ((TubelessActivity)context).progressDialog.hide();
//                Gson gson = new Gson();
//                JsonElement jsonElement = gson.toJsonTree(response.body());
//                TimelineListResponse responseX = gson.fromJson(jsonElement.getAsString(), TimelineListResponse.class);
//                for (TimelineItem item : responseX.getTimelineList()){
//                    item.setType(Tubeless_ITEM_TYPE);
//                    data.add(item);
//                    if (isRefresh) {
//                        adapter.notifyDataSetChanged();
//                    }else {
//                        adapter.notifyItemInserted(data.size());
//                    }
//                }
//                adapter.notifyDataSetChanged();

                    for (CommentItem item : responseX.getCommentList()){
//                        item.setType(Tubeless_ITEM_TYPE);
                        ((ListFragment)fragment).list.add(item);
                        if (isRefresh) {
                            adapter.notifyDataSetChanged();
                        }else {
                            adapter.notifyItemInserted(((ListFragment)fragment).list.size());
                        }
                    }
//                    adapter.notifyItemChanged(data.size() - 1);//.notifyDataSetChanged();
                }
            };
            Global.apiManagerTubeless.getCommentTimeline(blogId,current_page - 1, ssssssss);
        }else if (listType == TYPE_POST_SEARCH_RESULT) {
            adapter.notifyDataSetChanged();
        }

    }

    @Override
    public PostViewHolder onCreateViewHolder(final ViewGroup parent, final int viewType) {

        PostViewHolder holder = null;
        if (listType == TYPE_YADAK || listType == TYPE_LIST_CATEGORIES_DATA) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout._row_of_yafte_item, parent, false);
            holder = new TimelineItemViewHolder(view);
        }else if (listType == TYPE_LIST) {
            final View view = LayoutInflater.from(context).inflate(R.layout._row_of_bourse_nerkhroz_header, parent, false);
            //font 5
//            FontChangeCrawler fontChanger = new FontChangeCrawler(context.getAssets(), FONT_IRANSANS_MOBILE_NORMAL_TTF);
//            fontChanger.replaceFonts((ViewGroup)view);
            holder = new CategoryViewHolder(view);

        }else if (listType == TYPE_SELECT_CATEGORY || listType == TYPE_LIST_CATEGORY) {
            final View view = LayoutInflater.from(context).inflate(R.layout._row_of_bourse_nerkhroz_header, parent, false);
            //font 5
//            FontChangeCrawler fontChanger = new FontChangeCrawler(context.getAssets(), FONT_IRANSANS_MOBILE_NORMAL_TTF);
//            fontChanger.replaceFonts((ViewGroup)view);
            holder = new CategoryViewHolder(view);

        }else if (listType == TYPE_YAFTE) {
            final View view = LayoutInflater.from(context).inflate(R.layout._row_of_yafte_item, parent, false);
            //font 5
//            FontChangeCrawler fontChanger = new FontChangeCrawler(context.getAssets(), FONT_IRANSANS_MOBILE_NORMAL_TTF);
//            fontChanger.replaceFonts((ViewGroup)view);
            holder = new TimelineItemViewHolder(view);

        }else if (listType == TYPE_BOURSE_TRAIN || listType == TYPE_BOURSE_ANALIZE_All ||  listType == TYPE_BOURSE_ANALIZE_Old || listType == TYPE_BOURSE_NEWS ) {
            final View view = LayoutInflater.from(context).inflate(R.layout._row_of_bourse_item, parent, false);
            holder = new TimelineItemViewHolder(view);

        }else if (listType == TYPE_POST_SEARCH_RESULT) {
            final View view = LayoutInflater.from(context).inflate(R.layout._row_of_post_item, parent, false);
            holder = new PostItemViewHolder(view);

        }else if (listType == TYPE_COMMENTS) {
            final View view = LayoutInflater.from(context).inflate(R.layout._row_of_comment_item, parent, false);
            holder = new CommentItemViewHolder(view);
        }else if (listType == 1) {
            final View view = LayoutInflater.from(context).inflate(R.layout._row_image_of_a_car, parent, false);
            holder = new TwoLinesViewHolder(view);
        }


        return holder;
    }

    @Override
    public void onBindViewHolder(final PostViewHolder holder, final int position) {
        //first Item
        if (position == 0 && ((Activity)context) instanceof ContainerActivity) {                 //  <= فاصله اولین آیتم از بالای لیست
            ((ViewGroup.MarginLayoutParams) holder.itemView.getLayoutParams()).topMargin = 60;
        }


        //last Item
        if (position == getItemCount() - 1) {
//            ((ViewGroup.MarginLayoutParams) holder.itemView.getLayoutParams()).bottomMargin = holder.marginBottom + navigationHeight;
        } else {
            ((ViewGroup.MarginLayoutParams) holder.itemView.getLayoutParams()).bottomMargin = holder.marginBottom;
        }


        if (((ListFragment)fragment).list.get(position) instanceof PictureItem) {
            final PictureItem item = (PictureItem) ((ListFragment)fragment).list.get(position);
            item.fill(context , this, listType, holder, item, position);


//        }else if (data.get(position) instanceof TimelineItem) {
//            final TimelineItem item = (TimelineItem) data.get(position);
//            item.fill(context,this , listType, holder, item, position);

        }else if (((ListFragment)fragment).list.get(position) instanceof NewTimelineItem) {
            final NewTimelineItem item = (NewTimelineItem) ((ListFragment)fragment).list.get(position);
            Log.e("filllllll" , listType + " " + position);

            item.fill(context,this , listType, holder, item, position);



        }else if (((ListFragment)fragment).list.get(position) instanceof PostSearchResponseItem) {
            PostSearchResponseItem item = (PostSearchResponseItem) ((ListFragment)fragment).list.get(position);
            item.fill(context , this, listType, holder, item,position);

        }else if (((ListFragment)fragment).list.get(position) instanceof CommentItem) {
            CommentItem item = (CommentItem) ((ListFragment)fragment).list.get(position);
            item.fill(context , this, listType, holder, item,position);

        }else if (((ListFragment)fragment).list.get(position) instanceof TextItem) {
//            final TextItem item = (TextItem) data.get(position);
//            holder.title.setText(item.CarName);
//            Picasso.get()
//                    //.load(String.format(Url.MEDIA_SERVER_ADDRESS + "Files/Pictures/LogoOfCompanies/48/%s.jpg", header.getPicture()))
//                    .load("http://sajjadyosefi.ir/img/profile.jpg")
//                    .placeholder(R.drawable.progress_animation)
//                    .into(holder.imageView);
        }else if (((ListFragment)fragment).list.get(position) instanceof NotiesItem) {

            NotiesItem item = (NotiesItem)((ListFragment)fragment).list.get(position);
            item.fill(context , this, listType, holder, item,position);

        }else if (((ListFragment)fragment).list.get(position) instanceof MainListItem) {

            MainListItem item = (MainListItem) ((ListFragment)fragment).list.get(position);
            item.fill(context , this, listType, holder, item,position);

        }else if (((ListFragment)fragment).list.get(position) instanceof CategoryItem) {

            CategoryItem item = (CategoryItem) ((ListFragment)fragment).list.get(position);
            item.setNode(categoryFiltersNode);
            item.fill(context , this, listType,holder, item,position );

        }


        //Animation Set the view to fade in
//        setScaleAnimation(holder.itemView);
    }

    private void setFadeAnimation(View view) {
        AlphaAnimation anim = new AlphaAnimation(0.0f, 1.0f);
        anim.setDuration(FADE_DURATION);
        view.startAnimation(anim);
    }
    private void setScaleAnimation(View view) {
        ScaleAnimation anim = new ScaleAnimation(0.0f, 1.0f, 0.0f, 1.0f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        anim.setDuration(FADE_DURATION);
        view.startAnimation(anim);
    }


    @Override
    public void removeItem (int listType , int removeIndex ){
        ((ListFragment)fragment).list.remove(removeIndex);
        adapter.notifyItemRemoved(removeIndex);
    }

    @Override
    public int getItemCount() {
//        if (data == null)
//            data = new ArrayList<>();

        if (((ListFragment)fragment).list == null)
            return 0;
        else
            return ((ListFragment)fragment).list.size();
    }

    @Override
    public void onViewDetachedFromWindow(final PostViewHolder holder) {
        super.onViewDetachedFromWindow(holder);
    }


    public static void prepareToShare(Context mContext,String filePath, String statement, boolean loadedImage) {

    }

    private static String SaveImage(Context mContext , String urlString , DilatingDotsProgressBar dilatingDotsProgressBar) {
        return "";
    }

    void sssss( final RecyclerView recyclerViewTimeline,final Context context){

        if (CommonClass.isNetworkConnected(context)) {

        }else{
            try {
                DialogUtil.ShowMessageDialog(context,"",context.getString(R.string.errorInInternetConnection));
            }catch (Exception ex){}
        }
    }

    private void setAnimation(View viewToAnimate, int position) {

    }


//
//    @Override
//    public int getItemViewType(int position) {
//
//        return 1;
//    }
}

