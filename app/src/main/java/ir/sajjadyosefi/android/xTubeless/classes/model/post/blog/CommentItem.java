package ir.sajjadyosefi.android.xTubeless.classes.model.post.blog;
import android.content.Context;
import android.view.View;

import ir.sajjadyosefi.android.xTubeless.Adapter.XAdapter;
import ir.sajjadyosefi.android.xTubeless.classes.model.post.IItems;
import ir.sajjadyosefi.android.xTubeless.classes.model.post.ParentItem;
import ir.sajjadyosefi.android.xTubeless.classes.model.viewHolder.CommentItemViewHolder;
import ir.sajjadyosefi.android.xTubeless.classes.model.viewHolder.PostViewHolder;
import ir.sajjadyosefi.android.xTubeless.utility.DateConverterSjd;
import ir.sajjadyosefi.android.xTubeless.utility.picasso.LoadImages;


/**
 * Created by sajjad on 7/30/2017.
 */

public class CommentItem extends ParentItem{

    private int commentId;
    private String userName;
    private String userImage;
    private String date;
    private String text;
    private boolean isDeleted;
    private int disableByAdmin;
    private int replyId;

    public int getCommentId() {
        return commentId;
    }

    public void setCommentId(int commentId) {
        this.commentId = commentId;
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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }

    public int getDisableByAdmin() {
        return disableByAdmin;
    }

    public void setDisableByAdmin(int disableByAdmin) {
        this.disableByAdmin = disableByAdmin;
    }

    public int getReplyId() {
        return replyId;
    }

    public void setReplyId(int replyId) {
        this.replyId = replyId;
    }


    @Override
    public void fill(Context mContext, XAdapter xAdapter, int listType, PostViewHolder holder0, IItems item, int position) {
//        super.fill(mContext, xAdapter, listType, holder0,item, position);

        CommentItemViewHolder holder = (CommentItemViewHolder) holder0;
        final CommentItem CommentItem = (CommentItem)item;

        final boolean[] loadedImage = {false};
        DateConverterSjd dateUtiliti = new DateConverterSjd();

        StringBuilder date = new StringBuilder();
        date.append(CommentItem.getDate());
        date.append(" ( ");
//        date.append("تاریخ ثبت : ");
        date.append(CommentItem.getDate());
        date.append(" ) ");
        holder.textViewDate.setText(date.toString());


        holder.textViewText.setText(CommentItem.getText());
        holder.textViewUserName.setText(CommentItem.getUserName());
//        holder.textViewCount.setText(CommentItem.getViewCount() + "");


//        holder.imageviewPicture.setVisibility(View.GONE);



        onclicks(mContext,listType , holder, CommentItem);
        LoadImages.loadAvatarimage(CommentItem.getUserImage(),holder.imageViewUserAvatar);
//        LoadImages.loadProfileimage(CommentItem.getPicture(),holder.imageviewPicture);

        holder.imageViewMenu.setVisibility(View.GONE);
    }

    private void onclicks(Context mContext , int listType, CommentItemViewHolder holder, CommentItem timelineItem) {


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
                if (timelineItem instanceof CommentItem) {
//

                }
            }
        };


//        holder.imageviewPicture.setOnClickListener(onContentClick);
        holder.textViewDate.setOnClickListener(onContentClick);
//        holder.textViewLocation.setOnClickListener(onContentClick);
        holder.textViewTitle.setOnClickListener(onContentClick);
        holder.linearLayoutCenter.setOnClickListener(onContentClick);
        holder.textViewUserName.setOnClickListener(onclick2);

    }

    @Override
    protected void share(Context mContext, int listType, ParentItem timelineItem) {

    }
}


