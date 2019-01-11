package ir.sajjadyosefi.android.tubeless.view.fragment.minor;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.zl.reik.dilatingdotsprogressbar.DilatingDotsProgressBar;

import java.util.ArrayList;
import java.util.List;

import ir.sajjadyosefi.android.tubeless.activity.ContactUsActivity;
import ir.sajjadyosefi.android.tubeless.activity.blog.NewBlogActivity;
import ir.sajjadyosefi.android.tubeless.activity.yafteha.NewYafteActivity;
import ir.sajjadyosefi.android.tubeless.activity.innerActivity.SearchResultActivity;
import ir.sajjadyosefi.android.tubeless.activity.yafteha.SearchActivity;
import ir.sajjadyosefi.android.tubeless.adapter.EndlessList_Adapter;
import ir.sajjadyosefi.android.tubeless.adapter.fragment.FragmentTimelineAdapter;
import ir.sajjadyosefi.android.tubeless.Global;
import ir.sajjadyosefi.android.tubeless.R;
import ir.sajjadyosefi.android.tubeless.classes.animation.ResizeAnimation;


/**
 * Created by sajjad on 08/14/2016.
 */
// In this case, the fragment displays simple text based on the page
@SuppressLint("ValidFragment")
public class FragmentTimelineMinor extends Fragment {
    public static Context           context;
    public static final String      ARG_PAGE = "ARG_PAGE";
    public static final String      ARG_LIST = "ARG_LIST";
    public static final String      ARG_HEADER = "ARG_HEADER";

    private int                     tabindex;
    private int                     listType;
    private int                     idHeader;
    LinearLayoutManager             mLayoutManager;
    private RecyclerView            mRecyclerViewTimeline;
    private TextView                mTextViewNoting;
    private SwipeRefreshLayout      mSwipeRefreshLayout;
    private DilatingDotsProgressBar mProgressBar;
    List<Object>                    timelineItemList = new ArrayList<Object>();

    RelativeLayout searchBox ;
    RelativeLayout searchBoxRoot ;
    static int w = 0;
    private Button buttonSendComment;

//    public FloatingActionButton floatingActionButton, floatingActionButton2;

    public FragmentTimelineMinor newInstance(Context context, int page,int list) {
        this.context = context ;
        Bundle args = new Bundle();
        args.putInt(ARG_PAGE, page);
        args.putInt(ARG_LIST, list);
        FragmentTimelineMinor fragment = new FragmentTimelineMinor();
        fragment.setArguments(args);
        //this.values = context.getSharedPreferences(Statics.MAHAN, 0);
        return fragment;
    }

    public FragmentTimelineMinor newInstance(Context context, int page,int list,int headerId) {
        this.context = context ;
        Bundle args = new Bundle();
        args.putInt(ARG_PAGE, page);
        args.putInt(ARG_LIST, list);
        args.putInt(ARG_HEADER, headerId);
        FragmentTimelineMinor fragment = new FragmentTimelineMinor();
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
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);

        // Checks the orientation of the screen
        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            Toast.makeText(context, "landscape", Toast.LENGTH_SHORT).show();
        } else if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT){
            Toast.makeText(context, "portrait", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_timeline_minor, container, false);

        searchBox = (RelativeLayout) view.findViewById(R.id.searchBox);
        searchBoxRoot = (RelativeLayout) view.findViewById(R.id.searchBoxRoot);


        if(idHeader == 16 || idHeader == 17 ||idHeader == 18 ){
            searchBoxRoot.setVisibility(View.VISIBLE);
            buttonSendComment = (Button) view.findViewById(R.id.buttonSendComment);

            if(searchBoxRoot.getVisibility() == View.VISIBLE) {
                ResizeAnimation resizeAnimation = new ResizeAnimation(searchBox, 0);
                resizeAnimation.setDuration(600);
                searchBox.startAnimation(resizeAnimation);
            }

            buttonSendComment.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //Global.ShowMessageDialog(context,"",context.getString(R.string.commingSoon));

                    EditText editText = (EditText)view.findViewById(R.id.editTextComment);

                    if (editText.getText().toString().length() < 2){
                        Toast.makeText(context , context.getText(R.string.search_length_comment),Toast.LENGTH_SHORT).show();
                    }else {
                        Intent intent = new Intent(context, SearchResultActivity.class);
                        intent.putExtra("term",(editText.getText().toString()));
                        intent.putExtra("id", idHeader);
                        startActivity(intent);
                    }

                    Intent intent0 = new Intent(context, SearchActivity.class);
                    intent0.putExtra("term",((EditText)view.findViewById(R.id.editTextComment)).getText().toString());
                    intent0.putExtra("id", idHeader);
                    startActivity(intent0);
                }
            });
        }else {
            searchBoxRoot.setVisibility(View.GONE);
        }
        //if(tabindex == 1)
            loadTimeline(view,listType,idHeader);

//        floatingActionButton = (FloatingActionButton) view.findViewById(R.id.fab);
//        floatingActionButton2 = (FloatingActionButton) view.findViewById(R.id.fab2);
//        floatingActionButton2.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                ((RecyclerView) view.findViewById(R.id.listView)).smoothScrollToPosition(0);
//            }
//        });
//        floatingActionButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                ((Activity)context).startActivityForResult(new Intent(((Activity)context),NewPostListActivity.class),750);
////                if (((Activity) (context)) instanceof MainActivity){
////                    //((Activity) (context))
////                    try {
////                        ((MainActivity) (context)).menuBottomBarClick(((MainActivity) (context)).findViewById(R.id.post_layout));
////                    } catch (MalformedURLException e) {
////                        e.printStackTrace();
////                    }
////                }
//            }
//        });
        return view;
    }

    //8888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888
    EndlessList_Adapter adapter_Posts;

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        Toast.makeText(((Activity)context), " onActivityResult ", Toast.LENGTH_SHORT).show();

    }

    public FloatingActionButton floatingActionButton;

    private void loadTimeline(View view, final int listType, final int idHeader) {
        mRecyclerViewTimeline           = (RecyclerView) view.findViewById(R.id.listView);
        mTextViewNoting                 = (TextView) view.findViewById(R.id.nothing_text);
        mSwipeRefreshLayout             = (SwipeRefreshLayout)  view.findViewById(R.id.swipe_refresh_news);
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
                    context.startActivity(new Intent(context, NewBlogActivity.class));
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

                        if(searchBoxRoot.getVisibility() == View.VISIBLE) {
                            ResizeAnimation resizeAnimation = new ResizeAnimation(searchBox, 0);
                            resizeAnimation.setDuration(600);
                            searchBox.startAnimation(resizeAnimation);
                        }
                    }


                }
                else if (dy < 0) {
                    // Scroll Up
                    if (!floatingActionButton.isShown()) {
                        floatingActionButton.show();

                        if(searchBoxRoot.getVisibility() == View.VISIBLE) {

                            if (w == 0) {
                                w = screenWidth(context);
                            }

                            ResizeAnimation resizeAnimation = new ResizeAnimation(searchBox, w);
                            resizeAnimation.setDuration(600);
                            searchBox.startAnimation(resizeAnimation);
                        }
                    }


                }
            }
        });
    }

    //8888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888888

    int screenWidth(Context context){
        int w ;
        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((Activity)context).getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int height = displayMetrics.heightPixels;
        int width = displayMetrics.widthPixels;
        w = width;

//        int orientation = this.getResources().getConfiguration().orientation;
//        if (orientation == Configuration.ORIENTATION_PORTRAIT) {
//            //code for portrait mode
//            w = width;
//        } else {
//            //code for landscape mode
//            w = height;
//        }
        return  w;
    }

}