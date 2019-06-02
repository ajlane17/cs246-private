package com.adrianjlane.weatherapp;

import android.os.AsyncTask;
import android.util.Log;

import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class GetForecastTask extends AsyncTask <String,Void,Forecast> {

    private WeatherConditions weatherConditions;
    private String cityName;
    private MainActivity activity;

    public GetForecastTask(MainActivity activity) {
        this.activity = activity;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        // TODO Set notify the user of request in process
    }

    @Override
    protected Forecast doInBackground(String... strings) {
        cityName = strings[0];
        weatherConditions = WeatherConditions.getInstance();
        try {
            Forecast forecast = weatherConditions.getForecastByLocation(cityName);

            return forecast;

        } catch (Exception e) {
            // TODO Add a toast notification for success
            Log.e("GetForecastTask()","Error retrieving  forecast");
            e.printStackTrace();
            return null;
        }
    }

    @Override
    protected void onPostExecute(Forecast forecast) {
        super.onPostExecute(forecast);
        activity.setContentView(R.layout.activity_main);

        if (forecast != null) {

            RecyclerView forecastRecyclerView = activity.findViewById(R.id.forecastList);

            List<ForecastList> forecastList = forecast.getList();

            ForecastAdapter forecastAdapter = new ForecastAdapter(forecastList);

            forecastRecyclerView.setAdapter(forecastAdapter);
            forecastRecyclerView.setLayoutManager(new LinearLayoutManager(activity));
            forecastRecyclerView.addItemDecoration(new DividerItemDecoration(activity, DividerItemDecoration.VERTICAL));
        }
    }
}
