package ir.sajjadyosefi.android.xTubeless.classes.model.request;

import ir.sajjadyosefi.android.xTubeless.classes.StaticValue;

public class NewVoteRequest {

    private int ApplicationId = StaticValue.IDApplication;
    private int commentId;
    private int UserID;
    private Boolean vote;

    public int getApplicationId() {
        return ApplicationId;
    }

    public int getUserID() {
        return UserID;
    }

    public void setUserID(int userID) {
        UserID = userID;
    }

    public int getCommentId() {
        return commentId;
    }

    public void setCommentId(int commentId) {
        this.commentId = commentId;
    }

    public Boolean getVote() {
        return vote;
    }

    public void setVote(Boolean vote) {
        this.vote = vote;
    }
}
