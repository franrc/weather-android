package com.example.ali.forecastapp;

/**
 * Created by ali on 8/1/18.
 */

public interface ServiceResponse<DATA> {
     void onResponse(DATA data);
}
