package com.leeseo.lecturesite.dto;

import com.leeseo.lecturesite.entity.Lecture;
import com.leeseo.lecturesite.entity.Tutor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class LectureResponseDto {
    private String name;
    private Integer price;
    private String category;
    private String comment;
    private Tutor tutor;
    private LocalDateTime registeredAt;
    private int likes;

    public LectureResponseDto(Lecture lecture) {
        this.name = lecture.getName();
        this.price = lecture.getPrice();
        this.category = lecture.getCategory();
        this.comment = lecture.getComment();
        this.likes = lecture.getLikes();
        this.registeredAt = lecture.getRegisteredAt();
    }
}
