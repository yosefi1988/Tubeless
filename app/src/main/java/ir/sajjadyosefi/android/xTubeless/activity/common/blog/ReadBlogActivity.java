package ir.sajjadyosefi.android.xTubeless.activity.common.blog;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.gson.Gson;
import com.readystatesoftware.systembartint.SystemBarTintManager;
import com.squareup.picasso.Picasso;

import java.io.Serializable;
import java.util.ArrayList;

import ir.sajjadyosefi.android.xTubeless.BuildConfig;
import ir.sajjadyosefi.android.xTubeless.R;
import ir.sajjadyosefi.android.xTubeless.Global;
import ir.sajjadyosefi.android.xTubeless.activity.TubelessTransparentStatusBarActivity;
import ir.sajjadyosefi.android.xTubeless.activity.common.ContainerActivity;
import ir.sajjadyosefi.android.xTubeless.classes.StaticValue;
import ir.sajjadyosefi.android.xTubeless.classes.model.exception.TubelessException;
import ir.sajjadyosefi.android.xTubeless.classes.model.post.NewTimelineItem;
import ir.sajjadyosefi.android.xTubeless.classes.model.post.ParentItem;
import ir.sajjadyosefi.android.xTubeless.classes.model.post.blog.CommentItem;
import ir.sajjadyosefi.android.xTubeless.classes.model.request.NewVoteRequest;
import ir.sajjadyosefi.android.xTubeless.classes.model.response.ServerResponseBase;


import ir.sajjadyosefi.android.xTubeless.classes.model.response.TimelineItemResponse;
import ir.sajjadyosefi.android.xTubeless.networkLayout.retrofit.TubelessRetrofitCallbackss;
import ir.sajjadyosefi.android.xTubeless.networkLayout.retrofit.tmp.TimelineItem;
import ir.sajjadyosefi.android.xTubeless.utility.DateConverterSjd;
import it.sephiroth.android.library.bottomnavigation.BottomNavigation;
import retrofit2.Call;

import static ir.sajjadyosefi.android.xTubeless.Adapter.FirstFragmentsAdapter.TYPE_COMMENTS;
import static ir.sajjadyosefi.android.xTubeless.activity.common.ContainerActivity.READ_BLOG_COMMENTS;

/**
 * Created by sajjad on 2/11/2018.
 */

public class ReadBlogActivity extends TubelessTransparentStatusBarActivity {

    Context mContext = null;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);

        setContentView(R.layout.activity_read_blog);
        mContext = this;

        textViewUserName = findViewById(R.id.textViewUserName);
        textViewTitle = findViewById(R.id.textViewTitle);
        textViewLocation = findViewById(R.id.textViewLocation);
        textViewDate = findViewById(R.id.textViewDate);
        textViewCount = findViewById(R.id.textViewCount);
        textViewShare = findViewById(R.id.textViewShare);
        textViewComments = findViewById(R.id.textViewComments);
        textViewText = findViewById(R.id.textViewText);
        imageViewUserAvatar = findViewById(R.id.imageViewUserAvatar);
        imageViewShare = findViewById(R.id.imageViewShare);
        imageViewComments = findViewById(R.id.imageViewComments);
        imageviewPicture = findViewById(R.id.imageviewPicture);
        viewHeader = findViewById(R.id.header);



        Gson gson = new Gson();

        //old Transfer
//        String objectString = getIntent().getStringExtra("Object");

        //new transfer
        Bundle intent = getIntent().getExtras();
        String objectString = intent.getString("Object");
        String type = intent.getString("Type");

        ParentItem blogItem = null;
        if (type.equals("TimelineItem")){
//            blogItem = gson.fromJson(objectString, TimelineItem.class);
//            firstFillData((TimelineItem)blogItem);
//
//            fillTitle(getContext(),((TimelineItem) blogItem).getTitle(),((TimelineItem) blogItem).getCategoryID(),textViewTitle);

        }else if (type.equals("NewTimelineItem")){
            blogItem = gson.fromJson(objectString, NewTimelineItem.class);
            firstFillData((NewTimelineItem)blogItem);

            fillTitle(
                    getContext(),
                    ((NewTimelineItem) blogItem).getTitle(),
                    ((NewTimelineItem) blogItem).getCategoryID(),
                    textViewTitle);

        }else if (type.equals("MainPageNews")){

            blogItem = gson.fromJson(objectString, TimelineItem.class);
            firstFillDataNews((TimelineItem)blogItem);

            fillTitle(getContext(),((TimelineItem) blogItem).getTitle(),((TimelineItem) blogItem).getCategoryID(),textViewTitle);
        }


        TubelessRetrofitCallbackss ssssssss = new TubelessRetrofitCallbackss(getContext(), TimelineItemResponse.class) {
            @Override
            public void t_beforeSendRequest() {
                if (progressDialog != null)
                    progressDialog.show();
            }

            @Override
            public void t_afterGetResponse() {
//                progressDialog.hide();
                progressDialog.cancel();
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
                int a = 5 ;
                a++;
//                fillData(((TimelineItemResponse)response).getTimelineItem());
            }
        };
        Global.apiManagerTubeless.getTimelineItem(blogItem.getBlogID(), ssssssss);




