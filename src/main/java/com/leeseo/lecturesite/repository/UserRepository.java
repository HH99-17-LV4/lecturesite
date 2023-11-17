package com.leeseo.lecturesite.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.leeseo.lecturesite.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
	Optional<User> findByUsername(String username);
}