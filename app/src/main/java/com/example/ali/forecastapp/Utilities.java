package com.example.ali.forecastapp;

/**
 * Created by ali on 15/1/18.
 */

public class Utilities {

    private static final String CELSIUS = "ºC";
    private static final String FAREHEITH = "ºF";
    private static final String KPH = "KPH";
    private static final String MPH = "MPH";

    public static String getCurrentTemperature(Current.Weather w) {
        StringBuilder sb = new StringBuilder();
        if (PreferencesManager.getInstance().getUserDegreeFormat().equals(CELSIUS)) {
            sb.append(w.getTempC());
            sb.append(CELSIUS);
            return sb.toString();
        } else {
            sb.append(w.getTempF());
            sb.append(FAREHEITH);
            return sb.toString();
        }
    }

    public static String getFeelingTemperature(Current.Weather w) {
        StringBuilder sb = new StringBuilder();
        if (PreferencesManager.getInstance().getUserDegreeFormat().equals(CELSIUS)) {
            sb.append(w.getFeelslikeC());
            sb.append(CELSIUS);
            return sb.toString();
        } else {
            sb.append(w.getFeelslikeF());
            sb.append(FAREHEITH);
            return sb.toString();
        }
    }

    public static String getWindSpeed(Current.Weather w) {
        StringBuilder sb = new StringBuilder();
        if (PreferencesManager.getInstance().getUserDegreeFormat().equals(KPH)) {
            sb.append(w.getWindKPH());
            sb.append(KPH);
            return sb.toString();
        } else {
            sb.append(w.getWindMPH());
            sb.append(MPH);
            return sb.toString();
        }
    }
}
