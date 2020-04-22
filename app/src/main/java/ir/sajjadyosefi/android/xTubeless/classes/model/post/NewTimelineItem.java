package ir.sajjadyosefi.android.xTubeless.classes.model.post;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;
import com.zl.reik.dilatingdotsprogressbar.DilatingDotsProgressBar;

import java.util.Date;
import java.util.List;

import ir.sajjadyosefi.android.xTubeless.Adapter.XAdapter;
import ir.sajjadyosefi.android.xTubeless.Global;
import ir.sajjadyosefi.android.xTubeless.R;
import ir.sajjadyosefi.android.xTubeless.activity.common.ReadBlogActivity;
import ir.sajjadyosefi.android.xTubeless.classes.JsonDateDeserializer;
import ir.sajjadyosefi.android.xTubeless.classes.StaticValue;
import ir.sajjadyosefi.android.xTubeless.classes.model.BlogItem;
import ir.sajjadyosefi.android.xTubeless.classes.model.post.innerClass.Statement;
import ir.sajjadyosefi.android.xTubeless.classes.model.post.innerClass.TextContent;
import ir.sajjadyosefi.android.xTubeless.classes.model.viewHolder.BlogItemViewHolder;
import ir.sajjadyosefi.android.xTubeless.classes.model.viewHolder.PostViewHolder;
import ir.sajjadyosefi.android.xTubeless.classes.model.viewHolder.TimelineItemViewHolder;
import ir.sajjadyosefi.android.xTubeless.utility.DateConverterSjd;

/**
 * Created by sajjad on 1/20/2018.
 */

public class NewTimelineItem extends ParentItem{

    private int blogID;
    private String title;
    private String titlePicture;
    private String statement;
    private String textPicture;
    private String text;
    private String registerDate;
    private int viewCount;
    private int shareCount;
    private int userID;
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

    public void setStatement(String statement) {
        this.statement = statement;
    }

    public String getTextPicture() {
        return textPicture;
    }

    public void setTextPicture(String textPicture) {
        this.textPicture = textPicture;
    }


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

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
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

        StringBuilder partType = new StringBuilder();
        partType.append(timelineItem.getTitle());
        if (timelineItem.getCategoryID() == StaticValue.cat1) {
            partType.append(" (");
            partType.append(mContext.getString(R.string.type1));
            partType.append(")");
        }else if (timelineItem.getCategoryID() == StaticValue.cat2) {
            partType.append(" (");
            partType.append(mContext.getString(R.string.type2));
            partType.append(")");
        }else if (timelineItem.getCategoryID() == StaticValue.cat3) {
            partType.append(" (");
            partType.append(mContext.getString(R.string.type3));
            partType.append(")");
        }else {
            partType.append(" - " + timelineItem.getCategoryID() + " ");
        }
        holder.textViewTitle.setText(partType.toString());
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
                Toast.makeText(mContext,"user id : " + timelineItem.getUserID() ,Toast.LENGTH_SHORT).show();
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
//        holder.textViewUserName.setOnClickListener(onclick2);
        holder.textViewUserName.setOnClickListener(onContentClick);

    }

    @Override
    protected void share(Context mContext, int listType, NewTimelineItem timelineItem) {
        StringBuilder stringBuilder0 = new StringBuilder();
        stringBuilder0.append(timelineItem.getTitle());
        if (timelineItem.getCategoryID() == StaticValue.cat1) {
//            stringBuilder0.append(" (");
            stringBuilder0.append(StaticValue.cat1text);
//            stringBuilder0.append(")");

        }
        if (timelineItem.getCategoryID() == StaticValue.cat2) {
//            stringBuilder0.append(" (");
            stringBuilder0.append(StaticValue.cat2text);
//            stringBuilder0.append(")");
        }
        if (timelineItem.getCategoryID() == StaticValue.cat3) {
//            stringBuilder0.append(" (");
            stringBuilder0.append(StaticValue.cat3text);
//            stringBuilder0.append(")");
        }
        stringBuilder0.append("\n");
        stringBuilder0.append("\n");


        stringBuilder0.append(timelineItem.getTitle());
        stringBuilder0.append("-");
        stringBuilder0.append("توضیحات:");

        stringBuilder0.append(" در ");
        stringBuilder0.append("00/00/00");
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


    public void modal1(Context mContext){
        final BottomSheetDialog dialog = new BottomSheetDialog(mContext);

        View view = ((Activity)mContext).getLayoutInflater().inflate(R.layout.a_b_bottom_sheet_dialog_delete2, null);
        dialog.setContentView(view);



        final TextView buttonDelete = view.findViewById(R.id.textViewDelete);
//        final TextView textViewChangeToDiscountCard = view.findViewById(R.id.textViewChangeToDiscountCard);
        final View textViewChangeToDiscountCardHr = view.findViewById(R.id.textViewChangeToDiscountCardHr);
//        final TextView textViewDiscountCenters = view.findViewById(R.id.textViewDiscountCenters);

//        textViewDiscountCenters.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                Intent intent = new Intent(Intent.ACTION_VIEW);
//                intent.setData(Uri.parse("https://www.sep.ir/searchStore"));
////                            intent.setPackage("com.cloudacl");  // package of SafeBrowser App
//                ((Activity)mContext).startActivity(intent);
//
//            }
//        });

//        if (bankCard.getDiscountRequestStatusID() == 1 || bankCard.getDiscountRequestStatusID() == 0) {
//            textViewChangeToDiscountCard.setVisibility(View.GONE);
//            textViewChangeToDiscountCardHr.setVisibility(View.GONE);
//        }else {
//            textViewChangeToDiscountCard.setVisibility(View.VISIBLE);
//            textViewChangeToDiscountCardHr.setVisibility(View.VISIBLE);
//
//            textViewChangeToDiscountCard.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    Intent intent = new Intent(mContext, DiscountCardRequestActivity.class);
//                    Bundle bundle = new Bundle();
//                    bundle.putString("CardNo", bankCard.getCardNo());
//                    intent.putExtras(bundle);
//                    dialog.dismiss();
//                    mContext.startActivity(intent);
//                }
//            });
//        }

        buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    private void loadImage(TimelineItemViewHolder holder, NewTimelineItem timelineItem) {
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


        if (timelineItem.getTitlePicture() != null && timelineItem.getTitlePicture().length() > 10) {
            holder.imageviewPicture.setVisibility(View.VISIBLE);
            Picasso.get()
                    .load(timelineItem.getTitlePicture())
                    .placeholder(R.drawable.circle_border)
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
