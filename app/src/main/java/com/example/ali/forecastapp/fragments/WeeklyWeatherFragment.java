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

import com.example.ali.forecastapp.Current;
import com.example.ali.forecastapp.Forecast;
import com.example.ali.forecastapp.LocationGetter;
import com.example.ali.forecastapp.R;
import com.example.ali.forecastapp.RecyclerViewAdapter;
import com.example.ali.forecastapp.ServiceResponse;
import com.example.ali.forecastapp.Utilities;
import com.example.ali.forecastapp.myRetrofit;

import java.text.BreakIterator;
import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class WeeklyWeatherFragment extends Fragment {

    Forecast f;
    Current.LocationWeather lw;

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
        locationTV = (TextView) view.findViewById(R.id.locationTV);
        weatherTV = (TextView) view.findViewById(R.id.weatherTV);
        temperatureTV = (TextView) view.findViewById(R.id.tempTV);
    }

    @Override
    public void onStart() {
        super.onStart();

        layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);

        LocationGetter lg = new LocationGetter(this.getContext());
        Location location = lg.getLocation();
        StringBuilder loc = new StringBuilder();

        if (location != null) {
            loc.append(Double.toString(location.getLatitude()));
            loc.append(",");
            loc.append(Double.toString(location.getLongitude()));
        } else {
            loc.append(0);
            loc.append(",");
            loc.append(0);
        }

        String locS = "Bangkok";

        new myRetrofit().getForecast(locS, 7, new ServiceResponse<Forecast>() {
            @Override
            public void onResponse(final Forecast forecast) {
                if(forecast == null) {return;}
                else {
                    f = forecast;
                    lw = f.getLocationWeather();
                    setData();
                }
            }
        });
    }

    public void setData() {

        String city = lw.getCity();
        String country = lw.getCountry();
        locationTV.setText(getContext().getString(R.string.loc_text, city, country));

        weatherTV.setText(f.getCurrent().getDescription());
        temperatureTV.setText(getContext().getString(R.string.temp_text, Utilities.getCurrentTemperature(f.getCurrent())));

        List<Forecast.ForecastDay> input = f.getForecastDayList();
        mAdapter = new RecyclerViewAdapter(input);
        recyclerView.setAdapter(mAdapter);
    }
}
