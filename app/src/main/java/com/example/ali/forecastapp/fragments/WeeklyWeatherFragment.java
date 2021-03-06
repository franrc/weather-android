package com.example.ali.forecastapp.fragments;


import android.location.Location;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.ali.forecastapp.Forecast;
import com.example.ali.forecastapp.LocationGetter;
import com.example.ali.forecastapp.R;
import com.example.ali.forecastapp.RecyclerViewAdapter;
import com.example.ali.forecastapp.ServiceResponse;
import com.example.ali.forecastapp.myRetrofit;

import java.text.BreakIterator;
import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class WeeklyWeatherFragment extends Fragment {

    TextView locationTV;
    TextView weatherTV;
    TextView temperatureTV;

    private RecyclerView recyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;

    public static WeeklyWeatherFragment newInstance() {

        Bundle args = new Bundle();

        WeeklyWeatherFragment fragment = new WeeklyWeatherFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public WeeklyWeatherFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_weekly_weather, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {

        super.onViewCreated(view, savedInstanceState);

        recyclerView = (RecyclerView) getActivity().findViewById(R.id.recylerView);
        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);

        locationTV = (TextView) view.findViewById(R.id.locationTV);
        weatherTV = (TextView) view.findViewById(R.id.weatherTV);
        temperatureTV = (TextView) view.findViewById(R.id.tempTV);
    }


    @Override
    public void onStart() {
        super.onStart();

        LocationGetter lg = new LocationGetter(this.getContext());
        Location location = lg.getLocation();
        StringBuilder loc = new StringBuilder();

        if(location != null) {
            loc.append(Double.toString(location.getLatitude()));
            loc.append(",");
            loc.append(Double.toString(location.getLongitude()));
        } else {
            loc.append(0);
            loc.append(",");
            loc.append(0);
        }

        String locS = loc.toString();

        new myRetrofit().getForecast(locS, 7, new ServiceResponse<Forecast>() {
            @Override
            public void onResponse(Forecast forecast) {

                String city = forecast.getLocationWeather().getCity();
                String country = forecast.getLocationWeather().getCountry();
                locationTV.setText(getContext().getString(R.string.loc_text, city, country));

                weatherTV.setText(forecast.getCurrent().getDescription());
                String t = Double.toString(forecast.getCurrent().getTempC());
                temperatureTV.setText(t + " ºC");

                List<Forecast.ForecastDay> input = forecast.getForecastDayList();
                mAdapter = new RecyclerViewAdapter(input);
                recyclerView.setAdapter(mAdapter);

            }
        });
    }
}
