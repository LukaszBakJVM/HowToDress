package com.example.howtodress;

import java.util.List;

public record OpenWeather(List<OpenWeatherWeather> weather,OpenWeatherMain main, OpenWeatherWind wind ) {
}
