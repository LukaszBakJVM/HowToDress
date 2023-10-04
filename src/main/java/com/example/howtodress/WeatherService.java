package com.example.howtodress;



import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
    public class WeatherService {
   public String city="{city}";

    private  final String UNITS="metric";

        private final WebClient webClient;
        private final String apiKey="your api key";
        private final String LANG="pl";

        public WeatherService(WebClient.Builder webClientBuilder) {
            this.webClient = webClientBuilder.baseUrl("https://api.openweathermap.org/data/2.5").build();

        }

        private OpenWeather getWeatherDataByCity() {


            return webClient.get()
                    .uri(uriBuilder -> uriBuilder
                            .path("/weather")
                            .queryParam("q",city)
                            .queryParam("appid", apiKey)
                            .queryParam("units",UNITS)
                            .queryParam("lang",LANG)
                            .build())
                    .retrieve()
                    .bodyToMono(OpenWeather.class)
                    .block();
        }
        


         public    String howToDress(){
                double speed = getWeatherDataByCity().wind().speed();
                double temp = (int)getWeatherDataByCity().main().temp();
                OpenWeatherWeather openWeatherWeather = getWeatherDataByCity().weather().get(0);
                String description = openWeatherWeather.description();
                if (speed>5||temp<14){
                    return "Ubierz sie ciepło "+description+" "+temp+"C";
                }
                return description+" "+temp+"C";

            }

}

