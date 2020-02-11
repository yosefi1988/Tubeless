package ir.sajjadyosefi.android.tubeless.view.xFragment;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.zl.reik.dilatingdotsprogressbar.DilatingDotsProgressBar;

import java.util.ArrayList;
import java.util.List;

import ir.sajjadyosefi.android.tubeless.Global;
import ir.sajjadyosefi.android.tubeless.R;
import ir.sajjadyosefi.android.tubeless.activity.yafteha.NewYafteActivity;
import ir.sajjadyosefi.android.tubeless.adapter.EndlessList_Adapter;
import ir.sajjadyosefi.android.tubeless.adapter.fragment.FragmentTimelineAdapter;
import ir.sajjadyosefi.android.xTubeless.activity.common.ContactUsActivity;


/**
 * Created by sajjad on 08/14/2016.
 */
// In this case, the fragment displays simple text based on the page
@SuppressLint("ValidFragment")
public class TimelineFragment extends Fragment {
    public static Context           context;
    public static final String      ARG_PAGE = "ARG_PAGE";
    public static final String      ARG_LIST = "ARG_LIST";
    public static final String      ARG_HEADER = "ARG_HEADER";

    private int                     tabindex;
    private int                     listType;
    private int                     idHeader;
    LinearLayoutManager mLayoutManager;
    private RecyclerView mRecyclerViewTimeline;
    private TextView                mTextViewNoting;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private DilatingDotsProgressBar mProgressBar;
    List<Object>                    timelineItemList = new ArrayList<Object>();

    static int w = 0;
    private Button buttonSendComment;

//    public FloatingActionButton floatingActionButton, floatingActionButton2;

    public TimelineFragment newInstance(Context context, int page, int list) {
        this.context = context ;
        Bundle args = new Bundle();
        args.putInt(ARG_PAGE, page);
        args.putInt(ARG_LIST, list);
        TimelineFragment fragment = new TimelineFragment();
        fragment.setArguments(args);
        //this.values = context.getSharedPreferences(Statics.MAHAN, 0);
        return fragment;
    }

    public TimelineFragment newInstance(Context context, int page, int list, int headerId) {
        this.context = context ;
        Bundle args = new Bundle();
        args.putInt(ARG_PAGE, page);
        args.putInt(ARG_LIST, list);
        args.putInt(ARG_HEADER, headerId);
        TimelineFragment fragment = new TimelineFragment();
        fragment.setArguments(args);
        //this.values = context.getSharedPreferences(Statics.MAHAN, 0);
        return fragment;
    }

    int id;
    String term;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        tabindex = getArguments().getInt(ARG_PAGE);
        listType = getArguments().getInt(ARG_LIST);
        idHeader = getArguments().getInt(ARG_HEADER);

        id = getArguments().getInt("id");
        term = getArguments().getString("term");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.x_fragment_timeline, container, false);
            loadTimeline(view,listType,idHeader);
        return view;
    }

    //8888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888
    EndlessList_Adapter adapter_Posts;


    public FloatingActionButton floatingActionButton;

    private void loadTimeline(View view, final int listType, final int idHeader) {
        mRecyclerViewTimeline           = (RecyclerView) view.findViewById(R.id.recyclerView);
        mTextViewNoting                 = (TextView) view.findViewById(R.id.nothing_text);
        mSwipeRefreshLayout             = (SwipeRefreshLayout)  view.findViewById(R.id.swipeRefreshLayout);
        mProgressBar                    = (DilatingDotsProgressBar) view.findViewById(R.id.PBSjd);

        mRecyclerViewTimeline.setHasFixedSize(true);
        mRecyclerViewTimeline.setItemAnimator(new DefaultItemAnimator());
        mLayoutManager = new LinearLayoutManager(context);
        mRecyclerViewTimeline.setLayoutManager(mLayoutManager);

//        new AsyncLoad_Timeline().execute();
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                timelineItemList = new ArrayList<Object>();
                if(listType == FragmentTimelineAdapter.LIST_TIMELINE) {
                    adapter_Posts = new EndlessList_Adapter(
                            context,
                            0,
                            null,
                            false,
                            mProgressBar,
                            mTextViewNoting,
                            mSwipeRefreshLayout,
                            mRecyclerViewTimeline,
                            timelineItemList,
                            mLayoutManager,
                            listType);
                    Global.resetListIndex(0);
                }else if(listType == FragmentTimelineAdapter.LIST_BLOG) {
                    adapter_Posts = new EndlessList_Adapter(
                            context,
                            0,
                            null,
                            false,
                            mProgressBar,
                            mTextViewNoting,
                            mSwipeRefreshLayout,
                            mRecyclerViewTimeline,
                            timelineItemList,
                            mLayoutManager,
                            listType,
                            idHeader);
                    Global.resetListIndex(idHeader);
                }
                mRecyclerViewTimeline.setAdapter(adapter_Posts);
            }
        });



        if(term == null && id == 0) {
            if (listType == FragmentTimelineAdapter.LIST_TIMELINE) {
                adapter_Posts = new EndlessList_Adapter(
                        context,
                        0,
                        null,
                        false,
                        mProgressBar,
                        mTextViewNoting,
                        mSwipeRefreshLayout,
                        mRecyclerViewTimeline,
                        timelineItemList,
                        mLayoutManager,
                        listType);
            } else if (listType == FragmentTimelineAdapter.LIST_BLOG) {
                adapter_Posts = new EndlessList_Adapter(
                        context,
                        0,
                        null,
                        false,
                        mProgressBar,
                        mTextViewNoting,
                        mSwipeRefreshLayout,
                        mRecyclerViewTimeline,
                        timelineItemList,
                        mLayoutManager,
                        listType,
                        idHeader);
            }
            mRecyclerViewTimeline.setLayoutManager(mLayoutManager);
            mRecyclerViewTimeline.setAdapter(adapter_Posts);
        }else {
            adapter_Posts = new EndlessList_Adapter(
                    context,
                    0,
                    null,
                    false,
                    mProgressBar,
                    mTextViewNoting,
                    mSwipeRefreshLayout,
                    mRecyclerViewTimeline,
                    timelineItemList,
                    mLayoutManager,
                    term,
                    FragmentTimelineAdapter.LIST_YAFTE_RESULT,
                    id);

            mRecyclerViewTimeline.setLayoutManager(mLayoutManager);
            mRecyclerViewTimeline.setAdapter(adapter_Posts);
        }

        floatingActionButton = (FloatingActionButton) view.findViewById(R.id.fab);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (idHeader == 16 || idHeader == 17 || idHeader == 18) {
                    Intent intent = new Intent(context,NewYafteActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putInt(ContactUsActivity.Type,16);
                    intent.putExtras(bundle);
                    startActivity(intent);
                    ((Activity)context).overridePendingTransition(R.anim.fadeout, R.anim.fadein);
                }else {
//                    context.startActivity(new Intent(context, UploadPictureActivity.class));
//                    context.startActivity(new Intent(context, NewBlogActivity.class));
                }
            }
        });

        mRecyclerViewTimeline.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx,int dy){
                super.onScrolled(recyclerView, dx, dy);

                if (dy > 0) {
                    // Scroll Down
                    if (floatingActionButton.isShown()) {
                        floatingActionButton.hide();
                    }
                }
                else if (dy < 0) {
                    // Scroll Up
                    if (!floatingActionButton.isShown()) {
                        floatingActionButton.show();
                    }
                }
            }
        });
    }
    //8888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888
}