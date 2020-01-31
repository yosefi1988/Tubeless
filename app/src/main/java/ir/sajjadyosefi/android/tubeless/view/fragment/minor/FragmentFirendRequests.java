package ir.sajjadyosefi.android.tubeless.view.fragment.minor;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RadioGroup;

import com.zl.reik.dilatingdotsprogressbar.DilatingDotsProgressBar;

import ir.sajjadyosefi.android.tubeless.adapter.fragment.FriendRequest_AD_SajjadTest;
import ir.sajjadyosefi.android.tubeless.R;

/**
 * Created by sajjad on 08/14/2016.
 */
// In this case, the fragment displays simple text based on the page
@SuppressLint("ValidFragment")
public class FragmentFirendRequests extends Fragment {

    private final ViewPager viewPager;
    RecyclerView recyclerList ;
    public static SwipeRefreshLayout mSwipeRefreshLayout_list;
    FriendRequest_AD_SajjadTest adapter_friendRequest;



    DilatingDotsProgressBar progressBar;
    //ResponseFriendRequests responseFriendRequests = new ResponseFriendRequests();
    RadioGroup radioGroup;

    //CheckBoxPlus btnHamkaran , btnKarbaran ,btnRequests;
    ImageView imageView ;


    //People_Adapter people_adapter ;
    //SuggestionPeople suggestionPeople = new SuggestionPeople();

    int index = 0;


    public FragmentFirendRequests(Context context, ViewPager viewPager) {
        this.viewPager = viewPager;
        this.context = context;
    }

    public Context context;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        //final View view = inflater.inflate(R.layout.friend_request_fragment_page_sajjadtest, container, false);
        final View view = inflater.inflate(R.layout.empty_fragment_sajjadtest, container, false);

//
//        btnHamkaran = (CheckBoxPlus) view.findViewById(R.id.btnHamkaran);
//        btnKarbaran = (CheckBoxPlus) view.findViewById(R.id.btnKarbaran);
//        btnRequests = (CheckBoxPlus) view.findViewById(R.id.btnRequests);
//        imageView =  (ImageView) view.findViewById(R.id.imageView);
//
//        recyclerList = (RecyclerView) view.findViewById(R.id.recyclerList);
//        mProgressBar = (DilatingDotsProgressBar) view.findViewById(R.id.PBSjd);
//        mSwipeRefreshLayout_list = (SwipeRefreshLayout) view.findViewById(R.id.swipe_refresh_list);
//        recyclerList.setLayoutManager(new LinearLayoutManager(recyclerList.getContext()));
//
//        btnRequests.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//                if(isChecked)
//                    changeTab();
//            }
//        });
//        btnKarbaran.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//                if(isChecked){
//                    mSwipeRefreshLayout_list.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
//                        @Override
//                        public void onRefresh() {
//                            index = 0 ;
//                            new AsyncLoadHamkaran(context,
//                                    recyclerList,
//                                    people_adapter,
//                                    mProgressBar,
//                                    mSwipeRefreshLayout_list,
//                                    imageView,
//                                    suggestionPeople,
//                                    true,
//                                    index).execute();
//                        }
//                    });
//                    //new AsyncLoadKarbaran(context, imageView, recyclerList, mProgressBar, mSwipeRefreshLayout_list).execute();
//                    //String userId , Comments comments, int index, boolean noAnimationseta
//
//                    suggestionPeople.setUsers(new ArrayList<People>());
//                    suggestionPeople.setFriends(new ArrayList<People>());
//                    people_adapter = new People_Adapter(context,mainViewPager,mProgressBar,imageView,mSwipeRefreshLayout_list,recyclerList,suggestionPeople,true);
//                    recyclerList.setAdapter(people_adapter);
//                    new AsyncLoadHamkaran(context,
//                            recyclerList,
//                            people_adapter,
//                            mProgressBar,
//                            mSwipeRefreshLayout_list,
//                            imageView,
//                            suggestionPeople,
//                            true,
//                            index).execute();
//                }
//
//            }
//        });
//        btnHamkaran.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//                if(isChecked){
//                    mSwipeRefreshLayout_list.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
//                        @Override
//                        public void onRefresh() {
//                            index = 0 ;
//                            new AsyncLoadHamkaran(context,
//                                    recyclerList,
//                                    people_adapter,
//                                    mProgressBar,
//                                    mSwipeRefreshLayout_list,
//                                    imageView,
//                                    suggestionPeople,
//                                    false,
//                                    index).execute();
//                        }
//                    });
//                    //new AsyncLoadFriendRequests(context, imageView, recyclerList, mProgressBar, mSwipeRefreshLayout_list).execute();
//
//                    suggestionPeople.setUsers(new ArrayList<People>());
//                    suggestionPeople.setFriends(new ArrayList<People>());
//                    people_adapter = new People_Adapter(context, mainViewPager, mProgressBar,imageView,mSwipeRefreshLayout_list,recyclerList,suggestionPeople,false);
//                    recyclerList.setAdapter(people_adapter);
//                    new AsyncLoadHamkaran(context,
//                            recyclerList,
//                            people_adapter,
//                            mProgressBar,
//                            mSwipeRefreshLayout_list,
//                            imageView,
//                            suggestionPeople,
//                            false,
//                            index).execute();
//
//                }
//            }
//        });
//        {
//            //First Load - init
//            suggestionPeople.setUsers(new ArrayList<People>());
//            suggestionPeople.setFriends(new ArrayList<People>());
//            people_adapter = new People_Adapter(context, mainViewPager, mProgressBar,imageView,mSwipeRefreshLayout_list,recyclerList,suggestionPeople,false);
//            recyclerList.setAdapter(people_adapter);
//            new AsyncLoadHamkaran(context,
//                    recyclerList,
//                    people_adapter,
//                    mProgressBar,
//                    mSwipeRefreshLayout_list,
//                    imageView,
//                    suggestionPeople,
//                    false,
//                    index).execute();
//
//        }

//        return view;
        return null;
    }

    private void changeTab() {
        index = 0 ;
//
//        if(btnRequests.isChecked()) {
//
//            mSwipeRefreshLayout_list.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
//                @Override
//                public void onRefresh() {
//                    new AsyncLoadFriendRequests(context,imageView, recyclerList, mProgressBar, mSwipeRefreshLayout_list).execute();
//                }
//            });
//            new AsyncLoadFriendRequests(context, imageView, recyclerList, mProgressBar, mSwipeRefreshLayout_list).execute();
//        }else if (btnKarbaran.isChecked()){
//
//
//
//        }else if (btnHamkaran.isChecked()){
//


        }
    }

