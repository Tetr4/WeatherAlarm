package de.bonding.hackathon.group3.weatheralarm.openweathermap;

import android.os.AsyncTask;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonParser;
import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.dao.RuntimeExceptionDao;

import java.io.*;
import java.net.*;

import java.lang.reflect.Type;
import java.util.Date;

import de.bonding.hackathon.group3.weatheralarm.data.DatabaseHelper;
import de.bonding.hackathon.group3.weatheralarm.data.WeatherInfo;
import de.bonding.hackathon.group3.weatheralarm.ui.MainActivity;


public class OpenWeatherMapService extends AsyncTask<Object, String, String> {
    // Used to access the sqlite database.
    private DatabaseHelper databaseHelper = null;
    private MainActivity main = null;
    private String lat;
    private String lon;
    private String loc;

    protected void onProgressUpdate(Integer... progress) {

        // gebe aktuellen Fortschritt aus
    }

    protected void onPostExecute(Long result) {

        // Task abgeschlossen, Ergebnis kann verwendet werden
    }


    public OpenWeatherMapService() {

    }

    @Override
    protected String doInBackground(Object... params) {
        main = (MainActivity) params[3];
        getWeatherInfo((String) params[0],(String) params[1],(String) params[2]);

        return null;
    }


    public static String getHTML(String urlToRead) throws Exception {
        StringBuilder result = new StringBuilder();
        URL url = new URL(urlToRead);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        String line;
        while ((line = rd.readLine()) != null) {
            result.append(line);
        }
        rd.close();
        return result.toString();
    }

    public void getWeatherInfo(String lloc, String llat, String appid) {
        //api.openweathermap.org/data/2.5/forecast?lat=35&lon=139
        JsonElement jelement = null;
        //this.lat = llat;
        //this.lon = llon;
        this.loc = lloc;

        try {
            String json = getHTML("http://api.openweathermap.org/data/2.5/forecast?q="+lloc+"&appid="+appid);
            jelement = new JsonParser().parse(json);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if(jelement == null || !jelement.isJsonObject()) {
            return;
        }
        Log.i("ASD",jelement.toString());
        deserialize(jelement);
    }


    public void deserialize(JsonElement json) throws JsonParseException {
        //WeatherInfo info = new WeatherInfo();
        JsonObject root = json.getAsJsonObject();

        DatabaseHelper db = getDatabase();
        RuntimeExceptionDao<WeatherInfo, Integer> ruleDao = db.getWeatherInfoDao();

        JsonArray weather = root.getAsJsonArray("list");
        if(weather.size() > 0) {
            for(int i = 0; i<weather.size(); i++) {
                WeatherInfo info = new WeatherInfo();
                JsonObject curr = weather.get(i).getAsJsonObject();

                //info.setLat(lat);
               // info.setLon(lon);
                info.setLocation(this.loc);

                Date d = new Date(Integer.parseInt(curr.get("dt").getAsString()));
                info.setTime(d);

                JsonObject main = curr.get("main").getAsJsonObject();
                info.setTemp(Double.parseDouble(main.get("temp").getAsString()));
                info.setTempMin(Double.parseDouble(main.get("temp_min").getAsString()));
                info.setTempMax(Double.parseDouble(main.get("temp_max").getAsString()));

                JsonObject wind = curr.get("wind").getAsJsonObject();
                info.setWindSpeed(Double.parseDouble(wind.get("speed").getAsString()));
                info.setWindDegree(Double.parseDouble(wind.get("deg").getAsString()));

                JsonObject weather2 = curr.get("weather").getAsJsonArray().get(0).getAsJsonObject();
                info.setWeatherID(Integer.parseInt(weather2.get("id").getAsString()));
                info.setWeatherName(weather2.get("main").getAsString());
                info.setWeatherDescription(weather2.get("description").getAsString());
                info.setIcon(weather2.get("icon").getAsString());


                ruleDao.create(info);
            }
        }/*
        JsonObject main = root.getAsJsonObject("main");
        //info.temp = main.getAsJsonPrimitive("temp").getAsString();
        JsonObject wind = root.getAsJsonObject("wind");
        info.windSpeed = wind.getAsJsonPrimitive("speed").getAsString();
        info.windDegree = wind.getAsJsonPrimitive("deg").getAsString();

        return info;*/
    }

    // Get the database singleton
    public DatabaseHelper getDatabase() {
        if (databaseHelper == null) {
            databaseHelper =
                    OpenHelperManager.getHelper(main, DatabaseHelper.class);
        }
        return databaseHelper;
    }

}
