package ir.sajjadyosefi.android.xTubeless.classes.modelY.Blog;

import java.util.ArrayList;
import java.util.List;

import ir.sajjadyosefi.android.xTubeless.classes.modelY.responses.post.ServerResponse;


/**
 * Created by sajjad on 1/20/2018.
 */

public class BlogItemListResponse extends ServerResponse {


    //blogList
    List<BlogItem> blogList = new ArrayList<BlogItem>();

    public List<BlogItem> getBlogItem() {
        return blogList;
    }

    public void setBlogItem(List<BlogItem> blogItem) {
        blogList = blogItem;
    }

}
