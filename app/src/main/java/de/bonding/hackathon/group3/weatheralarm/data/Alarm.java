package de.bonding.hackathon.group3.weatheralarm.data;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.Date;

@DatabaseTable(tableName = "alarms")
public class Alarm {

    @DatabaseField(generatedId = true)
    private int id;
    @DatabaseField(canBeNull = false)
    private String location;

    @DatabaseField
    private String name = null;

    @DatabaseField(canBeNull = false)
    private Date desired_time;
    @DatabaseField
    private Date actual_time = null;

    public Alarm() {
        // ORMLite needs a no-arg constructor.
    }

    public Alarm(Date desired_time, String location) {
        this.desired_time = desired_time;
        this.location = location;
    }

    /**
     * Returns the adjusted time, if the weather forcast matched any profile settings.
     *
     * @return The time the alarm should go off.
     */
    public Date getAlarmTime() {
        if (actual_time != null)
            return actual_time;
        return desired_time;
    }

    public int getId() {
        return id;
    }

    public String getLocation() {
        return location;
    }

    public Date getDesiredTime() {
        return desired_time;
    }

    public Date getActualTime() {
        return actual_time;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
