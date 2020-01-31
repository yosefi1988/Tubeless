package ir.sajjadyosefi.android.xTubeless.classes.modelY.menu;

import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import ir.sajjadyosefi.android.tubeless.R;


/**
 * Created by sajjad on 06/23/2016.
 */
public class SliderMenuMultTitle extends SlidingMenuItemTitle {

    private List<MultiTitleItem> items;

    public SliderMenuMultTitle(int title, List<MultiTitleItem> items) {
        super(title);
        this.items = items;
    }

    @Override
    public RecyclerView.ViewHolder CreateViewHolderInstance(ViewGroup parent) {


        View container = LayoutInflater.from(parent.getContext()).inflate(R.layout._row_side_menu_multi_item, parent, false);


        for (MultiTitleItem item : items) {
            View v = container.findViewById(item.getRootId());
            v.setOnClickListener(item.getOnClickListener());

            v.setTag(item.getTag());
        }
        RecyclerView.ViewHolder holder = new RecyclerView.ViewHolder(container) {
        };
        return holder;

    }


}
