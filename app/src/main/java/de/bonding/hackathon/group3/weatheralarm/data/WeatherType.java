package de.bonding.hackathon.group3.weatheralarm.data;

import java.util.HashMap;
import java.util.Map;

/**
 * Represents a weather condition as set by open weather map.
 */
public class WeatherType {

    // Maps weather condition code to description.
    private static Map<Integer, WeatherType> WEATHER_TYPES = null;

    private int id;
    private String main;
    private String description;
    private String icon;

    public WeatherType(int id, String main, String description, String icon) {
        this.id = id;
        this.main = main;
        this.description = description;
        this.icon = icon;
    }

    public int getId() {
        return id;
    }

    public String getMain() {
        return main;
    }

    public String getDescription() {
        return description;
    }

    public String getIcon() {
        return icon;
    }

    public static Map<Integer, WeatherType> getWeatherTypes() {
        if (WEATHER_TYPES != null)
            return WEATHER_TYPES;

        WEATHER_TYPES = new HashMap<Integer, WeatherType>();
        // Group 2xx: Thunderstorm
        WEATHER_TYPES.put(200, new WeatherType(200, "Thunderstorm", "thunderstorm with light rain", "11d"));
        WEATHER_TYPES.put(201, new WeatherType(201, "Thunderstorm", "thunderstorm with rain", "11d"));
        WEATHER_TYPES.put(202, new WeatherType(202, "Thunderstorm", "thunderstorm with heavy rain", "11d"));
        WEATHER_TYPES.put(210, new WeatherType(210, "Thunderstorm", "light thunderstorm", "11d"));
        WEATHER_TYPES.put(211, new WeatherType(211, "Thunderstorm", "thunderstorm", "11d"));
        WEATHER_TYPES.put(212, new WeatherType(212, "Thunderstorm", "heavy thunderstorm", "11d"));
        WEATHER_TYPES.put(221, new WeatherType(221, "Thunderstorm", "ragged thunderstorm", "11d"));
        WEATHER_TYPES.put(230, new WeatherType(230, "Thunderstorm", "thunderstorm with light drizzle", "11d"));
        WEATHER_TYPES.put(231, new WeatherType(231, "Thunderstorm", "thunderstorm with drizzle", "11d"));
        WEATHER_TYPES.put(232, new WeatherType(232, "Thunderstorm", "thunderstorm with heavy drizzle", "11d"));


        WEATHER_TYPES.put(300, new WeatherType(300, "Drizzle", "light intensity drizzle", "09d"));
        WEATHER_TYPES.put(301, new WeatherType(301, "Drizzle", "drizzle", "09d"));
        WEATHER_TYPES.put(302, new WeatherType(302, "Drizzle", "heavy intensity drizzle", "09d"));
        WEATHER_TYPES.put(310, new WeatherType(310, "Drizzle", "light intensity drizzle rain", "09d"));
        WEATHER_TYPES.put(311, new WeatherType(311, "Drizzle", "drizzle rain", "09d"));
        WEATHER_TYPES.put(312, new WeatherType(312, "Drizzle", "heavy intensity drizzle rain", "09d"));
        WEATHER_TYPES.put(313, new WeatherType(313, "Drizzle", "shower rain and drizzle", "09d"));
        WEATHER_TYPES.put(314, new WeatherType(314, "Drizzle", "heavy shower rain and drizzle", "09d"));
        WEATHER_TYPES.put(321, new WeatherType(321, "Drizzle", "shower drizzle", "09d"));

        WEATHER_TYPES.put(500, new WeatherType(500, "Rain", "light rain", "10d"));
        WEATHER_TYPES.put(501, new WeatherType(501, "Rain", "moderate rain", "10d"));
        WEATHER_TYPES.put(502, new WeatherType(502, "Rain", "heavy intensity rain", "10d"));
        WEATHER_TYPES.put(503, new WeatherType(503, "Rain", "very heavy rain", "10d"));
        WEATHER_TYPES.put(504, new WeatherType(504, "Rain", "extreme rain", "10d"));
        WEATHER_TYPES.put(511, new WeatherType(511, "Rain", "freezing rain", "10d"));
        WEATHER_TYPES.put(520, new WeatherType(520, "Rain", "light intensity shower rain", "10d"));
        WEATHER_TYPES.put(521, new WeatherType(521, "Rain", "shower rain", "10d"));
        WEATHER_TYPES.put(522, new WeatherType(522, "Rain", "heavy intensity shower rain", "10d"));
        WEATHER_TYPES.put(531, new WeatherType(531, "Rain", "ragged shower rain", "10d"));

        WEATHER_TYPES.put(600, new WeatherType(600, "Snow", "light snow", "13d"));
        WEATHER_TYPES.put(601, new WeatherType(601, "Snow", "snow", "13d"));
        WEATHER_TYPES.put(602, new WeatherType(602, "Snow", "heavy snow", "13d"));
        WEATHER_TYPES.put(611, new WeatherType(611, "Snow", "sleet", "13d"));
        WEATHER_TYPES.put(612, new WeatherType(612, "Snow", "shower sleet", "13d"));
        WEATHER_TYPES.put(615, new WeatherType(615, "Snow", "light rain and snow", "13d"));
        WEATHER_TYPES.put(616, new WeatherType(616, "Snow", "rain and snow", "13d"));
        WEATHER_TYPES.put(620, new WeatherType(620, "Snow", "light shower snow", "13d"));
        WEATHER_TYPES.put(621, new WeatherType(621, "Snow", "shower snow", "13d"));
        WEATHER_TYPES.put(622, new WeatherType(622, "Snow", "heavy shower snow", "13d"));

        WEATHER_TYPES.put(701, new WeatherType(701, "Atmosphere", "mist", "50d"));
        WEATHER_TYPES.put(711, new WeatherType(711, "Atmosphere", "smoke", "50d"));
        WEATHER_TYPES.put(721, new WeatherType(721, "Atmosphere", "haze", "50d"));
        WEATHER_TYPES.put(731, new WeatherType(731, "Atmosphere", "sand, dust whirls", "50d"));
        WEATHER_TYPES.put(741, new WeatherType(741, "Atmosphere", "fog", "50d"));
        WEATHER_TYPES.put(751, new WeatherType(751, "Atmosphere", "sand", "50d"));
        WEATHER_TYPES.put(761, new WeatherType(761, "Atmosphere", "dust", "50d"));
        WEATHER_TYPES.put(762, new WeatherType(762, "Atmosphere", "volcanic ash", "50d"));
        WEATHER_TYPES.put(771, new WeatherType(771, "Atmosphere", "squalls", "50d"));
        WEATHER_TYPES.put(781, new WeatherType(781, "Atmosphere", "tornado", "50d"));

        WEATHER_TYPES.put(800, new WeatherType(800, "Clear", "clear sky", "01d "));

        WEATHER_TYPES.put(801, new WeatherType(801, "Clouds", "few clouds", "02d"));
        WEATHER_TYPES.put(802, new WeatherType(802, "Clouds", "scattered clouds", "03d"));
        WEATHER_TYPES.put(803, new WeatherType(803, "Clouds", "broken clouds", "04d"));
        WEATHER_TYPES.put(804, new WeatherType(804, "Clouds", "overcast clouds", "04d"));

        WEATHER_TYPES.put(900, new WeatherType(900, "Extreme", "tornado", ""));
        WEATHER_TYPES.put(901, new WeatherType(901, "Extreme", "tropical storm", ""));
        WEATHER_TYPES.put(902, new WeatherType(902, "Extreme", "hurricane", ""));
        WEATHER_TYPES.put(903, new WeatherType(903, "Extreme", "cold", ""));
        WEATHER_TYPES.put(904, new WeatherType(904, "Extreme", "hot", ""));
        WEATHER_TYPES.put(905, new WeatherType(905, "Extreme", "windy", ""));
        WEATHER_TYPES.put(906, new WeatherType(906, "Extreme", "hail", ""));

        WEATHER_TYPES.put(951, new WeatherType(951, "Additional", "calm", ""));
        WEATHER_TYPES.put(952, new WeatherType(952, "Additional", "light breeze", ""));
        WEATHER_TYPES.put(953, new WeatherType(953, "Additional", "gentle breeze", ""));
        WEATHER_TYPES.put(954, new WeatherType(954, "Additional", "moderate breeze", ""));
        WEATHER_TYPES.put(955, new WeatherType(955, "Additional", "fresh breeze", ""));
        WEATHER_TYPES.put(956, new WeatherType(956, "Additional", "strong breeze", ""));
        WEATHER_TYPES.put(957, new WeatherType(957, "Additional", "high wind, near gale", ""));
        WEATHER_TYPES.put(958, new WeatherType(958, "Additional", "gale", ""));
        WEATHER_TYPES.put(959, new WeatherType(959, "Additional", "severe gale", ""));
        WEATHER_TYPES.put(960, new WeatherType(960, "Additional", "storm", ""));
        WEATHER_TYPES.put(961, new WeatherType(961, "Additional", "violent storm", ""));
        WEATHER_TYPES.put(962, new WeatherType(962, "Additional", "hurricane", ""));

        return WEATHER_TYPES;
    }
}
