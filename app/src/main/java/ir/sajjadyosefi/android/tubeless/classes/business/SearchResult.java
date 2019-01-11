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
import com.zl.reik.dilatingdotsprogressbar.DilatingDotsProgressBar;

import java.util.Date;
import java.util.List;

import ir.sajjadyosefi.android.tubeless.Global;
import ir.sajjadyosefi.android.tubeless.R;
import ir.sajjadyosefi.android.tubeless.activity.innerActivity.ReadBlogActivity;
import ir.sajjadyosefi.android.tubeless.activity.yafteha.SearchResultActivity;
import ir.sajjadyosefi.android.tubeless.adapter.EndlessList_Adapter;
import ir.sajjadyosefi.android.tubeless.adapter.list.SearchResultAdapter;
import ir.sajjadyosefi.android.tubeless.asyncTask.blog.AsyncDeleteBlogItem;
import ir.sajjadyosefi.android.tubeless.asyncTask.blog.AsyncFavouriteBlogItem;
import ir.sajjadyosefi.android.tubeless.classes.DateConverterSjd;
import ir.sajjadyosefi.android.tubeless.classes.JsonDateDeserializer;
import ir.sajjadyosefi.android.tubeless.classes.model.Blog.BlogItem;
import ir.sajjadyosefi.android.tubeless.classes.model.yafte.search.SearchResponse;
import ir.sajjadyosefi.android.tubeless.classes.model.yafte.search.SearchResultObject;
import ir.sajjadyosefi.android.tubeless.classes.utility.RoundedCornersTransformation;

/**
 * Created by sajjad on 9/19/2018.
 */

public class SearchResult {
    final int radius = 5;
    final int margin = 5;
    final Transformation transformation = new RoundedCornersTransformation(radius, margin);


    public void prepareBlogItem(Context mContext, View mRootView, List<SearchResultObject> resultObjectList, SearchResultAdapter.BlogItemViewHolder holder, int position) {

        final boolean[] loadedImage = {false};
        final SearchResultObject searchResponse = (SearchResultObject)(resultObjectList.get(position));

        DateConverterSjd dateUtiliti = new DateConverterSjd();
        holder.textViewTitle.setText(searchResponse.getN());


        StringBuilder stringBuilder0 = new StringBuilder();
        stringBuilder0.append(searchResponse.getfName() + " " + searchResponse.getlName()  + " ");
        if (searchResponse.getFatherName().length() > 3)
            stringBuilder0.append(mContext.getString(R.string.son_of) + " " + searchResponse.getFatherName());



        holder.textViewName.setText(stringBuilder0.toString());
        holder.textViewStatment.setText(mContext.getString(R.string.find_date) + " " + searchResponse.getFindDate());


        StringBuilder stringBuilder = new StringBuilder();

        if (searchResponse.getNationalCode().length() > 5)
            stringBuilder.append(mContext.getString(R.string.code_melli) + " " + searchResponse.getNationalCode() );


        if (searchResponse.getIdentityNumber().length() > 3)
            stringBuilder.append(" " + mContext.getString(R.string.identity_number) + " " + searchResponse.getIdentityNumber() + " ");

        if ((searchResponse.getNationalCode().length() > 5) || (searchResponse.getIdentityNumber().length() > 3))
            holder.textViewStatment2.setVisibility(View.VISIBLE);
        else
            holder.textViewStatment2.setVisibility(View.GONE);

        holder.textViewStatment2.setText(stringBuilder.toString());



        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        };
    }
}
