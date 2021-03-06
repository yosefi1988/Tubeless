package ir.sajjadyosefi.android.xTubeless.Fragment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

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

import ir.sajjadyosefi.android.xTubeless.Adapter.recyclerView.XAdapterNerkhRoz;
import ir.sajjadyosefi.android.xTubeless.R;
import ir.sajjadyosefi.android.xTubeless.activity.activities.TubelessActivity;
import ir.sajjadyosefi.android.xTubeless.activity.common.ContainerActivity;
import ir.sajjadyosefi.android.xTubeless.activity.register.RegNewBlogActivity;
import ir.sajjadyosefi.android.xTubeless.activity.register.RegNewCommentActivity;
import ir.sajjadyosefi.android.xTubeless.activity.register.RegNewYadakActivity;
import ir.sajjadyosefi.android.xTubeless.activity.register.RegNewYafteActivity;
import ir.sajjadyosefi.android.xTubeless.Adapter.XAdapter;
import ir.sajjadyosefi.android.xTubeless.classes.StaticValue;
import ir.sajjadyosefi.android.xTubeless.classes.model.Tag;


import ir.sajjadyosefi.android.xTubeless.classes.model.bourseState.BourseState;
import ir.sajjadyosefi.android.xTubeless.classes.model.filter.CategoryFiltersNode;
import ir.sajjadyosefi.android.xTubeless.classes.model.post.IItems;
import ir.sajjadyosefi.android.xTubeless.classes.model.post.PictureItem;
import ir.sajjadyosefi.android.xTubeless.classes.model.post.TextItem;


import static ir.sajjadyosefi.android.xTubeless.Adapter.FirstFragmentsAdapter.TYPE_BOURSE_ANALIZE_All;
import static ir.sajjadyosefi.android.xTubeless.Adapter.FirstFragmentsAdapter.TYPE_BOURSE_ANALIZE_Old;
import static ir.sajjadyosefi.android.xTubeless.Adapter.FirstFragmentsAdapter.TYPE_BOURSE_NEWS;
import static ir.sajjadyosefi.android.xTubeless.Adapter.FirstFragmentsAdapter.TYPE_BOURSE_PLANE;
import static ir.sajjadyosefi.android.xTubeless.Adapter.FirstFragmentsAdapter.TYPE_BOURSE_TRAIN;
import static ir.sajjadyosefi.android.xTubeless.Adapter.FirstFragmentsAdapter.TYPE_COMMENTS;
import static ir.sajjadyosefi.android.xTubeless.Adapter.FirstFragmentsAdapter.TYPE_IMAGE;
import static ir.sajjadyosefi.android.xTubeless.Adapter.FirstFragmentsAdapter.TYPE_LIST_CATEGORIES_DATA;
import static ir.sajjadyosefi.android.xTubeless.Adapter.FirstFragmentsAdapter.TYPE_LIST_CATEGORY;
import static ir.sajjadyosefi.android.xTubeless.Adapter.FirstFragmentsAdapter.TYPE_SELECT_CATEGORY;
import static ir.sajjadyosefi.android.xTubeless.Adapter.FirstFragmentsAdapter.TYPE_POST_SEARCH_RESULT;
import static ir.sajjadyosefi.android.xTubeless.Adapter.FirstFragmentsAdapter.TYPE_YADAK;
import static ir.sajjadyosefi.android.xTubeless.Adapter.FirstFragmentsAdapter.TYPE_YAFTE;
import static ir.sajjadyosefi.android.xTubeless.Fragment.FinancialAccountLimitFragment.READ_RULE_AND_PAY;
import static ir.sajjadyosefi.android.xTubeless.activity.MainActivity.checkResult;
import static ir.sajjadyosefi.android.xTubeless.activity.register.RegNewCommentActivity.LOGIN_REQUEST_NEW_COMMENT;


public class ListFragment extends Fragment  {

