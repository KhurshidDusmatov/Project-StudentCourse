package com.company.repository;

import com.company.entity.StudentCourseMarkEntity;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDate;
import java.util.List;

public interface StudentCourseMarkRepository extends CrudRepository<StudentCourseMarkEntity, Integer> {
    List<StudentCourseMarkEntity> findAllByStudentIdAndCreatedDateBetween(Integer id, LocalDate fromDate, LocalDate toDate);
    List<StudentCourseMarkEntity> findByCreatedDateAndStudentId(LocalDate date, Integer id);
    List<StudentCourseMarkEntity> findAllByStudentIdOrderByCreatedDateDesc(Integer id);
}