//        //vote
//        NewVoteRequest aaaa = new NewVoteRequest();
////      aaaa.setUserID((int)Global.user.getUserId());
//        aaaa.setUserID(49);
//        aaaa.setCommentId(11);
//        aaaa.setVote(true);
//        newVote(aaaa);
     }

    public static void fillTitle(Context context ,String title , int cat , TextView textViewTitle) {
        StringBuilder stringBuilderTitle = new StringBuilder();

        stringBuilderTitle.append(title);



        if (BuildConfig.FLAVOR_version_name.equals("tubeless")){

        }else if (BuildConfig.FLAVOR_version_name.equals("yadak")){

        }else {

        }

        if (context.getResources().getInteger(R.integer.cat1Yadak) == cat){
            stringBuilderTitle.append(" (");
            stringBuilderTitle.append(context.getString(R.string.type1_yadak));
            stringBuilderTitle.append(")");
        }else if (context.getResources().getInteger(R.integer.cat2Yadak) == cat){
            stringBuilderTitle.append(" (");
            stringBuilderTitle.append(context.getString(R.string.type2_yadak));
            stringBuilderTitle.append(")");
        }else if (context.getResources().getInteger(R.integer.cat3Yadak) == cat){
            stringBuilderTitle.append(" (");
            stringBuilderTitle.append(context.getString(R.string.type3_yadak));
            stringBuilderTitle.append(")");
        }else if (context.getResources().getInteger(R.integer.cat1Yafte) == cat){
            stringBuilderTitle.append(" (");
            stringBuilderTitle.append(context.getString(R.string.type1_yafte));
            stringBuilderTitle.append(")");
        }else if (context.getResources().getInteger(R.integer.cat2Yafte) == cat){
            stringBuilderTitle.append(" (");
            stringBuilderTitle.append(context.getString(R.string.type2_yafte));
            stringBuilderTitle.append(")");
        }else if (context.getResources().getInteger(R.integer.cat3Yafte) == cat){
            stringBuilderTitle.append(" (");
            stringBuilderTitle.append(context.getString(R.string.type3_yafte));
            stringBuilderTitle.append(")");
        }else {
            stringBuilderTitle.append(" - ");
            stringBuilderTitle.append(cat + "");
        }
        textViewTitle.setText(stringBuilderTitle.toString());
    }

    public static String fillTitleForShare(Context context ,String title , int cat) {
        StringBuilder stringBuilderTitle = new StringBuilder();
        stringBuilderTitle.append(title);


        if (BuildConfig.FLAVOR_version_name.equals("tubeless")){

        }else if (BuildConfig.FLAVOR_version_name.equals("yadak")){

        }else {

        }

        if (context.getResources().getInteger(R.integer.cat1Yadak) == cat){
            stringBuilderTitle.append(" (");
            stringBuilderTitle.append(context.getString(R.string.type1_yadak));
            stringBuilderTitle.append(")");
        }else if (context.getResources().getInteger(R.integer.cat2Yadak) == cat){
            stringBuilderTitle.append(" (");
            stringBuilderTitle.append(context.getString(R.string.type2_yadak));
            stringBuilderTitle.append(")");
        }else if (context.getResources().getInteger(R.integer.cat3Yadak) == cat){
            stringBuilderTitle.append(" (");
            stringBuilderTitle.append(context.getString(R.string.type3_yadak));
            stringBuilderTitle.append(")");
        }else if (context.getResources().getInteger(R.integer.cat1Yafte) == cat){
            stringBuilderTitle.append(" (");
            stringBuilderTitle.append(context.getString(R.string.type1_yafte));
            stringBuilderTitle.append(")");
        }else if (context.getResources().getInteger(R.integer.cat2Yafte) == cat){
            stringBuilderTitle.append(" (");
            stringBuilderTitle.append(context.getString(R.string.type2_yafte));
            stringBuilderTitle.append(")");
        }else if (context.getResources().getInteger(R.integer.cat3Yafte) == cat){
            stringBuilderTitle.append(" (");
            stringBuilderTitle.append(context.getString(R.string.type3_yafte));
            stringBuilderTitle.append(")");
        }else {
            stringBuilderTitle.append(" - ");
            stringBuilderTitle.append(cat + "");
        }

        return stringBuilderTitle.toString();
    }


    @Override
    public SystemBarTintManager getSystemBarTint() {
        return null;
    }

    @Override
    public boolean hasTranslucentNavigation() {
        return false;
    }

    @Override
    public boolean hasTranslucentStatusBar() {
        return false;
    }

    @Override
    public BottomNavigation getBottomNavigation() {
        return null;
    }

    @Override
    public int getNavigationBarHeight() {
        return 0;
    }

    @Override
    public boolean hasManagedToolbarScroll() {
        return false;
    }

    @Override
    public boolean hasAppBarLayout() {
        return false;
    }

    @Override
    public Toolbar getToolbar() {
        return null;
    }


    TextView textViewUserName,textViewTitle,textViewLocation,textViewDate,textViewCount,textViewShare,textViewComments,textViewText;
    ImageView imageViewUserAvatar, imageViewShare,imageViewComments,imageviewPicture;
    View viewHeader;

    private void firstFillData(NewTimelineItem newTimelineItem) {

        DateConverterSjd dateUtiliti = new DateConverterSjd();

        if (newTimelineItem.getCategoryID() == StaticValue.newsCategory){
            viewHeader.setVisibility(View.GONE);
        }else {
            viewHeader.setVisibility(View.VISIBLE);
        }


        if (newTimelineItem.getTitlePicture() != null && newTimelineItem.getTitlePicture().length() > 10) {
            imageviewPicture.setVisibility(View.VISIBLE);
            Picasso.get()
                    .load(newTimelineItem.getTitlePicture())
                    .placeholder(R.drawable.bg_search)
                    //.centerInside()
                    //.transform(transformation)
                    .into(imageviewPicture, new com.squareup.picasso.Callback() {
                        @Override
                        public void onSuccess() {

                        }

                        @Override
                        public void onError(Exception e) {

                            Picasso.get()
                                    .load(R.drawable.png_image)
                                    //.transform(transformation)
                                    .into(imageviewPicture);
                        }
                    } );

        }else {
            imageviewPicture.setVisibility(View.GONE);
        }



        textViewLocation.setText(newTimelineItem.getTextFromJson());

//        if (newTimelineItem.getText() == null)
            textViewText.setVisibility(View.GONE);
//        else
//            textViewText.setText(newTimelineItem.getText());


        StringBuilder stringBuilder = new StringBuilder();
//        stringBuilder.append(newTimelineItem.getRegisterDate());
        stringBuilder.append(" ( ");
        stringBuilder.append("تاریخ ثبت : ");
        stringBuilder.append(newTimelineItem.getRegisterDate());
        stringBuilder.append(" ) ");
        textViewDate.setText(stringBuilder.toString());

        textViewUserName.setText(newTimelineItem.getUserNameMasked(newTimelineItem.getUserName()));
        textViewCount.setText(newTimelineItem.getViewCount() + "");



//        if(yafteItem.isInMyFavList())
//            holder.imageViewFavourite.setImageResource(android.R.drawable.btn_star_big_on);
//        else
//            holder.imageViewFavourite.setImageResource(android.R.drawable.btn_star_big_off);




        if (newTimelineItem.getUserImage().length() < 5){
            Picasso.get()
                    .load(R.drawable.png_user)
                    //.transform(transformation)
                    .into(imageViewUserAvatar);
        }else {
            Picasso.get()
                    .load(newTimelineItem.getUserImage())
                    .placeholder(R.drawable.bg_search)
                    //.centerInside()
                    //.transform(transformation)
                    .into(imageViewUserAvatar, new com.squareup.picasso.Callback() {
                        @Override
                        public void onSuccess() {

                        }

                        @Override
                        public void onError(Exception e) {
                            Picasso.get()
                                    .load(R.drawable.png_user)
                                    //.transform(transformation)
                                    .into(imageViewUserAvatar);
                        }
                    });
        }


        fillClicks(newTimelineItem);
    }
    private void firstFillDataNews(TimelineItem newTimelineItem) {

        DateConverterSjd dateUtiliti = new DateConverterSjd();

        if (newTimelineItem.getCategoryID() == StaticValue.newsCategory){
            viewHeader.setVisibility(View.GONE);
        }else {
            viewHeader.setVisibility(View.VISIBLE);
        }


        if (newTimelineItem.getPicture() != null && newTimelineItem.getPicture().length() > 10) {
            imageviewPicture.setVisibility(View.VISIBLE);
            Picasso.get()
                    .load(newTimelineItem.getPicture())
                    .placeholder(R.drawable.bg_search)
                    //.centerInside()
                    //.transform(transformation)
                    .into(imageviewPicture, new com.squareup.picasso.Callback() {
                        @Override
                        public void onSuccess() {

                        }

                        @Override
                        public void onError(Exception e) {

                            Picasso.get()
                                    .load(R.drawable.png_image)
                                    //.transform(transformation)
                                    .into(imageviewPicture);
                        }
                    } );

        }else {
            imageviewPicture.setVisibility(View.GONE);
        }



        textViewLocation.setText(newTimelineItem.getLocation());

//        if (newTimelineItem.getText() == null)
            textViewText.setVisibility(View.GONE);
//        else
//            textViewText.setText(newTimelineItem.getText());


        StringBuilder stringBuilder = new StringBuilder();
//        stringBuilder.append(newTimelineItem.getRegisterDate());
        stringBuilder.append(" ( ");
        stringBuilder.append("تاریخ ثبت : ");
        stringBuilder.append(newTimelineItem.getRegisterDate());
        stringBuilder.append(" ) ");
        textViewDate.setText(stringBuilder.toString());

        textViewUserName.setText(newTimelineItem.getUserNameMasked(newTimelineItem.getUserName()));
        textViewCount.setText(newTimelineItem.getViewCount() + "");



//        if(yafteItem.isInMyFavList())
//            holder.imageViewFavourite.setImageResource(android.R.drawable.btn_star_big_on);
//        else
//            holder.imageViewFavourite.setImageResource(android.R.drawable.btn_star_big_off);




        if (newTimelineItem.getUserImage().length() < 5){
            Picasso.get()
                    .load(R.drawable.png_user)
                    //.transform(transformation)
                    .into(imageViewUserAvatar);
        }else {
            Picasso.get()
                    .load(newTimelineItem.getUserImage())
                    .placeholder(R.drawable.bg_search)
                    //.centerInside()
                    //.transform(transformation)
                    .into(imageViewUserAvatar, new com.squareup.picasso.Callback() {
                        @Override
                        public void onSuccess() {

                        }

                        @Override
                        public void onError(Exception e) {
                            Picasso.get()
                                    .load(R.drawable.png_user)
                                    //.transform(transformation)
                                    .into(imageViewUserAvatar);
                        }
                    });
        }


        fillClicks(newTimelineItem);
    }

