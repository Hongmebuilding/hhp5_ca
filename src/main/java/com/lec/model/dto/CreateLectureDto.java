package com.lec.model.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CreateLectureDto {
    private String title;
    private Integer capacity;
    private LocalDateTime startDate;

}
