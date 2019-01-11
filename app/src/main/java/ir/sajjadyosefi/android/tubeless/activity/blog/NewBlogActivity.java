package ir.sajjadyosefi.android.tubeless.activity.blog;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.ScrollView;
import android.widget.TextView;

import com.orm.SugarContext;
import com.zl.reik.dilatingdotsprogressbar.DilatingDotsProgressBar;

import java.io.File;

import ir.adad.client.Adad;
import ir.sajjadyosefi.android.tubeless.Global;
import ir.sajjadyosefi.android.tubeless.R;
import ir.sajjadyosefi.android.tubeless.asyncTask.yafte.AsyncRegNewYafte;
import ir.sajjadyosefi.android.tubeless.classes.model.Blog.BlogItem;
import ir.sajjadyosefi.android.tubeless.classes.model.User.User;
import ir.sajjadyosefi.android.tubeless.view.widget.view.PersianTextView;

//import com.vansuita.gaussianblur.GaussianBlur;


public class NewBlogActivity extends AppCompatActivity {

    //Bundle String list
    public static final String Type = "TYPE";

    //Create New Activity List
    public static int messageType = 0;

    Context context;
    DilatingDotsProgressBar dilatingDotsProgressBar;
    PersianTextView  tvField1,tvField2;
    TextView tvTitle;
    RadioButton radioButton1,radioButton2,radioButton3;
    EditText editTextCategory;
    EditText editTextTitle;
    EditText editTextStatement;
    EditText editTextTitleText ;
    Button btn1;
    Button btn2;
    ScrollView svScroll;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //init
        context = this;
        SugarContext.init(this);
        Adad.initialize(getApplicationContext());

        //getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        //getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        //getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_new_blog);

        editTextCategory = (EditText) findViewById(R.id.editTextCategory);
        editTextTitle = (EditText) findViewById(R.id.editTextTitle);
        editTextStatement = (EditText) findViewById(R.id.editTextStatement);
        editTextTitleText = (EditText) findViewById(R.id.editTextTitleText);


        dilatingDotsProgressBar = (DilatingDotsProgressBar) findViewById(R.id.PBSjd);

        btn1 = (Button) findViewById(R.id.btn1);
        btn2 = (Button) findViewById(R.id.btn2);
        radioButton1 = (RadioButton) findViewById(R.id.radioButton1);
        radioButton2 = (RadioButton) findViewById(R.id.radioButton2);
        radioButton3 = (RadioButton) findViewById(R.id.radioButton3);
        tvField1 = (PersianTextView) findViewById(R.id.tvField1);
        tvField2 = (PersianTextView) findViewById(R.id.tvField2);
        tvTitle = (TextView) findViewById(R.id.tvTitle);
        svScroll = (ScrollView) findViewById(R.id.svScroll);

        //hide keyboard
        View.OnClickListener imageviewClickListener =  new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //hide keyboard
                InputMethodManager imm = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(tvTitle.getWindowToken(), 0);
            }
        };
//        tvField1.setOnClickListener(imageviewClickListener);
//        tvField2.setOnClickListener(imageviewClickListener);
//        tvTitle.setOnClickListener(imageviewClickListener);

//        if(Global.mUser != null){
//            etField1.setText(Global.mUser.getMobileNumber());
//            etField1.setEnabled(false);
//        }

        btn1.setText(context.getString(R.string.send));
        btn2.setText(context.getString(R.string.cancel));
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });


//        //getType
//        Intent intent = getIntent();
//        Bundle bundle = intent.getExtras();
//        switch (bundle.getInt(NewBlogActivity.Type)) {
//            case NewBlogActivity.PEYDA_SHODE:{
//                messageType = NewBlogActivity.PEYDA_SHODE;
//                break;
//            }
//            case NewBlogActivity.GOM_SHODE:{
//                messageType = NewBlogActivity.GOM_SHODE;
//                break;
//            }
//            case NewBlogActivity.SERGHATI:{
//                messageType = NewBlogActivity.SERGHATI;
//                break;
//            }
//        }


        (findViewById(R.id.btnCamera)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                ShowSelectSturceDialog(context);
                Global.ShowMessageDialog(context,"",context.getString(R.string.commingSoon));
            }
        });


        final Button btnSend = (Button)(findViewById(R.id.btn1));
        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!(editTextTitle.getText().length() > 1 &&
                        editTextCategory.getText().length() > 0 &&
                        editTextStatement.getText().length() > 0 &&
                        editTextTitleText.getText().length() > 1 )){
                    Global.ShowMessageDialog(context,"",context.getString(R.string.requerment_fields_inYafteha));
                }else {
                    //Do Send
                    BlogItem blogItem = new BlogItem();
                    blogItem.setCategoryID(Integer.parseInt(editTextCategory.getText().toString()));
                    blogItem.setUserID(49);
                    blogItem.setTitle(editTextTitle.getText().toString());
                    blogItem.setStatement(editTextStatement.getText().toString());
                    blogItem.setText(editTextTitleText.getText().toString());
                    //blogItem.setTextPicture(etField4.getText().toString());

                    AsyncRegNewYafte asyncRegNewYafte = new AsyncRegNewYafte(context,dilatingDotsProgressBar,blogItem);
                    asyncRegNewYafte.execute();
                }
            }
        });
    }
    public static final String TEMP_PHOTO_FILE_NAME = "TubelessImageYafte.jpg";

    public static long getFileSize(Context context, File file) {
        long length = file.length();
        length = length/1024;
        //System.out.println("File Path : " + file.getPath() + ", File size : " + length +" KB");
        return length/1024; //MB
    }

    public String toHex(String arg) {

        String retVal = "";
        retVal = arg.replace(" ", "%20");
        retVal = retVal.replace("_", "%5F");
        retVal = retVal.replace("\n", "%0A");

        return retVal;

    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Global.mUser = new User();
//        Intent intent = new Intent(this,MainActivity.class);
//        startActivity(intent);

        finish();
        overridePendingTransition(R.anim.fadeout, R.anim.fadein);
    }
}
