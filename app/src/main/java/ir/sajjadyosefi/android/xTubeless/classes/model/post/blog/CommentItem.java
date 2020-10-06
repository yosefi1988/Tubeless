package ir.sajjadyosefi.android.xTubeless.classes.model.post.blog;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.gson.Gson;

import ir.sajjadyosefi.android.xTubeless.Adapter.XAdapter;
import ir.sajjadyosefi.android.xTubeless.Global;
import ir.sajjadyosefi.android.xTubeless.R;
import ir.sajjadyosefi.android.xTubeless.activity.activities.TubelessActivity;
import ir.sajjadyosefi.android.xTubeless.activity.common.ContactUsActivity;
import ir.sajjadyosefi.android.xTubeless.classes.model.exception.TubelessException;
import ir.sajjadyosefi.android.xTubeless.classes.model.post.IItems;
import ir.sajjadyosefi.android.xTubeless.classes.model.post.NewTimelineItem;
import ir.sajjadyosefi.android.xTubeless.classes.model.post.ParentItem;
import ir.sajjadyosefi.android.xTubeless.classes.model.post.innerClass.CommentContent;
import ir.sajjadyosefi.android.xTubeless.classes.model.response.ServerResponseBase;
import ir.sajjadyosefi.android.xTubeless.classes.model.viewHolder.CommentItemViewHolder;
import ir.sajjadyosefi.android.xTubeless.classes.model.viewHolder.PostViewHolder;
import ir.sajjadyosefi.android.xTubeless.networkLayout.retrofit.TubelessRetrofitCallbackss;
import ir.sajjadyosefi.android.xTubeless.utility.DateConverterSjd;
import ir.sajjadyosefi.android.xTubeless.utility.picasso.LoadImages;
import retrofit2.Call;

import static ir.sajjadyosefi.android.xTubeless.classes.model.exception.TubelessException.TUBELESS_OPERATION_NOT_COMPLETE;
import static ir.sajjadyosefi.android.xTubeless.classes.model.exception.TubelessException.TUBELESS_TRY_AGAIN;


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

    public String getUserNameMasked() {
        return NewTimelineItem.getUserNameMasked(userName);
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
        super.fill(mContext, xAdapter, listType, holder0,item, position);

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


//        holder.textViewText.setText(CommentItem.getText());
        holder.textViewText.setText(CommentItem.getStatementFromJson());
        holder.textViewUserName.setText(CommentItem.getUserNameMasked());
//        holder.textViewCount.setText(CommentItem.getViewCount() + "");


//        holder.imageviewPicture.setVisibility(View.GONE);



        onclicks(mContext,listType , holder, CommentItem);
        LoadImages.loadAvatarimage(CommentItem.getUserImage(),holder.imageViewUserAvatar);
//        LoadImages.loadProfileimage(CommentItem.getPicture(),holder.imageviewPicture);

        holder.imageViewMenu.setVisibility(View.GONE);
    }


    public String getStatementFromJson() {
        try {
            Gson gson = new Gson();

            CommentContent statementObj = gson.fromJson(text, CommentContent.class);
            StringBuilder stringBuilder = new StringBuilder();

//            if (statementObj.getTitle() != null) {
//                stringBuilder.append(statementObj.getTitle());
//                stringBuilder.append("-");
//            }
//
//            if (BuildConfig.FLAVOR_version_name.equals("bourse")){
//                //stringBuilder.append(" : ");
//            }else {
//                stringBuilder.append(" مدل: ");
//            }

//            stringBuilder.append(statementObj.getModel());
//            stringBuilder.append("\n");
            stringBuilder.append(statementObj.getText());

            return stringBuilder.toString();
        }catch (Exception ex){
            return text;
        }
    }

    private void onclicks(Context mContext , int listType, CommentItemViewHolder holder, CommentItem timelineItem) {

        View.OnClickListener onReportClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putInt(ContactUsActivity.Type, ContactUsActivity.CONTENT_REPORT);
                bundle.putString(ContactUsActivity.Title, String.format("%s:%s",
                        mContext.getString(R.string.reportContent),
                        ((CommentItem) timelineItem).getStatementFromJson()));
                bundle.putString(ContactUsActivity.Phone,  String.format("CID=%s-CUID=%s-UID=%s-UN=%s",
                        ((CommentItem) timelineItem).getCommentId(),
                        ((CommentItem) timelineItem).getUserID(),
                        ((Global.user == null ? "X":Global.user.getUserId())),
                        ((Global.user == null ? "X":Global.user.getUserName()))));
                ((Activity)mContext).startActivity(ContactUsActivity.getIntent(mContext, bundle));
            }
        };
        holder.imageViewReport.setOnClickListener(onReportClickListener);
        holder.textViewReport.setOnClickListener(onReportClickListener);

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
                if (timelineItem instanceof CommentItem) {
//                    Toast.makeText(mContext,"user id : " + timelineItem.getUserID() , Toast.LENGTH_SHORT).show();
                    Toast.makeText(mContext,"," , Toast.LENGTH_SHORT).show();

//                    Bundle bundle = new Bundle();
//                    bundle.putInt("type" , TYPE_COMMENTS);
//                    bundle.putInt("blogId" , timelineItem.getBlogID());
//                    bundle.putSerializable("LIST", (Serializable)new ArrayList<CommentItem>());
//                    ((Activity)mContext).startActivityForResult(ContainerActivity.getIntent(mContext,bundle),READ_BLOG_COMMENTS);
                }

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


