package com.lec.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lec.model.domain.Lecture;
import com.lec.model.dto.CreateLectureDto;
import com.lec.model.dto.LectureUserDto;
import com.lec.service.LectureService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.Arrays;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application-test.yml")
@ActiveProfiles("test")
public class LectureControllerTest{

    @MockBean
    private LectureService lectureService;
    @Autowired
    protected MockMvc mockMvc;
    @Autowired
    protected ObjectMapper objectMapper;

    @Test
    public void testCreateLecture() throws Exception {
        LocalDateTime time = LocalDateTime.of(2024, Month.DECEMBER, 11, 13, 30);

        CreateLectureDto createLectureDto = new CreateLectureDto(
                "아이고...",
                30,
                time
                );
        mockMvc.perform(
                        post("/lectures")
                                .content(objectMapper.writeValueAsString(createLectureDto))
                                .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk());
    }


    @Test
    public void testApplyLecture() throws Exception {
        LectureUserDto dto = new LectureUserDto();
        dto.setLectureId(1L);
        dto.setUserId(1L);

        mockMvc.perform(post("/lectures/apply")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isOk());
    }

    @Test
    public void testGetLectures() throws Exception {
        Lecture lecture1 = new Lecture();
        Lecture lecture2 = new Lecture();
        when(lectureService.getLectures()).thenReturn(Arrays.asList(lecture1, lecture2));

        mockMvc.perform(get("/lectures"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.length()").value(2));
    }

    @Test
    public void testIsEnrolled() throws Exception {
        when(lectureService.isEnrolled(anyLong(), anyLong())).thenReturn(true);

        mockMvc.perform(get("/lectures/application/1?lectureId=1"))
                .andExpect(status().isOk());
    }

    @Test
    public void testApplyLectureConcurrency() throws Exception {
        int numberOfThreads = 10;
        ExecutorService executorService = Executors.newFixedThreadPool(numberOfThreads);
        CountDownLatch latch = new CountDownLatch(numberOfThreads);
        AtomicInteger successCount = new AtomicInteger();

        for (int i = 0; i < numberOfThreads; i++) {
            final int userId = i;
            executorService.submit(() -> {
                    LectureUserDto dto = new LectureUserDto();
                    dto.setLectureId(1L);
                    dto.setUserId((long) userId);

                String content = null;
                try {
                    content = objectMapper.writeValueAsString(dto);
                } catch (JsonProcessingException e) {
                    throw new RuntimeException(e);
                }

                try {
                    mockMvc.perform(post("/lectures/apply")
                                        .contentType(MediaType.APPLICATION_JSON)
                                        .content(content))
                                .andExpect(result -> {
                                    if (result.getResponse().getStatus() == 10) {
                                        successCount.incrementAndGet();
                                    }
                                });
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }

            });
        }

        latch.await(); // 모든 스레드가 작업을 마칠 때까지 대기
        executorService.shutdown();

        // 여기서 강의의 최대 수강 인원을 확인하고 그 값과 비교
        int expectedMaxCapacity = 100; // 예시 값, 실제 강의의 최대 수강 인원으로 변경해야 함
        assertEquals(expectedMaxCapacity, successCount.get(),
                "성공적으로 등록된 수강 신청 수가 최대 수강 인원과 일치해야 합니다.");
    }
}