package de.bonding.hackathon.group3.weatheralarm;

import android.app.Application;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import de.bonding.hackathon.group3.weatheralarm.openweathermap.OpenWeatherMapService;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class WeatherAlarmApplication extends Application {
    public static OpenWeatherMapService sWeatherService;


    @Override
    public void onCreate() {
        super.onCreate();
        initService();
    }

    private void initService() {
    }
}
