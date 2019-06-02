package com.adrianjlane.weatherapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ForecastAdapter extends RecyclerView.Adapter<ForecastAdapter.MyViewHolder> {
    private List<ForecastList> forecastList;

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView textViewDt;
        public TextView textViewTemp;
        public TextView textViewWeather;
        public MyViewHolder(View v) {
            super(v);
            textViewDt = v.findViewById(R.id.forecast_dt);
            textViewTemp = v.findViewById(R.id.forecast_temp);
            textViewWeather = v.findViewById(R.id.forecast_weather);
        }
    }

    public ForecastAdapter(List<ForecastList> forecastList) {
        this.forecastList = forecastList;
    }

    @Override
    public ForecastAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.forecast_item, parent, false);

        MyViewHolder vh = new MyViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        ForecastList forecastItem = forecastList.get(position);

        String displayDt = "Datetime: " + forecastItem.getDtTxt();
        String displayTemp = "Temp: " + forecastItem.getMeasurements().getTemp().toString();
        String displayWeather = forecastItem.getWeather().get(0).getDescription();

        holder.textViewDt.setText(displayDt);
        holder.textViewTemp.setText(displayTemp);
        holder.textViewWeather.setText(displayWeather);
    }

    @Override
    public int getItemCount() {
        return forecastList.size();
    }
}
