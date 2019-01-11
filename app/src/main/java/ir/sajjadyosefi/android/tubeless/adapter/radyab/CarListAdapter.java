package ir.sajjadyosefi.android.tubeless.adapter.radyab;


import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;
import java.util.zip.Inflater;

import ir.sajjadyosefi.android.tubeless.Global;
import ir.sajjadyosefi.android.tubeless.R;
import ir.sajjadyosefi.android.tubeless.activity.innerActivity.ImageAndVideoPlayer;
import ir.sajjadyosefi.android.tubeless.activity.radyab.MainRadyabActivity;
import ir.sajjadyosefi.android.tubeless.activity.radyab.RadyabCarActivity;
import ir.sajjadyosefi.android.tubeless.activity.radyab.SelectCarForRadyab;
import ir.sajjadyosefi.android.tubeless.classes.databaseLayout.DatabaseUtils;
import ir.sajjadyosefi.android.tubeless.classes.model.radyab.CarLogDetails;
import ir.sajjadyosefi.android.tubeless.classes.model.radyab.SlaveDetails;

/**
 * Created by sajjad on 5/23/2018.
 */

public class CarListAdapter extends RecyclerView.Adapter<CarListAdapter.CarSlaveViewHolder> {
    private List<SlaveDetails> carList;
    private Context context;
    DatabaseUtils databaseUtils;
    CarListAdapter adapter ;

    public CarListAdapter(Context context , List<SlaveDetails> carList) {
        this.carList = carList;
        this.context = context;
        this.adapter = this;

        ///////////////Default Slave //////////////////
        databaseUtils = new DatabaseUtils(context);
            boolean haveDefault = false;
            for (SlaveDetails item :carList) {
                if (item.isDefault() == true) {
                    haveDefault = true;
                    break;
                }
            }
            if(!haveDefault && carList.size() >= 1) {
                databaseUtils.updateDefault(carList.get(0).getSlaveId(), true);
                carList.get(0).setDefault(true);
                adapter.notifyDataSetChanged();
        }
        //////////////////////////////////////////////
    }

    @Override
    public CarSlaveViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_car_radyab,parent,false);

        return new CarSlaveViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CarSlaveViewHolder holder, final int position) {
        final SlaveDetails slaveDetails = carList.get(position);
        holder.textViewTitle.setText(slaveDetails.getCarName());
        try {
//            Picasso.with(context)
//                    .load(Global.mUser.getUserImage())
//                    .into(holder.imageViewCar);

            Bitmap bitmap = BitmapFactory.decodeFile(slaveDetails.getCarPicture());
            holder.imageViewCar.setImageBitmap(bitmap);
            holder.imageViewCar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //slaveDetails.getCarPicture()
                    Intent viewOfPlayer = new Intent(context, ImageAndVideoPlayer.class);
                    viewOfPlayer.putExtra("fileFormat", "jpg");
                    viewOfPlayer.putExtra("filePath",slaveDetails.getCarPicture());
                    context.startActivity(viewOfPlayer);
                    ((Activity) context).overridePendingTransition(R.anim.fadeout, R.anim.fadein);
                }
            });

        }catch (Exception ex){}
        holder.textViewDescription.setText(slaveDetails.getShomarePelak());
        holder.linearLayoutTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //position
                Intent intent = new Intent(context, MainRadyabActivity.class);
                Bundle bundle = new Bundle();
                bundle.putInt("IdSlave",slaveDetails.getSlaveId());
                intent.putExtras(bundle);
                context.startActivity(intent);
            }
        });
        holder.buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setCancelable(true);
                builder.setTitle("حذف");
                builder.setMessage("آیا میخواهید حذف کنید ؟");
                builder.setPositiveButton("Confirm",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                databaseUtils.deleteSlave(context, slaveDetails.getSlaveId());
                                carList.remove(position);
                                adapter.notifyDataSetChanged();
                            }
                        });
                builder.setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

                AlertDialog dialog = builder.create();
                dialog.show();


            }
        });
        holder.radioButton.setChecked(slaveDetails.isDefault);
        holder.radioButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked == true){
                    databaseUtils.updateDefault(slaveDetails.getSlaveId(),true);

                    for (SlaveDetails item :carList) {
                        item.setDefault(false);
                    }
                    carList.get(position).setDefault(true);
                    adapter.notifyDataSetChanged();
                }else {
                    databaseUtils.updateDefault(slaveDetails.getSlaveId(),false);
                }

            }
        });
    }

    @Override
    public int getItemCount() {
        return carList.size();
    }

    public class CarSlaveViewHolder extends RecyclerView.ViewHolder {
        public TextView textViewTitle, textViewDescription, genre;
        public ImageView imageViewCar;
        public RadioButton radioButton;
        public ImageButton buttonDelete;
        public LinearLayout linearLayoutTitle;

        public CarSlaveViewHolder(View itemView) {
            super(itemView);
            textViewTitle = (TextView) itemView.findViewById(R.id.textViewTitle);
            imageViewCar = (ImageView) itemView.findViewById(R.id.imageViewCar);
            textViewDescription = (TextView) itemView.findViewById(R.id.textViewDescription);
            radioButton = (RadioButton) itemView.findViewById(R.id.radioButton);
            buttonDelete = (ImageButton) itemView.findViewById(R.id.buttonDelete);
            linearLayoutTitle = (LinearLayout) itemView.findViewById(R.id.linearLayoutTitle);

        }
    }
}
