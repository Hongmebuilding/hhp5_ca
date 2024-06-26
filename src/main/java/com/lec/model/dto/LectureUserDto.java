package com.lec.model.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LectureUserDto {
    @NonNull
    @NotEmpty
    private Long lectureId;
    @NonNull
    @NotEmpty
    private Long userId;
}