//        holder.imageviewPicture.setOnClickListener(onclick);
        holder.textViewDate.setOnClickListener(onclick);
//        holder.textViewLocation.setOnClickListener(onclick);
        holder.textViewTitle.setOnClickListener(onclick);
        holder.linearLayoutCenter.setOnClickListener(onclick);
        holder.textViewUserName.setOnClickListener(onclick);

    }

    @Override
    protected void share(Context mContext, int listType, ParentItem timelineItem) {

    }

    @Override
    protected void delete(Context mContext, XAdapter xAdapter,String userId, int position, int listType, ParentItem timelineItem) {
        Global.apiManagerTubeless.deleteBlogComment(((CommentItem)timelineItem).getCommentId(), userId, new TubelessRetrofitCallbackss(mContext, ServerResponseBase.class) {
            @Override
            public void t_beforeSendRequest() {
                ((TubelessActivity)mContext).progressDialog.show();

            }

            @Override
            public void t_afterGetResponse() {
                ((TubelessActivity)mContext).progressDialog.hide();
            }

            @Override
            public void t_complite() {
            }

            @Override
            public void t_responseNull() {
                new TubelessException().handleServerMessage(mContext,new TubelessException(TUBELESS_OPERATION_NOT_COMPLETE));
            }

            @Override
            public void t_retry(Call<Object> call) {
                new TubelessException().handleServerMessage(mContext,new TubelessException(TUBELESS_TRY_AGAIN));
            }

            @Override
            public void t_onSuccess(Object response) {
                xAdapter.removeItem(listType,position);
            }
        });
    }

    @Override
    protected void invisible(Context mContext, XAdapter xAdapter,String userId, int position, int listType, ParentItem timelineItem) {
        Global.apiManagerTubeless.invisibleBlogComment(((CommentItem)timelineItem).getCommentId(), userId, new TubelessRetrofitCallbackss(mContext, ServerResponseBase.class) {
            @Override
            public void t_beforeSendRequest() {
                ((TubelessActivity)mContext).progressDialog.show();

            }

            @Override
            public void t_afterGetResponse() {
                ((TubelessActivity)mContext).progressDialog.hide();
            }

            @Override
            public void t_complite() {
            }

            @Override
            public void t_responseNull() {
                new TubelessException().handleServerMessage(mContext,new TubelessException(TUBELESS_OPERATION_NOT_COMPLETE));
            }

            @Override
            public void t_retry(Call<Object> call) {
                new TubelessException().handleServerMessage(mContext,new TubelessException(TUBELESS_TRY_AGAIN));
            }

            @Override
            public void t_onSuccess(Object response) {
                xAdapter.removeItem(listType,position);
            }
        });
    }
}


