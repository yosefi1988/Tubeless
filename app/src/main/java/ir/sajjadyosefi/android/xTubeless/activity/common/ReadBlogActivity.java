package ir.sajjadyosefi.android.xTubeless.activity.common;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.readystatesoftware.systembartint.SystemBarTintManager;
import com.squareup.picasso.Picasso;

import ir.sajjadyosefi.android.xTubeless.R;
import ir.sajjadyosefi.android.xTubeless.Global;
import ir.sajjadyosefi.android.xTubeless.activity.TubelessActivity;
import ir.sajjadyosefi.android.xTubeless.activity.TubelessTransparentStatusBarActivity;
import ir.sajjadyosefi.android.xTubeless.classes.SAccounts;
import ir.sajjadyosefi.android.xTubeless.classes.StaticValue;
import ir.sajjadyosefi.android.xTubeless.classes.model.network.responses.LoginResponse;
import ir.sajjadyosefi.android.xTubeless.classes.model.response.TimelineItemResponse;
import ir.sajjadyosefi.android.xTubeless.classes.model.user.User;
import ir.sajjadyosefi.android.xTubeless.classes.model.TimelineItem;

import ir.sajjadyosefi.android.xTubeless.networkLayout.retrofit.TubelessRetrofitCallbackss;
import ir.sajjadyosefi.android.xTubeless.utility.DateConverterSjd;
import it.sephiroth.android.library.bottomnavigation.BottomNavigation;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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



        Gson gson = new Gson();
        String objectString = getIntent().getStringExtra("Object");
        TimelineItem blogItem = gson.fromJson(objectString, TimelineItem.class);

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

    private void firstFillData(TimelineItem timelineItem) {

        DateConverterSjd dateUtiliti = new DateConverterSjd();


        if (timelineItem.getPicture() != null && timelineItem.getPicture().length() > 10) {
            imageviewPicture.setVisibility(View.VISIBLE);
            Picasso.get()
                    .load(timelineItem.getPicture())
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


        StringBuilder stringBuilder0 = new StringBuilder();
        stringBuilder0.append(timelineItem.getTitle());
        if (timelineItem.getCategoryID() == StaticValue.cat1) {
            stringBuilder0.append(" (");
            stringBuilder0.append("گمشده");
            stringBuilder0.append(")");

        }
        if (timelineItem.getCategoryID() == StaticValue.cat2) {
            stringBuilder0.append(" (");
            stringBuilder0.append("پیداشده");
            stringBuilder0.append(")");
        }
        if (timelineItem.getCategoryID() == StaticValue.cat3) {
            stringBuilder0.append(" (");
            stringBuilder0.append("سرقتی");
            stringBuilder0.append(")");
        }
        textViewTitle.setText(stringBuilder0.toString());


        textViewLocation.setText(timelineItem.getLocation());

        if (timelineItem.getText() == null)
            textViewText.setVisibility(View.GONE);
        else
            textViewText.setText(timelineItem.getText());


        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(timelineItem.getDate());
        stringBuilder.append(" ( ");
        stringBuilder.append("تاریخ ثبت : ");
        stringBuilder.append(timelineItem.getRegisterDate());
        stringBuilder.append(" ) ");
        textViewDate.setText(stringBuilder.toString());

        textViewUserName.setText(timelineItem.getUserName());
        textViewCount.setText(timelineItem.getViewCount() + "");



//        if(yafteItem.isInMyFavList())
//            holder.imageViewFavourite.setImageResource(android.R.drawable.btn_star_big_on);
//        else
//            holder.imageViewFavourite.setImageResource(android.R.drawable.btn_star_big_off);




        if (timelineItem.getUserImage().length() < 5){
            Picasso.get()
                    .load(R.drawable.png_user)
                    //.transform(transformation)
                    .into(imageViewUserAvatar);
        }else {
            Picasso.get()
                    .load(timelineItem.getUserImage())
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


        fillClicks(timelineItem);
    }

    private void fillClicks(TimelineItem timelineItem) {
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
                Toast.makeText(mContext,"user id : " + timelineItem.getUserID() ,Toast.LENGTH_SHORT).show();
            }
        };

        textViewUserName.setOnClickListener(onclick2);
    }

    private void fillData(TimelineItem timelineItem) {

    }
}
