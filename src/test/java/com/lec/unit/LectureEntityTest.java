package com.lec.unit;

import com.lec.model.entity.LectureEntity;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


class LectureEntityTest {

    @Test
    void initialize_WithAllFieldsSet_ShouldInitializeCorrectly() {
        // Arrange
        LectureEntity entity = LectureEntity.builder()
                .title("Test Lecture")
                .capacity(30)
                .startDate(LocalDateTime.now().plusDays(7))
                .build();
        entity.setTitle("Test Lecture");
        entity.setCapacity(30);
        entity.setStartDate(LocalDateTime.now().plusDays(7));

        // Act
        entity.initialize();

        // Assert
        assertNotNull(entity.getCreatedAt());
        assertEquals(0, entity.getCount());
    }
}