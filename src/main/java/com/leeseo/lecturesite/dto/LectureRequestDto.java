package com.leeseo.lecturesite.dto;

import lombok.Data;

import java.time.LocalDateTime;
@Data
public class LectureRequestDto {
    private String name;
    private Integer price;
    private String comment;
    private String category;
    private Long tutorId;
    private LocalDateTime registeredAt;
}
