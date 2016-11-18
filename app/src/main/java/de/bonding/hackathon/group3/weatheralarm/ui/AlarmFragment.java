package de.bonding.hackathon.group3.weatheralarm.ui;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.j256.ormlite.dao.RuntimeExceptionDao;
import com.j256.ormlite.stmt.PreparedQuery;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import de.bonding.hackathon.group3.weatheralarm.R;
import de.bonding.hackathon.group3.weatheralarm.data.Alarm;

public class AlarmFragment extends Fragment {
    private static final String TAG = AlarmFragment.class.getSimpleName();
    List<Alarm> alarms;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_alarm, container, false);

        // Construct the data source
        alarms = getAlarms();

        // Create the adapter to convert the array to views
        AlarmItemAdapter adapter = new AlarmItemAdapter(getActivity(), alarms);

        // Attach the adapter to a ListView
        ListView alarmList = (ListView) rootView.findViewById(R.id.alarm_list);
        alarmList.setAdapter(adapter);

        return rootView;
    }

    private List<Alarm> getAlarms() {
        MainActivity main = (MainActivity)getActivity();
        RuntimeExceptionDao<Alarm, Integer> alarmDao = main.getDatabase().getAlarmDao();

        return alarmDao.queryForAll();
    }
}
