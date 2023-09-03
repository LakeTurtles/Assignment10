package com.ultima.Senorial.Repository;
import com.ultima.Senorial.dto.Week;
import com.ultima.Senorial.dto.WeekResponse;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public  class MealRepository {
	private static List<WeekResponse> week = new ArrayList<>(100);


	public static List<WeekResponse> getWeekResponse() {
		return week;
	}


}
