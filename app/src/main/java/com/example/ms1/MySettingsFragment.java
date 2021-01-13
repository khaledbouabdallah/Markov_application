package com.example.ms1;

import android.os.Bundle;
import android.text.InputType;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.preference.EditTextPreference;
import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;

public class MySettingsFragment extends PreferenceFragmentCompat {
    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        setPreferencesFromResource(R.xml.prefernce_layout, rootKey);
        EditTextPreference numberOfStates = findPreference("number_of_states");
        EditTextPreference numberOfJumpes = findPreference("number_of_jumpes");

        numberOfStates.setSummaryProvider(EditTextPreference.SimpleSummaryProvider.getInstance());
        numberOfJumpes.setSummaryProvider(EditTextPreference.SimpleSummaryProvider.getInstance());

        numberOfJumpes.setOnBindEditTextListener(new EditTextPreference.OnBindEditTextListener() {
            @Override
            public void onBindEditText(@NonNull EditText editText) {
                editText.setInputType(InputType.TYPE_CLASS_NUMBER);
            }
        });

        numberOfJumpes.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
            @Override
            public boolean onPreferenceChange(Preference preference, Object newValue) {
                int new_int = Integer.parseInt(String.valueOf(newValue));
                if((new_int<0)||(new_int>100))
                {
                    Toast.makeText(getActivity(),"must be between 1 and 0",Toast.LENGTH_SHORT).show();
                    return false;
                }
                 return true;
            }
        });


        numberOfStates.setOnBindEditTextListener(new EditTextPreference.OnBindEditTextListener() {
            @Override
            public void onBindEditText(@NonNull EditText editText) {
                editText.setInputType(InputType.TYPE_CLASS_NUMBER);
            }
        });

        numberOfStates.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
            @Override
            public boolean onPreferenceChange(Preference preference, Object newValue) {
                int new_int = Integer.parseInt(String.valueOf(newValue));
                if((new_int<0)||(new_int>20))
                {
                    Toast.makeText(getActivity(),"must be between 1 and 20",Toast.LENGTH_SHORT).show();
                    return false;
                }
                return true;
            }
        });



    }

}