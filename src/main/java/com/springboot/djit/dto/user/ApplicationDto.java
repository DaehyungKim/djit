package com.springboot.djit.dto.user;


import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ApplicationDto {
    private String subjectName;
    private String name;
    private String email;
    private String sex;
    private String birth;
    private String phoneNumber;
    private String address;
    private String educationLevel;
    private String married;
    private String employmentInsurance;
    private String educationGoal;
    private String motivation;
}
