package com.leeseo.lecturesite.controller;

import com.leeseo.lecturesite.dto.TutorRequestDto;
import com.leeseo.lecturesite.dto.TutorResponseDto;
import com.leeseo.lecturesite.repository.TutorRepository;
import com.leeseo.lecturesite.service.TutorService;
import org.springframework.web.bind.annotation.*;

@RestController
public class TutorController {
    private final TutorService tutorService;
    private final TutorRepository tutorRepository;


    public TutorController(TutorService tutorService, TutorRepository tutorRepository) {
        this.tutorService = tutorService;
        this.tutorRepository = tutorRepository;

    }
    @PostMapping("/tutors")
    public void createTutor(@RequestBody TutorRequestDto requestDto) {
        tutorService.createTutors(requestDto);
    }
    @GetMapping("/tutors/{id}")
    public TutorResponseDto getTutorById(@PathVariable Long id) {
        return tutorService.getTutorById(id);
    }
}
