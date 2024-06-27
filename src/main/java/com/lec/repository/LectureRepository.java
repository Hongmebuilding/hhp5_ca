package com.lec.repository;

import com.lec.model.domain.Lecture;
import com.lec.model.domain.LectureApplication;

import java.util.List;

public interface LectureRepository {
    Lecture findById(Long lectureId);
    boolean save(Lecture lecture);
    List<Lecture> findAll();
    boolean isEnrolled(Long userId, Long lectureId);
    List<LectureApplication> getLectureRegistrationList(Long lectureId);
    boolean saveLectureApplication(Lecture lecture, Long userId);
}
