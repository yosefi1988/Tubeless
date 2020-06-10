package ir.sajjadyosefi.android.xTubeless.classes.model.request;

import ir.sajjadyosefi.android.xTubeless.classes.StaticValue;

public class NewBlogCommentRequest {

    private int ApplicationId = StaticValue.IDApplication;
    private int BlogID;
    private int UserID;
    private String Text;

    public int getApplicationId() {
        return ApplicationId;
    }

    public int getUserID() {
        return UserID;
    }

    public void setUserID(int userID) {
        UserID = userID;
    }

    public String getText() {
        return Text;
    }

    public void setText(String text) {
        Text = text;
    }


    public int getBlogID() {
        return BlogID;
    }

    public void setBlogID(int blogID) {
        BlogID = blogID;
    }

}
