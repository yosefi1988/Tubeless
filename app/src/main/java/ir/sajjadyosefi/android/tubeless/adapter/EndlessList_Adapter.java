package ir.sajjadyosefi.android.tubeless.adapter;

import android.content.Context;
import android.content.Intent;

import android.net.Uri;
import android.os.AsyncTask;
import android.os.Environment;
//import androidx.recyclerview.widget.LinearLayoutManager;
//import androidx.recyclerview.widget.RecyclerView;
//import android.support.v7.widget.RecyclerView.Adapter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.zl.reik.dilatingdotsprogressbar.DilatingDotsProgressBar;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import ir.sajjadyosefi.android.tubeless.adapter.fragment.FragmentTimelineAdapter;
import ir.sajjadyosefi.android.tubeless.asyncTask.blog.AsyncLoadBlogItemList;
import ir.sajjadyosefi.android.tubeless.asyncTask.timeline.AsyncLoadTimeline;
import ir.sajjadyosefi.android.tubeless.classes.CommonClass;
import ir.sajjadyosefi.android.tubeless.classes.business.Blog;
import ir.sajjadyosefi.android.tubeless.classes.business.Yafte;
import ir.sajjadyosefi.android.xTubeless.classes.modelY.Blog.BlogItem;
import ir.sajjadyosefi.android.tubeless.Global;
import ir.sajjadyosefi.android.tubeless.R;
import ir.sajjadyosefi.android.xTubeless.classes.modelY.viewHolder.BlogItemViewHolder;
import ir.sajjadyosefi.android.xTubeless.classes.modelY.viewHolder.PostViewHolder;
import ir.sajjadyosefi.android.xTubeless.classes.modelY.viewHolder.YafteItemViewHolder;
import it.sephiroth.android.library.bottomnavigation.app.ToolbarScrollHelper;

public class EndlessList_Adapter extends RecyclerView.Adapter<PostViewHolder> {

    //FINAL STATIC
    public static int YAFTE = 12 ;                  //سرقتی پیداشده گمشده
    public static int BLOG = 11 ;                  //Blog


    private String term;
    private Context mContext;
    private DilatingDotsProgressBar mProgressBar;
    private TextView mTextViewNoting;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private RecyclerView mRecyclerViewTimeline;
    private List<Object> mTimelineItemList;
    EndlessList_Adapter mPostsAdapter;
    private boolean hasAppBarLayout;

    //scroll
    private int previousTotal = 0;
    private boolean loading = true;
    private int visibleThreshold = 5;
    int firstVisibleItem, visibleItemCount, totalItemCount;
    ToolbarScrollHelper scrollHelper;


    //User Profile
    String userId;
    private String path;
    public static ImageView btPosterPic, coverPic;
    private TextView fullName;
    private TextView location;
    public LinearLayoutManager mLayoutManager = null ;
    private int navigationHeight;

    private int lastPosition = -1;
    public int listType = 0;
    public int idHeader = 0;


