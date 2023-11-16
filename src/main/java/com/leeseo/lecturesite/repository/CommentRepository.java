package com.leeseo.lecturesite.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.leeseo.lecturesite.entity.Comment;

public interface CommentRepository extends JpaRepository<Comment, Long> {

	@Query("SELECT c FROM Comment c " // Comment 테이블과 
		+ "LEFT JOIN Comment parent " // Comment 테이블을 조인한다.
		+ "ON c.lecture.id = parent.lecture.id " // 같은 lecture id를 가진 Comment c 들 중
		+ "WHERE c.lecture.id = :lectureId " // 매개변수 lecture id 와 같은 댓글들을, 
		+ "ORDER BY COALESCE(parent.id, 0) ASC, c.registeredAt ASC")
	// parent가 Null인 경우 0으로 치고 오름차순, 및 같은 부모인 경우 빠른 등록순 조회
	public List<Comment> findCommentByLectureId(@Param("lectureId") Long lectureId);
}
