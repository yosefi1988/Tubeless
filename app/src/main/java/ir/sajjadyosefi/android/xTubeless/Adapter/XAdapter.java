package ir.sajjadyosefi.android.xTubeless.Adapter;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.squareup.picasso.Picasso;
import com.zl.reik.dilatingdotsprogressbar.DilatingDotsProgressBar;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import ir.sajjadyosefi.android.xTubeless.R;
import ir.sajjadyosefi.android.xTubeless.Global;
import ir.sajjadyosefi.android.xTubeless.activity.activities.TubelessActivity;
import ir.sajjadyosefi.android.xTubeless.activity.common.ContainerActivity;
import ir.sajjadyosefi.android.xTubeless.classes.StaticValue;
import ir.sajjadyosefi.android.xTubeless.classes.model.mainList.MainListItem;
import ir.sajjadyosefi.android.xTubeless.classes.model.network.responses.post.PostSearchResponseItem;
import ir.sajjadyosefi.android.xTubeless.classes.model.post.NewTimelineItem;
import ir.sajjadyosefi.android.xTubeless.classes.model.post.NotiesItem;
import ir.sajjadyosefi.android.xTubeless.classes.model.post.IItems;
import ir.sajjadyosefi.android.xTubeless.classes.model.post.PictureItem;
import ir.sajjadyosefi.android.xTubeless.classes.model.post.TextItem;
import ir.sajjadyosefi.android.xTubeless.classes.model.post.blog.CommentItem;
import ir.sajjadyosefi.android.xTubeless.classes.model.response.CommentListResponse;
import ir.sajjadyosefi.android.xTubeless.classes.model.response.MainListResponse;
import ir.sajjadyosefi.android.xTubeless.classes.model.response.NewTimelineListResponse;
import ir.sajjadyosefi.android.xTubeless.classes.model.viewHolder.CommentItemViewHolder;
import ir.sajjadyosefi.android.xTubeless.classes.model.viewHolder.MainListViewHolder;
import ir.sajjadyosefi.android.xTubeless.classes.model.viewHolder.PostItemViewHolder;
import ir.sajjadyosefi.android.xTubeless.classes.model.viewHolder.PostViewHolder;
import ir.sajjadyosefi.android.xTubeless.classes.model.viewHolder.TimelineItemViewHolder;
import ir.sajjadyosefi.android.xTubeless.classes.model.viewHolder.TwoLinesViewHolder;
import ir.sajjadyosefi.android.xTubeless.networkLayout.retrofit.TubelessRetrofitCallbackss;
import ir.sajjadyosefi.android.xTubeless.networkLayout.retrofit.tmp.TimelineItem;
import ir.sajjadyosefi.android.xTubeless.networkLayout.retrofit.tmp.TimelineListResponse;
import ir.sajjadyosefi.android.xTubeless.utility.CommonClass;

import ir.sajjadyosefi.android.xTubeless.utility.DateConverterSjd;
import ir.sajjadyosefi.android.xTubeless.utility.DialogUtil;
import ir.sajjadyosefi.android.xTubeless.widget.recyclerview.EndlessRecyclerOnScrollListener;
import retrofit2.Call;

import static ir.sajjadyosefi.android.xTubeless.Adapter.FirstFragmentsAdapter.TYPE_BOURSE_ANALIZE_All;
import static ir.sajjadyosefi.android.xTubeless.Adapter.FirstFragmentsAdapter.TYPE_BOURSE_ANALIZE_Old;
import static ir.sajjadyosefi.android.xTubeless.Adapter.FirstFragmentsAdapter.TYPE_BOURSE_NEWS;
import static ir.sajjadyosefi.android.xTubeless.Adapter.FirstFragmentsAdapter.TYPE_BOURSE_TRAIN;
import static ir.sajjadyosefi.android.xTubeless.Adapter.FirstFragmentsAdapter.TYPE_COMMENTS;
import static ir.sajjadyosefi.android.xTubeless.Adapter.FirstFragmentsAdapter.TYPE_LIST;
import static ir.sajjadyosefi.android.xTubeless.Adapter.FirstFragmentsAdapter.TYPE_POST_SEARCH_RESULT;
import static ir.sajjadyosefi.android.xTubeless.Adapter.FirstFragmentsAdapter.TYPE_YADAK;
import static ir.sajjadyosefi.android.xTubeless.Adapter.FirstFragmentsAdapter.TYPE_YAFTE;

