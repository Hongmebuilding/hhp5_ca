package com.lec.model.domain;

import com.lec.model.dto.CreateLectureDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Lecture {
    private Long lectureId;
    private String title;
    private int count;
    private int capacity;
    private LocalDateTime startDate;
    private LocalDateTime createdAt;

    public Lecture(CreateLectureDto lecture) {
        this.title = lecture.getTitle();
        this.capacity = lecture.getCapacity();
        this.startDate = lecture.getStartDate();
    }

    public void addCnt() {
        this.count += 1;
    }

    public boolean isLectureFull() {
        return this.getCount() >= this.getCapacity();
    }
}
