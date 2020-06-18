package ir.sajjadyosefi.android.xTubeless.classes.model.post;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.gson.Gson;

import ir.sajjadyosefi.android.xTubeless.Adapter.XAdapter;
import ir.sajjadyosefi.android.xTubeless.R;
import ir.sajjadyosefi.android.xTubeless.activity.account.blog.ReadBlogActivity;
import ir.sajjadyosefi.android.xTubeless.classes.model.post.innerClass.Statement;
import ir.sajjadyosefi.android.xTubeless.classes.model.post.innerClass.TextContent;
import ir.sajjadyosefi.android.xTubeless.classes.model.viewHolder.PostViewHolder;
import ir.sajjadyosefi.android.xTubeless.classes.model.viewHolder.TimelineItemViewHolder;
import ir.sajjadyosefi.android.xTubeless.utility.DateConverterSjd;
import ir.sajjadyosefi.android.xTubeless.utility.picasso.LoadImages;

import static ir.sajjadyosefi.android.xTubeless.activity.account.blog.ReadBlogActivity.fillTitle;
import static ir.sajjadyosefi.android.xTubeless.activity.account.blog.ReadBlogActivity.fillTitleForShare;

/**
 * Created by sajjad on 1/20/2018.
 */

public class NewTimelineItem extends ParentItem{

    private String title;
    private String titlePicture;
    private String statement;
    private String textPicture;
    private String text;
    private String registerDate;
    private int viewCount;
    private int shareCount;
    private int categoryID;
    private String userName;
    private String userImage;


    public String getTitlePicture() {
        return titlePicture;
    }

    public void setTitlePicture(String titlePicture) {
        this.titlePicture = titlePicture;
    }

    public String getStatement() {
        return statement;
    }

    public String getStatementFromJson() {
        try {
            Gson gson = new Gson();

            Statement statementObj = gson.fromJson(statement, Statement.class);
            StringBuilder stringBuilder = new StringBuilder();

            if (statementObj.getTitle() != null) {
                stringBuilder.append(statementObj.getTitle());
                stringBuilder.append("-");
            }

            stringBuilder.append(" مدل: ");
            stringBuilder.append(statementObj.getModel());
            stringBuilder.append("\n");
            stringBuilder.append(statementObj.getDescription());

            return stringBuilder.toString();
        }catch (Exception ex){
            return statement;
        }
    }

    public String getDateFromStatement() {
        try {
            Gson gson = new Gson();

            Statement statementObj = gson.fromJson(statement, Statement.class);
            StringBuilder stringBuilder = new StringBuilder();

            if (statementObj.getTitle() != null) {
                stringBuilder.append(statementObj.getTitle());
                stringBuilder.append("-");
            }

            stringBuilder.append(" مدل: ");
            stringBuilder.append(statementObj.getModel());
            stringBuilder.append("\n");
            stringBuilder.append(statementObj.getDescription());

            return stringBuilder.toString();
        }catch (Exception ex){
            return statement;
        }
    }

    public void setStatement(String statement) {
        this.statement = statement;
    }

    public String getTextPicture() {
        return textPicture;
    }

    public void setTextPicture(String textPicture) {
        this.textPicture = textPicture;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }


