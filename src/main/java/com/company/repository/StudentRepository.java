package com.company.repository;

import com.company.entity.StudentEntity;
import com.company.enums.Gender;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface StudentRepository extends CrudRepository<StudentEntity, Integer> {
    List<StudentEntity> findAllByCreatedDate(LocalDateTime date);

    List<StudentEntity> findAllByCreatedDateBetween(LocalDateTime fromDate, LocalDateTime toDate);

    List<StudentEntity> findByNameOrSurnameLikeOrAgeBetween(String name, String surname
            , Integer ageFrom, Integer ageTo);

}
