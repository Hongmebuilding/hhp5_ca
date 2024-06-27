package com.lec.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.lec.exception.CustomException;
import com.lec.model.domain.LectureApplication;
import com.lec.model.vo.ErrorCode;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Getter
@Builder
@Entity
@Table(name = "LectureApplication")
@EntityListeners(AuditingEntityListener.class)
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class LectureApplicationEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties("lectureApplications")
    @JoinColumn(name = "lecture_id")
    private LectureEntity lecture;

    private LocalDateTime createdAt;


    public LectureApplication to() {
        LectureApplication application = new LectureApplication();
        application.setId(this.id);
        application.setUserId(this.userId);
        application.setLectureId(this.lecture != null ? this.lecture.getId() : null);
        application.setCreatedAt(this.createdAt);
        return application;
    }

    @PrePersist
    public void initialize() {
        if (userId == null || lecture == null) {
            throw new CustomException(HttpStatus.NO_CONTENT, ErrorCode.Illegal_ARGUMENT);
        }
        createdAt = LocalDateTime.now();
    }
}