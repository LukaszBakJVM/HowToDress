package com.example.howtodress;



import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
    public class WeatherService {

   public String city="{city}";

    private  final String UNITS="metric";

        private final WebClient webClient;
        private final String apiKey="";
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
        


         public    int howToDress(String s){

                double temp = (int)getWeatherDataByCity().main().temp();


             if (temp>14&&s.equals("Mężczyzna")){
                    return 0;
                }else if (temp<14&&s.equals("Mężczyzna")){
                    return 1;
                }else if (temp>14&&s.equals("Kobieta")){
                    return 2;
                }else {
                    return 3;
                }


            }


}

