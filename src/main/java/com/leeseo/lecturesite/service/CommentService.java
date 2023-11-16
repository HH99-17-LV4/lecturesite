package com.leeseo.lecturesite.service;

import java.util.Objects;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.leeseo.lecturesite.dto.comment.CommentRequestDto;
import com.leeseo.lecturesite.entity.Comment;
import com.leeseo.lecturesite.entity.User;
import com.leeseo.lecturesite.repository.CommentRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CommentService {
	private final CommentRepository commentRepository;
	public void create(Long lectureId, CommentRequestDto req, User user) {
		Comment comment = new Comment(lectureId, req, user);
		commentRepository.save(comment);
	}
	public void create(Long lectureId, Long commentId, CommentRequestDto req, User user) {
		Comment comment = new Comment(lectureId, req, user);
		commentRepository.save(comment);
	}

	@Transactional
	public void modify(Long lectureId, Long commentId, CommentRequestDto req, User user) {
		Comment comment = commentRepository.findById(commentId).orElseThrow(()-> new RuntimeException("해당 댓글이 존재하지 않습니다."));
		// 본인이 작성했던 댓글이 아니라면
		if(!Objects.equals(comment.getUser().getId(), user.getId())){
			throw new RuntimeException("댓글 작성자만 수정할 수 잇습ㄴ디ㅏ.");
		}
		comment.update(req);
	}

	public void delete(Long lectureId, Long commentId, User user) {
		Comment comment = commentRepository.findById(commentId).orElseThrow(()-> new RuntimeException("해당 댓글이 존재하지 않습니다."));
		// 본인이 작성했던 댓글이 아니라면
		if(!Objects.equals(comment.getUser().getId(), user.getId())){
			throw new RuntimeException("댓글 작성자만 삭제할 수 잇습ㄴ디ㅏ.");
		}
		commentRepository.delete(comment);
	}
}
