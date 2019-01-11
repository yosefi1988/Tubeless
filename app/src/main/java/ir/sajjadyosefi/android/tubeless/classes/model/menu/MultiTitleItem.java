package ir.sajjadyosefi.android.tubeless.classes.model.menu;

import android.view.View;

/**
 * Created by sajjad on 06/23/2016.
 */
public class MultiTitleItem {
    private String tag;
    private int  rootId;

    public int getIcon() {
        return Icon;
    }

    public void setIcon(int icon) {
        Icon = icon;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    private int  Icon;
    private int  size;
    private View.OnClickListener onClickListener;


    public MultiTitleItem(int rootId, int Icon, int size, String tag, View.OnClickListener onClickListener) {
        this.rootId=rootId;
        this.onClickListener = onClickListener;
        this.tag=tag;
        this.size = size;
        this.Icon = Icon;
    }



    public int getRootId() {
        return rootId;
    }

    public String getTag() {
        return tag;
    }

    public View.OnClickListener getOnClickListener() {
        return onClickListener;
    }
}