    public String getTextFromJson() {
        try {
            Gson gson = new Gson();

            TextContent statementObj = gson.fromJson(text, TextContent.class);
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(statementObj.getTitle());
            stringBuilder.append("-");
            stringBuilder.append(" مدل: ");
            stringBuilder.append(statementObj.getModel());
            stringBuilder.append("\n");
            stringBuilder.append(statementObj.getDescription());
            stringBuilder.append("\n");
            stringBuilder.append(statementObj.getText());

            if (statementObj.getMobile().length() > 5) {
                stringBuilder.append("\n");
                stringBuilder.append(" موبایل: ");
                stringBuilder.append(statementObj.getMobile());
            }

            return stringBuilder.toString();
        }catch (Exception ex){
            return text;
        }
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


    public String getUserName() {
        return userName;
    }

    public String getUserNameMasked() {
        return getUserNameMasked(userName);
    }

    public static String getUserNameMasked(String userName) {
        if (userName.equals("ثبت نام نکرده")){
            return userName;
        }else if (userName.equals("اپراتور سیستم")){
            return userName;
        }else if (userName.equals("کاربر ناشناس")){
            return userName;
        }else {
            if (userName.length() > 10) {
                try {

                    StringBuilder stringBuilder = new StringBuilder();
                    stringBuilder.append(userName.substring(0, 4));
                    stringBuilder.append("***");
                    stringBuilder.append(userName.substring(8, userName.length()));
                    return stringBuilder.toString();
                }catch (Exception ex){

                    StringBuilder stringBuilder = new StringBuilder();
                    stringBuilder.append(userName.substring(0, 4));
                    stringBuilder.append("***");
                    return stringBuilder.toString();
                }
            }else {
                return userName;
            }
        }
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

    public void fill(Context mContext,
                     XAdapter xAdapter,
                     int listType,
                     PostViewHolder holder0,
                     IItems item,
                     int position) {

        super.fill(mContext, xAdapter, listType,holder0,item, position);

        TimelineItemViewHolder holder = (TimelineItemViewHolder) holder0;
        final NewTimelineItem timelineItem = (NewTimelineItem)item;

        final boolean[] loadedImage = {false};
        DateConverterSjd dateUtiliti = new DateConverterSjd();

        StringBuilder date = new StringBuilder();
//        date.append("00/00/0000");
        date.append(" ( ");
        date.append("تاریخ ثبت : ");
        date.append(timelineItem.getRegisterDate());
        date.append(" ) ");
        holder.textViewDate.setText(date.toString());

        fillTitle(mContext,timelineItem.getTitle(),timelineItem.getCategoryID(),holder.textViewTitle);

        holder.textViewLocation.setText(timelineItem.getStatementFromJson());
        holder.textViewUserName.setText(timelineItem.getUserNameMasked());
        holder.textViewCount.setText(timelineItem.getViewCount() + "");

        holder.imageviewPicture.setVisibility(View.GONE);

        onclicks(mContext,listType , holder, timelineItem);
        loadImage(holder, timelineItem);
    }

    private void onclicks(Context mContext , int listType, TimelineItemViewHolder holder, NewTimelineItem timelineItem) {


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
//                Toast.makeText(mContext,"user id : " + timelineItem.getUserID() ,Toast.LENGTH_SHORT).show();
            }
        };

        View.OnClickListener onContentClick = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                public static int TYPE_YAFTE  = 3;
//                public static int TYPE_YADAK  = 2;
//                public static int TYPE_IMAGE  = 1;
                if (timelineItem instanceof NewTimelineItem) {
//                    if (listType == )
//                    Toast.makeText(mContext,"user id : " + timelineItem.getUserID() ,Toast.LENGTH_SHORT).show();


                    Intent intent = new Intent(mContext, ReadBlogActivity.class);
                    Gson gson = new Gson();
                    String json = gson.toJson(timelineItem);

                    // Old Transfer
//                    intent.putExtra("Object", json);

                    //New Transfer
                    Bundle bundle = new Bundle();
                    bundle.putString("Object", json);
                    bundle.putString("Type", "NewTimelineItem");
                    intent.putExtras(bundle);

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
//        holder.textViewUserName.setOnClickListener(onclick2);
        holder.textViewUserName.setOnClickListener(onContentClick);

    }

    @Override
    protected void share(Context mContext, int listType, ParentItem timelineItem0) {
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



    private void loadImage(TimelineItemViewHolder holder, NewTimelineItem timelineItem) {
        LoadImages.loadAvatarimage(timelineItem.getUserImage(),holder.imageViewUserAvatar);
        LoadImages.loadProfileimage(timelineItem.getTitlePicture(),holder.imageviewPicture);
    }

}
