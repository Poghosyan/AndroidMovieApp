package com.scapegate.movieapp;

import android.os.Bundle;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;

public class SettingsFragment extends PreferenceFragment implements Preference.OnPreferenceChangeListener{
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.preferences);
        Preference preference = getPreferenceManager().findPreference(getString(R.string.pref_key));
        preference.setOnPreferenceChangeListener(this);
        onPreferenceChange(preference,
                PreferenceManager
                        .getDefaultSharedPreferences(preference.getContext())
                        .getString(preference.getKey(), ""));
    }

    @Override
    public boolean onPreferenceChange(Preference preference, Object newValue) {
        ListPreference pref = (ListPreference) preference;
        int prefIndex = pref.findIndexOfValue(getString(R.string.pref_key));
        if (prefIndex >= 0) {
            preference.setSummary(pref.getEntries()[prefIndex]);
        }
        return true;
    }
}