//    private void firstFillData(TimelineItem timelineItem) {
//
//        DateConverterSjd dateUtiliti = new DateConverterSjd();
//
//        if (timelineItem.getCategoryID() == StaticValue.newsCategory){
//            viewHeader.setVisibility(View.GONE);
//        }else {
//            viewHeader.setVisibility(View.VISIBLE);
//        }
//
//
//        if (timelineItem.getPicture() != null && timelineItem.getPicture().length() > 10) {
//            imageviewPicture.setVisibility(View.VISIBLE);
//            Picasso.get()
//                    .load(timelineItem.getPicture())
//                    //.load("https://tejaratnews.com/wp-content/uploads/2018/12/boors.jpg")
//                    //.placeholder(R.drawable.bg_search)
//                    //.centerInside()
//                    //.transform(transformation)
//                    .into(imageviewPicture, new com.squareup.picasso.Callback() {
//                        @Override
//                        public void onSuccess() {
//
//                        }
//
//                        @Override
//                        public void onError(Exception e) {
//
//                            Picasso.get()
//                                    .load(R.drawable.png_image)
//                                    //.transform(transformation)
//                                    .into(imageviewPicture);
//                        }
//                    } );
//
//        }else {
//            imageviewPicture.setVisibility(View.GONE);
//        }
//
//
//        textViewLocation.setText(timelineItem.getLocation());
//
//        if (timelineItem.getText() == null)
//            textViewText.setVisibility(View.GONE);
//        else
//            textViewText.setText(timelineItem.getText());
//
//
//        StringBuilder stringBuilder = new StringBuilder();
//        stringBuilder.append(timelineItem.getDate());
//        stringBuilder.append(" ( ");
//        stringBuilder.append("تاریخ ثبت : ");
//        stringBuilder.append(timelineItem.getRegisterDate());
//        stringBuilder.append(" ) ");
//        textViewDate.setText(stringBuilder.toString());
//
//        textViewUserName.setText(timelineItem.getUserNameMasked(timelineItem.getUserName()));
//        textViewCount.setText(timelineItem.getViewCount() + "");
//
//
//
////        if(yafteItem.isInMyFavList())
////            holder.imageViewFavourite.setImageResource(android.R.drawable.btn_star_big_on);
////        else
////            holder.imageViewFavourite.setImageResource(android.R.drawable.btn_star_big_off);
//
//
//
//
//        if (timelineItem.getUserImage().length() < 5){
//            Picasso.get()
//                    .load(R.drawable.png_user)
//                    //.transform(transformation)
//                    .into(imageViewUserAvatar);
//        }else {
//            Picasso.get()
//                    .load(timelineItem.getUserImage())
//                    .placeholder(R.drawable.bg_search)
//                    //.centerInside()
//                    //.transform(transformation)
//                    .into(imageViewUserAvatar, new com.squareup.picasso.Callback() {
//                        @Override
//                        public void onSuccess() {
//
//                        }
//
//                        @Override
//                        public void onError(Exception e) {
//                            Picasso.get()
//                                    .load(R.drawable.png_user)
//                                    //.transform(transformation)
//                                    .into(imageViewUserAvatar);
//                        }
//                    });
//        }
//
//
//        fillClicks(timelineItem);
//    }

    private void fillClicks(ParentItem timelineItem) {
        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                if (timelineItem instanceof TimelineItem) {
//                    share(mContext,0,timelineItem);
//                }else
                    if (timelineItem instanceof NewTimelineItem) {
                    shareNew(mContext,0,timelineItem);
                }else {

                }
            }
        };

        textViewShare.setOnClickListener(onClickListener);
        imageViewShare.setOnClickListener(onClickListener);


        View.OnClickListener onCommentsClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                if (timelineItem instanceof TimelineItem) {
//                    Bundle bundle = new Bundle();
//                    bundle.putInt("type" , TYPE_COMMENTS);
//                    bundle.putInt("blogId" , timelineItem.getBlogID());
//                    bundle.putSerializable("LIST", (Serializable)new ArrayList<CommentItem>());
//                    ((Activity)mContext).startActivityForResult(ContainerActivity.getIntent(mContext,bundle),READ_BLOG_COMMENTS);
//                }else
                    if (timelineItem instanceof NewTimelineItem) {
                    Bundle bundle = new Bundle();
                    bundle.putInt("type" , TYPE_COMMENTS);
                    bundle.putInt("blogId" , timelineItem.getBlogID());
                    bundle.putSerializable("LIST", (Serializable)new ArrayList<CommentItem>());
                    ((Activity)mContext).startActivityForResult(ContainerActivity.getIntent(mContext,bundle),READ_BLOG_COMMENTS);
                }else {

                }
            }
        };

        textViewComments.setOnClickListener(onCommentsClickListener);
        imageViewComments.setOnClickListener(onCommentsClickListener);

        View.OnClickListener onStarClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                    AsyncFavouriteBlogItem asyncFavouriteBlogItem = new AsyncFavouriteBlogItem(mContext,mProgressBar,yafteItem.getID(),Global.user.getUserID(),yafteItem.isInMyFavList());
