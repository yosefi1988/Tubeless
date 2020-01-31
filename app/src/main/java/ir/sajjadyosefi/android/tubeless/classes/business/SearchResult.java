package ir.sajjadyosefi.android.tubeless.classes.business;

import android.content.Context;
import android.view.View;

import com.squareup.picasso.Transformation;

import java.util.List;

import ir.sajjadyosefi.android.tubeless.R;
import ir.sajjadyosefi.android.tubeless.adapter.list.SearchResultAdapter;
import ir.sajjadyosefi.android.tubeless.classes.DateConverterSjd;

import ir.sajjadyosefi.android.xTubeless.classes.modelY.yafte.search.SearchResultObject;
import ir.sajjadyosefi.android.xTubeless.utility.RoundedCornersTransformation;

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
