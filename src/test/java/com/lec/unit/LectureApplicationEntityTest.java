package com.lec.unit;

import com.lec.model.entity.LectureApplicationEntity;
import com.lec.model.entity.LectureEntity;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class LectureApplicationEntityTest {

    @Test
    void initialize_WithAllFieldsSet_ShouldInitializeCorrectly() {
        // Arrange
        LectureApplicationEntity entity = LectureApplicationEntity.builder()
                .userId(1L)
                .lecture(null)
                .build();

        // Act
        entity.initialize();

        // Assert
        assertNotNull(entity.getCreatedAt());
    }
}