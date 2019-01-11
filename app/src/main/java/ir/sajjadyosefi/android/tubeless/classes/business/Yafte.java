package ir.sajjadyosefi.android.tubeless.classes.business;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;

import java.util.Date;
import java.util.List;

import ir.sajjadyosefi.android.tubeless.Global;
import ir.sajjadyosefi.android.tubeless.R;
import ir.sajjadyosefi.android.tubeless.activity.innerActivity.ReadBlogActivity;
import ir.sajjadyosefi.android.tubeless.adapter.EndlessList_Adapter;
import ir.sajjadyosefi.android.tubeless.asyncTask.blog.AsyncDeleteBlogItem;
import ir.sajjadyosefi.android.tubeless.classes.DateConverterSjd;
import ir.sajjadyosefi.android.tubeless.classes.JsonDateDeserializer;
import ir.sajjadyosefi.android.tubeless.classes.utility.RoundedCornersTransformation;

/**
 * Created by sajjad on 9/19/2018.
 */

public class Yafte {

    final int radius = 5;
    final int margin = 5;
    final Transformation transformation = new RoundedCornersTransformation(radius, margin);

    public void prepareYafteItem(final Context mContext , final EndlessList_Adapter.YafteItemViewHolder holder, final List<Object> mTimelineItemList, final int position) {

        final boolean[] loadedImage = {false};
        Gson gson = new GsonBuilder().registerTypeAdapter(Date.class, new JsonDateDeserializer()).create();
        String json0000 = gson.toJson(mTimelineItemList.get(position));
        final ir.sajjadyosefi.android.tubeless.classes.model.yafte.Yafte yafteItem = gson.fromJson(json0000,ir.sajjadyosefi.android.tubeless.classes.model.yafte.Yafte.class);

        DateConverterSjd dateUtiliti = new DateConverterSjd();

        holder.textViewTitle.setText(yafteItem.getTitle());
        holder.textViewStatment.setText(yafteItem.getStatement());
        holder.textViewStatment2.setText(yafteItem.getTitlePicture());

                if (yafteItem.getUser() != null)
                    holder.textViewUserName.setText(yafteItem.getUser().getUserName());

                holder.textViewCount.setText(yafteItem.getViewCount() + "");

                View.OnClickListener onClickListener = new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                EndlessList_Adapter.prepareToShare(mContext,yafteItem.getTitlePicture(),yafteItem.getStatement(),false);
            }
        };
        holder.textViewShare.setOnClickListener(onClickListener);
        holder.imageViewShare.setOnClickListener(onClickListener);

        View.OnClickListener onStarClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Global.mUser == null){
                    Global.ShowMessageDialog(mContext,"",mContext.getString(R.string.NotLoggedIn3));
                }else {
//                    AsyncFavouriteBlogItem asyncFavouriteBlogItem = new AsyncFavouriteBlogItem(mContext,mProgressBar,yafteItem.getID(),Global.mUser.getUserID(),yafteItem.isInMyFavList());
//                    asyncFavouriteBlogItem.execute();
                }
            }
        };

//        holder.imageViewFavourite.setOnClickListener(onStarClickListener);
//        holder.textViewFavourite.setOnClickListener(onStarClickListener);
//        if(yafteItem.isInMyFavList())
//            holder.imageViewFavourite.setImageResource(android.R.drawable.btn_star_big_on);
//        else
//            holder.imageViewFavourite.setImageResource(android.R.drawable.btn_star_big_off);




        if (yafteItem.getUser() != null)
            Picasso.with(mContext)
                    .load(yafteItem.getUser().getUserImage())
                    .placeholder(R.drawable.progress_animation)
                    //.centerInside()
                    .transform(transformation)
                    .into(holder.imageViewUserAvatar, new Callback() {
                        @Override
                        public void onSuccess() {

                        }

                        @Override
                        public void onError() {
                            // TODO Auto-generated method stub
                            Picasso.with(mContext)
                                    .load(R.drawable.sajjad)
                                    .transform(transformation)
                                    .into(holder.imageViewUserAvatar);
                        }
                    });

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
                if (Global.mUser != null) {
                    if (Global.mUser.getMobileNumber().contains("09123678522")) {
                        new AlertDialog.Builder(mContext)
                                .setTitle("Title")
                                .setMessage("Do you really want to whatever?")
                                .setIcon(android.R.drawable.ic_dialog_alert)
                                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {

                                    public void onClick(DialogInterface dialog, int whichButton) {
                                        AsyncDeleteBlogItem asyncDeleteBlogItem = new AsyncDeleteBlogItem(mContext,null,yafteItem.getID(),Global.mUser.getUserID());
                                        asyncDeleteBlogItem.execute();
                                    }})
                                .setNegativeButton(android.R.string.no, null).show();


                    }else {
                        Toast.makeText(mContext,mContext.getResources().getString(R.string.not_access),Toast.LENGTH_SHORT).show();
                    }
                }else {
                    Toast.makeText(mContext,mContext.getResources().getString(R.string.not_login),Toast.LENGTH_SHORT).show();
                }
            }
        };
        holder.textViewDelete.setOnClickListener(onDeleteClickListener);
        holder.imageViewDelete.setOnClickListener(onDeleteClickListener);
    }
}
