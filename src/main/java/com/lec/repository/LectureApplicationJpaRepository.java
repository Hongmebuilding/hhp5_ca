package com.lec.repository;

import com.lec.model.entity.LectureApplicationEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LectureApplicationJpaRepository extends JpaRepository<LectureApplicationEntity, Long> {
}