//    private class AsyncLoadFriendRequests extends AsyncTask<Void, Void, ResponseFriendRequests> {
//
//        private final Context context;
//        private final RecyclerView recyclerList;
//        private final DilatingDotsProgressBar mProgressBar;
//        private final SwipeRefreshLayout mSwipeRefreshLayout_list;
//        private final ImageView imageView;
//
//        public AsyncLoadFriendRequests(Context context, ImageView imageView, RecyclerView recyclerList, DilatingDotsProgressBar mProgressBar, SwipeRefreshLayout mSwipeRefreshLayout_list) {
//            this.context = context ;
//            this.recyclerList = recyclerList ;
//            this.mProgressBar = mProgressBar ;
//            this.mSwipeRefreshLayout_list = mSwipeRefreshLayout_list ;
//            this.imageView = imageView;
//        }
//
//        @Override
//        protected void onPreExecute() {
//            super.onPreExecute();
//            mProgressBar.showNow();
//            mSwipeRefreshLayout_list.setRefreshing(true);
//        }
//
//        @Override
//        protected ResponseFriendRequests doInBackground(Void... params) {
//
//            RestApiHelper myParser = new RestApiHelper();
//            return myParser.getFriendRequest(mahanApplicationGlobal.values.getString(Statics.COOKIES, "") + " " + mahanApplicationGlobal.values.getString("cookies_login", ""));
//
//        }
//
//        @Override
//        protected void onPostExecute(ResponseFriendRequests responseFriendRequests) {
//            super.onPostExecute(responseFriendRequests);
//            mProgressBar.hideNow();
//            mSwipeRefreshLayout_list.setRefreshing(false);
//
//            //imageView
//
//            if (responseFriendRequests != null) {
//                if (responseFriendRequests.getRequests().getItem() != null && responseFriendRequests.getRequests().getItem().size() >= 1) {
//                    adapter_friendRequest = new FriendRequest_AD_SajjadTest(context, responseFriendRequests.getRequests(), recyclerList, mProgressBar);
//
//                    imageView.setVisibility(View.INVISIBLE);
//                    recyclerList.setLayoutManager(new LinearLayoutManager(context));
//                    recyclerList.setAdapter(adapter_friendRequest);
//                    recyclerList.setVisibility(View.VISIBLE);
//                }else {
//                    recyclerList.setVisibility(View.GONE);
//                    imageView.setVisibility(View.VISIBLE);
////                    new SweetAlertDialog(context, SweetAlertDialog.ERROR_TYPE).setTitleText("Error")
////                            .setContentText(context.getString(R.string.NOTING_TO_SHOW))
////                            .show();
//                }
//            } else {
////                new SweetAlertDialog(context, SweetAlertDialog.ERROR_TYPE).setTitleText("Error")
////                        .setContentText(context.getString(R.string.NOTING_TO_SHOW))
////                        .show();
//            }
//        }
//
//    }
//
//}