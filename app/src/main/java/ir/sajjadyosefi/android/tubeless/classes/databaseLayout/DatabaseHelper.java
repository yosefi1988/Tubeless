package ir.sajjadyosefi.android.tubeless.classes.databaseLayout;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import java.sql.SQLException;

import ir.sajjadyosefi.android.tubeless.R;
import ir.sajjadyosefi.android.tubeless.classes.model.radyab.RadyabSetting;
import ir.sajjadyosefi.android.tubeless.classes.model.radyab.SlaveDetails;
import ir.sajjadyosefi.android.tubeless.classes.model.radyab.CarLogDetails;


/**
 * Created by sajjad on 5/13/2018.
 */

public class DatabaseHelper extends OrmLiteSqliteOpenHelper {

    /************************************************
     * Suggested Copy/Paste code. Everything from here to the done block.
     ************************************************/

    private static final String DATABASE_NAME = "tubeless.db";
    private static final int DATABASE_VERSION = 1;

    private Dao<CarLogDetails, Integer> logDao;
    private Dao<SlaveDetails, Integer> slaveDao;
    private Dao<RadyabSetting, Integer> radyabSettingDao;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION, R.raw.ormlite_config);
    }

    /************************************************
     * Suggested Copy/Paste Done
     ************************************************/

    @Override
    public void onCreate(SQLiteDatabase sqliteDatabase, ConnectionSource connectionSource) {
        try {
            TableUtils.createTable(connectionSource, CarLogDetails.class);
            TableUtils.createTable(connectionSource, SlaveDetails.class);
        } catch (SQLException e) {
            Log.e(DatabaseHelper.class.getName(), "Unable to create datbases", e);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqliteDatabase, ConnectionSource connectionSource, int oldVer, int newVer) {
        try {

            // In case of change in database of next version of application, please increase the value of DATABASE_VERSION variable, then this method will be invoked
            //automatically. Developer needs to handle the upgrade logic here, i.e. create a new table or a new column to an existing table, take the backups of the
            // existing database etc.

            TableUtils.dropTable(connectionSource, CarLogDetails.class, true);
            TableUtils.dropTable(connectionSource, SlaveDetails.class, true);
            onCreate(sqliteDatabase, connectionSource);

        } catch (SQLException e) {
            Log.e(DatabaseHelper.class.getName(), "Unable to upgrade database from version " + oldVer + " to new "
                    + newVer, e);
        }
    }

    // Create the getDao methods of all database tables to access those from android code.
    // Insert, delete, read, update everything will be happened through DAOs

    public Dao<CarLogDetails, Integer> getLogDao() throws SQLException {
        if (logDao == null) {
            logDao = getDao(CarLogDetails.class);
        }
        return logDao;
    }


    public Dao<SlaveDetails, Integer> getSlaveDao() throws SQLException {
        if (slaveDao == null) {
            slaveDao = getDao(SlaveDetails.class);
        }
        return slaveDao;
    }
    public Dao<RadyabSetting, Integer> getRadyabSettingDao() throws SQLException {
        if (radyabSettingDao == null) {
            radyabSettingDao = getDao(RadyabSetting.class);
        }
        return radyabSettingDao;
    }
}