package de.bonding.hackathon.group3.weatheralarm.openweathermap;

import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;

import de.bonding.hackathon.group3.weatheralarm.model.WeatherInfo;
import okhttp3.OkHttpClient;

public class OpenWeatherMapDeserializer implements JsonDeserializer<WeatherInfo> {
    private static final String TAG = OpenWeatherMapDeserializer.class.getSimpleName();
    private OkHttpClient mClient;

    public OpenWeatherMapDeserializer(OkHttpClient client) {

    }

    @Override
    public WeatherInfo deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {

        WeatherInfo info = new WeatherInfo();
        JsonObject root = json.getAsJsonObject();
        JsonArray weather = root.getAsJsonArray("weather");
        if(weather.size() > 0) {
            JsonObject first = weather.get(0).getAsJsonObject();
            info.weatherName = first.getAsJsonPrimitive("main").getAsString();
            info.weatherDescription = first.getAsJsonPrimitive("description").getAsString();
            info.icon = first.getAsJsonPrimitive("icon").getAsString();
        }
        JsonObject main = root.getAsJsonObject("main");
        info.temp = main.getAsJsonPrimitive("temp").getAsString();
        JsonObject wind = root.getAsJsonObject("wind");
        info.windSpeed = wind.getAsJsonPrimitive("speed").getAsString();
        info.windDegree = wind.getAsJsonPrimitive("deg").getAsString();

        return info;
    }
}