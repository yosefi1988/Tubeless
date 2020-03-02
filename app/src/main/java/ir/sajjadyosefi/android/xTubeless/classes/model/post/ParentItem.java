package ir.sajjadyosefi.android.xTubeless.classes.model.post;

import android.content.Context;
import android.content.DialogInterface;
import android.view.View;

import androidx.appcompat.app.AlertDialog;

import ir.sajjadyosefi.android.xTubeless.Adapter.XAdapter;
import ir.sajjadyosefi.android.xTubeless.Global;
import ir.sajjadyosefi.android.xTubeless.activity.TubelessActivity;
import ir.sajjadyosefi.android.xTubeless.classes.model.exception.TubelessException;
import ir.sajjadyosefi.android.xTubeless.classes.model.response.ServerResponseBase;
import ir.sajjadyosefi.android.xTubeless.classes.model.viewHolder.PostViewHolder;
import ir.sajjadyosefi.android.xTubeless.classes.model.viewHolder.TimelineItemViewHolder;
import ir.sajjadyosefi.android.xTubeless.networkLayout.retrofit.TubelessRetrofitCallbackss;
import retrofit2.Call;

import static ir.sajjadyosefi.android.xTubeless.classes.model.exception.TubelessException.TUBELESS_DATABASE_ERROR;
import static ir.sajjadyosefi.android.xTubeless.classes.model.exception.TubelessException.TUBELESS_OPERATION_NOT_COMPLETE;
import static ir.sajjadyosefi.android.xTubeless.classes.model.exception.TubelessException.TUBELESS_TRY_AGAIN;

public abstract class ParentItem implements IItems {

    public void fill(Context mContext, XAdapter xAdapter, int listType, PostViewHolder holder0, IItems item, int position) {

        TimelineItemViewHolder holder = (TimelineItemViewHolder) holder0;
        final TimelineItem timelineItem = (TimelineItem) item;

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



    private void onclicks(Context mContext, XAdapter xAdapter, int listType, TimelineItemViewHolder holder, TimelineItem timelineItem, int position) {
        View.OnClickListener onShareClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                share(mContext ,listType, timelineItem);
            }
        };

        View.OnClickListener onDeleteClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(mContext)
                        .setTitle("Delete Post")
                        .setMessage("Do you really want to Delete this Post?")
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {

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
                        .setNegativeButton(android.R.string.no, null).show();
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


    protected abstract void share(Context mContext, int listType, TimelineItem timelineItem);
}
