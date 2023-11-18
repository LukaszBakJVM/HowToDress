package com.example.howtodress;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.*;


import java.io.IOException;

@RestController
@RequestMapping("/weather")
public class WeatherController {

    private final WeatherService weatherService;

    public WeatherController(WeatherService weatherService) {
        this.weatherService = weatherService;
    }
    @GetMapping//("/{city}/{gender}")
    void city (@RequestParam String city , @RequestParam String gender, HttpServletResponse response) throws IOException {
        weatherService.city = city;
        int i = weatherService.howToDress(gender);
        String weather = weatherService.weather(i);
        response.sendRedirect( weather);

    }

}
