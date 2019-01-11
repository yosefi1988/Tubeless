package ir.sajjadyosefi.android.tubeless.classes.model.menu;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import ir.sajjadyosefi.android.tubeless.R;
import ir.sajjadyosefi.android.tubeless.classes.adapter.menu.SideMenuAdapter;


/**
 * Created by Other on 06/23/2016.
 */
public class SlidingMenuItemTitle {

    private int title;

    public SlidingMenuItemTitle(int title) {
        this.title = title;
    }

    public int getTitle() {
        return title;
    }

    public void setTitle(int title) {
        this.title = title;
    }

    public RecyclerView.ViewHolder CreateViewHolderInstance(ViewGroup parent) {
        View itemView = LayoutInflater.from(parent.getContext()) .inflate(R.layout._row_sliding_menu_title, parent,false);
        return new SideMenuAdapter.SideMenuViewHodlerTitle(itemView);
    }
}
