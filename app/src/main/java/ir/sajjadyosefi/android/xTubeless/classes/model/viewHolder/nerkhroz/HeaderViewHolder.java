package ir.sajjadyosefi.android.xTubeless.classes.model.viewHolder.nerkhroz;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import ir.sajjadyosefi.android.xTubeless.R;
import ir.sajjadyosefi.android.xTubeless.classes.model.viewHolder.PostViewHolder;

public class HeaderViewHolder extends ParentViewHolder {

    public RelativeLayout linearLayoutMain;
    public TextView textViewTitle;
    public TextView textViewDate;

    public HeaderViewHolder(View itemView) {
        super(itemView);

        linearLayoutMain               = (RelativeLayout) itemView.findViewById(R.id.linearLayoutMain);
        textViewTitle                 = (TextView) itemView.findViewById(R.id.textViewTitle);
        textViewDate                  = (TextView) itemView.findViewById(R.id.textViewDate);
    }
}