    private CategoryFiltersNode categoryFiltersNode;
    //private
    private RecyclerView mRecyclerView;
    private CoordinatorLayout mCoordinatorLayout;
    private View fragmentRootView;
    private Activity activity;

    private int listType;

    private Bundle bundle;
    public List<IItems> list;
    private int scrolledPos = 0;
    private FloatingActionButton floatingActionButton ,floatingActionButtonList;

    private LinearLayoutManager mLayoutManager;
    private TextView mTextViewNoting;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private LinearLayout bourseExpire;
    private Button countinueButton;
    private DilatingDotsProgressBar mProgressBar;

    //public
    public int getListType() {
        return listType;
    }
    private SystemBarConfig config;
    private ViewGroup mRoot;

    //static
    public static final String      ARG_PAGE = "ARG_PAGE";
    public static final String      ARG_LIST = "ARG_LIST";
    public static final String      ARG_HEADER = "ARG_HEADER";
    public static Context           context;

    public ListFragment(Context context, int listType) {
        this.context = context;
        this.listType = listType;
        constractorInit();
    }

    public ListFragment(Context context, List<IItems> list, int listType, CategoryFiltersNode head) {
        this.context = context;
        this.listType = listType;
        this.categoryFiltersNode = head;
        this.list = list;
    }

