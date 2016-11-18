package de.bonding.hackathon.group3.weatheralarm.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.RuntimeExceptionDao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * Database helper class to manage opening and upgrading the database.
 */
public class DatabaseHelper extends OrmLiteSqliteOpenHelper {

    // Name of the database file for this App.
    private static final String DATABASE_NAME = "weatheralarm.db";

    // Version of the database structure. Increase everytime the database objects change.
    private static final int DATABASE_VERSION = 1;

    // The DAO (Data Access Object) for the Alarm class.
    private RuntimeExceptionDao<Alarm, Integer> alarmDao = null;
    private RuntimeExceptionDao<WeatherInfo, Integer> weatherDao = null;
    private RuntimeExceptionDao<WeatherRule, Integer> ruleDao = null;


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database, ConnectionSource connectionSource) {
        try {
            Log.i(DatabaseHelper.class.getName(), "onCreate");
            // Create all the tables for the different database objects.
            TableUtils.createTable(connectionSource, Alarm.class);
            TableUtils.createTable(connectionSource, WeatherInfo.class);
            TableUtils.createTable(connectionSource, WeatherRule.class);

            // Create some dummy data.
            SimpleDateFormat formatter = new SimpleDateFormat("hh:mm");
            try {
                Date date = formatter.parse("12:00");
                Alarm alarm = new Alarm(date, "Brunswick");
                getAlarmDao().create(alarm);

                date = formatter.parse("20:00");
                alarm = new Alarm(date, "Brunswick");
                getAlarmDao().create(alarm);
            } catch (ParseException e) {
                e.printStackTrace();
            }

        }
        catch (SQLException e) {
            Log.e(DatabaseHelper.class.getName(), "Can't create database", e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, ConnectionSource connectionSource, int oldVersion, int newVersion) {
        try {
            // Quick & dirty upgrade method.
            // Delete old tables and add new ones.
            Log.i(DatabaseHelper.class.getName(), "onUpgrade");
            TableUtils.dropTable(connectionSource, Alarm.class, true);
            TableUtils.dropTable(connectionSource, WeatherInfo.class, true);
            TableUtils.dropTable(connectionSource, WeatherRule.class, true);

            // after we drop the old database, add the new one.
            onCreate(database, connectionSource);
        }
        catch (SQLException e) {
            Log.e(DatabaseHelper.class.getName(), "Can't drop databases", e);
            throw new RuntimeException(e);
        }
    }

    /**
     * Returns the RuntimeExceptionDao (Database Access Object) version of a Dao for our Alarm class. It will
     * create it or just give the cached value. RuntimeExceptionDao only throw RuntimeExceptions.
     */
    public RuntimeExceptionDao<Alarm, Integer> getAlarmDao() {
        if (alarmDao == null)
            alarmDao = getRuntimeExceptionDao(Alarm.class);
        return alarmDao;
    }

    /**
     * Returns the RuntimeExceptionDao (Database Access Object) version of a Dao for our WeatherRule class. It will
     * create it or just give the cached value. RuntimeExceptionDao only throw RuntimeExceptions.
     */
    public RuntimeExceptionDao<WeatherInfo, Integer> getWeatherInfoDao() {
        if (weatherDao == null)
            weatherDao = getRuntimeExceptionDao(WeatherInfo.class);
        return weatherDao;
    }

    /**
     * Returns the RuntimeExceptionDao (Database Access Object) version of a Dao for our WeatherRule class. It will
     * create it or just give the cached value. RuntimeExceptionDao only throw RuntimeExceptions.
     */
    public RuntimeExceptionDao<WeatherRule, Integer> getWeatherRuleDao() {
        if (ruleDao == null)
            ruleDao = getRuntimeExceptionDao(WeatherRule.class);
        return ruleDao;
    }
}
