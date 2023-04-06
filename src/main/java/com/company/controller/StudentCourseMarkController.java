package com.company.controller;

import com.company.dto.BetweenDateDTO;
import com.company.dto.ResponseDTO;
import com.company.dto.StudentCourseMarkDTO;
import com.company.service.StudentCourseMarkService;
import org.springframework.aop.target.LazyInitTargetSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
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

    @GetMapping(value = "/get_by_given_date/{date}/{id}")
    public ResponseEntity<?> getByGivenDate(@PathVariable("date") LocalDate date, Integer id) {
        List<StudentCourseMarkDTO> dto = studentCourseMarkService.getMarkByGivenDate(date, id);
        return ResponseEntity.ok(dto);
    }

    //8
    @GetMapping(value = "/get-student-mark-between-date/{id}")
    public ResponseEntity<List<StudentCourseMarkDTO>> getStudentMarkBetweenDate(@PathVariable("id") Integer id, @RequestBody BetweenDateDTO dto){
        List<StudentCourseMarkDTO> list = studentCourseMarkService.getStudentMarkBetweenDate(id, dto);
        return ResponseEntity.ok(list);
    }

    @GetMapping(value = "get-marks-order-by-desc/{id}")
    public ResponseEntity<List<StudentCourseMarkDTO>> getMarksOrderByDesc(@PathVariable("id") Integer id){
        List<StudentCourseMarkDTO> list = studentCourseMarkService.getMarksOrderByDesc(id);
        return ResponseEntity.ok(list);
    }
}
