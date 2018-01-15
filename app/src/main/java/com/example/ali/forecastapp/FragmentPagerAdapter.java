package com.example.ali.forecastapp;



import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import com.example.ali.forecastapp.fragments.CurrentWeatherFragment;
import com.example.ali.forecastapp.fragments.WeeklyWeatherFragment;

/**
 * Created by ali on 5/1/18.
 */

public class FragmentPagerAdapter extends android.support.v4.app.FragmentPagerAdapter{

    public Context getmContext() {
        return mContext;
    }

    private Context mContext;

    public FragmentPagerAdapter(Context context, FragmentManager fm) {
        super(fm);
        mContext = context;
    }

    public Fragment getItem(int position) {

        if (position == 0) {
            return CurrentWeatherFragment.newInstance();
        } else if (position == 1){
            return WeeklyWeatherFragment.newInstance();
        }
        return null;
    }

    public int getCount() {
        return 2;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return mContext.getString(R.string.currentWeather);
            case 1:
                return mContext.getString(R.string.weekWeather);
            default:
                return null;
        }
    }
}