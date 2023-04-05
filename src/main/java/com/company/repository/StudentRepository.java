package com.company.repository;

import com.company.entity.StudentEntity;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface StudentRepository extends CrudRepository<StudentEntity, Integer> {
//    StudentEntity findByPhone(String phone);   // from StudentEntity where phone =:phone

    Optional<StudentEntity> findByPhone(String phone); // from StudentEntity where phone =:phone

    ///StudentEntity findByName(String name); // from StudentEntity where name =:name

    List<StudentEntity> findByName(String name);  // from StudentEntity where name =:name

    List<StudentEntity> findAllByName(String name);  // from StudentEntity where name =:name

    // from StudentEntity where name =:name and surname =:surname
    StudentEntity findByNameAndSurname(String name, String surname);

    // from StudentEntity where name =:name or phone =:phone
    StudentEntity findByNameOrPhone(String name, String phone);

    // from StudentEntity where age between :ageFrom and :ageTo
    List<StudentEntity> findAllByAgeBetween(Integer ageFrom, Integer ageTo);

    // from StudentEntity where age between :ageFrom and :ageTo
    List<StudentEntity> findAllByCreatedDateBetween(LocalDateTime dateFrom, LocalDateTime dateTo);
    // --  01.04.2023 00:00:00
    // 01.04.2023 12:50
    // 02.04.2023 12:50
    // 03.04.2023 12:50
    // 04.04.2023 12:50
    // --  04.04.2023 23:59:00
    // 05.04.2023 12:50

    List<StudentEntity> findByNameOrSurnameLikeOrAgeBetween(String name, String surname
            , Integer ageFrom, Integer ageTo);

    // from StudentEntity where name =:name order by age
    List<StudentEntity> findByNameOrderByAge(String name);

    // from StudentEntity where name =:name order by age
    List<StudentEntity> findTopByNameOrderByAge(String name); // limit 1

    List<StudentEntity> findFirstByNameOrderByAge(String name); // limit 1

    List<StudentEntity> findTop3ByNameOrderByAge(String name); // limit 3

    List<StudentEntity> findTop10ByNameOrderByAge(String name); // limit 10
}
