package ir.sajjadyosefi.android.tubeless.classes.model.Blog;

import java.util.ArrayList;
import java.util.List;

import ir.sajjadyosefi.android.tubeless.classes.model.Responses.ServerResponse;

/**
 * Created by sajjad on 1/20/2018.
 */

public class BlogItemListResponse extends ServerResponse {


    //blogList
    List<BlogItem> blogList = new ArrayList<BlogItem>();

    public List<ir.sajjadyosefi.android.tubeless.classes.model.Blog.BlogItem> getBlogItem() {
        return blogList;
    }

    public void setBlogItem(List<ir.sajjadyosefi.android.tubeless.classes.model.Blog.BlogItem> blogItem) {
        blogList = blogItem;
    }

}
