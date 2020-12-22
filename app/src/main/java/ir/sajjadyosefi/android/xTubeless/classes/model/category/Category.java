package ir.sajjadyosefi.android.xTubeless.classes.model.category;

import android.content.Context;
import android.view.View;

import com.squareup.picasso.Picasso;

import java.util.List;

import ir.sajjadyosefi.android.xTubeless.Adapter.EndlessList_AdapterCategory;
import ir.sajjadyosefi.android.xTubeless.Adapter.EndlessList_AdapterFile;
import ir.sajjadyosefi.android.xTubeless.R;
import ir.sajjadyosefi.android.xTubeless.activity.list.CategoryListActivity;
import ir.sajjadyosefi.android.xTubeless.activity.list.FileListActivity;
import ir.sajjadyosefi.android.xTubeless.classes.model.TubelessObject;

import static ir.sajjadyosefi.android.xTubeless.Adapter.EndlessList_AdapterCategory.ListItemType.TYPE_LAST_ITEM;

/**
 * Created by sajjad on 1/20/2018.
 */

public class Category extends TubelessObject {
    private int ID;
    private int HeaderID;
    private String Name;
    private String Statement;


    private String image;

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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }


    public EndlessList_AdapterCategory.ListItemType getListItemType() {
        return listItemType;
    }

    public void setListItemType(EndlessList_AdapterCategory.ListItemType listItemType) {
        this.listItemType = listItemType;
    }



    public void bind(Context mContext,
                     EndlessList_AdapterCategory.CategoryViewHolder holder,
                     List<Category> categoryList,
                     int position,
                     EndlessList_AdapterCategory adapter,
                     boolean deletable) {

        Category category = (Category) categoryList.get(position);

        StringBuilder text = new StringBuilder();
        text.append(category.getName());

        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        holder.textViewTitle.setText(text.toString());
        holder.textViewDescription.setText(category.getStatement());

        holder.textViewTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        holder.textViewDescription.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });


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
                categoryList.remove(position);

                if (categoryList.size() >= 3) {
                    if (categoryList.get(categoryList.size() - 1).getListItemType() == TYPE_LAST_ITEM) {
                        categoryList.remove(categoryList.size() - 1);
                        adapter.notifyDataSetChanged();
                    }else {
                        adapter.notifyDataSetChanged();
                    }
                }else {
                    adapter.notifyDataSetChanged();
                }

                ((CategoryListActivity)mContext).refreshButtons();
            }
        });


        Picasso.get()
                .load(category.getImage())
                //.transform(transformation)
                .into(holder.imageView);
    }


}
