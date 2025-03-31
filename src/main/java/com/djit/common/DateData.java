package com.djit.common;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DateData {
    private int year;
    private int month;
    private int date;
    private int value;
    private String month_title;
    
    // 추가 필드
    private int search_year;
    private int search_month;
    private int before_year;
    private int before_month;
    private int after_year;
    private int after_month;
    
    // 기본 생성자
    public DateData() {
    }
    
    // 사용 중인 생성자
    public DateData(int year, int month, int date, int value) {
        this.year = year;
        this.month = month;
        this.date = date;
        this.value = value;
        this.month_title = year + "년 " + month + "월";
    }
}