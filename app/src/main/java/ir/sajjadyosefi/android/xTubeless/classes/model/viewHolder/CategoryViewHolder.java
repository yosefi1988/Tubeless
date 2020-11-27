package ir.sajjadyosefi.android.xTubeless.classes.model.viewHolder;

import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import ir.sajjadyosefi.android.xTubeless.R;

public class CategoryViewHolder extends PostViewHolder {

    public RelativeLayout linearLayoutMain;
    public LinearLayout linearLayoutTop;
    public LinearLayout linearLayoutCenter;
    public LinearLayout linearLayoutBottom;



    public ImageView imageViewUserAvatar;
    public ImageView imageViewPicture;
    public ImageView readmore;
    public TextView textViewUserName;
    public TextView textViewPicture;

    public TextView textViewTitle;
    public TextView textViewLocation;
    public TextView textViewDate;
    public TextView textViewCount;
    public TextView textViewDescription;

    public CategoryViewHolder(View itemView) {
        super(itemView);

        linearLayoutTop               = (LinearLayout) itemView.findViewById(R.id.linearLayoutTop);
        linearLayoutMain               = (RelativeLayout) itemView.findViewById(R.id.linearLayoutMain);
        linearLayoutCenter            = (LinearLayout) itemView.findViewById(R.id.linearLayoutCenter);
        linearLayoutBottom            = (LinearLayout) itemView.findViewById(R.id.linearLayoutBottom);


        imageViewUserAvatar           = (ImageView) itemView.findViewById(R.id.imageViewUserAvatar);
        imageViewPicture              = (ImageView) itemView.findViewById(R.id.imageViewPicture);
        readmore                        = (ImageView) itemView.findViewById(R.id.readmore);
        textViewUserName              = (TextView) itemView.findViewById(R.id.textViewUserName);
        textViewPicture               = (TextView) itemView.findViewById(R.id.textViewPicture);

        textViewTitle                 = (TextView) itemView.findViewById(R.id.textViewTitle);
        textViewLocation              = (TextView) itemView.findViewById(R.id.textViewLocation);
        textViewDate                  = (TextView) itemView.findViewById(R.id.textViewDate);
        textViewCount                 = (TextView) itemView.findViewById(R.id.textViewCount);
        textViewDescription           = (TextView) itemView.findViewById(R.id.textViewDescription);

    }
}
