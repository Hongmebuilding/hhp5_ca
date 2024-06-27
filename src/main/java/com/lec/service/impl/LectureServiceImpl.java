package com.lec.service.impl;

import com.lec.exception.CustomException;
import com.lec.model.domain.Lecture;
import com.lec.model.domain.LectureApplication;
import com.lec.model.dto.CreateLectureDto;
import com.lec.model.vo.ErrorCode;
import com.lec.repository.LectureRepository;
import com.lec.service.LectureService;
import com.lec.validation.LectureValidator;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LectureServiceImpl implements LectureService {
    private final LectureRepository lectureRepository;
    private final LectureValidator validator;
    @Override
    @Transactional
    public void applyLecture(Long lectureId, Long userId) {
        Lecture lecture = lectureRepository.findById(lectureId);
        if (lecture == null) {
            throw new CustomException(HttpStatus.NOT_FOUND, ErrorCode.NO_DATA);
        }

        //TODO jpql
        List<LectureApplication> registrationList = lectureRepository.getLectureRegistrationList(lectureId);
        validator.validateLectureApplication(lecture, registrationList, userId);

        lecture.addCnt();

        boolean isSaved = lectureRepository.saveLectureApplication(lecture, userId);
        if (!isSaved) {
            throw new CustomException(HttpStatus.FORBIDDEN, ErrorCode.FAIL);
        }
    }

    @Override
    public List<Lecture> getLectures() {
        return lectureRepository.findAll();
    }

    @Override
    public boolean isEnrolled(Long userId, Long lectureId) {
        return lectureRepository.isEnrolled(userId, lectureId);
    }

    @Override
    @Transactional
    public void addLecture(CreateLectureDto lectureDto) {
        Lecture lecture = new Lecture(lectureDto);
        boolean isSaved = lectureRepository.save(lecture);
        if (!isSaved) {
            throw new CustomException(HttpStatus.FORBIDDEN, ErrorCode.FAIL);
        }
    }
}
