package ir.sajjadyosefi.android.xTubeless.activity.common;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.readystatesoftware.systembartint.SystemBarTintManager;
import com.zarinpal.ewallets.purchase.OnCallbackVerificationPaymentListener;
import com.zarinpal.ewallets.purchase.PaymentRequest;
import com.zarinpal.ewallets.purchase.ZarinPal;

import java.util.ArrayList;
import java.util.List;

import ir.sajjadyosefi.android.xTubeless.Fragment.ListFragment;
import ir.sajjadyosefi.android.xTubeless.R;
import ir.sajjadyosefi.android.xTubeless.activity.activities.TubelessActivity;
import ir.sajjadyosefi.android.xTubeless.activity.activities.TubelessTransparentStatusBarActivity;
import ir.sajjadyosefi.android.xTubeless.bussines.police.fragment.KartesekhtFragment;
import ir.sajjadyosefi.android.xTubeless.bussines.post.fragment.SearchByNameFragment;
import ir.sajjadyosefi.android.xTubeless.classes.StaticValue;
import ir.sajjadyosefi.android.xTubeless.classes.model.post.IItems;
import ir.sajjadyosefi.android.xTubeless.utility.DialogUtil;
import it.sephiroth.android.library.bottomnavigation.BottomNavigation;

import static ir.sajjadyosefi.android.xTubeless.Adapter.FirstFragmentsAdapter.TYPE_BOURSE_PLANE;
import static ir.sajjadyosefi.android.xTubeless.Adapter.FirstFragmentsAdapter.TYPE_COMMENTS;
import static ir.sajjadyosefi.android.xTubeless.Adapter.FirstFragmentsAdapter.TYPE_KSOKHT;
import static ir.sajjadyosefi.android.xTubeless.Adapter.FirstFragmentsAdapter.TYPE_LIST;
import static ir.sajjadyosefi.android.xTubeless.Adapter.FirstFragmentsAdapter.TYPE_LIST_CATEGORY;
import static ir.sajjadyosefi.android.xTubeless.Adapter.FirstFragmentsAdapter.TYPE_POST_SEARCH_RESULT;
import static ir.sajjadyosefi.android.xTubeless.Adapter.FirstFragmentsAdapter.TYPE_SEARCH_POST_BY_NAME;

import static ir.sajjadyosefi.android.xTubeless.Adapter.FirstFragmentsAdapter.TYPE_YADAK;
import static ir.sajjadyosefi.android.xTubeless.Fragment.FinancialAccountLimitFragment.READ_RULE_AND_PAY;
import static ir.sajjadyosefi.android.xTubeless.activity.MainActivity.payType;
import static ir.sajjadyosefi.android.xTubeless.bussines.police.fragment.KartesekhtFragment.cancelByBackbuttonPressed;

import ir.sajjadyosefi.android.xTubeless.Fragment.FinancialAccountLimitFragment;


public class ContainerActivity extends TubelessActivity {

    private Context mContext;
    private static int type = 0 ;
    private List<IItems> list;
    private Toolbar toolbar;


    //singletone instance
    private static ContainerActivity containerActivity;

    //singletone
    public synchronized static ContainerActivity getInstance(){
        if (containerActivity == null){
            containerActivity = new ContainerActivity();
        }
        return containerActivity;
    }

    //default constractor
    public ContainerActivity() { }


    public synchronized static Intent getIntent(Context context) {
        return getIntent(context,null);
    }

