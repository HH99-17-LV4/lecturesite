package com.leeseo.lecturesite.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.leeseo.lecturesite.dto.comment.CommentRequestDto;
import com.leeseo.lecturesite.dto.comment.CommentResponseDto;
import com.leeseo.lecturesite.entity.Comment;
import com.leeseo.lecturesite.entity.Lecture;
import com.leeseo.lecturesite.entity.User;
import com.leeseo.lecturesite.repository.CommentRepository;
import com.leeseo.lecturesite.repository.LectureRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CommentService {
	private final CommentRepository commentRepository;
	private final LectureRepository lectureRepository;

	public void createReply(Long lectureId, CommentRequestDto req, User user) {
		Lecture lecture = lectureRepository.findById(lectureId)
			.orElseThrow(() -> new RuntimeException("해당 강의가 존재하지 않습니다."));
		Comment comment = new Comment(lecture, req, user);
		commentRepository.save(comment);
	}

	public void createReply(Long lectureId, Long commentId, CommentRequestDto req, User user) {
		Comment foundComment = commentRepository.findById(commentId)
			.orElseThrow(() -> new RuntimeException("해당 댓글이 존재하지 않습니다."));
		Lecture foundLecture = lectureRepository.findById(lectureId)
			.orElseThrow(() -> new RuntimeException("해당 강의가 존재하지 않습니다."));

		// foundComment가 부모가 되어야하고, 혅 ㅐcomment가 부모를 갖고잇어야한다.

		Comment newComment = new Comment(foundLecture, req, user);
		newComment.updateParent(foundComment);
		commentRepository.save(newComment);
	}

	@Transactional
	public void modify(Long lectureId, Long commentId, CommentRequestDto req, User user) {
		Comment comment = commentRepository.findById(commentId)
			.orElseThrow(() -> new RuntimeException("해당 댓글이 존재하지 않습니다."));
		// 본인이 작성했던 댓글이 아니라면
		if (!Objects.equals(comment.getUser().getId(), user.getId())) {
			throw new RuntimeException("댓글 작성자만 수정할 수 잇습ㄴ디ㅏ.");
		}
		comment.update(req);

	}

	public void delete(Long lectureId, Long commentId, User user) {
		Comment comment = commentRepository.findById(commentId)
			.orElseThrow(() -> new RuntimeException("해당 댓글이 존재하지 않습니다."));
		// 본인이 작성했던 댓글이 아니라면
		if (!Objects.equals(comment.getUser().getId(), user.getId())) {
			throw new RuntimeException("댓글 작성자만 삭제할 수 잇습ㄴ디ㅏ.");
		}
		commentRepository.delete(comment);
	}

	public List<CommentResponseDto> getCommentList(Long lectureId) {
		List<Comment> findCommentList = commentRepository.findCommentByLectureId(lectureId);
		List<CommentResponseDto> result = new ArrayList<>();

		Map<Long, CommentResponseDto> commentMap = new HashMap<>();

		findCommentList.stream().map(CommentResponseDto::new).forEach(child -> {
			commentMap.put(child.getId(), child);
			if (child.getParentId() > 0) { // 부모가 있는 댓글일 경우
				commentMap.get(child.getParentId()).getChildren().add(child);
			} else { // 부모가 없는 댓글일 경우
				result.add(child);
			}
		});

		return result;
	}
}
