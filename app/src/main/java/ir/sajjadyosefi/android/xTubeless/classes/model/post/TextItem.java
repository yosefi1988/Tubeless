package ir.sajjadyosefi.android.xTubeless.classes.model.post;
import android.content.Context;

import java.util.List;

import ir.sajjadyosefi.android.xTubeless.Adapter.XAdapter;
import ir.sajjadyosefi.android.xTubeless.classes.model.Tag;
import ir.sajjadyosefi.android.xTubeless.classes.model.viewHolder.PostViewHolder;


/**
 * Created by sajjad on 7/30/2017.
 */

public class TextItem  extends ParentItem{

    public int id;
    public String CarID;
    public String FilePath;

    public String ThumbnailFilePath;
    public String CarName;
    public String CarLogo;
    public List<Tag> ListTag;

    public TextItem(int id, String the_flight, String scott_masterson, String thumbnailFilePath) {
        this.id = id;
        this.CarName = the_flight;
        this.CarName = scott_masterson;
        this.ThumbnailFilePath = thumbnailFilePath;
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

    public String getFilePath() {
        return FilePath;
    }

    public void setFilePath(String filePath) {
        FilePath = filePath;
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

    }

    @Override
    protected void share(Context mContext, int listType, NewTimelineItem timelineItem) {

    }
}


