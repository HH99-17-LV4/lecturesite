package com.leeseo.lecturesite.service;

import com.leeseo.lecturesite.dto.LectureRequestDto;
import com.leeseo.lecturesite.dto.LectureResponseDto;
import com.leeseo.lecturesite.entity.Lecture;
import com.leeseo.lecturesite.entity.Tutor;
import com.leeseo.lecturesite.repository.LectureRepository;
import com.leeseo.lecturesite.repository.TutorRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class LectureService {

    private final LectureRepository lectureRepository;
    private final TutorRepository tutorRepository;

    public LectureService(LectureRepository lectureRepository, TutorRepository tutorRepository) {
        this.lectureRepository = lectureRepository;
        this.tutorRepository = tutorRepository;
    }

    public String createLecture(LectureRequestDto requestDto) {
        Lecture lecture = new Lecture();
        Tutor tutor = findById(requestDto.getTutorId());
        lecture.setName(requestDto.getName());
        lecture.setPrice(requestDto.getPrice());
        lecture.setComment(requestDto.getComment());
        lecture.setCategory(requestDto.getCategory());
        lecture.setTutor(tutor);

        lectureRepository.save(lecture);

        return "강의가 등록되었습니다.";
    }
    public List<Lecture> getAllLectures() {
        return lectureRepository.findAll();
    }

    public LectureResponseDto getLectureById(Long id) {
        Lecture lecture = findLecture(id);
        return new LectureResponseDto(lecture);
    }

    private Lecture findLecture(Long id) {
        return lectureRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("선택한 강의는 없습니다")
        );
    }
    @Transactional
    public List<LectureResponseDto> getLecturesByCategory(String category, String sortBy, String sortOrder) {
        System.out.println(category);
        Sort sort = Sort.by(sortBy);
        if ("asc".equalsIgnoreCase(sortOrder)) {
            sort = sort.ascending();
        } else {
            sort = sort.descending();
        }
        List<Lecture> lectures = lectureRepository.findByCategory(category, sort);
        return lectures.stream()
                .map(LectureResponseDto::new)
                .collect(Collectors.toList());
    }
    private Tutor findById(Long tutorId) {
        return tutorRepository.findByid(tutorId).orElseThrow(() ->
                new IllegalArgumentException("존재하지 않는 강사입니다.")
        );
    }

    public void likeLecture(Long id) {
        Lecture lecture = findLecture(id);
        lecture.setLikes(lecture.getLikes() + 1);
        lectureRepository.save(lecture);
    }
    public void unlikeLecture(Long id) {
        Lecture lecture = findLecture(id);
        int likes = lecture.getLikes();
        if (likes > 0) {
            lecture.setLikes(likes - 1);
            lectureRepository.save(lecture);
        }
    }
    public boolean toggleLikeLecture(Long id) {
        Lecture lecture = findLecture(id);
        int likes = lecture.getLikes();
        if (likes > 0) {
            lecture.setLikes(0);
            // 좋아요를 이미 한 상태에서 다시 누르면 좋아요 취소
            lectureRepository.save(lecture);
            return false; // 좋아요 취소
        } else {
            lecture.setLikes(1);
            lectureRepository.save(lecture);
            return true; // 좋아요
        }
    }
}
