package com.leeseo.lecturesite.repository;

import com.leeseo.lecturesite.entity.Lecture;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface LectureRepository extends JpaRepository<Lecture, Long> {
    // 입력받은 category로 조회한다
    // select * from lecture where category = {category} [orderby ~~~`]
    List<Lecture> findByCategory(String category, Sort sort);
}
