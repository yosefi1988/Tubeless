package ir.sajjadyosefi.android.xTubeless.Adapter;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
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

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.internal.Primitives;
import com.squareup.picasso.Picasso;
import com.zl.reik.dilatingdotsprogressbar.DilatingDotsProgressBar;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import ir.sajjadyosefi.android.xTubeless.Fragment.ListFragment;
import ir.sajjadyosefi.android.xTubeless.Global;
import ir.sajjadyosefi.android.xTubeless.R;
import ir.sajjadyosefi.android.xTubeless.activity.activities.TubelessActivity;
import ir.sajjadyosefi.android.xTubeless.activity.common.ContainerActivity;
import ir.sajjadyosefi.android.xTubeless.classes.StaticValue;
import ir.sajjadyosefi.android.xTubeless.classes.model.category.CategoryItem;
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
import ir.sajjadyosefi.android.xTubeless.classes.model.response.ServerResponseBase;
import ir.sajjadyosefi.android.xTubeless.classes.model.response.category.CategoryListResponse;
import ir.sajjadyosefi.android.xTubeless.classes.model.response.nerkhroz.bourse.NerkhrozBourse;
import ir.sajjadyosefi.android.xTubeless.classes.model.response.nerkhroz.bourse.NerkhrozBourseResponse;

import ir.sajjadyosefi.android.xTubeless.classes.model.viewHolder.nerkhroz.HeaderViewHolder;
import ir.sajjadyosefi.android.xTubeless.classes.model.viewHolder.nerkhroz.ParentViewHolder;
import ir.sajjadyosefi.android.xTubeless.networkLayout.retrofit.TubelessRetrofitCallbackss;
import ir.sajjadyosefi.android.xTubeless.networkLayout.retrofit.tmp.TimelineItem;
import ir.sajjadyosefi.android.xTubeless.networkLayout.retrofit.tmp.TimelineListResponse;
import ir.sajjadyosefi.android.xTubeless.utility.CommonClass;
import ir.sajjadyosefi.android.xTubeless.utility.DialogUtil;
import ir.sajjadyosefi.android.xTubeless.widget.recyclerview.EndlessRecyclerOnScrollListener;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static ir.sajjadyosefi.android.xTubeless.Adapter.FirstFragmentsAdapter.TYPE_BOURSE_ANALIZE_All;
import static ir.sajjadyosefi.android.xTubeless.Adapter.FirstFragmentsAdapter.TYPE_BOURSE_ANALIZE_Old;
import static ir.sajjadyosefi.android.xTubeless.Adapter.FirstFragmentsAdapter.TYPE_BOURSE_NEWS;
import static ir.sajjadyosefi.android.xTubeless.Adapter.FirstFragmentsAdapter.TYPE_BOURSE_TRAIN;
import static ir.sajjadyosefi.android.xTubeless.Adapter.FirstFragmentsAdapter.TYPE_COMMENTS;
import static ir.sajjadyosefi.android.xTubeless.Adapter.FirstFragmentsAdapter.TYPE_LIST;
import static ir.sajjadyosefi.android.xTubeless.Adapter.FirstFragmentsAdapter.TYPE_NERKHROZ_BOURSE;
import static ir.sajjadyosefi.android.xTubeless.Adapter.FirstFragmentsAdapter.TYPE_POST_SEARCH_RESULT;
import static ir.sajjadyosefi.android.xTubeless.Adapter.FirstFragmentsAdapter.TYPE_YADAK;
import static ir.sajjadyosefi.android.xTubeless.Adapter.FirstFragmentsAdapter.TYPE_YAFTE;
import static org.litepal.LitePalApplication.getContext;


public class XAdapterNerkhRoz extends RecyclerView.Adapter<ParentViewHolder> implements ITubelessAdapter {

    private View rootView;
    //private final int scrollHeight;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private Bundle bundle;
    private RecyclerView recyclerView;
    private Picasso picasso;
    //private int navigationHeight;
//    private List<IItems> data;
    private Fragment fragment;

    XAdapterNerkhRoz adapter;
    int listType;
    int catid;
    //private boolean hasAppBarLayout;
    private Context context;

    //scroll
    public LinearLayoutManager mLayoutManager = null ;

    EndlessRecyclerOnScrollListener onScrollListener;



    public XAdapterNerkhRoz(int listType, final Context context, View rootView, RecyclerView recyclerView, LinearLayoutManager linearLayoutManager, SwipeRefreshLayout mSwipeRefreshLayout, Fragment fragment, Bundle bundle) {
         new XAdapterNerkhRoz(listType, 0, context, rootView, recyclerView, linearLayoutManager, mSwipeRefreshLayout, fragment, bundle) ;
    }
    public XAdapterNerkhRoz(int listType, int catid, final Context context, View rootView, RecyclerView recyclerView, LinearLayoutManager linearLayoutManager, SwipeRefreshLayout mSwipeRefreshLayout, Fragment fragment, Bundle bundle) {
        this.listType = listType;
        this.catid = catid;
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

        if (listType == TYPE_NERKHROZ_BOURSE) {
            Callback callback = new Callback() {
                @Override
                public void onResponse(Call call, Response response) {
                    Gson gson = new Gson();
                    JsonElement jsonElement = gson.toJsonTree(response.body());
                    NerkhrozBourse[] founderArray = gson.fromJson(jsonElement.getAsString(), NerkhrozBourse[].class);

//                    for (CategoryItem item : object.getCategoryItems()){
////                        item.setType(Tubeless_ITEM_TYPE);
//                        ((ListFragment)fragment).list.add(item);
//                        if (isRefresh) {
//                            adapter.notifyDataSetChanged();
//                        }else {
//                            adapter.notifyItemInserted(((ListFragment)fragment).list.size());
//                        }
//                    }

                    ((TubelessActivity)context).progressDialog.hide();
                }

                @Override
                public void onFailure(Call call, Throwable t) {

                }
            };
            Global.retrofitHelperNerkhRoz.getBourseDetailsService(callback);
        }else if (listType == 0) {
        }

    }

    @Override
    public ParentViewHolder onCreateViewHolder(final ViewGroup parent, final int viewType) {

        ParentViewHolder holder = null;
        if (listType == TYPE_NERKHROZ_BOURSE) {
            final View view = LayoutInflater.from(context).inflate(R.layout._row_of_category_item2, parent, false);
            holder = new HeaderViewHolder(view);
        }
        return holder;
    }

    @Override
    public void onBindViewHolder(final ParentViewHolder holder, final int position) {
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
            //item.fill(context , this, listType, holder, item, position);
        }


        //Animation Set the view to fade in
        setScaleAnimation(holder.itemView);
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
    public void onViewDetachedFromWindow(final ParentViewHolder holder) {
        super.onViewDetachedFromWindow(holder);
    }

}

