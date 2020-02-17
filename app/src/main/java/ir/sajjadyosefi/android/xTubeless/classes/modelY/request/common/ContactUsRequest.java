package ir.sajjadyosefi.android.xTubeless.classes.modelY.request.common;


import ir.sajjadyosefi.android.xTubeless.classes.StaticValue;

public class ContactUsRequest {


    private int ApplicationId = StaticValue.IDApplication;
    private int SenderUserID;
    private String text;
    private String title;
    private String phoneNumber;

    public int getApplicationId() {
        return ApplicationId;
    }


    public int getSenderUserID() {
        return SenderUserID;
    }

    public void setSenderUserID(int senderUserID) {
        SenderUserID = senderUserID;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }



}
