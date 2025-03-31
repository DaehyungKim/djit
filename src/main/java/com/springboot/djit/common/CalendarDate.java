package com.springboot.djit.common;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor 
@AllArgsConstructor
public class CalendarDate {
    private int year;
    private int month;
    private int date;
    private boolean isToday;
}
