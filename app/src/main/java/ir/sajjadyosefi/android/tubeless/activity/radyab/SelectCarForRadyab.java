package ir.sajjadyosefi.android.tubeless.activity.radyab;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import com.zl.reik.dilatingdotsprogressbar.DilatingDotsProgressBar;

import java.util.ArrayList;
import java.util.List;

import ir.sajjadyosefi.android.tubeless.R;

import ir.sajjadyosefi.android.tubeless.adapter.radyab.CarListAdapter;
import ir.sajjadyosefi.android.xTubeless.activity.MainActivity;
import ir.sajjadyosefi.android.xTubeless.classes.modelY.radyab.SlaveDetails;


public class SelectCarForRadyab extends AppCompatActivity   {

    Context context;
    Button buttonSend ,buttonCancel;
    DilatingDotsProgressBar dilatingDotsProgressBar;
    RecyclerView recyclerViewRadyabCars;
    private List<SlaveDetails> carList = new ArrayList<SlaveDetails>();
    public CarListAdapter mAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = this;
        setContentView(R.layout.activity_radyab_car_list);


        prepareCarData();

        recyclerViewRadyabCars = (RecyclerView) findViewById(R.id.recyclerViewRadyabCars);
        //mAdapter = new CarListAdapter(context, carList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerViewRadyabCars.setLayoutManager(mLayoutManager);
        recyclerViewRadyabCars.setItemAnimator(new DefaultItemAnimator());
        recyclerViewRadyabCars.setAdapter(mAdapter);

        dilatingDotsProgressBar = (DilatingDotsProgressBar) findViewById(R.id.PBSjd);
        buttonSend = (Button) findViewById(R.id.btn1);
        buttonCancel = (Button) findViewById(R.id.btn2);



        buttonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        buttonSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Intent intent = new Intent(getApplicationContext(), RegisterPhoneActivity.class);
//        Bundle bundle = new Bundle();
//        bundle.putInt(LoginActivity.Type, RegisterPhoneActivity.class);
//        intent.putExtras(bundle);
                finish();
                startActivity(intent);
                overridePendingTransition(R.anim.fadeout, R.anim.fadein);
            }
        });
    }

    private void prepareCarData() {
        //DatabaseUtils databaseUtils = new DatabaseUtils(context);
//        List<SlaveDetails> list = databaseUtils.loadSlaveList();
//        for (SlaveDetails item : list) {
//            carList.add(item);
//        }

    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.fadeout, R.anim.fadein);
        finish();
    }
}
