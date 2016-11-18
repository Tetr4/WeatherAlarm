package de.bonding.hackathon.group3.weatheralarm.ui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.j256.ormlite.dao.RuntimeExceptionDao;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import de.bonding.hackathon.group3.weatheralarm.R;
import de.bonding.hackathon.group3.weatheralarm.data.Alarm;
import de.bonding.hackathon.group3.weatheralarm.data.DatabaseHelper;
import de.bonding.hackathon.group3.weatheralarm.data.WeatherInfo;

public class AlarmItemAdapter extends ArrayAdapter<Alarm> {

    public AlarmItemAdapter(Context context, List<Alarm> values) {
        super(context, -1, values);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) getContext()
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.list_item_alarm, parent, false);

        Alarm alarm = getItem(position);
        TextView txtAlarmName = (TextView) rowView.findViewById(R.id.alarm_name);
        TextView txtAlarmTime = (TextView) rowView.findViewById(R.id.alarm_time);
        TextView txtEstimatedAlarmTime = (TextView) rowView.findViewById(R.id.alarm_estimated_time);
        ImageView imgWeather = (ImageView) rowView.findViewById(R.id.weather_image);

        // Set the name if there is one set.
        if (alarm.getName() != null) {
            txtAlarmName.setText(alarm.getName());
            txtAlarmName.setVisibility(View.VISIBLE);
        }
        else {
            txtAlarmName.setVisibility(View.GONE);
        }

        // Format the time.
        SimpleDateFormat format = new SimpleDateFormat("hh:mm");
        txtAlarmTime.setText(format.format(alarm.getDesiredTime()));

        // Only show an estimate if we have weather information.
        if (alarm.getActualTime() != null) {
            txtEstimatedAlarmTime.setText(format.format(alarm.getActualTime()));
            txtEstimatedAlarmTime.setVisibility(View.VISIBLE);
        }
        else
            txtEstimatedAlarmTime.setVisibility(View.GONE);

        // See if there is a forecast for our time.
        MainActivity main = (MainActivity)getContext();
        DatabaseHelper db = main.getDatabase();

        RuntimeExceptionDao<WeatherInfo, Integer> alarmDao = main.getDatabase().getWeatherInfoDao();
        HashMap<String, Object> search = new HashMap<>();
        search.put("location", alarm.getLocation());
        List<WeatherInfo> weatherInfos = alarmDao.queryForFieldValuesArgs(search);

        // Find closest forecast before alarm
        // TODO Update alarmtime to include date. Only time is valid from the database.
        WeatherInfo bestInfo = null;
        for (WeatherInfo info : weatherInfos) {
            if (info.getTime().before(alarm.getDesiredTime()) && (bestInfo == null || bestInfo.getTime().before(info.getTime())))
                bestInfo = info;
        }

        if (bestInfo != null) {
            // TODO Use bestInfo.getIcon()
            imgWeather.setImageResource(R.drawable.w01d);
        }

        return rowView;
    }
}

