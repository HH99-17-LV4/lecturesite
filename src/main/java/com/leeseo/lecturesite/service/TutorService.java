package com.leeseo.lecturesite.service;

import org.springframework.stereotype.Service;

import com.leeseo.lecturesite.dto.TutorAdminResponseDto;
import com.leeseo.lecturesite.dto.TutorRequestDto;
import com.leeseo.lecturesite.dto.TutorResponseDto;
import com.leeseo.lecturesite.entity.Tutor;
import com.leeseo.lecturesite.repository.TutorRepository;

@Service
public class TutorService {
	private final TutorRepository tutorRepository;

	public TutorService(TutorRepository tutorRepository) {
		this.tutorRepository = tutorRepository;
	}

	public TutorAdminResponseDto createTutors(TutorRequestDto registTutorRequestDto) {
		Tutor tutor = new Tutor(registTutorRequestDto);
		return new TutorAdminResponseDto(tutorRepository.save(tutor));
	}

	public TutorResponseDto getTutors(Long id) {
		Tutor tutor = tutorRepository.findById(id).orElseThrow(() ->
			new IllegalArgumentException("등록되지 않은 사용자입니다.")
		);
		return new TutorResponseDto(tutor);
	}
}