import static org.litepal.LitePalApplication.getContext;


public class XAdapter extends RecyclerView.Adapter<PostViewHolder> implements ITubelessAdapter {

    private final View rootView;
    //private final int scrollHeight;
    private final SwipeRefreshLayout mSwipeRefreshLayout;
    private final Bundle bundle;
    private RecyclerView recyclerView;
    private Picasso picasso;
    //private int navigationHeight;
    private List<IItems> data;

    XAdapter adapter;
    int listType;
    //private boolean hasAppBarLayout;
    private Context context;

    //scroll
    public LinearLayoutManager mLayoutManager = null ;

    EndlessRecyclerOnScrollListener onScrollListener;


    public XAdapter(int listType, final Context context, View rootView, RecyclerView recyclerView, LinearLayoutManager linearLayoutManager, SwipeRefreshLayout mSwipeRefreshLayout, final List<IItems> data, Bundle bundle) {
        this.listType = listType;
        //this.navigationHeight = navigationHeight;
        this.data = data;
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
                loadTimeline(context,1,true);
                mSwipeRefreshLayout.setRefreshing(false);

//                if(listType == TYPE_YAFTE){
//
//                }else if(listType == TYPE_YADAK){
//
//                }else if(listType == TYPE_IMAGE){
//
//                }
            }
        });
    }

    private void loadTimeline(Context context,int current_page, boolean isRefresh) {

        if (listType == TYPE_LIST) {
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
                data.add(item);
                if (isRefresh) {
                    adapter.notifyDataSetChanged();
                }else {
                    adapter.notifyItemInserted(data.size());
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
                        data.add(item);
                        if (isRefresh) {
                            adapter.notifyDataSetChanged();
                        }else {
                            adapter.notifyItemInserted(data.size());
                        }
                    }

                    recyclerView.scrollToPosition(5);

                }
            };
            Global.apiManagerTubeless.getTimelineListForYadak(context,current_page - 1, ssssssss);

        }else if (listType == TYPE_YAFTE) {
            TubelessRetrofitCallbackss ssssssss = new TubelessRetrofitCallbackss(getContext(), TimelineListResponse.class) {
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
                        data.add(newTimelineItem);
                        if (isRefresh) {
                            adapter.notifyDataSetChanged();
                        }else {
                            adapter.notifyItemInserted(data.size());
                        }
                    }
                    adapter.notifyDataSetChanged();

                }
            };
            Global.apiManagerTubeless.getTimelineListForYafte(context,current_page - 1, ssssssss);

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
                        data.add(item);
//                        if (isRefresh) {
                        adapter.notifyDataSetChanged();
//                        }else {
//                            adapter.notifyItemInserted(data.size());
//                        }
                    }
                    adapter.notifyDataSetChanged();
                }
            };
            Global.apiManagerTubeless.getTimelineListForBourseTrain(context,current_page - 1, ssssssss);

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
                        data.add(item);
//                        if (isRefresh) {
                        adapter.notifyDataSetChanged();
//                        }else {
//                            adapter.notifyItemInserted(data.size());
//                        }
                    }
                    adapter.notifyDataSetChanged();
                }
            };
            Global.apiManagerTubeless.getTimelineListForBourseAnalize(context,current_page - 1, ssssssss);
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
                        data.add(item);
//                        if (isRefresh) {
                        adapter.notifyDataSetChanged();
