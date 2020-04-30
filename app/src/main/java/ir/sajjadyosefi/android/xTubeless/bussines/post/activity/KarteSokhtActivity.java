package ir.sajjadyosefi.android.xTubeless.bussines.post.activity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;

import com.readystatesoftware.systembartint.SystemBarTintManager;

import ir.sajjadyosefi.android.xTubeless.Adapter.SpinnerAdapterA;
import ir.sajjadyosefi.android.xTubeless.Global;
import ir.sajjadyosefi.android.xTubeless.R;
import ir.sajjadyosefi.android.xTubeless.activity.TubelessTransparentStatusBarActivity;

import ir.sajjadyosefi.android.xTubeless.classes.model.config.Configuration;
import it.sephiroth.android.library.bottomnavigation.BottomNavigation;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;



public class KarteSokhtActivity extends TubelessTransparentStatusBarActivity {
    public static Context mContext;
    private static EditText editText1 ;
    private static String editText1Value ;
    private static EditText editText2 ;
    private static String editText2Value ;
    private static EditText editText3 ;
    private static String editText3Value ;
    private static String editTextXValue ;
    private static boolean goToVipValue = false;


    Spinner listView;
    private Button button ,buttonContactUS;
    //    String[] countryNames={"حرف","الف","ب","ت","ج","د","س","ص","ط","ع","ق","ل","م","ن","و","ه","ي","ک","ژ"};
    String[] countryNames = {"--", "الف", "ب", "ت", "ج", "د", "س", "ص", "ط", "ع", "ق", "ل", "م", "ن", "و", "ه", "ي", "ک", "ژ"};
    private static int selectedChar ;
    String[] countryArray = {"India", "Pakistan", "USA", "UK"};

    public static final String ARG_PAGE = "ARG_PAGE";






    public Button buttonReg , buttonBack;
    EditText editTextText,editTextTextPicture,editTextTitleStatment,editTextTitlePicture,editTextTitle;
    RadioButton radioButton1,radioButton2,radioButton3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);

        setContentView(R.layout.activity_kart_sokht);
        mContext = getContext();





        editText1 = (EditText) findViewById(R.id.editText1);
        editText2 = (EditText) findViewById(R.id.editText2);
        editText3 = (EditText) findViewById(R.id.editText3);
        button = (Button) findViewById(R.id.button);
        listView = (Spinner) findViewById(R.id.spinner);


        ArrayAdapter adapter = new ArrayAdapter<String>(mContext, R.layout._custom_spinner_text,R.id.textview, countryArray);
        final SpinnerAdapterA spinnerAdapter = new SpinnerAdapterA(mContext,countryNames);
        listView.setAdapter(spinnerAdapter);
        listView.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                selectedChar = i;
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                selectedChar = 0;
            }
        });


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                boolean isValid = true;
                editText1Value = editText1.getText().toString();
                editText2Value = editText2.getText().toString();
                editText3Value = editText3.getText().toString();
                editTextXValue = listView.getSelectedItemPosition() + "";

                if(editText1Value.length() != 2){
                    isValid = false;
                }
                if(editText2Value.length() != 3){
                    isValid = false;
                }
                if(editText3Value.length() != 2){
                    isValid = false;
                }
                if(listView.getSelectedItemPosition() == 0){
                    isValid = false;
                }

                if (isValid) {
                    CheckFilter();
                }else {
                    Toast.makeText(mContext,"اطلاعات را به درستی وارد کنید",Toast.LENGTH_LONG).show();
                }
            }
        });
