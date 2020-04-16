package ir.sajjadyosefi.android.xTubeless.activity.uploadPicture;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import ir.sajjadyosefi.android.superpickerlibrary.PickerManagerBuilder;
import ir.sajjadyosefi.android.xTubeless.Adapter.EndlessList_AdapterFile;
import ir.sajjadyosefi.android.xTubeless.R;
import ir.sajjadyosefi.android.xTubeless.classes.model.File;

import static ir.sajjadyosefi.android.xTubeless.Adapter.EndlessList_AdapterFile.FILES;
import static ir.sajjadyosefi.android.xTubeless.classes.model.File.MAP_1;
import static org.litepal.LitePalApplication.getContext;


public class FileListActivity extends Activity {

    private static final String TAG = "sssssssssssssss";
    private int RC_SIGN_IN = 1000;
    private List<File> fileList ;
    private int fileCount = 1;


    RecyclerView mRecyclerViewTimeline;
    EndlessList_AdapterFile adapter_Posts;
    LinearLayoutManager mLayoutManager;


    Button buttonBack,buttonClear;
    TextView textViewNameFamily1,TextViewSerial,textViewDate,textViewNameFamily2,textViewMobile,TextViewCodePosti,TextViewAddress;

    public static Activity activity ;
    private View rootView = null;

    public static ImageView imageView ;

    public synchronized static Intent getIntent(Context context, Bundle bundle) {
        bundle.putString("item1","value1");
        Intent intent = new Intent(context, FileListActivity.class);
        intent.putExtras(bundle);
        return intent;
    }

    public synchronized static Intent getIntent(Context context) {
        return getIntent(context,null);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        activity = this;
        fileCount = getIntent().getIntExtra("FILE_COUNT",1);
        fileList = (List<File>) getIntent().getSerializableExtra("LIST");

        setContentView(R.layout.activity_details);
        rootView = (ViewGroup) ((ViewGroup) this.findViewById(android.R.id.content)).getChildAt(0);
        mRecyclerViewTimeline = findViewById(R.id.recyclerView);
        imageView = findViewById(R.id.imageView);

        ((Button)(findViewById(R.id.buttonGallery))).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectFromGallery(activity);
            }
        });
        ((Button)(findViewById(R.id.buttonCamera))).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectFromCamera(activity);
            }
        });
        ((Button)(findViewById(R.id.buttonClear))).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fileList = new ArrayList<>();
                fillWigets(rootView , activity);
                adapter_Posts.notifyDataSetChanged();

                ((Button)(findViewById(R.id.buttonGallery))).setEnabled(true);
                ((Button)(findViewById(R.id.buttonCamera))).setEnabled(true);
            }
        });
        ((Button)(findViewById(R.id.buttonCancel))).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent returnIntent = new Intent();
                setResult(Activity.RESULT_CANCELED, returnIntent);
                finish();
            }
        });
        ((Button)(findViewById(R.id.buttonOK))).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent returnIntent = getIntent();
                returnIntent.putExtra("LIST",(Serializable)fileList);
                setResult(Activity.RESULT_OK, getIntent());
                finish();
            }
        });

        buttonClear = findViewById(R.id.buttonClear);
        buttonBack = findViewById(R.id.buttonBack);
        textViewDate = findViewById(R.id.textViewDate);
        fillWigets(rootView , activity);



//        try {
//            openFile(DetailsActivity.activity,path+"/arzyabi8331.jpg",false);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

    }

    private void selectFromGallery(final Activity activity) {
        new PickerManagerBuilder(activity,true, PickerManagerBuilder.SELECT_FROM_GALLERY)
                .setOnImageReceivedListener(new PickerManagerBuilder.onImageReceivedListener() {
                    @Override
                    public void onImageReceived(Uri imageUri) {
                        Toast.makeText(activity,"Got image - " + imageUri, Toast.LENGTH_LONG).show();
                        imageView.setImageURI(imageUri);

                        File map1 = new File();
                        map1.setTitle(imageUri.toString().substring(imageUri.toString().lastIndexOf("/")+1));
                        map1.setRequestContentId(1);
                        map1.setFrame(1);
                        map1.setFileType(MAP_1);
                        map1.setUri(imageUri.toString());
                        map1.setType(FILES);
                        fileList.add(map1);
                        adapter_Posts.notifyDataSetChanged();

                        if (fileCount == fileList.size()){
                            ((Button)(findViewById(R.id.buttonGallery))).setEnabled(false);
                            ((Button)(findViewById(R.id.buttonCamera))).setEnabled(false);
                        }
                    }
                })
                .setImageName("avatar")
                .start();
    }

    private void selectFromCamera(final Activity activity) {
        new PickerManagerBuilder(activity,true, PickerManagerBuilder.SELECT_FROM_CAMERA)
                .setOnImageReceivedListener(new PickerManagerBuilder.onImageReceivedListener() {
                    @Override
                    public void onImageReceived(Uri imageUri) {
                        Toast.makeText(activity,"Got image - " + imageUri, Toast.LENGTH_LONG).show();
                        imageView.setImageURI(imageUri);

                        File map1 = new File();
                        map1.setTitle(imageUri.toString().substring(imageUri.toString().lastIndexOf("/")+1));
                        map1.setRequestContentId(1);
                        map1.setFrame(1);
                        map1.setFileType(MAP_1);
                        map1.setUri(imageUri.toString());
                        map1.setType(FILES);
                        fileList.add(map1);
                        adapter_Posts.notifyDataSetChanged();

                        if (fileCount == fileList.size()){
                            ((Button)(findViewById(R.id.buttonGallery))).setEnabled(false);
                            ((Button)(findViewById(R.id.buttonCamera))).setEnabled(false);
                        }
                    }
                })
                .setImageName("avatar")
                .start();
    }

    private void fillWigets(View rootView , Activity activity) {
//        for (File file: fileList) {
//            File map1 = new File();
//            map1.setTitle("file.getDescription()");
//            map1.setRequestContentId(file.getRequestContentId());
//            map1.setFrame(file.getFrame());
//            map1.setFileType(MAP_1);
//            map1.setType(FILES);
//            fileList.add(map1);
//        }


        mRecyclerViewTimeline.setHasFixedSize(true);
        mRecyclerViewTimeline.setItemAnimator(new DefaultItemAnimator());
        mLayoutManager = new LinearLayoutManager(getContext());
        adapter_Posts = new EndlessList_AdapterFile(
                getContext(),
                mLayoutManager,
                rootView,
                fileList,
                FILES,
                false);
        mRecyclerViewTimeline.setLayoutManager(mLayoutManager);
        mRecyclerViewTimeline.setAdapter(adapter_Posts);
    }


}
