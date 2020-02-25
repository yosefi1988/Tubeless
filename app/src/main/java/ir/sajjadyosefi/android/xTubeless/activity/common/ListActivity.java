package ir.sajjadyosefi.android.xTubeless.activity.common;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.readystatesoftware.systembartint.SystemBarTintManager;
import com.zl.reik.dilatingdotsprogressbar.DilatingDotsProgressBar;

import ir.sajjadyosefi.android.xTubeless.Adapter.XAdapter;
import ir.sajjadyosefi.android.xTubeless.Fragment.ListFragment;
import ir.sajjadyosefi.android.xTubeless.R;
import ir.sajjadyosefi.android.xTubeless.activity.TubelessActivity;
import ir.sajjadyosefi.android.xTubeless.activity.TubelessTransparentStatusBarActivity;
import ir.sajjadyosefi.android.xTubeless.activity.register.RegNewYadakActivity;
import ir.sajjadyosefi.android.xTubeless.activity.register.RegNewYafteActivity;
import it.sephiroth.android.library.bottomnavigation.BottomNavigation;

import static ir.sajjadyosefi.android.xTubeless.Adapter.FirstFragmentsAdapter.TYPE_IMAGE;
import static ir.sajjadyosefi.android.xTubeless.Adapter.FirstFragmentsAdapter.TYPE_POST_SEARCH_RESULT;
import static ir.sajjadyosefi.android.xTubeless.Adapter.FirstFragmentsAdapter.TYPE_YADAK;
import static ir.sajjadyosefi.android.xTubeless.Adapter.FirstFragmentsAdapter.TYPE_YAFTE;


public class ListActivity extends Activity {

    private Context mContext;
    private int type = 0 ;
    private Toolbar toolbar;
    //private
    private RecyclerView mRecyclerView;
    private CoordinatorLayout mCoordinatorLayout;
    private View fragmentRootView;
    private Activity activity;
    private FloatingActionButton floatingActionButton ,floatingActionButtonList;
    private LinearLayoutManager mLayoutManager;
    private TextView mTextViewNoting;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private DilatingDotsProgressBar mProgressBar;

    //public
    private SystemBarTintManager.SystemBarConfig config;
    private ViewGroup mRoot;

    //singletone instance
    private static ListActivity containerActivity;

    //singletone
    public synchronized static ListActivity getInstance(){
        if (containerActivity == null){
            containerActivity = new ListActivity();
        }
        return containerActivity;
    }

    //default constractor
    public ListActivity() { }


    public synchronized static Intent getIntent(Context context) {
        return getIntent(context,null);
    }

    public synchronized static Intent getIntent(Context context, Bundle bundle) {
        bundle.putString("item1","value1");
        Intent intent = new Intent(context, ListActivity.class);
        intent.putExtras(bundle);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.x_fragment_timeline);
        mContext = this;
        type = getIntent().getIntExtra("type",0);

        mRecyclerView                   = (RecyclerView) findViewById(R.id.recyclerView);
        mTextViewNoting                 = (TextView) findViewById(R.id.nothing_text);
        mSwipeRefreshLayout             = (SwipeRefreshLayout)  findViewById(R.id.swipeRefreshLayout);
        mProgressBar                    = (DilatingDotsProgressBar) findViewById(R.id.PBSjd);
        floatingActionButton            = (FloatingActionButton) findViewById(R.id.fab);
        floatingActionButtonList            = (FloatingActionButton) findViewById(R.id.fabList);
        int a = 5 ;
        a++;


        activity            = (Activity)this;
//        if (activity.getSystemBarTint() != null)
//            config              = activity.getSystemBarTint().getConfig();

        mRoot               = (ViewGroup) activity.findViewById(R.id.CoordinatorLayout01);
//        if (mRoot instanceof CoordinatorLayout) {
//            mCoordinatorLayout = (CoordinatorLayout) mRoot;
//        }

        createAdater(this);
        loadTimeline(this,type);

    }


    private void createAdater(Context context) {
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(context);
        mRecyclerView.setLayoutManager(mLayoutManager);

        mRecyclerView.setAdapter(
                new XAdapter(
                        type,
                        context,
                        mRoot,
                        mRecyclerView,
                        mLayoutManager,
                        //scrollHelper != null ? scrollHelper.getToolbarHeight() : 0 ,
                        //height,
                        //hasAppBarLayout,
                        mSwipeRefreshLayout,
                        null));

//        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false));
    }


    private void loadTimeline(Context context, final int listType) {

        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mLayoutManager = new LinearLayoutManager(context);
        mRecyclerView.setLayoutManager(mLayoutManager);



        if (floatingActionButton != null)
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



                    if (listType == TYPE_YAFTE){
                        context.startActivity(new Intent(context, RegNewYafteActivity.class));
                    }else if (listType == TYPE_YADAK){
                        context.startActivity(new Intent(context, RegNewYadakActivity.class));

                    }else if (listType == TYPE_IMAGE){

                    }
                }
            });

        if (floatingActionButtonList != null)
            floatingActionButtonList.setOnClickListener(new View.OnClickListener() {
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
                    ////                    context.startActivity(new Intent(context, UploadPictureActivity.class));
                    //                    context.startActivity(new Intent(context, NewBlogActivity.class));
                    //                }
                    //Toast.makeText(getActivity(),"commented onClick 2",Toast.LENGTH_SHORT).show();

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