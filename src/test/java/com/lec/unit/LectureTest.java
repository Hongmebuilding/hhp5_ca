package com.lec.unit;

import com.lec.model.domain.Lecture;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class LectureTest {

    private Lecture lecture;

    @BeforeEach
    void setUp() {
        lecture = new Lecture(
                1L,
                "Test Lecture",
                0,
                10,
                LocalDateTime.now().plusDays(7),
                LocalDateTime.now()
        );
    }

    @Test
    void addCnt_ShouldIncrementCountMultipleTimes() {
        // Arrange
        int initialCount = lecture.getCount();
        int incrementTimes = 5;

        // Act
        for (int i = 0; i < incrementTimes; i++) {
            lecture.addCnt();
        }

        // Assert
        assertEquals(initialCount + incrementTimes, lecture.getCount());
    }
}