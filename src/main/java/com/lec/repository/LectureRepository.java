package com.lec.repository;

import com.lec.model.domain.Lecture;
import com.lec.model.domain.LectureApplication;
import com.lec.model.entity.LectureEntity;

import java.util.List;

public interface LectureRepository {
    Lecture findById(Long lectureId);
    LectureEntity save(Lecture lecture);
    List<Lecture> findAll();
    boolean isEnrolled(Long userId, Long lectureId);
    List<LectureApplication> getLectureRegistrationList(Long lectureId);
}
