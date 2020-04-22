package ir.sajjadyosefi.android.xTubeless.activity.common;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;

import com.google.gson.Gson;
import com.readystatesoftware.systembartint.SystemBarTintManager;
import com.squareup.picasso.Picasso;

import ir.sajjadyosefi.android.xTubeless.R;
import ir.sajjadyosefi.android.xTubeless.Global;
import ir.sajjadyosefi.android.xTubeless.activity.TubelessTransparentStatusBarActivity;
import ir.sajjadyosefi.android.xTubeless.classes.StaticValue;
import ir.sajjadyosefi.android.xTubeless.classes.model.post.NewTimelineItem;
import ir.sajjadyosefi.android.xTubeless.classes.model.response.TimelineItemResponse;
import ir.sajjadyosefi.android.xTubeless.classes.model.post.TimelineItem;

import ir.sajjadyosefi.android.xTubeless.networkLayout.retrofit.TubelessRetrofitCallbackss;
import ir.sajjadyosefi.android.xTubeless.utility.DateConverterSjd;
import it.sephiroth.android.library.bottomnavigation.BottomNavigation;
import retrofit2.Call;

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
        textViewText = findViewById(R.id.textViewText);
        imageViewUserAvatar = findViewById(R.id.imageViewUserAvatar);
        imageViewShare = findViewById(R.id.imageViewShare);
        imageviewPicture = findViewById(R.id.imageviewPicture);
        viewHeader = findViewById(R.id.header);



        Gson gson = new Gson();
        String objectString = getIntent().getStringExtra("Object");
        NewTimelineItem blogItem = gson.fromJson(objectString, NewTimelineItem.class);
        firstFillData(blogItem);

        TubelessRetrofitCallbackss ssssssss = new TubelessRetrofitCallbackss(getContext(), TimelineItemResponse.class) {
            @Override
            public void t_beforeSendRequest() {
                progressDialog.show();
            }

            @Override
            public void t_afterGetResponse() {
                progressDialog.hide();
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
                fillData(((TimelineItemResponse)response).getTimelineItem());
            }
        };
        Global.apiManagerTubeless.getTimelineItem(blogItem.getBlogID(), ssssssss);
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


    TextView textViewUserName,textViewTitle,textViewLocation,textViewDate,textViewCount,textViewShare,textViewText;
    ImageView imageViewUserAvatar, imageViewShare,imageviewPicture;
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


        StringBuilder stringBuilderTitle = new StringBuilder();
        stringBuilderTitle.append(newTimelineItem.getTitle());
        if (newTimelineItem.getCategoryID() == StaticValue.cat1) {
            stringBuilderTitle.append(" (");
            stringBuilderTitle.append(StaticValue.cat1text);
            stringBuilderTitle.append(")");
        }else if (newTimelineItem.getCategoryID() == StaticValue.cat2) {
            stringBuilderTitle.append(" (");
            stringBuilderTitle.append(StaticValue.cat2text);
            stringBuilderTitle.append(")");
        }else if (newTimelineItem.getCategoryID() == StaticValue.cat3) {
            stringBuilderTitle.append(" (");
            stringBuilderTitle.append(StaticValue.cat3text);
            stringBuilderTitle.append(")");
        }else {
            stringBuilderTitle.append(" - ");
            stringBuilderTitle.append(StaticValue.cat3text);
        }
        textViewTitle.setText(stringBuilderTitle.toString());


        textViewLocation.setText(newTimelineItem.getTextFromJson());

//        if (newTimelineItem.getText() == null)
//            textViewText.setVisibility(View.GONE);
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

    private void fillClicks(NewTimelineItem timelineItem) {
        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                EndlessList_Adapter.prepareToShare(mContext, timelineItem.getTitle(), timelineItem.getText(), false);
//                Toast.makeText(mContext,"Share" ,Toast.LENGTH_SHORT).show();

            }
        };

        textViewShare.setOnClickListener(onClickListener);
        imageViewShare.setOnClickListener(onClickListener);

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

    private void fillData(TimelineItem timelineItem) {

    }
}
