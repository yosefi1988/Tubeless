package ir.sajjadyosefi.android.xTubeless.classes.modelY.Blog;


import ir.sajjadyosefi.android.xTubeless.classes.modelY.responses.ServerResponse;

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
