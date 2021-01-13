package com.example.ms1;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class PreferncesActivity extends AppCompatActivity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acitivity_settings);
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_layout, new MySettingsFragment())
                .commit();


    }





}
