package ir.sajjadyosefi.android.xTubeless.classes.model;

import android.app.Activity;
import android.content.Context;
import android.view.View;

import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.util.List;

import ir.sajjadyosefi.android.xTubeless.Adapter.EndlessList_AdapterCategory;
import ir.sajjadyosefi.android.xTubeless.Adapter.EndlessList_AdapterFile;
import ir.sajjadyosefi.android.xTubeless.R;
import ir.sajjadyosefi.android.xTubeless.activity.list.FileListActivity;
import ir.sajjadyosefi.android.xTubeless.utility.file.FileUtils;

/**
 * Created by sajjad on 1/20/2018.
 */

public class Category extends TubelessObject {
    private int ID;
    private int HeaderID;
    private String Name;
    private String Statement;

    private EndlessList_AdapterCategory.ListItemType listItemType;


    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getHeaderID() {
        return HeaderID;
    }

    public void setHeaderID(int headerID) {
        HeaderID = headerID;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getStatement() {
        return Statement;
    }

    public void setStatement(String statement) {
        Statement = statement;
    }


    public EndlessList_AdapterCategory.ListItemType getListItemType() {
        return listItemType;
    }

    public void setListItemType(EndlessList_AdapterCategory.ListItemType listItemType) {
        this.listItemType = listItemType;
    }



    public void prepareFileItem(Context mContext,
                                EndlessList_AdapterCategory.CategoryViewHolder holder,
                                List<Category> mTimelineItemList,
                                int position,
                                EndlessList_AdapterCategory adapter,
                                boolean deletable) {

        Category file = (Category) mTimelineItemList.get(position);

        StringBuilder text = new StringBuilder();
        //text.append(file.getTitle());

        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        holder.textView.setText(text.toString());
        holder.textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        holder.radioButton.setChecked(position == EndlessList_AdapterFile.lastCheckedPosition);
        holder.radioButton2.setChecked(position == EndlessList_AdapterFile.lastCheckedPosition2);



        if (deletable){
            holder.buttonDelete.setEnabled(deletable);
            holder.buttonDelete.setVisibility(View.VISIBLE);
        }else {
            holder.buttonDelete.setEnabled(deletable);
            holder.buttonDelete.setVisibility(View.GONE);
        }

        holder.buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mTimelineItemList.remove(position);

                if (mTimelineItemList.size() == 0 )
                    holder.textView.setText(R.string.someText);

                adapter.notifyDataSetChanged();


//                ((FileListActivity) mContext).fileCount = ((FileListActivity) mContext).fileCount - 1;
                ((FileListActivity)mContext).refreshButtons();
            }
        });

        holder.buttonShow.setEnabled(true);
        holder.buttonShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                holder.textView.performClick();
            }
        });

    }


}
