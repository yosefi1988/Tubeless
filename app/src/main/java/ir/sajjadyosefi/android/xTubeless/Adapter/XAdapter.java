package ir.sajjadyosefi.android.xTubeless.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.squareup.picasso.Picasso;
import com.zl.reik.dilatingdotsprogressbar.DilatingDotsProgressBar;

import java.util.ArrayList;
import java.util.List;

import ir.sajjadyosefi.android.tubeless.Global;
import ir.sajjadyosefi.android.tubeless.R;
import ir.sajjadyosefi.android.tubeless.classes.CommonClass;
import ir.sajjadyosefi.android.xTubeless.classes.modelY.main.TimelineItem;
import ir.sajjadyosefi.android.xTubeless.networkLayout.retrofit.TubelessRetrofitCallback;
import ir.sajjadyosefi.android.tubeless.widget.recyclerview.EndlessRecyclerOnScrollListener;
import ir.sajjadyosefi.android.xTubeless.classes.modelY.post.IItems;
import ir.sajjadyosefi.android.xTubeless.classes.modelY.post.PictureItem;
import ir.sajjadyosefi.android.xTubeless.classes.modelY.post.TextItem;
import ir.sajjadyosefi.android.xTubeless.classes.modelY.responses.blog.TimelineListResponse;
import ir.sajjadyosefi.android.xTubeless.classes.modelY.viewHolder.PostViewHolder;
import ir.sajjadyosefi.android.xTubeless.classes.modelY.viewHolder.TimelineItemViewHolder;
import ir.sajjadyosefi.android.xTubeless.classes.modelY.viewHolder.TwoLinesViewHolder;
import ir.sajjadyosefi.android.xTubeless.networkLayout.retrofit.TubelessRetrofitCallbackss;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static ir.sajjadyosefi.android.xTubeless.Adapter.FirstFragmentsAdapter.TYPE_IMAGE;
import static ir.sajjadyosefi.android.xTubeless.Adapter.FirstFragmentsAdapter.TYPE_YADAK;
import static ir.sajjadyosefi.android.xTubeless.Adapter.FirstFragmentsAdapter.TYPE_YAFTE;
import static org.litepal.LitePalApplication.getContext;


public class XAdapter extends RecyclerView.Adapter<PostViewHolder> {

    private final View rootView;
    private final int scrollHeight;
    private final SwipeRefreshLayout mSwipeRefreshLayout;
    private RecyclerView recyclerView;
    private Picasso picasso;
    private int navigationHeight;
    private List<IItems> data;

    XAdapter adapter;
    int type;
    private boolean hasAppBarLayout;
    private Context context;

    //scroll
    public LinearLayoutManager mLayoutManager = null ;

    EndlessRecyclerOnScrollListener onScrollListener;