    public EndlessList_Adapter(final Context context, final int navigationHeight, ToolbarScrollHelper scrollHelper,final boolean hasAppBarLayout, DilatingDotsProgressBar progressBar, TextView textViewNoting, SwipeRefreshLayout swipeRefreshLayoutNews, final RecyclerView recyclerViewTimeline, List<Object> timelineItemList, LinearLayoutManager linearLayoutManager, final int listType, final int idHeader) {
        this.idHeader = idHeader;
        this.mContext = context ;
        this.mProgressBar = progressBar;
        this.mTextViewNoting = textViewNoting;
        this.mSwipeRefreshLayout = swipeRefreshLayoutNews ;
        this.mRecyclerViewTimeline = recyclerViewTimeline;
        this.mTimelineItemList =  timelineItemList;
        this.mPostsAdapter = this ;
        this.mLayoutManager = linearLayoutManager ;
        this.listType = listType;
        this.navigationHeight = navigationHeight;
        this.hasAppBarLayout = hasAppBarLayout;
        this.scrollHelper = scrollHelper;
        sssss(this.mRecyclerViewTimeline,this.mContext);
    }
    public EndlessList_Adapter(final Context context,final int navigationHeight, ToolbarScrollHelper scrollHelper, final boolean hasAppBarLayout, DilatingDotsProgressBar progressBar, TextView textViewNoting, SwipeRefreshLayout swipeRefreshLayoutNews, final RecyclerView recyclerViewTimeline, List<Object> timelineItemList, LinearLayoutManager linearLayoutManager, final int listType) {
        this.mContext = context ;
        this.mProgressBar = progressBar;
        this.mTextViewNoting = textViewNoting;
        this.mSwipeRefreshLayout = swipeRefreshLayoutNews ;
        this.mRecyclerViewTimeline = recyclerViewTimeline;
        this.mTimelineItemList = timelineItemList;
        this.mPostsAdapter = this ;
        this.mLayoutManager = linearLayoutManager ;
        this.listType = listType;
        this.navigationHeight = navigationHeight;
        this.hasAppBarLayout = hasAppBarLayout;
        this.scrollHelper = scrollHelper;

        sssss(this.mRecyclerViewTimeline,this.mContext);
    }
    public EndlessList_Adapter(final Context context,final int navigationHeight, ToolbarScrollHelper scrollHelper,final boolean hasAppBarLayout,  DilatingDotsProgressBar progressBar, TextView textViewNoting, SwipeRefreshLayout swipeRefreshLayoutNews, final RecyclerView recyclerViewTimeline, List<Object> timelineItemList, LinearLayoutManager linearLayoutManager, String term, final int listType, final int idHeader) {
        this.mContext = context ;
        this.mProgressBar = progressBar;
        this.mTextViewNoting = textViewNoting;
        this.mSwipeRefreshLayout = swipeRefreshLayoutNews ;
        this.term = term;
        this.idHeader = idHeader;
        this.mRecyclerViewTimeline = recyclerViewTimeline;
        this.mTimelineItemList = timelineItemList;
        this.mPostsAdapter = this ;
        this.mLayoutManager = linearLayoutManager ;
        this.listType = listType;
        this.navigationHeight = navigationHeight;
        this.hasAppBarLayout = hasAppBarLayout;
        this.scrollHelper = scrollHelper;

        sssss(this.mRecyclerViewTimeline,this.mContext);
    }


    void sssss( final RecyclerView recyclerViewTimeline,final Context context){
        ///////////////////////////////////////////// Scroll //////////////////////////////////////
        recyclerViewTimeline.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                if(mLayoutManager != null) {
                    visibleItemCount = recyclerViewTimeline.getChildCount();
                    totalItemCount = mLayoutManager.getItemCount();
                    firstVisibleItem = mLayoutManager.findFirstVisibleItemPosition();

                    if (loading) {
                        if (totalItemCount > previousTotal) {
                            loading = false;
                            previousTotal = totalItemCount;
                        }
                    }
                    if (!loading && (totalItemCount - visibleItemCount)
                            <= (firstVisibleItem + visibleThreshold)) {
                        // End has been reached

                        Log.i("Yaeye!", "end called");

                        // Do something
                        if(listType == FragmentTimelineAdapter.LIST_TIMELINE) {
                            new AsyncLoadTimeline(
                                    context,
                                    mProgressBar,
                                    mTextViewNoting,
                                    mSwipeRefreshLayout,
                                    mRecyclerViewTimeline,
                                    mTimelineItemList,
                                    mPostsAdapter,
                                    listType
                                    ).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
                        }else if(listType == FragmentTimelineAdapter.LIST_BLOG) {
                            new AsyncLoadBlogItemList(
                                    context,
                                    mProgressBar,
                                    mTextViewNoting,
                                    mSwipeRefreshLayout,
                                    mRecyclerViewTimeline,
                                    mTimelineItemList,
                                    mPostsAdapter,
                                    listType,
                                    idHeader
                                    ).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
                        }
                        loading = true;

                    }

                    Log.e("Sjd","  visibleItemCount :  " + visibleItemCount  );
                    Log.e("Sjd","  totalItemCount :  " + totalItemCount  );
                    Log.e("Sjd","  firstVisibleItem :  " + firstVisibleItem  );
                    Log.e("Sjd","  previousTotal :  " + previousTotal  );
                    Log.e("Sjd","  visibleThreshold :  " + visibleThreshold  );
                    Log.e("Sjd","  loading :  " + loading  );
                    Log.e("Sjd"," ___________________________________________ " );
                }
            }
        });
        ///////////////////////////////////////////////End Scroll //////////////////////////////////////

        if (CommonClass.isNetworkConnected(context)) {
            if(listType == FragmentTimelineAdapter.LIST_TIMELINE) {
                new AsyncLoadTimeline(
                        context,
                        mProgressBar,
                        mTextViewNoting,
                        mSwipeRefreshLayout,
                        mRecyclerViewTimeline,
                        mTimelineItemList,
                        mPostsAdapter,
                        listType
                        ).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
            }else if(listType == FragmentTimelineAdapter.LIST_BLOG) {
                new AsyncLoadBlogItemList(
                        context,
                        mProgressBar,
                        mTextViewNoting,
                        mSwipeRefreshLayout,
                        mRecyclerViewTimeline,
                        mTimelineItemList,
                        mPostsAdapter,
                        listType,
                        idHeader
                ).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
            }else if(listType == FragmentTimelineAdapter.LIST_YAFTE_RESULT) {
                new AsyncLoadBlogItemList(
                        context,
                        mProgressBar,
                        mTextViewNoting,
                        mSwipeRefreshLayout,
                        mRecyclerViewTimeline,
                        mTimelineItemList,
                        mPostsAdapter,
                        listType,
                        term,
                        idHeader
                ).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
            }
        }else{
            try {
                Global.ShowMessageDialog(context,"",context.getString(R.string.errorInInternetConnection));
            }catch (Exception ex){}
        }
    }


