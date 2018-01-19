package com.example.ali.forecastapp;

import android.util.Log;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;

import java.text.SimpleDateFormat;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

/**
 * Created by ali on 8/1/18.
 */

public class myRetrofit {

    public static ObjectMapper DEFAULT_MAPPER = new ObjectMapper()
            .setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"))
            .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
            .registerModule(new SimpleModule());

    HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor()
                                            .setLevel(HttpLoggingInterceptor.Level.BODY);

    OkHttpClient.Builder builder = new OkHttpClient.Builder()
            .connectTimeout(120, TimeUnit.SECONDS)
                    .readTimeout(120, TimeUnit.SECONDS)
                    .addInterceptor(interceptor);

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://api.apixu.com/v1/")
            .addConverterFactory(JacksonConverterFactory.create(DEFAULT_MAPPER.copy()))
            .client(builder.build())
            .build();



    GitHubService myService = retrofit.create(GitHubService.class);


    public void getCurrentWeather(String city, final ServiceResponse<Current> responseHandler) {
        Call<Current> currentWeatherCall = myService.getCurrentWeather(city);

        currentWeatherCall.enqueue(new Callback<Current>() {
            @Override
            public void onResponse(Call<Current> call, Response<Current> response) {
                Current currentWeather = response.body();

                if(responseHandler != null) {
                    responseHandler.onResponse(currentWeather);
                }
            }

            @Override
            public void onFailure(Call<Current> call, Throwable t) {

            }
        });
    }

    public void getForecast(String city, int days, final ServiceResponse<Forecast> responseHandler) {
        Call<Forecast> forecastCall = myService.getForecast(city, days);

        forecastCall.enqueue(new Callback<Forecast>() {
            @Override
            public void onResponse(Call<Forecast> call, Response<Forecast> response) {
                Forecast forecast = response.body();

                if(responseHandler != null) {
                    responseHandler.onResponse(forecast);
                }
            }

            @Override
            public void onFailure(Call<Forecast> call, Throwable t) {

            }
        });
    }
}
