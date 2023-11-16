package com.leeseo.lecturesite.entity;

import com.leeseo.lecturesite.dto.LectureRequestDto;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "lecture")
@EntityListeners(AuditingEntityListener.class)
@NoArgsConstructor
public class Lecture extends TimeStamped {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private Integer price;
    private String  comment;
    private String category;
    @ManyToOne
    private Tutor tutor;
    private int likes;

    public Lecture(LectureRequestDto requestDto) {
        this.name = requestDto.getName();
        this.price = requestDto.getPrice();
        this.comment = requestDto.getComment();
        this.category = requestDto.getCategory();

    }

}
