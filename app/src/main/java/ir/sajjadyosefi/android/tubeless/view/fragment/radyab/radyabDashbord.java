package ir.sajjadyosefi.android.tubeless.view.fragment.radyab;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import ir.sajjadyosefi.android.tubeless.R;
import ir.sajjadyosefi.android.tubeless.view.fragment.minor.FragmentTimelineMinor;

/**
 * Created by sajjad on 6/3/2018.
 */

public class radyabDashbord extends Fragment {

    static Context context;
    int id;
    String term;

//    public static Fragment newInstance(Context mContext) {
//        context = mContext;
//        Bundle args = new Bundle();
////        args.putInt(ARG_PAGE, page);
////        args.putInt(ARG_LIST, list);
////        args.putInt(ARG_HEADER, headerId);
//        FragmentTimelineMinor fragment = new FragmentTimelineMinor();
//        fragment.setArguments(args);
//        //this.values = context.getSharedPreferences(Statics.MAHAN, 0);
//        return fragment;
//    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        tabindex = getArguments().getInt(ARG_PAGE);
//        listType = getArguments().getInt(ARG_LIST);
//        idHeader = getArguments().getInt(ARG_HEADER);
//        id = getArguments().getInt("id");
//        term = getArguments().getString("term");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_radyab_dashbord, container, false);

//        searchBox = (RelativeLayout) view.findViewById(R.id.searchBox);
//        searchBoxRoot = (RelativeLayout) view.findViewById(R.id.searchBoxRoot);
        return view;
    }

}
