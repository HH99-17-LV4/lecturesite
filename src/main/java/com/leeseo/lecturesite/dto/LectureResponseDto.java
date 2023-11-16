package com.leeseo.lecturesite.dto;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.leeseo.lecturesite.dto.comment.CommentResponseDto;
import com.leeseo.lecturesite.entity.Lecture;

import lombok.Data;

@Data
public class LectureResponseDto {
	private String name;
	private Integer price;
	private String category;
	private String comment;
	private String tutorName; // 연관관계에 있는 엔티티를 직접 추가하지않도록 유의
	private LocalDateTime registeredAt;
	private Integer likes;
	private List<CommentResponseDto> commentList = new ArrayList<>();

	public LectureResponseDto(Lecture lecture) {
		this.name = lecture.getName();
		this.price = lecture.getPrice();
		this.category = lecture.getCategory();
		this.comment = lecture.getComment();
		this.tutorName = lecture.getTutor().getName();
		this.likes = lecture.getLikes().size();
		this.registeredAt = lecture.getRegisteredAt();
	}
}
