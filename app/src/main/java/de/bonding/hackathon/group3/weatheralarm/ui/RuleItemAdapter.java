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
import java.util.HashMap;
import java.util.List;

import de.bonding.hackathon.group3.weatheralarm.R;
import de.bonding.hackathon.group3.weatheralarm.data.Alarm;
import de.bonding.hackathon.group3.weatheralarm.data.DatabaseHelper;
import de.bonding.hackathon.group3.weatheralarm.data.WeatherInfo;
import de.bonding.hackathon.group3.weatheralarm.data.WeatherRule;
import de.bonding.hackathon.group3.weatheralarm.data.WeatherType;

public class RuleItemAdapter extends ArrayAdapter<WeatherRule> {

    public RuleItemAdapter(Context context, List<WeatherRule> values) {
        super(context, -1, values);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) getContext()
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.list_item_rule, parent, false);

        WeatherRule rule = getItem(position);
        TextView txtRuleName = (TextView) rowView.findViewById(R.id.rule_name);
        TextView txtTimeDiff = (TextView) rowView.findViewById(R.id.changed_time_text);
        ImageView imgWeather = (ImageView) rowView.findViewById(R.id.weather_condition_image);

        // Set the description if there is one set.
        if (rule.getDescription() != null) {
            txtRuleName.setText(rule.getDescription());
            txtRuleName.setVisibility(View.VISIBLE);
        }
        else {
            txtRuleName.setVisibility(View.GONE);
        }

        // Format the time difference.
        StringBuilder sb = new StringBuilder();
        int offset = rule.getTimeOffset();
        sb.append(Math.abs(offset));
        sb.append(" Minuten");
        if (offset < 0)
            sb.append(" früher");
        else
            sb.append(" später");
        txtTimeDiff.setText(sb.toString());

        String icon = null;
        for (WeatherType type : WeatherType.getWeatherTypes().values()) {
            if (type.getMain().equals(rule.getWeather())) {
                icon = type.getIcon();
                break;
            }
        }

        // Set the icon to the right one.
        if (icon != null)
            imgWeather.setImageResource(WeatherType.IconToResourceId(icon));
        else
            imgWeather.setImageResource(R.drawable.w01d);

        return rowView;
    }
}

