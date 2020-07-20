package ir.sajjadyosefi.android.xTubeless.classes.model.viewHolder;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import ir.sajjadyosefi.android.xTubeless.Adapter.XAdapter;
import ir.sajjadyosefi.android.xTubeless.R;
import ir.sajjadyosefi.android.xTubeless.classes.model.post.ParentItem;

public abstract class PostViewHolder extends RecyclerView.ViewHolder  {

    public final int marginBottom;

    public ImageView imageViewMenu;

    public LinearLayout linearLayoutAdmin;
    public ImageView imageViewDelete;
    public TextView textViewDelete;

    public ImageView imageViewInvisible;
    public TextView textViewInvisible;


    public ImageView imageViewShare;
    public TextView textViewShare;



    public PostViewHolder(View itemView) {
        super(itemView);
        marginBottom = ((ViewGroup.MarginLayoutParams) itemView.getLayoutParams()).bottomMargin;
        linearLayoutAdmin =          (LinearLayout) itemView.findViewById(R.id.linearLayoutAdmin);

        imageViewDelete               = (ImageView) itemView.findViewById(R.id.imageViewDelete);
        imageViewMenu               = (ImageView) itemView.findViewById(R.id.imageViewMenu);
        textViewDelete                = (TextView) itemView.findViewById(R.id.textViewDelete);

        imageViewInvisible               = (ImageView) itemView.findViewById(R.id.imageViewInvisible);
        textViewInvisible                = (TextView) itemView.findViewById(R.id.textViewInvisible);

        imageViewShare               = (ImageView) itemView.findViewById(R.id.imageViewShare);
        textViewShare                = (TextView) itemView.findViewById(R.id.textViewShare);

    }

    public void clearAnimation() {
        itemView.clearAnimation();
    }

}
