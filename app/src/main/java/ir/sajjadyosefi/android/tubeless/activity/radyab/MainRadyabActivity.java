package ir.sajjadyosefi.android.tubeless.activity.radyab;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import ir.sajjadyosefi.android.tubeless.R;
import ir.sajjadyosefi.android.tubeless.asyncTask.radyab.RequestRadyabServiceAsyncTask;
import ir.sajjadyosefi.android.tubeless.classes.databaseLayout.DatabaseUtils;
import ir.sajjadyosefi.android.tubeless.classes.model.radyab.SlaveDetails;
import ir.sajjadyosefi.android.tubeless.classes.model.radyab.requestService.RequestService;
import ir.sajjadyosefi.android.tubeless.view.fragment.radyab.radyabDashbord;
import ir.sajjadyosefi.android.tubeless.view.fragment.radyab.radyabHome;

public class MainRadyabActivity extends AppCompatActivity {

    private TextView mTextMessage;
    private Context mContext;
    public static SlaveDetails slaveDetails ;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener = new BottomNavigationView.OnNavigationItemSelectedListener() {

        Fragment fragment = null;

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home://activity_car_radyab
                    mTextMessage.setText(R.string.title_home);
                    fragment = new radyabHome() ;
                    break;
                case R.id.navigation_dashboard:
                    mTextMessage.setText(R.string.title_dashboard);
                    fragment = new radyabDashbord() ;
                    break;
                case R.id.navigation_map:
                    mTextMessage.setText("maaaaaaaaaaaaap");
                    break;
                case R.id.navigation_notifications:
                    mTextMessage.setText(R.string.title_notifications);
                    break;
            }

            if (fragment != null) {
                FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                ft.replace(R.id.frameLayoutPlaceholder, fragment);
                ft.commit();
            }

            return true;
        }
    };


    public void ButtonClick(View view){
        int id = view.getId();
        RequestRadyabServiceAsyncTask requestRadyabServiceAsyncTask;
        switch (id) {
            case R.id.buttonGeo:
                //slaveDetails
                requestRadyabServiceAsyncTask = new RequestRadyabServiceAsyncTask(mContext, RequestService.SERVICE_GEO) ;
                requestRadyabServiceAsyncTask.execute();
                break;
            case R.id.buttonGeoNetwork:
                //slaveDetails
                requestRadyabServiceAsyncTask = new RequestRadyabServiceAsyncTask(mContext, RequestService.SERVICE_GEO_NETWORK) ;
                requestRadyabServiceAsyncTask.execute();
                break;
            case R.id.buttonAddress:
                //slaveDetails
                requestRadyabServiceAsyncTask = new RequestRadyabServiceAsyncTask(mContext, RequestService.SERVICE_ADDRESS) ;
                requestRadyabServiceAsyncTask.execute();
                break;
            case R.id.buttonBatteryLevel:
                requestRadyabServiceAsyncTask = new RequestRadyabServiceAsyncTask(mContext, RequestService.BATTERY_LEVEL) ;
                requestRadyabServiceAsyncTask.execute();
                break;
            default:


                break;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_radyab);
        mContext = this;
        DatabaseUtils databaseUtils = new DatabaseUtils(mContext);

        Intent intent = getIntent();
        final int slaveId = intent.getExtras().getInt("IdSlave");
        slaveDetails = databaseUtils.loadSlaveDetails(slaveId);

        mTextMessage = (TextView) findViewById(R.id.message);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);


    }

}
