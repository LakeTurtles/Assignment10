package com.ultima.Senorial.web;
import com.ultima.Senorial.Repository.MealRepository;
import com.ultima.Senorial.domain.Nutrients;
import com.ultima.Senorial.dto.*;
import com.ultima.Senorial.service.JsonParser;
import com.ultima.Senorial.service.SpoonacularResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;


@RestController
public class SpoonacularController {

	@Autowired
	SpoonacularResponse spoonacularResponse;
	@Autowired
	JsonParser jsonParser;
	@Autowired
	MealRepository mealRepository;


	@RequestMapping("/${url.today}")
	@SuppressWarnings("unchecked")
	@GetMapping()
	public DayResponse getToday() throws IOException, InterruptedException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
		jsonParser.callApiExample2();
		
		String todaysDate = new SimpleDateFormat("EEEE").format(new Date());
		String methodName = "get" + todaysDate ;

		return  (DayResponse) MealRepository.getWeekResponse().get(0).getWeek()
				.getClass()
				.getMethod(methodName)
				.invoke(MealRepository.getWeekResponse().get(0).getWeek());
	}

	@GetMapping("mealplanner/tuesday")
	public DayResponse getTuesday() throws IOException, InterruptedException {
		jsonParser.callApiExample2();
		return MealRepository.getWeekResponse().get(0).getWeek().getTuesday();
	}

	@GetMapping("mealplanner/tuesday/titles")
	public List<String> getSpecific() throws IOException, InterruptedException {
		jsonParser.callApiExample2();

		List<String> titleMeals = MealRepository.getWeekResponse().get(0).getWeek()
				.getTuesday().getMeals().stream().map(s -> s.getTitle()).toList();
		return titleMeals;
	}

	@GetMapping("mealplanner/tuesday/nutrients")
	public Nutrients getSpecific2() throws IOException, InterruptedException {
		jsonParser.callApiExample2();

		Nutrients nutrients1 = MealRepository.getWeekResponse().get(0).getWeek().getTuesday().getNutrients();
		return nutrients1;
	}


	@GetMapping("")
	public List<WeekResponse> mainResponse() throws IOException, InterruptedException {
		jsonParser.callApiExample2();

	return mealRepository.getWeekResponse();
	}


	@GetMapping("mealplanner/test")
	public ResponseEntity<DayResponse> getMonday() throws IOException, InterruptedException {

		return getDayMeals("2000", "vegetarian", "gluten");
	}

	@RequestMapping("/${url.mealplanner.week}")
	@SuppressWarnings("unchecked")
	@GetMapping()
	public ResponseEntity<WeekResponse> getWeekMeals(String numCalories, String diet, String exclusions) {
		return (ResponseEntity<WeekResponse>) spoonacularResponse
				.getspoonacularResponse(numCalories, diet, exclusions, "week", WeekResponse.class);
	}

	@RequestMapping("/${url.mealplanner.day}")
	@SuppressWarnings("unchecked")
	@GetMapping()
	public ResponseEntity<DayResponse> getDayMeals(String numCalories, String diet, String exclusions) {
		return (ResponseEntity<DayResponse>) spoonacularResponse
				.getspoonacularResponse(numCalories, diet, exclusions, "day", DayResponse.class);
	}


}
