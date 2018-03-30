package ru.sash0k.bluetooth_terminal.activity;

import android.app.ActionBar;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.MenuItem;

import ru.sash0k.bluetooth_terminal.R;

import static android.content.ContentValues.TAG;

@SuppressWarnings("deprecation")
public final class SettingsActivity extends PreferenceActivity
        implements SharedPreferences.OnSharedPreferenceChangeListener {

    private static final String TAG = "SettingsActivity";
    @Override
    public void onCreate(Bundle savedInstanceState) {
        Log.d(TAG, "Settings activity");
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.settings_activity);

        final ActionBar bar = getActionBar();
        bar.setHomeButtonEnabled(true);
        bar.setDisplayHomeAsUpEnabled(true);

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        prefs.registerOnSharedPreferenceChangeListener(this);
        setPreferenceTitle(getString(R.string.pref_commands_mode));
        setPreferenceTitle(getString(R.string.pref_checksum_mode));
        setPreferenceTitle(getString(R.string.pref_commands_ending));
    }
    // ============================================================================


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case android.R.id.home:
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    // ============================================================================


    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String value) {
        setPreferenceTitle(value);
    }
    // ============================================================================

    private void setPreferenceTitle(String TAG) {
        final Preference preference = findPreference(TAG);
        if (preference == null) return;
        if (preference instanceof ListPreference) {
            if (((ListPreference) preference).getEntry() == null) return;
            final String title = ((ListPreference) preference).getEntry().toString();
            preference.setTitle(title);
        }
    }
    // ============================================================================
}
