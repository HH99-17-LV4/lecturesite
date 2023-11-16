package com.leeseo.lecturesite.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.leeseo.lecturesite.dto.LectureRequestDto;
import com.leeseo.lecturesite.dto.LectureResponseDto;
import com.leeseo.lecturesite.entity.Lecture;
import com.leeseo.lecturesite.entity.Likey;
import com.leeseo.lecturesite.entity.Tutor;
import com.leeseo.lecturesite.entity.User;
import com.leeseo.lecturesite.repository.LectureRepository;
import com.leeseo.lecturesite.repository.LikeyRepository;
import com.leeseo.lecturesite.repository.TutorRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j(topic = "LectureService")
@Service
public class LectureService {

	private final LectureRepository lectureRepository;
	private final TutorRepository tutorRepository;
	private final LikeyRepository likeyRepository;

	public LectureService(LectureRepository lectureRepository, TutorRepository tutorRepository,
		LikeyRepository likeyRepository) {
		this.lectureRepository = lectureRepository;
		this.tutorRepository = tutorRepository;
		this.likeyRepository = likeyRepository;
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
		return lectureRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("선택한 강의는 없습니다"));
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
		List<Lecture> lectures;
		if ("price".equalsIgnoreCase(sortBy)) {
			//가격으로 정렬하는 경우
			lectures = lectureRepository.findByCategoryOrderByPrice(category, sort);
		} else if ("registeredAt".equalsIgnoreCase(sortBy)) {
			// 등록일로 정렬하는 경우
			lectures = lectureRepository.findByCategoryOrderByRegisteredAt(category, sort);
		} else {
			// 강의명으로 기본 정렬
			lectures = lectureRepository.findByCategory(category, sort);
		}
		return lectures.stream().map(LectureResponseDto::new).collect(Collectors.toList());
	}

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
