package ir.sajjadyosefi.android.tubeless.activity.yafteha;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.Toast;


import androidx.appcompat.app.AlertDialog;
import androidx.core.content.ContextCompat;

import com.zl.reik.dilatingdotsprogressbar.DilatingDotsProgressBar;

import ir.sajjadyosefi.android.tubeless.Global;
import ir.sajjadyosefi.android.xTubeless.activity.common.ContactUsActivity;
import ir.sajjadyosefi.android.tubeless.adapter.list.SpinnerAdapter;
import ir.sajjadyosefi.android.tubeless.R;

import ir.sajjadyosefi.android.xTubeless.classes.modelY.yafte.search.Search;
import ir.sajjadyosefi.android.xTubeless.classes.modelY.yafte.search.SearchResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchActivity extends Activity {

    Context context;
    EditText editText_name ,editText_family ,editText_father_name ,editText_code_melli;
    DilatingDotsProgressBar dilatingDotsProgressBar;
    Switch switchSearchType;
    String searchType = "-1";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = this;
        setContentView(R.layout._activity_search);

//        Spinner mSpinner5 = (Spinner) findViewById(R.id.spinner2);
//        String[] years = {"1996","1997","1998","1998"};
////        ArrayAdapter<CharSequence> langAdapter = new ArrayAdapter<CharSequence>(this, R.layout._custom_spinner_text, years );
//        ArrayAdapter<String> langAdapter = new ArrayAdapter<String>(this, R.layout._custom_spinner_text, R.id.xxxxxxf, years);
//        langAdapter.setDropDownViewResource(R.layout._simple_spinner_dropdown);
//        mSpinner5.setAdapter(langAdapter);

        String[] countryNames={"همه موارد"};
        int flags[] = {R.drawable.png_download};


        String[] countryArray = {"India", "Pakistan", "USA", "UK"};
        ArrayAdapter adapter = new ArrayAdapter<String>(this, R.layout._custom_spinner_text,R.id.textview, countryArray);
        SpinnerAdapter spinnerAdapter = new SpinnerAdapter (this,flags,countryNames);
        Spinner listView = (Spinner) findViewById(R.id.spinner2);
        listView.setAdapter(spinnerAdapter);

        dilatingDotsProgressBar = (DilatingDotsProgressBar) findViewById(R.id.PBSjd);
        editText_name = (EditText) findViewById(R.id.editText_name);
        editText_family = (EditText) findViewById(R.id.editText_family);
        editText_father_name =(EditText) findViewById(R.id.editText_father_name );
        editText_code_melli =(EditText) findViewById(R.id.editText_code_melli);
        ((Spinner) findViewById(R.id.spinner2)).setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0) {
                    searchType = "-1";
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        switchSearchType = (Switch) findViewById(R.id.switchSearchType);



        ((Button)findViewById(R.id.button_search)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(switchSearchType.isChecked()){
                    if (editText_name.getText().toString().length() >= 3 ){
                        Search search = new Search(editText_name.getText().toString(), editText_family.getText().toString(), editText_father_name.getText().toString(),searchType);
                        dilatingDotsProgressBar.setVisibility(View.VISIBLE);

                        Global.apiManager.createUser(search, new Callback<SearchResponse>() {
                            @Override
                            public void onResponse(Call<SearchResponse> call, Response<SearchResponse> response) {
                                dilatingDotsProgressBar.setVisibility(View.GONE);
                                SearchResponse responseUser = response.body();
                                if (response.isSuccessful() && responseUser != null) {
                                    if (response.body().getType().equals("NoResult")){
                                        showDialogNotfound();
                                    }else {
                                        SearchResultActivity.searchResponse = response.body().getData();
                                        Intent intent = new Intent(context, SearchResultActivity.class);
                                        Bundle bundle = new Bundle();
                                        bundle.putInt(ContactUsActivity.Type, 16);
                                        intent.putExtras(bundle);
                                        startActivity(intent);
                                        ((Activity) context).overridePendingTransition(R.anim.fadeout, R.anim.fadein);
                                    }
                                } else {
                                    Toast.makeText(SearchActivity.this,
                                            String.format("Response is %s", String.valueOf(response.message()))
                                            , Toast.LENGTH_LONG).show();
                                }
                            }

                            @Override
                            public void onFailure(Call<SearchResponse> call, Throwable t) {
                                Toast.makeText(SearchActivity.this,
                                        String.format("Response is %s",t.getMessage().toString())
                                        , Toast.LENGTH_LONG).show();
                            }
                        });


                    }else {
                        Toast.makeText(context,context.getResources().getString(R.string.inputError),Toast.LENGTH_SHORT).show();
                    }
                }else {
                    if (editText_code_melli.getText().toString().length() == 10){
                        Search search = new Search(editText_code_melli.getText().toString(),searchType);
                        dilatingDotsProgressBar.setVisibility(View.VISIBLE);

                        Global.apiManager.createUser(search, new Callback<SearchResponse>() {
                            @Override
                            public void onResponse(Call<SearchResponse> call, Response<SearchResponse> response) {
                                dilatingDotsProgressBar.setVisibility(View.GONE);
                                SearchResponse responseUser = response.body();
                                if (response.isSuccessful() && responseUser != null) {
                                    if (response.body().getType().equals("NoResult")){
                                        showDialogNotfound();
                                    }else {

                                        SearchResultActivity.searchResponse = response.body().getData();
                                        Intent intent = new Intent(context, SearchResultActivity.class);
                                        Bundle bundle = new Bundle();
                                        bundle.putInt(ContactUsActivity.Type, 16);
                                        intent.putExtras(bundle);
                                        startActivity(intent);
                                        ((Activity) context).overridePendingTransition(R.anim.fadeout, R.anim.fadein);
                                    }
                                } else {
                                    Toast.makeText(SearchActivity.this,
                                            String.format("Response is %s", String.valueOf(response.message()))
                                            , Toast.LENGTH_LONG).show();
                                }
                            }

                            @Override
                            public void onFailure(Call<SearchResponse> call, Throwable t) {
                                Toast.makeText(SearchActivity.this,
                                        String.format("Response is %s",t.getMessage().toString())
                                        , Toast.LENGTH_LONG).show();
                            }
                        });
                    }else {
                        Toast.makeText(context,context.getResources().getString(R.string.inputError),Toast.LENGTH_SHORT).show();

                    }
                }
            }
        });

        switchSearchType.setChecked(true);
    }

    private void showDialogNotfound() {
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        View dialoglayout = inflater.inflate(R.layout.notfound_layout, null);
        final AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setView(dialoglayout);
        final AlertDialog alertDialog1 = builder.create();
        alertDialog1.getWindow().getAttributes().windowAnimations = R.style.alertDialogAnimation;


        final Button cancelBtn = (Button) dialoglayout.findViewById(R.id.buttonCancel);
        final Button downloadBtn = (Button) dialoglayout.findViewById(R.id.buttonDownload);
        downloadBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context,NewYafteActivity.class);
                Bundle bundle = new Bundle();
                bundle.putInt(ContactUsActivity.Type,16);
                intent.putExtras(bundle);
                startActivity(intent);
                ((Activity)context).overridePendingTransition(R.anim.fadeout, R.anim.fadein);
                alertDialog1.cancel();

            }
        });

        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog1.cancel();
            }
        });


        try {
            alertDialog1.show();
        }catch (Exception ex){

        }


    }


    @Override
    protected void onResume() {
        super.onResume();

        switchSearchType.getTrackDrawable().setColorFilter(ContextCompat.getColor(this, R.color.refreshcolor2), PorterDuff.Mode.SRC_IN);
        switchSearchType.getThumbDrawable().setColorFilter(ContextCompat.getColor(this, R.color.refreshcolor2), PorterDuff.Mode.SRC_IN);
        if(switchSearchType.isChecked()){
            ((View)findViewById(R.id.linearLayoutSearchWithCodeMelli)).setVisibility(View.GONE);
            ((View)findViewById(R.id.linearLayoutSearchWithNameFamily)).setVisibility(View.VISIBLE);
        }else {
            ((View)findViewById(R.id.linearLayoutSearchWithCodeMelli)).setVisibility(View.VISIBLE);
            ((View)findViewById(R.id.linearLayoutSearchWithNameFamily)).setVisibility(View.GONE);
        }
        switchSearchType.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if(isChecked){
                    ((View)findViewById(R.id.linearLayoutSearchWithCodeMelli)).setVisibility(View.GONE);
                    ((View)findViewById(R.id.linearLayoutSearchWithNameFamily)).setVisibility(View.VISIBLE);
                }else {
                    ((View)findViewById(R.id.linearLayoutSearchWithCodeMelli)).setVisibility(View.VISIBLE);
                    ((View)findViewById(R.id.linearLayoutSearchWithNameFamily)).setVisibility(View.GONE);
                }
            }
        });

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.fadeout, R.anim.fadein);
        finish();
    }


}

