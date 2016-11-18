package de.bonding.hackathon.group3.weatheralarm.data;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.Date;

@DatabaseTable(tableName = "forecasts")
public class WeatherInfo {

    @DatabaseField(generatedId = true)
    private int id;

    @DatabaseField(canBeNull = false)
    public String location;

    @DatabaseField(canBeNull = false)
    private Date time;

    /**
     * The weather id encoding for exact lookups.
     */
    @DatabaseField(canBeNull = false)
    private int weather_id;

    @DatabaseField(canBeNull = false)
    public String temp;
}
