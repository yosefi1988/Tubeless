package ir.sajjadyosefi.android.xTubeless.classes.model.post;

import android.content.Context;
import android.graphics.Color;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.view.MenuItem;
import android.view.View;
import android.widget.PopupMenu;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.List;

import ir.sajjadyosefi.android.xTubeless.Adapter.XAdapter;
import ir.sajjadyosefi.android.xTubeless.R;
import ir.sajjadyosefi.android.xTubeless.classes.model.Tag;
import ir.sajjadyosefi.android.xTubeless.classes.model.viewHolder.PostViewHolder;
import ir.sajjadyosefi.android.xTubeless.classes.model.viewHolder.TimelineItemViewHolder;
import ir.sajjadyosefi.android.xTubeless.classes.model.viewHolder.TwoLinesViewHolder;

/**
 * Created by sajjad on 7/30/2017.
 */

public class NotiesItem extends ParentItem{

    public NotiesItem() {

    }

    @Override
    public void fill(Context mContext, XAdapter xAdapter, int listType, PostViewHolder holder0, IItems item, int position) {

        TimelineItemViewHolder holder = (TimelineItemViewHolder) holder0;
        final TimelineItem timelineItem = (TimelineItem)item;




    }

    @Override
    protected void share(Context mContext, int listType, NewTimelineItem timelineItem) {

    }
}


