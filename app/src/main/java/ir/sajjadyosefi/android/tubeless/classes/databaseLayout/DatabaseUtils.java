package ir.sajjadyosefi.android.tubeless.classes.databaseLayout;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;

import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.DeleteBuilder;
import com.j256.ormlite.stmt.UpdateBuilder;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import ir.sajjadyosefi.android.tubeless.classes.model.radyab.CarLogDetails;
import ir.sajjadyosefi.android.tubeless.classes.model.radyab.RadyabSetting;
import ir.sajjadyosefi.android.tubeless.classes.model.radyab.SlaveDetails;


/**
 * Created by sajjad on 5/10/2018.
 */

public class DatabaseUtils {

    private static DatabaseHelper databaseHelper = null;
    static Dao<CarLogDetails, Integer> carLogDetailsDao;
    static Dao<SlaveDetails, Integer> slaveDetailsDao ;
    static Dao<RadyabSetting, Integer> radyabSettingDao ;

    public DatabaseUtils(Context context) {
        try {
            slaveDetailsDao = getHelper(context).getSlaveDao();
            carLogDetailsDao = getHelper(context).getLogDao();
            radyabSettingDao = getHelper(context).getRadyabSettingDao();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public int saveSlaveDetails(SlaveDetails slaveDetails) {
        SlaveDetails slaveDetails1 = slaveDetails;
        int id = 0;

        try {
            // This is how, a reference of DAO object can be done

            //This is the way to insert data into a database table
            slaveDetailsDao.create(slaveDetails1);
            id = slaveDetails1.getSlaveId();


        } catch (Exception ex) {
            return id;
        }
        clean();
        return id;
    }

    public boolean saveCarLog(CarLogDetails logDetails) {
        CarLogDetails carLogDetails = logDetails;
        Calendar calendar = Calendar.getInstance();
        carLogDetails.setDate(calendar.getTime());
        try {
            //This is the way to insert data into a database table
            carLogDetailsDao.create(carLogDetails);
        } catch (Exception ex) {
            return false;
        }
        clean();
        return true;
    }

    public List<RadyabSetting> loadMadterList (){
        List<RadyabSetting> radyabSettingList = new ArrayList<RadyabSetting>();
        try {
            // This is how, a reference of DAO object can be done
            radyabSettingList = radyabSettingDao.queryForAll();

        } catch (Exception ex) {

        }
        finally {
            clean();
            return radyabSettingList;
        }
    }
    public List<SlaveDetails> loadSlaveList (){
        List<SlaveDetails> slaveList = new ArrayList<SlaveDetails>();
        try {
            // This is how, a reference of DAO object can be done
            slaveList = slaveDetailsDao.queryForAll();

        } catch (Exception ex) {

        }
        finally {
            clean();
            return slaveList;
        }
    }
    public SlaveDetails loadSlaveDetails (int id){

        SlaveDetails slaveDetails = null;
        try {
            // This is how, a reference of DAO object can be done
            slaveDetails = slaveDetailsDao.queryForId(id);

        } catch (Exception ex) {

        }
        finally {
            clean();
            return slaveDetails;
        }
    }

    public List<CarLogDetails> loadCarLogList (){

        List<CarLogDetails> carLogList = null;
        try {
            // This is how, a reference of DAO object can be done
            carLogList = carLogDetailsDao.queryForAll();

        } catch (Exception ex) {

        }
        finally {
            clean();
            return carLogList;
        }
    }
    public CarLogDetails loadCarLogDetails (int id){

        CarLogDetails carLogDetails = null;
        try {
            // This is how, a reference of DAO object can be done
            carLogDetails = carLogDetailsDao.queryForId(id);

        } catch (Exception ex) {

        }
        finally {
            clean();
            return carLogDetails;
        }
    }


    // This is how, DatabaseHelper can be initialized for future use
    private DatabaseHelper getHelper(Context mContext) {
        if (databaseHelper == null) {
            databaseHelper = OpenHelperManager.getHelper(mContext, DatabaseHelper.class);
        }
        return databaseHelper;
    }

    public void clean(){
//        new Handler().postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                if (databaseHelper != null) {
//                    OpenHelperManager.releaseHelper();
//                    databaseHelper = null;
//                }
//            }
//        }, 10000);
        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
            @Override
            public void run() {
                if (databaseHelper != null) {
                    OpenHelperManager.releaseHelper();
                    databaseHelper = null;
                }
            }
        }, 5000);
    }

