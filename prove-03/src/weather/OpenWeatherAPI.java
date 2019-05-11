package weather;

import java.io.IOException;

public interface OpenWeatherAPI {
    WeatherNow getWeatherByLocation(String location) throws IOException;

    Forecast getForecastByLocation(String location) throws IOException;

    void displayForecast(Forecast forecast);

    void displayMaxTempAndWindFromForecast(Forecast forecast);
}