//    public void onActivityResult(int requestCode, int resultCode, Intent data) {
//        if (requestCode == 1)
//            if (resultCode == activity.RESULT_OK) {
//                if (true) {
//                    posts.getItems().get(position).setTotal_comment(Integer.valueOf(posts.getItems().get(position).getTotal_comment()) + 1 + "");
//                    tvCommentUnique.setText(posts.getItems().get(position).getTotal_comment());
//                    final Comment commentItem = (Comment) data.getSerializableExtra("commentItem");
////                    tvDate_comment_global.setText(commentItem.getPost_convert_time());
////                    tvCaption_comment_global.setText(commentItem.getText());
////                    tvTitle_comment_global.setText(commentItem.getFull_name());
//
////                    Common.getUserImageAndFill(activity,commentItem.getUser_image(),postPic_comment_global);
//
//
//                    if (posts.getItems().get(position).getTotal_comment().equals("0") || posts.getItems().get(position).getTotal_comment()== null)
//                        commentLayoutAll_global.setVisibility(View.GONE);
//                    else if (posts.getItems().get(position).getTotal_comment().equals("1")) {
//                        commentLayoutAll_global.setVisibility(View.VISIBLE);
//                        commentLayoutFirst_global.setVisibility(View.VISIBLE);
//                        commentLayoutSecond_global.setVisibility(View.GONE);
//                    } else {
//                        commentLayoutAll_global.setVisibility(View.VISIBLE);
//                        commentLayoutSecond_global.setVisibility(View.VISIBLE);
//                        commentLayoutFirst_global.setVisibility(View.VISIBLE);
//                    }
//
//                }
////                if (true) {
////                    posts.getItems().get(position).setFeed_total_like(Integer.valueOf(posts.getItems().get(position).getFeed_total_like()) + 1 + "");
////                    posts.getItems().get(position).setFeed_is_liked_b(true);
////                    tvLikeUnique.setText(posts.getItems().get(position).getFeed_total_like());
////
////                    likeUnique.setUnlikeDrawableRes(R.drawable.thumb_on);
////                    likeUnique.setLiked(true);
////
////                } else if (true) {
////                    posts.getItems().get(position).setFeed_total_like(Integer.valueOf(posts.getItems().get(position).getFeed_total_like()) - 1 + "");
////                    tvLikeUnique.setText(posts.getItems().get(position).getFeed_total_like());
////                    posts.getItems().get(position).setFeed_is_liked_b(false);
////
////                    likeUnique.setUnlikeDrawableRes(R.drawable.thumb_off);
////                    likeUnique.setLiked(false);
////
////                }
//            }
//
//        if (requestCode == 2) {
////            SharedPreferences values = mContext.getSharedPreferences(Statics.MAHAN, 0);
////            if (values.getBoolean("changeC", false)) {
////                _user.getPostItems().get(pos).setTotal_comment(Integer.valueOf(_user.getPostItems().get(pos).getTotal_comment()) + 1 + "");
////                tvCommentUnique.setText(_user.getPostItems().get(pos).getTotal_comment());
////                final Comment commentItem = (Comment) data.getSerializableExtra("commentItem");
////                tvDate_comment_global.setText(commentItem.getPost_convert_time());
////                tvCaption_comment_global.setText(commentItem.getText());
////                tvTitle_comment_global.setText(commentItem.getFull_name());
////                //imageLoader.DisplayImage(commentItem.getUser_image(), postPic_comment_global, true);
////
////                Common.getUserImageAndFill(mContext , commentItem.getUser_image(),postPic_comment_global);
//////                Picasso.with(profileActivity)
//////                        .load(commentItem.getUser_image())
//////                        .networkPolicy(NetworkPolicy.OFFLINE)
//////                        .transform(new CircleTransform())
//////                        .into(postPic_comment_global, new Callback() {
//////                            @Override
//////                            public void onSuccess() {
//////
//////                            }
//////
//////                            @Override
//////                            public void onError() {
//////                                //Try again online if cache failed
//////                                Picasso.with(profileActivity)
//////                                        .load(commentItem.getUser_image())
//////                                        .error(R.drawable.profile_ic_sajjad)
//////                                        .transform(new CircleTransform())
//////                                        .into(postPic_comment_global, new Callback() {
//////                                            @Override
//////                                            public void onSuccess() {
//////
//////                                            }
//////
//////                                            @Override
//////                                            public void onError() {
//////                                                Log.v("Picasso","Could not fetch image");
//////                                            }
//////                                        });
//////                            }
//////                        });
////
////
////
////
////                if (_user.getPostItems().get(pos).getTotal_comment().equals("0"))
////                    commentLayoutAll_global.setVisibility(View.GONE);
////                else if (_user.getPostItems().get(pos).getTotal_comment().equals("1")) {
////                    commentLayoutAll_global.setVisibility(View.VISIBLE);
////                    commentLayoutFirst_global.setVisibility(View.VISIBLE);
////                    commentLayoutSecond_global.setVisibility(View.GONE);
////                } else {
////                    commentLayoutAll_global.setVisibility(View.VISIBLE);
////                    commentLayoutSecond_global.setVisibility(View.VISIBLE);
////                    commentLayoutFirst_global.setVisibility(View.VISIBLE);
////                }
////            }
////            if (values.getBoolean("changeLT", false)) {
////                _user.getPostItems().get(pos).setFeed_total_like(Integer.valueOf(_user.getPostItems().get(pos).getFeed_total_like()) + 1 + "");
////                _user.getPostItems().get(pos).setFeed_is_liked_b(true);
////                tvLikeUnique.setText(_user.getPostItems().get(pos).getFeed_total_like());
////                likeUnique.setUnlikeDrawableRes(R.drawable.thumb_on);
////                likeUnique.setLiked(true);
////
////            } else if (values.getBoolean("changeLF", false)) {
////                _user.getPostItems().get(pos).setFeed_total_like(Integer.valueOf(_user.getPostItems().get(pos).getFeed_total_like()) - 1 + "");
////                tvLikeUnique.setText(_user.getPostItems().get(pos).getFeed_total_like());
////                _user.getPostItems().get(pos).setFeed_is_liked_b(false);
////                likeUnique.setUnlikeDrawableRes(R.drawable.thumb_off);
////                likeUnique.setLiked(false);
////            }
//        } else if (requestCode == 3 && resultCode == -1) {
//            Uri uri = data.getData();
//            //path = new getPath().getPath(mContext, uri);
////            new AsyncInsertUserImage().execute(path);
//        } else if (requestCode == 4 && resultCode == -1) {
////            coverPicButton.setVisibility(View.INVISIBLE);
//            Uri uri = data.getData();
//            //path = new getPath().getPath(mContext, uri);
////            new AsyncInsertCoverImage().execute(path);
//        }
//    }
//




    boolean animationAvalable = false;
    public void notifyDataSetChanged(boolean animationAvalable) {
        this.animationAvalable = animationAvalable ;
        notifyDataSetChanged();
    }






    @Override
    public void onViewDetachedFromWindow(final PostViewHolder holder) {
        super.onViewDetachedFromWindow(holder);
        holder.itemView.clearAnimation();

        ((PostViewHolder)holder).clearAnimation();

    }


    @Override
    public int getItemCount() {
        return mTimelineItemList.size();
    }

    @Override
    public PostViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        if (viewType == YAFTE){
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout._row_of_yafte_item, parent, false);
            YafteItemViewHolder yafteItemViewHolder = new YafteItemViewHolder(view);
            return yafteItemViewHolder;
        }else if (viewType == BLOG){
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout._row_of_blog_item, parent, false);
            BlogItemViewHolder yafteItemViewHolder = new BlogItemViewHolder(view);
            return yafteItemViewHolder;
        }else
            return null;

