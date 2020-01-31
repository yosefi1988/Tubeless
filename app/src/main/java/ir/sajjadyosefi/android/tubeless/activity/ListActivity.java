package ir.sajjadyosefi.android.tubeless.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.zl.reik.dilatingdotsprogressbar.DilatingDotsProgressBar;

import ir.sajjadyosefi.android.tubeless.adapter.EndlessList_Adapter;
import ir.sajjadyosefi.android.xTubeless.classes.modelY.Page;
import ir.sajjadyosefi.android.xTubeless.classes.modelY.Post;
import ir.sajjadyosefi.android.xTubeless.classes.modelY.Posts;
import ir.sajjadyosefi.android.tubeless.R;

/**
 * Created by sajjad on 07/26/2016.
 */
public class ListActivity extends AppCompatActivity {


    public static int index = 0 ;


    DilatingDotsProgressBar dilatingDotsProgressBar;
    Button buttonBack;
    public static TextView textViewTitle,textViewEmpty ;


    //Common
    Context context;
    int listType = 0 ;

    //VIEW_POST_OF_PAGE variable and objects
    Page page;
    Posts posts = new Posts();
    EndlessList_Adapter adapter_Posts;


    //VIEW_COMMENTS_OF_POST variable and objects
    Post post;
//    Comments comments = new Comments();
//    Comments_Adapter adapterComments ;
//
//
//    //List of messages
//    ServerResponseRecivedMessageList messages = new ServerResponseRecivedMessageList();
//    MessageAdapter messageAdapter;


    //VIEW_PROFILE  variable and objects
    String userId = null ;


    //for scroll
    RecyclerView recyclerList ;
    public static SwipeRefreshLayout mSwipeRefreshLayout_list;
    private RelativeLayout RL_insertComment;
    Button buttonSendComment;


//    @Subscribe(threadMode = ThreadMode.MAIN)
//    public void onMessageEvent(MessageEvent event) {
//        if (event.message.equals("MessageSent")){
//            new SweetAlertDialog(this,SweetAlertDialog.SUCCESS_TYPE)
//                    .setTitleText(" ")
//                    .setContentText(this.getString(R.string.MESSAGE_SENT))
//                    .show();
//        }
//    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        View v = getLayoutInflater().inflate(R.layout.activity_list,null);
        RelativeLayout activity_list = new RelativeLayout(this);
        activity_list.addView(v);

        Bundle bundle;
        if (getIntent() != null)
            if (getIntent().getExtras() != null) {
                bundle = getIntent().getExtras();
                listType = bundle.getInt("listType");
                if(listType !=  1)
                {
//                    setTheme(R.style.AppTheme2);
//                    super.setTheme(R.style.AppTheme2);
                }else {
                    RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams)v.getLayoutParams();
                    params.setMargins(15,15,10,15);
                    v.setLayoutParams(params);


                    RelativeLayout actionBar = (RelativeLayout)(activity_list.findViewById(R.id.actionBar));
                    actionBar.getLayoutParams().height = 0 ;

                }
            }
        //setContentView(R.layout.activity_list);
        setContentView(activity_list);
        this.context = this ;


        RL_insertComment = (RelativeLayout) findViewById(R.id.RL_insertComment);
        buttonSendComment = (Button) findViewById(R.id.buttonSendComment);

        dilatingDotsProgressBar = (DilatingDotsProgressBar) findViewById(R.id.PBSjd);
//        floatingActionButton = (FloatingActionButton) findViewById(R.id.fab);
//        floatingActionButton2 = (FloatingActionButton) findViewById(R.id.fab2);
//        floatingActionButton2.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                recyclerList.smoothScrollToPosition(0);
//            }
//        });




