package com.example.ali.forecastapp;

/**
 * Created by ali on 15/1/18.
 */

public class Utilities {

    public static String getCurrentTemperature(Current.Weather w) {
        StringBuilder sb = new StringBuilder();
        if (PreferencesManager.getInstance().getUserDegreeFormat().equals(PreferencesManager.C)) {
            sb.append(w.getTempC());
            sb.append(PreferencesManager.C);
            return sb.toString();
        } else {
            sb.append(w.getTempF());
            sb.append(PreferencesManager.F);
            return sb.toString();
        }
    }

    public static String getFeelingTemperature(Current.Weather w) {
        StringBuilder sb = new StringBuilder();
        if (PreferencesManager.getInstance().getUserDegreeFormat().equals(PreferencesManager.C)) {
            sb.append(w.getFeelslikeC());
            sb.append(PreferencesManager.C);
            return sb.toString();
        } else {
            sb.append(w.getFeelslikeF());
            sb.append(PreferencesManager.F);
            return sb.toString();
        }
    }

    public static String getWindSpeed(Current.Weather w) {
        StringBuilder sb = new StringBuilder();
        if (PreferencesManager.getInstance().getUserSpeed().equals(PreferencesManager.KM)) {
            sb.append(w.getWindKPH());
            sb.append(PreferencesManager.KM);
            return sb.toString();
        } else {
            sb.append(w.getWindMPH());
            sb.append(PreferencesManager.MI);
            return sb.toString();
        }
    }

    public static String getMinTemp(Forecast.ForecastDay fd) {
        StringBuilder sb = new StringBuilder();
        if (PreferencesManager.getInstance().getUserDegreeFormat().equals(PreferencesManager.C)) {
            sb.append(fd.getMintempC());
            return sb.toString();
        } else {
            sb.append(fd.getMintempF());
            return sb.toString();
        }
    }

    public static String getMaxTemp(Forecast.ForecastDay fd) {
        StringBuilder sb = new StringBuilder();
        if (PreferencesManager.getInstance().getUserDegreeFormat().equals(PreferencesManager.C)) {
            sb.append(fd.getMaxtempC());
            return sb.toString();
        } else {
            sb.append(fd.getMaxtempF());
            return sb.toString();
        }
    }
}
