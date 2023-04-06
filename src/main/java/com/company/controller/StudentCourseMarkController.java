package com.company.controller;

import com.company.dto.ResponseDTO;
import com.company.dto.StudentCourseMarkDTO;
import com.company.service.StudentCourseMarkService;
import org.springframework.aop.target.LazyInitTargetSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/student-course-mark")
public class StudentCourseMarkController {
    @Autowired
    private StudentCourseMarkService studentCourseMarkService;

    @PostMapping(value = "/create")
    public ResponseEntity<StudentCourseMarkDTO> create(@RequestBody StudentCourseMarkDTO studentCourseMarkDTO){
        StudentCourseMarkDTO dto = studentCourseMarkService.create(studentCourseMarkDTO);
        return  ResponseEntity.ok(dto);
    }

    @PutMapping(value = "/update/{id}")
    public ResponseEntity<StudentCourseMarkDTO> update(@PathVariable("id") Integer id, @RequestBody StudentCourseMarkDTO studentCourseMarkDTO){
        StudentCourseMarkDTO dto = studentCourseMarkService.update(id,studentCourseMarkDTO);
        return  ResponseEntity.ok(dto);
    }

    @GetMapping(value = "get/{id}")
    public ResponseEntity<StudentCourseMarkDTO> getById(@PathVariable("id") Integer id){
        StudentCourseMarkDTO dto = studentCourseMarkService.getById(id);
        return ResponseEntity.ok(dto);
    }

    @GetMapping(value = "get-all-detail/{id}")
    public ResponseEntity<ResponseDTO> getAllDetailById(@PathVariable("id") Integer id){
        ResponseDTO dto = studentCourseMarkService.getAllDetailById(id);
        return ResponseEntity.ok(dto);
    }

    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(studentCourseMarkService.delete(id));
    }

    @GetMapping(value = "/list")
    public ResponseEntity<List<StudentCourseMarkDTO>> getAll(){
        List<StudentCourseMarkDTO> list = studentCourseMarkService.getAll();
        return ResponseEntity.ok(list);
    }


}
