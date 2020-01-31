package ir.sajjadyosefi.android.xTubeless.classes.modelY.NetworkLayout.response;


import ir.sajjadyosefi.android.xTubeless.classes.modelY.Blog.BlogItem;
import ir.sajjadyosefi.android.xTubeless.classes.modelY.responses.basic.ServerResponseBase;

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

