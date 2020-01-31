/*
 * Copyright 2014 Eduardo Barrenechea
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package ir.sajjadyosefi.android.tubeless.widget;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import androidx.recyclerview.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import ir.sajjadyosefi.android.xTubeless.classes.model.Config.CategoryItem;
import ir.sajjadyosefi.android.tubeless.R;
import ir.sajjadyosefi.android.tubeless.widget.recyclerview.decoration.StickyHeaderAdapter;

public class StickyTestAdapter
        extends RecyclerView.Adapter<StickyTestAdapter.ViewHolder>
        implements StickyHeaderAdapter<StickyTestAdapter.HeaderHolder> {

    private List<CategoryItem> categoryItem;
    private static Context context;
    private List<CategoryItem> categoryHeaderItem;
    private LayoutInflater mInflater;
    int sortType;

    public StickyTestAdapter(Context context, List<CategoryItem> movieList0, List<CategoryItem> categoryHeaderItem0,int sortType) {
        mInflater = LayoutInflater.from(context);
        this.context = context;
        categoryItem = movieList0;
        categoryHeaderItem = categoryHeaderItem0;
        this.sortType = sortType;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        final View view = mInflater.inflate(R.layout.activity_main_select_car_category_item_test, viewGroup, false);
        return new ViewHolder(view);
    }
    @Override
    public HeaderHolder onCreateHeaderViewHolder(ViewGroup parent) {
        final View view = mInflater.inflate(R.layout.activity_main_select_car_category_header_test, parent, false);
        return new HeaderHolder(view);

    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {
//        if(!categoryItem.get(position).isHeader()) {
            viewHolder.item.setText(Html.fromHtml("<b>" + categoryItem.get(position).getText() + "</b>(" + categoryItem.get(position).getTitle() + ")."));

            //Log.i("body",categoryItem.get(position).getTitle());
//        }


        viewHolder.item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context,categoryItem.get(position).getText() ,Toast.LENGTH_SHORT).show();

                Intent intent = new Intent();
                Bundle conData = new Bundle();
                conData.putInt("ID", categoryItem.get(position).getID());
                conData.putString("DirectoryName",categoryItem.get(position).getDirectoryName());

                intent.putExtras(conData);

                if (((Activity)context).getParent() == null) {
                    ((Activity)context).setResult(Activity.RESULT_OK, intent );
                } else {
                    ((Activity)context).getParent().setResult(Activity.RESULT_OK, intent );
                }
                ((Activity)context).finish();
            }
        });
    }
    @Override
    public void onBindHeaderViewHolder(HeaderHolder viewholder, int position) {
        //Log.i("companyId",position + "");
        CategoryItem header = null;
        for (CategoryItem headerItem:  categoryHeaderItem) {
            if(headerItem.getID() == categoryItem.get(position).getIdHeader()){
                header = headerItem;
                break;
            }
        }
        if (header != null) {
            viewholder.imageView.setImageDrawable(((Activity)context).getResources().getDrawable(R.drawable.ic_launcher));
//            Picasso.with(context)
//                    //.load(String.format(Url.MEDIA_SERVER_ADDRESS + "Files/Pictures/LogoOfCompanies/48/%s.jpg", header.getPicture()))
//                    .load(String.format(Url.MEDIA_SERVER_ADDRESS + "Files/Pictures/LogoOfCompanies/50/%s.png", header.getPicture()))
//                    .placeholder(R.drawable.progress_animation)
//                    .into(viewholder.imageView);

            viewholder.header.setText(header.getTitle());
        }
    }



    @Override
    public int getItemCount() {
        return categoryItem.size();
    }

    long headerId = 0 ;
    @Override
    public long getHeaderId(int position) {
//        posision	result
//
//        0		0
//        1		0
//        2		0
//        3		1
//        4		1
//        5		1
//        6		2
//        7		2
//
        //return (long)  (long) position / 3 ;
        //if (!categoryItem.get(position).isHeader()) {
            return categoryItem.get(position).getIdHeader();// headerId++;
        //}
        //return headerId;
    }



    static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView item;

        public ViewHolder(View itemView) {
            super(itemView);

            item = (TextView) itemView;
        }
    }


    static class HeaderHolder extends RecyclerView.ViewHolder {
        public TextView header;
        public ImageView imageView;

        public HeaderHolder(View itemView) {
            super(itemView);

            header = (TextView) itemView.findViewById(R.id.textView5) ;
            imageView = (ImageView) itemView.findViewById(R.id.imageView3);

        }
    }
}
