package com.example.ali.forecastapp;

import android.location.Location;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Map;

import retrofit2.http.Query;

/**
 * Created by ali on 8/1/18.
 */

public class Current {

    Weather current;
    LocationWeather location;

    public Weather getCurrent() { return current; }

    public LocationWeather getLocationWeather() { return location; }

    public static class Weather {

        private String descriptionText;
        private String conditionIcon;

        @JsonProperty("temp_c")
        private double tempC;
        @JsonProperty("feelslike_c")
        private double feelslikeC;

        @JsonProperty("temp_f")
        private double tempF;
        @JsonProperty("feelslike_f")
        private double feelslikeF;

        @JsonProperty("wind_kph")
        private double windKPH;
        @JsonProperty("wind_MPH")
        private double windMPH;
        @JsonProperty("wind_dir")
        private String windDir;

        @JsonProperty("humidity")
        private double humidity;
        @JsonProperty("cloud")
        private double cloudiness;

        @JsonProperty("condition")
        private void unpackNested(Map<String,Object> condition) {
            this.conditionIcon = (String)condition.get("icon");

            if(!conditionIcon.startsWith("http")) {
                conditionIcon = "http:" + conditionIcon;
            }

            this.descriptionText = (String)condition.get("text");
        }

        public String getDescription() {
            return descriptionText;
        }

        public String getIcon() {
            return conditionIcon;
        }

        public double getTempC() {
            return tempC;
        }

        public double getFeelslikeC() {
            return feelslikeC;
        }

        public double getTempF() {
            return tempF;
        }

        public double getFeelslikeF() {
            return feelslikeF;
        }

        public double getHumidity() {
            return humidity;
        }

        public double getCloudiness() {
            return cloudiness;
        }

        public double getWindKPH() {
            return windKPH;
        }

        public double getWindMPH() {
            return windMPH;
        }

        public String getWindDir() {
            return windDir;
        }
    }

    public static class LocationWeather {

        @JsonProperty("name")
        private String city;
        @JsonProperty("region")
        private String region;
        @JsonProperty("country")
        private String country;

        public String getCity() { return city; }

        public String getRegion() { return region; }

        public String getCountry() { return country; }
    }
}
