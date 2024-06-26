package com.lec.service;

import com.lec.model.domain.Lecture;
import com.lec.model.dto.CreateLectureDto;

import java.util.List;

public interface LectureService {
    void applyLecture(Long lectureId, Long userId);

    List<Lecture> getLectures();

    boolean isEnrolled(Long userId, Long lectureId);

    boolean addLecture(CreateLectureDto lecture);
}
