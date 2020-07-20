package ir.sajjadyosefi.android.xTubeless.classes.model.post;

import android.content.Context;
import android.graphics.Color;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.view.MenuItem;
import android.view.View;
import android.widget.PopupMenu;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.List;

import ir.sajjadyosefi.android.xTubeless.Adapter.XAdapter;
import ir.sajjadyosefi.android.xTubeless.R;
import ir.sajjadyosefi.android.xTubeless.classes.model.Tag;
import ir.sajjadyosefi.android.xTubeless.classes.model.viewHolder.PostViewHolder;
import ir.sajjadyosefi.android.xTubeless.classes.model.viewHolder.TwoLinesViewHolder;

/**
 * Created by sajjad on 7/30/2017.
 */

public class PictureItem  extends ParentItem{

    public int id;
    public String CarID;
    public String ThumbnailFilePath;
    public String CarName;
    public String CarLogo;
    public List<Tag> ListTag;

    public PictureItem(int id, String the_flight, String scott_masterson, String thumbnailFilePath) {
        this.id = id;
        this.CarName = the_flight;
        this.CarName = scott_masterson;
        this.ThumbnailFilePath = thumbnailFilePath;
    }

    public PictureItem() {
    }

    public List<Tag> getListTag() {
        return ListTag;
    }

    public void setListTag(List<Tag> listTag) {
        ListTag = listTag;
    }

    public String getThumbnailFilePath() {
        return ThumbnailFilePath;
    }

    public void setThumbnailFilePath(String thumbnailFilePath) {
        ThumbnailFilePath = thumbnailFilePath;
    }

    public String getCarID() {
        return CarID;
    }

    public void setCarID(String carID) {
        CarID = carID;
    }


    public String getCarName() {
        return CarName;
    }

    public void setCarName(String carName) {
        CarName = carName;
    }

    public String getCarLogo() {
        return CarLogo;
    }

    public void setCarLogo(String carLogo) {
        CarLogo = carLogo;
    }


    @Override
    public void fill(Context mContext, XAdapter xAdapter, int listType, PostViewHolder holder0, IItems item, int position) {
        super.fill(mContext, xAdapter, listType, holder0,item, position);

        PictureItem sssss = ((PictureItem)item);
        TwoLinesViewHolder hhhhhh = ((TwoLinesViewHolder) holder0);

        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(mContext,"You Selected : " + view.toString(),Toast.LENGTH_SHORT).show();
            }
        };

        Spannable wordtoSpan = new SpannableString("I know just how to whisper, And I know just how to cry,I know just where to find the answers");
        wordtoSpan.setSpan(new ForegroundColorSpan(Color.BLUE), 15, 30, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
//        hhhhhh.title.setText(wordtoSpan);
        hhhhhh.title.setText(sssss.CarName + sssss.CarName);

        hhhhhh.textViewCarName.setText(sssss.CarName);
        hhhhhh.textViewCarName.setText(sssss.CarName);
        hhhhhh.imageViewMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PopupMenu popup = new PopupMenu(mContext, hhhhhh.imageViewMenu);
                popup.getMenuInflater().inflate(R.menu.menupic, popup.getMenu());

                for (int i = 0; i < popup.getMenu().size(); i++) {
                    MenuItem item = popup.getMenu().getItem(i);
                    SpannableString spanString = new SpannableString(popup.getMenu().getItem(i).getTitle().toString());
                    spanString.setSpan(new ForegroundColorSpan(Color.RED), 0, spanString.length(), 0);
                    item.setTitle(spanString);
                }
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    public boolean onMenuItemClick(MenuItem item) {
                        Toast.makeText(mContext,"You Selected : " + item.getTitle(),Toast.LENGTH_SHORT).show();
                        return true;
                    }
                });
                popup.show();
//                popup.show();//showing popup menu
            }
        });

        Picasso.get()
                .load(sssss.getThumbnailFilePath())
                .noPlaceholder()
//                .resizeDimen(R.dimen.simple_card_image_width, R.dimen.simple_card_image_height)
//                .centerCrop()
//                .fit()
                .into(hhhhhh.imageView);

        Picasso.get()
                .load(sssss.getCarLogo())
                .noPlaceholder()
//                .resizeDimen(R.dimen.simple_card_image_width, R.dimen.simple_card_image_height)
//                .centerCrop()
                .into(hhhhhh.imageViewCarLogo);


//        holder.button1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(final View view) {
//                Snackbar snackbar =
//                        Snackbar.make(rootView, "Button 1 of item " + holder.getAdapterPosition(), Snackbar.LENGTH_LONG)
//                                .setAction(
//                                        "Action",
//                                        null
//                                );
//                snackbar.show();
//            }
//        });
//        holder.button2.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(final View view) {
//                Snackbar snackbar = Snackbar.make(rootView, "Button 2 of item " + holder.getAdapterPosition(),
//                        Snackbar.LENGTH_LONG
//                )
//                        .setAction(
//                                "Action",
//                                null
//                        );
//                snackbar.show();
//            }
//        });

        (hhhhhh.layoutCount).setOnClickListener(onClickListener);
        (hhhhhh.layoutShare).setOnClickListener(onClickListener);
        (hhhhhh.layoutDownload).setOnClickListener(onClickListener);
        (hhhhhh.layoutDelete).setOnClickListener(onClickListener);
        (hhhhhh.viewInvisible).setOnClickListener(onClickListener);
//        (hhhhhh.textViewCount).setOnClickListener(onClickListener);
        (hhhhhh.title).setOnClickListener(onClickListener);
        (hhhhhh.textViewCarName).setOnClickListener(onClickListener);
        (hhhhhh.imageView).setOnClickListener(onClickListener);
        (hhhhhh.imageViewCarLogo).setOnClickListener(onClickListener);
        (hhhhhh.imageViewMenu).setOnClickListener(onClickListener);
    }

    @Override
    protected void share(Context mContext, int listType, ParentItem timelineItem) {

    }

    @Override
    protected void delete(Context mContext, XAdapter xAdapter, String userId, int position, int listType, ParentItem timelineItem) {

    }

    @Override
    protected void invisible(Context mContext, XAdapter xAdapter, String userId, int position, int listType, ParentItem timelineItem) {

    }
}


