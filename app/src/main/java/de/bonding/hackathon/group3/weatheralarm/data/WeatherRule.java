package de.bonding.hackathon.group3.weatheralarm.data;


import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "rules")
public class WeatherRule {

    @DatabaseField(generatedId = true)
    private int id;

    /**
     * The weather id encoding for exact lookups.
     */
    @DatabaseField(canBeNull = false)
    private int weather_id;

    /**
     * The description of the weather.
     */
    @DatabaseField(canBeNull = false)
    private String weather;

    @DatabaseField(canBeNull = false)
    private int time_offset;

    public WeatherRule() {
        // ORMLite needs a no-arg constructor.
    }

    public WeatherRule(int weather_id, String weather, int time_offset) {
        this.weather_id = weather_id;
        this.weather = weather;
        this.time_offset = time_offset;
    }

    public int getWeatherId() {
        return weather_id;
    }

    public void setWeatherId(int weather_id) {
        this.weather_id = weather_id;
    }

    public String getWeather() {
        return weather;
    }

    public void setWeather(String weather) {
        this.weather = weather;
    }

    public int getTimeOffset() {
        return time_offset;
    }

    public void setTimeOffset(int time_offset) {
        this.time_offset = time_offset;
    }
}
