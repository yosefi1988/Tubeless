package ir.sajjadyosefi.android.xTubeless.Fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.readystatesoftware.systembartint.SystemBarTintManager.SystemBarConfig;
import com.zl.reik.dilatingdotsprogressbar.DilatingDotsProgressBar;
import java.util.ArrayList;
import java.util.List;
import ir.sajjadyosefi.android.xTubeless.R;
import ir.sajjadyosefi.android.xTubeless.activity.TubelessActivity;
import ir.sajjadyosefi.android.xTubeless.activity.register.RegNewBlogActivity;
import ir.sajjadyosefi.android.xTubeless.activity.register.RegNewYadakActivity;
import ir.sajjadyosefi.android.xTubeless.activity.register.RegNewYafteActivity;
import ir.sajjadyosefi.android.xTubeless.Adapter.XAdapter;
import ir.sajjadyosefi.android.xTubeless.classes.model.Tag;
import ir.sajjadyosefi.android.xTubeless.classes.model.post.IItems;
import ir.sajjadyosefi.android.xTubeless.classes.model.post.PictureItem;
import ir.sajjadyosefi.android.xTubeless.classes.model.post.TextItem;


import static ir.sajjadyosefi.android.xTubeless.Adapter.FirstFragmentsAdapter.TYPE_BOURSE_ANALIZE_All;
import static ir.sajjadyosefi.android.xTubeless.Adapter.FirstFragmentsAdapter.TYPE_BOURSE_NEWS;
import static ir.sajjadyosefi.android.xTubeless.Adapter.FirstFragmentsAdapter.TYPE_BOURSE_TRAIN;
import static ir.sajjadyosefi.android.xTubeless.Adapter.FirstFragmentsAdapter.TYPE_IMAGE;
import static ir.sajjadyosefi.android.xTubeless.Adapter.FirstFragmentsAdapter.TYPE_POST_SEARCH_RESULT;
import static ir.sajjadyosefi.android.xTubeless.Adapter.FirstFragmentsAdapter.TYPE_YADAK;
import static ir.sajjadyosefi.android.xTubeless.Adapter.FirstFragmentsAdapter.TYPE_YAFTE;


public class ListFragment extends Fragment  {

    //private
    private RecyclerView mRecyclerView;
    private CoordinatorLayout mCoordinatorLayout;
    private View fragmentRootView;
    private TubelessActivity activity;
    private int listType;
    private Bundle bundle;
    private List<IItems> list;
    private FloatingActionButton floatingActionButton ,floatingActionButtonList;
    private LinearLayoutManager mLayoutManager;
    private TextView mTextViewNoting;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private DilatingDotsProgressBar mProgressBar;

    //public
    private SystemBarConfig config;
    private ViewGroup mRoot;

    //static
    public static final String      ARG_PAGE = "ARG_PAGE";
    public static final String      ARG_LIST = "ARG_LIST";
    public static final String      ARG_HEADER = "ARG_HEADER";
    public static Context           context;



    public ListFragment newInstance(Context context, int page, int list, int headerId) {
        this.context = context ;
        Bundle args = new Bundle();
        args.putInt(ARG_PAGE, page);
        args.putInt(ARG_LIST, list);
        args.putInt(ARG_HEADER, headerId);
        ListFragment fragment = new ListFragment(context , 0);
        fragment.setArguments(args);
        //this.values = context.getSharedPreferences(Statics.MAHAN, 0);
        return fragment;
    }

    public ListFragment() {
    }

    public ListFragment(Context context, int listType) {
        this.context = context;
        this.listType = listType;
    }

    public ListFragment(Context context,List<IItems> list, int listType) {
        this.context = context;
        this.listType = listType;
        this.list = list;
    }

    public ListFragment(Context context,List<IItems> list, int listType , Bundle bundle) {
        this.context = context;
        this.listType = listType;
        this.list = list;
        this.bundle = bundle;
    }

