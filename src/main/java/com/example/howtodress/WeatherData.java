package com.example.howtodress;

public record WeatherData(  int cityName, String description,
                            double temperature, double humidity, double windSpeed) {
}
