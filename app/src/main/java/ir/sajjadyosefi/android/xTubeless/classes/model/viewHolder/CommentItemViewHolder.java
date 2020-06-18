package ir.sajjadyosefi.android.xTubeless.classes.model.viewHolder;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import ir.sajjadyosefi.android.xTubeless.R;

public class CommentItemViewHolder extends PostViewHolder {

    public LinearLayout linearLayoutTop;
    public LinearLayout linearLayoutCenter;
    public LinearLayout linearLayoutBottom;
    public LinearLayout linearLayoutAdmin;

    public ImageView imageViewShare;
    public TextView textViewShare;

    public ImageView imageViewMenu;
    public ImageView imageViewDelete;
    public TextView textViewDelete;

    public ImageView imageViewInvisible;
    public TextView textViewInvisible;

    public ImageView imageViewUserAvatar;
    public TextView textViewUserName;

    public TextView textViewTitle;
    public TextView textViewText;
    public TextView textViewDate;
    public TextView textViewCount;

    public CommentItemViewHolder(View itemView) {
        super(itemView);

        linearLayoutTop               = (LinearLayout) itemView.findViewById(R.id.linearLayoutTop);
        linearLayoutCenter            = (LinearLayout) itemView.findViewById(R.id.linearLayoutCenter);
        linearLayoutBottom            = (LinearLayout) itemView.findViewById(R.id.linearLayoutBottom);
        linearLayoutAdmin =          (LinearLayout) itemView.findViewById(R.id.linearLayoutAdmin);

        imageViewInvisible               = (ImageView) itemView.findViewById(R.id.imageViewInvisible);
        textViewInvisible                = (TextView) itemView.findViewById(R.id.textViewInvisible);
        imageViewShare               = (ImageView) itemView.findViewById(R.id.imageViewShare);
        textViewShare                = (TextView) itemView.findViewById(R.id.textViewShare);

        imageViewDelete               = (ImageView) itemView.findViewById(R.id.imageViewDelete);
        imageViewMenu               = (ImageView) itemView.findViewById(R.id.imageViewMenu);
        textViewDelete                = (TextView) itemView.findViewById(R.id.textViewDelete);


        imageViewUserAvatar           = (ImageView) itemView.findViewById(R.id.imageViewUserAvatar);
        textViewUserName              = (TextView) itemView.findViewById(R.id.textViewUserName);

        textViewTitle                 = (TextView) itemView.findViewById(R.id.textViewTitle);
        textViewText                  = (TextView) itemView.findViewById(R.id.textViewText);
        textViewDate                  = (TextView) itemView.findViewById(R.id.textViewDate);
        textViewCount                 = (TextView) itemView.findViewById(R.id.textViewCount);
    }
}
