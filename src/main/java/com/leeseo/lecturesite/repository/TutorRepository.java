package com.leeseo.lecturesite.repository;

import com.leeseo.lecturesite.entity.Tutor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TutorRepository extends JpaRepository<Tutor, Long> {
    Optional<Tutor> findByid(Long tutorId);
}
