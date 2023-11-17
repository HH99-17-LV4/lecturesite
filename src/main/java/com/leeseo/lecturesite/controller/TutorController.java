package com.leeseo.lecturesite.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.leeseo.lecturesite.dto.TutorAdminResponseDto;
import com.leeseo.lecturesite.dto.TutorRequestDto;
import com.leeseo.lecturesite.dto.TutorResponseDto;
import com.leeseo.lecturesite.repository.TutorRepository;
import com.leeseo.lecturesite.service.TutorService;

@RestController
public class TutorController {
	private final TutorService tutorService;

	public TutorController(TutorService tutorService, TutorRepository tutorRepository) {
		this.tutorService = tutorService;
	}

	@PostMapping("/admin/tutors")
	public ResponseEntity<TutorAdminResponseDto> createTutors(@RequestBody TutorRequestDto RequestDto) {
		return new ResponseEntity<>(tutorService.createTutors(RequestDto), HttpStatus.OK);
	}

	@GetMapping("/tutors/{id}")
	public ResponseEntity<TutorResponseDto> getTutors(@PathVariable Long id) {
		return new ResponseEntity<>(tutorService.getTutors(id), HttpStatus.OK);
	}

}
