package com.leeseo.lecturesite.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.leeseo.lecturesite.dto.LectureDetailResponseDto;
import com.leeseo.lecturesite.dto.LectureRequestDto;
import com.leeseo.lecturesite.dto.LectureResponseDto;
import com.leeseo.lecturesite.dto.comment.CommentResponseDto;
import com.leeseo.lecturesite.entity.Lecture;
import com.leeseo.lecturesite.entity.Likey;
import com.leeseo.lecturesite.entity.Tutor;
import com.leeseo.lecturesite.entity.User;
import com.leeseo.lecturesite.repository.LectureRepository;
import com.leeseo.lecturesite.repository.LikeyRepository;
import com.leeseo.lecturesite.repository.TutorRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j(topic = "LectureService")
@Service
@RequiredArgsConstructor
public class LectureService {

	private final LectureRepository lectureRepository;
	private final TutorRepository tutorRepository;
	private final LikeyRepository likeyRepository;
	private final CommentService commentService;

	public String createLecture(LectureRequestDto requestDto) {
		Tutor tutor = findById(requestDto.getTutorId());
		Lecture lecture = new Lecture(requestDto, tutor);

		lectureRepository.save(lecture);

		return "강의가 등록되었습니다.";
	}

	//Lecture 를 반환하면 순환 참조가 발생합니다.
	public List<LectureResponseDto> getAllLectures() {
		return lectureRepository.findAll().stream().map(LectureResponseDto::new).collect(Collectors.toList());
	}

	public LectureDetailResponseDto getLectureById(Long id) {
		Lecture lecture = findLecture(id);
		List<CommentResponseDto> commentList = commentService.getCommentList(lecture.getId());
		return new LectureDetailResponseDto(lecture, commentList);
	}

	private Lecture findLecture(Long id) {
		return lectureRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("선택한 강의는 없습니다"));
	}

	@Transactional
	public List<LectureResponseDto> getLecturesByCategory(String category, String sortBy, String sortOrder) {
		Sort.Direction direction = "asc".equalsIgnoreCase(sortOrder) ? Sort.Direction.ASC : Sort.Direction.DESC;
		Sort sort = Sort.by(direction, sortBy);
		List<Lecture> lectures = lectureRepository.findByCategory(category, sort);
		return lectures.stream().map(LectureResponseDto::new).collect(Collectors.toList());
	}
//	if ("price".equalsIgnoreCase(sortBy)) {
//		lectures = lectureRepository.findByCategoryOrderByPrice(category, sort);
//	} else if ("registeredAt".equalsIgnoreCase(sortBy)) {
//		lectures = lectureRepository.findByCategoryOrderByRegisteredAt(category, sort);
//	} else {
//		lectures = lectureRepository.findByCategory(category, sort);
//	}

	private Tutor findById(Long tutorId) {
		return tutorRepository.findByid(tutorId).orElseThrow(() -> new IllegalArgumentException("존재하지 않는 강사입니다."));
	}

	public boolean likes(Long id, User user) {
		Lecture foundLecture = lectureRepository.findById(id)
			.orElseThrow(() -> new RuntimeException(" 해당 강의가 존재하지 않습니다."));

		Optional<Likey> likey = likeyRepository.findByLectureAndUser(foundLecture, user);

		if (likey.isPresent()) {
			log.info("이미 좋아요 함");
			likeyRepository.delete(likey.get());
			return true;
		}
		log.info("좋아요 안한 상태에서 좋아요 함");
		likeyRepository.save(new Likey(foundLecture, user));
		return false;
	}
}
