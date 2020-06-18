package ir.sajjadyosefi.android.xTubeless.classes.model.response;



import java.util.ArrayList;
import java.util.List;

import ir.sajjadyosefi.android.xTubeless.classes.model.post.blog.CommentItem;

/**
 * Created by sajjad on 1/20/2018.
 */

public class CommentListResponse extends ServerResponseBase {

    List<CommentItem> commentList = new ArrayList<CommentItem>();

    public List<CommentItem> getCommentList() {
        return commentList;
    }

    public void setCommentList(List<CommentItem> commentList) {
        this.commentList = commentList;
    }
}
