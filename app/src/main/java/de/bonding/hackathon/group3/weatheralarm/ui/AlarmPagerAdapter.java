package de.bonding.hackathon.group3.weatheralarm.ui;

import android.app.Fragment;
import android.app.FragmentManager;
import android.content.res.Resources;
import android.support.v13.app.FragmentPagerAdapter;


public class AlarmPagerAdapter extends FragmentPagerAdapter {
    private static final int PAGE_COUNT = 2;

    private static final int ALARM_FRAGMENT_POSITION = 0;
    private static final int RULES_FRAGMENT_POSITION = 1;

    private final Resources mResources;

    public AlarmPagerAdapter(FragmentManager fm, Resources resources) {
        super(fm);
        mResources = resources;
    }

    @Override
    public int getCount() {
        return PAGE_COUNT;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case ALARM_FRAGMENT_POSITION:
                return new AlarmFragment();

            case RULES_FRAGMENT_POSITION:
                return new RulesFragment();

            default:
                return null;
        }
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case ALARM_FRAGMENT_POSITION:
                // TODO string resource
                return "Wecker";

            case RULES_FRAGMENT_POSITION:
                // TODO string resource
                return "Regeln";

            default:
                return null;
        }
    }

}
