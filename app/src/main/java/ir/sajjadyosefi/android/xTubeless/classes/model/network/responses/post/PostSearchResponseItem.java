package ir.sajjadyosefi.android.xTubeless.classes.model.network.responses.post;

import android.content.Context;
import android.view.View;

import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import java.io.Serializable;

import ir.sajjadyosefi.android.xTubeless.Global;
import ir.sajjadyosefi.android.xTubeless.R;
import ir.sajjadyosefi.android.xTubeless.classes.model.TubelessObject;
import ir.sajjadyosefi.android.xTubeless.classes.model.post.IItems;
import ir.sajjadyosefi.android.xTubeless.classes.model.viewHolder.PostItemViewHolder;
import ir.sajjadyosefi.android.xTubeless.classes.model.viewHolder.PostViewHolder;


public class PostSearchResponseItem extends TubelessObject implements Serializable ,IItems{

    private String id;
    private int fkFindedType;
    private String findDate;
    private String n;
    private String fName;
    private String lName;
    private String fatherName;
    private String identityNumber;
    private String nationalCode;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getFkFindedType() {
        return fkFindedType;
    }

    public void setFkFindedType(int fkFindedType) {
        this.fkFindedType = fkFindedType;
    }

    public String getFindDate() {
        return findDate;
    }

    public void setFindDate(String findDate) {
        this.findDate = findDate;
    }

    public String getN() {
        return n;
    }

    public void setN(String n) {
        this.n = n;
    }

    public String getfName() {
        return fName;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    public String getlName() {
        return lName;
    }

    public void setlName(String lName) {
        this.lName = lName;
    }

    public String getFatherName() {
        return fatherName;
    }

    public void setFatherName(String fatherName) {
        this.fatherName = fatherName;
    }

    public String getIdentityNumber() {
        return identityNumber;
    }

    public void setIdentityNumber(String identityNumber) {
        this.identityNumber = identityNumber;
    }

    public String getNationalCode() {
        return nationalCode;
    }

    public void setNationalCode(String nationalCode) {
        this.nationalCode = nationalCode;
    }

    public void fill(Context mContext, int listType , PostViewHolder holder0, IItems item) {
        PostItemViewHolder holder = (PostItemViewHolder) holder0;
        final PostSearchResponseItem timelineItem = (PostSearchResponseItem)item;


        StringBuilder date = new StringBuilder();
//        date.append(" ( ");
        date.append("تاریخ پیدا شدن : ");
        date.append(timelineItem.getFindDate());
//        date.append(" ) ");
        holder.textViewDate.setText(date.toString());

        StringBuilder partType = new StringBuilder();
        partType.append("نوع :");
        partType.append(timelineItem.getN());
        partType.append(" ");
        partType.append("کد شناسایی :");
        partType.append(timelineItem.getNationalCode());
        holder.textViewTitle.setText(partType);

        holder.textViewLocation.setText("متعلق به " + timelineItem.fName + " " + timelineItem.lName + " فرزند " + timelineItem.getFatherName());
//        holder.textViewUserName.setText(timelineItem.getUserName());
//        holder.textViewCount.setText(timelineItem.getViewCount() + "");

        holder.textViewUserName.setText("اپراتور شرکت پست");
//        holder.textViewCount.setText(timelineItem.getNationalCode()+ "");


        if (Global.user == null) {
            if (holder.linearLayoutAdmin != null)
                holder.linearLayoutAdmin.setVisibility(View.GONE);
        }else {
            if (Global.user.getUserId() == 140241 || Global.user.getUserName().equals("yosefi1988@gmail.com")|| Global.user.getUserName().equals("09123978522")) {
                holder.linearLayoutAdmin.setVisibility(View.VISIBLE);
            } else {
                holder.linearLayoutAdmin.setVisibility(View.GONE);
            }
        }


        loadImage(holder);

    }
    private void loadImage(PostItemViewHolder holder) {

        Picasso.get()
                .load("https://newtracking.post.ir/Content/Image/postarm.png")
                .placeholder(R.drawable.bg_search)
//                    //.centerInside()
//                    .transform(transformation)
                .into(holder.imageViewUserAvatar, new Callback() {
                    @Override
                    public void onSuccess() {

                    }

                    @Override
                    public void onError(Exception e) {
                        Picasso.get()
                                .load(R.drawable.png_user)
//                                    .transform(transformation)
                                .into(holder.imageViewUserAvatar);
                    }
                });


    }

}
