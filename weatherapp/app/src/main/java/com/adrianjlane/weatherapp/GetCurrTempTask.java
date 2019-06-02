package com.adrianjlane.weatherapp;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.TextView;

public class GetCurrTempTask extends AsyncTask<String,Void,WeatherNow> {

    private WeatherConditions weatherConditions;
    private String cityName;
    private MainActivity activity;

    public GetCurrTempTask(MainActivity activity) {
        this.activity = activity;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        TextView tvCurrTemp = activity.findViewById(R.id.currTemp);
        String message = "Fetching...";
        tvCurrTemp.setText(message);
    }

    @Override
    protected WeatherNow doInBackground(String... strings) {

        cityName = strings[0];
        weatherConditions = WeatherConditions.getInstance();
        try {
            WeatherNow weatherNow = weatherConditions.getWeatherByLocation(cityName);

            return weatherNow;

        } catch (Exception e) {
            // TODO Add a toast notification for success
            Log.e("GetCurrTempTask()","Error retrieving current weather");
            e.printStackTrace();
            return null;
        }
    }

    @Override
    protected void onPostExecute(WeatherNow weatherNow) {
        super.onPostExecute(weatherNow);
        TextView tvCurrTemp = activity.findViewById(R.id.currTemp);
        String strTemp;

        if (weatherNow != null) {
            Log.i("GetCurrTempTask()",weatherNow.toString());
            Double tempfNow = weatherNow.getMeasurements().getTemp();
            String strDouble = String.format("%.1f", tempfNow);
            strTemp = strDouble + (char) 0x00B0;
            tvCurrTemp.setText(strTemp);
        } else {
            // TODO Add a toast notification for failure
            strTemp = "Error";
            tvCurrTemp.setText(strTemp);
        }

    }
}
