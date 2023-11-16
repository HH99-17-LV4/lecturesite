package com.leeseo.lecturesite.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.leeseo.lecturesite.dto.comment.CommentRequestDto;
import com.leeseo.lecturesite.security.UserDetailsImpl;
import com.leeseo.lecturesite.service.CommentService;

import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor
@RestController
public class CommentController {

	private final CommentService commentService;
	@PostMapping("/lectures/{lectureId}/comment")
	public ResponseEntity<String> create(
		@PathVariable Long lectureId,
		@RequestBody CommentRequestDto req,
		@AuthenticationPrincipal UserDetailsImpl userDetails){
		try{
			commentService.create(lectureId, req, userDetails.getUser());
			return new ResponseEntity<>("댓글 등록 성공", HttpStatus.OK);
		} finally {

		}
	}
	@PostMapping("/lectures/{lectureId}/comment/{commentId}")
	public ResponseEntity<String> modify(
		@PathVariable Long lectureId,
		@PathVariable Long commentId,
		@RequestBody CommentRequestDto req,
		@AuthenticationPrincipal UserDetailsImpl userDetails){
		try{
			commentService.modify(lectureId, commentId, req, userDetails.getUser());
			return new ResponseEntity<>("댓글 수정 성공", HttpStatus.OK);
		} finally {

		}
	}
	@DeleteMapping("/lectures/{lectureId}/comment/{commentId}")
	public ResponseEntity<String> delete(
		@PathVariable Long lectureId,
		@PathVariable Long commentId,
		@AuthenticationPrincipal UserDetailsImpl userDetails){
		try{
			commentService.delete(lectureId, commentId, userDetails.getUser());
			return new ResponseEntity<>("댓글 삭제 성공", HttpStatus.OK);
		} finally {

		}
	}
}
