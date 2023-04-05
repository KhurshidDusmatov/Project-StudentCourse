package com.company.service;


import com.company.dto.BetweenDateDTO;
import com.company.dto.StudentDTO;
import com.company.entity.StudentEntity;
import com.company.exp.AppBadRequestException;
import com.company.exp.StudentNotFoundException;
import com.company.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
public class StudentService {
    @Autowired
    private StudentRepository studentRepository; // StudentRepositoryImpl

    public StudentDTO getById(Integer id) {
        StudentEntity entity = get(id);
        StudentDTO dto = new StudentDTO();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setSurname(entity.getSurname());
        dto.setAge(entity.getAge());
        dto.setLevel(entity.getLevel());
        dto.setGender(entity.getGender());
        dto.setCreatedDate(entity.getCreatedDate());
        return dto;
    }

    public boolean update(Integer id, StudentDTO dto) {
        StudentEntity entity = get(id);
        entity.setName(dto.getName());
        entity.setSurname(dto.getSurname());
        entity.setAge(dto.getAge());
        entity.setLevel(dto.getLevel());
        entity.setGender(dto.getGender());
//        entity.setCreatedDate(LocalDateTime.now());
        check(dto);
        studentRepository.save(entity);
        return true;
    }

    public StudentDTO create(StudentDTO dto) {
        StudentEntity entity = new StudentEntity();
        entity.setName(dto.getName());
        entity.setSurname(dto.getSurname());
        entity.setAge(dto.getAge());
        entity.setLevel(dto.getLevel());
        entity.setGender(dto.getGender());
        entity.setCreatedDate(LocalDate.now());
        check(dto);
        studentRepository.save(entity);
        dto.setId(entity.getId());
        dto.setCreatedDate(entity.getCreatedDate());
        return dto;
    }

    public List<StudentDTO> getAll() {
        Iterable<StudentEntity> iterable = studentRepository.findAll();
        List<StudentDTO> dtoList = new LinkedList<>();

        iterable.forEach(entity -> {
            StudentDTO dto = new StudentDTO();
            dto.setId(entity.getId());
            dto.setName(entity.getName());
            dto.setSurname(entity.getSurname());
            dto.setAge(entity.getAge());
            dto.setLevel(entity.getLevel());
            dto.setGender(entity.getGender());
            dto.setCreatedDate(entity.getCreatedDate());
            dtoList.add(dto);
        });
        return dtoList;
    }

    public boolean delete(Integer id) {
        StudentEntity entity = get(id);
        studentRepository.delete(entity);
        return true;
    }

    public StudentEntity get(Integer id) {
        Optional<StudentEntity> optional = studentRepository.findById(id);
        if (optional.isEmpty()) {
            throw new StudentNotFoundException("No student with this ID was found : " + id);
        }
        return optional.get();
    }

    public List<StudentDTO> getListByGivenDate(LocalDate date) {
        List<StudentEntity> entities = studentRepository.findAllByCreatedDate(date);
        return parseToDto(entities);
    }

    public List<StudentDTO> getListByGivenDates(BetweenDateDTO dto) {
        List<StudentEntity> entities = studentRepository.findAllByCreatedDateBetween(dto.getFromDate(), dto.getToDate());
        return parseToDto(entities);
    }

//    public List<StudentDTO> getBySomeField(String someField) {
//        List<StudentEntity> entities = studentRepository.findAllByNameOrSurnameOrAgeOrLevelOrGender();
//        List<StudentDTO> dtos = new LinkedList<>();
//        entities.forEach(entity -> {
//            StudentDTO dto = new StudentDTO();
//            dto.setId(entity.getId());
//            dto.setName(entity.getName());
//            dto.setSurname(entity.getSurname());
//            dto.setAge(entity.getAge());
//            dto.setLevel(entity.getLevel());
//            dto.setGender(entity.getGender());
//            dto.setCreatedDate(entity.getCreatedDate());
//            dtos.add(dto);
//        });
//        return dtos;
//    }

    private void check(StudentDTO dto){
        if (dto.getName() == null || dto.getName().isBlank()) {
            throw new AppBadRequestException("Name is invalid");
        }
        if (dto.getSurname() == null || dto.getSurname().isBlank()) {
            throw new AppBadRequestException("Surname is invalid");
        }
        if (dto.getAge() == null || dto.getAge() < 0) {
            throw new AppBadRequestException("Age is invalid");
        }
        if (dto.getLevel() == null || dto.getLevel().isBlank()) {
            throw new AppBadRequestException("Level is invalid");
        }
        if (dto.getGender() == null ) {
            throw new AppBadRequestException("Gender is invalid");
        }
    }

    public List<StudentDTO> parseToDto(List<StudentEntity> entities){
        List<StudentDTO> dtos = new LinkedList<>();
        entities.forEach(entity -> {
            StudentDTO dto = new StudentDTO();
            dto.setId(entity.getId());
            dto.setName(entity.getName());
            dto.setSurname(entity.getSurname());
            dto.setAge(entity.getAge());
            dto.setLevel(entity.getLevel());
            dto.setGender(entity.getGender());
            dto.setCreatedDate(entity.getCreatedDate());
            dtos.add(dto);
        });
        return dtos;
    }



}
