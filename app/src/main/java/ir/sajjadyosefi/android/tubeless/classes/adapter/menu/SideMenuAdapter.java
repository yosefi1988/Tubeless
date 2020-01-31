package ir.sajjadyosefi.android.tubeless.classes.adapter.menu;

import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import ir.sajjadyosefi.android.tubeless.R;
import ir.sajjadyosefi.android.tubeless.view.widget.view.IconTextView;
import ir.sajjadyosefi.android.xTubeless.classes.modelY.menu.SliderMenuItem;
import ir.sajjadyosefi.android.xTubeless.classes.modelY.menu.SlidingMenuItemTitle;

/**
 * Created by Other on 06/23/2016.
 */
public class SideMenuAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    List<SlidingMenuItemTitle> items;

    //constractor
    public SideMenuAdapter(List<SlidingMenuItemTitle> items) {
        this.items = items;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        SlidingMenuItemTitle item = items.get(viewType);
        RecyclerView.ViewHolder viewHodler = item.CreateViewHolderInstance(parent);
        return viewHodler;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof SideMenuViewHodler) {
            ((SideMenuViewHodler) holder).setValue((SliderMenuItem) items.get(position));


        }else  if (holder instanceof SideMenuViewHodlerTitle)
            ((SideMenuViewHodlerTitle) holder).setItem(items.get(position));
    }

    public SlidingMenuItemTitle getItemAtPosition(int position) {
        return items.get(position);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public static class SideMenuViewHodler extends RecyclerView.ViewHolder {
        private TextView txtTitle;
        private IconTextView imgIcon;

        public SideMenuViewHodler(View itemView) {
            super(itemView);
            txtTitle = (TextView) itemView.findViewById(R.id.sideMenuTxtTitle);
            imgIcon = (IconTextView) itemView.findViewById(R.id.sideMenuIcon);
        }
        public void setValue(SliderMenuItem item) {
            txtTitle.setText(item.getTitle());
            //imgIcon.setImageResource(item.getIcon());
            imgIcon.setText(item.getIcon());
            imgIcon.setTextSize(item.getSize());

            txtTitle.setOnClickListener(item.onClickListener);
            imgIcon.setOnClickListener(item.onClickListener);
        }
    }

    public static class SideMenuViewHodlerTitle extends RecyclerView.ViewHolder {
        private TextView txtTitle;
        public SideMenuViewHodlerTitle(View itemView) {
            super(itemView);
            txtTitle = (TextView) itemView.findViewById(R.id.sideMenuTxtTitle);
        }
        public void setItem(SlidingMenuItemTitle item) {
            txtTitle.setText(item.getTitle());
        }
    }

    public static class slideHolder extends RecyclerView.ViewHolder {
        public slideHolder(View itemView) {
            super(itemView);
        }
    }
}
