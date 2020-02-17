package ir.sajjadyosefi.android.xTubeless.classes.modelY.NetworkLayout.request.yafte;

import ir.sajjadyosefi.android.xTubeless.classes.StaticValue;

public class NewBlogRequest {


    private int ApplicationId = StaticValue.IDApplication;
    private int CategoryID;
    private int UserID;
    private String Text;
    private String TextPicture;
    private String Statement;
    private String Title;
    private String TitlePicture;



    public int getApplicationId() {
        return ApplicationId;
    }

    public int getCategoryID() {
        return CategoryID;
    }

    public void setCategoryID(int categoryID) {
        CategoryID = categoryID;
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

    public String getTextPicture() {
        return TextPicture;
    }

    public void setTextPicture(String textPicture) {
        TextPicture = textPicture;
    }

    public String getStatement() {
        return Statement;
    }

    public void setStatement(String statement) {
        Statement = statement;
    }

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

}
