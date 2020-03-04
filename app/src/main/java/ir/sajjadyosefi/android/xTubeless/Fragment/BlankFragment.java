package ir.sajjadyosefi.android.xTubeless.Fragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import ir.sajjadyosefi.android.xTubeless.R;

//import com.astuetz.PagerSlidingTabStripDefault;

/**
 * Created by sajjad on 08/14/2016.
 */
// In this case, the fragment displays simple text based on the page
@SuppressLint("ValidFragment")
public class BlankFragment extends Fragment {
    public static Context context;
    public static int mIndex;

    public static final String ARG_PAGE = "ARG_PAGE";


    public BlankFragment() {

    }

    public BlankFragment newInstance(Context context) {
        this.context = context ;
        Bundle args = new Bundle();
//        args.putInt(ARG_PAGE, page);
//        args.putInt(ARG_LIST, list);
//        args.putInt(ARG_HEADER, headerId);
        BlankFragment fragment = new BlankFragment();
        fragment.setArguments(args);
        //this.values = context.getSharedPreferences(Statics.MAHAN, 0);
        return fragment;
    }

    public BlankFragment(Context context) {
        this.context = context;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.blank_fragment, container, false);

        return view;
    }

}