package ir.sajjadyosefi.android.tubeless.classes.model.NetworkLayout.response;

import ir.sajjadyosefi.android.tubeless.classes.model.Blog.BlogItem;
import ir.sajjadyosefi.android.tubeless.classes.model.NetworkLayout.ServerResponseBase;
import ir.sajjadyosefi.android.tubeless.classes.model.User.User;

/**
 * Created by sajjad on 3/2/2017.
 */
public class NewYafteResponse extends ServerResponseBase {
    private BlogItem blog ;


    public BlogItem getBlog() {
        return blog;
    }

    public void setBlog(BlogItem blog) {
        this.blog = blog;
    }


}

