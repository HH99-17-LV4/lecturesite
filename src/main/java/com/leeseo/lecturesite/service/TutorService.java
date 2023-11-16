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
    public void createTutors(TutorRequestDto requestDto) {
        Tutor tutor = new Tutor(requestDto);
        tutorRepository.save(tutor);
    }

    public TutorResponseDto getTutorById(Long id) {
        Tutor tutor = findTutor(id);
        return new TutorResponseDto(tutor);
    }
    private Tutor findTutor(Long id) {
        return tutorRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("선택한 강의는 없습니다")
        );
    }
}
