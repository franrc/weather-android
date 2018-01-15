package com.example.ali.forecastapp.fragments;

import android.annotation.SuppressLint;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ali.forecastapp.Current;
import com.example.ali.forecastapp.LocationGetter;
import com.example.ali.forecastapp.R;
import com.example.ali.forecastapp.ServiceResponse;
import com.example.ali.forecastapp.Utilities;
import com.example.ali.forecastapp.myRetrofit;
import com.squareup.picasso.Picasso;

import java.util.prefs.Preferences;

/**
 * A simple {@link Fragment} subclass.
 */
public class CurrentWeatherFragment extends Fragment {

    ImageView weatherIcon;
    TextView temperatureTV;
    TextView sensTV;
    TextView locationTV;
    TextView minTempTV;
    TextView maxTempTV;
    TextView cloudinessTV;
    TextView humidityTV;
    TextView weatherTV;
    TextView windSpeedTV;
    TextView windDirTV;

    public static CurrentWeatherFragment newInstance() {

        Bundle args = new Bundle();

        CurrentWeatherFragment fragment = new CurrentWeatherFragment();
        fragment.setArguments(args);
        return fragment;
    }

    public CurrentWeatherFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_current_weather, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        weatherIcon = (ImageView) view.findViewById(R.id.weatherIcon);
        temperatureTV = (TextView) view.findViewById(R.id.tempText);
        sensTV = (TextView) view.findViewById(R.id.sensText);
        locationTV = (TextView) view.findViewById(R.id.locationText);
        minTempTV = (TextView) view.findViewById(R.id.tempMin);
        maxTempTV = (TextView) view.findViewById(R.id.tempMax);
        cloudinessTV = (TextView) view.findViewById(R.id.clouds);
        humidityTV = (TextView) view.findViewById(R.id.humidity);
        weatherTV = (TextView) view.findViewById(R.id.weatherText);
        windSpeedTV = (TextView) view.findViewById(R.id.windSpeedText);
        windDirTV = (TextView) view.findViewById(R.id.windDirText);

    }

    @Override
    public void onStart() {
        super.onStart();

        LocationGetter lg = new LocationGetter(this.getActivity());
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
        Log.i("Location: ", locS);

        new myRetrofit().getCurrentWeather(locS,
                new ServiceResponse<Current>() {
            @SuppressLint("StringFormatMatches")
            @Override
            public void onResponse(final Current weather) {

                Current.Weather wc = weather.getCurrent();

                Picasso.with(getActivity()).load(wc.getIcon()).error(R.mipmap.ic_launcher).into(weatherIcon);

                String city = weather.getLocationWeather().getCity();
                String country = weather.getLocationWeather().getCountry();
                locationTV.setText(getContext().getString(R.string.loc_text, city, country));

                temperatureTV.setText(getContext().getString(R.string.temp_text, Utilities.getCurrentTemperature(wc)));

                sensTV.setText(getContext().getString(R.string.tempfl_text, Utilities.getFeelingTemperature(wc)));

                String desc = wc.getDescription();
                weatherTV.setText(getContext().getString(R.string.description_text, desc));

                windSpeedTV.setText(getContext().getString(R.string.windSpeed_text, Utilities.getWindSpeed(wc)));

                String windDirection = wc.getWindDir();
                windDirTV.setText(getContext().getString(R.string.windDirection_text, windDirection));

                //minTempTV.setText(Double.toString(weather.getCurrent().getTempMin()));

                //maxTempTV.setText(Double.toString(weather.getCurrent().getTempMax()));

                double humidity = wc.getHumidity();
                humidityTV.setText(getContext().getString(R.string.humidity_text, humidity));

                double cloudiness = wc.getCloudiness();
                cloudinessTV.setText(getContext().getString(R.string.cloudiness_text, cloudiness));

            }
        });
    }

}
