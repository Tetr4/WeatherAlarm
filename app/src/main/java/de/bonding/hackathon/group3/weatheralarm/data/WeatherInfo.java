package de.bonding.hackathon.group3.weatheralarm.data;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.Date;

@DatabaseTable(tableName = "forecasts")
public class WeatherInfo {

    @DatabaseField(generatedId = true)
    private int id;

    @DatabaseField(canBeNull = false)
    private String lat;

    @DatabaseField(canBeNull = false)
    private String lon;

    @DatabaseField(canBeNull = false)
    private Double temp;

    @DatabaseField(canBeNull = false)
    private Double tempMin;

    @DatabaseField(canBeNull = false)
    private Double tempMax;

    @DatabaseField(canBeNull = false)
    private Double windSpeed;

    @DatabaseField(canBeNull = false)
    private Double windDegree;
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
    private String weatherDescription;

    @DatabaseField(canBeNull = false)
    private String weatherName;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLon() {
        return lon;
    }

    public void setLon(String lon) {
        this.lon = lon;
    }

    public Double getTemp() {
        return temp;
    }

    public void setTemp(Double temp) {
        this.temp = temp;
    }

    public Double getTempMin() {
        return tempMin;
    }

    public void setTempMin(Double tempMin) {
        this.tempMin = tempMin;
    }

    public Double getTempMax() {
        return tempMax;
    }

    public void setTempMax(Double tempMax) {
        this.tempMax = tempMax;
    }

    public Double getWindSpeed() {
        return windSpeed;
    }

    public void setWindSpeed(Double windSpeed) {
        this.windSpeed = windSpeed;
    }

    public Double getWindDegree() {
        return windDegree;
    }

    public void setWindDegree(Double windDegree) {
        this.windDegree = windDegree;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public int getWeather_id() {
        return weather_id;
    }

    public void setWeather_id(int weather_id) {
        this.weather_id = weather_id;
    }

    public String getWeatherName() {
        return weatherName;
    }

    public void setWeatherName(String weatherName) {
        this.weatherName = weatherName;
    }

    public String getWeatherID() {
        return weatherID;
    }

    public void setWeatherID(String weatherID) {
        this.weatherID = weatherID;
    }

    public String getWeatherDescription() {
        return weatherDescription;
    }

    public void setWeatherDescription(String weatherDescription) {
        this.weatherDescription = weatherDescription;
    }

    @DatabaseField(canBeNull = false)
    private String weatherID;



}
