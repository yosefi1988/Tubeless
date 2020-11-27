package ir.sajjadyosefi.android.xTubeless.classes.model.viewHolder.nerkhroz;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import ir.sajjadyosefi.android.xTubeless.R;

public abstract class ParentViewHolder extends RecyclerView.ViewHolder  {

    public final int marginBottom;
    public ImageView imageViewMenu;


    public ParentViewHolder(View itemView) {
        super(itemView);
        marginBottom = ((ViewGroup.MarginLayoutParams) itemView.getLayoutParams()).bottomMargin;
        imageViewMenu               = (ImageView) itemView.findViewById(R.id.imageViewMenu);
    }

    public void clearAnimation() {
        itemView.clearAnimation();
    }

}
