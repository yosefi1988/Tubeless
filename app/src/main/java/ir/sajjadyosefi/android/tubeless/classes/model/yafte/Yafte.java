package ir.sajjadyosefi.android.tubeless.classes.model.yafte;

import ir.sajjadyosefi.android.tubeless.classes.model.User.User;

/**
 * Created by sajjad on 9/19/2018.
 */

public class Yafte {
    private int ID;
    private String Title;
    private String TitlePicture;
    private String Statement;
    private String Text;
    private int ViewCount;
    private User User;
    private int UserID;


    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getTitlePicture() {
        return TitlePicture;
    }

    public void setTitlePicture(String titlePicture) {
        TitlePicture = titlePicture;
    }

    public String getStatement() {
        return Statement;
    }

    public void setStatement(String statement) {
        Statement = statement;
    }


    public String getText() {
        return Text;
    }

    public void setText(String text) {
        Text = text;
    }

    public int getViewCount() {
        return ViewCount;
    }

    public void setViewCount(int viewCount) {
        ViewCount = viewCount;
    }

    public ir.sajjadyosefi.android.tubeless.classes.model.User.User getUser() {
        return User;
    }

    public void setUser(ir.sajjadyosefi.android.tubeless.classes.model.User.User user) {
        User = user;
    }

    public int getUserID() {
        return UserID;
    }

    public void setUserID(int userID) {
        UserID = userID;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }


}