//                        }else {
//                            adapter.notifyItemInserted(data.size());
//                        }
                    }
                    adapter.notifyDataSetChanged();
                }
            };

            Global.apiManagerTubeless.getTimelineListForBourseAnalizeOld(context,current_page - 1, ssssssss, StaticValue.bourseState.endDate);
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
                        adapter.notifyItemInserted(data.size());
                    }
//                }
//                adapter.notifyDataSetChanged();


                    for (NewTimelineItem item : responseX.getTimelineList()){
//                        item.setType(Tubeless_ITEM_TYPE);
                        data.add(item);
//                        if (isRefresh) {
                        adapter.notifyDataSetChanged();
//                        }else {
//                            adapter.notifyItemInserted(data.size());
//                        }
                    }
                    adapter.notifyDataSetChanged();
                }
            };
            Global.apiManagerTubeless.getTimelineListForBourseNews(context,current_page - 1, ssssssss);
        }else if (listType == TYPE_COMMENTS) {
            int blogId = bundle.getInt("blogId");
            TubelessRetrofitCallbackss ssssssss = new TubelessRetrofitCallbackss(getContext(), CommentListResponse.class) {
                @Override
                public void t_beforeSendRequest() {
                    if (isRefresh && current_page == 1 ) {
                        data.clear();
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
                        data.add(item);
                        if (isRefresh) {
                            adapter.notifyDataSetChanged();
                        }else {
                            adapter.notifyItemInserted(data.size());
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
        if (listType == TYPE_YADAK) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout._row_of_yafte_item, parent, false);
            holder = new TimelineItemViewHolder(view);
        }else if (listType == TYPE_LIST) {
            final View view = LayoutInflater.from(context).inflate(R.layout._row_of_group_item, parent, false);
            //font 5
//            FontChangeCrawler fontChanger = new FontChangeCrawler(context.getAssets(), FONT_IRANSANS_MOBILE_NORMAL_TTF);
//            fontChanger.replaceFonts((ViewGroup)view);
            holder = new MainListViewHolder(view);

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


        if (data.get(position) instanceof PictureItem) {
            final PictureItem item = (PictureItem) data.get(position);
            item.fill(context , this, listType, holder, item, position);

//        }else if (data.get(position) instanceof TimelineItem) {
//            final TimelineItem item = (TimelineItem) data.get(position);
//            item.fill(context,this , listType, holder, item, position);

        }else if (data.get(position) instanceof NewTimelineItem) {
            final NewTimelineItem item = (NewTimelineItem) data.get(position);
            item.fill(context,this , listType, holder, item, position);



        }else if (data.get(position) instanceof PostSearchResponseItem) {
            PostSearchResponseItem item = (PostSearchResponseItem) data.get(position);
            item.fill(context , this, listType, holder, item,position);

        }else if (data.get(position) instanceof CommentItem) {
            CommentItem item = (CommentItem) data.get(position);
            item.fill(context , this, listType, holder, item,position);

        }else if (data.get(position) instanceof TextItem) {
//            final TextItem item = (TextItem) data.get(position);
//            holder.title.setText(item.CarName);
//            Picasso.get()
//                    //.load(String.format(Url.MEDIA_SERVER_ADDRESS + "Files/Pictures/LogoOfCompanies/48/%s.jpg", header.getPicture()))
//                    .load("http://sajjadyosefi.ir/img/profile.jpg")
//                    .placeholder(R.drawable.progress_animation)
//                    .into(holder.imageView);
        }else if (data.get(position) instanceof NotiesItem) {

            NotiesItem item = (NotiesItem) data.get(position);
            item.fill(context , this, listType, holder, item,position);

        }else if (data.get(position) instanceof MainListItem) {

            MainListItem item = (MainListItem) data.get(position);
            item.fill(context , this, listType, holder, item,position);

        }


        //Animation Set the view to fade in
        setScaleAnimation(holder.itemView);
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
        data.remove(removeIndex);
        adapter.notifyItemRemoved(removeIndex);
    }

    @Override
    public int getItemCount() {
        if (data == null)
            data = new ArrayList<>();

        return data.size();
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

