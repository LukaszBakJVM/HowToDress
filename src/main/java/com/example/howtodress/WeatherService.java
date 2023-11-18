package com.example.howtodress;



import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
    public class WeatherService {
    private final int MEN_HOT = 0;
    private final int MEN_COLD =1;
    private final int WOMEN_HOT =2;
    private final int WOMEN_COLD =3;

   public String city="{city}";

    private  final String UNITS="metric";

        private final WebClient webClient;
        private final String apiKey="Api key";
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
        


         protected     int howToDress(String s){

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
    protected String weather(int i){

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

