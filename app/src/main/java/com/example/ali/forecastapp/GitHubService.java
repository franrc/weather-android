package com.example.ali.forecastapp;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by ali on 8/1/18.
 */

public interface GitHubService {

    @GET("current.json?key=31d8046675a641648c984315180501")
    Call<Current> getCurrentWeather(@Query("q") String city);

    @GET("forecast.json?key=31d8046675a641648c984315180501")
    Call<Forecast> getForecast(@Query("q") String city, @Query("days") int days);

}
