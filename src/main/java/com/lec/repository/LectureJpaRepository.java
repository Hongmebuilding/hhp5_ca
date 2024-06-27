package com.lec.repository;

import com.lec.model.entity.LectureEntity;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface LectureJpaRepository extends JpaRepository<LectureEntity, Long> {
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("select l from LectureEntity l where l.id = :id")
    @EntityGraph(value = "lectureApplications")
    Optional<LectureEntity> findByIdFetch(long id);
}