//        fetchWelcome();

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





    private boolean checkResult(Response<Configuration> response)  {
        Boolean aBoolean = false;

        try {
            PackageInfo pInfo = mContext.getPackageManager().getPackageInfo(mContext.getPackageName(), 0);
            String versionName = pInfo.versionName;
            if (versionName.contains("a")) {
                aBoolean = response.body().getConfiguration().play;

            } else if (versionName.contains("b")) {
                aBoolean = response.body().getConfiguration().bazaar;

            } else if (versionName.contains("m")) {
                aBoolean = response.body().getConfiguration().myket;

            } else if (versionName.contains("i")) {
                aBoolean = response.body().getConfiguration().iranapps;

            } else if (versionName.contains("j")) {
                aBoolean = response.body().getConfiguration().jhobin;

            } else if (versionName.contains("k")) {
                aBoolean = response.body().getConfiguration().kandoo;

            } else {
                aBoolean = response.body().getConfiguration().play;

            }

        }catch (Exception e){
            e.printStackTrace();
            aBoolean = false;
        }
        return aBoolean;
    }



    private void CheckFilter() {

        Global.retrofitHelperEpolice.callPelackservice("platesearch","1", "1","66" +  "03" + "25"  + "566" , new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if (response.body().contains("خارج از کشور")) {
                    Toast.makeText(mContext,"در صورتی که از فیلتر شکن استفاده می کنید آن را خاموش کنید.",Toast.LENGTH_SHORT).show();
                }else {
                    button.setEnabled(false);
//                    Global.retrofit2.config(new Callback<Configuration>() {
//                        @Override
//                        public void onResponse(Call<Configuration> call, Response<Configuration> response) {
//
//                            if (validData()) {

//                                if (error == false) {
                                    callService();
//                                } else {
//                                    callService();
//                                }
//                            }
//                        }
//
//                        @Override
//                        public void onFailure(Call<Configuration> call, Throwable t) {
//                            Toast.makeText(mContext,"عملیات با خطا مواجه شد",Toast.LENGTH_SHORT).show();
//                            Toast.makeText(mContext,"اینترنت خود را چک کنید",Toast.LENGTH_SHORT).show();
//                            //error = true;
//
//                            if (button != null)
//                                button.setEnabled(true);
//                        }
//                    });
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Toast.makeText(mContext,"عملیات با خطا مواجه شد",Toast.LENGTH_SHORT).show();
                Toast.makeText(mContext,"دوباره تلاش کنید",Toast.LENGTH_SHORT).show();

                if (button != null)
                    button.setEnabled(true);
            }
        });
    }

    private void showManyResultDialog(String responseX) {
        //before inflating the custom alert dialog layout, we will get the current activity viewgroup
        ViewGroup viewGroup = findViewById(android.R.id.content);

        //then we will inflate the custom alert dialog xml that we created
        final View dialogView = LayoutInflater.from(this).inflate(R.layout.layout_dialog, viewGroup, false);
        TextView textViewStatment = dialogView.findViewById(R.id.textViewStatment);
        Button buttonOk = dialogView.findViewById(R.id.buttonOk);
        Button buttonCancel = dialogView.findViewById(R.id.buttonCancel);
        buttonCancel.setText("باشه");
        buttonOk.setVisibility(View.GONE);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            textViewStatment.setText(Html.fromHtml(responseX, Html.FROM_HTML_MODE_COMPACT));
        } else {
            textViewStatment.setText(Html.fromHtml(responseX));
        }

        //Now we need an AlertDialog.Builder object
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        //setting the view of the builder to our custom view that we already inflated
        builder.setView(dialogView);

        //finally creating the alert dialog and displaying it
        final AlertDialog alertDialog = builder.create();
        buttonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.dismiss();
            }
        });
        buttonOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.dismiss();

            }
        });
        alertDialog.show();
    }

    private void callService() {

        Global.retrofitHelperEpolice.callPelackservice("platesearch","1", "1",editText1Value +  (Integer.parseInt(editTextXValue) <= 9 ? "0" + editTextXValue : editTextXValue) + editText3Value  + editText2Value , new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
//                response.body()

                showManyResultDialog(response.body());


                editText1Value = null;
                editText2Value = null;
                editText3Value = null;
                editTextXValue = null;

                if (button != null)
                    button.setEnabled(true);
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Toast.makeText(mContext,"عملیات با خطا مواجه شد",Toast.LENGTH_SHORT).show();
                Toast.makeText(mContext,"دوباره تلاش کنید",Toast.LENGTH_SHORT).show();

                if (button != null)
                    button.setEnabled(true);
            }
        });
    }

    private boolean validData() {
        Boolean isValid = false;
        if (selectedChar != 0){
            if (editText1.getText().toString().length() == 2){
                try {
                    new Integer(Integer.parseInt(editText1.getText().toString()));

                    if (editText2.getText().toString().length() == 3){
                        try {
                            new Integer(Integer.parseInt(editText2.getText().toString()));
                            if (editText3.getText().toString().length() == 2){
                                try {
                                    new Integer(Integer.parseInt(editText3.getText().toString()));
                                    isValid = true;

                                }catch (Exception e){
                                    isValid = false;
                                }
                            }

                        }catch (Exception e){
                            isValid = false;
                        }
                    }

                }catch (Exception e){
                    isValid = false;
                }
            }
        }

        if (isValid == false){
            Toast.makeText(mContext,"در ورود اطلاعات دقت فرمایید",Toast.LENGTH_SHORT).show();
            button.setEnabled(true);
        }
        return isValid;
    }


}
