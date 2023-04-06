package com.company.service;

import com.company.dto.ResponseDTO;
import com.company.dto.StudentCourseMarkDTO;
import com.company.entity.CourseEntity;
import com.company.entity.StudentCourseMarkEntity;
import com.company.entity.StudentEntity;
import com.company.exp.CourseNotFoundException;
import com.company.exp.StudentCourseMarkException;
import com.company.exp.StudentNotFoundException;
import com.company.repository.CourseRepository;
import com.company.repository.StudentCourseMarkRepository;
import com.company.repository.StudentRepository;
import org.springframework.aop.target.LazyInitTargetSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Array;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
public class StudentCourseMarkService {
    @Autowired
    private StudentCourseMarkRepository studentCourseMarkRepository;
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private CourseRepository courseRepository;

    public StudentCourseMarkDTO create(StudentCourseMarkDTO dto) {
        Optional<StudentEntity> student = studentRepository.findById(dto.getStudentId());
        if (student.isEmpty()) {
            throw new StudentNotFoundException("Student not found : " + dto.getStudentId());
        }
        Optional<CourseEntity> course = courseRepository.findById(dto.getCourseId());
        if (course.isEmpty()) {
            throw new CourseNotFoundException("Course not found : " + dto.getCourseId());
        }
        StudentCourseMarkEntity entity = new StudentCourseMarkEntity();
        entity.setStudent(student.get());
        entity.setCourse(course.get());
        entity.setMark(dto.getMark());
        studentCourseMarkRepository.save(entity);
        dto.setId(entity.getId());
        dto.setCreatedDate(entity.getCreatedDate());
        return dto;
    }

    public StudentCourseMarkDTO update(Integer id, StudentCourseMarkDTO dto) {
        Optional<StudentEntity> student = studentRepository.findById(dto.getStudentId());
        if (student.isEmpty()) {
            throw new StudentNotFoundException("Student not found : " + dto.getStudentId());
        }
        Optional<CourseEntity> course = courseRepository.findById(dto.getCourseId());
        if (course.isEmpty()) {
            throw new CourseNotFoundException("Course not found : " + dto.getCourseId());
        }
        StudentCourseMarkEntity entity = get(id);
        entity.setStudent(student.get());
        entity.setCourse(course.get());
        entity.setMark(dto.getMark());
        studentCourseMarkRepository.save(entity);
        dto.setId(entity.getId());
        dto.setCreatedDate(entity.getCreatedDate());
        return dto;
    }

    private StudentCourseMarkEntity get(Integer id){
        Optional<StudentCourseMarkEntity> studentCourseMark = studentCourseMarkRepository.findById(id);
        if (studentCourseMark.isEmpty()){
            throw new StudentCourseMarkException("StudentCourseMark not found ");
        }
        return studentCourseMark.get();
    }


    public StudentCourseMarkDTO getById(Integer id) {
        StudentCourseMarkEntity entity = get(id);
        StudentCourseMarkDTO dto = new StudentCourseMarkDTO();
        return toDTO(entity, dto);
    }

    public ResponseDTO getAllDetailById(Integer id) {
        StudentCourseMarkEntity entity = get(id);
        ResponseDTO dto = new ResponseDTO();
        dto.setScmId(entity.getId());
        dto.setSId(entity.getStudent().getId());
        dto.setSName(entity.getStudent().getName());
        dto.setSSurname(entity.getStudent().getSurname());
        dto.setCId(entity.getCourse().getId());
        dto.setCName(entity.getCourse().getName());
        dto.setMark(entity.getMark());
        dto.setCreatedDate(entity.getCreatedDate());
        return dto;
    }

    public boolean delete(Integer id) {
        studentCourseMarkRepository.deleteById(id);
        return true;
    }

    public List<StudentCourseMarkDTO> getAll() {
        Iterable<StudentCourseMarkEntity> all = studentCourseMarkRepository.findAll();
        List<StudentCourseMarkDTO> dtos = new LinkedList<>();
        all.forEach(entity -> {
            StudentCourseMarkDTO dto = new StudentCourseMarkDTO();
            StudentCourseMarkDTO toDTO = toDTO(entity, dto);
            dtos.add(toDTO);
        });
        return dtos;
    }

       private StudentCourseMarkDTO toDTO(StudentCourseMarkEntity entity, StudentCourseMarkDTO dto){
           dto.setId(entity.getId());
           dto.setCourseId(entity.getCourse().getId());
           dto.setStudentId(entity.getStudent().getId());
           dto.setMark(entity.getMark());
           dto.setCreatedDate(entity.getCreatedDate());
           return dto;
       }
}
