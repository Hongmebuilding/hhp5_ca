package com.lec.validation;

import com.lec.exception.CustomException;
import com.lec.model.domain.Lecture;
import com.lec.model.domain.LectureApplication;
import com.lec.model.vo.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class LectureValidator {

    public void validateLectureApplication(Lecture lecture, List<LectureApplication> registrationList, Long userId) {
        if (isUserAlreadyApplied(registrationList, userId)) {
            throw new CustomException(HttpStatus.CONFLICT, ErrorCode.ALREADY_APPLIED);
        }
        if (lecture.isLectureFull()) {
            throw new CustomException(HttpStatus.INSUFFICIENT_STORAGE, ErrorCode.LECTURE_FULL);
        }
        lecture.addCnt();
    }

    private boolean isUserAlreadyApplied(List<LectureApplication> registrationList, Long userId) {
        return registrationList.stream()
                .anyMatch(v -> v.getUserId().equals(userId));
    }

}
