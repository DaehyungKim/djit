package com.springboot.djit.controller;


import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import com.springboot.djit.common.CalendarDate;
import com.springboot.djit.common.DateData;
import org.springframework.ui.Model;


@Controller

public class DjitController {
	
	private final Logger LOGGER = LoggerFactory.getLogger(DjitController.class);
	
	  @GetMapping("/pages/*")
	    public String main(HttpServletRequest request) {
	        return null;
	    }
	  
	  
	  @GetMapping("/calendar")
	    public String showCalendar(HttpServletRequest request,
	    		@RequestParam(name = "year", required = false) Integer year,
	    	    @RequestParam(name = "month", required = false) Integer month,
	            Model model) {
		  String viewName = (String) request.getAttribute("viewName");
		  LOGGER.info(viewName + "이거 확인하고 인터셉터 지운다");
	        // 현재 날짜 정보
	        LocalDate today = LocalDate.now();
	        
	        // 파라미터가 없으면 현재 년월 사용
	        if (year == null) year = today.getYear();
	        if (month == null) month = today.getMonthValue();
	        
	        // 선택된 년월의 YearMonth 객체 생성
	        YearMonth yearMonth = YearMonth.of(year, month);
	        
	        // 이전달, 다음달 계산
	        YearMonth prevMonth = yearMonth.minusMonths(1);
	        YearMonth nextMonth = yearMonth.plusMonths(1);
	        
	        // 캘린더 데이터 생성
	        List<List<CalendarDay>> calendar = generateCalendar(yearMonth, today);
	        
	        // 모델에 데이터 추가
	        model.addAttribute("currentYear", today.getYear());
	        model.addAttribute("selectedYear", year);
	        model.addAttribute("selectedMonth", month);
	        model.addAttribute("prevYear", prevMonth.getYear());
	        model.addAttribute("prevMonth", prevMonth.getMonthValue());
	        model.addAttribute("nextYear", nextMonth.getYear());
	        model.addAttribute("nextMonth", nextMonth.getMonthValue());
	        model.addAttribute("calendar", calendar);
	        
	        return "calendar";
	    }
	    
	    @GetMapping("/day-detail")
	    public String showDayDetail(
	    		@RequestParam(name = "year") Integer year,
	            @RequestParam(name = "month") Integer month,
	            @RequestParam(name = "day") Integer day,
	            Model model) {
	        
	        // 선택된 날짜의 상세 정보 조회 로직
	        LocalDate selectedDate = LocalDate.of(year, month, day);
	        
	        model.addAttribute("date", selectedDate);
	        // 여기에 해당 날짜의 예약 정보 등을 추가로 모델에 넣을 수 있음
	        
	        return "day-detail";
	    }
	    
	    private List<List<CalendarDay>> generateCalendar(YearMonth yearMonth, LocalDate today) {
	        List<List<CalendarDay>> calendar = new ArrayList<>();
	        
	        // 해당 월의 첫날
	        LocalDate firstDay = yearMonth.atDay(1);
	        
	        // 첫날의 요일 (1: 월요일, 7: 일요일)
	        DayOfWeek dayOfWeek = firstDay.getDayOfWeek();
	        int value = dayOfWeek.getValue();
	        
	        // 일요일이 첫번째 칸이 되도록 조정 (일요일=0, 월요일=1, ...)
	        int dayOfWeekValue = value % 7;
	        
	        // 이전 달의 마지막 날짜들을 추가
	        List<CalendarDay> week = new ArrayList<>();
	        YearMonth prevMonth = yearMonth.minusMonths(1);
	        int prevMonthLength = prevMonth.lengthOfMonth();
	        
	        // 첫 주의 이전 달 날짜 채우기
	        for (int i = 0; i < dayOfWeekValue; i++) {
	            int day = prevMonthLength - dayOfWeekValue + i + 1;
	            week.add(new CalendarDay(day, false, false, i));
	        }
	        
	        // 현재 달의 날짜 채우기
	        int daysInMonth = yearMonth.lengthOfMonth();
	        for (int i = 1; i <= daysInMonth; i++) {
	            LocalDate date = yearMonth.atDay(i);
	            boolean isToday = date.equals(today);
	            int dayOfWeekVal = date.getDayOfWeek().getValue() % 7;
	            
	            week.add(new CalendarDay(i, true, isToday, dayOfWeekVal));
	            
	            // 토요일이거나 마지막 날이면 새로운 주로
	            if (dayOfWeekVal == 6 || i == daysInMonth) {
	                calendar.add(week);
	                week = new ArrayList<>();
	            }
	        }
	        
	        // 마지막 주의 다음 달 날짜 채우기
	        if (!week.isEmpty()) {
	            int count = 7 - week.size();
	            for (int i = 1; i <= count; i++) {
	                int dayOfWeekVal = (dayOfWeekValue + daysInMonth + i - 1) % 7;
	                week.add(new CalendarDay(i, false, false, dayOfWeekVal));
	            }
	            calendar.add(week);
	        }
	        
	        return calendar;
	    }
	    
	    // 캘린더 날짜 표현을 위한 내부 클래스
	    public static class CalendarDay {
	        private int day;
	        private boolean currentMonth;
	        private boolean today;
	        private int dayOfWeek;  // 0: 일요일, 6: 토요일
	        
	        public CalendarDay(int day, boolean currentMonth, boolean today, int dayOfWeek) {
	            this.day = day;
	            this.currentMonth = currentMonth;
	            this.today = today;
	            this.dayOfWeek = dayOfWeek;
	        }
	        
	        public int getDay() {
	            return day;
	        }
	        
	        public boolean isCurrentMonth() {
	            return currentMonth;
	        }
	        
	        public boolean isToday() {
	            return today;
	        }
	        
	        public int getDayOfWeek() {
	            return dayOfWeek;
	        }
	    }

}
