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
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.gson.Gson;

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
public class BourseDataFragment extends Fragment {
    public static Context context;
    public static final String ARG_PAGE = "ARG_PAGE";

    RadioButton radioButton1,radioButton2,radioButton3;


    public BourseDataFragment newInstance(Context context) {
        this.context = context ;
        Bundle args = new Bundle();
//        args.putInt(ARG_PAGE, page);
//        args.putInt(ARG_LIST, list);
//        args.putInt(ARG_HEADER, headerId);
        BourseDataFragment fragment = new BourseDataFragment(context);
        fragment.setArguments(args);
        //this.values = context.getSharedPreferences(Statics.MAHAN, 0);
        return fragment;
    }

    public BourseDataFragment(Context context) {
        this.context = context;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.financial_account_limited_fragment, container, false);

        radioButton1 = view.findViewById(R.id.radioButton1);
        radioButton2 = view.findViewById(R.id.radioButton2);
        radioButton3 = view.findViewById(R.id.radioButton3);

        return view;
    }

}