//                    asyncFavouriteBlogItem.execute();

            }
        };

//        holder.imageViewFavourite.setOnClickListener(onStarClickListener);
//        holder.textViewFavourite.setOnClickListener(onStarClickListener);


        View.OnClickListener onclick2 = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Toast.makeText(mContext,"user id : " + timelineItem.getUserID() ,Toast.LENGTH_SHORT).show();
            }
        };

        textViewUserName.setOnClickListener(onclick2);
    }

    private void fillData(NewTimelineItem timelineItem) {

    }


    protected void shareNew(Context mContext, int listType, ParentItem timelineItem0) {
        StringBuilder stringBuilder0 = new StringBuilder();
        NewTimelineItem timelineItem = (NewTimelineItem) timelineItem0;

        stringBuilder0.append(fillTitleForShare(mContext, timelineItem.getTitle(),timelineItem.getCategoryID()));
        stringBuilder0.append("\n");
        stringBuilder0.append("\n");


        stringBuilder0.append(timelineItem.getTitle());
        stringBuilder0.append("-");
        stringBuilder0.append("توضیحات:");
        stringBuilder0.append(((NewTimelineItem) timelineItem0).getStatementFromJson());

        stringBuilder0.append("\n");
        stringBuilder0.append("\n");
        stringBuilder0.append(" ثبت شده در اپلیکیشن تیوبلس در تاریخ ");
        stringBuilder0.append(timelineItem.getRegisterDate());

        stringBuilder0.append("\n");

        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_SEND);
        intent.putExtra(Intent.EXTRA_TEXT, stringBuilder0.toString() );
        intent.setType("text/plain");
        ((Activity)mContext).startActivityForResult(intent , 60);
    }

    protected void share(Context mContext, int listType, ParentItem timelineItem0) {
//                EndlessList_Adapter.prepareToShare(mContext,blogItem.getTitlePicture(), blogItem.getStatement(), loadedImage[0]);

//        TimelineItem timelineItem = (TimelineItem) timelineItem0;
//        StringBuilder stringBuilder0 = new StringBuilder();
//        stringBuilder0.append(fillTitleForShare(mContext, timelineItem.getTitle(),timelineItem.getCategoryID()));
//        stringBuilder0.append("\n");
//        stringBuilder0.append("\n");
//
//
//        stringBuilder0.append(timelineItem.getTitle());
//        stringBuilder0.append("-");
//        stringBuilder0.append(timelineItem.getPicture());
//
//        stringBuilder0.append(" در ");
//        stringBuilder0.append(((TimelineItem) timelineItem0).getLocation());
//
//        stringBuilder0.append("\n");
//        stringBuilder0.append("\n");
//        stringBuilder0.append(" ثبت شده در اپلیکیشن تیوبلس در تاریخ ");
//        stringBuilder0.append(timelineItem.getRegisterDate());
//
//        stringBuilder0.append("\n");
//
//        Intent intent = new Intent();
//        intent.setAction(Intent.ACTION_SEND);
//        intent.putExtra(Intent.EXTRA_TEXT, stringBuilder0.toString() );
//        intent.setType("text/plain");
//        ((Activity)mContext).startActivityForResult(intent , 60);
    }



    private void newVote(NewVoteRequest newVoteRequest) {
        final BottomSheetDialog dialog = new BottomSheetDialog(getContext());

        TubelessRetrofitCallbackss ssssssss = new TubelessRetrofitCallbackss(getContext(), ServerResponseBase.class) {
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
                TubelessException.ShowSheetDialogMessage(getContext(), dialog, getContext().getString(R.string.tray_error_again), new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });

            }

            @Override
            public void t_retry(Call<Object> call) {

            }

            @Override
            public void t_onSuccess(Object response) {
                ServerResponseBase responseX = (ServerResponseBase)response;

                if (responseX.getTubelessException().getCode() > 0) {

                    //pic
//                    boolean havePic = true;
//                    if (filesList.size()>=3){
//                        havePic = true;
//                    }
//
//                    if(havePic){
//
//                        //ok
//                        Intent mIntent = new Intent(getContext(), FileUploadService.class);
//                        mIntent.putExtra("BlogId", responseX.getTubelessException().getMessage());
//
//                        if (lastCheckedPosition2 != -1){
//                            mIntent.putExtra("TitlePicture" , UriUtil.getPath(getContext(), Uri.parse(filesList.get(lastCheckedPosition2).getUri())));
//                        }
//
//                        if (lastCheckedPosition != -1){
//                            mIntent.putExtra("TextPicture" , UriUtil.getPath(getContext(), Uri.parse(filesList.get(lastCheckedPosition).getUri())));
//                        }
//
//                        int index = 0;
//                        for (File file : filesList) {
//                            if (file.getListItemType() == EndlessList_AdapterFile.ListItemType.TYPE_ITEM && !file.isHeaderPic() && !file.isContentPic() ) {
//                                index++;
//                                mIntent.putExtra("image" + index, UriUtil.getPath(getContext(), Uri.parse(file.getUri())));
//                            }
//                        }
//                        mIntent.putExtra("filesCount", index );
//                        FileUploadService.enqueueWork(getContext(), mIntent);
//
//
//                    }else {
                    TubelessException.ShowSheetDialogMessage(getContext(), dialog, getContext().getString(R.string.new_yafte_new_yafte_inserted), "ok", new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            finish();
                        }
                    });
