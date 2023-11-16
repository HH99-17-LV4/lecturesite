package com.leeseo.lecturesite.dto.comment;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.leeseo.lecturesite.entity.Comment;

import lombok.Getter;

@Getter
public class CommentResponseDto {

	private Long id;
	private String content;
	private LocalDateTime registeredAt;
	private LocalDateTime modifiedAt;
	private Long parentId;
	private List<CommentResponseDto> children = new ArrayList<>();

	public CommentResponseDto(Comment comment){
		this.id = comment.getId();
		this.content = comment.getContent();
		this.registeredAt = comment.getRegisteredAt();
		this.modifiedAt = comment.getModifiedAt();
		this.parentId = comment.getParent() != null ? comment.getParent().getId() : 0;
	}
}
