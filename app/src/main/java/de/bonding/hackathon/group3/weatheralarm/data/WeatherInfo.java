package de.bonding.hackathon.group3.weatheralarm.data;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.Date;

@DatabaseTable(tableName = "forecasts")
public class WeatherInfo {

    @DatabaseField(generatedId = true)
    private int id;

    @DatabaseField(canBeNull = false)
    private String location;

    @DatabaseField(canBeNull = false)
    private Date time;

    /**
     * The weather id encoding for exact lookups.
     */
    @DatabaseField(canBeNull = false)
    private int weather_id;

    @DatabaseField(canBeNull = false)
    private String temp;

    @DatabaseField(canBeNull = false)
    private String icon;

    public WeatherInfo() {
        // ORMLite needs a no-arg constructor.
    }

    public WeatherInfo(String location, Date time, int weather_id, String temp, String icon) {
        this.location = location;
        this.time = time;
        this.weather_id = weather_id;
        this.temp = temp;
        this.icon = icon;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public int getWeatherId() {
        return weather_id;
    }

    public void setWeatherId(int weather_id) {
        this.weather_id = weather_id;
    }

    public String getTemp() {
        return temp;
    }

    public void setTemp(String temp) {
        this.temp = temp;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }
}
