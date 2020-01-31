package ir.sajjadyosefi.android.tubeless.classes.business;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import androidx.appcompat.app.AlertDialog;
import android.view.View;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.squareup.picasso.Transformation;
import com.zl.reik.dilatingdotsprogressbar.DilatingDotsProgressBar;

import java.util.Date;
import java.util.List;

import ir.sajjadyosefi.android.tubeless.Global;
import ir.sajjadyosefi.android.tubeless.R;
import ir.sajjadyosefi.android.tubeless.activity.innerActivity.ReadBlogActivity;
import ir.sajjadyosefi.android.tubeless.adapter.EndlessList_Adapter;
import ir.sajjadyosefi.android.tubeless.asyncTask.blog.AsyncDeleteBlogItem;
import ir.sajjadyosefi.android.tubeless.asyncTask.blog.AsyncFavouriteBlogItem;
import ir.sajjadyosefi.android.tubeless.classes.DateConverterSjd;
import ir.sajjadyosefi.android.tubeless.classes.JsonDateDeserializer;
import ir.sajjadyosefi.android.xTubeless.classes.modelY.Blog.BlogItem;
import ir.sajjadyosefi.android.xTubeless.classes.modelY.viewHolder.BlogItemViewHolder;
import ir.sajjadyosefi.android.xTubeless.utility.RoundedCornersTransformation;

/**
 * Created by sajjad on 9/19/2018.
 */

public class Blog {
    final int radius = 5;
    final int margin = 5;
    final Transformation transformation = new RoundedCornersTransformation(radius, margin);

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


        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EndlessList_Adapter.prepareToShare(mContext,blogItem.getTitlePicture(), blogItem.getStatement(), loadedImage[0]);
            }
        };
        holder.textViewShare.setOnClickListener(onClickListener);
        holder.imageViewShare.setOnClickListener(onClickListener);

        holder.linearLayoutFaveorative.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Global.user == null){
                    Global.ShowMessageDialog(mContext,"",mContext.getString(R.string.NotLoggedIn3));
                }else {
                    AsyncFavouriteBlogItem asyncFavouriteBlogItem = new AsyncFavouriteBlogItem(mContext,mProgressBar,blogItem.getID(),Global.user.getUserId(),blogItem.isInMyFavList());
                    asyncFavouriteBlogItem.execute();
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
                Intent intent = new Intent(mContext, ReadBlogActivity.class);

                Gson gson = new Gson();
                String json = gson.toJson(mTimelineItemList.get(position));
                intent.putExtra("Object", json);
                mContext.startActivity(intent);
                ((Activity) mContext).overridePendingTransition(R.anim.fadeout, R.anim.fadein);
            }
        };
        holder.textViewTitle.setOnClickListener(onclick);
        holder.textViewStatment.setOnClickListener(onclick);
        holder.textViewStatment2.setOnClickListener(onclick);




        View.OnClickListener onDeleteClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Global.user != null) {
                    if (Global.user.getMobileNumber().contains("09123678522")) {
                        new AlertDialog.Builder(mContext)
                                .setTitle("Title")
                                .setMessage("Do you really want to whatever?")
                                .setIcon(android.R.drawable.ic_dialog_alert)
                                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {

                                    public void onClick(DialogInterface dialog, int whichButton) {
                                        AsyncDeleteBlogItem asyncDeleteBlogItem = new AsyncDeleteBlogItem(mContext,mProgressBar,blogItem.getID(),Global.user.getUserId());
                                        asyncDeleteBlogItem.execute();
                                    }})
                                .setNegativeButton(android.R.string.no, null).show();


                    }else {
                        Toast.makeText(mContext,mContext.getResources().getString(R.string.not_access),Toast.LENGTH_SHORT).show();
                    }
                }else {
                    Toast.makeText(mContext,mContext.getResources().getString(R.string.not_login),Toast.LENGTH_SHORT).show();
                }
                //mContext,
                //blogItem.getTitlePicture(),
                // blogItem.getStatement(),
                // loadedImage[0]);
            }
        };
        holder.textViewDelete.setOnClickListener(onDeleteClickListener);
        holder.imageViewDelete.setOnClickListener(onDeleteClickListener);
    }


}
