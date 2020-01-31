package ir.sajjadyosefi.android.xTubeless.classes.modelY.main;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.util.Date;

import ir.sajjadyosefi.android.tubeless.Global;
import ir.sajjadyosefi.android.tubeless.R;
import ir.sajjadyosefi.android.tubeless.classes.JsonDateDeserializer;
import ir.sajjadyosefi.android.tubeless.classes.StaticValue;
import ir.sajjadyosefi.android.xTubeless.classes.modelY.post.IItems;
import ir.sajjadyosefi.android.xTubeless.classes.modelY.viewHolder.PostViewHolder;
import ir.sajjadyosefi.android.xTubeless.classes.modelY.viewHolder.TimelineItemViewHolder;
import ir.sajjadyosefi.android.xTubeless.utility.DateConverterSjd;
import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by sajjad on 1/20/2018.
 */

public class TimelineItem implements IItems {

    private int blogID;
    private String title;
    private String date;
    private String location;
    private String picture;
    private String text;
    private String registerDate;
    private int viewCount;
    private int shareCount;
    private int userID;
    private String userName;
    private String userImage;
    private int categoryID;


    public int getBlogID() {
        return blogID;
    }

    public void setBlogID(int blogID) {
        this.blogID = blogID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getPicture() {
        return picture;
    }

    public String getPictureTumble() {
        return picture + "2";
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(String registerDate) {
        this.registerDate = registerDate;
    }

    public int getViewCount() {
        return viewCount;
    }

    public void setViewCount(int viewCount) {
        this.viewCount = viewCount;
    }

    public int getShareCount() {
        return shareCount;
    }

    public void setShareCount(int shareCount) {
        this.shareCount = shareCount;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserImage() {
        return userImage;
    }

    public void setUserImage(String userImage) {
        this.userImage = userImage;
    }

    public int getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(int categoryID) {
        this.categoryID = categoryID;
    }

    public void fill(Context mContext, PostViewHolder holder0, IItems item) {
        TimelineItemViewHolder holder = (TimelineItemViewHolder) holder0;
        final TimelineItem timelineItem = (TimelineItem)item;

        final boolean[] loadedImage = {false};
        DateConverterSjd dateUtiliti = new DateConverterSjd();

        StringBuilder date = new StringBuilder();
        date.append(timelineItem.getDate());
        date.append(" ( ");
        date.append("تاریخ ثبت : ");
        date.append(timelineItem.getRegisterDate());
        date.append(" ) ");
        holder.textViewDate.setText(date.toString());

        StringBuilder partType = new StringBuilder();
        partType.append(timelineItem.getTitle());
        if (timelineItem.getCategoryID() == StaticValue.cat1) {
            partType.append(" (");
            partType.append(mContext.getString(R.string.type1));
            partType.append(")");
        }
        if (timelineItem.getCategoryID() == StaticValue.cat2) {
            partType.append(" (");
            partType.append(mContext.getString(R.string.type2));
            partType.append(")");
        }
        if (timelineItem.getCategoryID() == StaticValue.cat3) {
            partType.append(" (");
            partType.append(mContext.getString(R.string.type3));
            partType.append(")");
        }
        holder.textViewTitle.setText(partType.toString());
        holder.textViewLocation.setText(timelineItem.getLocation());
        holder.textViewUserName.setText(timelineItem.getUserName());
        holder.textViewCount.setText(timelineItem.getViewCount() + "");


        if (Global.user == null) {
            if (holder.linearLayoutAdmin != null)
                holder.linearLayoutAdmin.setVisibility(View.GONE);
        }else {
            if (Global.user.getUserId() == 140241 || Global.user.getUserName().equals("yosefi1988@gmail.com")|| Global.user.getUserName().equals("09123978522")) {
                holder.linearLayoutAdmin.setVisibility(View.VISIBLE);
            } else {
                holder.linearLayoutAdmin.setVisibility(View.GONE);
            }
        }


        holder.imageviewPicture.setVisibility(View.GONE);



        onclicks(mContext, holder, timelineItem);
        loadImage(holder, timelineItem);

    }

    private void onclicks(Context mContext, TimelineItemViewHolder holder, TimelineItem timelineItem) {
        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                EndlessList_Adapter.prepareToShare(mContext, timelineItem.getTitle(),
//                        timelineItem.getTitle() + "-" +
//                                timelineItem.getDate() + "-" +
//                                timelineItem.getPicture() + " " +
//                                timelineItem.getLocation() + " "
//                        , false);
////                Toast.makeText(mContext,"Share" ,Toast.LENGTH_SHORT).show();

            }
        };


        View.OnClickListener onDeleteClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(mContext)
                        .setTitle("Title")
                        .setMessage("Do you really want to whatever?")
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {

                            public void onClick(DialogInterface dialog, int whichButton) {

//                                String userId = "";
//
//                                if (Global.user.getUserId() == 0){
//                                    userId = Global.user.getEmail();
//                                }else {
//                                    userId = Global.user.getUserId() + "";
//                                }
//
//                                Global.apiManagerTubeless.deleteTimelineItem(
//                                        timelineItem.getBlogID(),
//                                        userId,
//                                        new TubelessRetrofitCallback<Object>(mContext,null, false, null, new retrofit2.Callback<Object>() {
//                                            @Override
//                                            public void onResponse(Call<Object> call, Response<Object> response) {
//                                                Gson gson = new Gson();
//                                                JsonElement jsonElement = gson.toJsonTree(response.body());
//                                                TimelineItemResponse responseX = gson.fromJson(jsonElement.getAsString(), TimelineItemResponse.class);
//
//
//
//                                            }
//
//                                            @Override
//                                            public void onFailure(Call<Object> call, Throwable t) {
//
//                                            }
//                                        }));
//

                            }})
                        .setNegativeButton(android.R.string.no, null).show();
            }
        };


