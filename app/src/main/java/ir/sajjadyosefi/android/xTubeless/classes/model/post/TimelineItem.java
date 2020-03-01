package ir.sajjadyosefi.android.xTubeless.classes.model.post;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;
import com.zl.reik.dilatingdotsprogressbar.DilatingDotsProgressBar;

import java.util.Date;
import java.util.List;

import ir.sajjadyosefi.android.xTubeless.R;
import ir.sajjadyosefi.android.xTubeless.Global;
import ir.sajjadyosefi.android.xTubeless.activity.common.ReadBlogActivity;
import ir.sajjadyosefi.android.xTubeless.classes.JsonDateDeserializer;
import ir.sajjadyosefi.android.xTubeless.classes.StaticValue;
import ir.sajjadyosefi.android.xTubeless.classes.model.BlogItem;
import ir.sajjadyosefi.android.xTubeless.classes.model.viewHolder.BlogItemViewHolder;
import ir.sajjadyosefi.android.xTubeless.classes.model.viewHolder.PostViewHolder;
import ir.sajjadyosefi.android.xTubeless.classes.model.viewHolder.TimelineItemViewHolder;
import ir.sajjadyosefi.android.xTubeless.utility.DateConverterSjd;

/**
 * Created by sajjad on 1/20/2018.
 */

public class TimelineItem extends ParentItem{

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

    public void fill(Context mContext, int listType , PostViewHolder holder0, IItems item) {
        super.fill(mContext,listType,holder0,item);

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


        holder.imageviewPicture.setVisibility(View.GONE);



        onclicks(mContext,listType , holder, timelineItem);
        loadImage(holder, timelineItem);

    }

    private void onclicks(Context mContext, int listType, TimelineItemViewHolder holder, TimelineItem timelineItem) {



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


        View.OnClickListener onclick2 = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext,"user id : " + timelineItem.getUserID() ,Toast.LENGTH_SHORT).show();
            }
        };

        View.OnClickListener onContentClick = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                public static int TYPE_YAFTE  = 3;
//                public static int TYPE_YADAK  = 2;
//                public static int TYPE_IMAGE  = 1;
                if (timelineItem instanceof TimelineItem) {
//                    if (listType == )
//                    Toast.makeText(mContext,"user id : " + timelineItem.getUserID() ,Toast.LENGTH_SHORT).show();


                    Intent intent = new Intent(mContext, ReadBlogActivity.class);
                    Gson gson = new Gson();
                    String json = gson.toJson(timelineItem);
                    intent.putExtra("Object", json);
                    mContext.startActivity(intent);
                    ((Activity) mContext).overridePendingTransition(R.anim.fadeout, R.anim.fadein);

                }
            }
        };


        holder.imageviewPicture.setOnClickListener(onContentClick);
        holder.textViewDate.setOnClickListener(onContentClick);
        holder.textViewLocation.setOnClickListener(onContentClick);
        holder.textViewTitle.setOnClickListener(onContentClick);
        holder.linearLayoutCenter.setOnClickListener(onContentClick);

        holder.imageViewUserAvatar.setOnClickListener(onclick2);
        holder.textViewUserName.setOnClickListener(onclick2);

    }

    @Override
    protected void share(Context mContext, int listType, TimelineItem timelineItem) {
//                EndlessList_Adapter.prepareToShare(mContext,blogItem.getTitlePicture(), blogItem.getStatement(), loadedImage[0]);

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

    public void prepareBlogItem(final Context mContext, final DilatingDotsProgressBar mProgressBar,
                                final BlogItemViewHolder holder, final List<Object> mTimelineItemList, final int position) {

        final boolean[] loadedImage = {false};
        Gson gson = new GsonBuilder().registerTypeAdapter(Date.class, new JsonDateDeserializer()).create();
        String json0000 = gson.toJson((BlogItem)(mTimelineItemList.get(position)));
        final BlogItem blogItem = gson.fromJson(json0000,BlogItem.class);


        DateConverterSjd dateUtiliti = new DateConverterSjd();
        holder.textViewTitle.setText(blogItem.getTitle());
//        holder.textViewTitle2.setText(blogItem.getTitle());
        holder.textViewStatment.setText(blogItem.getStatement());
        holder.textViewStatment2.setVisibility(View.GONE);

        if (blogItem.getUser() != null)
            holder.textViewUserName.setText(blogItem.getUser().getUserName());

        holder.textViewCount.setText(blogItem.getViewCount() + "");


        holder.linearLayoutFaveorative.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Global.user == null){
                    Global.ShowMessageDialog(mContext,"",mContext.getString(R.string.NotLoggedIn3));
                }else {
//                    AsyncFavouriteBlogItem asyncFavouriteBlogItem = new AsyncFavouriteBlogItem(mContext,mProgressBar,blogItem.getID(),Global.user.getUserId(),blogItem.isInMyFavList());
//                    asyncFavouriteBlogItem.execute();
                }

            }
        });
        if(blogItem.isInMyFavList())
            holder.imageViewFavourite.setImageResource(android.R.drawable.btn_star_big_on);
        else
            holder.imageViewFavourite.setImageResource(android.R.drawable.btn_star_big_off);

//        if (blogItem.getUser() != null)
//            Picasso.with(mContext)
//                    .load(blogItem.getUser().getUserImage())
//                    .placeholder(R.drawable.progress_animation)
//                    //.centerInside()
//                    .transform(transformation)
//                    .into(holder.imageViewUserAvatar, new Callback() {
//                        @Override
//                        public void onSuccess() {
//
//                        }
//
//                        @Override
//                        public void onError() {
//                            // TODO Auto-generated method stub
////                            Picasso.with(mContext)
////                                    .load(R.drawable.sajjad)
////                                    .transform(transformation)
////                                    .into(holder.imageViewUserAvatar);
//                        }
//                    });

        View.OnClickListener onclick = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(mContext, ReadBlogActivity.class);
//                Gson gson = new Gson();
//                String json = gson.toJson(timelineItem);
//                intent.putExtra("Object", json);
//                mContext.startActivity(intent);
//                ((Activity) mContext).overridePendingTransition(R.anim.fadeout, R.anim.fadein);
            }
        };
        holder.textViewTitle.setOnClickListener(onclick);
        holder.textViewStatment.setOnClickListener(onclick);
        holder.textViewStatment2.setOnClickListener(onclick);
    }
}
