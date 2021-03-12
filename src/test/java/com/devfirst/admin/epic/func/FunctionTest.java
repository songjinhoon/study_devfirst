package com.devfirst.admin.epic.func;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import org.junit.Test;

public class FunctionTest {
	
	@Test
	public void getDateTime() throws Exception {
	}
	
	@Test
	public void 날짜() throws Exception {
		LocalTime currentTime = LocalTime.now();   
		LocalTime targetTime = LocalTime.of(12,33,35,22); 
		LocalDate now = LocalDate.now();
		LocalDate targetDate = LocalDate.of(2019,11,12);
		LocalDateTime currentDateTime = LocalDateTime.now();    
		LocalDateTime targetDateTime = LocalDateTime.of(2019, 11, 12, 12, 32,22,3333);
		System.out.println(currentDateTime);
		System.out.println(currentDateTime.minusDays(30));
		System.out.println(currentDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))); // LocalDateTime -> String
	}
}
