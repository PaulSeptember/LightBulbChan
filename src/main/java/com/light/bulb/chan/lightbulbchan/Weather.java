package com.light.bulb.chan.lightbulbchan;

import com.light.bulb.chan.lightbulbchan.models.LBCMessage;
import com.light.bulb.chan.lightbulbchan.models.Response;
import com.light.bulb.chan.lightbulbchan.models.WeatherResponse;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

@Service
public class Weather {
    private final RestTemplate restTemplate;
    private final HttpHeaders headers;

    public static String parseWeather(final WeatherResponse response) {
        StringBuilder result = new StringBuilder();
        Long temp = Long.parseLong(response.getFact().getFeelsLike());
        Float cloudness = Float.parseFloat(response.getFact().getCloudness());
        Float prec = Float.parseFloat(response.getFact().getPrecStrength());
        Float precType = Float.parseFloat(response.getFact().getPrecType());

        if (temp < 0) {
            result.append("Кажется на улице холодно. Советую одется по теплее!\n");
        }

        if (temp > 25) {
            result.append("ЖАААРКО. Шорты твой выход)\n");
        }

        if (cloudness > 0) {
            result.append("☁\n");
        }

        if (prec > 0) {
            result.append("Имеются осадки!\n");
        }

        result.append("Ощущаемая температура: ").append(temp).append(" C\n Скорость ветра: " + response.getFact().getWindSpeed()).append(" м/c");
        return result.toString();
    }

    public Weather() {
        restTemplate = new RestTemplate();
        headers = new HttpHeaders();
        headers.add("X-Yandex-API-Key", "f43e6136-6717-4212-905c-73aa0e9385b7");
    }

    public Response<WeatherResponse> getWeather(final String lan, final String lon) {
        String url = String.format("https://api.weather.yandex.ru/v1/forecast?lat=%s&lon=%s&lang=ru_RU&limit=1&extra=1", lan, lon);
        try {
            HttpEntity<String> request = new HttpEntity<String>(headers);
            WeatherResponse response = restTemplate.exchange(url, HttpMethod.GET, request, WeatherResponse.class).getBody();
            return new Response<>(response, true);
        } catch (RestClientException e) {
            e.getLocalizedMessage();
            return new Response<>(null, false);
        }
    }
}
