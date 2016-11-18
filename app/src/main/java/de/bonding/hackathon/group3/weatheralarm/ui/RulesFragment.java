package de.bonding.hackathon.group3.weatheralarm.ui;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import de.bonding.hackathon.group3.weatheralarm.R;

public class RulesFragment extends Fragment {
    private static final String TAG = AlarmFragment.class.getSimpleName();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_rules, container, false);
        return rootView;
    }
}
