package com.leeseo.lecturesite.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.leeseo.lecturesite.entity.Lecture;
import com.leeseo.lecturesite.entity.Likey;
import com.leeseo.lecturesite.entity.User;

public interface LikeyRepository extends JpaRepository<Likey, Long> {

	Optional<Likey> findByLectureAndUser(Lecture foundLecture, User user);
}
