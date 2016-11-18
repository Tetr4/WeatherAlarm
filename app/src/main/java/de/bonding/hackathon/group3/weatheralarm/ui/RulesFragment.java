package de.bonding.hackathon.group3.weatheralarm.ui;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.j256.ormlite.dao.RuntimeExceptionDao;

import java.util.List;

import de.bonding.hackathon.group3.weatheralarm.R;
import de.bonding.hackathon.group3.weatheralarm.data.WeatherRule;

public class RulesFragment extends Fragment {
    private static final String TAG = AlarmFragment.class.getSimpleName();

    List<WeatherRule> rules;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_rules, container, false);

        // Construct the data source
        rules = getRules();

        // Create the adapter to convert the array to views
        RuleItemAdapter adapter = new RuleItemAdapter(getActivity(), rules);

        // Attach the adapter to a ListView
        ListView rulesList = (ListView) rootView.findViewById(R.id.rules_list);
        rulesList.setAdapter(adapter);

        return rootView;
    }

    private List<WeatherRule> getRules() {
        MainActivity main = (MainActivity)getActivity();
        RuntimeExceptionDao<WeatherRule, Integer> rulesDao = main.getDatabase().getWeatherRuleDao();

        return rulesDao.queryForAll();
    }
}