    public synchronized static Intent getIntent(Context context, Bundle bundle) {
        bundle.putString("item1","value1");
        Intent intent = new Intent(context,ContainerActivity.class);
        intent.putExtras(bundle);
        return intent;
    }


    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

    }

    public static final int READ_BLOG_COMMENTS = 1042;


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (type == TYPE_COMMENTS){
            if (resultCode == Activity.RESULT_OK) {

                ((TubelessActivity)mContext).progressDialog.show();

                FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                bundle.putInt("blogId", getIntent().getIntExtra("blogId", 0));
                list = new ArrayList<>();
                ft.replace(R.id.include, new ListFragment(this, list, type, bundle));
                ft.addToBackStack(ft.getClass().getName());
                ft.commit();
            }
        }

        if (requestCode == READ_RULE_AND_PAY) {
            if (resultCode == Activity.RESULT_OK) {
//                int payType = data.getIntExtra("payType" , 0);
                setResult(Activity.RESULT_OK, data);
                finish();
            }else {
                setResult(Activity.RESULT_CANCELED);
                finish();
            }
        }
    }

    Bundle bundle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.x_activity_container);
        mContext = this;
        if(type == 0){
            type = getIntent().getIntExtra("type", 0);
        }else if (getIntent() != null && getIntent().hasExtra("type") && type != getIntent().getIntExtra("type", 0)){
            type = getIntent().getIntExtra("type", 0);
        }


        if (type != 0) {
            list = (List<IItems>) getIntent().getSerializableExtra("LIST");
            if (type == TYPE_POST_SEARCH_RESULT) {
                //            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                //            FragmentAbouteUs fragmentDemo = FragmentAbouteUs.newInstance();
                //            ft.replace(R.id.output, fragmentDemo);
                //            ft.commit();

                FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.include, new ListFragment(this, list, TYPE_POST_SEARCH_RESULT));
                //            ft.replace(R.id.include, new BlankFragment(getContext()));
                ft.commit();
                //            ListFragment2 newFragment = new ListFragment2(getContext(),2);
                //            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                //            transaction.replace(R.id.include, newFragment);
                //            transaction.addToBackStack(null);
                //            transaction.commit();

            } else if (type == TYPE_SEARCH_POST_BY_NAME) {
                FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.include, new SearchByNameFragment());
                ft.commit();
            } else if (type == TYPE_KSOKHT) {
                FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.include, new KartesekhtFragment(), "KartesekhtFragment");
                ft.commit();
            } else if (type == TYPE_LIST) {
                ((TubelessActivity)mContext).progressDialog.show();

                FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
//                ft.replace(R.id.include, new ListFragment(this,list,TYPE_LIST));
                ft.replace(R.id.include, new ListFragment(this,list,TYPE_LIST_CATEGORY,0));
                ft.commit();


            } else if (type == TYPE_BOURSE_PLANE) {
                FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.include, new FinancialAccountLimitFragment(this));
                ft.commit();

            } else if (type == TYPE_COMMENTS) {

                FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                bundle = new Bundle();
                bundle.putInt("blogId", getIntent().getIntExtra("blogId", 0));
                ft.replace(R.id.include, new ListFragment(this, list, type, bundle));
                ft.commit();

            } else if (type == 1) {

                FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                //            FragmentNotifications fragmentDemo = FragmentNotifications.newInstance(1,1);
                //            ft.replace(R.id.output, fragmentDemo);
                ft.commit();

            } else if (type == 2) {
                FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                //            FragmentProfile fragmentDemo = FragmentProfile.newInstance(getContext() ,1,1);
                //            ft.replace(R.id.output, fragmentDemo);
                ft.commit();
                //        }else {
                //            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                //            FragmentNotifications fragmentDemo = FragmentNotifications.newInstance(1,1);
                //            ft.replace(R.id.output, fragmentDemo);
                //            ft.commit();
            } else {
                // Begin the transaction
                FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                // Replace the contents of the container with the new fragment
                ft.replace(R.id.include, prepareFragment());
                // or ft.add(R.id.your_placeholder, new FooFragment());
                // Complete the changes added above
                ft.commit();
            }
        }


        Uri data2 = getIntent().getData();
        ZarinPal.getPurchase(getContext()).verificationPayment(data2, new OnCallbackVerificationPaymentListener() {
            @Override
            public void onCallbackResultVerificationPayment(boolean isPaymentSuccess, String refID, PaymentRequest paymentRequest) {
                if(isPaymentSuccess){
//                    //Toast.makeText(getContext(),"pay success" ,Toast.LENGTH_LONG).show();
                    DialogUtil.showLoadingDialog(getContext());

                    if(payType == 100){
                        //کارت سوخت
                        KartesekhtFragment myFragment = (KartesekhtFragment)getSupportFragmentManager().findFragmentByTag("KartesekhtFragment");
                        if (myFragment != null && myFragment.isVisible()) {
                            // add your code here
                            if (myFragment instanceof KartesekhtFragment){
                                ((KartesekhtFragment)myFragment).callService(getRootActivity());
                            }
                        }

                        cancelByBackbuttonPressed = false;
                        int a = 5 ;
                        a++;
                    }


                    if(payType != 100) {

                    }

                }else {
                    //not ok
//                    show message refID
//                    Toast.makeText(getContext(),"not ok " ,Toast.LENGTH_LONG).show();

                    cancelByBackbuttonPressed = true;
                }

                final Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        DialogUtil.hideLoading();
                    }
                },2000);

            }
        });

        int a = 5 ;
        a++;


        KartesekhtFragment myFragment = (KartesekhtFragment)getSupportFragmentManager().findFragmentByTag("KartesekhtFragment");
        if (myFragment != null && myFragment.isVisible()) {
            // add your code here
            if (myFragment instanceof KartesekhtFragment){
                ((KartesekhtFragment)myFragment).callService(getRootActivity());
            }
        }
    }

    @Override
    public SystemBarTintManager getSystemBarTint() {
        return null;
    }

    @Override
    public boolean hasTranslucentNavigation() {
        return false;
    }

    @Override
    public boolean hasTranslucentStatusBar() {
        return false;
    }

    @Override
    public BottomNavigation getBottomNavigation() {
        return null;
    }

    @Override
    public int getNavigationBarHeight() {
        return 0;
    }

    @Override
    public boolean hasManagedToolbarScroll() {
        return false;
    }

    @Override
    public boolean hasAppBarLayout() {
        return false;
    }

    @Override
    public Toolbar getToolbar() {
        return null;
    }

//    @Override
//    public SystemBarTintManager getSystemBarTint() {
//        return null;
//    }
//
//    @Override
//    public boolean hasTranslucentNavigation() {
//        return false;
//    }
//
//    @Override
//    public boolean hasTranslucentStatusBar() {
//        return false;
//    }
//
//    @Override
//    public BottomNavigation getBottomNavigation() {
//        return null;
//    }
//
//    @Override
//    public int getNavigationBarHeight() {
//        return 0;
//    }
//
//    @Override
//    public boolean hasManagedToolbarScroll() {
//        return false;
//    }
//
//    @Override
//    public boolean hasAppBarLayout() {
//        return false;
//    }
//
//    @Override
//    public Toolbar getToolbar() {
//        return null;
//    }


    Fragment prepareFragment() {

        int id = getIntent().getExtras().getInt("id");
        String term = getIntent().getExtras().getString("term");

        Bundle args = new Bundle();
        args.putString("term", term);
        args.putInt("id", id);
//        FragmentTimelineMinor fragment = new FragmentTimelineMinor();
//        fragment.setArguments(args);
        //this.values = context.getSharedPreferences(Statics.MAHAN, 0);
//        return fragment;
        return null;
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        type = 0;
    }
}