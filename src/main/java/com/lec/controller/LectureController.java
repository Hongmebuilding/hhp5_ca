package com.lec.controller;

import com.lec.model.domain.Lecture;
import com.lec.model.dto.LectureUserDto;
import com.lec.service.LectureService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequestMapping("/lectures")
@RestController
@RequiredArgsConstructor
public class LectureController {
    private final LectureService lectureService;

    @PostMapping("/apply")
    public void applyLecture(@RequestBody LectureUserDto lectureUserDto) {
        this.lectureService.applyLecture(lectureUserDto.getLectureId(), lectureUserDto.getUserId());
    }

    @GetMapping()
    public List<Lecture> getLectures() {
        return lectureService.getLectures();
    }

    @GetMapping("/application/{userId}")
    public boolean isEnrolled(@PathVariable Long userId, @RequestParam Long lectureId) {
        return this.lectureService.isEnrolled(userId, lectureId);
    }
}
