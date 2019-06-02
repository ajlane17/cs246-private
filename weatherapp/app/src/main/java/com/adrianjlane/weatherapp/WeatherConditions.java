package com.adrianjlane.weatherapp;

import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.List;

public class WeatherConditions implements OpenWeatherAPI {
    private static WeatherConditions ourInstance = new WeatherConditions();
    private String appId = "804eb00b3dbe6aebde43fe550dd28bd2";
    private String charset = "UTF-8";
    private String units = "imperial";
    private Gson gson = new Gson();

    public static WeatherConditions getInstance() {
        return ourInstance;
    }

    private WeatherConditions() {
    }

    @Override
    public WeatherNow getWeatherByLocation(String location) throws IOException {

        String url = "https://api.openweathermap.org/data/2.5/weather";
        String response = null;

        WeatherNow weatherNow = null;

        // Build url string
        String query = String.format("q=%s&units=%s&APPID=%s",
                URLEncoder.encode(location, charset),
                URLEncoder.encode(units, charset),
                URLEncoder.encode(appId, charset));


        try {
            // Set up http request and fire
            URL u = new URL(url + "?" + query);
            response = getJSONResponse(u);
            weatherNow = gson.fromJson(response, WeatherNow.class);
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        return weatherNow;
    }

    @Override
    public Forecast getForecastByLocation(String location) throws IOException {
        String url = "https://api.openweathermap.org/data/2.5/forecast";
        String response = null;

        Forecast forecast = null;

        // Build url string
        String query = String.format("q=%s&units=%s&APPID=%s",
                URLEncoder.encode(location, charset),
                URLEncoder.encode(units, charset),
                URLEncoder.encode(appId, charset));


        try {
            // Set up http request and fire
            URL u = new URL(url + "?" + query);
            response = getJSONResponse(u);
            forecast = gson.fromJson(response, Forecast.class);
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        return forecast;
    }

    @Override
    public void displayForecast(Forecast forecast) {
        List<ForecastList> forecastListList = forecast.getList();

            for (ForecastList listItem : forecastListList) {
                StringBuilder sb = new StringBuilder();
                sb.append("\nTime: " + listItem.getDtTxt());
                sb.append("\n\tTemp: " + listItem.getMeasurements().getTemp());
                sb.append("\n\tWeather: " + listItem.getWeather().get(0).getDescription());
                sb.append("\n\tWind Speed: " + listItem.getWind().getSpeed());
                System.out.println(sb.toString());
            }
    }

    @Override
    public void displayMaxTempAndWindFromForecast(Forecast forecast) {
        double temp = -1000;
        double wind = -1;

        for (ForecastList listItem : forecast.getList()) {
            double forecastTemp = listItem.getMeasurements().getTemp();
            double forecastWind = listItem.getWind().getSpeed();
            if (forecastTemp > temp) {
                temp = forecastTemp;
            }
            if (forecastWind > wind) {
                wind = forecastWind;
            }
        }

        System.out.println("\nCity: " + forecast.getCity().getName());

        if (temp > -1000) {
            System.out.println("\nMax Temp: " + temp);
        }
        else {
            System.out.println("\nMax Temp: N/A");
        }

        if (wind > -1) {
            System.out.println("\nMax Wind Speed: " + wind);
        }
        else {
            System.out.println("\nMax Wind Speed: N/A");
        }
    }

    private String getJSONResponse(URL url) {

        HttpURLConnection connection = null;
        String response = null;

        try {
            connection  = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Accept-Charset", charset);
            connection.connect();

            // If returns success, build response and set it
            int status = connection.getResponseCode();
            if (status == 200) {
                // Read the response into a buffer, then string
                BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                StringBuilder sb = new StringBuilder();
                String line;
                while ((line = br.readLine()) != null) {
                    sb.append(line + "\n");
                }
                response = sb.toString();
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            if (connection != null) {
                try {
                    connection.disconnect();
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        return response;
    }
}
