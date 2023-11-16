package com.leeseo.lecturesite.controller;

import java.nio.charset.StandardCharsets;
import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.leeseo.lecturesite.dto.LectureRequestDto;
import com.leeseo.lecturesite.dto.LectureResponseDto;
import com.leeseo.lecturesite.entity.Lecture;
import com.leeseo.lecturesite.security.UserDetailsImpl;
import com.leeseo.lecturesite.service.LectureService;

@RestController
public class LectureController {
	private final LectureService lectureService;

	public LectureController(LectureService lectureService) {
		this.lectureService = lectureService;
	}

	@PostMapping("/admin/lectures")
	public ResponseEntity<String> createLecture(@RequestBody LectureRequestDto requestDto) {
		String successMessage = lectureService.createLecture(requestDto);
		return ResponseEntity.status(HttpStatus.CREATED)
			.header(HttpHeaders.CONTENT_TYPE, MediaType.TEXT_PLAIN_VALUE + ";charset=" + StandardCharsets.UTF_8)
			.body(successMessage);
	}

	@GetMapping("/lectures")
	public List<LectureResponseDto> getAllLectures() {
		return lectureService.getAllLectures();
	}

	@GetMapping("/lectures/{id}")
	public LectureResponseDto getLectureById(@PathVariable Long id) {
		return lectureService.getLectureById(id);
	}

	@GetMapping("/lectures/category")
	public List<LectureResponseDto> getLecturesByCategory(@RequestParam String category,
		@RequestParam(defaultValue = "lectureName") String sortBy,
		@RequestParam(defaultValue = "desc") String sortOrder) {
		return lectureService.getLecturesByCategory(category, sortBy, sortOrder);
	}

	@GetMapping("/lectures/{id}/toggle-like")
	public ResponseEntity<String> toggleLikeLecture(@PathVariable Long id,
		@AuthenticationPrincipal UserDetailsImpl userDetail) {

		boolean alreadyLiked = lectureService.likes(id, userDetail.getUser());

		if (alreadyLiked) {
			return ResponseEntity.ok("이미 한 좋아요 취소");
		}
		return ResponseEntity.ok("좋아요 성공");

	}
}
