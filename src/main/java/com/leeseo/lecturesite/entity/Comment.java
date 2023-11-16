package com.leeseo.lecturesite.entity;

import com.leeseo.lecturesite.dto.comment.CommentRequestDto;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
@Entity
public class Comment {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String content;
	// TODO: 2023-11-16 lectrue와 관계 맺기

	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;
	@ManyToOne
	@JoinColumn(name = "parent_id")
	private Comment parent;

	public Comment(Long lectureId, CommentRequestDto req, User user) {
		this.content = req.getContent();
		this.user = user;
		this.parent = null;
	}

	public void update(CommentRequestDto req) {
		this.content = req.getContent();
	}
}
