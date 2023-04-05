package com.company.repository;

import com.company.entity.CourseEntity;
import org.apache.catalina.LifecycleState;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDate;
import java.util.List;

public interface CourseRepository extends CrudRepository<CourseEntity, Integer> {
       List<CourseEntity> findAllByPriceBetween(Double fromPrice, Double untilPrice);
       List<CourseEntity> findAllByCreatedDateBetween(LocalDate fromDate, LocalDate toDate);
}
