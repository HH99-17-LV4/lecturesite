package com.leeseo.lecturesite.entity;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.leeseo.lecturesite.dto.LectureRequestDto;

import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "lecture")
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor
public class Lecture extends TimeStamped {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	private Integer price;
	private String comment;
	private String category;
	@ManyToOne
	private Tutor tutor;

	public Lecture(LectureRequestDto requestDto) {
		this.name = requestDto.getName();
		this.price = requestDto.getPrice();
		this.comment = requestDto.getComment();
		this.category = requestDto.getCategory();

	}

}