//                    }
                }else {
                    TubelessException.ShowSheetDialogMessage(getContext(), dialog, getContext().getString(R.string.tray_again), new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            dialog.dismiss();
                        }
                    });
                }
            }
        };
        Global.apiManagerTubeless.voteBlogComment(newVoteRequest ,ssssssss);


    }

    private void invisibleComment(int id ,String userId) {
        final BottomSheetDialog dialog = new BottomSheetDialog(getContext());

        TubelessRetrofitCallbackss ssssssss = new TubelessRetrofitCallbackss(getContext(), ServerResponseBase.class) {
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
                TubelessException.ShowSheetDialogMessage(getContext(), dialog, getContext().getString(R.string.tray_error_again), new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });

            }

            @Override
            public void t_retry(Call<Object> call) {

            }

            @Override
            public void t_onSuccess(Object response) {
                ServerResponseBase responseX = (ServerResponseBase)response;

                if (responseX.getTubelessException().getCode() > 0) {

                    //pic
//                    boolean havePic = true;
//                    if (filesList.size()>=3){
//                        havePic = true;
//                    }
//
//                    if(havePic){
//
//                        //ok
//                        Intent mIntent = new Intent(getContext(), FileUploadService.class);
//                        mIntent.putExtra("BlogId", responseX.getTubelessException().getMessage());
//
//                        if (lastCheckedPosition2 != -1){
//                            mIntent.putExtra("TitlePicture" , UriUtil.getPath(getContext(), Uri.parse(filesList.get(lastCheckedPosition2).getUri())));
//                        }
//
//                        if (lastCheckedPosition != -1){
//                            mIntent.putExtra("TextPicture" , UriUtil.getPath(getContext(), Uri.parse(filesList.get(lastCheckedPosition).getUri())));
//                        }
//
//                        int index = 0;
//                        for (File file : filesList) {
//                            if (file.getListItemType() == EndlessList_AdapterFile.ListItemType.TYPE_ITEM && !file.isHeaderPic() && !file.isContentPic() ) {
//                                index++;
//                                mIntent.putExtra("image" + index, UriUtil.getPath(getContext(), Uri.parse(file.getUri())));
//                            }
//                        }
//                        mIntent.putExtra("filesCount", index );
//                        FileUploadService.enqueueWork(getContext(), mIntent);
//
//
//                    }else {
                    TubelessException.ShowSheetDialogMessage(getContext(), dialog, getContext().getString(R.string.new_yafte_new_yafte_inserted), "ok", new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            finish();
                        }
                    });
