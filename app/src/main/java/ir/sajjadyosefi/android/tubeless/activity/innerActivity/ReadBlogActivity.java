package ir.sajjadyosefi.android.tubeless.activity.innerActivity;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.gson.Gson;

import ir.sajjadyosefi.android.xTubeless.classes.modelY.Blog.BlogItem;
import ir.sajjadyosefi.android.tubeless.R;

/**
 * Created by sajjad on 2/11/2018.
 */

public class ReadBlogActivity extends AppCompatActivity {

    Context mContext = null;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_read_blog);
        mContext = this;

        Gson gson = new Gson();
        String objectString = getIntent().getStringExtra("Object");
        BlogItem blogItem = gson.fromJson(objectString,BlogItem.class);

//        AsyncLoadBlogItem asyncLoadBlogItem = new AsyncLoadBlogItem(
//                mContext,
//                ((DilatingDotsProgressBar)findViewById(R.id.PBSjd)),
//                ((ImageView)findViewById(R.id.iv_menuCover)),
//                ((TextView)(findViewById(R.id.tvPersian0))),
//                ((TextView)(findViewById(R.id.tvPersian))),
//                ((TextView)(findViewById(R.id.nothing_text))),
//                blogItem.getID());
//        asyncLoadBlogItem.execute();

        getSupportActionBar().setTitle(getString(R.string.pleaseWait));

    }
}
