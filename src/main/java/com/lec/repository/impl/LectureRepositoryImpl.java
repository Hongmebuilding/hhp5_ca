package com.lec.repository.impl;

import com.lec.exception.CustomException;
import com.lec.model.domain.Lecture;
import com.lec.model.domain.LectureApplication;
import com.lec.model.entity.LectureApplicationEntity;
import com.lec.model.entity.LectureEntity;
import com.lec.model.vo.ErrorCode;
import com.lec.repository.LectureJpaRepository;
import com.lec.repository.LectureRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class LectureRepositoryImpl implements LectureRepository {
    private final LectureJpaRepository jpaRepository;

    @Override
    public Lecture findById(Long lectureId) {
        return jpaRepository.findById(lectureId)
                .orElseThrow(() -> new CustomException(ErrorCode.NO_DATA))
                .to();
    }

    @Override
    public LectureEntity save(Lecture lecture) {
        LectureEntity lectureEntity = LectureEntity.from(lecture);
        jpaRepository.save(lectureEntity);
        return lectureEntity;
    }

    @Override
    public List<Lecture> findAll() {
        List<LectureEntity> lectureEntities = jpaRepository.findAll();
        return lectureEntities.stream().map(LectureEntity::to).toList();
    }

    @Override
    public boolean isEnrolled(Long userId, Long lectureId) {
        return jpaRepository.findById(lectureId)
                .orElseThrow(() -> new CustomException(ErrorCode.NO_DATA))
                .getLectureApplications().stream()
                .anyMatch(v -> v.getUserId().equals(userId));
    }

    @Override
    public List<LectureApplication> getLectureRegistrationList(Long lectureId) {
        return jpaRepository.findById(lectureId)
                .orElseThrow(() -> new CustomException(ErrorCode.NO_DATA))
                .getLectureApplications().stream()
                .map(LectureApplicationEntity::to)
                .toList();
    }
}
