package ir.sajjadyosefi.android.tubeless.classes.model.menu;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import ir.sajjadyosefi.android.tubeless.R;
import ir.sajjadyosefi.android.tubeless.classes.adapter.menu.SideMenuAdapter;
import ir.sajjadyosefi.android.tubeless.view.widget.view.IconTextView;


/**
 * Created by sajjad on 06/23/2016.
 */
public class SliderMenuItem extends SlidingMenuItemTitle {

    private int icon;
    private int size;

    private String clickItem;
    public View.OnClickListener onClickListener;
    private int textAndIconColor;

    public SliderMenuItem(int title, int icon, int size, View.OnClickListener onClickListener, int textAndIconColor) {
        super(title);
        this.icon = icon;
        this.size = size;
        this.clickItem = clickItem;
        this.onClickListener = onClickListener ;
        this.textAndIconColor=textAndIconColor;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }


    public String getClickItem() {
        return clickItem;
    }

    public void setClickItem(String clickItem) {
        this.clickItem = clickItem;
    }


    @Override
    public RecyclerView.ViewHolder CreateViewHolderInstance(ViewGroup parent){
//        if(textAndIconColor == R.color.colorPrimary) {
            View itemView = LayoutInflater.from(parent.getContext()) .inflate(R.layout._row_sliding_menu, parent,false);
            IconTextView icontextview = (IconTextView) itemView.findViewById(R.id.sideMenuIcon);
            return new SideMenuAdapter.SideMenuViewHodler(itemView);
//        }
//        else{
//            View itemView =LayoutInflater.from(parent.getContext()) .inflate(R.layout.row_side_menu_exit, parent,false);
//            return new SideMenuAdapter.SideMenuViewHodler(itemView);
//        }
    }
}