//        switch (viewType) {
//            case 1:
//                return new TimeLineItemViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.row_price, parent, false), viewType);
//            case 2:
//                return new TimeLineItemViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.row_notice, parent, false), viewType);
//            case 3:
//                return new TimeLineItemViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.row_addad, parent, false), viewType);
//            case 4:
//                return new TimeLineItemViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout._row_image_of_a_car, parent, false), viewType);

//        }
    }





    @Override
    public void onBindViewHolder(final PostViewHolder holder, final int position) {

        ((ViewGroup.MarginLayoutParams) holder.itemView.getLayoutParams()).topMargin = 0;
        if (position == getItemCount() - 1) {
            ((ViewGroup.MarginLayoutParams) holder.itemView.getLayoutParams()).bottomMargin = holder.marginBottom + navigationHeight;
        } else if (position == 0 && !hasAppBarLayout) {
            ((ViewGroup.MarginLayoutParams) holder.itemView.getLayoutParams()).topMargin = scrollHelper.getToolbarHeight();
        } else {
            ((ViewGroup.MarginLayoutParams) holder.itemView.getLayoutParams()).bottomMargin = holder.marginBottom;
        }

//        if (mTimelineItemList.get(position) instanceof TimelineItemBase) {
//            if (((TimelineItemBase) (mTimelineItemList.get(position))).getID_TimelineType() == 3) { //.getNmae().equals("CarPictures")){
////                holder.adView = xMainActivity.adView;
//            }
//        }
//        if(mTimelineItemList.get(position) instanceof TimelineItemBase)
//            if (((TimelineItemBase)(mTimelineItemList.get(position))).getID_TimelineType() == 1 ){ //.getNmae().equals("CarPictures")){
//                Gson gson = new Gson();
//                String json0000 = gson.toJson(((TimelineItemBase)(mTimelineItemList.get(position))).getObject());
//                if(!json0000.equals("null")) {
//                    final PictureItem pictureItem = gson.fromJson(json0000, PictureItem.class);
//
//                    holder.textViewPictureTitle.setText(pictureItem.getCarName());
//                    holder.textViewPictureDescription.setText(Tag.getTagListAsString(pictureItem.getListTag()));
//                    holder.textViewCarName.setText(pictureItem.getCarName());
//
////                    Picasso.with(mContext)
////                            .load(String.format(Url.MEDIA_SERVER_ADDRESS + "Files/Pictures/LogoOfCompanies/48/%s.jpg", pictureItem.getCarLogo()))
////                            .placeholder(R.drawable.progress_animation)
////                            //.centerInside()
////                            .transform(transformation)
////                            .into(holder.imageViewCarLogo);
//
//                    Picasso.with(mContext)
//                            //.load(String.format(Url.MEDIA_SERVER_ADDRESS + "Files/Pictures/LogoOfCompanies/48/%s.jpg", header.getPicture()))
//                            .load(String.format(Url.MEDIA_SERVER_ADDRESS + "Files/Pictures/LogoOfCompanies/50/%s.png", pictureItem.getCarLogo()))
//                            .placeholder(R.drawable.progress_animation)
//                            .into(holder.imageViewCarLogo);
//    //            Picasso.with(mContext)
//    //                    .load(pictureItem.getFilePath()+".koi")
//    //                    .resize(600,300)
//    //                    .centerCrop()
//    //                    //.placeholder( R.drawable.rotator)
//    //                    .into(new Target() {
//    //                        @Override
//    //                        public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
//    //                            //finalViewHolder.asanaImg.setImageBitmap(bitmap);
//    //                            //stop progressbar here
//    //                            // Bitmap is loaded, use image here
//    //                            holder.imageViewPicture.setImageBitmap(bitmap);
//    //                        }
//    //
//    //                        @Override
//    //                        public void onBitmapFailed(Drawable errorDrawable) {
//    //                            //stop progressbar here
//    //                            // Fires if bitmap couldn't be loaded.
//    //                            holder.imageViewPicture.setImageDrawable(mContext.getResources().getDrawable(R.drawable.png_image_not_found));
//    //                        }
//    //
//    //                        @Override
//    //                        public void onPrepareLoad(Drawable placeHolderDrawable) {
//    //                            //holder.imageViewPicture.setImageDrawable(mContext.getResources().getDrawable(R.drawable.png_login));
//    //
//    //                            Animation myAnim = AnimationUtils.loadAnimation(mContext.getApplicationContext(), R.anim.rotator);
//    //                            placeHolderDrawable = mContext.getResources().getDrawable(R.drawable.png_download);
//    //                            holder.imageViewPicture.setImageDrawable(placeHolderDrawable);
//    //                            holder.imageViewPicture.startAnimation(myAnim);
//    //                        }
//    //
//    //                    });
//    ////                    //.into();
//    //
//
//
//
//                    Picasso.with(mContext)
//                            .load(pictureItem.getFilePath())
//                            .placeholder(R.drawable.progress_animation_2)
//                            .networkPolicy(NetworkPolicy.OFFLINE)
//                            .into(holder.imageViewPicture, new Callback() {
//                                @Override
//                                public void onSuccess() {
//                                }
//
//                                @Override
//                                public void onError() {
//                                    Picasso.with(mContext)
//                                            .load(pictureItem.getFilePath())
//                                            .error(R.drawable.png_image_not_found)
//                                            .placeholder(R.drawable.progress_animation_2)
//                                            .into(holder.imageViewPicture, new Callback() {
//                                                @Override
//                                                public void onSuccess() {
//
//                                                }
//
//                                                @Override
//                                                public void onError() {
//                                                    Log.v("Picasso", "Could not fetch image");
//
//                                                    Picasso.with(mContext)
//                                                            .load(R.drawable.png_image_not_found)
//                                                            .into(holder.imageViewPicture);
//                                                }
//                                            });
//                                }
//                            });
//
//                    holder.imageViewPicture.setOnClickListener(new View.OnClickListener() {
//                        @Override
//                        public void onClick(View v) {
//                            Intent viewOfPlayer = new Intent(mContext, ImageAndVideoPlayer.class);
//                            viewOfPlayer.putExtra("fileFormat", "jpg");
//                            viewOfPlayer.putExtra("filePath", pictureItem.getFilePath());
//                            mContext.startActivity(viewOfPlayer);
//                            ((Activity) mContext).overridePendingTransition(R.anim.fadeout, R.anim.fadein);
//
//
//                        }
//                    });
//                }
//            }
//        if(mTimelineItemList.get(position) instanceof TimelineItemBase)
//            if (((TimelineItemBase)(mTimelineItemList.get(position))).getID_TimelineType() == 2 ){ //.getNmae().equals("CarsPrice")){
//
//                //Gson gson = new Gson();
//                Gson gson = new GsonBuilder().registerTypeAdapter(Date.class, new JsonDateDeserializer()).create();
//                String json0000 = gson.toJson(((TimelineItemBase)(mTimelineItemList.get(position))).getObject());
//                PriceItem priceItem = gson.fromJson(json0000,PriceItem.class);
//
//                holder.textViewCarName.setText(priceItem.getCar().getNameFa());
//                holder.textViewPriceType.setText(priceItem.getPriceType().getName());
//
//
//
//                DateConverterSjd dateUtiliti = new DateConverterSjd();
//                holder.textViewCarModel.setText(dateUtiliti.getCurrentShamsidateModel(priceItem.getModelDate().toString()));
//
//
//                holder.textViewPrice.setAmount(Float.parseFloat(priceItem.carPrice)/1000);
//                holder.textViewPrice.setSymbol(priceItem.getCurrency().getName_Local());
//                //holder.textViewPrice.setText((priceItem.getCarPrice()) + " " + priceItem.getCurrency().getName_Local());
//
//
//
//                holder.textViewCurrencyLocal.setText(priceItem.getCurrency().getName_Local());
//                Picasso.with(mContext)
//                        .load(String.format(Url.MEDIA_SERVER_ADDRESS + "Files/Pictures/LogoOfCompanies/48/%s.jpg", priceItem.getCar().getCompany().getLogo()))
//                        .placeholder(R.drawable.progress_animation)
//                        //.centerInside()
//                        .into(holder.imageViewCar);
//
//
//    //            Picasso.with(mContext)
//    //                    .load(String.format(Url.MEDIA_SERVER_ADDRESS + "Files/Pictures/LogoOfCompanies/48/%s.jpg", priceItem.getCar().getCompany().getLogo()))
//    //                    .resize(600,300)
//    //                    .centerInside()
//    //                    .placeholder(R.drawable.progress_animation)
//    //                    .into(holder.imageViewCar);
//
//    //            holder.imageViewCar.setOnClickListener(new View.OnClickListener() {
//    //                @Override
//    //                public void onClick(View v) {
//    //                    openimage();
//    //                }
//    //
//    //                private void openimage() {
//    //                    mContext.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(priceItem.getCar_Picture()))); /** replace with your own uri */
//    //                }
//    //            });
//    //            holder.textViewCarName.setOnClickListener(new View.OnClickListener() {
//    //                @Override
//    //                public void onClick(View v) {
//    //                    mContext.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(priceItem.getCar_Picture()))); /** replace with your own uri */
//    //                }
//    //            });
//
//            }
        if (mTimelineItemList.get(position) instanceof BlogItem){
            if ((((BlogItem)mTimelineItemList.get(position)).getCategoryID() == 16) ||
                    (((BlogItem)mTimelineItemList.get(position)).getCategoryID() == 17)||
                    (((BlogItem)mTimelineItemList.get(position)).getCategoryID() == 18)) {
                Yafte yafte = new Yafte();
                yafte.prepareYafteItem(mContext , (YafteItemViewHolder) holder, mTimelineItemList, position); //سرقتی / گمشده / پیدا شده
            }else {
                Blog blog = new Blog();
                blog.prepareBlogItem(mContext,mProgressBar, (BlogItemViewHolder) holder,mTimelineItemList, position); //blog
            }
        }
// else if (mTimelineItemList.get(position).getItemType().equals("1")){
//
//            NoticeItem noticeItem = (NoticeItem) mTimelineItemList.get(position);
//            holder.textViewNoticeDescription.setText(noticeItem.getDiscription());
//            holder.textViewNoticeTitle.setText(noticeItem.getTitle());
//            //holder.imageViewCar.setImageResource(priceItem.getCarClass_Pricture());
//
//        }else if (mTimelineItemList.get(position).getItemType().equals("addad")){
//            NoticeItem addadItem = (NoticeItem) mTimelineItemList.get(position);
//
//        }else if (mTimelineItemList.get(position).getItemType().equals("imageOfACar")){
//            NoticeItem imageOfACarItem = (NoticeItem) mTimelineItemList.get(position);
//            if (imageOfACarItem.getDiscription() != null) {
//                holder.textViewNoticeDescription.setText(imageOfACarItem.getDiscription());
//            }
//            if (imageOfACarItem.getTitle() != null) {
//                holder.textViewNoticeTitle.setText(imageOfACarItem.getTitle());
//            }
//
//            Picasso.with(mContext).load(imageOfACarItem.getImageUrl()).into(holder.imageViewNotice);
//        }
//        else{
//
//        }

//        if(animationAvalable) {
//            Animation animation = AnimationUtils.loadAnimation(mContext, (position > lastPosition) ? R.anim.zoomout2 : R.anim.zoomout2);
//            holder.itemView.startAnimation(animation);
//            lastPosition = position;
//        }else
//            animationAvalable = true;

        // Here you apply the animation when the view is bound
        setAnimation(holder.itemView, position);
    }



    public static void prepareToShare(Context mContext,String filePath, String statement, boolean loadedImage) {
        //String FileName = SaveScreenshot();

        String FileName = null;
        if(loadedImage)
            FileName = SaveImage(mContext,filePath,null);

        Intent intent4 = new Intent(Intent.ACTION_SEND);
//        intent4.setType("image/*");
//        intent4.setType("image/jpeg");
        intent4.setType("*/*");

        if(loadedImage)
            intent4.putExtra(Intent.EXTRA_STREAM, Uri.parse(FileName));

        intent4.putExtra(Intent.EXTRA_TEXT,statement + "" + mContext.getString(R.string.ShareText2));
        mContext.startActivity(Intent.createChooser(intent4, mContext.getString(R.string.ShareTitle)));
    }


    static File apkStorage = null;
    static File outputFile = null;
    private static String SaveImage(Context mContext , String urlString ,DilatingDotsProgressBar dilatingDotsProgressBar) {
        try {
            if(dilatingDotsProgressBar != null)
                dilatingDotsProgressBar.showNow();
            //
            URL url = new URL(urlString);                                           //Create Download URl
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();//Open Url Connection
            connection.setRequestMethod("GET");                                     //Set Request Method to "GET" since we are grtting data
            connection.connect();                                                   //connect the URL Connection

            //If Connection response is not OK then show Logs
            if (connection.getResponseCode() != HttpURLConnection.HTTP_OK) {
                Toast.makeText(mContext,"Error" , Toast.LENGTH_LONG).show();
            }

            //Get File if SD card is present
            if (Global.isSDCardPresent()) {
                apkStorage = Global.getFileStoragePath();
            }
            //else
            //Toast.makeText(context, "Oops!! There is no SD Card.", Toast.LENGTH_SHORT).show();

            //If File is not present create directory
            if (!apkStorage.exists()) {
                apkStorage.mkdir();
            }

            String fileNameWithoutExtn = url.toString().substring(0, url.toString().lastIndexOf('.'));
            String fileName = Global.getFileFullName(url.toString());
            outputFile = new File(apkStorage,fileName );                                //Create Output file in Main File
            List values = connection.getHeaderFields().get("content-Length");

//            try {
//                // Make sure the Pictures directory exists.
//                //File path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
//                File path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM);
//                path.mkdirs();
//            }catch (Exception ex){
//                int a =  2 + 6 ;
//            }

            File path = Environment.getExternalStoragePublicDirectory( Environment.DIRECTORY_PICTURES);
//            final File path = Environment.getExternalStoragePublicDirectory
//                            (
//                                    //Environment.DIRECTORY_PICTURES
//                                    //Environment.DIRECTORY_DCIM
//                                    Environment.DIRECTORY_DCIM + "/TubelessImages/"
//                            );

            File file = new File(Environment.getExternalStorageDirectory().getAbsoluteFile(), fileName);    //InternalStoreage
            //File file = new File(Environment.getDataDirectory().getAbsoluteFile(), fileName);             //InternalStoreage

            //File file = new File(path, fileName);
            try {
                // Make sure the Pictures directory exists.
                path.mkdirs();
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }

            if (!outputFile.getParentFile().exists())
                outputFile.getParentFile().mkdirs();
            if (!outputFile.exists())
                outputFile.createNewFile();



            //Create New File if not present
            if (!outputFile.exists()) {
                if (!outputFile.getParentFile().exists())
                    outputFile.getParentFile().mkdirs();
                if (!outputFile.exists())
                    outputFile.createNewFile();
            }

            FileOutputStream fos = new FileOutputStream(outputFile);//Get OutputStream for NewFile Location

            InputStream is = connection.getInputStream();//Get InputStream for connection
            String sLength;
            int sLengthInt = 0;
            if (values != null && !values.isEmpty()) {
                // getHeaderFields() returns a Map with key=(String) header
                // name, value = List of String values for that header field.
                // just use the first value here.
                sLength = (String) values.get(0);

                if (sLength != null) {
                    //parse the length into an integer...
                }
                sLengthInt = Integer.parseInt(sLength);
            }

            byte[] buffer = new byte[1024];//Set buffer type
            int len1 = 0;//init length
            int downloadedSize = 0;
            int counter=1;
            long total = 0;

            int darsad = 0,darsadTmp = 0;
            while ((len1 = is.read(buffer)) != -1) {
                total += len1;
                fos.write(buffer, 0, len1);//Write new file

                downloadedSize += len1;
                darsad = (int)((total*100)/sLengthInt);
                if(darsad != darsadTmp) {
                    darsadTmp = darsad;
                }
            }
            //Close all connection after doing task
            fos.close();
            is.close();

            if (dilatingDotsProgressBar != null)
                dilatingDotsProgressBar.hideNow();
        } catch (Exception e) {
            //Read exception if something went wrong
            e.printStackTrace();
            outputFile = null;
            if (dilatingDotsProgressBar != null)
                dilatingDotsProgressBar.hideNow();
        }

        return "file://" + outputFile.toString();
    }

