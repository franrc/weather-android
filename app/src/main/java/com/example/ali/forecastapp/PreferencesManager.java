package com.example.ali.forecastapp;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * Created by dekalabs on 02/08/2016
 */
public class PreferencesManager {


    public static final String C = "ºC";
    public static final String F = "ºF";
    public static final String KM = "KPH";
    public static final String MI = "MPH";

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
        sharedPreferences.edit().putString(USER_DEGREE, userDegreeFormat).commit();
    }

    public String getUserDegreeFormat() {
        return sharedPreferences.getString(USER_DEGREE, C);
    }

    public void setUserSpeed(String userSpeedUnit) {
        sharedPreferences.edit().putString(USER_SPEED, userSpeedUnit).commit();
    }

    public String getUserSpeed() {
        return sharedPreferences.getString(USER_SPEED, KM);
    }

    public void clear(){
        sharedPreferences.edit()
                .clear()
                .apply();
    }
}