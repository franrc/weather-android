package com.example.ali.forecastapp;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;

import com.example.ali.forecastapp.fragments.CurrentWeatherFragment;

/**
 * Created by ali on 10/1/18.
 */

public class LocationGetter implements LocationListener {

    private Context mContext;
    private double latitude;
    private double longitude;

    public LocationGetter(Context mContext) {
        this.mContext = mContext;
    }

    public Location getLocation() {

        LocationManager locationManager;
        String provider;
        String context = Context.LOCATION_SERVICE;
        locationManager = (LocationManager) mContext.getSystemService(context);
        provider = locationManager.getBestProvider(new Criteria(), false);
        @SuppressLint("MissingPermission")
        Location location = locationManager.getLastKnownLocation(provider);
            if (location != null) {
                latitude = location.getLatitude();
                longitude = location.getLongitude();
            }
        return location;
    }

    public double getLatitude() { return latitude; }

    public double getLongitude() { return longitude; }

    @Override
    public void onLocationChanged(Location location) {
        latitude = location.getLatitude();
        longitude = location.getLongitude();
    }

    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) {

    }

    @Override
    public void onProviderEnabled(String s) {

    }

    @Override
    public void onProviderDisabled(String s) {

    }

    public Context getContext() {
        return mContext;
    }
}
