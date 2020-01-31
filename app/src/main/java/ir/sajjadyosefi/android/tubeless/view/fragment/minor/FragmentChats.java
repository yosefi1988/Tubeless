package ir.sajjadyosefi.android.tubeless.view.fragment.minor;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import ir.sajjadyosefi.android.tubeless.R;
import ir.sajjadyosefi.android.tubeless.view.widget.DepthPageTransformer;
import ir.sajjadyosefi.android.tubeless.view.widget.ViewPagerCustomDuration;

/**
 * Created by sajjad on 08/14/2016.
 */
// In this case, the fragment displays simple text based on the page
@SuppressLint("ValidFragment")
public class FragmentChats extends Fragment {

    public static SwipeRefreshLayout mSwipeRefreshLayout_list;

    //chat Activity
    ViewPagerCustomDuration viewPager;
    public static boolean isChat = false ;

    public FragmentChats() {
    }

    public FragmentChats(Context context) {
        this.context = context;
    }
    public Context context;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        final View view = inflater.inflate(R.layout.empty_fragment_sajjadtest, container, false);
        viewPager = (ViewPagerCustomDuration) view.findViewById(R.id.pager);
        //mainViewPager.setAdapter(new ChatSlidePageAdapter(getChildFragmentManager(), context,mainViewPager,3));

        return view;
    }

    public boolean goToChatListView (Context context, ViewPagerCustomDuration viewPager) {
        try {
            isChat = false;
            viewPager.setPageTransformer(true, new DepthPageTransformer());
            viewPager.setScrollDurationFactor(3); // make the animation twice as slow
            viewPager.setCurrentItem(0);
            return true;
        }catch (Exception ex){
            return false;
        }
    }




}