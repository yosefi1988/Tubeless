package ir.sajjadyosefi.android.tubeless.classes;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
//import android.widget.AutoCompleteTextView;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;

import ir.sajjadyosefi.android.tubeless.R;


/**
 * Created by sajjad
 */
public class GetUsersWindow extends AlertDialog {
    private Context context;
//    public boolean save;
    CheckBox checkBox;
    RelativeLayout rl_filter;
    public static boolean Writeable = false ;
    View dialoglayout;
    private int Width;
    ImageView iv_Close ;

    //ChipsMultiAutoCompleteTextview mu;

    public GetUsersWindow(final Context context, final Boolean save, Boolean filter, String Message, final boolean CategoryAvalable) {
        //save = close by save or without save
        //filter = firends Or global
        //message
        //CategoryAvalable
        super(context);
        this.context = context;
//        this.save = save;


        //First init
        LayoutInflater inflater = ((Activity) context).getLayoutInflater();
        dialoglayout = inflater.inflate(R.layout.dialog_get_users,null);
        this.setView(dialoglayout);

        //this.setCancelable(true);

//        //init
//        final Button btn_positive = (Button) dialoglayout.findViewById(R.id.b_positive);
//        iv_Close = (ImageView) dialoglayout.findViewById(R.id.iv_close);

//        TextView Tv_content = (TextView) dialoglayout.findViewById(R.id.tv_content);
////        final GridView gridView = (GridView) dialoglayout.findViewById(R.id.gv_cat);
////        checkBox = (CheckBox) dialoglayout.findViewById(R.id.checkBox);
////        rl_filter = (RelativeLayout) dialoglayout.findViewById(R.id.rl_filter);
//        final AutoCompleteTextView sp_userName = (AutoCompleteTextView) dialoglayout.findViewById(R.id.button2);
        final TextView Tv_Title = (TextView) dialoglayout.findViewById(R.id.tv_titleGetUser);


        String[] list = new String[] {
                "سجاد", "امیر", "آرش", "محسن", "محمد"
        };
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(context,android.R.layout.simple_dropdown_item_1line, list);
//        sp_userName.setAdapter(adapter);


//        mu = (ChipsMultiAutoCompleteTextview)dialoglayout.findViewById(R.id.multiAutoCompleteTextView1sjd);
//        String[] item = context.getResources().getStringArray(R.array.country);
//        mu.setAdapter(new ArrayAdapter<String>(context,android.R.layout.simple_dropdown_item_1line, item));
//        mu.setTokenizer(new MultiAutoCompleteTextView.CommaTokenizer());

//
////        gridView.setOnScrollListener(new AbsListView.OnScrollListener() {
////            @Override
////            public void onScrollStateChanged(AbsListView view, int scrollState) {
////
////                if (Tv_Title.getVisibility() != View.VISIBLE) {
////                    InputMethodManager mgr = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
////                    mgr.hideSoftInputFromWindow(et_category.getWindowToken(), 0);
////                }
////            }
////
////            @Override
////            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
////
////            }
////        });
//
//
//        TextWatcher mTextEditorWatcher = new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//
//            }
//
//            @Override
//            public void onTextChanged(CharSequence s, int start, int before, int count) {
////                if (et_category.getText().length() < 3) {
////                    if (et_category.getText().length() == 0) {
////                        //load all
////                        loadAllCategories(context, gridView);
////                    } else
////                        MyToastV2.makeTextAndShow(context, R.string.tooShortTeext, Toast.LENGTH_SHORT);
////                } else {
////                    String url = WebConf.URL_GET_SEARCHCATEGORY + et_category.getText();
////
////                    CategoryListEndlessAdapter adapter = null;
////                    if(dialoglayout != null)
////                        adapter = new CategoryListEndlessAdapter(context,Width, url, gridView,true,save);
////
////                    if(adapter != null)
////                        gridView.setAdapter(adapter);
////                    adapter.notifyDataSetChanged();
////                }
//            }
//
//            @Override
//            public void afterTextChanged(Editable s) {
//
//            }
//        };
//        et_category.addTextChangedListener(mTextEditorWatcher);
//
//
////        Tv_Title.setText(R.string.category_msg_title);
//
//
////        if(save)
////            btn_positive.setText(R.string.category_msg_save);
////        else
////            btn_positive.setText(R.string.category_msg_close);
//
//
////        if(ConfigInfo.isHomeDefaultOnGlobal(context))
////            checkBox.setChecked(false);
////        else
////            checkBox.setChecked(true);
//
//
//        iv_Close.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                btn_positive.performClick();
//            }
//        });
//        btn_positive.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
////                if ((CategoryAvalable && MyApplication.USER_CATEGORIES_Selected.size() >= 1 && save)||(!CategoryAvalable)) {
////                        if (save) {
////                            //save User Cats
////                            if (CategoryAvalable) {
////                                SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(context);
////                                SharedPreferences.Editor editor = sharedPrefs.edit();
////                                Gson gson = new Gson();
////                                String json = gson.toJson(MyApplication.USER_CATEGORIES_Selected);
////                                editor.putString("USER_CATEGORIES", json);
////                                editor.commit();
////                            }
////                        }
////
////
////                        if (checkBox.isChecked()) {
////                            ConfigInfo.getDefaultPreferenceProvider(context).putBoolean(ConfigInfo.PREF_IS_HOME_DEFAULT_GLOBAL, false);
////                        } else {
////                            ConfigInfo.getDefaultPreferenceProvider(context).putBoolean(ConfigInfo.PREF_IS_HOME_DEFAULT_GLOBAL, true);
////                        }
////
////                        EventBus.getDefault().post(new MessageEvent("CATEGORIES_WINDOW_DISMISSED"));
////                        //overridePendingTransition(R.anim.right_in, R.anim.right_out);
////
////
////                        Writeable = false;
////                        ClosePermision = true;
////                        dismiss();
////                        CategoriesIsShowen = false;
////
////
////                    } else {
////                        ClosePermision = false;
////                        if (MyApplication.USER_CATEGORIES_Temp.size() < 1 && save == false ) {
////                            MyToastV2.makeTextAndShow(context, context.getResources().getString(R.string.categorySelect), Toast.LENGTH_LONG);
////                        }
////                        else {
////
////                            if(save) {
////                                //mehdi
////                                //MyToastV2.makeTextAndShow(context,R.string.categorySelect,Toast.LENGTH_LONG);
////                                MyToastV2.makeTextAndShow(context, context.getResources().getString(R.string.categorySelect), Toast.LENGTH_LONG);
////                            }
////                            else {
////                                if (checkBox.isChecked()) {
////                                    ConfigInfo.getDefaultPreferenceProvider(context).putBoolean(ConfigInfo.PREF_IS_HOME_DEFAULT_GLOBAL, false);
////                                } else {
////                                    ConfigInfo.getDefaultPreferenceProvider(context).putBoolean(ConfigInfo.PREF_IS_HOME_DEFAULT_GLOBAL, true);
////                                }
////                                EventBus.getDefault().post(new MessageEvent("CATEGORIES_WINDOW_DISMISSED"));
////                                //overridePendingTransition(R.anim.right_in, R.anim.right_out);
////                                Writeable = false;
////                                dismiss();
////                                CategoriesIsShowen = false;
////                            }
////                        }
////
////
////                    }
//
//            }
//        });
//
//        //Read User Cats
//        try {
//            SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(context);
//            Gson gson = new Gson();
//            String json = sharedPrefs.getString("USER_CATEGORIES", null);
//            Type type = new TypeToken<ArrayList<Integer>>() {}.getType();
//            ArrayList<Integer> arrayList = gson.fromJson(json, type);
//
////            if(arrayList==null) {
////                MyApplication.USER_CATEGORIES_Selected = new ArrayList<Integer>();
////                //MyApplication.USER_CATEGORIES_Temp = new ArrayList<String>();
////            }
////            else
////            {
////                MyApplication.USER_CATEGORIES_Selected = arrayList;
////                //MyApplication.USER_CATEGORIES_Temp = arrayList;
////            }
////            MyApplication.USER_CATEGORIES_UnSelected = new ArrayList<Integer>();
//        }
//        catch (Exception ex){}
//
////        loadAllCategories(context, gridView);


    }

//    @Override
//    public void onWindowFocusChanged(boolean hasFocus) {
//        super.onWindowFocusChanged(hasFocus);
//
//        dialoglayout.post(new Runnable() {
//            @Override
//            public void run() {
//                Width = (dialoglayout.getWidth()/2)-20;
//            }
//        });
//        //Width = getWindow().getDecorView().getWidth();
//    }

//    @Override
//    public void setOnCancelListener(OnCancelListener listener) {
//        super.setOnCancelListener(listener);
//
//        if(iv_Close != null)
//            iv_Close.performClick();
//
//    }


}
