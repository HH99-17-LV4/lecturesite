package com.leeseo.lecturesite.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.leeseo.lecturesite.dto.comment.CommentRequestDto;
import com.leeseo.lecturesite.security.UserDetailsImpl;
import com.leeseo.lecturesite.service.LikeyService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class LikeyController {

	// private final LikeyService likeyService;
	//
	// @PostMapping("/lectures/{lectureId}/like")
	// public ResponseEntity<String> create(
	// 	@PathVariable Long lectureId,
	// 	@AuthenticationPrincipal UserDetailsImpl userDetails) {
	// 	try {
	// 		likeyService.create(lectureId, req, userDetails.getUser());
	// 		return new ResponseEntity<>("댓글 등록 성공", HttpStatus.OK);
	// 	} finally {
	//
	// 	}
	// }
}
