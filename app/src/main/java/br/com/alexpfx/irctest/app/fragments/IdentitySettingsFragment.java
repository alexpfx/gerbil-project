package br.com.alexpfx.irctest.app.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.preference.PreferenceFragment;
import br.com.alexpfx.irctest.app.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class IdentitySettingsFragment extends PreferenceFragment {

    public IdentitySettingsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.identity_settings);
    }


}