        View.OnClickListener onInvisibleClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(mContext)
                        .setTitle("Title")
                        .setMessage("Do you really want to whatever?")
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {

                            public void onClick(DialogInterface dialog, int whichButton) {
//
//                                String userId = "";
//
//                                if (Global.user.getUserId() == 0){
//                                    userId = Global.user.getEmail();
//                                }else {
//                                    userId = Global.user.getUserId() + "";
//                                }
//
//                                Global.apiManagerTubeless.invisibleTimelineItem(
//                                        timelineItem.getBlogID(),
//                                        userId,
//                                        new TubelessRetrofitCallback<Object>(mContext,null, false, null, new retrofit2.Callback<Object>() {
//                                            @Override
//                                            public void onResponse(Call<Object> call, Response<Object> response) {
//                                                Gson gson = new Gson();
//                                                JsonElement jsonElement = gson.toJsonTree(response.body());
//                                                TimelineItemResponse responseX = gson.fromJson(jsonElement.getAsString(), TimelineItemResponse.class);
//
//
//
//                                            }
//
//                                            @Override
//                                            public void onFailure(Call<Object> call, Throwable t) {
//
//                                            }
//                                        }));


                            }})
                        .setNegativeButton(android.R.string.no, null).show();
            }
        };


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


        View.OnClickListener onclick = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(mContext, ReadBlogActivity.class);
//                Gson gson = new Gson();
//                String json = gson.toJson(mTimelineItemList.get(position));
//                intent.putExtra("Object", json);
//                mContext.startActivity(intent);
//                ((Activity) mContext).overridePendingTransition(R.anim.fadeout, R.anim.fadein);
            }
        };
        View.OnClickListener onclick2 = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext,"user id : " + timelineItem.getUserID() ,Toast.LENGTH_SHORT).show();
            }
        };

        holder.textViewDelete.setOnClickListener(onInvisibleClickListener);
        holder.textViewDelete.setOnClickListener(onDeleteClickListener);
        holder.imageViewShare.setOnClickListener(onClickListener);
        holder.textViewShare.setOnClickListener(onClickListener);
        holder.imageViewDelete.setOnClickListener(onDeleteClickListener);
        holder.imageViewDelete.setOnClickListener(onInvisibleClickListener);
        holder.imageviewPicture.setOnClickListener(onclick);
        holder.textViewDate.setOnClickListener(onclick);
        holder.textViewLocation.setOnClickListener(onclick);
        holder.textViewTitle.setOnClickListener(onclick);
        holder.imageViewUserAvatar.setOnClickListener(onclick2);
        holder.textViewUserName.setOnClickListener(onclick2);
    }

    private void loadImage(TimelineItemViewHolder holder, TimelineItem timelineItem) {
        if (timelineItem.getUserImage().length() < 5){
            if (holder.imageViewUserAvatar != null)
                Picasso.get()
                    .load(R.drawable.png_user)
//                    .transform(transformation)
                    .into(holder.imageViewUserAvatar);
        }else {
            Picasso.get()
                    .load(timelineItem.getUserImage())
                    .placeholder(R.drawable.bg_search)
//                    //.centerInside()
//                    .transform(transformation)
                    .into(holder.imageViewUserAvatar, new Callback() {
                        @Override
                        public void onSuccess() {

                        }

                        @Override
                        public void onError(Exception e) {
                            Picasso.get()
                                    .load(R.drawable.png_user)
//                                    .transform(transformation)
                                    .into(holder.imageViewUserAvatar);
                        }
                    });
        }



        if (timelineItem.getPicture()!= null && timelineItem.getPicture().length() > 10) {
            holder.imageviewPicture.setVisibility(View.VISIBLE);
            Picasso.get()
                .load(timelineItem.getPictureTumble())
                .placeholder(R.drawable.bg_search)
                //.centerInside()
//                .transform(transformation)
                .into(holder.imageviewPicture, new Callback() {
                    @Override
                    public void onSuccess() {

                    }

                    @Override
                    public void onError(Exception e) {
                        Picasso.get()
                                .load(R.drawable.png_image)
//                                .transform(transformation)
                                .into(holder.imageviewPicture);
                    }
                });

        }

    }

}
