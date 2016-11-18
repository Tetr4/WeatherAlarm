package de.bonding.hackathon.group3.weatheralarm.openweathermap;

import de.bonding.hackathon.group3.weatheralarm.model.WeatherInfo;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface OpenWeatherMapService {

    @GET("data/2.5/weather/?lat={lat}&lon={lon}")
    Call<WeatherInfo> getWeatherInfo(@Path("lat") String lat, @Path("lon") String lon);
}
