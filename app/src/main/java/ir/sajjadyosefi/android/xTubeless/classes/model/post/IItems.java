package ir.sajjadyosefi.android.xTubeless.classes.model.post;

import android.content.Context;

import ir.sajjadyosefi.android.xTubeless.classes.model.viewHolder.PostViewHolder;


public interface IItems {

    void fill(Context context , int listType , PostViewHolder holder , IItems item);
}
