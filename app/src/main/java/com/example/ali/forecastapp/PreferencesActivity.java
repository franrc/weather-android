package com.example.ali.forecastapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.CompoundButton;
import android.widget.Switch;

public class PreferencesActivity extends AppCompatActivity {

    private static final String C = "CELSIUS";
    private static final String F = "FARENHEIT";
    private static final String KM = "KPH";
    private static final String MI = "MPH";
    Switch tempUnit;
    Switch windSpeedUnit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preferences);
        tempUnit = (Switch) findViewById(R.id.tUnitSwitch);
        windSpeedUnit = (Switch) findViewById(R.id.wUnitSwitch);

        tempUnit.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    PreferencesManager.getInstance().setUserDegreeFormat(F);
                } else {
                    PreferencesManager.getInstance().setUserDegreeFormat(C);
                }
            }
        });

        windSpeedUnit.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    PreferencesManager.getInstance().setUserSpeed(MI);
                } else {
                    PreferencesManager.getInstance().setUserSpeed(KM);
                }
            }
        });
    }
}
