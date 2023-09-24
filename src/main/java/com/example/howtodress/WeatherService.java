package com.example.howtodress;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
    public class WeatherService {

    private  final String UNITS="metric";
        private final WebClient webClient;
        private final String apiKey;

        public WeatherService(WebClient.Builder webClientBuilder, @Value("${openweathermap.apiKey}") String apiKey) {
            this.webClient = webClientBuilder.baseUrl("https://api.openweathermap.org/data/2.5").build();
            this.apiKey = apiKey;
        }

        public OpenWeather getWeatherDataByCity(String city) {

            return webClient.get()
                    .uri(uriBuilder -> uriBuilder
                            .path("/weather")
                            .queryParam("q",city)
                            .queryParam("appid", apiKey)
                            .queryParam("units",UNITS)
                            .build())
                    .retrieve()
                    .bodyToMono(OpenWeather.class)
                    .block();
        }
        

            

}

