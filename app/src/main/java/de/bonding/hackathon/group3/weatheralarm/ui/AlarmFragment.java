package de.bonding.hackathon.group3.weatheralarm.ui;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
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
    private List<Alarm> alarms;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_alarm, container, false);

        // Construct the data source
        alarms = getAlarms();

        // Create the adapter to convert the array to views
        AlarmItemAdapter adapter = new AlarmItemAdapter(getActivity(), alarms, this);

        // Attach the adapter to a ListView
        ListView alarmList = (ListView) rootView.findViewById(R.id.alarm_list);
        alarmList.setAdapter(adapter);

        FloatingActionButton fab = (FloatingActionButton)rootView.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentTransaction ft = getChildFragmentManager().beginTransaction();
                AlarmDetailDialog detail = AlarmDetailDialog.newInstance();
                ft.add(detail, "alarm_details");
                ft.commit();
            }
        });

        return rootView;
    }

    public List<Alarm> getAlarmList() {
        return alarms;
    }

    public void notifyAlarmlistUpdate() {
        ListView alarmList = (ListView) getView().findViewById(R.id.alarm_list);
        AlarmItemAdapter adapter = (AlarmItemAdapter) alarmList.getAdapter();
        adapter.notifyDataSetChanged();
    }

    private List<Alarm> getAlarms() {
        MainActivity main = (MainActivity)getActivity();
        RuntimeExceptionDao<Alarm, Integer> alarmDao = main.getDatabase().getAlarmDao();

        return alarmDao.queryForAll();
    }
}
