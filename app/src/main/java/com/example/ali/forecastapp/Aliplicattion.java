package com.example.ali.forecastapp;

import android.app.Application;
import android.widget.Switch;

import com.example.ali.forecastapp.PreferencesManager;

/**
 * Created by ali on 15/1/18.
 */

public class Aliplicattion extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        PreferencesManager.initialize(this);
    }
}
