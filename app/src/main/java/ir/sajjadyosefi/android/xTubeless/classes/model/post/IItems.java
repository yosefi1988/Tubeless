package ir.sajjadyosefi.android.xTubeless.classes.model.post;

import android.content.Context;

import ir.sajjadyosefi.android.xTubeless.Adapter.XAdapter;
import ir.sajjadyosefi.android.xTubeless.classes.model.viewHolder.PostViewHolder;


public interface IItems {

    void fill(Context mContext, XAdapter xAdapter, int listType, PostViewHolder holder0, IItems item, int position);
}
