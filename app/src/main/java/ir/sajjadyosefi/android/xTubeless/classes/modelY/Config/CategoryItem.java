package ir.sajjadyosefi.android.xTubeless.classes.model.Config;

/**
 * Created by sajjad on 8/7/2017.
 */

public class CategoryItem {

    public int ID;
    public boolean isHeader;
    public int idHeader;
    public String Picture;
    public String Title;
    public String Text;
    public String DirectoryName;

    public int getID() {
        return ID;
    }


    public String getDirectoryName() {
        return DirectoryName;
    }

    public void setDirectoryName(String directoryName) {
        DirectoryName = directoryName;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getText() {
        return Text;
    }

    public void setText(String text) {
        Text = text;
    }

    public String getPicture() {
        return Picture;
    }

    public void setPicture(String picture) {
        Picture = picture;
    }

    public boolean isHeader() {
        return isHeader;
    }

    public void setHeader(boolean header) {
        isHeader = header;
    }


    public int getIdHeader() {
        return idHeader;
    }

    public void setIdHeader(int idHeader) {
        this.idHeader = idHeader;
    }

}
