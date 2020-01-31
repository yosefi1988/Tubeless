package ir.sajjadyosefi.android.tubeless.adapter.fragment;

import android.app.Activity;
import android.os.AsyncTask;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import ir.sajjadyosefi.android.xTubeless.classes.modelY.Notification;
import ir.sajjadyosefi.android.xTubeless.classes.modelY.ResponseNotification;


/**
 * Created by a.fereydoni on 2/2/2016.
 */
public class Notify_AD_SajjadTest extends RecyclerView.Adapter<Notify_AD_SajjadTest.PostViewHolder> {

    Notification notifications;
    Activity cx;
//    ImageLoader imageLoader;
    String userId;
    RecyclerView listView;

    boolean goNews = false;
    private int index = 0;
    private int previousTotal = 0;
    private boolean loading = true;
    private int visibleThreshold = 5;
    int firstVisibleItem, visibleItemCount, totalItemCount;

    public Notify_AD_SajjadTest(Activity activity, Notification _notifications, final RecyclerView listView, final LinearLayoutManager llm) {

        this.notifications = _notifications;
        cx = activity;
        this.listView = listView;
//        imageLoader = new ImageLoader(activity.getApplicationContext());

        listView.setLayoutManager(llm);
        listView.addOnScrollListener(new RecyclerView.OnScrollListener() {

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                visibleItemCount = listView.getChildCount();
                totalItemCount = llm.getItemCount();
                firstVisibleItem = llm.findFirstVisibleItemPosition();

                if (loading) {
                    if (totalItemCount > previousTotal) {
                        loading = false;
                        previousTotal = totalItemCount;
                    }
                }
                if (!loading && (totalItemCount - visibleItemCount)
                        <= (firstVisibleItem + visibleThreshold)) {
                    // End has been reached

                    Log.e("Yaeye NNNNNNNNNNN!", "end called");

                    if (goNews) {
                        new AsyncLoadNotify().execute();
                        loading = true;
                    }
                    else goNews = true;

                    // Do something

                }
            }
        });
    }

    public class PostViewHolder extends RecyclerView.ViewHolder {

        TextView tvContent;
        TextView tvDate;
        ImageView im;
        LinearLayout mainLayout;

        public PostViewHolder(View view) {
            super(view);
//            tvContent = (TextView) view.findViewById(R.id.content);
//            mainLayout = (LinearLayout) view.findViewById(R.id.main_profile_layout);
//            tvDate = (TextView) view.findViewById(R.id.date);
//            im = (ImageView) view.findViewById(R.id.poster_pic);
        }
    }

    @Override
    public int getItemCount() {
        return (notifications.getItems().size());
    }

    @Override
    public PostViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//        return new TimeLineItemViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_notif, parent, false));
        return null;
    }

    @Override
    public void onBindViewHolder(final PostViewHolder holder, final int pos) {



        holder.tvContent.setText(
                Html.fromHtml(
                        ((notifications.getItems().get(pos).getMessage() == null) ? "" : notifications.getItems().get(pos).getMessage())
                ), TextView.BufferType.SPANNABLE);
        holder.tvDate.setText(notifications.getItems().get((pos)).getDate());
        //imageLoader.DisplayImage(notifications.getItems().get((pos)).getUser_image(), holder.im, true);



//        Common.getUserImageAndFill(cx , notifications.getItems().get((pos)).getUser_image(),holder.im);
//
//        holder.im.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                userId = notifications.getItems().get((pos)).getUser_id();
//                new AsyncLoadProfileSajjadTest(cx,userId).execute();
//            }
//        });
//
//        holder.mainLayout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                userId = notifications.getItems().get(pos).getUser_id();
//                new AsyncLoadProfileSajjadTest(cx,userId).execute();
//            }
//        });
    }


    private class AsyncLoadNotify extends AsyncTask<Void, Void, ResponseNotification> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            //cx.mProgressBar.setVisibility(View.VISIBLE);
        }

        @Override
        protected ResponseNotification doInBackground(Void... params) {
//            SharedPreferences values = cx.getSharedPreferences(Statics.MAHAN, 0);
//            RestApiHelper myParser = new RestApiHelper();
//            return myParser.getNotificationfromserver(values.getString(Statics.COOKIES, "") + " " + values.getString("cookies_login", ""), index + "");

            return null;
        }

        @Override
        protected void onPostExecute(ResponseNotification responseNotification) {
            super.onPostExecute(responseNotification);
            //cx.mProgressBar.setVisibility(View.INVISIBLE);
            try {


                if (responseNotification != null) {
                    if (responseNotification.getNotification().getItems().size() > 0) {
                        for (int i = 0; i < responseNotification.getNotification().getItems().size(); i++)
                            notifications.addItem(responseNotification.getNotification().getItems().get(i));
                        //cx.adapter_notify.notifyDataSetChanged();
                        index++;
                    } else {
//                    Toast.makeText(activity, "Nothing to show !", Toast.LENGTH_SHORT).show();
                    }
                } else {

//                    new SweetAlertDialog(cx, SweetAlertDialog.ERROR_TYPE).setTitleText("Error")
//                            .setContentText(cx.getString(R.string.ERROR_IN_CONNECTION))
//                            .show();
                }
            }catch (Exception ex){
                ex.printStackTrace();
            }
        }
    }


}