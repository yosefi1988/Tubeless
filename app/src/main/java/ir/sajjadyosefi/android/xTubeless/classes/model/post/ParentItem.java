package ir.sajjadyosefi.android.xTubeless.classes.model.post;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.PopupMenu;

import ir.sajjadyosefi.android.xTubeless.Adapter.XAdapter;
import ir.sajjadyosefi.android.xTubeless.Global;
import ir.sajjadyosefi.android.xTubeless.R;
import ir.sajjadyosefi.android.xTubeless.activity.TubelessActivity;
import ir.sajjadyosefi.android.xTubeless.activity.common.ContactUsActivity;
import ir.sajjadyosefi.android.xTubeless.classes.model.exception.TubelessException;
import ir.sajjadyosefi.android.xTubeless.classes.model.response.ServerResponseBase;
import ir.sajjadyosefi.android.xTubeless.classes.model.viewHolder.PostViewHolder;
import ir.sajjadyosefi.android.xTubeless.classes.model.viewHolder.TimelineItemViewHolder;
import ir.sajjadyosefi.android.xTubeless.networkLayout.retrofit.TubelessRetrofitCallbackss;
import retrofit2.Call;

import static ir.sajjadyosefi.android.xTubeless.classes.model.exception.TubelessException.TUBELESS_OPERATION_NOT_COMPLETE;
import static ir.sajjadyosefi.android.xTubeless.classes.model.exception.TubelessException.TUBELESS_TRY_AGAIN;

public abstract class ParentItem implements IItems {

    private int blogID;
    private int userID;

    public int getBlogID() {
        return blogID;
    }

