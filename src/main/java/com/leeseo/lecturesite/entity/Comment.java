package com.leeseo.lecturesite.entity;

import java.util.ArrayList;
import java.util.List;

import com.leeseo.lecturesite.dto.comment.CommentRequestDto;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
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

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	private User user;

	//셀프 참조
	@ManyToOne
	@JoinColumn(name = "parent_id") // 연관관계의 주인, Child
	private Comment parent;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "parent_id", orphanRemoval = true)
	private List<Comment> children = new ArrayList<>();


	public Comment(Long lectureId, CommentRequestDto req, User user) {
		this.content = req.getContent();
		this.user = user;
		this.parent = null;
	}

	public void update(CommentRequestDto req) {
		this.content = req.getContent();
	}
}
