package weather;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class WeatherApp {

    public static void main(String[] args) {

        boolean endProgram = false;
        int response;
        String city;

        Input input = Input.getInstance();
        WeatherConditions weatherConditions = WeatherConditions.getInstance();

        StringBuilder menu = new StringBuilder();
        menu.append("\nWEATHER APP");
        menu.append("\n1.  Weather Now by City");
        menu.append("\n2.  Forecast by City");
        menu.append("\n3.  Forecast for 5 Cities");
        menu.append("\n4.  Max Temp and Wind Speed in Forecast for City");
        menu.append("\n5.  Sort Forecast for City by Temp");
        menu.append("\n6.  Sort Forecast for City by Wind Speed");
        menu.append("\n7.  Exit");
        menu.append("\n\nPlease choose an item: ");

        while (!endProgram) {

            response = input.getInt(menu.toString());

            switch (response) {
                case 1:
                    city = input.getString("Enter a city: ");
                    try {
                        WeatherNow weatherCity = weatherConditions.getWeatherByLocation(city);
                    System.out.println(weatherCity.toString());
                    }
                    catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                case 2:
                    city = input.getString("Enter a city: ");
                    try {
                        Forecast forecastCity = weatherConditions.getForecastByLocation(city);
                        weatherConditions.displayForecast(forecastCity);
                    }
                    catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                case 3:
                    List<String> cities = new ArrayList<>();
                    cities.add("Rexburg");
                    cities.add("Boise");
                    cities.add("Los Angeles");
                    cities.add("Tampa");
                    cities.add("New Orleans");
                    for (String listItem : cities) {
                        try {
                            System.out.println("Forecast for: " + listItem);
                            Forecast forecastCity = weatherConditions.getForecastByLocation(listItem);
                            weatherConditions.displayForecast(forecastCity);
                        }
                        catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                    break;
                case 4:
                    city = input.getString("Enter a city: ");
                    try {
                        Forecast forecastCity = weatherConditions.getForecastByLocation(city);
                        weatherConditions.displayMaxTempAndWindFromForecast(forecastCity);
                    }
                    catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                case 5:
                    city = input.getString("Enter a city: ");
                    try {
                        Forecast forecastCity = weatherConditions.getForecastByLocation(city);
                        Collections.sort(forecastCity.getList(), Comparators.TEMP);
                        weatherConditions.displayForecast(forecastCity);
                    }
                    catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                case 6:
                    city = input.getString("Enter a city: ");
                    try {
                        Forecast forecastCity = weatherConditions.getForecastByLocation(city);
                        Collections.sort(forecastCity.getList(), Comparators.WIND);
                        weatherConditions.displayForecast(forecastCity);
                    }
                    catch (Exception e) {
                        e.printStackTrace();
                    }
                    break;
                case 7:
                    endProgram = true;
                    break;
                default:
                    System.out.println("INVALID - NOT A MENU ITEM");
                    break;
            }
        }
    }
}
