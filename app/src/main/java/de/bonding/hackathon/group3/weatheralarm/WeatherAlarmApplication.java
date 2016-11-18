package de.bonding.hackathon.group3.weatheralarm;

import android.app.Application;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import de.bonding.hackathon.group3.weatheralarm.model.WeatherInfo;
import de.bonding.hackathon.group3.weatheralarm.openweathermap.OpenWeatherMapDeserializer;
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
        OkHttpClient client = new OkHttpClient();

        Gson gson = new GsonBuilder()
                .registerTypeAdapter(WeatherInfo.class, new OpenWeatherMapDeserializer(client))
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://api.openweathermap.org/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(client)
                .build();
        sWeatherService = retrofit.create(OpenWeatherMapService.class);
    }
}
