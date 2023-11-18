package com.example.howtodress;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.*;


import java.io.IOException;

@RestController
@RequestMapping("/weather")
public class WeatherController {
    private final int MEN_HOT = 0;
    private final int MEN_COLD =1;
    private final int WOMEN_HOT =2;
    private final int WOMEN_COLD =3;
    private final WeatherService weatherService;

    public WeatherController(WeatherService weatherService) {
        this.weatherService = weatherService;
    }
    @GetMapping//("/{city}/{gender}")
    void city (@RequestParam String city , @RequestParam String gender, HttpServletResponse response) throws IOException {
        weatherService.city = city;
        int i = weatherService.howToDress(gender);
        String weather = weather(i);
        response.sendRedirect( weather);

    }
    private String weather(int i){

        String result;
        switch (i){

            case MEN_HOT:
                result = "https://www.zalando.pl/odziez-meska-koszulki";
                break;

            case MEN_COLD:
                result = "https://www.zalando.pl/odziez-meska-kurtki";
                break;
            case WOMEN_HOT:
                result = "https://www.zalando.pl/odziez-damska-sukienki";
                break;
            case WOMEN_COLD:
                result = "https://www.zalando.pl/odziez-damska-kurtki-plaszcze";
                break;
            default:
                result = "http://localhost:8080";

        }
        return result;
    }
}
