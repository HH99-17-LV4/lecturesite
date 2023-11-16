package com.leeseo.lecturesite.entity;

import com.leeseo.lecturesite.dto.LectureRequestDto;
import com.leeseo.lecturesite.dto.TutorRequestDto;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@Table(name = "tutor")
public class Tutor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private Integer career;
    private String company;
    private String phone;
    private String comment;

    @OneToMany(mappedBy = "tutor", orphanRemoval = true)
    private List<Lecture> lectureList = new ArrayList<>();

    public Tutor(TutorRequestDto requestDto) {
        this.name = requestDto.getName();
        this.career = requestDto.getCareer();
        this.comment = requestDto.getComment();
        this.company = requestDto.getCompany();
        this.phone = requestDto.getPhone();
    }
    public List<Lecture> getLectures() {
        return lectureList;
    }

}
