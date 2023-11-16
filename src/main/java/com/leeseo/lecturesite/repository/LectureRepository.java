package com.leeseo.lecturesite.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import com.leeseo.lecturesite.entity.Lecture;

public interface LectureRepository extends JpaRepository<Lecture, Long> {
	List<Lecture> findByCategory(String category, Sort sort);

	Optional<Lecture> findByIdAndTutor_Id(Long lectureId, Long tutorId);
}
