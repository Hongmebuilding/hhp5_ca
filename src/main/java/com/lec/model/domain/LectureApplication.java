package com.lec.model.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LectureApplication {
    private Long id;
    private Long userId;
    private Long lectureId;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
