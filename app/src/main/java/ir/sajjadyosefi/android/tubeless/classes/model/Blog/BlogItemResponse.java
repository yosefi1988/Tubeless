package ir.sajjadyosefi.android.tubeless.classes.model.Blog;

import ir.sajjadyosefi.android.tubeless.classes.model.Responses.ServerResponse;

/**
 * Created by sajjad on 1/20/2018.
 */

public class BlogItemResponse extends ServerResponse {

    //blogList
    BlogItem blog = new BlogItem();



    public BlogItem getBlog() {
        return blog;
    }

    public void setBlog(BlogItem blog) {
        this.blog = blog;
    }





}
