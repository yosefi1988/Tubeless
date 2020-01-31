package ir.sajjadyosefi.android.tubeless.activity.innerActivity;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.widget.ImageView;
import android.widget.VideoView;

import com.joaquimley.faboptions.FabOptions;
import com.zl.reik.dilatingdotsprogressbar.DilatingDotsProgressBar;

import ir.sajjadyosefi.android.tubeless.R;
import ir.sajjadyosefi.android.tubeless.view.fragment.minor.FragmentTimelineMinor;
//import static com.joaquimley.faboptions.sample.R.id.toolbar;

/**
 * Created by sajjad on 9/15/2017.
 */

public class SearchResultActivity extends AppCompatActivity  {

    String fileFormat;
    String filePath;
    VideoView downloadedVideo;
    ImageView downloadedImage;
    boolean doubleBackToExitPressedOnce = false;
    Context context ;


    private FabOptions mFabOptions;
    public DilatingDotsProgressBar dilatingDotsProgressBar ;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_result_activity);
        context = this;

        dilatingDotsProgressBar = (DilatingDotsProgressBar) findViewById(R.id.PBSjd);

        // Begin the transaction
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        // Replace the contents of the container with the new fragment
        ft.replace(R.id.your_placeholder, prepareFragment());
        // or ft.add(R.id.your_placeholder, new FooFragment());
        // Complete the changes added above
        ft.commit();

    }


    Fragment prepareFragment() {

        int id = getIntent().getExtras().getInt("id");
        String term = getIntent().getExtras().getString("term");

        Bundle args = new Bundle();
        args.putString("term", term);
        args.putInt("id", id);
        FragmentTimelineMinor fragment = new FragmentTimelineMinor();
        fragment.setArguments(args);
        //this.values = context.getSharedPreferences(Statics.MAHAN, 0);
        return fragment;
    }
}
