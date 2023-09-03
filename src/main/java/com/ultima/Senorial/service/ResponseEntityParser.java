package com.ultima.Senorial.service;
import com.ultima.Senorial.Repository.MealRepository;
import com.ultima.Senorial.dto.WeekResponse;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Service
public class ResponseEntityParser {

    @Value("${uri.endpoint1}")
    String apiUrlEndpoint;

    @Autowired
    MealRepository mealRepository;


    @Test
    public ResponseEntity<?> getspoonacularResponse(String numCalories, String diet,
                                                     String exclusions, String timeFrame, Class<?> responseClass) {
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(apiUrlEndpoint)
                .queryParam("timeFrame", timeFrame)
                .queryParam("apiKey", "ccf6fe0c1b514dae80053bfdfb5e229c");


        if (!StringUtils.isEmpty(numCalories)) {
            builder = builder.queryParam("targetCalories", Integer.parseInt(numCalories));
        }
        if (!StringUtils.isEmpty(diet)) {
            builder = builder.queryParam("diet", diet);
        }
        if (!StringUtils.isEmpty(exclusions)) {
            builder = builder.queryParam("exclude", exclusions);
        }

        URI uri = builder.build().toUri();

        RestTemplate rt = new RestTemplate();
        ResponseEntity<?> responseEntity = rt.getForEntity(uri, responseClass);





        if (responseClass == WeekResponse.class){
            WeekResponse responseObject =  rt.getForObject(uri, WeekResponse.class);

                MealRepository.getWeekResponse().add(MealRepository.getWeekResponse().size(), responseObject);


        }

        System.out.println(responseClass);


        System.out.println(responseEntity);
        return responseEntity;
    }
}
