package com.leeseo.lecturesite.entity;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.leeseo.lecturesite.dto.LectureRequestDto;

import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Table(name = "lecture")
//@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor
public class Lecture extends TimeStamped {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	private Integer price;
	private String comment;
	private String category;

	// JoinColumn이 없길래 추가했습니다. 근데 없어도 왜 되지?
	@ManyToOne
	@JoinColumn(name = "tutor_id")
	private Tutor tutor;

	@OneToMany(mappedBy = "lecture")
	private List<Likey> likes = new ArrayList<>();

	public Lecture(LectureRequestDto requestDto) {
		this.name = requestDto.getName();
		this.price = requestDto.getPrice();
		this.comment = requestDto.getComment();
		this.category = requestDto.getCategory();
	}

	public Lecture(LectureRequestDto requestDto, Tutor tutor) {
		this.name = requestDto.getName();
		this.price = requestDto.getPrice();
		this.comment = requestDto.getComment();
		this.category = requestDto.getCategory();
		this.tutor = tutor;

	}
}
