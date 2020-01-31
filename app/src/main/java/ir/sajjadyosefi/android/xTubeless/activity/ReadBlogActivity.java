package ir.sajjadyosefi.android.xTubeless.activity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.squareup.picasso.Picasso;

import ir.sajjadyosefi.android.tubeless.Global;
import ir.sajjadyosefi.android.tubeless.R;
import ir.sajjadyosefi.android.tubeless.adapter.EndlessList_Adapter;
import ir.sajjadyosefi.android.tubeless.classes.DateConverterSjd;
import ir.sajjadyosefi.android.tubeless.classes.StaticValue;

import ir.sajjadyosefi.android.xTubeless.classes.modelY.main.TimelineItem;
import ir.sajjadyosefi.android.xTubeless.networkLayout.retrofit.TubelessRetrofitCallback;
import ir.sajjadyosefi.android.xTubeless.classes.modelY.NetworkLayout.Responses.blog.TimelineItemResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by sajjad on 2/11/2018.
 */

public class ReadBlogActivity extends TubelessActivity {

    Context mContext = null;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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


        Global.apiManagerTubeless.getTimelineItem(blogItem.getBlogID(),new TubelessRetrofitCallback<Object>(this,null, false, null, new Callback<Object>() {
            @Override
            public void onResponse(Call<Object> call, Response<Object> response) {
                Gson gson = new Gson();
                JsonElement jsonElement = gson.toJsonTree(response.body());
                TimelineItemResponse responseX = gson.fromJson(jsonElement.getAsString(), TimelineItemResponse.class);


                fillData(responseX.getTimelineItem());
                int a = 5 ;
                a++;

            }

            @Override
            public void onFailure(Call<Object> call, Throwable t) {

            }
        }));

//        getSupportActionBar().setTitle(getString(R.string.pleaseWait));

    }


    TextView textViewUserName,textViewTitle,textViewLocation,textViewDate,textViewCount,textViewShare,textViewText;
    ImageView imageViewUserAvatar, imageViewShare,imageviewPicture;

    private void fillData(TimelineItem timelineItem) {

        DateConverterSjd dateUtiliti = new DateConverterSjd();


        if (timelineItem.getPicture()!= null && timelineItem.getPicture().length() > 10) {
            imageviewPicture.setVisibility(View.VISIBLE);
            Picasso.get()
                    .load(timelineItem.getPictureTumble())
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

        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EndlessList_Adapter.prepareToShare(mContext, timelineItem.getTitle(), timelineItem.getText(), false);
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



        View.OnClickListener onclick2 = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext,"user id : " + timelineItem.getUserID() ,Toast.LENGTH_SHORT).show();
            }
        };

        textViewUserName.setOnClickListener(onclick2);

    }
}