    public XAdapter(int type, final Context context, View rootView, RecyclerView recyclerView, LinearLayoutManager linearLayoutManager, int ToolbarHeight, final int navigationHeight, final boolean hasAppBarLayout, SwipeRefreshLayout mSwipeRefreshLayout, final List<IItems> data) {
        this.type = type;
        this.navigationHeight = navigationHeight;
        this.data = data;
        this.hasAppBarLayout = hasAppBarLayout;
        this.picasso = Picasso.get();
        this.context = context;
        this.rootView = rootView;
        this.scrollHeight = ToolbarHeight;
        this.mLayoutManager = linearLayoutManager ;
        this.recyclerView = recyclerView;
        this.mSwipeRefreshLayout = mSwipeRefreshLayout ;

        this.adapter = this;

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

//                timelineItemList = new ArrayList<Object>();
//                if(listType == FragmentTimelineAdapter.LIST_TIMELINE) {
//                    adapter_Posts = new EndlessList_Adapter(
//                            context,
//                            navigationHeight,
//                            scrollHelper,
//                            activity.hasAppBarLayout(),
//                            mProgressBar,
//                            mTextViewNoting,
//                            mSwipeRefreshLayout,
//                            mRecyclerView,
//                            timelineItemList,
//                            mLayoutManager,
//                            listType);
//                    Global.resetListIndex(0);
//                }else if(listType == FragmentTimelineAdapter.LIST_BLOG) {
//                    adapter_Posts = new EndlessList_Adapter(
//                            context,
//                            navigationHeight,
//                            scrollHelper,
//                            activity.hasAppBarLayout(),
//                            mProgressBar,
//                            mTextViewNoting,
//                            mSwipeRefreshLayout,
//                            mRecyclerView,
//                            timelineItemList,
//                            mLayoutManager,
//                            listType,
//                            idHeader);
//                    Global.resetListIndex(idHeader);
//                }
//                mRecyclerView.setAdapter(adapter_Posts);
                loadTimeline(context,1,false);

                mSwipeRefreshLayout.setRefreshing(false);

//                if(type == TYPE_YAFTE){
//
//                }else if(type == TYPE_YADAK){
//
//                }else if(type == TYPE_IMAGE){
//
//                }
            }
        });
    }

    private void loadTimeline(Context context,int current_page,boolean isRefresh) {

        if (type == 2) {
            Global.apiManagerTubeless.getTimelineYadak(current_page - 1, new TubelessRetrofitCallback<Object>(context, null, true, null, new Callback<Object>() {
                @Override
                public void onResponse(Call<Object> call, Response<Object> response) {

                    Gson gson = new Gson();
                    JsonElement jsonElement = gson.toJsonTree(response.body());
                    TimelineListResponse responseX = gson.fromJson(jsonElement.getAsString(), TimelineListResponse.class);

                    for (IItems item : responseX.getTimelineList()){
                        //item.setType(Tubeless_ITEM_TYPE);
                        data.add(item);
                        if (isRefresh) {
                            adapter.notifyDataSetChanged();
                        }else {
                            adapter.notifyItemInserted(data.size());
                        }
                    }
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
                }

                @Override
                public void onFailure(Call<Object> call, Throwable t) {

                }
            }));

        }else if (type == TYPE_YAFTE) {
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
                        data.add(item);
//                        if (isRefresh) {
//                            adapter.notifyDataSetChanged();
//                        }else {
//                            adapter.notifyItemInserted(data.size());
//                        }
                    }
                    adapter.notifyDataSetChanged();
                }
            };
            Global.apiManagerTubeless.getYafteTimeline(current_page - 1, ssssssss);

        }else if (type == TYPE_YADAK) {

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
                    for (TimelineItem item : responseX.getTimelineList()){
//                        item.setType(Tubeless_ITEM_TYPE);
                        data.add(item);
//                        if (isRefresh) {
//                            adapter.notifyDataSetChanged();
//                        }else {
//                            adapter.notifyItemInserted(data.size());
//                        }
                    }
                    adapter.notifyDataSetChanged();
                }
            };
            Global.apiManagerTubeless.getYadakTimeline(current_page - 1, ssssssss);

        }
    }

    @Override
    public PostViewHolder onCreateViewHolder(final ViewGroup parent, final int viewType) {

        PostViewHolder holder = null;
        if (type == TYPE_YADAK) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout._row_of_yafte_item, parent, false);
            holder = new TimelineItemViewHolder(view);
        }else if (type == TYPE_YAFTE) {
            final View view = LayoutInflater.from(context).inflate(R.layout._row_of_yafte_item, parent, false);
            holder = new TimelineItemViewHolder(view);
        }else if (type == 1) {

            final View view = LayoutInflater.from(context).inflate(R.layout._row_image_of_a_car, parent, false);
            holder = new TwoLinesViewHolder(view);
        }
        return holder;
    }

    @Override
    public void onBindViewHolder(final PostViewHolder holder, final int position) {
        ((ViewGroup.MarginLayoutParams) holder.itemView.getLayoutParams()).topMargin = 0;
        if (position == getItemCount() - 1) {
            ((ViewGroup.MarginLayoutParams) holder.itemView.getLayoutParams()).bottomMargin = holder.marginBottom + navigationHeight;

//        } else if (position == 0 && !hasAppBarLayout) {       <= فاصله اولین آیتم از بالای لیست
//            ((ViewGroup.MarginLayoutParams) holder.itemView.getLayoutParams()).topMargin = scrollHeight;
        } else {
            ((ViewGroup.MarginLayoutParams) holder.itemView.getLayoutParams()).bottomMargin = holder.marginBottom;
        }

        if (data.get(position) instanceof PictureItem) {
            final PictureItem item = (PictureItem) data.get(position);
            item.fill(context ,type, holder, item);

        }else if (data.get(position) instanceof TimelineItem) {
            final TimelineItem item = (TimelineItem) data.get(position);
            item.fill(context ,type, holder, item);

        }else if (data.get(position) instanceof TextItem) {
//            final TextItem item = (TextItem) data.get(position);
//            holder.title.setText(item.CarName);
//            Picasso.get()
//                    //.load(String.format(Url.MEDIA_SERVER_ADDRESS + "Files/Pictures/LogoOfCompanies/48/%s.jpg", header.getPicture()))
//                    .load("http://sajjadyosefi.ir/img/profile.jpg")
//                    .placeholder(R.drawable.progress_animation)
//                    .into(holder.imageView);
        }
    }

    @Override

    public int getItemCount() {
        if (data == null)
            data = new ArrayList<>();

        return data.size();
    }



    public void notifyDataSetChanged(boolean animationAvalable) {
        notifyDataSetChanged();
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
//            if(listType == FragmentTimelineAdapter.LIST_TIMELINE) {
//                new AsyncLoadTimeline(
//                        context,
//                        mProgressBar,
//                        mTextViewNoting,
//                        mSwipeRefreshLayout,
//                        mRecyclerViewTimeline,
//                        mTimelineItemList,
//                        mPostsAdapter,
//                        listType
//                ).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
//            }else if(listType == FragmentTimelineAdapter.LIST_BLOG) {
//                new AsyncLoadBlogItemList(
//                        context,
//                        mProgressBar,
//                        mTextViewNoting,
//                        mSwipeRefreshLayout,
//                        mRecyclerViewTimeline,
//                        mTimelineItemList,
//                        mPostsAdapter,
//                        listType,
//                        idHeader
//                ).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
//            }else if(listType == FragmentTimelineAdapter.LIST_YAFTE_RESULT) {
//                new AsyncLoadBlogItemList(
//                        context,
//                        mProgressBar,
//                        mTextViewNoting,
//                        mSwipeRefreshLayout,
//                        mRecyclerViewTimeline,
//                        mTimelineItemList,
//                        mPostsAdapter,
//                        listType,
//                        term,
//                        idHeader
//                ).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
//            }
        }else{
            try {
                Global.ShowMessageDialog(context,"",context.getString(R.string.errorInInternetConnection));
            }catch (Exception ex){}
        }
    }

    private void setAnimation(View viewToAnimate, int position) {

    }

    @Override
    public int getItemViewType(int position) {

        return 1;
    }
}

