package com.example.ali.forecastapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.CompoundButton;
import android.widget.Switch;

public class PreferencesActivity extends AppCompatActivity {



    Switch tempUnit;
    Switch windSpeedUnit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_preferences);
        tempUnit = (Switch) findViewById(R.id.tUnitSwitch);
        windSpeedUnit = (Switch) findViewById(R.id.wUnitSwitch);

        if(PreferencesManager.getInstance().getUserDegreeFormat().equals(PreferencesManager.C)) {
            tempUnit.setChecked(false);
        } else {
            tempUnit.setChecked(true);
        }

        if(PreferencesManager.getInstance().getUserSpeed().equals(PreferencesManager.KM)) {
            windSpeedUnit.setChecked(false);
        } else {
            windSpeedUnit.setChecked(true);
        }

        tempUnit.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (!isChecked) {
                    PreferencesManager.getInstance().setUserDegreeFormat(PreferencesManager.C);
                    Log.i("State: ", "off");
                } else {
                    PreferencesManager.getInstance().setUserDegreeFormat(PreferencesManager.F);
                    Log.i("State: ", "on");
                }
            }
        });

        windSpeedUnit.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(!isChecked) {
                    PreferencesManager.getInstance().setUserSpeed(PreferencesManager.KM);
                } else {
                    PreferencesManager.getInstance().setUserSpeed(PreferencesManager.MI);
                }
            }
        });
    }
}
