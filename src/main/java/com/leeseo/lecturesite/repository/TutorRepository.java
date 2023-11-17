package com.leeseo.lecturesite.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.leeseo.lecturesite.entity.Tutor;

public interface TutorRepository extends JpaRepository<Tutor, Long> {
	Optional<Tutor> findByid(Long tutorId);
}
