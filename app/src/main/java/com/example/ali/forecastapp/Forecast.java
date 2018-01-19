package com.example.ali.forecastapp;

import android.util.Log;

import com.fasterxml.jackson.annotation.JsonProperty;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by ali on 8/1/18.
 */

public class Forecast extends Current {

    List<ForecastDay> forecastDayList;

    @JsonProperty("forecast")
    public void unpackForecast(Map<String, Object> map) {
        if(map == null) return;

        Object forecastDayList = map.get("forecastday");

        if(forecastDayList != null && forecastDayList instanceof Collection) {
            List forecastDays = (List) forecastDayList;

            this.forecastDayList = new ArrayList<>();

            for(Object day : forecastDays) {

                    ForecastDay fDay = myRetrofit.DEFAULT_MAPPER.convertValue(day, ForecastDay.class);

                    this.forecastDayList.add(fDay);
            }
        }
    }

    public List<ForecastDay> getForecastDayList() { return  forecastDayList; }

    public Current.Weather getCurrent() { return current; }

    public static class ForecastDay {

        private double mintempC;
        private double maxtempC;

        private double mintempF;
        private double maxtempF;

        private String date;
        private String conditionText;
        private String conditionIcon;

        public double getMintempC() {
            return mintempC;
        }

        public double getMaxtempC() {
            return maxtempC;
        }

        public double getMintempF() {
            return mintempF;
        }

        public double getMaxtempF() {
            return maxtempF;
        }

        public String getConditionText() {
            return conditionText;
        }

        public String getIcon() {
            return conditionIcon;
        }

        public String getDate() {
            return date;
        }

        @JsonProperty("day")
        private void unpackNestedDay(Map<String,Object> day) {
            this.mintempC = (double) day.get("mintemp_c");
            this.maxtempC = (double) day.get("maxtemp_c");
            this.mintempF = (double) day.get("mintemp_f");
            this.maxtempF = (double) day.get("maxtemp_f");

            unpackNestedCondition((Map)day.get("condition"));
        }

        private void unpackNestedCondition(Map<String,Object> condition) {
            this.conditionIcon = (String)condition.get("icon");

            if(!conditionIcon.startsWith("http")) {
                conditionIcon = "http:" + conditionIcon;
            }

            this.conditionText = (String)condition.get("text");
        }
    }
}
