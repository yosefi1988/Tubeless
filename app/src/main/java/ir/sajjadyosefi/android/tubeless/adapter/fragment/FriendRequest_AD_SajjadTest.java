package ir.sajjadyosefi.android.tubeless.adapter.fragment;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.zl.reik.dilatingdotsprogressbar.DilatingDotsProgressBar;

import ir.sajjadyosefi.android.tubeless.classes.model.NetworkLayout.ServerResponseConfig;


/**
 * Created by a.fereydoni on 2/2/2016.
 */
public class FriendRequest_AD_SajjadTest extends RecyclerView.Adapter<FriendRequest_AD_SajjadTest.PostViewHolder> {

    private final DilatingDotsProgressBar progressBar;
    //FriendRequests friendRequests;
    Context cx;
    RecyclerView listView;
    //ImageLoader imageLoader;
    String userId;

    public FriendRequest_AD_SajjadTest(Context context,
                                       //FriendRequests notifyFeed,
                                       RecyclerView listView, DilatingDotsProgressBar dilatingDotsProgressBar) {

        this.listView = listView;
        //this.friendRequests = notifyFeed;
        cx = context;
        //imageLoader = new ImageLoader(((Activity)context).getApplicationContext());
        this.progressBar = dilatingDotsProgressBar ;
    }

    public class PostViewHolder extends RecyclerView.ViewHolder {

        TextView tvContent, tvName, tvDate;
        ImageView im;
        Button btAccept, btIgnore;

        public PostViewHolder(View view) {
            super(view);
//            tvContent = (TextView) view.findViewById(R.id.content);
//            tvName = (TextView) view.findViewById(R.id.requestName);
////            tvDate = (TextView) view.findViewById(R.id.date);
//            im = (ImageView) view.findViewById(R.id.poster_pic);
//            btAccept = (Button) view.findViewById(R.id.accept);
//            btIgnore = (Button) view.findViewById(R.id.ignore);
        }
    }

    @Override
    public int getItemCount() {
//        if (friendRequests.getItem() == null)
            return 0;
//        else
//            return (friendRequests.getItem().size());
    }

    @Override
    public PostViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//        return new TimeLineItemViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_friend_request, parent, false));
        return null;
    }

    @Override
    public void onBindViewHolder(final PostViewHolder holder, final int pos) {

//        if(friendRequests.getItem().get(pos).getMessage()!= null)
//            holder.tvContent.setText(Html.fromHtml(friendRequests.getItem().get(pos).getMessage()), TextView.BufferType.SPANNABLE);
//
//        holder.tvName.setText(friendRequests.getItem().get(pos).getFull_name());
//        holder.btAccept.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                new AsyncAcceptFriend().execute(friendRequests.getItem().get(pos).getUser_id());
//            }
//        });
//        holder.btIgnore.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                new AsyncDeleteFriend().execute(friendRequests.getItem().get(pos).getUser_id());
//            }
//        });
//
//
//        Common.getUserImageAndFill(cx , friendRequests.getItem().get(pos).getUser_image(),holder.im);
//
//        holder.im.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                userId = friendRequests.getItem().get(pos).getUser_id();
//                new AsyncLoadProfile(((Activity)cx),userId,mProgressBar).execute();
//
//            }
//        });
    }


    private class AsyncAcceptFriend extends AsyncTask<String, Void, ServerResponseConfig> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            //cx.mProgressBar.setVisibility(View.VISIBLE);
        }

        @Override
        protected ServerResponseConfig doInBackground(String... params) {

//            SharedPreferences values = cx.getSharedPreferences(Statics.MAHAN, 0);
//            RestApiHelper myParser = new RestApiHelper();
//            return myParser.acceptFriendRequest(values.getString(Statics.COOKIES, "") + " " + values.getString("cookies_login", ""), params[0], values.getString("token", ""));

            return null;
        }

        @Override
        protected void onPostExecute(ServerResponseConfig result) {
            super.onPostExecute(result);
            //cx.mProgressBar.setVisibility(View.INVISIBLE);
//            if (result.getResultStatus().getCode() == 200) {
//                listView.setAdapter(null);
//
//                //result.getResultStatus().getMessage()
//                new SweetAlertDialog(cx,SweetAlertDialog.SUCCESS_TYPE).setTitleText("با موفقیت انجام شد")
//                        .setContentText("OK")
//                        .show();
//
//                //new AsyncSendSimpleIMMessage(cx,result.getResultStatus().getMessage()).execute();
//                new AsyncSendSimpleIMMessage(cx,result.getResultStatus().getMessage()).execute();
//            } else {
//                new SweetAlertDialog(cx,SweetAlertDialog.WARNING_TYPE).setTitleText("Warning")
//                        .setContentText(result.getResultStatus().getMessage())
//                        .show();
//            }
        }

    }
}