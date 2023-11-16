package com.leeseo.lecturesite.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.LastModifiedDate;

import com.leeseo.lecturesite.dto.comment.CommentRequestDto;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
@Entity
public class Comment extends TimeStamped{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String content;

	@LastModifiedDate
	@Temporal(TemporalType.TIMESTAMP)
	private LocalDateTime modifiedAt;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "lecture_id")
	private Lecture lecture;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	private User user;

	//셀프 참조
	@ManyToOne
	@JoinColumn(name = "parent") // 연관관계의 주인, Child
	private Comment parent;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "parent", orphanRemoval = true)
	private List<Comment> children = new ArrayList<>();



	public Comment(Lecture lecture, CommentRequestDto req, User user) {
		this.content = req.getContent();
		this.lecture = lecture;
		this.user = user;
		this.parent = null;
	}

	public void updateParent(Comment parent) {
		this.parent = parent;
		parent.getChildren().add(this);
	}

	public void update(CommentRequestDto req) {
		this.content = req.getContent();
	}
}
