package ir.sajjadyosefi.android.xTubeless.Fragment;

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

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.gson.Gson;

import ir.sajjadyosefi.android.xTubeless.Adapter.EndlessList_AdapterFile;
import ir.sajjadyosefi.android.xTubeless.Global;
import ir.sajjadyosefi.android.xTubeless.R;
import ir.sajjadyosefi.android.xTubeless.activity.common.WebViewActivity;
import ir.sajjadyosefi.android.xTubeless.classes.StaticValue;
import ir.sajjadyosefi.android.xTubeless.classes.model.user.User;
import ir.sajjadyosefi.android.xTubeless.utility.DialogUtil;

import static ir.sajjadyosefi.accountauthenticator.activity.AuthenticatorActivity.PARAM_USER;
import static ir.sajjadyosefi.android.xTubeless.activity.MainActivity.checkResult;

//import com.astuetz.PagerSlidingTabStripDefault;

/**
 * Created by sajjad on 08/14/2016.
 */
// In this case, the fragment displays simple text based on the page
@SuppressLint("ValidFragment")
public class FinancialAccountLimitFragment extends Fragment {
    public static Context context;
    public static int mIndex;

    public static final String ARG_PAGE = "ARG_PAGE";
    public static int READ_RULE_AND_PAY = 5200;

    public FinancialAccountLimitFragment newInstance(Context context) {
        this.context = context ;
        Bundle args = new Bundle();
//        args.putInt(ARG_PAGE, page);
//        args.putInt(ARG_LIST, list);
//        args.putInt(ARG_HEADER, headerId);
        FinancialAccountLimitFragment fragment = new FinancialAccountLimitFragment(context);
        fragment.setArguments(args);
        //this.values = context.getSharedPreferences(Statics.MAHAN, 0);
        return fragment;
    }

    public FinancialAccountLimitFragment(Context context) {
        this.context = context;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == READ_RULE_AND_PAY) {
            if (resultCode == Activity.RESULT_OK) {

                if (requestCode == READ_RULE_AND_PAY){
                    if (data.hasExtra(PARAM_USER)){
                        Gson gson = new Gson();
//                        ir.sajjadyosefi.accountauthenticator.model.User userLib = gson.fromJson(data.getExtras().getString(PARAM_USER),ir.sajjadyosefi.accountauthenticator.model.User.class);
                        User user = gson.fromJson(data.getExtras().getString(PARAM_USER),User.class);

//                        if(savedToDataBase(user)){
//                            if (Global.user != null && Global.user.isAdmin()) {
//                                firstFragmentsAdapter.notifyList();
//                                updatedrawableMenuItems();
//                            }
//                        }
                    }
                }
            }
        }
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.financial_account_limited_fragment, container, false);

        if (checkResult(getContext(), StaticValue.configuration)) {
            ((Button) (view.findViewById(R.id.button_pay))).setText(R.string.pardakht_rule);
            ((TextView) (view.findViewById(R.id.txtPrice))).setText(
                    ((TextView) (view.findViewById(R.id.txtPrice))).getText() + " " +
                            StaticValue.configuration.getConfiguration().getVip1Month()
                            + " " +
                     context.getString(R.string.rial)
            );

        }else {
            ((TextView) (view.findViewById(R.id.txtPriceComment))).setText(context.getString(R.string.txtPriceComment));
            ((TextView) (view.findViewById(R.id.txtPrice))).setVisibility(View.INVISIBLE);
            ((Button) (view.findViewById(R.id.button_pay))).setText(R.string.pardakht_rule2);
        }

        (view.findViewById(R.id.button_pay)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogUtil.showLoadingDialog(context);

                Bundle bundle = new Bundle();
                bundle.putString("WebType" , "rule");
                bundle.putBoolean("payButton" , true);
                getActivity().startActivityForResult(WebViewActivity.getIntent(getContext(),bundle), READ_RULE_AND_PAY);

            }
        });
        return view;
    }

}