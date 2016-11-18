package de.bonding.hackathon.group3.weatheralarm.ui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.j256.ormlite.dao.RuntimeExceptionDao;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import de.bonding.hackathon.group3.weatheralarm.R;
import de.bonding.hackathon.group3.weatheralarm.data.Alarm;
import de.bonding.hackathon.group3.weatheralarm.data.DatabaseHelper;
import de.bonding.hackathon.group3.weatheralarm.data.WeatherInfo;
import de.bonding.hackathon.group3.weatheralarm.data.WeatherType;

public class AlarmItemAdapter extends ArrayAdapter<Alarm> {

    public AlarmItemAdapter(Context context, List<Alarm> values) {
        super(context, -1, values);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) getContext()
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.list_item_alarm, parent, false);

        final Alarm alarm = getItem(position);
        MainActivity main = (MainActivity)getContext();
        DatabaseHelper db = main.getDatabase();
        final RuntimeExceptionDao<Alarm, Integer> alarmDao = db.getAlarmDao();

        // FIXME not firing.
        ToggleButton togglebutton = (ToggleButton)rowView.findViewById(R.id.togglebutton);
        togglebutton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                // Save the new state
                alarm.setEnabled(isChecked);
                alarmDao.update(alarm);
                Toast.makeText(getContext(), "Alarm " + (isChecked?"aktiviert":"deaktiviert"), Toast.LENGTH_SHORT);
            }
        });


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
        SimpleDateFormat format = new SimpleDateFormat("HH:mm");
        txtAlarmTime.setText(format.format(alarm.getDesiredTime()));

        // Only show an estimate if we have weather information.
        if (alarm.getActualTime() != null) {
            txtEstimatedAlarmTime.setText(format.format(alarm.getActualTime()));
            txtEstimatedAlarmTime.setVisibility(View.VISIBLE);
        }
        else
            txtEstimatedAlarmTime.setVisibility(View.GONE);

        // See if there is a forecast for our time.
        HashMap<String, Object> search = new HashMap<>();
        search.put("location", alarm.getLocation());
        RuntimeExceptionDao<WeatherInfo, Integer> weatherInfoDao = db.getWeatherInfoDao();
        List<WeatherInfo> weatherInfos = weatherInfoDao.queryForFieldValuesArgs(search);

        // Find closest forecast before alarm
        // TODO Update alarmtime to include date. Only time is valid from the database.
        WeatherInfo bestInfo = null;
        for (WeatherInfo info : weatherInfos) {
            if (info.getTime().before(alarm.getDesiredTime()) && (bestInfo == null || bestInfo.getTime().before(info.getTime())))
                bestInfo = info;
        }

        if (bestInfo != null)
            imgWeather.setImageResource(WeatherType.IconToResourceId(bestInfo.getIcon()));
        else
            imgWeather.setImageResource(R.drawable.w01d);

        return rowView;
    }
}

