package com.leeseo.lecturesite.dto;

import com.leeseo.lecturesite.entity.Tutor;

import lombok.Data;

@Data
public class TutorResponseDto {
	private String name;
	private Integer career;
	private String company;
	private String phone;
	private String comment;

	public TutorResponseDto(Tutor tutor) {
		this.name = tutor.getName();
		this.career = tutor.getCareer();
		this.company = tutor.getCompany();
		this.comment = tutor.getComment();
	}
}