//        floatingActionButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//            }
//        });

        buttonBack = (Button) findViewById(R.id.buttonBack);
        textViewTitle = (TextView) findViewById(R.id.textViewTitle);
        recyclerList = (RecyclerView) findViewById(R.id.recyclerList);
        textViewEmpty = (TextView) findViewById(R.id.empty_view);
        mSwipeRefreshLayout_list = (SwipeRefreshLayout) findViewById(R.id.swipe_refresh_list);
        mSwipeRefreshLayout_list.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                Bundle bundle = new Bundle();
                if (getIntent() != null)
                    if (getIntent().getExtras() != null) {
                        bundle = getIntent().getExtras();

                        listType = bundle.getInt("listType");
                        switch (listType) {
                            case 2: {
                                index = 0;
                                initialize_PostOfPages(bundle);
                                break;
                            }
                            case 1: {
                                index = 0;
                                initialize_CommentOfPost(bundle,0);
                                break;
                            }
                        }
                        //or
                        if(bundle != null) {
                            if (bundle.getString("listType") != null) {
                                if (bundle.getString("listType").equals("inbox")) {
                                    index = 0;
                                    initialize_Message(getIntent().getExtras());
                                } else if (bundle.getString("listType").equals("sentbox")) {
                                    index = 0;
                                    initialize_Message(getIntent().getExtras());
                                } else if (bundle.getString("listType").equals("trash")) {
                                    index = 0;
                                    initialize_Message(getIntent().getExtras());
                                }
                            }
                        }
                    }
            }
        });
        mSwipeRefreshLayout_list.setColorSchemeResources(R.color.refreshcolor1, R.color.refreshcolor2, R.color.refreshcolor3, R.color.refreshcolor4, R.color.refreshcolor5,R.color.refreshcolor6);

        recyclerList.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy){
                super.onScrolled(recyclerView, dx, dy);

                if (dy > 0) {
                    // Scroll Down
//                    if (floatingActionButton.isShown()) {
//                        floatingActionButton.hide();
//                    }
//
//
//                    if (floatingActionButton2.isShown()) {
//                        floatingActionButton2.hide();
//                    }
                }
                else if (dy < 0) {
                    // Scroll Up
//                    if (!floatingActionButton.isShown()) {
//                        floatingActionButton.show();
//                    }
//
//                    if (!floatingActionButton2.isShown()) {
//                        floatingActionButton2.show();
//                    }
                }
            }
        });

        //Button Back
        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        if(getIntent() != null)
            if(getIntent().getExtras() != null) {
                bundle = getIntent().getExtras();

                listType = bundle.getInt("listType");
                switch (listType) {
                    case 2: {
                        initialize_PostOfPages(bundle);
                        RL_insertComment.getLayoutParams().height = 0 ;
                    }
                    break;
                    case 3: {
                        initialize_CommentOfPost(bundle,0);
                        RelativeLayout.LayoutParams rlViewParams = new RelativeLayout.LayoutParams(
                                RelativeLayout.LayoutParams.MATCH_PARENT,
                                RelativeLayout.LayoutParams.WRAP_CONTENT);
                        //RL_insertComment.setLayoutParams(rlViewParams );
                    }
                    break;
                    case 4: {
                        initialize_Profile(bundle);
                        RL_insertComment.getLayoutParams().height = 0 ;
                    }
                    break;
                    case 5 :{

                        getIntent().putExtra("listTitle",context.getString(R.string.app_pardakht_text));
                        getIntent().putExtra("listType", "inbox");
                        bundle = getIntent().getExtras();

                        initialize_Message(bundle);
                        RL_insertComment.getLayoutParams().height = 0 ;
                        break;
                    }
                    case 6 :{

                        getIntent().putExtra("listTitle",context.getString(R.string.create));
                        getIntent().putExtra("listType", "sentbox");
                        bundle = getIntent().getExtras();

                        initialize_Message(bundle);
                        RL_insertComment.getLayoutParams().height = 0 ;
                        break;
                    }
//                    case Statics.VIEW_TRASH_MESSAGE :{
//
//                        getIntent().putExtra("listTitle",mContext.getString(R.string.VIEW_TRASH_MESSAGE_LIST));
//                        getIntent().putExtra("listType", "trash");
//                        bundle = getIntent().getExtras();
//
//                        initialize_Message(bundle);
//                        RL_insertComment.getLayoutParams().height = 0 ;
//                        break;
//                    }
                }

            }

        final EditText editTextComment = ((EditText) findViewById(R.id.editTextComment));

        buttonSendComment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //send comment
