package com.leeseo.lecturesite.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.leeseo.lecturesite.entity.Comment;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
