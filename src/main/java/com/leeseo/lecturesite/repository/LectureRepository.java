package com.leeseo.lecturesite.repository;

import com.leeseo.lecturesite.entity.Lecture;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface LectureRepository extends JpaRepository<Lecture, Long> {
    List<Lecture> findByCategory(String category, Sort sort);

    Optional<Lecture> findByIdAndTutor_Id(Long lectureId, Long tutorId);
}