    public boolean updateSlaveToken (int id,SlaveDetails slaveDetails) {
        try {
            UpdateBuilder<SlaveDetails, Integer> updateBuilder = slaveDetailsDao.updateBuilder();
            //updateBuilder.where().eq("Id", 1);
            updateBuilder.updateColumnValue("slavePushNotificationToken" /* column */, slaveDetails.getSlavePushNotificationToken() /* value */);
            updateBuilder.updateColumnValue("slavePhoneNumber" /* column */, slaveDetails.getSlavePhoneNumber() /* value */);
            updateBuilder.updateColumnValue("isSlaveTokenValid" /* column */, slaveDetails.isSlaveTokenValid /* value */);
            updateBuilder.updateColumnValue("id",id);
            return true;
        }catch (Exception ex){
            return false;
        }
    }


    public boolean updateSlaveDetails (int id,SlaveDetails slaveDetails) {
        try {
            UpdateBuilder<SlaveDetails, Integer> updateBuilder = slaveDetailsDao.updateBuilder();
            updateBuilder.where().eq("slaveId", id);
            updateBuilder.updateColumnValue("carName"       /* column */, slaveDetails.getCarName()         /* value */);
            updateBuilder.updateColumnValue("carPicture"    /* column */, slaveDetails.getCarPicture()      /* value */);
            updateBuilder.updateColumnValue("shomareShasi"  /* column */, slaveDetails.getShomareShasi()    /* value */);
            updateBuilder.updateColumnValue("shomareMotor"  /* column */,slaveDetails.getShomareMotor())    /* value */;
            updateBuilder.updateColumnValue("shomarePelak"  /* column */,slaveDetails.getShomarePelak())    /* value */;
            updateBuilder.update();
            return true;
        }catch (Exception ex){
            return false;
        }
    }
    public boolean updateDefault(int id,boolean isDefault ) {
        try {
            UpdateBuilder<SlaveDetails, Integer> updateBuilder = slaveDetailsDao.updateBuilder();
            updateBuilder.where().eq("slaveId", id);
            updateBuilder.updateColumnValue("isDefault"  /* column */,isDefault)    /* value */;
            updateBuilder.update();
            return true;
        }catch (Exception ex){
            return false;
        }
    }

    public boolean updateMasterToken(RadyabSetting radyabSetting) {
        try {
            UpdateBuilder<RadyabSetting, Integer> updateBuilder = radyabSettingDao.updateBuilder();
            //updateBuilder.where().eq("Id", 1);
            updateBuilder.updateColumnValue("masterPushNotificationToken" /* column */, radyabSetting.getMasterPushNotificationToken() /* value */);
            updateBuilder.updateColumnValue("masterPhoneNamber" /* column */, radyabSetting.getMasterPhoneNamber() /* value */);
            updateBuilder.updateColumnValue("isMasterTokenValid" /* column */, radyabSetting.isMasterTokenValid() /* value */);
            updateBuilder.update();
            return true;
        }catch (Exception ex){
            return false;
        }
    }

    public boolean saveMasterToken(RadyabSetting radyabSetting) {
        RadyabSetting radyabSetting1 = radyabSetting;

        try {
            //This is the way to insert data into a database table
            radyabSettingDao.create(radyabSetting1);
        } catch (Exception ex) {
            return false;
        }
        clean();
        return true;
    }

    public boolean deleteSlave(Context context , int slaveId) {
        try {
            slaveDetailsDao = getHelper(context).getSlaveDao();
            DeleteBuilder<SlaveDetails, Integer> deleteBuilder = slaveDetailsDao.deleteBuilder();
            deleteBuilder.where().eq("slaveId", slaveId);
            deleteBuilder.delete();

            return true;
        }catch (Exception ex){
            return false;
        }
    }
}