//    @Override
//    public void onViewRecycled( TimeLineItemViewHolder holder) {
//        super.onViewRecycled(holder);
//        //xxxxx
//        //Toast.makeText(mContext,"onViewRecycled", Toast.LENGTH_SHORT).show();
//    }

    private void setAnimation(View viewToAnimate, int position) {
        // If the bound view wasn't previously displayed on screen, it's animated
        if (position > lastPosition)
        {
            //Animation animation = AnimationUtils.loadAnimation(mContext, android.R.anim.slide_in_left);
            Animation animation = AnimationUtils.loadAnimation(mContext, R.anim.slide_in_left_timeline);
            viewToAnimate.startAnimation(animation);
            lastPosition = position;
        }
    }

    @Override
    public int getItemViewType(int position) {
//        if ((mTimelineItemList.get(position)) instanceof TimelineItemBase &&
//                ((TimelineItemBase) (mTimelineItemList.get(position))).getNmae().equals("CarPictures")) {
//            return 4;
//        }
//
//        if (mTimelineItemList.get(position) instanceof TimelineItemBase &&
//                ((TimelineItemBase)(mTimelineItemList.get(position))).equals("CarsPrice"))
//            return 1;
//
//        if ( mTimelineItemList.get(position) instanceof TimelineItemBase &&
//                (((TimelineItemBase)(mTimelineItemList.get(position))).getID_TimelineType() == 100))  //addad
//            return 3;


//        if (mTimelineItemList.get(position) instanceof ir.sajjadyosefi.android.xTubeless.classes.modelY.yafte.Yafte){
            if ((((BlogItem)mTimelineItemList.get(position)).getCategoryID() == 16) ||
                    (((BlogItem)mTimelineItemList.get(position)).getCategoryID() == 17)||
                    (((BlogItem)mTimelineItemList.get(position)).getCategoryID() == 18))
                return YAFTE;
            else if (((BlogItem)mTimelineItemList.get(position)).getCategoryID() < 16){
                return BLOG; //blog
            }
//        }
        return 1;
    }


}