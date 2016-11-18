package de.bonding.hackathon.group3.weatheralarm.openweathermap;

import de.bonding.hackathon.group3.weatheralarm.model.WeatherInfo;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface OpenWeatherMapService {

    @GET("data/2.5/weather/")
    Call<WeatherInfo> getWeatherInfo(@Query("lat") String lat, @Query("lon") String lon);
}
