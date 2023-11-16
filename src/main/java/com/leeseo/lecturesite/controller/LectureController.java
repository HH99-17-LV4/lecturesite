package com.leeseo.lecturesite.controller;

import com.leeseo.lecturesite.dto.LectureRequestDto;
import com.leeseo.lecturesite.dto.LectureResponseDto;
import com.leeseo.lecturesite.entity.Lecture;
import com.leeseo.lecturesite.repository.LectureRepository;
import com.leeseo.lecturesite.service.LectureService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.nio.charset.StandardCharsets;
import java.util.List;

@RestController
public class LectureController {
    private final LectureService lectureService;
    public LectureController(LectureService lectureService) {
        this.lectureService = lectureService;
    }
    @PostMapping("/lectures")
    public ResponseEntity<String> createLecture(@RequestBody LectureRequestDto requestDto) {
        String successMessage = lectureService.createLecture(requestDto);
        return ResponseEntity.status(HttpStatus.CREATED)
                .header(
                        HttpHeaders.CONTENT_TYPE, MediaType.TEXT_PLAIN_VALUE + ";charset=" + StandardCharsets.UTF_8)
                .body(successMessage);
    }

    @GetMapping("/lectures")
    public List<Lecture> getAllLectures() {
        return lectureService.getAllLectures();
    }
    @GetMapping("/lectures/{id}")
    public LectureResponseDto getLectureById(@PathVariable Long id) {
        return lectureService.getLectureById(id);
    }
    @GetMapping("/lectures/category")
    public List<LectureResponseDto> getLecturesByCategory(
            @RequestParam String category,
            @RequestParam(defaultValue = "lectureName") String sortBy,
            @RequestParam(defaultValue = "desc") String sortOrder) {
        return lectureService.getLecturesByCategory(category, sortBy, sortOrder);
    }
}

