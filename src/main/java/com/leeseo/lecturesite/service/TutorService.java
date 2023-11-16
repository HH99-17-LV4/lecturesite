package com.leeseo.lecturesite.service;

import com.leeseo.lecturesite.dto.TutorRequestDto;
import com.leeseo.lecturesite.dto.TutorResponseDto;
import com.leeseo.lecturesite.entity.Tutor;
import com.leeseo.lecturesite.repository.TutorRepository;
import org.springframework.stereotype.Service;

@Service
public class TutorService {
    private final TutorRepository tutorRepository;

    public TutorService(TutorRepository tutorRepository) {
        this.tutorRepository = tutorRepository;
    }
    public TutorResponseDto createTutors(TutorRequestDto registTutorRequestDto) {
        Tutor tutor = new Tutor(registTutorRequestDto);
        return new TutorResponseDto(tutorRepository.save(tutor));
    }

    public TutorResponseDto getTutors(Long id) {
        Tutor tutor = tutorRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("등록되지 않은 사용자입니다.")
        );
        return new TutorResponseDto(tutor);
    }
}
