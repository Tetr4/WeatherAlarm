package de.bonding.hackathon.group3.weatheralarm.ui;


import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.j256.ormlite.dao.RuntimeExceptionDao;

import java.util.List;

import de.bonding.hackathon.group3.weatheralarm.R;
import de.bonding.hackathon.group3.weatheralarm.data.Alarm;

public class AlarmDetailDialog extends DialogFragment implements View.OnClickListener {
    private static final String ARG_ALARM_ID = "ARG_ALARM_ID";

    private List<Alarm> getAlarms() {
        MainActivity main = (MainActivity)getActivity();
        RuntimeExceptionDao<Alarm, Integer> alarmDao = main.getDatabase().getAlarmDao();
        return alarmDao.queryForAll();
    }

    public static AlarmDetailDialog newInstance(Alarm alarm) {
        AlarmDetailDialog fragment = new AlarmDetailDialog();

        Bundle args = new Bundle();
        args.putLong(ARG_ALARM_ID, alarm.getId());
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // inflate content layout

        View dialogView = getActivity().getLayoutInflater().inflate(R.layout.fragment_alarm_detail, null);

        // add or edit a station?
        Alarm alarm = getAlarmArgument();
        boolean edit = alarm != null;

        if (edit) {
            ListView list = (ListView) dialogView.findViewById(R.id.rules_list);
            // TODO put alarm rule entries into list
            // TODO set timepicker
        }

        // build alert dialog
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Edit Alarm")
                .setView(dialogView)
                .setPositiveButton(android.R.string.ok, null) // see below
                .setNegativeButton(android.R.string.cancel, null);
        final AlertDialog alertDialog = builder.create();

        alertDialog.setOnShowListener(new DialogInterface.OnShowListener() {
            @Override
            public void onShow(DialogInterface dialog) {
                Button positiveButton = alertDialog.getButton(DialogInterface.BUTTON_POSITIVE);
                positiveButton.setOnClickListener(AlarmDetailDialog.this);
            }
        });

        // alertDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        return alertDialog;
    }

    private Alarm getAlarmArgument() {
        Bundle arguments = getArguments();
        if (arguments != null && arguments.containsKey(ARG_ALARM_ID)) {
            long alarmId = arguments.getLong(ARG_ALARM_ID);
            for (Alarm alarm : getAlarms()) {
                if(alarm.getId()==alarmId) {
                    return alarm;
                }
            }
        }
        return null;
    }

    @Override
    public void onClick(View dialogView) {
        // OK is clicked

        Dialog dialog = getDialog();

        // TODO save input

        dialog.dismiss();
    }

}