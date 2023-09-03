package com.ultima.Senorial.service;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ultima.Senorial.Repository.MealRepository;
import com.ultima.Senorial.dto.WeekResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

@Service
public class HttpJsonParser {


    @Value("${url.api1}")
    String apiUrl;



    public  List<WeekResponse> callApiExample2() throws IOException, InterruptedException {



//        String apiUrl = "https://api.spoonacular.com/mealplanner/generate?apiKey=ccf6fe0c1b514dae80053bfdfb5e229c";

        URL url = new URL(apiUrl);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");


        int responseCode = connection.getResponseCode();
        if (responseCode == HttpURLConnection.HTTP_OK) {
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String inputLine;
            StringBuilder response = new StringBuilder();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            String jsonResponse = response.toString();

            ObjectMapper objectMapper = new ObjectMapper();
            WeekResponse responseObject = objectMapper.readValue(jsonResponse, WeekResponse.class);


            System.out.println(responseObject);

            MealRepository.getWeekResponse().add(responseObject);

        } else {
            System.out.println("HTTP Request Failed with response code: " + responseCode);
        }

        return  MealRepository.getWeekResponse();


    }





}