//                Long as = post.getId() ;
                int a ;
                a = 1 + 2 ;

                if(editTextComment.getText().toString().length() > 0) {
//                    new AsyncSendComment(getApplicationContext(), false,
//                            recyclerList, adapterComments, dilatingDotsProgressBar, null, editTextComment, null, null, null, 0, true
//                    ).execute(getIntent().getStringExtra(Statics.PAGE_ID),
//                            post.getFeed_id(),
//                            editTextComment.getText().toString(),
//                            true + "",
//                            post.getType_id(),
//                            post.getItem_id(),
//                            post.getParent_user_id());
                }

            }
        });

    }

    public void initialize_CommentOfPost(Bundle bundle,int count) {
        //get Page Object

        if (bundle.getString("Post") != null)
            post = new Gson().fromJson(bundle.getString("Post"), Post.class);

        if(count != 0)
            post.setTotal_comment((Integer.parseInt(post.getTotal_comment()) + 1 ) + "");

        //set Title
        if (bundle.getString("Title") != null)
            textViewTitle.setText(bundle.getString("Title"));
        else
            textViewTitle.setText("");

//        //Get Posts of page from database
//        recyclerList.setHasFixedSize(true);
//        recyclerList.setItemAnimator(new DefaultItemAnimator());
//        adapterComments = new Comments_Adapter(mContext,dilatingDotsProgressBar,textViewEmpty,mSwipeRefreshLayout_list,recyclerList, comments, post,null);
//        recyclerList.setLayoutManager(new LinearLayoutManager(this));
//        recyclerList.setAdapter(adapterComments);
    }


    public void initialize_Message(Bundle bundle) {
        //get Page Object



        //set Title
        if (bundle.getString("Title") != null)
            textViewTitle.setText(bundle.getString("listTitle"));
        else
            textViewTitle.setText("");

        //Get Posts of page from database
//        recyclerList.setHasFixedSize(true);
//        recyclerList.setItemAnimator(new DefaultItemAnimator());
//        //messageAdapter = new MessageAdapter(mContext,dilatingDotsProgressBar,textViewEmpty,mSwipeRefreshLayout_list,recyclerList, comments, post,null);
//        messageAdapter = new MessageAdapter(mContext,dilatingDotsProgressBar,textViewEmpty,mSwipeRefreshLayout_list,recyclerList,messages, bundle.getString("listType"));
//        recyclerList.setLayoutManager(new LinearLayoutManager(this));
//        recyclerList.setAdapter(messageAdapter);

    }

    private void initialize_Profile(Bundle bundle) {
        //get Page Object
        if (bundle.getString("UserID") != null) {
            userId = bundle.getString("UserID") ;
        }
        //set Title
        textViewTitle.setText(context.getString(R.string.app_id));
        //Get Posts of page from database
        recyclerList.setHasFixedSize(true);
        recyclerList.setItemAnimator(new DefaultItemAnimator());


        adapter_Posts = null;//new EndlessList_Adapter(mContext,dilatingDotsProgressBar,textViewEmpty,mSwipeRefreshLayout_list,recyclerList, posts, null,userId);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(RecyclerView.VERTICAL);
        recyclerList.setLayoutManager(llm);
        recyclerList.setAdapter(adapter_Posts);

    }





    private void initialize_PostOfPages(Bundle bundle) {
        //get Page Object
        if (bundle.getString("Page") != null) {
            page = new Gson().fromJson(bundle.getString("Page"), Page.class);
        }
        //set Title
        textViewTitle.setText(page.getTitle());
        //Get Posts of page from database
        recyclerList.setHasFixedSize(true);
        recyclerList.setItemAnimator(new DefaultItemAnimator());
        adapter_Posts = null;// new EndlessList_Adapter(mContext,dilatingDotsProgressBar,textViewEmpty,mSwipeRefreshLayout_list,recyclerList, posts, page,null);
        recyclerList.setAdapter(adapter_Posts);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


        if (requestCode == 0)
            if (resultCode == ((Activity)context).RESULT_OK) {
                post.setTotal_comment(Integer.valueOf(post.getTotal_comment()) + 1 + "");
                //tvCommentUnique.setText(post.getTotal_comment());
//                SharedPreferences values = ((Activity)mContext).getSharedPreferences(Statics.MAHAN, 0);
//                values.edit().putBoolean("changeC", true).apply();


//                new AsyncLoadComment().execute(_postItem.getParent_user_id(),
//                        _postItem.getFeed_id(),
//                        "0",
//                        kind+"");

                Log.e("$$$$$", "onActivityResult: " + "OOOOOOKKKKKKK");

//                new SweetAlertDialog(((Activity)mContext),SweetAlertDialog.SUCCESS_TYPE)
//                        .setTitleText(" ")
//                        .setContentText(((Activity)mContext).getString(R.string.COMMENT_INSERTED))
//                        .show();


                Bundle bundle = new Bundle();
                bundle = getIntent().getExtras();
                initialize_CommentOfPost(bundle,1);

            } else if (resultCode == ((Activity)context).RESULT_CANCELED) {
//                new SweetAlertDialog(((Activity)mContext),SweetAlertDialog.WARNING_TYPE)
//                        .setTitleText("WARNING")
//                        .setContentText(((Activity)mContext).getString(R.string.COMMENT_NOT_INSERTED))
//                        .show();
            }else {
//                new SweetAlertDialog(((Activity)mContext),SweetAlertDialog.ERROR_TYPE).setTitleText("Error")
//                        .setContentText(((Activity)mContext).getString(R.string.ERROR_IN_CONNECTION))
//                        .show();
            }

        //request Code :
        // 007 delete message Hamon => Email
        //
        Log.e("Request Code ", "onActivityResult: " + requestCode);
        Log.e("Result Code", "onActivityResult: " + resultCode);

        if (requestCode == 007) {
            Intent intent = getIntent();
            String ReadActivityUserActionType;
            Bundle bundle = intent.getExtras();
            if(bundle != null) {
                ReadActivityUserActionType = bundle.getString("ActionType");

                if(ReadActivityUserActionType != null && ReadActivityUserActionType.equals("Delete"))
                {
                    finish();
                    startActivity(intent);
                    overridePendingTransition(R.anim.fadeout, R.anim.fadein);

                }else if(ReadActivityUserActionType != null && ReadActivityUserActionType.equals("Report"))
                {

                } else if(ReadActivityUserActionType != null && ReadActivityUserActionType.equals("Reply"))
                {
//                    new SweetAlertDialog(this,SweetAlertDialog.SUCCESS_TYPE).setTitleText(" ")
//                            .setContentText(this.getString(R.string.POST_INSERTED))
//                            .show();
                }

            }
        }else if(requestCode == 101) {
            if(resultCode == RESULT_OK)
            {

//                new SweetAlertDialog(this,SweetAlertDialog.SUCCESS_TYPE)
//                        .setTitleText(" ")
//                        .setContentText(this.getString(R.string.MESSAGE_SENT))
//                        .show();

            }else
            {
//                new SweetAlertDialog(this,SweetAlertDialog.ERROR_TYPE).setTitleText("Error")
//                        .setContentText(this.getString(R.string.MESSAGE_SENT_ERROR))
//                        .show();
            }
        }

//        if (requestCode == 3 || requestCode == 4 || requestCode == 2)
//            adapter_Posts.onActivityResult(requestCode, resultCode, data);

    }



}