    public static ListFragment newInstance(Context context, int page, int list, int headerId) {
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



    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
//        savedInstanceState.putString(TITLE, "Gladiator");
//        savedInstanceState.putDouble(RATING, 8.5);
//        savedInstanceState.putInt(YEAR, 2000);

//        mRecyclerView.computeHorizontalScrollOffset();
//        mRecyclerView.computeVerticalScrollOffset();

    }


    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
//        title = savedInstanceState.getString(TITLE);
//        rating = savedInstanceState.getDouble(RATING);
//        year = savedInstanceState.getInt(YEAR);
    }


    private void constractorInit() {

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
        if (fragmentRootView == null)
            fragmentRootView = inflater.inflate(R.layout.x_fragment_timeline, container, false);

        return fragmentRootView;
    }

    //2
    @Override
    public void onViewCreated(final View view, @Nullable final Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if (mRecyclerView == null) {
            mRecyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
            mTextViewNoting = (TextView) view.findViewById(R.id.nothing_text);
            mSwipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipeRefreshLayout);
            mProgressBar = (DilatingDotsProgressBar) view.findViewById(R.id.PBSjd);
            floatingActionButton = (FloatingActionButton) view.findViewById(R.id.fab);
            floatingActionButtonList = (FloatingActionButton) view.findViewById(R.id.fabList);

            if (listType == TYPE_BOURSE_ANALIZE_All || listType == TYPE_BOURSE_ANALIZE_Old) {
                bourseExpire = (LinearLayout) view.findViewById(R.id.bourseExpire);
                countinueButton = (Button) view.findViewById(R.id.countinueButton);

                if (!checkResult(context, StaticValue.configuration)) {

                    //سرور دستور داده که رایگا ن باشه
                    bourseExpire.setVisibility(View.GONE);
                } else {

                    if (StaticValue.bourseState.totalPayedValue > 0) {
                        if (BourseState.CheckDateIsValid(StaticValue.bourseState.endDate, StaticValue.configuration.getResponseStatus().getDate())) {
                            //پرداخت کرده و منقضی هم نشده
                            bourseExpire.setVisibility(View.GONE);
                        } else {
                            //پرداخت کرده و منقضی شده
                            bourseExpire.setVisibility(View.VISIBLE);

                            countinueButton.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    Bundle bundle = new Bundle();
                                    bundle.putInt("type", TYPE_BOURSE_PLANE);
                                    getActivity().startActivityForResult(ContainerActivity.getIntent(getContext(), bundle), READ_RULE_AND_PAY);
                                }
                            });
                        }
                    } else {
                        //هیچ پرداختی قبلا انجام نداده است
                    }
                }
            }else if (listType == TYPE_SELECT_CATEGORY || listType == TYPE_SELECT_CATEGORY || listType == TYPE_LIST_CATEGORIES_DATA) {
                ((TubelessActivity)context).progressDialog.show();
            }
        }
    }

    //3
    @Override
    public void onActivityCreated(@Nullable final Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

//        activity            = (TubelessActivity) getActivity();
        if (activity == null)
            activity            = (Activity) getActivity();

//        if (activity.getSystemBarTint() != null)
//            config              = activity.getSystemBarTint().getConfig();

        if (mRoot == null) {
            mRoot = (ViewGroup) activity.findViewById(R.id.CoordinatorLayout01);
            if (mRoot instanceof CoordinatorLayout) {
                mCoordinatorLayout = (CoordinatorLayout) mRoot;
            }
        }

        if (mLayoutManager == null)
            createAdater();

        mRecyclerView.getAdapter();
        prepareFabButton(fragmentRootView,listType);

        //font 4
//        FontChangeCrawler fontChanger = new FontChangeCrawler(getContext().getAssets(), FONT_IRANSANS_MOBILE_NORMAL_TTF);
//        fontChanger.replaceFonts((ViewGroup) this.getView());
    }

    XAdapter xAdapter;
    XAdapterNerkhRoz xAdapterNerkhRoz;
    private void createAdater( ) {
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(context);
        mRecyclerView.setLayoutManager(mLayoutManager);

        if(listType < 200) {
            xAdapter = new XAdapter(
                    listType,
                    categoryFiltersNode,
                    getContext(),
                    mRoot,
                    mRecyclerView,
                    mLayoutManager,
                    //scrollHelper != null ? scrollHelper.getToolbarHeight() : 0 ,
                    //height,
                    //hasAppBarLayout,
                    mSwipeRefreshLayout,
                    this,
                    bundle );
            mRecyclerView.setAdapter(xAdapter);
//        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false));
        }else {
//            xAdapterNerkhRoz = new XAdapterNerkhRoz(
//                    listType,
//                    catid,
//                    getContext(),
//                    mRoot,
//                    mRecyclerView,
//                    mLayoutManager,
//                    //scrollHelper != null ? scrollHelper.getToolbarHeight() : 0 ,
//                    //height,
//                    //hasAppBarLayout,
//                    mSwipeRefreshLayout,
//                    this,
//                    bundle);
            mRecyclerView.setAdapter(xAdapterNerkhRoz);
        }
    }


    public void refreshForAdmin(){
        if (xAdapter != null)
            xAdapter.notifyDataSetChanged();

        if (xAdapterNerkhRoz != null)
            xAdapterNerkhRoz.notifyDataSetChanged();
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
                    } else if (listType == TYPE_BOURSE_ANALIZE_All || listType == TYPE_BOURSE_ANALIZE_Old) {
//                        Toast.makeText(context, "ssss", Toast.LENGTH_LONG).show();
                        startActivity(new Intent(context, RegNewBlogActivity.class));
                    } else if (listType == TYPE_BOURSE_NEWS) {
//                        Toast.makeText(context, "ssss", Toast.LENGTH_LONG).show();
                        startActivity(new Intent(context, RegNewBlogActivity.class));
                    } else if (listType == TYPE_COMMENTS) {
//                        int blogId = bundle.getInt("blogId");
//xxxxxxxxxxxxxxxx
//                        Bundle bundle = new Bundle();
//                        bundle.putString("X" , "X");
                        getActivity().startActivityForResult(RegNewCommentActivity.getIntent(getContext(),bundle), LOGIN_REQUEST_NEW_COMMENT);
                    } else if (listType == TYPE_SELECT_CATEGORY || listType == TYPE_LIST_CATEGORY) {
                        Toast.makeText(context, " cat id is : " + categoryFiltersNode.getCatId(), Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(context, "not set", Toast.LENGTH_LONG).show();
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
