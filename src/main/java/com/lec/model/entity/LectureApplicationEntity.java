package com.lec.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.lec.model.domain.LectureApplication;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

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

    @NotNull
    private Long userId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnoreProperties("lectureApplications")
    @JoinColumn(name = "lecture_id")
    private LectureEntity lecture;

    private LocalDateTime createdAt;

    public static LectureApplicationEntity from(LectureApplication application) {
        LectureApplicationEntity entity = LectureApplicationEntity.builder()
                .id(application.getId())
                .userId(application.getUserId())
                .build();
        // lecture 설정은 별도로 해야 함
        return entity;
    }

    public LectureApplication to() {
        LectureApplication application = new LectureApplication();
        application.setId(this.id);
        application.setUserId(this.userId);
        application.setLectureId(this.lecture != null ? this.lecture.getId() : null);
        application.setCreatedAt(this.createdAt);
        return application;
    }

    public LectureApplication of() {
        LectureApplication lectureApplication = new LectureApplication();
        lectureApplication.setId(this.id);
        lectureApplication.setUserId(this.userId);
        return lectureApplication;
    }

    @PrePersist
    public void defaultData() {
        createdAt = LocalDateTime.now();
    }
}