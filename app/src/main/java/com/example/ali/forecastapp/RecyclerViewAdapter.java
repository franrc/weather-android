package com.example.ali.forecastapp;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by ali on 9/1/18.
 */

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>{

    private List<Forecast.ForecastDay> values;

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView day;
        public TextView tempMin;
        public TextView tempMax;
        public ImageView icon;
        public View layout;

        public ViewHolder(View v) {
            super(v);
            layout = v;
            day = (TextView) v.findViewById(R.id.dayRV);
            tempMin = (TextView) v.findViewById(R.id.tempMin);
            tempMax = (TextView) v.findViewById(R.id.tempMax);
            icon = (ImageView) v.findViewById(R.id.iconRV);

        }
    }

    public void add(int position, Forecast.ForecastDay item) {
        values.add(position, item);
        notifyItemInserted(position);
    }

    public void remove(int position) {
        values.remove(position);
        notifyItemRemoved(position);
    }

    public RecyclerViewAdapter(List<Forecast.ForecastDay> myDataset) {
        values = myDataset;
    }

    @Override
    public RecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View v = inflater.inflate(R.layout.row_layout, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(RecyclerViewAdapter.ViewHolder holder, int position) {
        Forecast.ForecastDay fday = values.get(position);

        SimpleDateFormat newDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date myDate = null;
        try {
            myDate = newDateFormat.parse(fday.getDate());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        newDateFormat.applyPattern("EEEE");

        final String day = newDateFormat.format(myDate);
        holder.day.setText(day);
        Picasso.with(holder.icon.getContext()).load(fday.getIcon()).error(R.mipmap.ic_launcher).into(holder.icon);

        final String tempMin = Double.toString(fday.getMintempC());
        holder.tempMin.setText(tempMin);
        final String tempMax = Double.toString(fday.getMaxtempC());
        holder.tempMax.setText(tempMax);
    }

    @Override
    public int getItemCount() {
        return values.size();
    }
}
