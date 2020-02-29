package ir.sajjadyosefi.android.xTubeless.classes.model.viewHolder;

import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

public class PostViewHolder extends RecyclerView.ViewHolder {

    public final int marginBottom;

    public PostViewHolder(View itemView) {
        super(itemView);
        marginBottom = ((ViewGroup.MarginLayoutParams) itemView.getLayoutParams()).bottomMargin;
    }

    public void clearAnimation() {
        itemView.clearAnimation();
    }
}
