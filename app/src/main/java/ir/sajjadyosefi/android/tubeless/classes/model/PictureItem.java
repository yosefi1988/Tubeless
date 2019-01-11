package ir.sajjadyosefi.android.tubeless.classes.model;

import java.util.List;

/**
 * Created by sajjad on 7/30/2017.
 */

public class PictureItem {

    public String CarID;
    public String FilePath;

    public String ThumbnailFilePath;
    public String CarName;
    public String CarLogo;
    public List<Tag> ListTag;

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



}


