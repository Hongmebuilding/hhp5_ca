package com.lec.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class LectureDto {
    private Long lectureId;
    private String title;
    private int count;
    private int capacity;
    private long startDate;
    private long createdAt;
}

