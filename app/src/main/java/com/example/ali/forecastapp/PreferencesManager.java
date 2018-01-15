package com.example.ali.forecastapp;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * Created by dekalabs on 02/08/2016
 */
public class PreferencesManager {


    private final String USER_DEGREE = "preferences.user.degree";
    private final String USER_SPEED = "preferences.user.speed";

    private SharedPreferences sharedPreferences;

    private static PreferencesManager sInstance;


    private PreferencesManager(Context context) {
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
    }

    public static void initialize(Context context) {
        sInstance = new PreferencesManager(context);
    }

    public static PreferencesManager getInstance() {
        if (sInstance == null) {
            throw new IllegalStateException("You must call PreferencesManager.initialize in the onCreate method of your custom Application");
        }

        return sInstance;
    }

    public void setUserDegreeFormat(String userDegreeFormat) {
        sharedPreferences.edit().putString(USER_DEGREE, userDegreeFormat).apply();
    }

    public String getUserDegreeFormat() {
        return sharedPreferences.getString(USER_DEGREE, "ºC");
    }

    public void setUserSpeed(String userSpeedUnit) {
        sharedPreferences.edit().putString(USER_SPEED, userSpeedUnit).commit();
    }

    public String getUserSpeed() {
        return sharedPreferences.getString(USER_SPEED, "KPH");
    }


    public void clear(){
        sharedPreferences.edit()
                .clear()
                .apply();
    }
}