    //0
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        tabindex = getArguments().getInt(ARG_PAGE);
//        listType = getArguments().getInt(ARG_LIST);
//        idHeader = getArguments().getInt(ARG_HEADER);
//
//        id = getArguments().getInt("id");
//        term = getArguments().getString("term");
    }

    //1
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        fragmentRootView = inflater.inflate(R.layout.x_fragment_timeline, container, false);
        return fragmentRootView;
    }

    //2
    @Override
    public void onViewCreated(final View view, @Nullable final Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mRecyclerView                   = (RecyclerView) view.findViewById(R.id.recyclerView);
        mTextViewNoting                 = (TextView) view.findViewById(R.id.nothing_text);
        mSwipeRefreshLayout             = (SwipeRefreshLayout)  view.findViewById(R.id.swipeRefreshLayout);
        mProgressBar                    = (DilatingDotsProgressBar) view.findViewById(R.id.PBSjd);
        floatingActionButton            = (FloatingActionButton) view.findViewById(R.id.fab);
        floatingActionButtonList            = (FloatingActionButton) view.findViewById(R.id.fabList);
    }

    //3
    @Override
    public void onActivityCreated(@Nullable final Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        activity            = (TubelessActivity) getActivity();

        if (activity.getSystemBarTint() != null)
            config              = activity.getSystemBarTint().getConfig();

        mRoot               = (ViewGroup) activity.findViewById(R.id.CoordinatorLayout01);
        if (mRoot instanceof CoordinatorLayout) {
            mCoordinatorLayout = (CoordinatorLayout) mRoot;
        }

        createAdater();
        prepareFabButton(fragmentRootView,listType);



        //font 4
//        FontChangeCrawler fontChanger = new FontChangeCrawler(getContext().getAssets(), FONT_IRANSANS_MOBILE_NORMAL_TTF);
//        fontChanger.replaceFonts((ViewGroup) this.getView());
    }

    XAdapter xAdapter;
    private void createAdater( ) {
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(context);
        mRecyclerView.setLayoutManager(mLayoutManager);
        xAdapter = new XAdapter(
                listType,
                getContext(),
                mRoot,
                mRecyclerView,
                mLayoutManager,
                //scrollHelper != null ? scrollHelper.getToolbarHeight() : 0 ,
                //height,
                //hasAppBarLayout,
                mSwipeRefreshLayout,
                list,
                bundle);
        mRecyclerView.setAdapter(xAdapter);
//        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false));
    }


    public void refreshForAdmin(){
        xAdapter.notifyDataSetChanged();
    }

    public static List<IItems> createData() {
        List<IItems> list = new ArrayList<>();

        PictureItem item1 = new PictureItem();
        item1.setCarID("1002");
        item1.setCarName("فورد");
        List<Tag> ssssss = new ArrayList<>();
        Tag t1 = new Tag();
        t1.setID(1);
        t1.setTitle("car");
        ssssss.add(t1);
        item1.setListTag(ssssss);
        item1.setCarLogo("https://www.beytoote.com/images/stories/scientific/hhs2278.jpg");
        item1.setThumbnailFilePath("https://www.beytoote.com/images/stories/art/art570.jpg");
        list.add(item1);

        PictureItem item2 = new PictureItem();
        item2.setCarID("1003");
        item2.setCarName("مزدا");
        ssssss.add(t1);
        t1.setID(2);
        t1.setTitle("mazda");
        ssssss.add(t1);
        item2.setListTag(ssssss);
        item2.setCarLogo("https://www.beytoote.com/images/stories/scientific/hhs2282.jpg");
        item2.setThumbnailFilePath("https://cdn.bama.ir/uploads//BamaImages/News/41bb71bf-3bb8-48b4-90e1-017830545958/article_637132150003497227_thumb_960_542.jpg");
        list.add(item2);

        PictureItem item3 = new PictureItem();
        item3.setCarID("1004");
        item3.setCarName("بنز");
        item3.setCarLogo("https://www.beytoote.com/images/stories/scientific/hhs2293.jpg");
        item3.setThumbnailFilePath("https://www.beytoote.com/images/stories/art/art579.jpg");
        list.add(item3);

        PictureItem item4 = new PictureItem();
        item4.setCarID("1007");
        item4.setCarName("BMW");
        item4.setCarLogo("https://www.beytoote.com/images/stories/scientific/hhs2293.jpg");
        item4.setThumbnailFilePath("https://www.beytoote.com/images/stories/art/art581.jpg");
        list.add(item4);

        TextItem item5 = new TextItem(1, "The Flight", "Scott Masterson", "https://i.imgur.com/dyyP2iO.jpg");
        list.add(item5);
        return list;
    }

    private PictureItem[] createData0() {
        return new PictureItem[]{
                new PictureItem(1,"The Flight", "Scott Masterson", "https://i.imgur.com/dyyP2iO.jpg"),
                new PictureItem(2,"Room of Plates", "Ali Conners", "https://i.imgur.com/da6QIlR.jpg"),
                new PictureItem(3,"The Sleek Boot", "Sandra Adams", "https://i.imgur.com/YHoOJh4.jpg"),
                new PictureItem(4,"Night Hunting", "Janet Perkins", "https://i.imgur.com/3jxqrKP.jpg"),
                new PictureItem(5,"Rain and Coffee", "Peter Carlsson", "https://i.imgur.com/AZRynvM.jpg"),
                new PictureItem(6,"Ocean View", "Trevor Hansen", "https://i.imgur.com/IvhOJcw.jpg"),
                new PictureItem(7,"Lovers Of The Roof", "Britta Holt", "https://i.imgur.com/pxgI1b4.png"),
                new PictureItem(8,"Lessons from Delhi", "Mary Johnson", "https://i.imgur.com/oT1WYX9.jpg"),
                new PictureItem(9,"Mountaineers", "Abbey Christensen", "https://i.imgur.com/CLLDz.jpg"),
                new PictureItem(10,"Plains In The Night", "David Park", "https://i.imgur.com/7MrSvXE.jpg?1"),
                new PictureItem(11,"Dear Olivia", "Sylvia Sorensen", "https://i.imgur.com/3mkUuux.jpg"),
                new PictureItem(12,"Driving Lessons", "Halime Carver", "https://i.imgur.com/LzYAfFL.jpg"),
        };
    }

    public void scrollToTop() {
        mRecyclerView.smoothScrollToPosition(0);
    }

    private void prepareFabButton(View view, final int listType) {

//        mRecyclerView.setHasFixedSize(true);
//        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
//        mLayoutManager = new LinearLayoutManager(context);
//        mRecyclerView.setLayoutManager(mLayoutManager);



        if (floatingActionButton != null) {
            floatingActionButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //                if (idHeader == 16 || idHeader == 17 || idHeader == 18) {
                    //                    Intent intent = new Intent(context, NewYafteActivity.class);
                    //                    Bundle bundle = new Bundle();
                    //                    bundle.putInt(ContactUsActivity.Type,16);
                    //                    intent.putExtras(bundle);
                    //                    startActivity(intent);
                    //                    ((Activity)context).overridePendingTransition(R.anim.fadeout, R.anim.fadein);
                    //                }else {
                    //                    context.startActivity(new Intent(context, UploadPictureActivity.class));
                    //                    context.startActivity(new Intent(context, NewBlogActivity.class));
                    //                }


                    if (listType == TYPE_YAFTE) {
                        context.startActivity(new Intent(context, RegNewYafteActivity.class));
                    } else if (listType == TYPE_YADAK) {
                        context.startActivity(new Intent(context, RegNewYadakActivity.class));

                    } else if (listType == TYPE_IMAGE) {

                    } else if (listType == TYPE_POST_SEARCH_RESULT) {
                        context.startActivity(new Intent(context, RegNewYafteActivity.class));

                    } else if (listType == TYPE_BOURSE_TRAIN) {
//                        Toast.makeText(context, "ssss", Toast.LENGTH_LONG).show();
                        startActivity(new Intent(context, RegNewBlogActivity.class));
                    } else if (listType == TYPE_BOURSE_ANALIZE_All) {
//                        Toast.makeText(context, "ssss", Toast.LENGTH_LONG).show();
                        startActivity(new Intent(context, RegNewBlogActivity.class));
                    } else if (listType == TYPE_BOURSE_NEWS) {
//                        Toast.makeText(context, "ssss", Toast.LENGTH_LONG).show();
                        startActivity(new Intent(context, RegNewBlogActivity.class));
                    }
                }
            });

            floatingActionButtonList.setBackgroundResource(R.drawable.dot);
            if (floatingActionButtonList != null)
                floatingActionButtonList.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
//                    if (idHeader == 16 || idHeader == 17 || idHeader == 18) {
//                        Intent intent = new Intent(context, NewYafteActivity.class);
//                        Bundle bundle = new Bundle();
//                        bundle.putInt(ContactUsActivity.Type,16);
//                        intent.putExtras(bundle);
//                        startActivity(intent);
//                        ((Activity)context).overridePendingTransition(R.anim.fadeout, R.anim.fadein);
//                    }else {
//                        //                    context.startActivity(new Intent(context, UploadPictureActivity.class));
//                        context.startActivity(new Intent(context, NewBlogActivity.class));
//                    }


                    }
                });

//        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
//            @Override
//            public void onScrolled(RecyclerView recyclerView, int dx,int dy){
//                super.onScrolled(recyclerView, dx, dy);
//
//                if (dy > 0) {
//                    // Scroll Down
//                    if (floatingActionButton.isShown()) {
//                        floatingActionButton.hide();
//                    }
//                }
//                else if (dy < 0) {
//                    // Scroll Up
//                    if (!floatingActionButton.isShown()) {
//                        floatingActionButton.show();
//                    }
//                }
//            }
//        });

        }
    }

}
