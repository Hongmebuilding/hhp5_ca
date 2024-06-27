package com.lec.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.lec.model.domain.Lecture;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "lecture")
@EntityListeners(AuditingEntityListener.class)
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@NamedEntityGraph(
        name = "lectureApplications",
        attributeNodes = @NamedAttributeNode("lectureApplications")
)
public class LectureEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(min = 1, max = 255)
    private String title;

    private Integer count;

    private Integer capacity;

    private LocalDateTime startDate;

    private LocalDateTime createdAt;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "lecture", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnoreProperties("lecture")
    private List<LectureApplicationEntity> lectureApplications = new ArrayList<>();

    public static LectureEntity from(Lecture lecture) {
        LectureEntity entity = new LectureEntity();
        entity.setId(lecture.getLectureId());
        entity.setTitle(lecture.getTitle());
        entity.setCapacity(lecture.getCapacity());
        entity.setStartDate(lecture.getStartDate());
        entity.setCount(lecture.getCount());
        entity.setCreatedAt(lecture.getCreatedAt());
        return entity;
    }

    public Lecture to() {
        Lecture lecture = new Lecture();
        lecture.setLectureId(this.id);
        lecture.setTitle(this.title);
        lecture.setCount(this.count);
        lecture.setCapacity(this.capacity);
        lecture.setStartDate(this.startDate);
        lecture.setCreatedAt(this.createdAt);
        return lecture;
    }

    @PrePersist
    public void defaultData() {
        count = 0;
        createdAt = LocalDateTime.now();
    }
}