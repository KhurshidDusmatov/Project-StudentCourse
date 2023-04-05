package com.company.service;


import com.company.dto.StudentDTO;
import com.company.entity.StudentEntity;
import com.company.exp.AppBadRequestException;
import com.company.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        //...
        return dto;
    }

    public boolean update(Integer id, StudentDTO studentDTO) {
        StudentEntity entity = get(id);
        entity.setName(studentDTO.getName());
        entity.setSurname(studentDTO.getSurname());
        studentRepository.save(entity);
        return true;
    }

    public StudentDTO crate(StudentDTO dto) {
        StudentEntity entity = new StudentEntity();
        entity.setName(dto.getName());
        entity.setSurname(dto.getSurname());
        if (dto.getName() == null || dto.getName().isBlank()) {
            throw new AppBadRequestException("Name qani?");
        }
        if (dto.getSurname() == null || dto.getSurname().isBlank()) {
            throw new AppBadRequestException("Surname qani?");
        }

        studentRepository.save(entity);
        dto.setId(entity.getId());
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
            //...
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
            throw new AppBadRequestException("Student not found: " + id);
        }
        return optional.get();
    }

    public void test() {
//        StudentEntity entity = studentRepository.findByPhone("123");
//        System.out.println(entity);

        Optional<StudentEntity> entity = studentRepository.findByPhone("123");
        System.out.println(entity);
    }
}
