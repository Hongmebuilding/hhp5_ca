package com.lec.controller;

import com.lec.model.dto.CreateLectureDto;
import com.lec.service.LectureService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/lectures")
@RestController
@RequiredArgsConstructor
public class AdminLectureController {
    private final LectureService lectureService;

    @PostMapping
    public boolean addLecture(@RequestBody CreateLectureDto lecture) {
        return lectureService.addLecture(lecture);
    }
}
