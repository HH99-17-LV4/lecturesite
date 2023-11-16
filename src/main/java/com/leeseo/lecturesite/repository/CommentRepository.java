package com.leeseo.lecturesite.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.leeseo.lecturesite.entity.Comment;
import com.leeseo.lecturesite.entity.Lecture;

public interface CommentRepository extends JpaRepository<Comment, Long> {

	@Query("SELECT c FROM Comment c "
		+ "LEFT JOIN Comment parent "
		+ "ON c.lecture.id = parent.lecture.id "
		+ "WHERE c.lecture.id = :lectureId "
		+ "ORDER BY COALESCE(parent.id, 0) ASC, c.registeredAt ASC")
	public List<Comment> findCommentByLectureId(@Param("lectureId") Long lectureId);
}