    public void setBlogID(int blogID) {
        this.blogID = blogID;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public void fill(Context mContext, XAdapter xAdapter, int listType, PostViewHolder holder0, IItems item, int position) {

        TimelineItemViewHolder holder = (TimelineItemViewHolder) holder0;
        final ParentItem timelineItem = (ParentItem) item;

//        if (Global.user != null) {
//            if (Global.user.getMobileNumber().contains("09123678522")) {
//
//
//            }else {
//                Toast.makeText(mContext,mContext.getResources().getString(R.string.not_access),Toast.LENGTH_SHORT).show();
//            }
//        }else {
//            Toast.makeText(mContext,mContext.getResources().getString(R.string.not_login),Toast.LENGTH_SHORT).show();
//        }


        if (Global.user == null) {
            if (holder.linearLayoutAdmin != null)
                holder.linearLayoutAdmin.setVisibility(View.GONE);
        } else {
            if (Global.user.isAdmin()) {
                holder.linearLayoutAdmin.setVisibility(View.VISIBLE);
            } else {
                holder.linearLayoutAdmin.setVisibility(View.GONE);
            }
        }


        onclicks(mContext,xAdapter, listType, holder, timelineItem,position);
    }


    View.OnClickListener onDeleteClickListener = null;
    private void onclicks(Context mContext, XAdapter xAdapter, int listType, TimelineItemViewHolder holder, ParentItem timelineItem, int position) {

        holder.imageViewMenu.setClickable(true);
        holder.imageViewMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                modal(mContext,view,listType,timelineItem);
            }
        });



        View.OnClickListener onShareClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                share(mContext ,listType, timelineItem);
            }
        };

        onDeleteClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(mContext)
                        .setTitle(mContext.getString(R.string.Delete))
                        .setMessage(mContext.getString(R.string.DeletePostMessage))
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .setPositiveButton(mContext.getString(R.string.yes), new DialogInterface.OnClickListener() {

                            public void onClick(DialogInterface dialog, int whichButton) {

                                String userId = "";

                                if (Global.user.getUserId() == 0){
                                    userId = Global.user.getEmail();
                                }else {
                                    userId = Global.user.getUserId() + "";
                                }

                                Global.apiManagerTubeless.deleteTimelineItem(timelineItem.getBlogID(), userId, new TubelessRetrofitCallbackss(mContext, ServerResponseBase.class) {
                                    @Override
                                    public void t_beforeSendRequest() {
                                        ((TubelessActivity)mContext).progressDialog.show();

                                    }

                                    @Override
                                    public void t_afterGetResponse() {
                                        ((TubelessActivity)mContext).progressDialog.hide();
                                    }

                                    @Override
                                    public void t_complite() {
                                    }

                                    @Override
                                    public void t_responseNull() {
                                        new TubelessException().handleServerMessage(mContext,new TubelessException(TUBELESS_OPERATION_NOT_COMPLETE));
                                    }

                                    @Override
                                    public void t_retry(Call<Object> call) {
                                        new TubelessException().handleServerMessage(mContext,new TubelessException(TUBELESS_TRY_AGAIN));
                                    }

                                    @Override
                                    public void t_onSuccess(Object response) {
                                        xAdapter.removeItem(listType,position);
                                    }
                                });
                            }})
                        .setNegativeButton(mContext.getString(R.string.cancel), null).show();
            }
        };


        View.OnClickListener onInvisibleClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(mContext)
                        .setTitle("Set Invisible")
                        .setMessage("Do you really want to set invisible?")
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {

                            public void onClick(DialogInterface dialog, int whichButton) {

                                String userId = "";

                                if (Global.user.getUserId() == 0){
                                    userId = Global.user.getEmail();
                                }else {
                                    userId = Global.user.getUserId() + "";
                                }

                                Global.apiManagerTubeless.invisibleTimelineItem(timelineItem.getBlogID(), userId, new TubelessRetrofitCallbackss(mContext, ServerResponseBase.class) {
                                    @Override
                                    public void t_beforeSendRequest() {
                                        ((TubelessActivity)mContext).progressDialog.show();

                                    }

                                    @Override
                                    public void t_afterGetResponse() {
                                        ((TubelessActivity)mContext).progressDialog.hide();
                                    }

                                    @Override
                                    public void t_complite() {
                                    }

                                    @Override
                                    public void t_responseNull() {
                                        new TubelessException().handleServerMessage(mContext,new TubelessException(TUBELESS_OPERATION_NOT_COMPLETE));
                                    }

                                    @Override
                                    public void t_retry(Call<Object> call) {
                                        new TubelessException().handleServerMessage(mContext,new TubelessException(TUBELESS_TRY_AGAIN));
                                    }

                                    @Override
                                    public void t_onSuccess(Object response) {
                                        xAdapter.removeItem(listType,position);
                                    }
                                });
                            }})
                        .setNegativeButton(android.R.string.no, null).show();
            }
        };



        holder.textViewDelete.setOnClickListener(onDeleteClickListener);
        holder.imageViewDelete.setOnClickListener(onDeleteClickListener);

        holder.textViewInvisible.setOnClickListener(onInvisibleClickListener);
        holder.imageViewInvisible.setOnClickListener(onInvisibleClickListener);

        holder.imageViewShare.setOnClickListener(onShareClickListener);
        holder.textViewShare.setOnClickListener(onShareClickListener);
    }


    public void modal(Context mContext, View view, int listType, ParentItem timelineItem){
        PopupMenu popup = new PopupMenu(mContext, view);
        MenuInflater inflater = popup.getMenuInflater();
        if (Global.user != null && Global.user.isAdmin())
            inflater.inflate(R.menu.actions_for_admin, popup.getMenu());
        else if (Global.user != null && timelineItem.getUserID() == Global.user.getUserId())
            inflater.inflate(R.menu.actions_for_owner, popup.getMenu());
        else if (Global.user != null)
            inflater.inflate(R.menu.actions_for_users, popup.getMenu());
        else
            inflater.inflate(R.menu.actions_for_everyone, popup.getMenu());


        popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.pmnuDelete:

                        onDeleteClickListener.onClick(view);

                        break;
                    case R.id.pmnuReport:

                        Bundle bundle = new Bundle();
                        bundle.putInt(ContactUsActivity.Type , ContactUsActivity.CONTACT_US);
                        bundle.putString(ContactUsActivity.Title , "محتوی نامناسب , کد : " + timelineItem.getBlogID());
                        bundle.putString(ContactUsActivity.Text , "توضیح : ");
                        ((Activity)mContext).startActivity(ContactUsActivity.getIntent(mContext,bundle));
                        break;
                    case R.id.pmnuShare:
                        share(mContext ,listType, timelineItem);
                        break;
                }
                return false;
            }
        });
        popup.show();
    }


    protected abstract void share(Context mContext, int listType, ParentItem timelineItem);

}
