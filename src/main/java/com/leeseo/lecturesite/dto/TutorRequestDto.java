package com.leeseo.lecturesite.dto;

import lombok.Data;

@Data
public class TutorRequestDto {
    private String name;
    private Integer career;
    private String company;
    private String phone;
    private String comment;
}
