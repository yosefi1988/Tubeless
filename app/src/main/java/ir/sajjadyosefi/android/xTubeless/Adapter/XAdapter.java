package ir.sajjadyosefi.android.xTubeless.Adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.squareup.picasso.Picasso;
import com.zl.reik.dilatingdotsprogressbar.DilatingDotsProgressBar;

import java.util.ArrayList;
import java.util.List;
import ir.sajjadyosefi.android.xTubeless.R;
import ir.sajjadyosefi.android.xTubeless.Global;
import ir.sajjadyosefi.android.xTubeless.activity.common.ContainerActivity;
import ir.sajjadyosefi.android.xTubeless.classes.model.network.responses.post.PostSearchResponseItem;
import ir.sajjadyosefi.android.xTubeless.classes.model.post.TimelineItem;
import ir.sajjadyosefi.android.xTubeless.classes.model.post.IItems;
import ir.sajjadyosefi.android.xTubeless.classes.model.post.PictureItem;
import ir.sajjadyosefi.android.xTubeless.classes.model.post.TextItem;
import ir.sajjadyosefi.android.xTubeless.classes.model.response.TimelineListResponse;
import ir.sajjadyosefi.android.xTubeless.classes.model.viewHolder.PostItemViewHolder;
import ir.sajjadyosefi.android.xTubeless.classes.model.viewHolder.PostViewHolder;
import ir.sajjadyosefi.android.xTubeless.classes.model.viewHolder.TimelineItemViewHolder;
import ir.sajjadyosefi.android.xTubeless.classes.model.viewHolder.TwoLinesViewHolder;
import ir.sajjadyosefi.android.xTubeless.networkLayout.retrofit.TubelessRetrofitCallbackss;
import ir.sajjadyosefi.android.xTubeless.utility.CommonClass;
import ir.sajjadyosefi.android.xTubeless.widget.recyclerview.EndlessRecyclerOnScrollListener;
import retrofit2.Call;

import static ir.sajjadyosefi.android.xTubeless.Adapter.FirstFragmentsAdapter.TYPE_POST_SEARCH_RESULT;
import static ir.sajjadyosefi.android.xTubeless.Adapter.FirstFragmentsAdapter.TYPE_YADAK;
import static ir.sajjadyosefi.android.xTubeless.Adapter.FirstFragmentsAdapter.TYPE_YAFTE;
import static org.litepal.LitePalApplication.getContext;


public class XAdapter extends RecyclerView.Adapter<PostViewHolder> implements ITubelessAdapter {

    private final View rootView;
    //private final int scrollHeight;
    private final SwipeRefreshLayout mSwipeRefreshLayout;
    private RecyclerView recyclerView;
    private Picasso picasso;
    //private int navigationHeight;
    private List<IItems> data;

    XAdapter adapter;
    int type;
    //private boolean hasAppBarLayout;
    private Context context;

    //scroll
    public LinearLayoutManager mLayoutManager = null ;

    EndlessRecyclerOnScrollListener onScrollListener;


    public XAdapter(int type, final Context context, View rootView, RecyclerView recyclerView,LinearLayoutManager linearLayoutManager,SwipeRefreshLayout mSwipeRefreshLayout, final List<IItems> data) {
        this.type = type;
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

        if (type == TYPE_YADAK) {
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
                            adapter.notifyDataSetChanged();
//                        }else {
//                            adapter.notifyItemInserted(data.size());
//                        }
                    }
                }
            };
            Global.apiManagerTubeless.getTimelineYadak(current_page - 1, ssssssss);

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
        }else if (type == TYPE_POST_SEARCH_RESULT) {
            adapter.notifyDataSetChanged();
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

        }else if (type == TYPE_POST_SEARCH_RESULT) {
            final View view = LayoutInflater.from(context).inflate(R.layout._row_of_post_item, parent, false);
            holder = new PostItemViewHolder(view);

        }else if (type == 1) {

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
            item.fill(context , this, type, holder, item, position);

        }else if (data.get(position) instanceof TimelineItem) {
            final TimelineItem item = (TimelineItem) data.get(position);
            item.fill(context,this ,type, holder, item, position);

        }else if (data.get(position) instanceof PostSearchResponseItem) {
            PostSearchResponseItem item = (PostSearchResponseItem) data.get(position);
            item.fill(context , this, type, holder, item,position);

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

