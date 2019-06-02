package com.adrianjlane.weatherapp;

import java.util.Comparator;

public class Comparators {
    public static final Comparator<ForecastList> TEMP = (ForecastList o1, ForecastList o2) -> o1.getMeasurements().getTemp().compareTo(o2.getMeasurements().getTemp());
    public static final Comparator<ForecastList> WIND = (ForecastList o1, ForecastList o2) -> o1.getWind().getSpeed().compareTo(o2.getWind().getSpeed());
}