//                    }
                }else {
                    TubelessException.ShowSheetDialogMessage(getContext(), dialog, getContext().getString(R.string.tray_again), new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            dialog.dismiss();
                        }
                    });
                }
            }
        };
        Global.apiManagerTubeless.invisibleBlogComment(id,userId ,ssssssss);
    }

    private void deleteComment(int id ,String userId) {
        final BottomSheetDialog dialog = new BottomSheetDialog(getContext());

        TubelessRetrofitCallbackss ssssssss = new TubelessRetrofitCallbackss(getContext(), ServerResponseBase.class) {
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
                TubelessException.ShowSheetDialogMessage(getContext(), dialog, getContext().getString(R.string.tray_error_again), new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });

            }

            @Override
            public void t_retry(Call<Object> call) {

            }

            @Override
            public void t_onSuccess(Object response) {
                ServerResponseBase responseX = (ServerResponseBase)response;

                if (responseX.getTubelessException().getCode() > 0) {

                    TubelessException.ShowSheetDialogMessage(getContext(), dialog, getContext().getString(R.string.new_yafte_new_yafte_inserted), "ok", new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            finish();
                        }
                    });
//                    }
                }else {
                    TubelessException.ShowSheetDialogMessage(getContext(), dialog, getContext().getString(R.string.tray_again), new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            dialog.dismiss();
                        }
                    });
                }
            }
        };
        Global.apiManagerTubeless.deleteBlogComment(id,userId ,ssssssss);
    }

}
