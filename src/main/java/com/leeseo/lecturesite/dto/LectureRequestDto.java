package com.leeseo.lecturesite.dto;

import lombok.Data;

@Data
public class LectureRequestDto {
	private String name;
	private Integer price;
	private String comment;
	private String category;
	private Long tutorId;
}
